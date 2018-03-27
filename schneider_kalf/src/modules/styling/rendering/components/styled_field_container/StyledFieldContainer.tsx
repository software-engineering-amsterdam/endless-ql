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
  const mergedStyle = props.field.getMergedStyle();
  const cssStyles = mergedStyle.getFieldContainerCssStyle();
  const widget = mergedStyle.getWidgetAttribute();

  const DefaultFieldComponent = findComponentForFieldType(props.field.type, fieldComponentsMapping);
  const WidgetComponent = (widget) ? widget.getRenderComponent() : null;

  const renderField = () => {
    if (!WidgetComponent) {
      return (
          <DefaultFieldComponent onChange={props.onChange} value={props.value} field={props.field}/>
      );
    }

    return <WidgetComponent widget={widget} onChange={props.onChange} value={props.value} field={props.field}/>;
  };

  return (
      <div style={cssStyles} className="field-container field-container-styled">
        {renderField()}
      </div>
  );
};