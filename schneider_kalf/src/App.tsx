import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from "reactstrap/lib/Input";
import { FormComponent } from "./rendering/components/form_component/FormComponent";
import Form from "./form/Form";
import { sampleForm } from "./mock/sampleForm";
import { QlsTest } from "./modules/styling/rendering/components/qls_test/QlsTest";

import QuestionForm from "./form/QuestionForm";
import FormNode from "./form/nodes/FormNode";

export interface AppComponentProps {
}

export interface AppComponentState {
  qlInput?: string;
  form: Form;
  errorMessage: string;
}

const qlParser = require("./parsing/parsers/ql_parser");

class App extends React.Component<AppComponentProps, AppComponentState> {
  constructor(props: AppComponentProps) {
    super(props);

    this.state = {
      qlInput: require("!raw-loader!./mock/sample.ql.txt"),
      form: sampleForm,
      errorMessage: ""
    };

    this.onChange = this.onChange.bind(this);
  }

  componentDidMount() {
    this.onChangeQuestionnaire(require("!raw-loader!./mock/sample.ql.txt"));
  }

  onChangeQuestionnaire(text: string) {
    let errorMessage: string = "";

    try {
      const formNodes: FormNode[] = qlParser.parse(text);

      this.setState({
        form: new QuestionForm(formNodes[0], this.state.form.getState()),
        errorMessage: errorMessage,
        qlInput: text
      });
    } catch (error) {
      errorMessage = error.message;
    }

    this.setState({
      errorMessage: errorMessage,
      qlInput: text
    });
  }

  onChange(identifier: string, value: any) {
    this.setState({
      form: this.state.form.setAnswer(identifier, value)
    });
  }

  render() {
    return (
        /**
         * The lines below only demonstrate the behaviour of the DSL and will be replaced by
         * the real formula.
         */
        <div className="app container">
          <h2>Sample QL ouput</h2>
          <QlsTest/>
          <div className="row ql-sample-output">
            <div className="col-md-6">
              <Input
                  type="textarea"
                  value={this.state.qlInput}
                  onChange={e => this.onChangeQuestionnaire(e.target.value)}
                  name="ql_input"
              />
              {this.state.errorMessage}
            </div>
            <div className="col-md-6">
              <FormComponent onChange={this.onChange} form={this.state.form}/>
            </div>
          </div>
        </div>
    );
  }
}

export default App;