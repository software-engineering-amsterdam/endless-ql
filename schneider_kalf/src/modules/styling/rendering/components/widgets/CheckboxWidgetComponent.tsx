import * as React from 'react';
import CheckboxAttribute from "../../../form/nodes/attributes/widget_attribtues/CheckboxAttribute";

export interface CheckboxWidgetComponentProps {
  widget: CheckboxAttribute;
}

export const CheckboxWidgetComponent: React.SFC<CheckboxWidgetComponentProps> = (props) => {
  return (
      <div>
        { props.widget.options.forEach() }
      </div>
  );
};