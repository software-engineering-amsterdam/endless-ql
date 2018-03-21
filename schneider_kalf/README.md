# NEWSKQL - New Schneider Kalf Questionnaire Language

DSL for Questionnaire Language

## Demo

Feel free to try out the DSL editor on the demo website:

[http://sp.schneider.click/](http://sp.schneider.click/) 

So far it only outputs the given questionnaire to a JSON textarea.

## Development

**Requirements:**

* Node: [https://nodejs.org](https://nodejs.org/)
* NPM: [https://www.npmjs.com/](https://www.npmjs.com/)
* Yarn: [https://yarnpkg.com](https://yarnpkg.com/en/)

**Install dependencies:** 

Only necessary if dependencies changed or first run.

```
yarn
```


**Run development server:**

```
yarn start
```

Visit [http://localhost:3000](http://localhost:3000/)

## Generating a new parser

**Requirements to generate parser**

You need a [global installation of pegjs](https://github.com/pegjs/pegjs) to run the parser generator command
below:

```bash
npm install -g pegjs
```

**Run QL parser generator**

After making changes to `src/parsing/grammars/ql_grammar.pegts`
you can run the following command to generate a new parser:

```
npm run generate:ql
```

The results will be written to `src/parsing/parsers/ql_parser.ts`.
If the development server is running it will automatically reload.

## Tests

To run the tests inside `src/test` please execute: 

```
yarn test
```

This will watch all files for changes and execute the tests every time a 
file is saved. You maybe have to press `a` initially to run all tests.

## Build for production

```
yarn build
```

Result will be written into the `/build` folder

# Doubts
## QLS
* TODO: Check for double default styles on same level

## General
* TODO: Root of expressions should be within brackets
* TODO: Replace this simple integer check with solution in parser, differentiate between 10 and 10.0 (See type check visitor visitNumberLiteral)
* TODO: Allow money and date literal (See type check visitor visitNumberLiteral)
* TODO: Allow float literals (5.1 and 5.0)
* TODO: money = (50 + 10) results in Addition(NumberLiteral(5), NumberLiteral(10))
* TODO: Every character of changed field is in state (Maybe kill state when form changes?)
* TODO: Divide money by money transformed to float
* TODO: SellingPrice: boolean = (1 / 10) is not a problem
* TODO improve naming
* TODO: Rename Nodes to end with "Node"
* TODO: Rename question style to field style (can also style computed fields)
* TODO: Rename QuestionStyles to FieldStyle
* TODO: Either use body or children so we can use this general function (Explain mees how JavaScript works)
* TODO: Consider variable type to merge default styles
* TODO: Divide money by money is equal to decimal. Do not allow money * money and money + decimal

## QL Requirements

- [x] Questions are enabled and disabled when different values are entered.

### The type checker detects:

- [x] reference to undefined questions
- [x] duplicate question declarations with different types
- [x] conditions that are not of the type boolean
- [x] operands of invalid type to operators
- [ ] cyclic dependencies between questions
- [x] duplicate labels (warning)
- [x] The language supports booleans, integers and string values 
- [x] (possibly also dates and decimals).

- [x] Different data types in QL map to different (default) GUI widgets.

### Requirements on the implementation:
- [x] The parser of the DSL is implemented using a grammar-based parser generator.
- [x] The internal structure of a DSL program is represented using abstract syntax trees.
- [x] QL programs are executed as GUI programs, not command-line dialogues.
- [x] QL programs are executed by interpretation, not code generation.

## QLS Requirements

- [x] QLS allows you to place questions of a base QL program in pages and sections.
- [x] Furthermore, you can define default widget types and styles for questions of a particular type (e.g. boolean questions). 
- [x] Such default styles can be overridden on a per widget basis.
- [x] The execution of a QL + QLS program should be the same as executing the QL program individually, except for where questions appear (page/section), what font-styles etc. are used, and what widget types are used.
- [ ] As widget types you are supposed to support at least: slider, spinbox (for numbers), text (for numbers and strings), yesno-radios, checkbox, yesno-dropdown (for booleans).

### The type checker detects:
- [ ] no references to questions that are not in the QL program
- [ ] all questions of the QL program are placed by the QLS program.
- [ ] (default) widget assignments are compatible with question types (e.g. no radio button for integer widgets).
- [ ] you cannot place a single question multiple times.

### Requirements on the implementation:

- [x] You could say QLS defines an "aspect" of questionnaires, i.e. its appearance. The challenge is to implement QLS without (invasively) changing the code pertaining to QL, and without duplicating or reimplementing the QL code. (Copying and/or duplicating is not allowed, but some changes might be needed at certain join points...).
- [x] The QL code, and especially, the QL ASTs should be oblivious to the QLS code. Think about how you can achieve that.

## General 
- [x] Do not forget to take care of keyword reservation: true and false should be parsed as boolean literals, not as identifiers.
- [x] Add single-line comments (a la Java: //).
- [x] Add syntax productions for forms, questions, computed questions, types (int, bool, and string) and if-then and. Use string literals for question labels. See the LWC'13 link above for an example questionnaire.
- [ ] if-then-else statements.
- [ ] Add tests to check your syntax extensions.
- [x] Add AST classes for the provided expression categories, and for you syntactic extensions. Make sure the parser creates objects of the appropriate type.
- [ ] It is unacceptable that there are remnants of dead code, commented out sections, or debugging print statements etc. in the code that you will present for grading.
