import { browser, by, element } from 'protractor';
import {promise} from 'selenium-webdriver';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }

  clearInputs() {
    element(by.id('inputQl')).clear();
    element(by.id('inputQls')).clear();
  }

  parseInput(input: string) {
    element(by.id('inputQl')).sendKeys(input);
    element(by.id('parse-button')).click();
  }

  formDisplayed(): promise.Promise<boolean> {
    return element(by.id('form')).isPresent();
  }

  errorDisplayed(): promise.Promise<boolean> {
    return element(by.id('error-message')).isPresent();
  }
}
