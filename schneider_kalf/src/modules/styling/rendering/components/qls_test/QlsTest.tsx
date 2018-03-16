import * as React from 'react';

import { QlsParserPipeline } from "../../../parsing/QlsParserPipeline";

export interface QlsTestProps {

}

export interface QlsTestState {

}

const qlsParser = require("../../../parsing/parsers/qls_parser");

export class QlsTest extends React.Component<QlsTestProps, QlsTestState> {
  private exampleInput: string = require("!raw-loader!../../../mock/sample.qls.txt");
  private exampleOutput = qlsParser.parse(this.exampleInput);

  constructor(props: QlsTestProps) {
    super(props);

    this.state = {};
    console.log(this.getParseResult(this.exampleInput));
  }

  getParseResult(text: string) {
    return (new QlsParserPipeline(text)).run();
  }

  render() {
    return (
        <div>
          QLS style nodes are shown in the console
        </div>
    );
  }
}