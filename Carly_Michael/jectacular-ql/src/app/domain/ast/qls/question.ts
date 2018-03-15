import {Default} from './default';
import {Location} from '../location';
import {Widget} from './widget';
import {Node, QuestionWithAppliedStyles} from './node';
import {Style} from './style';
import {QuestionType} from '../question-type';
import {WidgetType} from './widget-type';
import {MissingIdentifierError, UnsupportedTypeError} from '../../errors';
import {Question as QlQuestion} from '../ql/question';
import * as _ from 'lodash';

export class Question extends Node {
  constructor(public name: string, public type: Widget, public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(parentStyles: ReadonlyArray<Style>, widgetParent: Widget): ReadonlyArray<QuestionWithAppliedStyles> {
    const updatedParentStyles: ReadonlyArray<Style> = this.defaultSettings && this.defaultSettings.styles.length > 0 ?
      parentStyles.concat(this.defaultSettings.styles) : parentStyles;

    const widget = this.type.type !== WidgetType.NONE ? this.type : widgetParent;

    return [new QuestionWithAppliedStyles(this, updatedParentStyles, widget)];
  }

  checkStylesheet(parentDefaults: ReadonlyArray<Default>, allQuestions: QlQuestion[]): void {
    const qlQuestion: QlQuestion = _.find(allQuestions, {name: this.name});

    if (!qlQuestion) {
      throw new MissingIdentifierError(`Question with name ${this.name} not found`);
    }

    if (this.type.type !== WidgetType.NONE) {
      this.throwIfQlsTypeDoesNotMatchQlType(qlQuestion.type, this.type.type);
      return;
    }

    if (this.defaultSettings && this.defaultSettings.type === qlQuestion.type) {
      this.throwIfQlsTypeDoesNotMatchQlType(qlQuestion.type, this.defaultSettings.widget.type);
    } else {
      for (let i = parentDefaults.length - 1; i >= 0; i++) {
        if (parentDefaults[i].type === qlQuestion.type) {
          this.throwIfQlsTypeDoesNotMatchQlType(qlQuestion.type, parentDefaults[i].widget.type);
          break;
        }
      }
    }
  }

  // throw an exception when an error is detected, otherwise do nothing
  private throwIfQlsTypeDoesNotMatchQlType(qlType: QuestionType, qlsType: WidgetType): void {
    if (qlsType === WidgetType.NONE) {
      throw new UnsupportedTypeError(`Expected a type for question ${this.name}`);
    }

    if (qlType === QuestionType.BOOLEAN && !(qlsType === WidgetType.CHECKBOX || qlsType === WidgetType.RADIO)) {
      throw new TypeError(`Question ${this.name} has type boolean but QLS defines something else than checkbox or radio.`);
    } else if (qlType === QuestionType.INT &&
        !(qlsType === WidgetType.TEXT || qlsType === WidgetType.SLIDER || qlsType === WidgetType.SPINBOX)) {
      throw new TypeError(`Question ${this.name} has type integer but QLS defines something else than text, slider or spinbox.`);
    } else if (qlType === QuestionType.STRING && !(qlsType === WidgetType.TEXT)) {
      throw new TypeError(`Question ${this.name} has type string but QLS defines something else than text.`);
    } else if (qlType === QuestionType.DATE && !(qlsType === WidgetType.TEXT)) {
      throw new TypeError(`Question ${this.name} has type date but QLS defines something else than text.`);
    }
  }
}
