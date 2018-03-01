import {Section} from './section';
import {Default} from './default';
import {Location} from '../location';

export class Page {
  constructor(public name: string, public sections: Section[], public location: Location, public defaultSettings?: Default) {}
}
