import * as React from 'react';
import Field from "../../../form/field/Field";
import BooleanWrapper from "../../../form/values/BooleanWrapper";
import { FormGroup, Label, Input } from 'reactstrap';

export interface BooleanFieldProps {
  value: BooleanWrapper;
  field: Field;
}

export const BooleanField: React.SFC<BooleanFieldProps> = (props) => {
  return (
      <FormGroup check={true}>
        <Label for={props.field.name} check={true}>
          <Input checked={props.value.get()} name={props.field.name} type="checkbox"/>{' '}
          {props.field.label}
        </Label>
      </FormGroup>
  );
};