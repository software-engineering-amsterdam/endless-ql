import * as React from 'react';
import RadioAttribute from "../../../form/nodes/attributes/widget_attribtues/RadioAttribute";

export interface RadioWidgetComponentProps {
  widget: RadioAttribute;
}

export const RadioWidgetComponent: React.SFC<RadioWidgetComponentProps> = (props) => {
  return (
      <div>
        { props.widget.options.forEach() }
      </div>
  );
};