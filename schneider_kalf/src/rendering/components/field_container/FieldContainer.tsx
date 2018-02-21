import * as React from 'react';
import Field from "../../../form/nodes/fields/FieldNode";
import { fieldComponentsMapping } from "../../../config/field_components_mapping";

export interface FieldContainerProps {
  field: Field;
  onChange: (value: any) => void;
  value: any;
}

export const FieldContainer: React.SFC<FieldContainerProps> = (props) => {
  // TODO: Move to renderer?
  const findComponent: any = () => {
    const pair = fieldComponentsMapping.find(mapping => {
      return props.field.type === mapping.type;
    });

    if (!pair) {
      return null;
    }

    return pair.component;
  };

  const FieldComponent = findComponent();

  if (!FieldComponent) {
    return <span>{"NO COMPONENT FOUND FOR TYPE" + props.field.type}</span>;
  }

  return (
      <div className="field-container">
        <FieldComponent onChange={props.onChange} value={props.value} field={props.field}/>
      </div>
  );
};