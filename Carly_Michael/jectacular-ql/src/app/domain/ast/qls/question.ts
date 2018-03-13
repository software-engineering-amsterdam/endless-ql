import {Default} from './default';
import {Location} from '../location';
import {Widget} from './widget';
import {Node, QuestionWithAppliedStyles} from './node';
import {Style} from './style';

export class Question extends Node {
  constructor(public name: string, public type: Widget, public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(parentStyles: ReadonlyArray<Style>): ReadonlyArray<QuestionWithAppliedStyles> {
    const updatedParentStyles: ReadonlyArray<Style> = this.defaultSettings && this.defaultSettings.styles.length > 0 ?
      parentStyles.concat(this.defaultSettings.styles) : parentStyles;

    return [{question: this, styles: updatedParentStyles}];
  }
}
