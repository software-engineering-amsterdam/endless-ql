import * as React from 'react';
import Field from '../../form/field/Field';
import { Label } from "../label/Label";
import { TextField } from "../field/TextField";
import StringWrapper from "../../form/values/StringWrapper";

export interface FieldContainerProps {
  field: Field;
}

export const FieldContainer: React.SFC<FieldContainerProps> = (props) => {
  if (props.field.value instanceof StringWrapper) {
    return (
        <div>
          <Label field={props.field}/>
          <TextField
              value={props.field.value}
              field={props.field}
          />
        </div>
    );
  }

  return (
      <div>
        <Label field={props.field}/>
        UNKOWN FIELD
      </div>
  );
};