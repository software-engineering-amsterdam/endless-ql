import * as React from 'react';
import { FormGroup, Label, InputGroup, Input } from 'reactstrap';
import FieldNode from "../../../form/nodes/fields/FieldNode";
import { FieldType } from "../../../form/FieldType";
import { makeNumberValue } from "../../../form/values/values_helpers";

export interface MoneyFieldProps {
  value: number;
  field: FieldNode;
  onChange: (value: any) => void;
}

export const MoneyField: React.SFC<MoneyFieldProps> = (props) => {
  const onChange = (value: string) => {
    props.onChange(makeNumberValue(value, FieldType.Money));
  };

  return (
      <FormGroup>
        <Label for={props.field.identifier}>{props.field.label}</Label>
        <InputGroup>
          <Input
              readOnly={props.field.isReadOnly()}
              onChange={e => onChange(e.target.value)}
              name={props.field.identifier}
              type="number"
              step={0.01}
              value={(props.value || props.value === 0) ? props.value : ""}
          />
          <div className="input-group-append">
            <span className="input-group-text">€</span>
          </div>
        </InputGroup>
      </FormGroup>
  );
};