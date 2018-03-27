import * as React from 'react';
import QlForm from "../../../form/QlForm";
import QlsForm from "../../../modules/styling/form/QlsForm";
import Button from "reactstrap/lib/Button";
import Input from "reactstrap/lib/Input";

export interface FormStateOutputProps {
  form?: QlForm | QlsForm;
}

export const FormStateOutput: React.SFC<FormStateOutputProps> = (props) => {
  if (!props.form) {
    return null;
  }

  const onExportState = () => {
    if (!props.form) {
      return;
    }

    const json: string = props.form.getState().toJson();
    require("downloadjs")(json, `${props.form.getName()}_${Math.round(Date.now())}`, "application/json");
  };

  const inputValue = (props.form !== null) ? props.form.getState().toString() : '';

  return (
      <div className="state-output-container">
        <div className="row">
          <div className="col-md-8">
            <h2>State</h2>
          </div>
          <div className="col-md-4">
            <Button disabled={props.form !== null} onClick={() => onExportState()}>Export state</Button>
          </div>
        </div>
        <Input
            type="textarea"
            readOnly={true}
            value={inputValue}
        />
      </div>
  );
};