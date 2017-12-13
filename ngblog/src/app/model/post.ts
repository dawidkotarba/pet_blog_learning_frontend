import {Author} from './author';
import {Comment} from './comment';
import {Label} from './label';

export class Post {
  id: number;
  subject: string;
  body: string;
  published: string;
  authors: Author[];
  comments: Comment[];
  labels: Label[];
}
