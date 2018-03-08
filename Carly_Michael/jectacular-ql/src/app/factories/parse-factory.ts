import {Form} from '../domain/ast';
import {Stylesheet} from '../domain/ast/qls';

export interface ParseResult {
  formName: string;
  qlForm: Form;
  qlsStylesheet: Stylesheet;
}

export interface ParseFactory {
  parse(): ParseResult;
}
