import StyleTreeNode from "./nodes/StyleTreeNode";
import DefaultStyle from "./nodes/children/DefaultStyleNode";
import StyleFilterVisitor from "./visitors/StyleFilterVisitor";
import WidgetAttribute from "./nodes/attributes/WidgetAttribute";
import QuestionStyle from "./nodes/children/QuestionStyle";

export const getDefaultStyleNodes = (container: StyleTreeNode): DefaultStyle[] => {
  const visitor = new StyleFilterVisitor({
    includeQuestions: false,
    includeDefaults: true,
    recursive: false
  });

  return container.accept(visitor);
};

export const getQuestionStyleNodes = (container: StyleTreeNode, recursive: boolean = false): QuestionStyle[] => {
  const visitor = new StyleFilterVisitor({
    includeQuestions: true,
    includeDefaults: false,
    recursive: recursive
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
      alert("Widget '" + attribute.getName() + "' is not supported");
  }
  return;
};

export const checkBaseAttribute = (type: string, argument: string): void => {
  return;
};