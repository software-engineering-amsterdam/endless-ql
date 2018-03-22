import {Page} from '../page';
import {Section} from '../section';
import {Stylesheet} from '../stylesheet';
import {QlsQuestion} from '../qls-question';
import {Widget} from '../widget';
import {QuestionType} from '../../question-type';
import {WidgetType} from '../widget-type';
import {Style} from '../style';
import {MissingIdentifierError} from '../../../errors';

export interface QlsVisitor<T> {
  visitStylesheet(stylesheet: Stylesheet): T;
  visitPage(page: Page): T;
  visitSection(section: Section): T;
  visitQlsQuestion(qlsQuestion: QlsQuestion): T;
}

export class StylesForQlQuestion {
  constructor(public styles: Style[], public widget: Widget) { }

  isStylingValid(): boolean {
    return this.styles.length > 0 || this.widget.type !== WidgetType.NONE;
  }
}

export class CollectStylesForQuestionVisitor implements QlsVisitor<StylesForQlQuestion> {
  constructor(private questionId: string, private questionType: QuestionType<any>) { }

  static visit(questionId: string, questionType: QuestionType<any>, stylesheet: Stylesheet): StylesForQlQuestion {
    const visitor = new CollectStylesForQuestionVisitor(questionId, questionType);
    return stylesheet.accept(visitor);
  }

  visitStylesheet(stylesheet: Stylesheet): StylesForQlQuestion {
    let styles: StylesForQlQuestion = undefined;
    styles = this.findStylesForStyleable(stylesheet.pages);

    if (!styles) {
      throw new MissingIdentifierError(`Styles for identifier ${this.questionId} were not found`);
    }
    return styles;
  }

  visitPage(page: Page): StylesForQlQuestion {
    let styles: StylesForQlQuestion;

    styles = this.findStylesForStyleable(page.sections);

    styles = this.collectDefaultStylingIfNeeded(styles, page);

    return styles;
  }

  visitSection(section: Section): StylesForQlQuestion {
    let styles: StylesForQlQuestion = undefined;
    const question = section.questions.find(question => question.name === this.questionId);

    if (question) {
      styles = question.accept(this);
    } else {
      styles = this.findStylesForStyleable(section.subSections);
    }

    styles = this.collectDefaultStylingIfNeeded(styles, section);

    return styles;
  }

  visitQlsQuestion(qlsQuestion: QlsQuestion): StylesForQlQuestion {
    if(qlsQuestion.name === this.questionId) {
      return new StylesForQlQuestion([], qlsQuestion.widget);
    }
    return undefined;
  }

  private collectDefaultStylingIfNeeded(styles: StylesForQlQuestion, defaultStyleable: Section | Page): StylesForQlQuestion {
    if(styles && !styles.isStylingValid()) {
      return this.collectDefaultStylesForQuestion(defaultStyleable) || styles;
    }
    return styles;
  }

  private findStylesForStyleable(styleableArray: Section[] | Page[]) {
    for (const styleable of styleableArray) {
      const styles = styleable.accept(this);
      if (styles) {
        return styles
      }
    }
    return undefined;
  }

  private collectDefaultStylesForQuestion(defaultStyleable: Section | Page): StylesForQlQuestion {
    if ( defaultStyleable.defaultSettings && this.questionType.toString() === defaultStyleable.defaultSettings.type.toString()) {
      return new StylesForQlQuestion(defaultStyleable.defaultSettings.styles, defaultStyleable.defaultSettings.widget);
    }
    return undefined;
  }
}
