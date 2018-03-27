import {Section} from './section';
import {DefaultStyling} from './default-styling';
import {Location} from '../location';
import {QlsNode, QuestionWithAppliedStyles} from './qls-node';
import {Style} from './style';
import {QlQuestion} from '../ql/ql-question';
import {Widget} from './widget';
import {QlsVisitor} from './visitors/qls-visitor';

export class Page extends QlsNode {
  constructor(readonly name: string, readonly sections: Section[], readonly location: Location, readonly defaultSettings?: DefaultStyling) {
    super();
  }

  accept<T>(visitor: QlsVisitor<T>): T {
    return visitor.visitPage(this);
  }
}
