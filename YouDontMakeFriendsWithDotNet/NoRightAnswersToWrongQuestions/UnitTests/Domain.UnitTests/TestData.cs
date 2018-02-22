﻿using System;
using System.Collections;
using NUnit.Framework;

namespace UnitTests.Domain.UnitTests
{
    public static class TestData
    {
        private static readonly string NewLine = Environment.NewLine;

        public static IEnumerable CommentCases
        {
            get
            {
                yield return new TestCaseData(@"form CommentForm {}//", @"CommentForm");
                yield return new TestCaseData(@"form CommentFormX {}// has comment", @"CommentFormX");
                var comment1 = @"// has comment
form CommentFormXYZ {}";
                yield return new TestCaseData(comment1, @"CommentFormXYZ");
                yield return new TestCaseData(@"form CommentFormABC {}/* . */", @"CommentFormABC");
                yield return new TestCaseData(@"form /* inline */ CommentFormDEF {}", @"CommentFormDEF");
                var multilineComment1 = @"/*
some comment
*/
form CommentFormML {}";
                yield return new TestCaseData(multilineComment1, @"CommentFormML");
                var multilineComment2 = @"/*
//some comment
*/
form CommentFormMLX {}";
                yield return new TestCaseData(multilineComment2, @"CommentFormMLX");
                yield return new TestCaseData(@"form /* //inline */ CommentFormCIC {}", @"CommentFormCIC");
                // ToDo: Add instring comments and make sure they print
            }
        }

        public static IEnumerable ValidNameCases
        {
            get
            {
                yield return new TestCaseData(@"form NameForm {}", @"NameForm");
                yield return new TestCaseData(@"form NameForm1 {}", @"NameForm1");
                yield return new TestCaseData(@"form A {}", @"A");
                yield return new TestCaseData(@"form z {}", @"z");
                yield return new TestCaseData(@"form Name_Form1 {}", @"Name_Form1");
            }
        }

        public static IEnumerable InvalidNameCases
        {
            get
            {
                yield return new TestCaseData(@"form 1NameForm {}", @"1");
                yield return new TestCaseData(@"form Name$Form {}", @"Name$Form");
                yield return new TestCaseData(@"form _NameForm {}", @"_NameForm");
                yield return new TestCaseData(@"form % {}", @"%");
                yield return new TestCaseData(@"form +_(# {}", @"+_(#");
            }
        }

        public static IEnumerable QuestionCases
        {
            get
            {
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", @"x", @"xyz");
                yield return new TestCaseData("form NameForm { qname : \"this is a question\"  boolean }", @"qname", @"this is a question");
                yield return new TestCaseData(
                    $"form NameForm {{ {NewLine} qname2 : \"this is a question too\"  boolean{NewLine}  }} ",
                    @"qname2",
                    @"this is a question too");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three");
                yield return new TestCaseData("form NameForm { x: \"xyz\" boolean }", @"x", @"xyz");
                yield return new TestCaseData("form NameForm { \"xyz\"  x: boolean }", @"x", @"xyz");
                yield return new TestCaseData($"form NameForm {{ \"xyz\" {NewLine} x: boolean {NewLine} \"xxx\" {NewLine} y: boolean {NewLine}}}", @"x", @"xyz");
            }
        }
        
        public static IEnumerable MultipleQuestionCases
        {
            get
            {
                yield return new TestCaseData("form NameForm { x : \"xyz\" boolean }", 1);
                yield return new TestCaseData("form NameForm { qname : \"this is a question\" boolean }", 1);
                yield return new TestCaseData($"form NameForm {{ {NewLine} qname2 : \"this is a question too\"  boolean{NewLine} }} ", 1);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\"  boolean{NewLine}    qname4 : \"this is a question four\"  boolean }} ",
                    2);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    x : \"xyz\"  boolean{NewLine}    y : \"yzx\"  boolean{NewLine}    z : \"zxy\"  boolean }} ",
                    3);
            }
        }

        public static IEnumerable TypeCases
        {
            get
            {
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", typeof(bool));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  string }", typeof(string));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  integer }", typeof(int));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  date }", typeof(DateTime));
                yield return new TestCaseData("form NameForm { x : \"xyz\"  decimal }", typeof(decimal));
            }
        }

        public static IEnumerable ConditionalStatementCases
        {
            get
            {
                yield return new TestCaseData("form NameForm { x : \"xyz\"  boolean }", 0);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    x : \"xyz\"  boolean{NewLine}    if (x) {{{NewLine}    z : \"zxy\"  boolean }} }} ",
                    1);
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    x : \"xyz\"  boolean{NewLine}    if (x) {{{NewLine}    z : \"zxy\"  boolean{NewLine}    if (z) {{{NewLine}    a : \"aaa\"  boolean }} }} }} ",
                    2);
            }
        }

        public static IEnumerable QuestionDuplicatesCases
        {
            get
            {
                yield return new TestCaseData($"form NameForm {{     x : \"xyz\"  boolean {NewLine}    x : \"123\"  boolean  }}", "x");
                yield return new TestCaseData($"form NameForm {{     y : \"xyz\"  boolean {NewLine}    x : \"123\"  boolean  {NewLine}    y : \"123\"  boolean  }}", "y");
                yield return new TestCaseData($"form NameForm {{     x : \"xyz\"  boolean {NewLine}    z : \"123\"  boolean  {NewLine}    z : \"123\"  boolean  }}", "z");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    aName : \"xyz\"  boolean{NewLine}    if (aName) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "aName");
            }
        }

        public static IEnumerable NonBooleanConditional
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    stringQuestion : \"xyz\"  string{NewLine}    if (stringQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "stringQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    integerQuestion : \"xyz\"  integer{NewLine}    if (integerQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "integerQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    decimalQuestion : \"xyz\"  decimal{NewLine}    if (decimalQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "decimalQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    dateQuestion : \"xyz\"  date{NewLine}    if (dateQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", "dateQuestion");
            }
        }

        public static IEnumerable BooleanConditional
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ", new[] { "boolQuestion" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion1 : \"xyz\"  boolean{NewLine}    if (boolQuestion1) {{{NewLine}    aName : \"zxy\"  boolean }} {NewLine}    if (boolQuestion1) {{{NewLine}    aName2 : \"zxy\"  boolean }} }} ", new[] { "boolQuestion1" });
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    boolQuestion2 : \"xyz\"  boolean{NewLine}    if (boolQuestion2) {{{NewLine}    aName : \"zxy\"  boolean {NewLine}    if (aName) {{{NewLine}    aName2 : \"zxy\"  boolean }}  }} }} ", new[] { "boolQuestion2", "aName" });
            }
        }

        public static IEnumerable ComparisonConditional
        {
            get
            {
                var formTemplate = $"form NameForm {{{{{NewLine} {{0}}: \"xyz\"  {{1}}{NewLine}    if ({{2}}) {{{{{NewLine}    aName : \"zxy\"  boolean }}}} }}}}" ;
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion == true"), 
                    new[] { "boolQuestion==true" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion == True"), 
                    new[] { "boolQuestion==True" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion == TRUE"), 
                    new[] { "boolQuestion==TRUE" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion == false"),
                    new[] { "boolQuestion==false" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion == False"),
                    new[] { "boolQuestion==False" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion == FALSE"),
                    new[] { "boolQuestion==FALSE" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion != TRUE"),
                    new[] { "boolQuestion!=TRUE" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolQuestion", "boolean", "boolQuestion != false"),
                    new[] { "boolQuestion!=false" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "intQuestion", "integer", "intQuestion > 10"),
                    new[] { "intQuestion>10" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "intQuestion", "integer", "intQuestion > 999"),
                    new[] { "intQuestion>999" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "intQuestion", "integer", "intQuestion > -1234"),
                    new[] { "intQuestion>-1234" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "decimalQuestion", "decimal", "decimalQuestion > 10.1"),
                    new[] { "decimalQuestion>10.1" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "decimalQuestion", "decimal", "decimalQuestion > 999.999"),
                    new[] { "decimalQuestion>999.999" });
                yield return new TestCaseData(
                    string.Format(formTemplate, "decimalQuestion", "decimal", "decimalQuestion > -1234.5678"),
                    new[] { "decimalQuestion>-1234.5678" });
            }
        }
    }
}