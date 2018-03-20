import StyleTreeNode from "./nodes/StyleTreeNode";
import StyleAttribute from "./nodes/StyleAttribute";
import { getDefaults } from "./style_helpers";
import QuestionStyle from "./nodes/children/QuestionStyle";

export class QuestionStyles {
  private styles: Map<string, StyleAttribute>;
  private identifier: string;

  constructor(identifier: string) {
    this.identifier = identifier;
    this.styles = new Map();
  }

  inheritStyleFrom(node: StyleTreeNode) {
    let defaultNodes = getDefaults(node);
    defaultNodes.reverse().forEach(defaultNode => {
      defaultNode.children.forEach(styleAttribute => {
        this.styles.set(styleAttribute.name, styleAttribute);
      });
    });
  }

  addLocalStyle(question: QuestionStyle) {
    question.children.forEach(child => {
      this.styles.set(child.name, child);
    });
  }

  getIdentifier(): string {
    return this.identifier;
  }
}