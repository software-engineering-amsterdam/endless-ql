package org.uva.forcepushql.tests.testLexer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.junit.Assert;
import org.junit.Test;
import org.uva.forcepushql.antlr.GrammarLexer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class LexerTest {

    public GrammarLexer lexerForCode (String code) throws IOException {
        return new GrammarLexer(new ANTLRInputStream(new StringReader(code)));
    }

    /*public GrammarLexer lexerForResource (String resourceName) throws IOException {
        return new GrammarLexer(new ANTLRInputStream(this.getClass().getResourceAsStream("/${resourceName}.grammar")));
    }*/

    public List<String> tokens(GrammarLexer lexer){
        LinkedList<String> tokens = new LinkedList<String>();
        Token t = lexer.nextToken();
        int tType = t.getType();

        do{

            if(tType == -1)
                tokens.add("EOF");

            else{
                if (tType != GrammarLexer.WHITESPACE)
                    tokens.add(lexer.getRuleNames()[tType - 1]);
            }

            t = lexer.nextToken();
            tType = t.getType();

        } while (t.getType() != -1);

        return tokens;

    }

    @Test public void parseLabelVariableAssignedABoolean() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("BOOL");
        Assert.assertEquals(test ,tokens(lexerForCode("\"Did you sell a house?\" hasSoldHouse:boolean" )));

    }

    @Test public void parseLabelVariableAssignedString() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("STR");
        Assert.assertEquals(test, tokens(lexerForCode("\"What is your name?\" userName:string")));
    }

    @Test public void parseLabelVariableAssignedInteger() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("INT");
        Assert.assertEquals(test, tokens(lexerForCode("\"How old are you?\" userAge:integer")));
    }

    @Test public void parseLabelVariableAssignedDate() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("DATE");
        Assert.assertEquals(test, tokens(lexerForCode("\"What date were you born?\" userBirth:date")));
    }

    @Test public void parseLabelVariableAssignedDecimal() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("DECIMAL");
        Assert.assertEquals(test, tokens(lexerForCode("\"What is your weight?\" userWeight:decimal")));
    }

    @Test public void parseLabelVariableAssignedMultipleAnswer() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("MULTIPLEANSWER");
        Assert.assertEquals(test, tokens(lexerForCode("\"What are ingredients do you have?\" userIngredients:multipleAnswer")));
    }

    @Test public void parseLabelVariableAssignedMoney() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("MONEY");
        Assert.assertEquals(test, tokens(lexerForCode("\"How much money did you spent on shopping?\" userShopping:money")));
    }

    @Test public void parseLabelVariableAssignedCurrency() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("MONEY");
        Assert.assertEquals(test, tokens(lexerForCode("\"How much money did you spent on shopping?\" userShopping:currency")));
    }

    @Test public void parseIfConditionQuestion() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("IF");
        test.add("LPAREN");
        test.add("VAR");
        test.add("RPAREN");
        test.add("LBRACE");
        test.add("LABEL");
        test.add("VAR");
        test.add("ASSIGN");
        test.add("BOOL");
        test.add("RBRACE");
        Assert.assertEquals(test, tokens(lexerForCode("if(var1){\"Did you sell a house in 2010?\" hasSoldHouse:boolean}")));
    }

    @Test public void parseMathExpression() throws IOException {
        LinkedList<String> test = new LinkedList<String>();
        test.add("LPAREN");
        test.add("NUM");
        test.add("PLUS");
        test.add("VAR");
        test.add("RPAREN");
        test.add("MINUS");
        test.add("DEC");
        test.add("GREATER");
        test.add("VAR");
        Assert.assertEquals(test, tokens(lexerForCode("(25 + var1) - 58.45 > var2")));
    }
}
