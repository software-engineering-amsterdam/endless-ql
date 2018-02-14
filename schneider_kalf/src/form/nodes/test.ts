import Addition from "./expressions/arithmetic/Addition";
import NumberLiteral from "./expressions/arithmetic/NumberLiteral";
import Multiplication from "./expressions/arithmetic/Multiplication";
import Equals from "./expressions/comparisons/Equals";
import FormNode from "./FormNode";
import EvaluationVisitor from "./visitors/EvaluationVisitor";
import Question from "./fields/Question";
import FieldType from "../field/FieldType";
import ComputedField from "./fields/ComputedField";
import Variable from "./expressions/Variable";
import BooleanLiteral from "./expressions/boolean_expressions/BooleanLiteral";
import IfCondition from "./conditions/IfCondition";
import Negation from "./expressions/boolean_expressions/Negation";

export const testExpressionStuff = () => {
  const formula = new Multiplication(
      new NumberLiteral(5),
      new Addition(
          new NumberLiteral(3),
          new Multiplication(
              new NumberLiteral(3),
              new NumberLiteral(2)
          )
      )
  );
  const evaluator = new EvaluationVisitor();

  const formula2 = new Equals(
      new NumberLiteral(45),
      new Addition(
          new NumberLiteral(5),
          new Multiplication(
              new NumberLiteral(4),
              new NumberLiteral(10)
          )
      )
  );

  const form1 = new FormNode("MyForm1", [
    new Question("q1", "Is q1 true?", FieldType.Boolean),
    new ComputedField("q2", "Is q1 false?", FieldType.Boolean, new Equals(
        new Variable("q1"),
        new BooleanLiteral(true)
    )),
    new IfCondition(new Negation(new Variable("q1")), [
      new Question("q3", "Only visible if q1  is false?", FieldType.Boolean),
    ]),
    new IfCondition(new Negation(new Variable("q2")), [
      new Question("q3", "Only visible if q1  is true?", FieldType.Boolean),
    ])
  ]);

  console.log(form1);

  console.log(formula2.accept(evaluator)); // true
  console.log(formula.accept(evaluator));

};
