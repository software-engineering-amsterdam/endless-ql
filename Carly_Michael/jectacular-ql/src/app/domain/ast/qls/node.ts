import {Question} from './question';
import {Question as QlQuestion} from '../question';
import {Style} from './style';
import {Default} from './default';

export abstract class Node {
  getQuestions(parentStyles: ReadonlyArray<Style>): ReadonlyArray<QuestionWithAppliedStyles> {
    return [];
  }

  abstract checkStylesheet(parentDefaults: ReadonlyArray<Default>, allQuestions: QlQuestion[]);
}

export interface QuestionWithAppliedStyles {
  question: Question;
  styles: ReadonlyArray<Style>;
}
