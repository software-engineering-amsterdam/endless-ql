using System.Collections;
using NUnit.Framework;
using QlsTransformer.Domain.Ast.Nodes;

namespace QLS.UnitTests.ParserTests
{
    public static class TestQlsData
    {
        public static IEnumerable EmptyStyleSheet
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { }",
                    @"ss1");

                yield return new TestCaseData(
                    @"stylesheet xyz { }",
                    @"xyz");

                yield return new TestCaseData(
                    @"stylesheet PhiL123 { }",
                    @"PhiL123");
            }
        }

        public static IEnumerable StyleSheetWithOnePage
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } }",
                    @"p1");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page anotherPage { } }",
                    @"anotherPage");
            }
        }

        public static IEnumerable StyleSheetWithMultiplePages
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { }}",
                    1);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { }}",
                    2);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { } page p3 { }}",
                    3);
            }
        }

        public static IEnumerable StyleSheetSection
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } } }",
                    @"s1");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { section s2 { } } }",
                    @"s2");
            }
        }

        public static IEnumerable MultipleStyleSheetSection
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } } }",
                    1);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } section s2 { } } }",
                    2);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } } page p2 { section s2 { } } }",
                    2);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { } section s2 { } } page p2 { section s11 { } section s12 { } section s13 { } } }",
                    5);
            }
        }

        public static IEnumerable WithAQuestion
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 } } }",
                    @"q1");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } page p2 { section s2 { question q2 } } }",
                    @"q2");
            }
        }

        public static IEnumerable WithMultipleQuestions
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 question q2} } }",
                    2);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1  { question q1 } } page p2 { section s2 { question q2 } } }",
                    2);
            }
        }

        public static IEnumerable StyleSheetWithDefaultStyle
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget checkbox}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio(""Ay"", ""Nee"") }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown(""darntootin"", ""nonononono"") }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default string widget textbox }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget slider(50, 100, 2)}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget spinbox}");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal widget textbox }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { widget textbox } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { widget textbox width: 200 } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { font : ""Arial"" fontsize: 12.5 color: #00FF19BF } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { fontsize: 9.2 widget textbox width: 120 font : ""ComicSans"" color: #FFFFFFFF } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default decimal { fontsize: 5 widget textbox color: #12345678 } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { default decimal { fontsize: 5 widget textbox color: #12345678 } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 question q2 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 widget textbox question q2 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 { widget textbox fontsize: 20 } question q2 default decimal { fontsize: 5 widget textbox color: #12345678 } } } }");
            }
        }

        public static IEnumerable WidgetType
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget checkbox}",
                    typeof(ICheckBox));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget textbox}",
                    typeof(ITextBox));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio}",
                    typeof(IRadioButton));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio(""blah"",""noblah"")}",
                    typeof(IRadioButton));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown}",
                    typeof(IDropDown));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown(""blah"",""noblah"")}",
                    typeof(IDropDown));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget spinbox}",
                    typeof(ISpinBox));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget slider}",
                    typeof(ISlider));

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget slider(20,40,2)}",
                    typeof(ISlider));
            }
        }

        public static IEnumerable BooleanWidgetType
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio}",
                    @"true",
                    @"false");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown}",
                    @"true",
                    @"false");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget radio(""hello"",""goodbye"")}",
                    @"hello",
                    @"goodbye");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default boolean widget dropdown(""fine"",""whatever"")}",
                    @"fine",
                    @"whatever");
            }
        }

        public static IEnumerable SliderWidget
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget slider}",
                    0,
                    100,
                    1);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget slider(100,2000,50)}",
                    100,
                    2000,
                    50);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer widget slider(-500,-300,20)}",
                    -500,
                    -300,
                    20);

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer { widget slider(100,2000,50) color: #00112233}}",
                    100,
                    2000,
                    50);
            }
        }

        public static IEnumerable PropertyValues
        {
            get
            {
                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { } default integer {width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }}",
                    200,
                    @"ComicSans",
                    12.3m,
                    @"#00000000");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { default integer {width: 100 font: ""Calibri"" fontsize: 48 color: #090B00F0 }}}",
                    100,
                    @"Calibri",
                    48m,
                    @"#090B00F0");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { default string {font: ""Wingdings"" width: 666 color: #01234567  fontsize: 9}}}}",
                    666,
                    @"Wingdings",
                    9m,
                    @"#01234567");

                yield return new TestCaseData(
                    @"stylesheet ss1 { page p1 { section s1 { question q1 { color: #FEDCBA98  fontsize: 22.5 font: ""Times New Roman"" width: 123 }}}}",
                    123,
                    @"Times New Roman",
                    22.5m,
                    @"#FEDCBA98");
            }
        }

        public static IEnumerable DefaultPropertyCounts
        {
            get
            {
                yield return new TestCaseData(
                    @"
stylesheet ss1 
{
    page p1 
    {
    }
    default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
    default boolean { widget radio(""Rick"",""Morty"") }
    default date { widget textbox width: 99 font: ""ComicSans"" } 
    default string { color: #FA57B055 }
    default decimal { widget slider(150,200,5) } 
}",
                    5);

                yield return new TestCaseData(
                    @"
stylesheet ss1 
{
    page p1 
    {
        default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
        default boolean { widget radio(""Rick"",""Morty"") }
        default date { widget textbox width: 99 font: ""ComicSans"" } 
    }
    default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
    default boolean { widget radio(""Rick"",""Morty"") }
    default date { widget textbox width: 99 font: ""ComicSans"" } 
    default string { color: #FA57B055 }
    default decimal { widget slider(150,200,5) } 
}",
                    8);

                yield return new TestCaseData(
                    @"
stylesheet ss1 
{
    page p1 
    {
        section s1 
        {
            default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
            default boolean { widget radio(""Rick"",""Morty"") }
        }
        default date { widget textbox width: 99 font: ""ComicSans"" } 
        default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
    }

    page p2 
    {
        section s2 
        {
            default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
        }
        default date { widget textbox width: 99 font: ""ComicSans"" } 
        default integer { widget spinbox width: 200 font: ""ComicSans"" fontsize: 12.3 color: #00000000 }
        default boolean { widget radio(""Rick"",""Morty"") }
    }

    default boolean { widget radio(""Rick"",""Morty"") }
    default decimal { widget slider(150,200,5) } 
}",
                    10);
            }
        }
    }
}