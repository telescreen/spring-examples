<script lang="ts">
import Vue from "vue";
import { Component, Inject } from "vue-property-decorator";
import { StompService } from "../services";

@Component
export default class App extends Vue {
  query: string = "";

  @Inject() stompService: StompService;

  query_OnUpdated() {
    this.stompService.publish(JSON.stringify({query: this.query}));
  }
}
</script>
