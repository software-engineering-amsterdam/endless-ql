import * as React from 'react';
import MoneyWrapper from "../../../form/values/MoneyWrapper";
import { FormGroup, Label, InputGroup, Input } from 'reactstrap';
import Field from "../../../form/nodes/fields/FieldNode";

export interface MoneyFieldProps {
  value: MoneyWrapper;
  field: Field;
}

export const MoneyField: React.SFC<MoneyFieldProps> = (props) => {
  return (
      <FormGroup>
        <Label for={props.field.identifier}>{props.field.label}</Label>
        <InputGroup>
          <Input name={props.field.identifier} type="number" step={0.01} value={props.value.toString()}/>
          <div className="input-group-append">
            <span className="input-group-text">€</span>
          </div>
        </InputGroup>
      </FormGroup>
  );
};