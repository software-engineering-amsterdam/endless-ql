export class Stylesheet {
  constructor(public identifier: string, public pages: Page[]) {
  }
}

export class Page {
  constructor(public identifier: string, public sections: Section[]) {
  }
}

export class Section {
  constructor(public name: string, public questions: Question[]) {
  }
}

export class Widget {
  constructor(public name: string, public options?: String[]) {
  }
}

export class Question {
  constructor(public identifier: string, public widget: Widget) {
  }
}

export class Default {
  constructor(public type: string, public styles: Style[]) {
  }
}

export default class Style {
  constructor(public name: string, public value: string) {
  }
}
