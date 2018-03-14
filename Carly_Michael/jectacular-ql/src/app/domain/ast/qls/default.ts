import {Location} from '../location';
import {Style} from './style';
import {Widget} from './widget';

export class Default {
  constructor(public type: string, public widget: Widget, public styles: Style[], public location: Location) {}
}
