import * as React from 'react';
import { FormGroup, Label, Input } from 'reactstrap';
import Field from "../../../form/nodes/fields/FieldNode";
import NumberValue from "../../../form/values/NumberValue";
import { makeNumberValue } from "../../../form/values/values_helpers";

export interface TextFieldProps {
  value: NumberValue;
  field: Field;
  onChange: (value: any) => void;
}

export const NumberField: React.SFC<TextFieldProps> = (props) => {
  const onChange = (value: string) => {
    props.onChange(makeNumberValue(value, props.field.type));
  };

  return (
      <FormGroup>
        <Label for={props.field.identifier}>{props.field.label}</Label>
        <Input
            readOnly={props.field.isReadOnly()}
            name={props.field.identifier}
            type="number"
            onChange={e => onChange(e.target.value)}
            value={(props.value) ? props.value.toString() : ""}
        />
      </FormGroup>
  );
};