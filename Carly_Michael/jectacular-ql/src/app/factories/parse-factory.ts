import {Form} from '../domain/ast';
import {Stylesheet} from '../domain/ast/qls';
import {parse} from '../../parser/ql-parser';
import {parse as parseQls} from '../../parser/qls-parser';

export class ParseResult {
  constructor(readonly formName: string, readonly form: Form, readonly styles: Stylesheet) { }
}

export class ParseFactory {
  static parse(qlInput: string, qlsInput: string): ParseResult {
    let astQls: Stylesheet;

    const astQl: Form = parse(qlInput, {});
    // check form
    astQl.checkForm();

    if (qlsInput.trim().length > 0 ) {
      astQls = parseQls(qlsInput, {});
    }
    return new ParseResult(astQl.name, astQl, astQls);
  }
}
