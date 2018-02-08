import * as React from 'react';
import Field from "../../form/field/Field";

export interface LabelProps {
  field: Field;
}

export const Label: React.SFC<LabelProps> = (props) => {
  return (
      <label htmlFor={props.field.name}>{props.field.label}</label>
  );
};