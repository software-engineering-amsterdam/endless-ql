import * as React from 'react';
import SpinboxAttribute from "../../../form/nodes/attributes/widget_attribtues/SpinBoxAttribute";

export interface SpinboxWidgetComponentProps {
  widget: SpinboxAttribute;
}

export const SpinboxWidgetComponent: React.SFC<SpinboxWidgetComponentProps> = (props) => {
  return (
      <div>
        { props.widget.options.forEach() }
      </div>
  );
};