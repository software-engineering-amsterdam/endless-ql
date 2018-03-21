import {QlsQuestion} from '../qls-question';
import {emptyLoc} from '../../location';
import {Widget} from '../widget';
import {WidgetType} from '../widget-type';
import {CollectStylesForQuestionVisitor, StylesForQlQuestion} from './collect-styles-for-question-visitor';
import {StringQuestionType} from '../../question-type';
import {Stylesheet} from '../stylesheet';
import {Section} from '../section';
import {DefaultStyling} from '../default-styling';
import {Style} from '../style';
import {HexValue} from '../style-value';

fdescribe('Collect styles for question visitor', () => {
  const textWidget = new Widget(WidgetType.TEXT);
  const colorStyle = new Style('color', new HexValue('#00FF00'), emptyLoc);
  const defaultStyling = new DefaultStyling(new StringQuestionType(), textWidget, [colorStyle], emptyLoc);
  const question = new QlsQuestion('name', textWidget , emptyLoc);
  const unstyledQuestion = new QlsQuestion('name', Widget.Empty, emptyLoc);
  const unstyledSectionWithQuestion = new Section('sectionName', [], [question], emptyLoc);
  const unstyledSectionWithUnstyledQuestion = new Section('sectionName', [], [unstyledQuestion], emptyLoc);
  const styledSectionWithQuestion = new Section('sectionName', [], [question], emptyLoc, defaultStyling);
  const styledSectionWithUnstyledQuestion = new Section('sectionName', [], [unstyledQuestion], emptyLoc, defaultStyling);
  let visitor: CollectStylesForQuestionVisitor;

  beforeEach(() => {
    visitor = new CollectStylesForQuestionVisitor('name', new StringQuestionType());
  });

  describe('visit question', () => {
    it('should return the right widget for a question', () => {
      const visitQuestionResult = visitor.visitQlsQuestion(question);
      expect(visitQuestionResult).toEqual(new StylesForQlQuestion([], textWidget));
    });

    it('should not return anything if no widget is defined for the question', () => {
      const visitQuestionResult = visitor.visitQlsQuestion(unstyledQuestion);
      expect(visitQuestionResult).toBeUndefined();
    });
  });

  describe('visit section', () => {
    it('should return the question styling if the question is in the section and has a defined widget', () => {
      const visitSectionResult = visitor.visitSection(unstyledSectionWithQuestion);
      expect(visitSectionResult).toEqual(new StylesForQlQuestion([], textWidget));
    });

    it('should return nothing if the question is in the section without a defined widget but has no default styling', () => {
      const visitSectionResult = visitor.visitSection(unstyledSectionWithUnstyledQuestion);
      expect(visitSectionResult).toBeUndefined();
    });

    it('should return question styling if the question is in the section and has a defined widget', () => {
      const visitSectionResult = visitor.visitSection(styledSectionWithQuestion);
      expect(visitSectionResult).toEqual(new StylesForQlQuestion([], textWidget));
    });

    it('should return default styling if the question is in the section and has no defined widget', () => {
      const visitSectionResult = visitor.visitSection(styledSectionWithUnstyledQuestion);
      expect(visitSectionResult).toEqual(defaultStyling);
    });
  });
});
