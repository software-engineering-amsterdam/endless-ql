export default class Stylesheet extends AbstractStyleNode {
  readonly name: string;
  readonly children: FormChild[];

  constructor(name: string, children: FormChild[]) {
    super();
    this.name = name;
    this.children = children;
  }

  accept(visitor: StyleNodeVisitor): any {
    return visitor.visitStyleSheet(this);
  }
}
