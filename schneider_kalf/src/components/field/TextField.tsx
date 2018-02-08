import * as React from 'react';
import StringWrapper from "../../form/values/StringWrapper";
import Field from "../../form/field/Field";

export interface TextFieldProps {
  value: StringWrapper;
  field: Field;
}

export const TextField: React.SFC<TextFieldProps> = (props) => {
  return (
      <input name={props.field.name} type="text" value={props.value.get()}/>
  );
};