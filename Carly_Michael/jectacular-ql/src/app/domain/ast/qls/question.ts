import {Default} from './default';
import {Location} from '../location';
import {WidgetType} from './widget-type';

export class Question {
  constructor(public name: string, public type: WidgetType, public location: Location, public defaultSettings?: Default) {}
}
