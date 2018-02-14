import * as React from 'react';
import { FormComponent } from './rendering/components/form_component/FormComponent';
import { sampleForm } from "./mock/sampleForm";
import 'bootstrap/dist/css/bootstrap.css';
import { testExpressionStuff } from "./form/nodes/test";

const pegjs = require("pegjs");
const tspegjs = require("ts-pegjs");

class App extends React.Component {
  componentDidMount() {
    const grammar = require('!raw-loader!./parsing/grammars/questionnaire.pegts');

    const parserSource = pegjs.generate(grammar, {
      output: "source",
      format: "commonjs",
      plugins: [tspegjs],
      "tspegjs": {
        "noTslint": true,
        "customHeader": "import Nodes from \"./form/nodes/Nodes\";"
      }
    });

    console.log("Parser source:");
    console.log(parserSource);

    // const javascriptGrammar = require('!raw-loader!./parsing/grammars/javascript.pegjs');
    // const javascriptParser = peg.generate(javascriptGrammar);
    // const ast = javascriptParser.parse("if(true && var1){alert(\"asd\");alert(\"fgh\");}else{return null;}");
    // console.log(ast); // OUTPUT: AST

    testExpressionStuff();
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
