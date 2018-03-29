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