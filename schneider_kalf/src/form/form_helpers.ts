import EvaluationVisitor from "./nodes/visitors/EvaluationVisitor";
import Expression from "./nodes/expressions/Expression";

/**
 * Alias to evaluate a form using the EvaluationVisitor
 *
 * @param {Expression} expression
 * @returns {any}
 */
export const evaluate = (expression: Expression): any => {
  const evaluationVisitor = new EvaluationVisitor();
  return expression.accept(evaluationVisitor);
};