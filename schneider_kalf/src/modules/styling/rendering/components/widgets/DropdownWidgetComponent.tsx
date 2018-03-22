import * as React from 'react';
import DropdownAttribute from "../../../form/nodes/attributes/widget_attribtues/DropdownAttribute";

export interface DropdownWidgetComponentProps {
  widget: DropdownAttribute;
}

export const DropdownWidgetComponent: React.SFC<DropdownWidgetComponentProps> = (props) => {
  return (
      <div>
        { props.widget.options.forEach() }
      </div>
  );
};