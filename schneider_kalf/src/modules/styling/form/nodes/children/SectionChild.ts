import StyleTreeNode from "../StyleTreeNode";
import SectionNode from "../containers/SectionNode";
import QuestionStyle from "./QuestionStyle";

export default interface SectionChild extends StyleTreeNode {
  isSection(): this is SectionNode;

  isQuestionStyle(): this is QuestionStyle;

  isRendered(): boolean;
}