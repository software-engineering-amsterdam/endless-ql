import {Question} from './question';
import {Question as QlQuestion} from '../ql/question';
import {Style} from './style';
import {Widget} from './widget';
import {Default} from './default';

export abstract class Node {
  getQuestions(parentStyles: ReadonlyArray<Style>, widget: Widget): ReadonlyArray<QuestionWithAppliedStyles> {
    return [];
  }

  abstract checkStylesheet(parentDefaults: ReadonlyArray<Default>, allQuestions: QlQuestion[]);
}

export class QuestionWithAppliedStyles {
  constructor(readonly question: Question, readonly styles: ReadonlyArray<Style>, readonly widget: Widget) { }
}
