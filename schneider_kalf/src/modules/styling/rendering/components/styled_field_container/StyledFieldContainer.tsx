import * as React from 'react';
import { findComponentForFieldType } from "../../../../../rendering/field_rendering";
import { fieldComponentsMapping } from "../../../../../config/field_components_mapping";
import StyledFieldNode from "../../../form/StyledFieldNode";

export interface StyledFieldContainerProps {
  field: StyledFieldNode;
  onChange: (value: any) => void;
  value: any;
}

export const StyledFieldContainer: React.SFC<StyledFieldContainerProps> = (props) => {
  const FieldComponent = findComponentForFieldType(props.field.type, fieldComponentsMapping);

  const cssStyles = props.field.getStyles().getFieldContainerCssStyle();

  return (
      <div style={cssStyles} className="field-container field-container-styled">
        <FieldComponent onChange={props.onChange} value={props.value} field={props.field}/>
      </div>
  );
};