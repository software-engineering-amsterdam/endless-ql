import Form from "../Form";
import { SyntaxTree } from "../../parsing/SyntaxTree";
import QuestionnaireForm from "../QuestionnaireForm";

export default class QuestionnaireFormFactory {
  public getFromAst(tree: SyntaxTree): Form {

    return new QuestionnaireForm("test", [], []);
  }
}