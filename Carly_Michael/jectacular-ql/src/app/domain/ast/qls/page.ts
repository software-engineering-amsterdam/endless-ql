import {Section} from './section';
import {Default} from './default';
import {Location} from '../location';
import {Node} from './node-interface';
import {Question} from './question';

export class Page extends Node {
  constructor(public name: string, public sections: Section[], public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(): Question[] {
    let questions = [];

    for (const section of this.sections) {
      questions = questions.concat(section.getQuestions());
    }

    return questions;
  }
}
