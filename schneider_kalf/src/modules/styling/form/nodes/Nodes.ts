import StyleNodeVisitor from "../visitors/StyleNodeVisitor";
import AbstractStyleNode from "./AbstractStyleNode";

class Stylesheet extends AbstractStyleNode {
  readonly name: string;
  readonly children: FormChild[];

  constructor(name: string, children: FormChild[]) {
    super();
    this.name = name;
    this.children = children;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

interface FormChild {
}

// Style
interface StyleAttribute {
  readonly name: string;
  readonly value: string;
}

class BaseAttribute extends AbstractStyleNode implements StyleAttribute {
  readonly name: string;
  readonly value: string;

  constructor(name: string, value: string) {
    super();
    this.name = name;
    this.value = value;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

class WidgetAttribute extends AbstractStyleNode implements StyleAttribute {
  readonly name: string;
  readonly value: string;
  readonly options: string[] | undefined;

  constructor(name: string, value: string, options?: string[]) {
    super();
    this.name = name;
    this.value = value;
    this.options = options;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

// Page
class Page extends AbstractStyleNode implements FormChild {
  readonly body: PageChild[];
  readonly name: string;

  constructor(name: string, body: PageChild[]) {
    super();
    this.name = name;
    this.body = body;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

interface PageChild {
}

// Section
class Section extends AbstractStyleNode  implements PageChild {
  readonly body: SectionChild[];
  readonly name: string;

  constructor(name: string, body: SectionChild[]) {
    super();
    this.name = name;
    this.body = body;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

interface SectionChild {
}

// Question
class Question extends AbstractStyleNode implements SectionChild, PageChild {
  readonly identifier: string;
  readonly children: StyleAttribute[];

  constructor(identifier: string, children: StyleAttribute[]) {
    super();
    this.identifier = identifier;
    this.children = children;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

// Default
class DefaultStyle extends AbstractStyleNode implements SectionChild, PageChild, FormChild {
  readonly type: string;
  readonly children: StyleAttribute[];

  constructor(type: string, children: StyleAttribute[]) {
    super();
    this.type = type;
    this.children = children;
  }

  accept(visitor: StyleNodeVisitor) {
    throw new Error("Method not implemented.");
  }
}

/**
 * List all available node types for easy access in the grammar.
 * This list is not needed otherwise, but used to create according
 * instances inside the parser.
 */
export default {
  Stylesheet,
  BaseAttribute,
  WidgetAttribute,
  Page,
  Section,
  Question,
  DefaultStyle
};