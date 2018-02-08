import * as React from 'react';
import Field from "../../form/field/Field";
import ValueWrapper from "../../form/values/ValueWrapper";
import { FormGroup, Label, Input } from 'reactstrap';

export interface TextFieldProps {
  value: ValueWrapper;
  field: Field;
}

export const TextField: React.SFC<TextFieldProps> = (props) => {
  return (
      <FormGroup>
        <Label for={props.field.name}>{props.field.label}</Label>
        <Input name={props.field.name} type="text" value={props.value.toString()}/>
      </FormGroup>
  );
};