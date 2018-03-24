using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Microsoft.CSharp.RuntimeBinder;

namespace Assignment1.Execution
{
    public class QLExpressionEvaluator : IExpressionVisitor
    {
        private IValue _result;
        private readonly Dictionary<string, IValue> _answers;

        public static IValue Evaluate(IExpression expression, Dictionary<string, IValue> answers)
        {
            var evaluator = new QLExpressionEvaluator(answers);
            try
            {
                expression.Accept(evaluator);
            }
            catch (RuntimeBinderException)
            {
                Debug.Fail("There is a mismatch between the type checker and this evaluator.");
                evaluator._result = new Undefined();
            }
            return evaluator._result;
        }

        private QLExpressionEvaluator(Dictionary<string, IValue> answers)
        {
            _answers = answers;
        }

        public static bool AsBool(IValue value)
        {
            return (value as QLBoolean)?.Value == true;
        }

        public void Visit(QLBoolean value)
        {
            _result = value;
        }

        public void Visit(QLInteger value)
        {
            _result = value;
        }

        public void Visit(Undefined undefined)
        {
            _result = undefined;
        }

        public void Visit(QLString value)
        {
            _result = value;
        }

        public void Visit(QLDate value)
        {
            _result = value;
        }

        public void Visit(QLDecimal value)
        {
            _result = value;
        }

        public void Visit(QLMoney value)
        {
            _result = value;
        }

        public void Visit(Not expression)
        {
            expression.Accept(this);
            _result = new QLBoolean(!AsBool(_result));
        }

        public void Visit(Reference expression)
        {
            _result = _answers.ContainsKey(expression.QuestionId) ? _answers[expression.QuestionId] : new Undefined();
        }

        private (IValue left, IValue right) VisitBinary(Binary expression)
        {
            expression.Left.Accept(this);
            var left = _result;
            expression.Right.Accept(this);
            var right = _result;
            return (left, right);
        }

        private IValue VisitConditional(Binary expression, Func<dynamic, dynamic, bool> condition)
        {
            (var left, var right) = VisitBinary(expression);
            return new QLBoolean(condition(left, right));
        }

        public void Visit(And expression)
        {
            (var left, var right) = VisitBinary(expression);
            _result = new QLBoolean(AsBool(left) && AsBool(right));
        }

        public void Visit(Or expression)
        {
            (var left, var right) = VisitBinary(expression);
            _result = new QLBoolean(AsBool(left) || AsBool(right));
        }

        public void Visit(LessThan expression)
        {
            _result = VisitConditional(expression, (a, b) => LessThan(a, b));
        }

        private static bool LessThan(Undefined a, object b) => false;
        private static bool LessThan(object a, Undefined b) => false;
        private static bool LessThan(QLInteger a, QLInteger b) => a.Value < b.Value;
        private static bool LessThan(QLDecimal a, QLDecimal b) => a.Value < b.Value;
        private static bool LessThan(QLDate a, QLDate b) => a.Value < b.Value;
        private static bool LessThan(QLMoney a, QLMoney b) => a.Value < b.Value;

        public void Visit(GreaterThan expression)
        {
            _result = VisitConditional(expression, (a, b) => GreaterThan(a, b));
        }

        private static bool GreaterThan(Undefined a, object b) => false;
        private static bool GreaterThan(object a, Undefined b) => false;
        private static bool GreaterThan(QLInteger a, QLInteger b) => a.Value > b.Value;
        private static bool GreaterThan(QLDecimal a, QLDecimal b) => a.Value > b.Value;
        private static bool GreaterThan(QLDate a, QLDate b) => a.Value > b.Value;
        private static bool GreaterThan(QLMoney a, QLMoney b) => a.Value > b.Value;

        public void Visit(GreaterThanOrEqual expression)
        {
            _result = VisitConditional(expression, (a, b) => GreaterThanOrEqual(a, b));
        }

        private static bool GreaterThanOrEqual(dynamic a, dynamic b) => GreaterThan(a, b) || Equal(a, b);

        public void Visit(LessThanOrEqual expression)
        {
            _result = VisitConditional(expression, (a, b) => LessThanOrEqual(a, b));
        }

        private static bool LessThanOrEqual(dynamic a, dynamic b) => LessThan(a, b) || Equal(a, b);

        public void Visit(NotEqual expression)
        {
            _result = VisitConditional(expression, (a, b) => NotEqual(a, b));
        }

        private static bool NotEqual(dynamic a, dynamic b) => !Equal(a, b);

        public void Visit(Equal expression)
        {
            _result = VisitConditional(expression, (a, b) => Equal(a, b));
        }

        private static bool Equal(Undefined a, object b) => false;
        private static bool Equal(object a, Undefined b) => false;
        private static bool Equal(QLInteger a, QLInteger b) => a.Value == b.Value;
        private static bool Equal(QLDecimal a, QLDecimal b) => a.Value == b.Value;
        private static bool Equal(QLDate a, QLDate b) => a.Value == b.Value;
        private static bool Equal(QLMoney a, QLMoney b) => a.Value == b.Value;
        private static bool Equal(QLString a, QLString b) => a.Value == b.Value;

        public void Visit(Add expression)
        {
            (dynamic left, dynamic right) = VisitBinary(expression);
            _result = Add(left, right);
        }

        private static Undefined Add(Undefined a, object b) => a;
        private static Undefined Add(object a, Undefined b) => Add(b, a);
        private static QLInteger Add(QLInteger a, QLInteger b) => new QLInteger(a.Value + b.Value);
        private static QLDecimal Add(QLDecimal a, QLDecimal b) => new QLDecimal(a.Value + b.Value);
        private static QLMoney Add(QLMoney a, QLMoney b) => new QLMoney(a.Value + b.Value);
        private static QLString Add(QLString a, QLString b) => new QLString(a.Value + b.Value);

        public void Visit(Subtract expression)
        {
            (dynamic left, dynamic right) = VisitBinary(expression);
            _result = Subtract(left, right);
        }

        private static Undefined Subtract(Undefined a, object b) => a;
        private static Undefined Subtract(object a, Undefined b) => Subtract(b, a);
        private static QLInteger Subtract(QLInteger a, QLInteger b) => new QLInteger(a.Value - b.Value);
        private static QLDecimal Subtract(QLDecimal a, QLDecimal b) => new QLDecimal(a.Value - b.Value);
        private static QLMoney Subtract(QLMoney a, QLMoney b) => new QLMoney(a.Value - b.Value);
        private static QLString Subtract(QLString a, QLString b) => a.Value.Contains(b.Value) ? new QLString(a.Value.Remove(a.Value.LastIndexOf(b.Value, StringComparison.Ordinal))) : a;

        public void Visit(Multiply expression)
        {
            (dynamic left, dynamic right) = VisitBinary(expression);
            _result = Multiply(left, right);
        }

        private static Undefined Multiply(Undefined a, object b) => a;
        private static Undefined Multiply(object a, Undefined b) => Multiply(b, a);
        private static QLInteger Multiply(QLInteger a, QLInteger b) => new QLInteger(a.Value * b.Value);
        private static QLDecimal Multiply(QLDecimal a, QLDecimal b) => new QLDecimal(a.Value * b.Value);
        private static QLMoney Multiply(QLInteger a, QLMoney b) => new QLMoney(a.Value * b.Value);
        private static QLMoney Multiply(QLMoney a, QLInteger b) => Multiply(b, a);
        private static QLMoney Multiply(QLDecimal a, QLMoney b) => new QLMoney(a.Value * b.Value);
        private static QLMoney Multiply(QLMoney a, QLDecimal b) => Multiply(b, a);
        private static QLDecimal Multiply(QLInteger a, QLDecimal b) => new QLDecimal(a.Value * b.Value);
        private static QLDecimal Multiply(QLDecimal a, QLInteger b) => Multiply(b, a);
        private static QLString Multiply(QLInteger a, QLString b) => new QLString(string.Concat(Enumerable.Repeat(b.Value, a.Value)));
        private static QLString Multiply(QLString a, QLInteger b) => Multiply(b, a);

        public void Visit(Divide expression)
        {
            (dynamic left, dynamic right) = VisitBinary(expression);
            _result = Divide(left, right);
        }

        private static Undefined Divide(Undefined a, object b) => a;
        private static Undefined Divide(object a, Undefined b) => Divide(b, a);
        private static QLInteger Divide(QLInteger a, QLInteger b) => new QLInteger(a.Value / b.Value);
        private static QLDecimal Divide(QLDecimal a, QLDecimal b) => new QLDecimal(a.Value / b.Value);
        private static QLMoney Divide(QLMoney a, QLInteger b) => new QLMoney(a.Value / b.Value);
        private static QLDecimal Divide(QLMoney a, QLMoney b) => new QLDecimal(a.Value / b.Value);
        private static QLDecimal Divide(QLInteger a, QLDecimal b) => new QLDecimal(a.Value / b.Value);
        private static QLDecimal Divide(QLDecimal a, QLInteger b) => new QLDecimal(a.Value / b.Value);
    }
}
