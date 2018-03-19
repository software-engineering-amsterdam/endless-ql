using System;
using System.Collections;
using NUnit.Framework;

namespace UnitTests.Domain.UnitTests.Data
{
    public static class TestValidationData
    {
        public static IEnumerable RepeatedNamesDifferentTypes
        {
            get
            {
                yield return new TestCaseData(
                    @"form John { q: ""i""  date q: ""2"" string}",
                    new[] {typeof(DateTime), typeof(string)},
                    @"The Question identifier 'q' is used multiple times with different types");

                yield return new TestCaseData(
                    @"form Matthew { x: ""i"" integer x: ""2"" decimal x: ""3"" string }",
                    new[] {typeof(int), typeof(decimal), typeof(string)},
                    @"The Question identifier 'x' is used multiple times with different types");

                yield return new TestCaseData(
                    @"form Matthew { xj: ""i"" integer xj: ""2"" decimal xj: ""3"" decimal }",
                    new[] {typeof(int), typeof(decimal), typeof(decimal)},
                    @"The Question identifier 'xj' is used multiple times with different types");
            }
        }

        public static IEnumerable RepeatedNamesSameTypes
        {
            get
            {
                yield return new TestCaseData(
                    @"form John { q: ""i""  date q: ""2"" date}");

                yield return new TestCaseData(
                    @"form Matthew { x: ""i"" integer x: ""2"" integer x: ""3"" integer }");
            }
        }
        
        public static IEnumerable UndefinedVariable
        {
            get
            {
                yield return new TestCaseData(
                    @"form Luke { q: ""i"" integer=(pj+3)}",
                    @"The variable 'pj' has not been defined");

                yield return new TestCaseData(
                    @"form Mark { q: ""i"" integer if (j) { q2: ""i"" integer} }",
                    @"The variable 'j' has not been defined");
            }
        }

        public static IEnumerable NonBooleanConditionVariable
        {
            get
            {
                yield return new TestCaseData(
                    @"form Phil { pm: ""i"" integer if (pm) { q2: ""i"" integer} }",
                    @"The variable 'pm' is in a condition but is not a bool, it is 'System.Int32'");

                yield return new TestCaseData(
                    @"form Grant { gm: ""i"" date if (gm) { q2: ""i"" integer} }",
                    @"The variable 'gm' is in a condition but is not a bool, it is 'System.DateTime'");

                yield return new TestCaseData(
                    @"form Peggy { pm: ""i"" decimal if (pm) { q2: ""i"" integer} }",
                    @"The variable 'pm' is in a condition but is not a bool, it is 'System.Decimal'");

                yield return new TestCaseData(
                    @"form Sam { sm: ""i"" string if (sm) { q2: ""i"" integer} }",
                    @"The variable 'sm' is in a condition but is not a bool, it is 'System.String'");

                yield return new TestCaseData(
                    @"form Billy { bm: ""i"" integer if (bm && True) { q2: ""i"" integer} }",
                    @"The variable 'bm' is in a condition but is not a bool, it is 'System.Int32'");

                yield return new TestCaseData(
                    @"form Sharron { sm: ""i"" date if (False || sm) { q2: ""i"" integer} }",
                    @"The variable 'sm' is in a condition but is not a bool, it is 'System.DateTime'");

                yield return new TestCaseData(
                    @"form Tiffany { tm: ""i"" decimal if (!tm) { q2: ""i"" integer} }",
                    @"The variable 'tm' is in a condition but is not a bool, it is 'System.Decimal'");
            }
        }

        public static IEnumerable NonDateConditionVariable
        {
            get
            {
                yield return new TestCaseData(
                    @"form Arthur { af: ""i"" integer if (19/02/1985 == af) { q2: ""i"" integer} }",
                    @"The variable 'af' is in a date comparison but is not a date, it is 'System.Int32'");

                yield return new TestCaseData(
                    @"form Pauleen { pf: ""i"" boolean if (pf != 19/2/85) { q2: ""i"" integer} }",
                    @"The variable 'pf' is in a date comparison but is not a date, it is 'System.Boolean'");

                yield return new TestCaseData(
                    @"form Mark { mf: ""i"" decimal if (mf > 14/02/2003) { q2: ""i"" integer} }",
                    @"The variable 'mf' is in a date comparison but is not a date, it is 'System.Decimal'");

                yield return new TestCaseData(
                    @"form Michelle { mf: ""i"" string if (31/12/94 <= mf) { q2: ""i"" integer} }",
                    @"The variable 'mf' is in a date comparison but is not a date, it is 'System.String'");
            }
        }
        
        public static IEnumerable NonTextConditionVariable
        {
            get
            {
                yield return new TestCaseData(
                    @"form Ian { ib: ""i"" integer if (""a string"" == ib) { q2: ""i"" integer} }",
                    @"The variable 'ib' is in a string comparison but is not a string, it is 'System.Int32'");

                yield return new TestCaseData(
                    @"form Kathy { kb: ""i"" boolean if (kb != ""another sting"") { q2: ""i"" integer} }",
                    @"The variable 'kb' is in a string comparison but is not a string, it is 'System.Boolean'");
               }
        }

        public static IEnumerable NonNumericConditionVariable
        {
            get
            {
                yield return new TestCaseData(
                    @"form Ricky { rb: ""i"" date if (1 == rb) { q2: ""i"" integer} }",
                    @"The variable 'rb' is in a number comparison but is not a number, it is 'System.DateTime'");

                yield return new TestCaseData(
                    @"form Pat { pb: ""i"" string if (pb != 10.5) { q2: ""i"" integer} }",
                    @"The variable 'pb' is in a number comparison but is not a number, it is 'System.String'");

                yield return new TestCaseData(
                    @"form Frank { fb: ""i"" boolean if (-200 <= fb) { q2: ""i"" integer} }",
                    @"The variable 'fb' is in a number comparison but is not a number, it is 'System.Boolean'");

                yield return new TestCaseData(
                    @"form Bianca { bb: ""i"" date if (bb > -365.25) { q2: ""i"" integer} }",
                    @"The variable 'bb' is in a number comparison but is not a number, it is 'System.DateTime'");
            }
        }

        //public static IEnumerable NonNumberCalculationVariable
        //{
        //    get
        //    {
        //        yield return new TestCaseData(
        //            @"form Ricky { rb: ""i"" boolean clc: ""c"" decimal  = (rb) }",
        //            @"The variable 'rb' is in a calculation but is not a number, it is 'System.Boolean'");

        //        yield return new TestCaseData(
        //            @"form Pat { pb: ""i"" date clc: ""c"" decimal  = (pb) }",
        //            @"The variable 'pb' is in a calculation but is not a number, it is 'System.DateTime'");

        //        yield return new TestCaseData(
        //            @"form Frank { fb: ""i"" string clc: ""c"" decimal  = (fb) }",
        //            @"The variable 'fb' is in a calculation but is not a number, it is 'System.String'");
        //    }
        //}
    }
}