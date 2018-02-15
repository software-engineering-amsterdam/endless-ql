import * as React from 'react';
import { FormComponent } from './rendering/components/form_component/FormComponent';
import { sampleForm } from "./mock/sampleForm";
import 'bootstrap/dist/css/bootstrap.css';
import { testExpressionStuff } from "./mock/evaluationExamples";

class App extends React.Component {
  componentDidMount() {
    /*
      const qlParser = require("./parsing/parsers/ql_parser");
      const ql = require("./mock/sample.ql.txt");

      const result = qlParser.parse(ql);
      console.log("Parser source:");
      console.log(result);
      */

    // testExpressionStuff();
  }

  render() {
    return (
        <div className="app container">
          <FormComponent form={sampleForm}/>
        </div>
    );
  }
}

export default App;
