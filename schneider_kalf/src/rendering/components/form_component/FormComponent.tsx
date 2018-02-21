import * as React from 'react';
import Form from "../../../form/Form";
import { FieldContainer } from "../field_container/FieldContainer";
import Field from "../../../form/nodes/fields/FieldNode";

export interface FormComponentProps {
  form: Form;
}

export const FormComponent: React.SFC<FormComponentProps> = (props) => {
  const renderFields = () => {
    return props.form.fields.map((field: Field) => {
      return <FieldContainer key={field.identifier} field={field}/>;
    });
  };

  return (
      <div>
        <h1>FORM: {props.form.name}</h1>
        {renderFields()}
      </div>
  );
};