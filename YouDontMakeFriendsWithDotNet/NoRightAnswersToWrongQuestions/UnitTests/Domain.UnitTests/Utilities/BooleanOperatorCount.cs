namespace UnitTests.Domain.UnitTests.Utilities
{
    public struct BooleanOperatorCount
    {
        public int AndCount { get; set; }
        public int OrCount { get; set; }
        public int NegateCount { get; set; }
        public int EqualityCount { get; set; }
        public int InequalityCount { get; set; }
        public int GreaterOrEqualCount { get; set; }
        public int GreaterThanCount { get; set; }
        public int LessOrEqualCount { get; set; }
        public int LessThanCount { get; set; }
    }
}