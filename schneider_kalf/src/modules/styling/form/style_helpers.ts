import StyleTreeNode from "./nodes/StyleTreeNode";
import DefaultStyle from "./nodes/children/DefaultStyle";
import StyleFilterVisitor from "./visitors/StyleFilterVisitor";
import WidgetAttribute from "./nodes/attributes/WidgetAttribute";

export const getDefaults = (container: StyleTreeNode): DefaultStyle[] => {
  const visitor = new StyleFilterVisitor({
    includeQuestions: false,
    includeDefaults: true
  });
  return container.accept(visitor);
};

// TODO implementation of checkWidgetAttribute and checkBaseAttribuut
export const checkWidgetAttribute = (attribute: WidgetAttribute): void => {
  switch (attribute.value) {
    case "spinbox":
      // spinboxWidget.checkParentType(attribute.getParent())
      // spinboxWidget.checkArgumentType(attribute.options)
    case "radio":
      // radioWidget.checkParentType(attribute.getParent())
      // radioWidget.checkArgumentType(attribute.options)
    default:
      alert("Widget '" + attribute.name + "' is not supported");
  }
  return;
};

export const checkBaseAttribute = (type: string, argument: string): void => {
  return;
};