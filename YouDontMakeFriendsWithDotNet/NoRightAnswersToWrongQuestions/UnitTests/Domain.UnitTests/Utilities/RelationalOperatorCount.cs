namespace QL.UnitTests.Domain.UnitTests.Utilities
{
    public struct RelationalOperatorCount
    {
        public int EqualityCount { get; set; }
        public int InequalityCount { get; set; }
        public int GreaterThanCount { get; set; }
        public int GreaterOrEqualCount { get; set; }
        public int LessThanCount { get; set; }
        public int LessOrEqualCount { get; set; }
    }
}