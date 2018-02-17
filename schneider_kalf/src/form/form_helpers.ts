import EvaluationVisitor from "./nodes/visitors/EvaluationVisitor";
import Expression from "./nodes/expressions/Expression";

export const evaluate = (expression: Expression): any => {
  const evaluationVisitor = new EvaluationVisitor();
  return expression.accept(evaluationVisitor);
};