import {Authority} from './authority';

export class User {
  id: number;
  username: string;
  firstname: string;
  lastname: string;
  enabled: string;
  accountNonExpired: string;
  accountNonLocked: string;
  credentialsNonExpired: string;
  authorities: Authority[];
}
