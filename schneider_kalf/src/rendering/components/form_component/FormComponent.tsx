import * as React from 'react';
import Form from "../../../form/Form";
import { FieldContainer } from "../field_container/FieldContainer";
import Field from "../../../form/nodes/fields/FieldNode";

export interface FormComponentProps {
  form: Form;
  onChange: (identifier: string, value: any) => void;
  visibleFields: Set<string>;
  renderField?: (field: Field) => any;
}

export const FormComponent: React.SFC<FormComponentProps> = (props) => {
  const onChange = (identifier: string) => (value: any): void => {
    props.onChange(identifier, value);
  };

  const renderFields = () => {
    console.log('all fields', props.form.getFields());
    return props.form.getFields().map((field: Field) => {
      console.log("render field", field);
      if (!props.visibleFields.has(field.identifier)) {
        return null;
      }

      if (props.renderField) {
        return props.renderField(field);
      }

      return (
          <FieldContainer
              onChange={onChange(field.identifier)}
              key={field.identifier}
              field={field}
              value={props.form.getState().get(field.identifier)}
          />);
    });
  };

  return (
      <div className="ql-form">
        <h1>Form ({props.form.getName()})</h1>
        {renderFields()}
      </div>
  );
};