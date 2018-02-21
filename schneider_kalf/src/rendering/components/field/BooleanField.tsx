import * as React from 'react';
import BooleanWrapper from "../../../form/values/BooleanWrapper";
import { FormGroup, Label, Input } from 'reactstrap';
import Field from "../../../form/nodes/fields/FieldNode";

export interface BooleanFieldProps {
  value: BooleanWrapper;
  field: Field;
}

export const BooleanField: React.SFC<BooleanFieldProps> = (props) => {
  return (
      <FormGroup check={true}>
        <Label for={props.field.identifier} check={true}>
          <Input checked={props.value.get()} name={props.field.identifier} type="checkbox"/>{' '}
          {props.field.label}
        </Label>
      </FormGroup>
  );
};