import {Page} from './page';
import {Location} from '../location';
import {Node, QuestionWithAppliedStyles} from './node';
import {Style} from './style';

export class Stylesheet extends Node {
  constructor(public name: string, public pages: Page[], public location: Location) {
    super();
  }

  getQuestions(parentStyles: ReadonlyArray<Style>): ReadonlyArray<QuestionWithAppliedStyles> {
    let questions = [];

    for (const page of this.pages) {
      questions = questions.concat(page.getQuestions([]));
    }

    return questions;
  }

}
