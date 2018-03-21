parser grammar QlsParser;

options { tokenVocab=QlsLexer; }

root: styleheader OCB stylebody CCB;

styleheader: STYLESHEET_LIT identifier;
stylebody: page+;

page: pageheader OCB pagebody CCB;
pageheader: PAGE_LIT identifier;
pagebody: section+;

section: sectionheader OCB sectionbody CCB |
  sectionheader question;

sectionheader: SECTION_LIT STRING_LIT;
sectionbody:
  section |
  question;

question: QUESTION_LIT identifier widget?;
widget: WIDGET_LIT WIDGET_TYPE;

identifier: IDENTIFIER;
