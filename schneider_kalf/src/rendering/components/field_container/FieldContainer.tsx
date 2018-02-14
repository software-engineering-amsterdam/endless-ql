import * as React from 'react';
import Field from '../../../form/field/Field';

export interface FieldContainerProps {
  field: Field;
}

export const FieldContainer: React.SFC<FieldContainerProps> = (props) => {
  // TODO: Move to renderer?
  /*const findComponent = () => {
    return fieldComponentsMapping.find(mapping => {
      return props.field.value instanceof mapping.value;
    });
  };

  const FieldComponent = findComponent();
  */

  return (
      <div className="field-container">
        Oh look at me, I`m a field.
      </div>
  );
};