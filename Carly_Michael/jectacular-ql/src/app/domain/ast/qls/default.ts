import {WidgetType} from './widget-type';
import {Location} from '../location';

export class Default {
  constructor(public type: string, public widgetType: WidgetType, public location: Location) {}
}
