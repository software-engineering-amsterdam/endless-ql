import {Stylesheet} from './stylesheet';
import {emptyLoc} from '../location';
import {Question} from './question';
import {Widget} from './widget';
import {WidgetType} from './widget-type';
import {Section} from './section';
import {Page} from './page';
import {Question as QlQuestion} from '../question';
import {QuestionType} from '../question-type';

describe('QLS Stylesheet', () => {
  it('Stylesheet which doesn\'t include all QL questions should throw', () => {
    const question = new Question('name', new Widget(WidgetType.NONE, []), emptyLoc, null);
    const section = new Section('section', [], [question], emptyLoc, null);
    const page = new Page('page', [section], emptyLoc, null);
    const stylesheet = new Stylesheet('stylesheet', [page], emptyLoc);

    const qlIntQuestion = new QlQuestion('name', 'label', QuestionType.INT, emptyLoc);
    const qlBoolQuestion = new QlQuestion('missingname', 'label', QuestionType.BOOLEAN, emptyLoc);

    expect(() => stylesheet.checkStylesheet([], [qlIntQuestion, qlBoolQuestion])).toThrow();
  });
});
