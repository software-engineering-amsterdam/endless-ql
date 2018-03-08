import {ParseFactory, ParseResult} from './parse-factory';
import {Page, Question as QlsQuestion, Section, Stylesheet, Widget, WidgetType} from '../domain/ast/qls';
import {emptyLoc, Form, QuestionType} from '../domain/ast';
import {parse} from '../../parser/ql-parser';

export class ParseQlWithDefaultStylingFactory implements ParseFactory {
  constructor(private ql: string) { }

  private static qlQuestionTypeToQlsType(qlType: QuestionType): WidgetType {
    switch (qlType) {
      case QuestionType.INT:
      case QuestionType.DECIMAL:
        return WidgetType.SPINBOX;
      case QuestionType.BOOLEAN:
        return WidgetType.CHECKBOX;
      case QuestionType.DATE:
      case QuestionType.STRING:
      default:
        return WidgetType.NONE;
      // throw new Error('TODO implementation');
    }
  }

  parse(): ParseResult {
    const astQl: Form = parse(this.ql, {});
    // check form
    astQl.checkForm();

    const qlsQuestions: QlsQuestion[] = [];

    for (const qlQuestion of astQl.getAllQuestions()) {
      const widget = new Widget(ParseQlWithDefaultStylingFactory.qlQuestionTypeToQlsType(qlQuestion.type), []);
      qlsQuestions.push(new QlsQuestion(qlQuestion.name, widget, emptyLoc));
    }

    const section = new Section('default section', [], qlsQuestions, emptyLoc);
    const page = new Page('default page', [section], emptyLoc);
    const astQls = new Stylesheet('default implementation', [page], emptyLoc);

    return {formName: astQl.name, qlForm: astQl, qlsStylesheet: astQls};
  }
}
