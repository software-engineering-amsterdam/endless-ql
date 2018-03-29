import { cloneArray } from "../../helpers/array_helpers";

export default class GenericCollection<E> {
  private list: E[] = [];

  constructor(elements: E[]) {
    this.add = this.add.bind(this);

    this.addMany(elements);
  }

  add(element: E) {
    this.list.push(element);
  }

  addMany(elements: E[]) {
    elements.forEach(this.add);
  }

  toArray() {
    return cloneArray(this.list);
  }
}