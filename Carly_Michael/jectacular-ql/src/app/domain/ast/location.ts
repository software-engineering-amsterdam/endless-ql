export interface Line {
  offset: number;
  line: number;
  column: number;
}

export interface Location {
  start: Line;
  end: Line;
}
