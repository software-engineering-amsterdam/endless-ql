import {Question} from './question';

export abstract class Node {

  getQuestions(): Question[] {
    return [];
  }
}
