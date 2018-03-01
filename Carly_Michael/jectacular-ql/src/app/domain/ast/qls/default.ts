import {WidgetType} from './widget-type';
import {Location} from '../location';
import {Style} from './style';

export class Default {
  constructor(public type: string, public widgetType: WidgetType, public styles: Style[], public location: Location) {}
}
