using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;
using QLParser.Analysis.QL.Semantic;
using QLParser.AST;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;

namespace QLParser.Tests.Analysis.Semantic
{
    [TestClass]
    public class StatementTypeAnalyserTests : QLTest
    {
        [TestMethod]
        public void ValidBoolComparisonTest()
        {
            SymbolTable.Add("TestBool", QValueType.Boolean);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");
            var right = new LiteralNode(new Location(0, 0), "false", QValueType.Boolean);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.Or, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidBoolComparisonTest()
        {
            SymbolTable.Add("TestBool", QValueType.Boolean);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");
            var right = new LiteralNode(new Location(0, 0), "false", QValueType.Integer);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.Or, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void ValidComparisonWithArthimetricTest()
        {
            SymbolTable.Add("TestBool", QValueType.Boolean);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.Integer);
            var ten = new LiteralNode(new Location(0, 0), "10", QValueType.Integer);
            var right = new ComparisonExpressionNode(new Location(0, 0), five, ComparisonOperator.GreaterEqual, ten);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.Or, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidComparisonWithArthimetricTest()
        {
            SymbolTable.Add("TestBool", QValueType.Boolean);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.Integer);
            var ten = new LiteralNode(new Location(0, 0), "blah blah", QValueType.Text);
            var right = new ComparisonExpressionNode(new Location(0, 0), five, ComparisonOperator.GreaterEqual, ten);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.Or, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void ValidArthimetricComparisonTest()
        {
            SymbolTable.Add("x", QValueType.Double);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.Integer);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.Mult, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidArthimetricComparisonTest()
        {
            SymbolTable.Add("blah blah", QValueType.Text);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.Integer);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.Mult, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void InValidArthimetricComparisonWithBooleanTest()
        {
            SymbolTable.Add("blah blah", QValueType.Text);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.Boolean);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.Mult, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void IntAndDoubleMultiplicationTest()
        {
            SymbolTable.Add("x", QValueType.Integer);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5.0", QValueType.Double);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.Mult, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void ValidBooleanComparisonTest()
        {
            SymbolTable.Add("x", QValueType.Boolean);
            var x = new IdentifierNode(new Location(0, 0), "x");

            SymbolTable.Add("y", QValueType.Boolean);
            var y = new IdentifierNode(new Location(0, 0), "y");

            var comparisonNode = new ComparisonExpressionNode(new Location(0, 0), x, ComparisonOperator.Equal, y);
            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);

            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidBooleanComparisonTest()
        {
            SymbolTable.Add("x", QValueType.Boolean);
            var x = new IdentifierNode(new Location(0, 0), "x");

            SymbolTable.Add("y", QValueType.Boolean);
            var y = new IdentifierNode(new Location(0, 0), "y");

            var comparisonNode = new ComparisonExpressionNode(new Location(0, 0), x, ComparisonOperator.LessEqual, y);
            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);

            Assert.IsFalse(result);
        }
    }
}