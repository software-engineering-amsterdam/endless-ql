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
    }
}
