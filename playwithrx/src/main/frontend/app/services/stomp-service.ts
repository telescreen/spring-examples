import * as Stomp from '@stomp/stompjs'
import { Subject } from 'rxjs/Rx'
import { BehaviorSubject } from 'rxjs/BehaviorSubject'

enum StompState {
  CLOSED = 0,
  TRYING,
  DISCONNECTING,
  SUBSCRIBED,
  CONNECTED
}

interface StompConfig {
  host: string;
  port: number;
  endpoint: string;
  ssl: boolean;

  username: string;
  password: string;

  publish: string[];
  subscribe: string[];

  heartbeat_in: number;
  heartbeat_out: number;

  debug: boolean;
}

class StompService {

  public state: BehaviorSubject<StompState>

  private config!: StompConfig;

  private client!: Stomp.Client;

  // Resolve Promise made to calling class, when connected
  private resolvePromise?: (...args: any[]) => void;

  public messages: Subject<Stomp.Message>;

  private timer?: number;

  constructor() {
    this.messages = new Subject<Stomp.Message>();
    this.state = new BehaviorSubject<StompState>(StompState.CLOSED);
  }

  configure(config?: StompConfig) {
    if (this.state.getValue() !== StompState.CLOSED) {
      throw Error("Already running");
    }
    if (config === null && this.config === null) {
      throw Error("No configuration");
    }

    if (config != null) {
      this.config = config;
    }

    let scheme = "ws";
    if (this.config.ssl) {
      scheme = "wss";
    }

    this.client = Stomp.client(`${scheme}://${this.config.host}:${this.config.port}/${this.config.endpoint}/websocket`);
    this.client.heartbeat.outgoing = this.config.heartbeat_out;
    this.client.heartbeat.incoming = this.config.heartbeat_in;

    // Set function to debug print messages
    if (this.config.debug || this.config.debug == null) {
      this.client.debug = this.debug;
    }
  }

  try_connect(): Promise<{}> {
    if (this.state.getValue() !== StompState.CLOSED) {
      throw Error("Can\'t try_connect() if not in CLOSED state");
    }
    if (this.client === null) {
      throw Error("Client not configured");
    }
    this.client.connect(
      this.config.username,
      this.config.password,
      this.on_connect,
      this.on_error
    );
    this.debug("Connecting...");
    this.state.next(StompState.TRYING);

    return new Promise(
      (resolve, reject) => this.resolvePromise = resolve
    );
  }

  public disconnect(): void {
    this.state.next(StompState.DISCONNECTING);

    if (this.timer) {
      clearTimeout(this.timer);
      this.timer = undefined;
    }

    if (this.client && this.client.connected) {
      this.client.disconnect(
        () => this.state.next(StompState.CLOSED)
      );
    }
  }

  public publish(message?: string) {
    for(const t of this.config.publish) {
      this.client.send(t, {}, message);
    }
  }

  public subscribe(): void {
    // Subscribe to our configured queues
    for (const t of this.config.subscribe) {
      this.client.subscribe(t, this.on_message, { ack: 'auto' });
    }

    // Update the state
    if (this.config.subscribe.length > 0) {
      this.state.next(StompState.SUBSCRIBED);
    }
  }

  /**
  * Callback Functions
  *
  * Note the method signature: () => preserves lexical scope
  * if we need to use this.x inside the function
  */
  public debug(...args: any[]): void {
    // Push arguments to this function into console.log
    if (console && console.log && console.log.apply) {
      console.log.apply(console, args);
    }
  }

  public on_connect = () => {
    this.debug("Connected");
    this.state.next(StompState.CONNECTED);

    this.subscribe();
    this.resolvePromise!();
    this.resolvePromise = undefined;

    this.timer = undefined;
  }

  public on_error = (error: string | Stomp.Message) => {
    if (typeof error === 'object') {
      error = (<Stomp.Message>error).body;
    }
    console.error(`Error: ${error}`);
    // Check for dropped connection and try reconnecting
    if (error.indexOf('Lost connection') !== -1) {

      // Reset state indicator
      this.state.next(StompState.CLOSED);

      this.debug("Reconneting in 5 seconds...");
      this.timer = setTimeout(() => {
        this.configure();
        this.try_connect();
      }, 5000);
    }
  }

  // On message RX, notify the Observable with the message object
  public on_message = (message: Stomp.Message) => {

    if (message.body) {
      this.messages.next(message);
    } else {
      console.error('Empty message received!');
    }
  }
}

export {
  StompState,
  StompConfig,
  StompService
}