import { browser, by, element } from 'protractor';
import {promise} from 'selenium-webdriver';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }

  parseInput(input: string) {
    element(by.id('input')).sendKeys(input);
    element(by.id('parse-button')).click();
  }

  formDisplayed(): promise.Promise<boolean> {
    return element(by.id('form')).isPresent();
  }

  errorDisplayed(): promise.Promise<boolean> {
    return element(by.id('error-message')).isPresent();
  }
}
