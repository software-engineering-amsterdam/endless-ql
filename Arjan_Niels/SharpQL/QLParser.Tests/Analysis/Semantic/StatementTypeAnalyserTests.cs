using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;
using QLParser.Analysis.Semantic;
using QLParser.AST;
using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using QLParser.Tests;

namespace QL_Parser.Tests.Analysis.Semantic
{
    [TestClass]
    public class StatementTypeAnalyserTests : QLTest
    {
        [TestMethod]
        public void ValidBoolComparisonTest()
        {
            SymbolTable.Add("TestBool", QValueType.BOOLEAN);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");
            var right = new LiteralNode(new Location(0, 0), "false", QValueType.BOOLEAN);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.OR, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidBoolComparisonTest()
        {
            SymbolTable.Add("TestBool", QValueType.BOOLEAN);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");
            var right = new LiteralNode(new Location(0, 0), "false", QValueType.INTEGER);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.OR, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void ValidComparisonWithArthimetricTest()
        {
            SymbolTable.Add("TestBool", QValueType.BOOLEAN);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.INTEGER);
            var ten = new LiteralNode(new Location(0, 0), "10", QValueType.INTEGER);
            var right = new ComparisonExpressionNode(new Location(0, 0), five, ComparisonOperator.GE, ten);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.OR, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidComparisonWithArthimetricTest()
        {
            SymbolTable.Add("TestBool", QValueType.BOOLEAN);
            var left = new IdentifierNode(new Location(0, 0), "TestBool");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.INTEGER);
            var ten = new LiteralNode(new Location(0, 0), "blah blah", QValueType.TEXT);
            var right = new ComparisonExpressionNode(new Location(0, 0), five, ComparisonOperator.GE, ten);
            var comparisonNode = new LogicalExpressionNode(new Location(0, 0), left, LogicalOperator.OR, right);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void ValidArthimetricComparisonTest()
        {
            SymbolTable.Add("x", QValueType.DOUBLE);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.INTEGER);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.MULT, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidArthimetricComparisonTest()
        {
            SymbolTable.Add("blah blah", QValueType.TEXT);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.INTEGER);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.MULT, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void InValidArthimetricComparisonWithBooleanTest()
        {
            SymbolTable.Add("blah blah", QValueType.TEXT);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5", QValueType.BOOLEAN);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.MULT, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void IntAndDoubleMultiplicationTest()
        {
            SymbolTable.Add("x", QValueType.INTEGER);
            var x = new IdentifierNode(new Location(0, 0), "x");

            var five = new LiteralNode(new Location(0, 0), "5.0", QValueType.DOUBLE);
            var arthimetricNode = new ArthimetricExpressionNode(new Location(0, 0), x, ArthimetricOperator.MULT, five);

            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(arthimetricNode);
            Assert.IsTrue(result);
        }

        [TestMethod]
        public void ValidBooleanComparisonTest()
        {
            SymbolTable.Add("x", QValueType.BOOLEAN);
            var x = new IdentifierNode(new Location(0, 0), "x");

            SymbolTable.Add("y", QValueType.BOOLEAN);
            var y = new IdentifierNode(new Location(0, 0), "y");

            var comparisonNode = new ComparisonExpressionNode(new Location(0, 0), x, ComparisonOperator.EQ, y);
            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);

            Assert.IsTrue(result);
        }

        [TestMethod]
        public void InValidBooleanComparisonTest()
        {
            SymbolTable.Add("x", QValueType.BOOLEAN);
            var x = new IdentifierNode(new Location(0, 0), "x");

            SymbolTable.Add("y", QValueType.BOOLEAN);
            var y = new IdentifierNode(new Location(0, 0), "y");

            var comparisonNode = new ComparisonExpressionNode(new Location(0, 0), x, ComparisonOperator.LE, y);
            var analyser = new StatementTypeAnalyser();
            var result = analyser.Analyse(comparisonNode);

            Assert.IsFalse(result);
        }
    }
}