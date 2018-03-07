import StyleNodeVisitor from "../visitors/StyleNodeVisitor";

class Stylesheet {
  readonly name: string;
  readonly children: FormChild[];

  constructor(name: string, children: FormChild[]) {
    this.name = name;
    this.children = children;
  }
}

interface FormChild {
}

// Style
interface StyleAttribute {
  readonly name: string;
  readonly value: string;
}

class BaseAttribute implements StyleAttribute {
  readonly name: string;
  readonly value: string;

  constructor(name: string, value: string) {
    this.name = name;
    this.value = value;
  }
}

class WidgetAttribute implements StyleAttribute {
  readonly name: string;
  readonly value: string;
  readonly options: string[] | undefined;

  constructor(name: string, value: string, options?: string[]) {
    this.name = name;
    this.value = value;
    this.options = options;
  }
}

// Page
class Page implements FormChild {
  readonly body: PageChild[];
  readonly name: string;

  constructor(name: string, body: PageChild[]) {
    this.name = name;
    this.body = body;
  }
}

interface PageChild {
}

// Section
class Section implements PageChild {
  readonly body: SectionChild[];
  readonly name: string;

  constructor(name: string, body: SectionChild[]) {
    this.name = name;
    this.body = body;
  }
}

interface SectionChild {
}

// Question
class Question implements SectionChild, PageChild {
  readonly identifier: string;
  readonly children: StyleAttribute[];

  constructor(identifier: string, children: StyleAttribute[]) {
    this.identifier = identifier;
    this.children = children;
  }
}

// Default
class DefaultStyle implements SectionChild, PageChild, FormChild {
  readonly type: string;
  readonly children: StyleAttribute[];

  constructor(type: string, children: StyleAttribute[]) {
    this.type = type;
    this.children = children;
  }
}

/**
 * List all available node types for easy access in the grammar.
 * This list is not needed otherwise, but used to create according
 * instances inside the parser.
 */
export default {
  Stylesheet,
  BaseAttribuut: BaseAttribute,
  WidgetAttribuut: WidgetAttribute,
  Page,
  Section,
  Question,
  DefaultStyle
};