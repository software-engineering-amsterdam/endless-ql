import StyleTreeNode from "./nodes/StyleTreeNode";
import StyleAttribute from "./nodes/StyleAttribute";
import { getDefaultStyleNodes } from "./style_helpers";
import QuestionStyle from "./nodes/children/QuestionStyle";
import styleConstants from "../config/styleConstants";

export default class MergedFieldStyle {
  private styles: Map<string, StyleAttribute>;
  private identifier: string;

  constructor(identifier: string) {
    this.identifier = identifier;
    this.styles = new Map();
  }

  inheritStyleFrom(node: StyleTreeNode) {
    let defaultNodes = getDefaultStyleNodes(node);
    defaultNodes.reverse().forEach(defaultNode => {
      defaultNode.children.forEach(styleAttribute => {
        this.styles.set(styleAttribute.getName(), styleAttribute);
      });
    });
  }

  addLocalStyle(question: QuestionStyle) {
    question.children.forEach(child => {
      this.styles.set(child.getName(), child);
    });
  }

  getIdentifier(): string {
    return this.identifier;
  }

  getFieldContainerCssStyle(): object {
    const cssStyles = {};

    this.styles.forEach((attribute: StyleAttribute, key: string) => {
      const cssAttributeName = styleConstants.CSS_STYLE_MAPPING[key];

      if (typeof cssAttributeName === 'undefined') {
        return;
      }

      cssStyles[cssAttributeName] = attribute.getStringValue();
    });

    return cssStyles;
  }
}