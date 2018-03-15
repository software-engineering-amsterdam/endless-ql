import {QlsQuestion} from './qls-question';
import {QlQuestion} from '../ql';
import {Style} from './style';
import {Widget} from './widget';
import {Default} from './default';

export abstract class QlsNode {
  getQuestions(parentStyles: ReadonlyArray<Style>, widget: Widget): ReadonlyArray<QuestionWithAppliedStyles> {
    return [];
  }

  abstract checkStylesheet(parentDefaults: ReadonlyArray<Default>, allQuestions: QlQuestion[]);
}

export class QuestionWithAppliedStyles {
  constructor(readonly question: QlsQuestion, readonly styles: ReadonlyArray<Style>, readonly widget: Widget) { }
}
