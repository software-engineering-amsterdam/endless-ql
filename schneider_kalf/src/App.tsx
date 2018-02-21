import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from "reactstrap/lib/Input";
import Addition from "./form/nodes/expressions/arithmetic/Addition";
import Multiplication from "./form/nodes/expressions/arithmetic/Multiplication";
import NumberLiteral from "./form/nodes/expressions/arithmetic/NumberLiteral";
import { evaluate } from "./form/evaluation/evaluation_functions";
import { FormComponent } from "./rendering/components/form_component/FormComponent";
import Expression from "./form/nodes/expressions/Expression";
import Form from "./form/Form";
import { sampleForm } from "./mock/sampleForm";
import { QlsTest } from "./modules/rendering/components/qls_test/QlsTest";

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

  onChangeQuestionnaire(text: string) {
    let form: Form;
    let errorMessage: string = "";

    try {
      form = qlParser.parse(this.state.qlInput);

      this.setState({
        form: form,
        errorMessage: errorMessage
      });
    } catch (error) {
      errorMessage = error.message;
    }

    this.setState({
      errorMessage: errorMessage
    });
  }

  onChange(identifier: string, value: any) {
    this.setState({
      form: this.state.form.setAnswer(identifier, value)
    });
  }

  render() {
    let form = null;
    let errorMessage = null;

    if (this.state.qlInput && this.state.qlInput.length !== 0) {
      try {
        form = qlParser.parse(this.state.qlInput);
      } catch (error) {
        errorMessage = error.message;
      }
    }

    const sampleExpression: Expression = new Addition(
        new Multiplication(new NumberLiteral(5), new NumberLiteral(3)),
        new NumberLiteral(1)
    );

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
                  onChange={e => this.setState({qlInput: e.target.value})}
                  name="ql_input"
              />
              {errorMessage}
            </div>
            <div className="col-md-6">
              <FormComponent onChange={this.onChange} form={this.state.form}/>
            </div>
            <h2>Sample Expression evaluation</h2>

            <div className="col-md-12">

              <pre>5 * 3 + 1 = {evaluate(sampleExpression)}</pre>
            </div>
          </div>
        </div>
    );
  }
}

export default App;
