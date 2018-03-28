import SourceInputs from "./form/source/SourceInputs";
import FormState from "./form/state/FormState";
import { QlParserPipeline, QlParserResult } from "./parsing/QlParserPipeline";
import { QlsParserPipeline, QlsParserResult } from "./modules/styling/parsing/QlsParserPipeline";
import StatefulForm from "./form/StatefulForm";
import QlForm from "./form/QlForm";
import QlsForm from "./modules/styling/form/QlsForm";

export const makeStatefulForm = (inputs: SourceInputs, existingState: FormState): StatefulForm => {
  const parseResult: QlParserResult | QlsParserResult | any = runParserPipeline(inputs);

  let form: StatefulForm = new QlForm(parseResult.node, existingState);

  if (inputs.qlsIsFilledAndEnabled()) {
    form = new QlsForm(form, parseResult.styleNode);
  }

  return form;
};

export const runParserPipeline = (inputs: SourceInputs): QlParserResult | QlsParserResult => {
  if (inputs.qlsIsFilledAndEnabled()) {
    return (new QlsParserPipeline(inputs.getQlSource(), inputs.getQlsSource())).run();
  }

  return (new QlParserPipeline(inputs.getQlSource())).runFirst();
};