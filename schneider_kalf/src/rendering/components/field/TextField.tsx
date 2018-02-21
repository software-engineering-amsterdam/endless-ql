import * as React from 'react';
import ValueWrapper from "../../../form/values/ValueWrapper";
import { FormGroup, Label, Input } from 'reactstrap';
import Field from "../../../form/nodes/fields/FieldNode";

export interface TextFieldProps {
  value: ValueWrapper;
  field: Field;
}

export const TextField: React.SFC<TextFieldProps> = (props) => {
  return (
      <FormGroup>
        <Label for={props.field.identifier}>{props.field.label}</Label>
        <Input name={props.field.identifier} type="text" value={props.value.toString()}/>
      </FormGroup>
  );
};