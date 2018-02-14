import * as React from 'react';
import Field from '../../../form/field/Field';
import { fieldComponentsMapping } from "../../../config/field_components_mapping";

export interface FieldContainerProps {
  field: Field;
}

export const FieldContainer: React.SFC<FieldContainerProps> = (props) => {
  // TODO: Move to renderer?
  const findComponent = () => {
    return fieldComponentsMapping.find(mapping => {
      return props.field.value instanceof mapping.value;
    });
  };

  const FieldComponent = findComponent();

  return (
      <div className="field-container">
        <FieldComponent/>
      </div>
  );
};