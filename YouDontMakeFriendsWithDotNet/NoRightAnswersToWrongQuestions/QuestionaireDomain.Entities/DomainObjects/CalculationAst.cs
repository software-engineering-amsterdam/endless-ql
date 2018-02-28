using System;
using System.ComponentModel;
using System.Data;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculationAst : AstNodeBase, ICalculationAst
    {
        public CalculationAst(Guid id, string calculationDefinition) : base(id)
        {
            CalculationDefinition = calculationDefinition;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public string CalculationDefinition { get; }

        public MathOperator Operator { get; set; }

        public MathValue Value { get; set; }

        public Reference<ICalculationAst> LeftChild { get; set; }

        public Reference<ICalculationAst> RightChild { get; set; }
    }

    public class Reference<T> where T : IAstNode
    {
        public Guid Id { get; set; }
    }

    public class MathValue
    {
        public MathType MathType { get; }
        public int? IntValue { get; }
        public decimal? DecimalValue { get; }
        public string VariableName { get; }

        public MathValue(
            MathType mathType,
            int? intValue = null,
            decimal? decimalValue = null,
            string variableName = null)
        {
            ValidateInput(mathType, intValue, decimalValue, variableName);

            MathType = mathType;
            IntValue = intValue;
            DecimalValue = decimalValue;
            VariableName = variableName;
        }

        private static void ValidateInput(MathType mathType, int? intValue, decimal? decimalValue, string variableName)
        {
            switch (mathType)
            {
                case MathType.Integer:
                    if (decimalValue.HasValue)
                    {
                        throw new ArgumentException("decimal value in integer math object", nameof(decimalValue));
                    }
                    break;
                case MathType.Decimal:
                    if (intValue.HasValue)
                    {
                        throw new ArgumentException("integer value in decimal math object", nameof(decimalValue));
                    }
                    break;
            }

            var valueCount =
                (intValue.HasValue ? 1 : 0) +
                (decimalValue.HasValue ? 1 : 0) +
                (string.IsNullOrEmpty(variableName) ? 1 : 0);

            if (valueCount != 1)
            {
                var intText = intValue?.ToString() ?? "NULL";
                var decimalText = decimalValue?.ToString() ?? "NULL";
                var variableText = variableName ?? "NULL";

                var message =
                    $@"the math object has an IntValue of '{intText}', " +
                    $@"a DecimalValue of {decimalText} " +
                    $@"and a VariableName of {variableText}. " +
                    "It should have just one value.";
                throw new ArgumentException(message);
            }
        }
    }

    public enum MathType
    {
        Integer,
        Decimal
    }
    
    public enum MathOperator
    {
        Terminal,
        Multiply,
        Divide,
        Add,
        Subtract
    }
}