import {Label} from './label';
import {Author} from './author';

export class PostOut {
  id: number;
  subject: string;
  body: string;
  published: string;
  authors: Author[];
  labels: Label[];
}
