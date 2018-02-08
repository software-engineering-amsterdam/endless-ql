import { Injectable } from '@angular/core';
import {Form, Question, QuestionType} from '../domain/ast';
import {parse} from '../../parser/QLParser';
import {UnsupportedTypeError} from '../domain/errors';

@Injectable()
export class ParserService {

  parseInput(input: string): Form {
    let ast;
    try {
      ast = parse(input, {});
    } catch (e) {
      throw new Error(e);
    }

    return this.toForm(ast);
  }

  private toForm(ast): Form {
    let questions = [];

    for (let question of ast.questions) {
      questions.push(new Question(question.name, question.label, this.toQuestionType(question.type)));
    }

    return new Form(ast.name, questions);
  }

  private toQuestionType(stringType: string): QuestionType {
    switch (stringType) {
      case 'integer' : return QuestionType.INT;
      case 'decimal' : return QuestionType.DECIMAL;
      case 'money' : return QuestionType.MONEY;
      case 'boolean' : return QuestionType.BOOLEAN;
      case 'string' : return QuestionType.STRING;
      case 'date' : return QuestionType.DATE;
      default:  {
        throw new UnsupportedTypeError(`QuestionType ${stringType} is not supported`);
      }
    }
  }
}
