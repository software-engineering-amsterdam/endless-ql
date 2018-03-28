import { QlsParserPipeline, QlsParserResult } from "../modules/styling/parsing/QlsParserPipeline";
import { QlParserPipeline, QlParserResult } from "./QlParserPipeline";
import SourceInputs from "../form/source/SourceInputs";

const qlParser = require("./parsers/ql_parser");

export const getParserErrorMessage = (error: Error | any) => {
  let message = error.message;

  if (typeof error.location !== 'undefined') {
    message += ` Line: ${error.location.start.line}`;
  }

  return message;
};

export const getQlParser = () => {
  return qlParser;
};

export const runParserPipeline = (inputs: SourceInputs): QlParserResult | QlsParserResult => {
  if (inputs.qlsIsFilledAndEnabled()) {
    return (new QlsParserPipeline(inputs.getQlSource(), inputs.getQlsSource())).run();
  }

  return (new QlParserPipeline(inputs.getQlSource())).runFirst();
};