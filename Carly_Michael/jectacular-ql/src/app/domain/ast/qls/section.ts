import {Question} from './question';
import {Default} from './default';
import {Location} from '../location';
import {Node, QuestionWithAppliedStyles} from './node';
import {Style} from './style';

export class Section extends Node {
  constructor(public name: string, public subSections: Section[], public questions: Question[],
              public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(parentStyles: ReadonlyArray<Style>): ReadonlyArray<QuestionWithAppliedStyles> {
    let questions = [];
    const updatedParentStyles: ReadonlyArray<Style> = this.defaultSettings && this.defaultSettings.styles.length > 0 ?
      parentStyles.concat(this.defaultSettings.styles) : parentStyles;

    for (const section of this.subSections) {
      questions = questions.concat(section.getQuestions(updatedParentStyles));
    }

    for (const question of this.questions) {
      questions = questions.concat(question.getQuestions(updatedParentStyles));
    }

    return questions;
  }
}
