import {Page} from './page';
import {Location} from '../location';

export class Stylesheet {
  constructor(public name: string, public pages: Page[], public location: Location) {}
}
