import {Section} from './section';
import {Default} from './default';
import {Location} from '../location';
import {Node, QuestionWithAppliedStyles} from './node';
import {Style} from './style';
import {Question as QlQuestion} from '../ql/question';
import {Widget} from './widget';

export class Page extends Node {
  constructor(public name: string, public sections: Section[], public location: Location, public defaultSettings?: Default) {
    super();
  }

  getQuestions(parentStyles: ReadonlyArray<Style>): ReadonlyArray<QuestionWithAppliedStyles> {
    let questions = [];

    // get styles
    const updatedParentStyles: ReadonlyArray<Style> = this.defaultSettings && this.defaultSettings.styles.length > 0 ?
      parentStyles.concat(this.defaultSettings.styles) : parentStyles;

    const widgetType = this.defaultSettings ? this.defaultSettings.widget : Widget.Empty;

    for (const section of this.sections) {
      questions = questions.concat(section.getQuestions(updatedParentStyles, widgetType));
    }

    return questions;
  }

  checkStylesheet(parentDefaults: ReadonlyArray<Default>, allQuestions: QlQuestion[]): void {
    const defaults = this.defaultSettings ?  parentDefaults.concat(this.defaultSettings) : parentDefaults;

    for (const section of this.sections) {
      section.checkStylesheet(defaults, allQuestions);
    }
  }
}
