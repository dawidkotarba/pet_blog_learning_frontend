import {Post} from './post';

export class Pagination {
  content: Post[];
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  numberOfElements: number;
  sort: string;
  size: number;
  number: number;
}
