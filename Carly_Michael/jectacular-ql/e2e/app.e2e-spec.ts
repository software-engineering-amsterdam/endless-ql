import { AppPage } from './app.po';
import * as mockInput from '../src/app/mock-input';

describe('jectacular-ql App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to our spectacular QL parser!');
  });

  it('should parse an input to a form', done => {
    page.navigateTo();
    page.parseInput(mockInput.ifQuestionForm);
    page.formDisplayed().then(displayed => {
      expect(displayed).toBe(true);
    }).then(done, done.fail);
  });

  it('should show an error', done => {
    page.navigateTo();
    page.parseInput(mockInput.duplicateIdentifierForm);
    page.errorDisplayed().then(errorDisplayed => {
      expect(errorDisplayed).toBe(true);
      page.formDisplayed().then(formDisplayed => {
        expect(formDisplayed).toBe(false);
      });
    }).then(done, done.fail);
  });
});
