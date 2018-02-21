import * as React from 'react';
import { FormGroup, Label, Input } from 'reactstrap';
import Field from "../../../form/nodes/fields/FieldNode";

export interface BooleanFieldProps {
  value: boolean;
  field: Field;
  onChange: (value: any) => void;
}

export const BooleanField: React.SFC<BooleanFieldProps> = (props) => {
  return (
      <FormGroup check={true}>
        <Label for={props.field.identifier} check={true}>
          <Input
              onChange={e => props.onChange(e.target.checked)}
              checked={props.value}
              name={props.field.identifier}
              type="checkbox"
          />
          {' '}
          {props.field.label}
        </Label>
      </FormGroup>
  );
};