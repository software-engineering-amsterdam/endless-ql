import * as React from 'react';
import { FormComponent } from './rendering/components/form_component/FormComponent';
import { sampleForm } from "./mock/sampleForm";
import 'bootstrap/dist/css/bootstrap.css';
import { testExpressionStuff } from "./mock/evaluationExamples";

const qlParser = require("./parsing/parsers/ql_parser");

class App extends React.Component {
  render() {
    return (
        <div className="app container">
          <FormComponent form={sampleForm}/>
        </div>
    );
  }
}

export default App;
