package parsing.visitors;

import classes.CodeBlock;
import classes.Question;
import classes.values.*;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

import java.util.HashMap;

public class TypeVisitor extends QLBaseVisitor {
    public Value visitType(QLParser.TypeContext ctx){
        return (Value) visit(ctx);
    }

    @Override
    public BooleanValue visitBooltype(QLParser.BooltypeContext ctx) {
        return new BooleanValue();
    }

    @Override
    public StringValue visitStringtype(QLParser.StringtypeContext ctx) {
        return new StringValue();
    }

    @Override
    public IntegerValue visitIntegertype(QLParser.IntegertypeContext ctx) {
        return new IntegerValue();
    }

    @Override
    public MoneyValue visitMoneytype(QLParser.MoneytypeContext ctx) {
        return new MoneyValue();
    }

    @Override
    public DateValue visitDatetype(QLParser.DatetypeContext ctx) {
        return new DateValue();
    }

    @Override
    public DecimalValue visitDecimaltype(QLParser.DecimaltypeContext ctx) {
        return new DecimalValue();
    }

}