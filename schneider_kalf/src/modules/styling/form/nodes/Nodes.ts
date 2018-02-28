import NodeVisitor from "../../../../form/nodes/visitors/NodeVisitor";

class Stylesheet {
  readonly name: string;
  readonly children: FormChild[];

  constructor(name: string, children: FormChild[]) {
    this.name = name;
    this.children = children;
  }
}

interface FormChild {}

// Style
interface StyleAttribuut {
  readonly name: string;
  readonly value: string;
}

class BaseAttribuut implements StyleAttribuut {
  readonly name: string;
  readonly value: string;

  constructor(name: string, value: string) {
    this.name = name;
    this.value = value;
  }
}

class WidgetAttribuut implements StyleAttribuut {
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

interface PageChild {}

// Section
class Section implements PageChild {
  readonly body: SectionChild[];
  readonly name: string;
  constructor(name: string, body: SectionChild[]) {
    this.name = name;
    this.body = body;
  }
}

interface SectionChild {}

// Question
class Question implements SectionChild, PageChild {
  readonly identifier: string;
  readonly children: StyleAttribuut[];
  constructor(identifier: string, children: StyleAttribuut[]) {
    this.identifier = identifier;
    this.children = children;
  }
}

// Default
class Default implements SectionChild, PageChild, FormChild {
  readonly type: string;
  readonly children: StyleAttribuut[];
  constructor(type: string, children: StyleAttribuut[]) {
    this.type = type;
    this.children = children;
  }
}
