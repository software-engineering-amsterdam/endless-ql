import {Question} from './question';
import {Default} from './default';
import {Location} from '../location';
import {Node} from './node-interface';

export class Section extends Node {
  constructor(public name: string, public subSections: Section[], public questions: Question[],
              public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(): Question[] {
    let questions = [];

    for (const section of this.subSections) {
      questions = questions.concat(section.getQuestions());
    }

    questions = questions.concat(this.questions);

    return questions;
  }
}
