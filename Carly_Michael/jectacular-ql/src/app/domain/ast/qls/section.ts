import {Question} from './question';
import {Default} from './default';
import {Location} from '../location';
import {Node, QuestionWithAppliedStyles} from './node';
import {Style} from './style';
import {WidgetType} from './widget-type';
import {Widget} from './widget';
import {Question as QlQuestion} from '../question';

export class Section extends Node {
  constructor(public name: string, public subSections: Section[], public questions: Question[],
              public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(parentStyles: ReadonlyArray<Style>, widgetTypeParent: Widget): ReadonlyArray<QuestionWithAppliedStyles> {
    let questions = [];
    const updatedParentStyles: ReadonlyArray<Style> = this.defaultSettings && this.defaultSettings.styles.length > 0 ?
      parentStyles.concat(this.defaultSettings.styles) : parentStyles;

    const widgetType = this.defaultSettings && this.defaultSettings.widget.type !== WidgetType.NONE ?
      this.defaultSettings.widget : widgetTypeParent;

    for (const section of this.subSections) {
      questions = questions.concat(section.getQuestions(updatedParentStyles, widgetType));
    }

    for (const question of this.questions) {
      questions = questions.concat(question.getQuestions(updatedParentStyles, widgetType));
    }

    return questions;
  }

  checkStylesheet(parentDefaults: ReadonlyArray<Default>, allQuestions: QlQuestion[]): void {
    const defaults = this.defaultSettings ?  parentDefaults.concat(this.defaultSettings) : parentDefaults;

    for (const section of this.subSections) {
      section.checkStylesheet(defaults, allQuestions);
    }

    for (const question of this.questions) {
      question.checkStylesheet(defaults, allQuestions);
    }
  }
}
