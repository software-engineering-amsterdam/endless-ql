import * as React from 'react';
import TextAttribute from "../../../form/nodes/attributes/widget_attribtues/TextAttribute";

export interface TextWidgetComponentProps {
  widget: TextAttribute;
}

export const TextWidgetComponent: React.SFC<TextWidgetComponentProps> = (props) => {
  return (
      <div>
        { props.widget.options.forEach() }
      </div>
  );
};