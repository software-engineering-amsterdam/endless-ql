import * as React from 'react';
import SliderAttribute from "../../../form/nodes/attributes/widget_attribtues/SliderAttribute";

export interface SliderWidgetComponentProps {
  widget: SliderAttribute;
}

export const SliderWidgetComponent: React.SFC<SliderWidgetComponentProps> = (props) => {
  return (
      <div>
        { props.widget.options.forEach() }
      </div>
  );
};