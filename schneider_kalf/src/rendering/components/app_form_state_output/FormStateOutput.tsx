import * as React from 'react';
import Button from "reactstrap/lib/Button";
import Input from "reactstrap/lib/Input";
import Form from "../../../form/StatefulForm";

export interface FormStateOutputProps {
  form?: Form;
}

export const AppFormStateOutput: React.SFC<FormStateOutputProps> = (props) => {
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