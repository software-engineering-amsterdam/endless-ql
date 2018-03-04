import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from "reactstrap/lib/Input";
import { FormComponent } from "./rendering/components/form_component/FormComponent";
import Form from "./form/Form";
import { sampleForm } from "./mock/sampleForm";
import QuestionForm from "./form/QuestionForm";
import FormNode from "./form/nodes/FormNode";
import Alert from "reactstrap/lib/Alert";
import { getParserErrorMessage } from "./parsing/parsing_helpers";
import { QlParserPipeline, QlParserResult } from "./parsing/QlParserPipeline";

export interface AppComponentProps {
}

export interface AppComponentState {
  qlInput?: string;
  form: Form;
  parserError: Error | null;
}

class App extends React.Component<AppComponentProps, AppComponentState> {
  constructor(props: AppComponentProps) {
    super(props);

    this.state = {
      qlInput: require("!raw-loader!./mock/sample.ql.txt"),
      form: sampleForm,
      parserError: null
    };

    this.onChange = this.onChange.bind(this);
  }

  componentDidMount() {
    this.onChangeQuestionnaire(require("!raw-loader!./mock/sample.ql.txt"));
  }

  onChangeQuestionnaire(text: string) {
    try {
      const parseResults: QlParserResult[] = (new QlParserPipeline(text)).run();

      console.log(parseResults[0]);

      this.setState({
        form: new QuestionForm(parseResults[0].node, this.state.form.getState()),
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

  onChange(identifier: string, value: any) {
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

  render() {
    return (
        /**
         * The lines below only demonstrate the behaviour of the DSL and will be replaced by
         * the real formula.
         */
        <div className="app container">
          <h1>NEWSKQL</h1>
          <div className="row ql-sample-output">
            <div className="col-md-6">
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
              <FormComponent onChange={this.onChange} form={this.state.form}/>
              <h2>State</h2>
              <Input
                  type="textarea"
                  readOnly={true}
                  value={this.state.form.getState().toString()}
              />
            </div>
          </div>
        </div>
    );
  }
}

export default App;