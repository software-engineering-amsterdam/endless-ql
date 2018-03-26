import { QlsParserPipeline, QlsParserResult } from "../modules/styling/parsing/QlsParserPipeline";
import { QlParserPipeline, QlParserResult } from "./QlParserPipeline";

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

export const runParserPipeline = (ql: string, qls: string, qlsEnabled: boolean): QlParserResult | QlsParserResult => {
  qlsEnabled = qlsEnabled && qls.length > 0;

  if (qlsEnabled) {
    return (new QlsParserPipeline(ql, qls)).run();
  }

  return (new QlParserPipeline(ql)).run()[0];
};