import {Location} from '../location';
import {StyleValue} from './style-value';

export class Style {
  constructor(public name: string, public value: StyleValue, public location: Location) { }
}
