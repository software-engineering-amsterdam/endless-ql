export default interface StyleNodeVisitor {
  visitDefaultStyle(defaultStyle: DefaultStyle): any;

  visitQuestion(question: Question): any;

  visitSection(section: Section): any;

  visitPageAttribute(page: Page): any;

  visitWidgetAttribute(widgetAttribute: WidgetAttribute): any;

  visitBaseAttribute(baseAttribute: BaseAttribute): any;

  visitStyleSheet(stylesheet: Stylesheet): any;
}