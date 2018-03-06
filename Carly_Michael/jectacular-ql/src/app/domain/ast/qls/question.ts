import {Default} from './default';
import {Location} from '../location';
import {Widget} from './widget';

export class Question {
  constructor(public name: string, public type: Widget, public location: Location, public defaultSettings?: Default) {}
}
