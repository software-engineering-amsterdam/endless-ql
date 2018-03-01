import { EmptyVariableScopeStackError } from "../form_errors";

export class VariableScopeStack {
  private _store: string[][];

  private _history: Set<string>;

  constructor() {
    this._store = [];
    this._history = new Set();
  }

  /**
   * Removes the deepest level of the scope.
   */
  moveUp(): void {
    this._store.splice(-1, 1);
  }

  /**
   * Adds one level to the scope.
   */
  moveDown(): void {
    this._store.push([]);
  }

  /**
   * Get the amount of levels that the scope currently stores.
   *
   * @returns {number}
   */
  depth(): number {
    return this._store.length;
  }

  /**
   * Add an identifier to the current (deepest) scope level.
   *
   * @param {string} identifier
   */
  add(identifier: string): void {
    if (this._store.length === 0) {
      throw EmptyVariableScopeStackError.make(identifier);
    }

    const lowestLevel = this._store[this.depth() - 1];

    this._history.add(identifier);

    lowestLevel.push(identifier);
  }

  /**
   * Check if the variable scope knows of a declared variable with a certain identifier.
   *
   * @param {string} identifier
   * @returns {string[]}
   */
  contains(identifier: string): boolean {
    return this._store.findIndex((level: string[]) => {
      return level.findIndex((_identifier: string) => {
        return _identifier === identifier;
      }) !== -1;
    }) !== -1;
  }

  /**
   * Check if the variable scope contains all variables in the given list.
   *
   * @param {string[]} identifiers
   * @returns {boolean}
   */
  containsAll(identifiers: string[]): boolean {
    return identifiers.every(identifier => this.contains(identifier));
  }

  /**
   * Check if variable identifier is in current scope or was declared in the past.
   * Useful to check if the same variable identifier was used twice.
   *
   * @param {string} identifier
   * @returns {boolean}
   */
  wasAlreadyDeclared(identifier: string): boolean {
    return this._history.has(identifier);
  }
}