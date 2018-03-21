import AbstractStyleNode from "./AbstractStyleNode";
import StyleSheetChild from "./children/StyleSheetChild";
import StyleNodeVisitor from "../visitors/StyleNodeVisitor";
import Page from "./containers/PageNode";

export default class StyleSheetNode extends AbstractStyleNode {
  readonly name: string;
  readonly children: StyleSheetChild[];

  constructor(name: string, children: StyleSheetChild[]) {
    super();
    this.name = name;
    this.children = children;
  }

  accept(visitor: StyleNodeVisitor): any {
    return visitor.visitStyleSheet(this);
  }

  getPages(): Page[] | any {
    return this.children.filter(child => child instanceof Page);
  }
}
