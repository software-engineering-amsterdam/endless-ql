using System;
using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;
using UnitTests.Domain.UnitTests.Utilities;

namespace UnitTests.Domain.UnitTests.Data
{
    public static class TestAstData
    {
        private static readonly string NewLine = Environment.NewLine;

        public static IEnumerable GoodFormCases
        {
            get
            {
                yield return new TestCaseData(@"form MyForm {}", @"MyForm");
                yield return new TestCaseData($@"// has comment{NewLine}form MyForm2 {{}}", @"MyForm2");
                yield return new TestCaseData($@"form MyForm_3 {{{NewLine}}}", @"MyForm_3");
            }
        }

        public static IEnumerable BadFormCases
        {
            get
            {
                yield return new TestCaseData(@"forx MyForm {}");
                yield return new TestCaseData(@"form MyForm {");
                yield return new TestCaseData($@"form MyFo$rm_3 {{{NewLine}}}");
                yield return new TestCaseData(@"form MyForm {form MyForm2 {}}");
            }
        }

        public static IEnumerable GoodCommentCases
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

        public static IEnumerable BadCommentCases
        {
            get
            {
                yield return new TestCaseData(@"//form CommentForm {}//");
                yield return new TestCaseData(@"form CommentFormX {/}/ has comment");
                yield return new TestCaseData(@"form /* inline * CommentFormDEF {}");
                var multilineComment1 = @"/*
some comment

form CommentFormML {}";
                yield return new TestCaseData(multilineComment1);
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
                yield return new TestCaseData(@"form QuestionName$Form {}", @"QuestionName$Form");
                yield return new TestCaseData(@"form _NameForm {}", @"_NameForm");
                yield return new TestCaseData(@"form % {}", @"%");
                yield return new TestCaseData(@"form +_(# {}", @"+_(#");
            }
        }

        public static IEnumerable QuestionCases
        {
            get
            {
                yield return new TestCaseData(
                    "form NameForm { x : \"xyz\"  boolean }",
                    @"x",
                    @"xyz",
                    typeof(bool));
                yield return new TestCaseData(
                    "form NameForm { qname : \"this is a question\"  string }",
                    @"qname",
                    @"this is a question",
                    typeof(string));
                yield return new TestCaseData(
                    $"form NameForm {{ {NewLine} qname2 : \"this is a question too\"  decimal{NewLine}  }} ",
                    @"qname2",
                    @"this is a question too",
                    typeof(decimal));
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" date{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three",
                    typeof(DateTime));
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    whoop : \"this is a question three\" integer{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"whoop",
                    @"this is a question three",
                    typeof(int));
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    qname3 : \"this is a question three\" boolean{NewLine}    qname4 : \"this is a question four\" boolean }} ",
                    @"qname3",
                    @"this is a question three",
                    typeof(bool));
                yield return new TestCaseData(
                    $"form NameForm {{ \"xyz\" {NewLine} x: boolean {NewLine} \"xxx\" {NewLine} y: boolean {NewLine}}}",
                    @"x",
                    @"xyz",
                    typeof(bool));
            }
        }

        public static IEnumerable CalculationQuestionCases
        {
            get
            {
                var noVarNames = new string[] { };
                yield return new TestCaseData(
                    "form NameForm { x: \"xyz\" integer = (10 + 20) }",
                    new[] {@"10+20"},
                    new[] {10m, 20m}, noVarNames);
//                yield return new TestCaseData(
//                    "form NameForm { x: \"xyz\" decimal = (10.5 + 20.54) }",
//                    new[] {@"10.5+20.54"},
//                    new[] { 10.5m, 20.54m }, 
//                    noVarNames);
//                yield return new TestCaseData(
//                    "form NameForm {  \"xyz\" x: integer =(10/20*30) }", 
//                    new[] {@"10/20*30"},
//                    new[] { 10m, 20m, 30m }, 
//                    noVarNames);
//                yield return new TestCaseData(
//                    $@"form NameForm {{  
//    ""xyz"" x: integer = (10/20*30)
//    a: ""abc"" decimal =(10.5 - 20.54)
//}}",
//                    new[] { @"10/20*30", "10.5-20.54" },
//                    new[] { 10m, 20m, 30m, 10.5m, 20.54m }, 
//                    noVarNames);
//                yield return new TestCaseData(
//                    $@"form NameForm {{  
//    ""xyz"" intQuestion: integer
//    a: ""abc"" decimal = (intQuestion - 20.54)
//}}",
//                    new[] { "intQuestion-20.54" },
//                    new[] { 20.54m },
//                    new[] {"intQuestion"});
                //                yield return new TestCaseData($@"form NameForm {{  
                //    ""xyz"" intQuestion: integer
                //    a: ""abc"" decimal = ((intQuestion - 20.54) * (intQuestion/3))
                //}}",
                //                    new[] { "(intQuestion-20.54)*(intQuestion/3)" });
            }
        }

        public static IEnumerable MultipleQuestionCases
        {
            get
            {
                yield return new TestCaseData("form NameForm { x : \"xyz\" boolean }", 1);
                yield return new TestCaseData("form NameForm { qname : \"this is a question\" boolean }", 1);
                yield return new TestCaseData(
                    $"form NameForm {{ {NewLine} qname2 : \"this is a question too\"  boolean{NewLine} }} ", 1);
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

        public static IEnumerable ElseStatementCases
        {
            get
            {
                yield return new TestCaseData(
                    $@"form NameForm {{
    x : ""bbb""  boolean
    if (x) {{
        y : ""ccc""  boolean 
    }} else {{
        z : ""ddd""  boolean 
    }}
}} ",
                    3);
                yield return new TestCaseData(
                    $@"form NameForm {{
    x : ""bbb""  boolean
    if (!x) {{
        y : ""ccc""  boolean 
    }} else {{
        z : ""ddd""  boolean 
        aa : ""eeee""  boolean 
    }}
}} ",
                    4);
            }
        }

        public static IEnumerable QuestionDuplicatesCases
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{     x : \"xyz\"  boolean {NewLine}    x : \"123\"  boolean  }}", "x");
                yield return new TestCaseData(
                    $"form NameForm {{     y : \"xyz\"  boolean {NewLine}    x : \"123\"  boolean  {NewLine}    y : \"123\"  boolean  }}",
                    "y");
                yield return new TestCaseData(
                    $"form NameForm {{     x : \"xyz\"  boolean {NewLine}    z : \"123\"  boolean  {NewLine}    z : \"123\"  boolean  }}",
                    "z");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    aName : \"xyz\"  boolean{NewLine}    if (aName) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    "aName");
            }
        }

        public static IEnumerable NonBooleanConditional
        {
            get
            {
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    stringQuestion : \"xyz\"  string{NewLine}    if (stringQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    "stringQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    integerQuestion : \"xyz\"  integer{NewLine}    if (integerQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    "integerQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    decimalQuestion : \"xyz\"  decimal{NewLine}    if (decimalQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    "decimalQuestion");
                yield return new TestCaseData(
                    $"form NameForm {{{NewLine}    dateQuestion : \"xyz\"  date{NewLine}    if (dateQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    "dateQuestion");
            }
        }

        public static IEnumerable BooleanConditional
        {
            get
            {
                //test processes if statement
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{{NewLine}    boolQuestion : \"xyz\"  boolean{NewLine}    if (boolQuestion) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    definitions: new[] {"boolQuestion"});

                //test AND and multiple boolean variables
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{{NewLine}    boolQuestion1 : \"xyz1\"  boolean{NewLine}    boolQuestion2 : \"xyz2\"  boolean{NewLine}    if (boolQuestion1 && boolQuestion2) {{{NewLine}    aName : \"zxy\"  boolean }} }} ",
                    definitions: new[] {"boolQuestion1&&boolQuestion2"},
                    variables: new[] {"boolQuestion1", "boolQuestion2"},
                    operators: new BooleanOperatorCount {AndCount = 1});

                //test multiple if statements and OR
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean bq2 : \"2\"  boolean bq3 : \"3\"  boolean bq4 : \"4\"  boolean  if (bq1 && bq2) {{ x : \"xx\"  date }}  if (bq3 || bq4) {{ y : \"yy\"  date }} }} ",
                    definitions: new[] {"bq1&&bq2", "bq3||bq4"},
                    variables: new[] {"bq1", "bq2", "bq3", "bq4"},
                    operators: new BooleanOperatorCount {AndCount = 1, OrCount = 1});

                //test multiple AND with variables
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean bq2 : \"2\"  boolean bq3 : \"3\"  boolean bq4 : \"4\"  boolean  if (bq1 && bq2 && bq3 && bq4) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"bq1&&bq2&&bq3&&bq4"},
                    variables: new[] {"bq1", "bq2", "bq3", "bq4"},
                    operators: new BooleanOperatorCount {AndCount = 3});

                //test multiple OR with variables
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean bq2 : \"2\"  boolean bq3 : \"3\"  boolean bq4 : \"4\"  boolean  if (bq1 || bq2 || bq3 || bq4) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"bq1||bq2||bq3||bq4"},
                    variables: new[] {"bq1", "bq2", "bq3", "bq4"},
                    operators: new BooleanOperatorCount {OrCount = 3});

                // test bracketed grouping
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean bq2 : \"2\"  boolean bq3 : \"3\"  boolean bq4 : \"4\"  boolean  if ((bq1 || bq2) && (bq3 || bq4)) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"(bq1||bq2)&&(bq3||bq4)"},
                    variables: new[] {"bq1", "bq2", "bq3", "bq4"},
                    operators: new BooleanOperatorCount {AndCount = 1, OrCount = 2});

                // test the various Boolean True Literals
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean if (true && True && TRUE) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"true&&True&&TRUE"},
                    values: new[] {true, true, true},
                    operators: new BooleanOperatorCount {AndCount = 2});

                // test the various Boolean False Literals
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean if (bq1 || false || False || FALSE) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"bq1||false||False||FALSE"},
                    values: new[] {false, false, false},
                    operators: new BooleanOperatorCount {OrCount = 3});

                // test Negate
                yield return BooleanTestCaseData(
                    formText:
                    $"form NameForm {{bq1 : \"1\"  boolean if (!bq1 || !(false && False) || !(FALSE)) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"!bq1||!(false&&False)||!(FALSE)"},
                    variables: new[] {"bq1"},
                    operators: new BooleanOperatorCount {NegateCount = 3, AndCount = 1, OrCount = 2});

                // test Equality
                yield return BooleanTestCaseData(
                    formText: $"form NameForm {{bq1 : \"1\"  boolean if (true == true) {{ x : \"xx\"  date }} }} ",
                    definitions: new[] {"true==true"},
                    operators: new BooleanOperatorCount {EqualityCount = 1},
                    subdefinitions: new[] {"true==true"});

                //ToDo: move this to relational test
                //// test Inequality
                //yield return BooleanTestCaseData(
                //    formText: $"form NameForm {{bq1 : \"1\"  boolean if (false != true) {{ x : \"xx\"  date }} }} ",
                //    definitions: new[] { "false!=true" },
                //    operators: new BooleanOperatorCount { InequalityCount = 1 },
                //    subdefinitions: new[] { "false!=true" });
                //ToDo: continue conversion of old tests
            }
        }

        private static TestCaseData BooleanTestCaseData(
            string formText,
            IEnumerable<string> definitions = null,
            IEnumerable<bool> values = null,
            IEnumerable<string> variables = null,
            BooleanOperatorCount operators = new BooleanOperatorCount(),
            IEnumerable<string> subdefinitions = null)
        {
            definitions = definitions ?? new List<string>();
            values = values ?? new List<bool>();
            variables = variables ?? new List<string>();
            subdefinitions = subdefinitions ?? new List<string>();

            return new TestCaseData(
                formText,
                definitions,
                values,
                variables,
                operators,
                subdefinitions);
        }

        public static IEnumerable MathStatements
        {
            get
            {
                var formTemplate = "form NameForm {{ intq: \"iii\"  integer=({0})}}";
                //test single number
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "1"),
                    values: new[] {1m});

                //test single decimal number
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "2.1"),
                    values: new[] {2.1m});

                //test single variable name
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "intVar"),
                    variables: new[] {"intVar"});

                //test bracketed single decimal number
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "(3.14)"),
                    values: new[] {3.14m});

                //test Multiply
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "(6 * 9)"),
                    values: new[] {6m, 9m},
                    operators: new MathOperatorCount {MultiplicationCount = 1});

                //test divide
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "(6 / 9) / (6 / 2)"),
                    values: new[] {6m, 9m, 6m, 2m},
                    operators: new MathOperatorCount {DivisionCount = 3});

                //test Add
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "(1.2 + 3.4 + 5.6)"),
                    values: new[] {1.2m, 3.4m, 5.6m},
                    operators: new MathOperatorCount {AdditionCount = 2});

                //test minus
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "(10 - 8 - 5 - 4)"),
                    values: new[] {10m, 8m, 5m, 4m},
                    operators: new MathOperatorCount {SubtractionCount = 3});

                //test variable
                yield return MathTestCaseData(
                    formText: string.Format(formTemplate, "(intVar + 8 + decVar + 4)"),
                    variables: new[] {"intVar", "decVar"},
                    operators: new MathOperatorCount {AdditionCount = 3});
            }
        }

        private static TestCaseData MathTestCaseData(
            string formText,
            IEnumerable<decimal> values = null,
            IEnumerable<string> variables = null,
            MathOperatorCount operators = new MathOperatorCount())
        {
            values = values ?? new List<decimal>();
            variables = variables ?? new List<string>();

            return new TestCaseData(
                formText,
                values,
                variables,
                operators);
        }

        public static IEnumerable ComparisonConditional
        {
            get
            {
                var formTemplate = "form NameForm {{ bq1: \"xx\" boolean if ({0}) {{aq : \"yy\"  boolean }} }}";

                // test equal to true
                yield return new TestCaseData(
                    string.Format(formTemplate, "bq1 == true"),
                    new[] {"bq1==true"},
                    new[] {true},
                    new[] {"bq1"},
                    new RelationalOperatorCount() {EqualityCount = 1});

                // test equal to True
                yield return new TestCaseData(
                    string.Format(formTemplate, "bq1 == True"),
                    new[] {"bq1==True"},
                    new[] {true},
                    new[] {"bq1"},
                    new RelationalOperatorCount {EqualityCount = 1});

                // test equal to TRUE
                yield return new TestCaseData(
                    string.Format(formTemplate, "bq1 == TRUE"),
                    new[] {"bq1==TRUE"},
                    new[] {true},
                    new[] {"bq1"},
                    new RelationalOperatorCount {EqualityCount = 1});

                // test equal to false
                yield return new TestCaseData(
                    string.Format(formTemplate, "false == bq1"),
                    new[] {"false==bq1"},
                    new[] {false},
                    new[] {"bq1"},
                    new RelationalOperatorCount {EqualityCount = 1});

                // test equal to False
                yield return new TestCaseData(
                    string.Format(formTemplate, "False == bq1"),
                    new[] {"False==bq1"},
                    new[] {false},
                    new[] {"bq1"},
                    new RelationalOperatorCount {EqualityCount = 1});

                // test equal to FALSE
                yield return new TestCaseData(
                    string.Format(formTemplate, "FALSE == bq1"),
                    new[] {"FALSE==bq1"},
                    new[] {false},
                    new[] {"bq1"},
                    new RelationalOperatorCount {EqualityCount = 1});

                // test inequality
                yield return new TestCaseData(
                    string.Format(formTemplate, "FALSE != bq1"),
                    new[] {"FALSE!=bq1"},
                    new[] {false},
                    new[] {"bq1"},
                    new RelationalOperatorCount {InequalityCount = 1});

                // test inequality 2 variables
                yield return new TestCaseData(
                    string.Format(formTemplate, "bq1 != bq1"),
                    new[] { "bq1!=bq1" },
                    new bool[] { },
                    new[] { "bq1", "bq1" },
                    new RelationalOperatorCount { InequalityCount = 1 });
                
                }
        }

        public static IEnumerable DateComparisonConditional
        {
            get
            {
                var formTemplate = "form NameForm {{ dq1: \"dd\" date if ({0}) {{aq : \"yy\"  boolean }} }}";

                // test date equality condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "01/10/2013 == 02/03/1965"),
                    new[] {"01/10/2013==02/03/1965"},
                    new[] {new DateTime(2013, 10, 1), new DateTime(1965, 3, 2)},
                    new string[] { },
                    new RelationalOperatorCount {EqualityCount = 1});

                // test equality with variable
                yield return new TestCaseData(
                    string.Format(formTemplate, "01/10/2013 == 02/03/1965"),
                    new[] {"01/10/2013==02/03/1965"},
                    new[] {new DateTime(2013, 10, 1), new DateTime(1965, 3, 2)},
                    new string[] { },
                    new RelationalOperatorCount {EqualityCount = 1});

                // test equality with variable
                yield return new TestCaseData(
                    string.Format(formTemplate, "01/10/2013 == dq1"),
                    new[] {"01/10/2013==dq1"},
                    new[] {new DateTime(2013, 10, 1)},
                    new[] {"dq1"},
                    new RelationalOperatorCount {EqualityCount = 1});

                // test inequality
                yield return new TestCaseData(
                    string.Format(formTemplate, "dq1 != 31/12/1999"),
                    new[] {"dq1!=31/12/1999"},
                    new[] {new DateTime(1999, 12, 31)},
                    new[] {"dq1"},
                    new RelationalOperatorCount {InequalityCount = 1});

                // test greater than
                yield return new TestCaseData(
                    string.Format(formTemplate, "dq1 > 31/12/1999"),
                    new[] {"dq1>31/12/1999"},
                    new[] {new DateTime(1999, 12, 31)},
                    new[] {"dq1"},
                    new RelationalOperatorCount {GreaterThanCount = 1});

                // test greater or equal
                yield return new TestCaseData(
                    string.Format(formTemplate, "dq1 >= 31/12/1999"),
                    new[] {"dq1>=31/12/1999"},
                    new[] {new DateTime(1999, 12, 31)},
                    new[] {"dq1"},
                    new RelationalOperatorCount {GreaterOrEqualCount = 1});

                // test less than 
                yield return new TestCaseData(
                    string.Format(formTemplate, "dq1 < 31/12/1999"),
                    new[] {"dq1<31/12/1999"},
                    new[] {new DateTime(1999, 12, 31)},
                    new[] {"dq1"},
                    new RelationalOperatorCount {LessThanCount = 1});


                // test less or equal 
                yield return new TestCaseData(
                    string.Format(formTemplate, "dq1 <= 31/12/1999"),
                    new[] {"dq1<=31/12/1999"},
                    new[] {new DateTime(1999, 12, 31)},
                    new[] {"dq1"},
                    new RelationalOperatorCount {LessOrEqualCount = 1});

            }
        }

        public static IEnumerable TextEqualityConditional
        {
            get
            {
                var formTemplate = "form NameForm {{ tq1: \"dd\" string if ({0}) {{aq : \"yy\"  boolean }} }}";
            
                // test string equality condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "\"bob\" == \"fred\""),
                    new[] { "\"bob\"==\"fred\"" },
                    new[] { "bob", "fred" },
                    new string[] { },
                    new RelationalOperatorCount { EqualityCount = 1 });

                // test string inequality condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "\"bob\" != \"squid\""),
                    new[] { "\"bob\"!=\"squid\"" },
                    new[] { "bob", "squid" },
                    new string[] { },
                    new RelationalOperatorCount { InequalityCount = 1 });

                // test string inequality with variable condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "tq1 != \"squid\""),
                    new[] { "tq1!=\"squid\"" },
                    new[] { "squid" },
                    new[] { "tq1" },
                    new RelationalOperatorCount { InequalityCount = 1 });

                // test string equality condition with varible
                yield return new TestCaseData(
                    string.Format(formTemplate, "\"MrBlobby\" == tq1"),
                    new[] { "\"MrBlobby\"==tq1" },
                    new[] { "MrBlobby" },
                    new[] { "tq1" },
                    new RelationalOperatorCount { EqualityCount = 1 });

                // test string equality condition with varible
                yield return new TestCaseData(
                    string.Format(formTemplate, "tq1 == tq1"),
                    new[] { "tq1==tq1" },
                    new string[] { },
                    new[] { "tq1", "tq1" },
                    new RelationalOperatorCount { EqualityCount = 1 });
            }
        }

        public static IEnumerable CalculationRelationalConditional
        {
            get
            {
                var formTemplate = "form NameForm {{ nq1: \"dd\" decimal if ({0}) {{aq : \"yy\"  boolean }} }}";

                // test number equality condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "1==2"),
                    new[] { "1==2" },
                    new[] { "1", "2" },
                    new string[] { },
                    new RelationalOperatorCount { EqualityCount = 1 });

                // test number equality condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "(3+4)!=(5*6)"),
                    new[] { "(3+4)!=(5*6)" },
                    new[] { "3+4", "5*6", "3", "4", "5", "6" },
                    new string[] { },
                    new RelationalOperatorCount { InequalityCount = 1 });
            
                // test number greater than with variable condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "nq1 > (5.8 / 6.3)"),
                    new[] { "nq1>(5.8/6.3)" },
                    new[] { "5.8/6.3", "5.8", "6.3", "nq1" },
                    new[] { "nq1" },
                    new RelationalOperatorCount { GreaterThanCount = 1 });

                // test number greater or equal with variable condition
                yield return new TestCaseData(
                    string.Format(formTemplate, "(9.5 - 8) >= nq1"),
                    new[] { "(9.5-8)>=nq1" },
                    new[] { "9.5-8", "9.5", "8", "nq1" },
                    new[] { "nq1" },
                    new RelationalOperatorCount { GreaterOrEqualCount = 1 });

                // test number less than and less or equal 
                yield return new TestCaseData(
                    string.Format(formTemplate, "(10 <= nq1) && ((nq1*nq1) < 100)"),
                    new[] { "10<=nq1","(nq1*nq1)<100" },
                    new[] { "nq1*nq1","10", "100", "nq1" },
                    new[] { "nq1", "nq1", "nq1" },
                    new RelationalOperatorCount { LessThanCount = 1, LessOrEqualCount = 1});
            }
        }
    }
}