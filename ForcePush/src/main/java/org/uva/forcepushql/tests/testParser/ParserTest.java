package org.uva.forcepushql.tests.testParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;

import java.io.IOException;
import java.io.StringReader;


public class ParserTest
{

    public GrammarLexer lexerForCode(String code) throws IOException
    {
        return new GrammarLexer(new ANTLRInputStream(new StringReader(code)));
    }


    public GrammarParser parserForCode(GrammarLexer grammarLexer){

        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);
        return new GrammarParser(commonTokenStream);
    }

    @Test
    public void parseQuestionFormat() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode(" \"Did you sell a house in 2010?\" hasSoldHouse: boolean"));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.questionFormat().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst)));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.questionFormat().getSourceInterval();

        Assert.assertEquals("\"Did you sell a house in 2010?\"hasSoldHouse:boolean", tokenStreamSecond.getText(rangeSecond));
    }

    @Test
    public void parseSimpleMathExpression() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode("4-2"));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.mathUnit().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst)));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.mathUnit().getSourceInterval();

        Assert.assertEquals("4-2", tokenStreamSecond.getText(rangeSecond));
    }

    @Test
    public void parseMathExpression() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode("((4 + 9) * 75 ) / 41"));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.mathUnit().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst)));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.mathUnit().getSourceInterval();

        Assert.assertEquals("((4+9)*75)/41", tokenStreamSecond.getText(rangeSecond));
    }

    @Test
    public void parseConditionalIfExpression() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode("if (hasSoldHouse) { \"What was the selling price?\"" +
                " sellingPrice: money}"));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.conditionalIf().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst)));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.conditionalIf().getSourceInterval();

        Assert.assertEquals("if(hasSoldHouse){\"What was the selling price?\"sellingPrice:money}",
                tokenStreamSecond.getText(rangeSecond));
    }

    @Test
    public void parseConditionalExpression() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode("if (hasSoldHouse) { \"What was the selling price?\"" +
                " sellingPrice: money }  ifelse(hasBoughtHouse){\n" +
                "         \"Private debts for the sold house:\"\n" +
                "          privateDebt: money } else { \"Did you buy a house in 2010?\"\n" +
                "    hasBoughtHouse: boolean } "));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.conditionalIf().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst)));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.conditionalIf().getSourceInterval();

        Assert.assertEquals("if(hasSoldHouse){\"What was the selling price?\"" +
                        "sellingPrice:money}ifelse(hasBoughtHouse){" +
                        "\"Private debts for the sold house:\"" +
                        "privateDebt:money}else{\"Did you buy a house in 2010?\"" +
                        "hasBoughtHouse:boolean}",
                tokenStreamSecond.getText(rangeSecond));
    }

    @Test
    public void parseForm() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode("form taxOfficeExample {\n" +
                "  \"Did you sell a house in 2010?\"\n" +
                "    hasSoldHouse: boolean\n" +
                "  \"Did you buy a house in 2010?\"\n" +
                "    hasBoughtHouse: boolean\n" +
                "  \"Did you enter a loan?\"\n" +
                "    hasMaintLoan: boolean\n } "));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.formStructure().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst).replaceAll("formtaxOffice",
                "form taxOffice")));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.formStructure().getSourceInterval();

        Assert.assertEquals("formtaxOfficeExample{" +
                        "\"Did you sell a house in 2010?\"" +
                        "hasSoldHouse:boolean" +
                        "\"Did you buy a house in 2010?\"" +
                        "hasBoughtHouse:boolean" +
                        "\"Did you enter a loan?\"" +
                        "hasMaintLoan:boolean}",
                tokenStreamSecond.getText(rangeSecond));
    }


    @Test
    public void parseQuestionAssignValueFormat() throws IOException {
        GrammarParser grammarParserFirst = parserForCode(lexerForCode("\"Value residue:\"" +
                "      valueResidue: money =" +
                "       (sellingPrice - privateDebt)"));
        TokenStream tokenStreamFirst = grammarParserFirst.getTokenStream();
        Interval rangeFirst = grammarParserFirst.questionAssignValue().getSourceInterval();

        GrammarParser grammarParserSecond = parserForCode(lexerForCode(tokenStreamFirst.getText(rangeFirst)));
        TokenStream tokenStreamSecond = grammarParserSecond.getTokenStream();
        Interval rangeSecond = grammarParserSecond.questionAssignValue().getSourceInterval();

        Assert.assertEquals("\"Value residue:\"" +
                "valueResidue:money=" +
                "(sellingPrice-privateDebt)", tokenStreamSecond.getText(rangeSecond));
    }
}
