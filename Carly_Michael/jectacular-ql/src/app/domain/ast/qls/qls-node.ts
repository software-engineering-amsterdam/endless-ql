import {QlsQuestion} from './qls-question';
import {Style} from './style';
import {Widget} from './widget';
import {QlsVisitor} from './visitors/qls-visitor';

export abstract class QlsNode {
  abstract accept<T>(visitor: QlsVisitor<T>): T;
}

export class QuestionWithAppliedStyles {
  constructor(readonly question: QlsQuestion, readonly styles: ReadonlyArray<Style>, readonly widget: Widget) { }
}
