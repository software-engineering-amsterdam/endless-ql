import StyleAttribute from "./nodes/StyleAttribute";
import styleConstants from "../config/styleConstants";
import { FieldType } from "../../../form/FieldType";
import DefaultStyleNode from "./nodes/children/DefaultStyleNode";

export default class MergedFieldStyle {
  private styles: Map<string, StyleAttribute>;
  private identifier: string;
  private type: FieldType;

  constructor(identifier: string, type: FieldType) {
    this.identifier = identifier;
    this.type = type;
    this.styles = new Map();
  }

  getIdentifier(): string {
    return this.identifier;
  }

  applyDefaults(defaults: DefaultStyleNode[]) {
    defaults.forEach(defaultNode => {
      this.applyDefault(defaultNode);
    });
  }

  applyDefault(defaultNode: DefaultStyleNode) {
    if (this.type !== defaultNode.type) {
      return;
    }

    this.applyStyle(defaultNode.children);
  }

  applyStyle(attributes: StyleAttribute[]) {
    attributes.forEach(styleAttribute => {
      this.styles.set(styleAttribute.getName(), styleAttribute);
    });
  }

  getFieldContainerCssStyle(): object {
    const cssStyles = {};

    this.styles.forEach((attribute: StyleAttribute, key: string) => {
      Object.assign(cssStyles, attribute.getCssValues());
    });

    return cssStyles;
  }
}