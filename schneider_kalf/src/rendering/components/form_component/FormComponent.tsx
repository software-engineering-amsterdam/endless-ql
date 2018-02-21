import * as React from 'react';
import Form from "../../../form/Form";
import { FieldContainer } from "../field_container/FieldContainer";
import Field from "../../../form/nodes/fields/FieldNode";

export interface FormComponentProps {
  form: Form;
  onChange: (identifier: string, value: any) => void;
}

export const FormComponent: React.SFC<FormComponentProps> = (props) => {
  const onChange = (identifier: string) => (value: any): void => {
    props.onChange(identifier, value);
  };

  const renderFields = () => {
    return props.form.getFields().map((field: Field) => {
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
      <div>
        <h1>FORM: {props.form.getName()}</h1>
        {renderFields()}
      </div>
  );
};