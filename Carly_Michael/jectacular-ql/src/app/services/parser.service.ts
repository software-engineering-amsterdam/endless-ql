import { Injectable } from '@angular/core';
import {Form, If, Question, QuestionType, Statement} from '../domain/ast';
import {parse} from '../../parser/ql-parser';
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
    console.log('toForm', ast);

    return new Form(ast.name, this.statementsToForm(ast.statements));
  }

  private statementsToForm(inputStatements): Statement[] {
    let statements = [];
    for (let statement of inputStatements) {
      if(statement.statementType == "question") {
        statements.push(new Question(statement.name, statement.label, this.toQuestionType(statement.type)));
      } else if(statement.statementType == "if") {
        statements.push(new If(statement.condition, this.statementsToForm(statement.statements)));
      }
    }
    return statements;
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
