import {Question} from './question';
import {Default} from './default';
import {Location} from '../location';

export class Section {
  constructor(public name: string, public questions: Question[], public location: Location, public defaultSettings?: Default) {}
}
