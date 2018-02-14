import Statement from "./Statement";

export default interface FormNode {
  readonly name: string;
  readonly statements: Statement[];
}