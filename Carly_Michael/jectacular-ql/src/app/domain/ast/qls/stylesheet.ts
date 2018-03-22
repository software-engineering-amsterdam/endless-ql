import {Page} from './page';
import {Location} from '../location';
import {QlsNode, QuestionWithAppliedStyles} from './qls-node';
import {Style} from './style';
import {DefaultStyling} from './default-styling';
import {QlQuestion as QlQuestion} from '../ql';
import * as _ from 'lodash';
import {MissingIdentifierError} from '../../errors';
import {QlsVisitor} from './visitors/qls-visitor';

export class Stylesheet extends QlsNode {
  constructor(readonly name: string, readonly pages: Page[], readonly location: Location) {
    super();
  }

  accept<T>(visitor: QlsVisitor<T>): T {
    return visitor.visitStylesheet(this);
  }
}
