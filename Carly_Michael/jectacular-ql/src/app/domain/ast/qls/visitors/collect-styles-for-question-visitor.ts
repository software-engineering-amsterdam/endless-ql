import {Page} from '../page';
import {Section} from '../section';
import {Stylesheet} from '../stylesheet';
import {QlsQuestion} from '../qls-question';
import {Widget} from '../widget';
import {QuestionType} from '../../question-type';
import {WidgetType} from '../widget-type';
import {Style} from '../style';

export interface QlsVisitor<T> {
  visitStylesheet(stylesheet: Stylesheet): T;
  visitPage(page: Page): T;
  visitSection(section: Section): T;
  visitQlsQuestion(qlsQuestion: QlsQuestion): T;
}

export class StylesForQlQuestion {
  constructor(public styles: Style[], public widget: Widget) { }
}

export class CollectStylesForQuestionVisitor implements QlsVisitor<StylesForQlQuestion> {
  constructor(private questionId: string, private questionType: QuestionType<any>) { }

  static visit(questionId: string, questionType: QuestionType<any>, stylesheet: Stylesheet): StylesForQlQuestion {
    const visitor = new CollectStylesForQuestionVisitor(questionId, questionType);
    return stylesheet.accept(visitor);
  }

  visitStylesheet(stylesheet: Stylesheet): StylesForQlQuestion {
    return undefined;
  }

  visitPage(page: Page): StylesForQlQuestion {
    return undefined;
  }

  visitSection(section: Section): StylesForQlQuestion {
    let styles: StylesForQlQuestion;
    const question = section.questions.find(question => question.name === this.questionId);

    if (question) {
      return question.accept(this);
    } else {
      for (const subSection of section.subSections) {
        subSection.accept(this);
      }
    }

    return undefined;
  }

  visitQlsQuestion(qlsQuestion: QlsQuestion): StylesForQlQuestion {
    if(qlsQuestion.name === this.questionId && qlsQuestion.widget.type !== WidgetType.NONE) {
      return new StylesForQlQuestion([], qlsQuestion.widget);
    }
    return undefined;
  }

  private collectDefaultStylesForQuestion(defaultStyleable: Section | Page): StylesForQlQuestion {
    return undefined;
  }

}
