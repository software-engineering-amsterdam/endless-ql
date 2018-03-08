import {Form} from '../domain/ast';
import {Stylesheet} from '../domain/ast/qls';
import {parse} from '../../parser/ql-parser';
import {parse as parseQls} from '../../parser/qls-parser';

export class ParseResult {
  constructor(readonly formName: string, readonly form: Form, readonly styles: Stylesheet) { }
}

export class ParseFactory {
  static parse(qlInput: string, qlsInput: string): ParseResult {

    const astQl: Form = parse(qlInput, {});
    const astQls: Stylesheet = parseQls(qlsInput, {});
    // check form
    astQl.checkForm();

    return new ParseResult(astQl.name, astQl, astQls);
  }
}
