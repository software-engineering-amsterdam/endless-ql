import * as React from 'react';
import { findComponentForFieldType } from "../../../../../rendering/field_rendering";
import { fieldComponentsMapping } from "../../../../../config/field_components_mapping";
import StyledFieldNode from "../../../form/StyledFieldNode";

export interface FieldContainerProps {
  field: StyledFieldNode;
  onChange: (value: any) => void;
  value: any;
}

export const StyledFieldContainer: React.SFC<FieldContainerProps> = (props) => {
  const FieldComponent = findComponentForFieldType(props.field.type, fieldComponentsMapping);

  return (
      <div className="field-container">
        <FieldComponent onChange={props.onChange} value={props.value} field={props.field}/>
      </div>
  );
};