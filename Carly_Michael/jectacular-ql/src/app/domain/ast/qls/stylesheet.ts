import {Page} from './page';
import {Location} from '../location';
import {Question} from './question';
import {Node} from './node-interface';

export class Stylesheet extends Node {
  constructor(public name: string, public pages: Page[], public location: Location) {
    super();
  }

  getQuestions(): Question[] {
    let questions = [];

    for (const page of this.pages) {
      questions = questions.concat(page.getQuestions());
    }

    return questions;
  }

}
