import {Location} from '../location';
import {Style} from './style';
import {Widget} from './widget';
import {QuestionType} from '../question-type';

export class Default {
  constructor(public type: QuestionType<any>, public widget: Widget, public styles: Style[], public location: Location) {}
}
