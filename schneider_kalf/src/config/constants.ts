import { Currencies } from 'ts-money';
import fieldComponentsMapping from "./field_components_mapping";

const constants = {
  NODE_TYPES: {
    FORM: "FORM",
    FIELD: "FIELD",
    EXPRESSION: "EXPRESSION",
    MINUS: "MINUS",
    PLUS: "PLUS"
  },
  FIELD_COMPONENTS_MAPPING: fieldComponentsMapping,
  DEFAULT_CURRENCY: Currencies.EUR
};

export default constants;