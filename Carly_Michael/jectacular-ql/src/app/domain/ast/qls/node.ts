import {Question} from './question';
import {Style} from './style';

export abstract class Node {
  getQuestions(parentStyles: ReadonlyArray<Style>): ReadonlyArray<QuestionWithAppliedStyles> {
    return [];
  }
}

export interface QuestionWithAppliedStyles {
  question: Question;
  styles: ReadonlyArray<Style>;
}
