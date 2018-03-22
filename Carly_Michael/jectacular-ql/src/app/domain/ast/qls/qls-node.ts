import {QlsQuestion} from './qls-question';
import {QlQuestion} from '../ql';
import {Style} from './style';
import {Widget} from './widget';
import {DefaultStyling} from './default-styling';
import {QlsVisitor} from './visitors/qls-visitor';

export abstract class QlsNode {
  getQuestions(parentStyles: ReadonlyArray<Style>, widget: Widget): ReadonlyArray<QuestionWithAppliedStyles> {
    return [];
  }

  abstract checkStylesheet(parentDefaults: ReadonlyArray<DefaultStyling>, allQuestions: ReadonlyArray<QlQuestion>);
  abstract accept<T>(visitor: QlsVisitor<T>): T;
}

export class QuestionWithAppliedStyles {
  constructor(readonly question: QlsQuestion, readonly styles: ReadonlyArray<Style>, readonly widget: Widget) { }
}
