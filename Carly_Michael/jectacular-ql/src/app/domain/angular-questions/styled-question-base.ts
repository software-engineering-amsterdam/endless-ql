import {QuestionBase} from './question-base';
import {Widget} from '../ast/qls';
import {FormGroup} from '@angular/forms';

export class StyledQuestionBase<T> extends QuestionBase<T> {

  constructor(key: string,
              label: string,
              value: T,
              type: string,
              controlType: string,
              readonly widget: Widget,
              readonly style: {[key: string]: string},
              hiddenCondition: (form: FormGroup) => boolean) {
    super(key, label, value, type, controlType, hiddenCondition);
  }
}
