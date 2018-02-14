import * as React from 'react';
import Field from '../../form/field/Field';
import { TextField } from "../field/TextField";
import StringWrapper from "../../form/values/StringWrapper";
import MoneyWrapper from "../../form/values/MoneyWrapper";
import { MoneyField } from "../field/MoneyField";
import BooleanWrapper from "../../form/values/BooleanWrapper";
import { BooleanField } from "../field/BooleanField";

export interface FieldContainerProps {
  field: Field;
}

export const FieldContainer: React.SFC<FieldContainerProps> = (props) => {
  let field = null;

  // TODO: Replace instanceof checks with more elaborate approach
  if (props.field.value instanceof MoneyWrapper) {
    field = (
        <MoneyField
            value={props.field.value}
            field={props.field}
        />);
  }

  if (props.field.value instanceof BooleanWrapper) {
    field = (
        <BooleanField
            value={props.field.value}
            field={props.field}
        />);
  }

  if (field === null) {
    field = (
        <TextField
            value={props.field.value}
            field={props.field}
        />);
  }

  return (
      <div className="field-container">
        {field}
      </div>
  );
};