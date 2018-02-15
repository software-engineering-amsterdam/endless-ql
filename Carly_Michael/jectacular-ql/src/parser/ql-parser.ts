// tslint:disable:quotemark

// Generated by PEG.js v. 0.10.0 (ts-pegjs plugin v. 0.1.13 )
//
// https://pegjs.org/   https://github.com/metadevpro/ts-pegjs

"use strict";

/* tslint:disable */ 
 import { Form, Question, QuestionType, If } from '../app/domain/ast'
export class SyntaxError extends Error {
  public static buildMessage(expected: string, found: string) {
    const DESCRIBE_EXPECTATION_FNS = {
      literal(expectation) {
        return "\"" + literalEscape(expectation.text) + "\"";
      },

      class(expectation) {
        const escapedParts = expectation.parts.map((part) => {
          return Array.isArray(part)
            ? classEscape(part[0]) + "-" + classEscape(part[1])
            : classEscape(part);
        });

        return "[" + (expectation.inverted ? "^" : "") + escapedParts + "]";
      },

      any(): string {
        return "any character";
      },

      end(): string {
        return "end of input";
      },

      other(expectation): string {
        return expectation.description;
      }
    };

    function hex(ch: string): string {
      return ch.charCodeAt(0).toString(16).toUpperCase();
    }

    function literalEscape(s: string): string {
      return s
        .replace(/\\/g, "\\\\")
        .replace(/"/g,  "\\\"")
        .replace(/\0/g, "\\0")
        .replace(/\t/g, "\\t")
        .replace(/\n/g, "\\n")
        .replace(/\r/g, "\\r")
        .replace(/[\x00-\x0F]/g,            (ch) => "\\x0" + hex(ch) )
        .replace(/[\x10-\x1F\x7F-\x9F]/g, (ch) => "\\x"  + hex(ch) );
    }

    function classEscape(s: string): string {
      return s
        .replace(/\\/g, "\\\\")
        .replace(/\]/g, "\\]")
        .replace(/\^/g, "\\^")
        .replace(/-/g,  "\\-")
        .replace(/\0/g, "\\0")
        .replace(/\t/g, "\\t")
        .replace(/\n/g, "\\n")
        .replace(/\r/g, "\\r")
        .replace(/[\x00-\x0F]/g,            (ch) => "\\x0" + hex(ch) )
        .replace(/[\x10-\x1F\x7F-\x9F]/g, (ch) => "\\x"  + hex(ch) );
    }

    function describeExpectation(expectation) {
      return DESCRIBE_EXPECTATION_FNS[expectation.type](expectation);
    }

    function describeExpected(expected1) {
      const descriptions = expected1.map(describeExpectation);
      let i;
      let j;

      descriptions.sort();

      if (descriptions.length > 0) {
        for (i = 1, j = 1; i < descriptions.length; i++) {
          if (descriptions[i - 1] !== descriptions[i]) {
            descriptions[j] = descriptions[i];
            j++;
          }
        }
        descriptions.length = j;
      }

      switch (descriptions.length) {
        case 1:
          return descriptions[0];

        case 2:
          return descriptions[0] + " or " + descriptions[1];

        default:
          return descriptions.slice(0, -1).join(", ")
            + ", or "
            + descriptions[descriptions.length - 1];
      }
    }

    function describeFound(found1) {
      return found1 ? "\"" + literalEscape(found1) + "\"" : "end of input";
    }

    return "Expected " + describeExpected(expected) + " but " + describeFound(found) + " found.";
  }

  public message: string;
  public expected: string;
  public found: string;
  public location: any;
  public name: string;

  constructor(message: string, expected: string, found: string, location) {
    super();
    this.message = message;
    this.expected = expected;
    this.found = found;
    this.location = location;
    this.name = "SyntaxError";

    if (typeof (Error as any).captureStackTrace === "function") {
      (Error as any).captureStackTrace(this, SyntaxError);
    }
  }
}

function peg$parse(input, options) {
  options = options !== undefined ? options : {};

  const peg$FAILED = {};

  const peg$startRuleFunctions = { form: peg$parseform };
  let peg$startRuleFunction = peg$parseform;

  const peg$c0 = "form";
  const peg$c1 = peg$literalExpectation("form", false);
  const peg$c2 = "{";
  const peg$c3 = peg$literalExpectation("{", false);
  const peg$c4 = "}";
  const peg$c5 = peg$literalExpectation("}", false);
  const peg$c6 = function(name, statements) {
                    return new Form(name, statements);
                  };
  const peg$c7 = "if";
  const peg$c8 = peg$literalExpectation("if", false);
  const peg$c9 = "(";
  const peg$c10 = peg$literalExpectation("(", false);
  const peg$c11 = ")";
  const peg$c12 = peg$literalExpectation(")", false);
  const peg$c13 = function(condition, statements) {
                      return new If(condition, statements);
                    };
  const peg$c14 = peg$otherExpectation("question");
  const peg$c15 = ":";
  const peg$c16 = peg$literalExpectation(":", false);
  const peg$c17 = "\"";
  const peg$c18 = peg$literalExpectation("\"", false);
  const peg$c19 = function(name, label, type) {
                      return new Question(name, label, type);
                    };
  const peg$c20 = function() {return text();};
  const peg$c21 = peg$otherExpectation("whitespace");
  const peg$c22 = /^[ \t\n\r]/;
  const peg$c23 = peg$classExpectation([" ", "\t", "\n", "\r"], false, false);
  const peg$c24 = function() { return; };
  const peg$c25 = /^[a-zA-Z0-9]/;
  const peg$c26 = peg$classExpectation([["a", "z"], ["A", "Z"], ["0", "9"]], false, false);
  const peg$c27 = /^[a-zA-Z0-9:?\\\/.,;!]/;
  const peg$c28 = peg$classExpectation([["a", "z"], ["A", "Z"], ["0", "9"], ":", "?", "\\", "/", ".", ",", ";", "!"], false, false);
  const peg$c29 = "boolean";
  const peg$c30 = peg$literalExpectation("boolean", false);
  const peg$c31 = function() { return QuestionType.BOOLEAN; };
  const peg$c32 = "string";
  const peg$c33 = peg$literalExpectation("string", false);
  const peg$c34 = function() { return QuestionType.STRING; };
  const peg$c35 = "integer";
  const peg$c36 = peg$literalExpectation("integer", false);
  const peg$c37 = function() { return QuestionType.INT; };
  const peg$c38 = "date";
  const peg$c39 = peg$literalExpectation("date", false);
  const peg$c40 = function() { return QuestionType.DATE; };
  const peg$c41 = "decimal";
  const peg$c42 = peg$literalExpectation("decimal", false);
  const peg$c43 = function() { return QuestionType.DECIMAL; };
  const peg$c44 = "money";
  const peg$c45 = peg$literalExpectation("money", false);
  const peg$c46 = function() { return QuestionType.MONEY; };

  let peg$currPos = 0;
  let peg$savedPos = 0;
  const peg$posDetailsCache = [{ line: 1, column: 1 }];
  let peg$maxFailPos = 0;
  let peg$maxFailExpected: any[] = [];
  let peg$silentFails = 0;

  const peg$resultsCache = {};

  let peg$result;

  if ("startRule" in options) {
    if (!(options.startRule in peg$startRuleFunctions)) {
      throw new Error("Can't start parsing from rule \"" + options.startRule + "\".");
    }

    peg$startRuleFunction = peg$startRuleFunctions[options.startRule];
  }

  function text(): string {
    return input.substring(peg$savedPos, peg$currPos);
  }

  function location() {
    return peg$computeLocation(peg$savedPos, peg$currPos);
  }

  function expected(description, location1?) {
    location1 = location1 !== undefined
      ? location1
      : peg$computeLocation(peg$savedPos, peg$currPos);

    throw peg$buildStructuredError(
      [peg$otherExpectation(description)],
      input.substring(peg$savedPos, peg$currPos),
      location1
    );
  }

  function error(message: string, location1?) {
    location1 = location1 !== undefined
      ? location1
      : peg$computeLocation(peg$savedPos, peg$currPos);

    throw peg$buildSimpleError(message, location1);
  }

  function peg$literalExpectation(text1: string, ignoreCase: boolean) {
    return { type: "literal", text: text1, ignoreCase: ignoreCase };
  }

  function peg$classExpectation(parts, inverted, ignoreCase: boolean) {
    return { type: "class", parts: parts, inverted: inverted, ignoreCase: ignoreCase };
  }

  function peg$anyExpectation() {
    return { type: "any" };
  }

  function peg$endExpectation() {
    return { type: "end" };
  }

  function peg$otherExpectation(description) {
    return { type: "other", description: description };
  }

  function peg$computePosDetails(pos: number) {
    let details = peg$posDetailsCache[pos];
    let p;

    if (details) {
      return details;
    } else {
      p = pos - 1;
      while (!peg$posDetailsCache[p]) {
        p--;
      }

      details = peg$posDetailsCache[p];
      details = {
        line: details.line,
        column: details.column
      };

      while (p < pos) {
        if (input.charCodeAt(p) === 10) {
          details.line++;
          details.column = 1;
        } else {
          details.column++;
        }

        p++;
      }

      peg$posDetailsCache[pos] = details;

      return details;
    }
  }

  function peg$computeLocation(startPos: number, endPos: number) {
    const startPosDetails = peg$computePosDetails(startPos);
    const endPosDetails = peg$computePosDetails(endPos);

    return {
      start: {
        offset: startPos,
        line: startPosDetails.line,
        column: startPosDetails.column
      },
      end: {
        offset: endPos,
        line: endPosDetails.line,
        column: endPosDetails.column
      }
    };
  }

  function peg$fail(expected1) {
    if (peg$currPos < peg$maxFailPos) { return; }

    if (peg$currPos > peg$maxFailPos) {
      peg$maxFailPos = peg$currPos;
      peg$maxFailExpected = [];
    }

    peg$maxFailExpected.push(expected1);
  }

  function peg$buildSimpleError(message, location1) {
    return new SyntaxError(message, "", "", location1);
  }

  function peg$buildStructuredError(expected1, found, location1) {
    return new SyntaxError(
      SyntaxError.buildMessage(expected1, found),
      expected1,
      found,
      location1
    );
  }

  function peg$parseform() {
    let s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;

    const key = peg$currPos * 15 + 0;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    s1 = peg$parsews();
    if (s1 !== peg$FAILED) {
      if (input.substr(peg$currPos, 4) === peg$c0) {
        s2 = peg$c0;
        peg$currPos += 4;
      } else {
        s2 = peg$FAILED;
        if (peg$silentFails === 0) { peg$fail(peg$c1); }
      }
      if (s2 !== peg$FAILED) {
        s3 = peg$parsews();
        if (s3 !== peg$FAILED) {
          s4 = peg$parseidentifier();
          if (s4 !== peg$FAILED) {
            s5 = peg$parsews();
            if (s5 !== peg$FAILED) {
              if (input.charCodeAt(peg$currPos) === 123) {
                s6 = peg$c2;
                peg$currPos++;
              } else {
                s6 = peg$FAILED;
                if (peg$silentFails === 0) { peg$fail(peg$c3); }
              }
              if (s6 !== peg$FAILED) {
                s7 = peg$parsews();
                if (s7 !== peg$FAILED) {
                  s8 = [];
                  s9 = peg$parsestatement();
                  while (s9 !== peg$FAILED) {
                    s8.push(s9);
                    s9 = peg$parsestatement();
                  }
                  if (s8 !== peg$FAILED) {
                    if (input.charCodeAt(peg$currPos) === 125) {
                      s9 = peg$c4;
                      peg$currPos++;
                    } else {
                      s9 = peg$FAILED;
                      if (peg$silentFails === 0) { peg$fail(peg$c5); }
                    }
                    if (s9 !== peg$FAILED) {
                      s10 = peg$parsews();
                      if (s10 !== peg$FAILED) {
                        peg$savedPos = s0;
                        s1 = peg$c6(s4, s8);
                        s0 = s1;
                      } else {
                        peg$currPos = s0;
                        s0 = peg$FAILED;
                      }
                    } else {
                      peg$currPos = s0;
                      s0 = peg$FAILED;
                    }
                  } else {
                    peg$currPos = s0;
                    s0 = peg$FAILED;
                  }
                } else {
                  peg$currPos = s0;
                  s0 = peg$FAILED;
                }
              } else {
                peg$currPos = s0;
                s0 = peg$FAILED;
              }
            } else {
              peg$currPos = s0;
              s0 = peg$FAILED;
            }
          } else {
            peg$currPos = s0;
            s0 = peg$FAILED;
          }
        } else {
          peg$currPos = s0;
          s0 = peg$FAILED;
        }
      } else {
        peg$currPos = s0;
        s0 = peg$FAILED;
      }
    } else {
      peg$currPos = s0;
      s0 = peg$FAILED;
    }

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsestatement() {
    let s0;

    const key = peg$currPos * 15 + 1;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$parseq();
    if (s0 === peg$FAILED) {
      s0 = peg$parseifStatement();
    }

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parseifStatement() {
    let s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15;

    const key = peg$currPos * 15 + 2;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    s1 = peg$parsews();
    if (s1 !== peg$FAILED) {
      if (input.substr(peg$currPos, 2) === peg$c7) {
        s2 = peg$c7;
        peg$currPos += 2;
      } else {
        s2 = peg$FAILED;
        if (peg$silentFails === 0) { peg$fail(peg$c8); }
      }
      if (s2 !== peg$FAILED) {
        s3 = peg$parsews();
        if (s3 !== peg$FAILED) {
          if (input.charCodeAt(peg$currPos) === 40) {
            s4 = peg$c9;
            peg$currPos++;
          } else {
            s4 = peg$FAILED;
            if (peg$silentFails === 0) { peg$fail(peg$c10); }
          }
          if (s4 !== peg$FAILED) {
            s5 = peg$parsews();
            if (s5 !== peg$FAILED) {
              s6 = peg$parseidentifier();
              if (s6 !== peg$FAILED) {
                s7 = peg$parsews();
                if (s7 !== peg$FAILED) {
                  if (input.charCodeAt(peg$currPos) === 41) {
                    s8 = peg$c11;
                    peg$currPos++;
                  } else {
                    s8 = peg$FAILED;
                    if (peg$silentFails === 0) { peg$fail(peg$c12); }
                  }
                  if (s8 !== peg$FAILED) {
                    s9 = peg$parsews();
                    if (s9 !== peg$FAILED) {
                      if (input.charCodeAt(peg$currPos) === 123) {
                        s10 = peg$c2;
                        peg$currPos++;
                      } else {
                        s10 = peg$FAILED;
                        if (peg$silentFails === 0) { peg$fail(peg$c3); }
                      }
                      if (s10 !== peg$FAILED) {
                        s11 = peg$parsews();
                        if (s11 !== peg$FAILED) {
                          s12 = [];
                          s13 = peg$parsestatement();
                          while (s13 !== peg$FAILED) {
                            s12.push(s13);
                            s13 = peg$parsestatement();
                          }
                          if (s12 !== peg$FAILED) {
                            s13 = peg$parsews();
                            if (s13 !== peg$FAILED) {
                              if (input.charCodeAt(peg$currPos) === 125) {
                                s14 = peg$c4;
                                peg$currPos++;
                              } else {
                                s14 = peg$FAILED;
                                if (peg$silentFails === 0) { peg$fail(peg$c5); }
                              }
                              if (s14 !== peg$FAILED) {
                                s15 = peg$parsews();
                                if (s15 !== peg$FAILED) {
                                  peg$savedPos = s0;
                                  s1 = peg$c13(s6, s12);
                                  s0 = s1;
                                } else {
                                  peg$currPos = s0;
                                  s0 = peg$FAILED;
                                }
                              } else {
                                peg$currPos = s0;
                                s0 = peg$FAILED;
                              }
                            } else {
                              peg$currPos = s0;
                              s0 = peg$FAILED;
                            }
                          } else {
                            peg$currPos = s0;
                            s0 = peg$FAILED;
                          }
                        } else {
                          peg$currPos = s0;
                          s0 = peg$FAILED;
                        }
                      } else {
                        peg$currPos = s0;
                        s0 = peg$FAILED;
                      }
                    } else {
                      peg$currPos = s0;
                      s0 = peg$FAILED;
                    }
                  } else {
                    peg$currPos = s0;
                    s0 = peg$FAILED;
                  }
                } else {
                  peg$currPos = s0;
                  s0 = peg$FAILED;
                }
              } else {
                peg$currPos = s0;
                s0 = peg$FAILED;
              }
            } else {
              peg$currPos = s0;
              s0 = peg$FAILED;
            }
          } else {
            peg$currPos = s0;
            s0 = peg$FAILED;
          }
        } else {
          peg$currPos = s0;
          s0 = peg$FAILED;
        }
      } else {
        peg$currPos = s0;
        s0 = peg$FAILED;
      }
    } else {
      peg$currPos = s0;
      s0 = peg$FAILED;
    }

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parseq() {
    let s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11;

    const key = peg$currPos * 15 + 3;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    peg$silentFails++;
    s0 = peg$currPos;
    s1 = peg$parsews();
    if (s1 !== peg$FAILED) {
      s2 = peg$parseidentifier();
      if (s2 !== peg$FAILED) {
        if (input.charCodeAt(peg$currPos) === 58) {
          s3 = peg$c15;
          peg$currPos++;
        } else {
          s3 = peg$FAILED;
          if (peg$silentFails === 0) { peg$fail(peg$c16); }
        }
        if (s3 !== peg$FAILED) {
          s4 = peg$parsews();
          if (s4 !== peg$FAILED) {
            if (input.charCodeAt(peg$currPos) === 34) {
              s5 = peg$c17;
              peg$currPos++;
            } else {
              s5 = peg$FAILED;
              if (peg$silentFails === 0) { peg$fail(peg$c18); }
            }
            if (s5 !== peg$FAILED) {
              s6 = peg$parsews();
              if (s6 !== peg$FAILED) {
                s7 = peg$parsetext();
                if (s7 !== peg$FAILED) {
                  if (input.charCodeAt(peg$currPos) === 34) {
                    s8 = peg$c17;
                    peg$currPos++;
                  } else {
                    s8 = peg$FAILED;
                    if (peg$silentFails === 0) { peg$fail(peg$c18); }
                  }
                  if (s8 !== peg$FAILED) {
                    s9 = peg$parsews();
                    if (s9 !== peg$FAILED) {
                      s10 = peg$parsetype();
                      if (s10 !== peg$FAILED) {
                        s11 = peg$parsews();
                        if (s11 !== peg$FAILED) {
                          peg$savedPos = s0;
                          s1 = peg$c19(s2, s7, s10);
                          s0 = s1;
                        } else {
                          peg$currPos = s0;
                          s0 = peg$FAILED;
                        }
                      } else {
                        peg$currPos = s0;
                        s0 = peg$FAILED;
                      }
                    } else {
                      peg$currPos = s0;
                      s0 = peg$FAILED;
                    }
                  } else {
                    peg$currPos = s0;
                    s0 = peg$FAILED;
                  }
                } else {
                  peg$currPos = s0;
                  s0 = peg$FAILED;
                }
              } else {
                peg$currPos = s0;
                s0 = peg$FAILED;
              }
            } else {
              peg$currPos = s0;
              s0 = peg$FAILED;
            }
          } else {
            peg$currPos = s0;
            s0 = peg$FAILED;
          }
        } else {
          peg$currPos = s0;
          s0 = peg$FAILED;
        }
      } else {
        peg$currPos = s0;
        s0 = peg$FAILED;
      }
    } else {
      peg$currPos = s0;
      s0 = peg$FAILED;
    }
    peg$silentFails--;
    if (s0 === peg$FAILED) {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c14); }
    }

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsetext() {
    let s0, s1, s2, s3, s4, s5;

    const key = peg$currPos * 15 + 4;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    s1 = [];
    s2 = peg$currPos;
    s3 = peg$parsews();
    if (s3 !== peg$FAILED) {
      s4 = peg$parseword();
      if (s4 !== peg$FAILED) {
        s5 = peg$parsews();
        if (s5 !== peg$FAILED) {
          s3 = [s3, s4, s5];
          s2 = s3;
        } else {
          peg$currPos = s2;
          s2 = peg$FAILED;
        }
      } else {
        peg$currPos = s2;
        s2 = peg$FAILED;
      }
    } else {
      peg$currPos = s2;
      s2 = peg$FAILED;
    }
    if (s2 !== peg$FAILED) {
      while (s2 !== peg$FAILED) {
        s1.push(s2);
        s2 = peg$currPos;
        s3 = peg$parsews();
        if (s3 !== peg$FAILED) {
          s4 = peg$parseword();
          if (s4 !== peg$FAILED) {
            s5 = peg$parsews();
            if (s5 !== peg$FAILED) {
              s3 = [s3, s4, s5];
              s2 = s3;
            } else {
              peg$currPos = s2;
              s2 = peg$FAILED;
            }
          } else {
            peg$currPos = s2;
            s2 = peg$FAILED;
          }
        } else {
          peg$currPos = s2;
          s2 = peg$FAILED;
        }
      }
    } else {
      s1 = peg$FAILED;
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c20();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsetype() {
    let s0;

    const key = peg$currPos * 15 + 5;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$parsebooleanType();
    if (s0 === peg$FAILED) {
      s0 = peg$parsestringType();
      if (s0 === peg$FAILED) {
        s0 = peg$parseintegerType();
        if (s0 === peg$FAILED) {
          s0 = peg$parsedateType();
          if (s0 === peg$FAILED) {
            s0 = peg$parsedecimalType();
            if (s0 === peg$FAILED) {
              s0 = peg$parsemoneyType();
            }
          }
        }
      }
    }

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsews() {
    let s0, s1, s2;

    const key = peg$currPos * 15 + 6;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    peg$silentFails++;
    s0 = peg$currPos;
    s1 = [];
    if (peg$c22.test(input.charAt(peg$currPos))) {
      s2 = input.charAt(peg$currPos);
      peg$currPos++;
    } else {
      s2 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c23); }
    }
    while (s2 !== peg$FAILED) {
      s1.push(s2);
      if (peg$c22.test(input.charAt(peg$currPos))) {
        s2 = input.charAt(peg$currPos);
        peg$currPos++;
      } else {
        s2 = peg$FAILED;
        if (peg$silentFails === 0) { peg$fail(peg$c23); }
      }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c24();
    }
    s0 = s1;
    peg$silentFails--;
    if (s0 === peg$FAILED) {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c21); }
    }

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parseidentifier() {
    let s0, s1, s2;

    const key = peg$currPos * 15 + 7;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    s1 = [];
    if (peg$c25.test(input.charAt(peg$currPos))) {
      s2 = input.charAt(peg$currPos);
      peg$currPos++;
    } else {
      s2 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c26); }
    }
    if (s2 !== peg$FAILED) {
      while (s2 !== peg$FAILED) {
        s1.push(s2);
        if (peg$c25.test(input.charAt(peg$currPos))) {
          s2 = input.charAt(peg$currPos);
          peg$currPos++;
        } else {
          s2 = peg$FAILED;
          if (peg$silentFails === 0) { peg$fail(peg$c26); }
        }
      }
    } else {
      s1 = peg$FAILED;
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c20();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parseword() {
    let s0, s1, s2;

    const key = peg$currPos * 15 + 8;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    s1 = [];
    if (peg$c27.test(input.charAt(peg$currPos))) {
      s2 = input.charAt(peg$currPos);
      peg$currPos++;
    } else {
      s2 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c28); }
    }
    if (s2 !== peg$FAILED) {
      while (s2 !== peg$FAILED) {
        s1.push(s2);
        if (peg$c27.test(input.charAt(peg$currPos))) {
          s2 = input.charAt(peg$currPos);
          peg$currPos++;
        } else {
          s2 = peg$FAILED;
          if (peg$silentFails === 0) { peg$fail(peg$c28); }
        }
      }
    } else {
      s1 = peg$FAILED;
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c20();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsebooleanType() {
    let s0, s1;

    const key = peg$currPos * 15 + 9;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    if (input.substr(peg$currPos, 7) === peg$c29) {
      s1 = peg$c29;
      peg$currPos += 7;
    } else {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c30); }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c31();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsestringType() {
    let s0, s1;

    const key = peg$currPos * 15 + 10;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    if (input.substr(peg$currPos, 6) === peg$c32) {
      s1 = peg$c32;
      peg$currPos += 6;
    } else {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c33); }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c34();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parseintegerType() {
    let s0, s1;

    const key = peg$currPos * 15 + 11;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    if (input.substr(peg$currPos, 7) === peg$c35) {
      s1 = peg$c35;
      peg$currPos += 7;
    } else {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c36); }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c37();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsedateType() {
    let s0, s1;

    const key = peg$currPos * 15 + 12;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    if (input.substr(peg$currPos, 4) === peg$c38) {
      s1 = peg$c38;
      peg$currPos += 4;
    } else {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c39); }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c40();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsedecimalType() {
    let s0, s1;

    const key = peg$currPos * 15 + 13;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    if (input.substr(peg$currPos, 7) === peg$c41) {
      s1 = peg$c41;
      peg$currPos += 7;
    } else {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c42); }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c43();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  function peg$parsemoneyType() {
    let s0, s1;

    const key = peg$currPos * 15 + 14;
    const cached = peg$resultsCache[key];

    if (cached) {
      peg$currPos = cached.nextPos;

      return cached.result;
    }

    s0 = peg$currPos;
    if (input.substr(peg$currPos, 5) === peg$c44) {
      s1 = peg$c44;
      peg$currPos += 5;
    } else {
      s1 = peg$FAILED;
      if (peg$silentFails === 0) { peg$fail(peg$c45); }
    }
    if (s1 !== peg$FAILED) {
      peg$savedPos = s0;
      s1 = peg$c46();
    }
    s0 = s1;

    peg$resultsCache[key] = { nextPos: peg$currPos, result: s0 };

    return s0;
  }

  peg$result = peg$startRuleFunction();

  if (peg$result !== peg$FAILED && peg$currPos === input.length) {
    return peg$result;
  } else {
    if (peg$result !== peg$FAILED && peg$currPos < input.length) {
      peg$fail(peg$endExpectation());
    }

    throw peg$buildStructuredError(
      peg$maxFailExpected,
      peg$maxFailPos < input.length ? input.charAt(peg$maxFailPos) : null,
      peg$maxFailPos < input.length
        ? peg$computeLocation(peg$maxFailPos, peg$maxFailPos + 1)
        : peg$computeLocation(peg$maxFailPos, peg$maxFailPos)
    );
  }
}

export interface IParseOptions {
  filename?: string;
  startRule?: string;
  tracer?: any;
  [key: string]: any;
}
export type ParseFunction = (input: string, options: IParseOptions) => any;
export const parse: ParseFunction = peg$parse;

