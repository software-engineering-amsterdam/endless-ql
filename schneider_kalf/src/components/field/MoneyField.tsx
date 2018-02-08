import * as React from 'react';
import Field from "../../form/field/Field";
import MoneyWrapper from "../../form/values/MoneyWrapper";
import { FormGroup, Label, InputGroup, Input } from 'reactstrap';

export interface TextFieldProps {
  value: MoneyWrapper;
  field: Field;
}

export const MoneyField: React.SFC<TextFieldProps> = (props) => {
  return (
      <FormGroup>
        <Label for={props.field.name}>{props.field.label}</Label>
        <InputGroup>
          <Input name={props.field.name} type="number" value={props.value.toString()}/>
          <div className="input-group-append">
            <span className="input-group-text">€</span>
          </div>
        </InputGroup>
      </FormGroup>
  );
};