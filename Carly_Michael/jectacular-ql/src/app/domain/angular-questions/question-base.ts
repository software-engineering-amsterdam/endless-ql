import { FormGroup } from '@angular/forms';
import {LiteralType} from '../ast';

export class QuestionBase<T> {
  value: T;
  key: string;
  label: string;
  required: boolean;
  order: number;
  controlType: string;
  style: string;
  hiddenCondition: (form: FormGroup) => LiteralType;
  calculateValue: (form: FormGroup) => T;
  readonly: boolean;

  constructor(options: {
    value?: T,
    key?: string,
    label?: string,
    required?: boolean,
    order?: number,
    controlType?: string,
    hiddenCondition?: (form: FormGroup) => LiteralType,
    calculateValue?: (form: FormGroup) => T
    readonly?: boolean
  } = {}) {
    this.value = options.value;
    this.key = options.key || '';
    this.label = options.label || '';
    this.required = !!options.required;
    this.order = options.order === undefined ? 1 : options.order;
    this.controlType = options.controlType || '';
    this.hiddenCondition = options.hiddenCondition || (() => true);
    this.calculateValue = (() => this.value);
    this.readonly = false;
  }

  toCalculatedQuestion(calculateFunction: (form: FormGroup) => T) {
    this.calculateValue = calculateFunction;
    this.readonly = true;
  }
}
