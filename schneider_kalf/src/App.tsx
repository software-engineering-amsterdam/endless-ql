import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from "reactstrap/lib/Input";
import Addition from "./form/nodes/expressions/arithmetic/Addition";
import Multiplication from "./form/nodes/expressions/arithmetic/Multiplication";
import NumberLiteral from "./form/nodes/expressions/arithmetic/NumberLiteral";
import { evaluate } from "./form/form_helpers";

export interface AppComponentProps {
}

export interface AppComponentState {
  qlInput?: string;
}

const qlParser = require("./parsing/parsers/ql_parser");

class App extends React.Component<AppComponentProps, AppComponentState> {
  constructor(props: AppComponentProps) {
    super(props);

    this.state = {
      qlInput: require("!raw-loader!./mock/sample.ql.txt")
    };
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

    const sampleFormula = new Addition(
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
              <Input type="textarea" readOnly={true} value={JSON.stringify(form, null, '\t')} name="form_tree_output"/>
            </div>
            <h2>Sample Expression evaluation</h2>

            <div className="col-md-12">

              <pre>5 * 3 + 1 = {evaluate(sampleFormula)}</pre>
            </div>
          </div>
        </div>
    );
  }
}

export default App;
