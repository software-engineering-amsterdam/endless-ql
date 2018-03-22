import * as React from 'react';
import FieldNode from "../../../../../form/nodes/fields/FieldNode";
import CheckboxAttribute from "../../../form/nodes/attributes/widget_attribtues/CheckboxAttribute";
import { BooleanField } from "../../../../../rendering/components/field/BooleanField";

export interface CheckboxWidgetComponentProps {
  widget: CheckboxAttribute;
  value: boolean;
  field: FieldNode;
  onChange: (value: any) => void;
}

export const CheckboxWidgetComponent: React.SFC<CheckboxWidgetComponentProps> = (props) => {
  return (
      <BooleanField
          onChange={props.onChange}
          value={props.value}
          field={props.field}
      />
  );
};