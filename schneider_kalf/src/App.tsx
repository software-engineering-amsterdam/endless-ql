import * as React from 'react';
import './App.css';

const peg = require('pegjs');
const logo = require('./logo.svg');

class App extends React.Component {
  componentDidMount() {
    const javascriptGrammar = require('!raw-loader!./parsing/grammars/javascript.pegjs');
    console.log(javascriptGrammar);
    const javascriptParser = peg.generate(javascriptGrammar);
    const ast = javascriptParser.parse("if(true && var1) alert(\"asd\") else return null");
    console.log(ast); // OUTPUT: AST
  }

  render() {
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <h1 className="App-title">Welcome to React</h1>
          </header>
          <p className="App-intro">
            To get started, edit <code>src/App.tsx</code> and save to reload.
          </p>
        </div>
    );
  }
}

export default App;
