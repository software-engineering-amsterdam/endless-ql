import Addition from "./expressions/arithmetic/Addition";
import NumberLiteral from "./expressions/arithmetic/NumberLiteral";
import { EvaluationVisitor } from "./visitors/EvaluationVisitor";
import Multiplication from "./expressions/arithmetic/Multiplication";

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

  console.log(formula.accept(evaluator));
};
