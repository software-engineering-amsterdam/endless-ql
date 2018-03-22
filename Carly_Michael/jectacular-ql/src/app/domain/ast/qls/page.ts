import {Section} from './section';
import {DefaultStyling} from './default-styling';
import {Location} from '../location';
import {QlsNode, QuestionWithAppliedStyles} from './qls-node';
import {Style} from './style';
import {QlQuestion} from '../ql/ql-question';
import {Widget} from './widget';
import {QlsVisitor} from './visitors/collect-styles-for-question-visitor';

export class Page extends QlsNode {
  constructor(readonly name: string, readonly sections: Section[], readonly location: Location, readonly defaultSettings?: DefaultStyling) {
    super();
  }

  accept<T>(visitor: QlsVisitor<T>): T {
    return visitor.visitPage(this);
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

  checkStylesheet(parentDefaults: ReadonlyArray<DefaultStyling>, allQuestions: ReadonlyArray<QlQuestion>): void {
    const defaults = this.defaultSettings ?  parentDefaults.concat(this.defaultSettings) : parentDefaults;
    for (const section of this.sections) {
      section.checkStylesheet(defaults, allQuestions);
    }
  }
}
