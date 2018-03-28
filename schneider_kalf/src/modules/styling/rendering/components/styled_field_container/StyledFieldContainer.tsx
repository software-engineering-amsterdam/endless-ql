import * as React from 'react';
import { findComponentForFieldType } from "../../../../../rendering/field_rendering";
import { fieldComponentsMapping } from "../../../../../config/field_components_mapping";
import FieldWithStyle from "../../../form/StyledField";

export interface StyledFieldContainerProps {
  styledField: FieldWithStyle;
  onChange: (value: any) => void;
  value: any;
}

export const StyledFieldContainer: React.SFC<StyledFieldContainerProps> = (props) => {
  const mergedStyle = props.styledField.getMergedStyle();
  const cssStyles = mergedStyle.getFieldContainerCssStyle();
  const widget = mergedStyle.getWidgetAttribute();

  const DefaultFieldComponent = findComponentForFieldType(props.styledField.getType(), fieldComponentsMapping);
  const WidgetComponent = (widget) ? widget.getRenderComponent() : null;

  const renderField = () => {
    if (!WidgetComponent) {
      return (
          <DefaultFieldComponent
              onChange={props.onChange}
              value={props.value}
              field={props.styledField.getFieldNode()}
          />
      );
    }

    return (
        <WidgetComponent
            widget={widget}
            onChange={props.onChange}
            value={props.value}
            field={props.styledField.getFieldNode()}
        />
    );
  };

  return (
      <div style={cssStyles} className="field-container field-container-styled">
        {renderField()}
      </div>
  );
};