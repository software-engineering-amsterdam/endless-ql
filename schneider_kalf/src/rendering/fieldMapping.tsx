import BooleanWrapper from "../form/values/BooleanWrapper";

export interface FieldComponentMapping {
  value: string;
  component: string;
}

export const someMapping: FieldComponentMapping = {
  value: BooleanWrapper.name,
  component: "asdasd"
};