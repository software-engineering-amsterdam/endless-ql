import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from "reactstrap/lib/Input";
import { FormComponent } from "./rendering/components/form_component/FormComponent";
import Form from "./form/Form";
import Alert from "reactstrap/lib/Alert";
import { getParserErrorMessage } from "./parsing/parsing_helpers";
import VisibleFieldsVisitor from "./form/evaluation/VisibleFieldsVisitor";
import { QlsParserPipeline, QlsParserResult } from "./modules/styling/parsing/QlsParserPipeline";
import StyledForm from "./modules/styling/form/StyledForm";
import PagedFormState from "./modules/styling/form/PagedFormState";

export interface AppComponentProps {
}

export interface AppComponentState {
  qlInput?: string;
  qlsInput: string;
  form: Form | null;
  parserError: Error | null;
}

class App extends React.Component<AppComponentProps, AppComponentState> {
  constructor(props: AppComponentProps) {
    super(props);

    this.state = {
      qlInput: require("!raw-loader!./mock/sample.ql.txt"),
      qlsInput: require("!raw-loader!../../../mock/sample.qls.txt"),
      form: null,
      parserError: null
    };

    this.onChange = this.onChange.bind(this);
  }

  componentDidMount() {
    this.onChangeQuestionnaire(require("!raw-loader!./mock/sample.ql.txt"));
  }

  onChangeQuestionnaire(text: string) {
    try {
      const parseResult: QlsParserResult = (new QlsParserPipeline(text, this.state.qlsInput)).run();

      this.setState({
        form: new StyledForm(parseResult.node, this.getFormState(), parseResult.styleNode),
        parserError: null,
        qlInput: text
      });
    } catch (error) {
      console.error(error);
      this.setState({
        parserError: error,
        qlInput: text
      });
    }
  }

  getFormState() {
    if (!this.state.form) {
      return new PagedFormState();
    }

    return this.state.form.getState();
  }

  onChange(identifier: string, value: any) {
    if (!this.state.form) {
      return;
    }

    this.setState({
      form: this.state.form.setAnswer(identifier, value)
    });
  }

  renderErrorMessage() {
    if (!this.state.parserError) {
      return null;
    }

    return (
        <Alert color="danger">
          {getParserErrorMessage(this.state.parserError)}
        </Alert>
    );
  }

  renderForm() {
    if (!this.state.form) {
      return (
          <span>Form not yet parsed</span>
      );
    }

    return (
        <FormComponent
            onChange={this.onChange}
            form={this.state.form}
            visibleFields={VisibleFieldsVisitor.run(this.state.form)}
        />
    );
  }

  render() {
    return (
        <div className="app container">
          <h1>NEWSKQL</h1>
          <div className="row ql-sample-output">
            <div className="col-md-6">
              <QlsTest/>
              <Input
                  valid={!this.state.parserError}
                  type="textarea"
                  value={this.state.qlInput}
                  onChange={e => this.onChangeQuestionnaire(e.target.value)}
                  name="ql_input"
              />
            </div>
            <div className="col-md-6">
              {this.renderErrorMessage()}
              {this.renderForm()}
              <hr/>
              <div className="state-output-container">
                <h2>State</h2>
                <Input
                    type="textarea"
                    readOnly={true}
                    value={this.getFormState().toString()}
                />
              </div>

            </div>
          </div>
        </div>
    );
  }
}

export default App;