import $ from 'jquery'
import { BASE_API_ENDPOINT } from '../constants'

class User {
  name: string
  authorities: string[]

  constructor(user: User) {
    this.name = user.name
    this.authorities = user.authorities
  }
}

class UserRepository {
  constructor() {}

  fetchUser(): Promise<User> {
    return new Promise((resolve) => {
      $.get(BASE_API_ENDPOINT + "/user/info").then((response) => {
        resolve(response);
      });
    });
  }
}

export { User, UserRepository }