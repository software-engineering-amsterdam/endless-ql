import {Expression} from './expression';
import {Variable} from './variable';
import {Location} from '../../location';
import * as _ from 'lodash';

export abstract class BinaryExpression extends Expression {

  constructor(public readonly left: Expression, public readonly right: Expression, readonly location: Location) {
    super(location);
  }
}
