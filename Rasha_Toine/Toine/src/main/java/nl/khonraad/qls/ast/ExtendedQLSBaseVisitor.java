package nl.khonraad.qls.ast;

import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.ql.algebra.Value;
import nl.khonraad.ql.algebra.value.Type;
import nl.khonraad.qls.QLSBaseVisitor;
import nl.khonraad.qls.QLSParser;
import nl.khonraad.qls.ast.data.Style;
import nl.khonraad.qls.ast.data.Styles;

public final class ExtendedQLSBaseVisitor extends QLSBaseVisitor<Value> {

    @Inject
    Logger logger;
    
    @Inject
    Styles styles;
    
    @Override public Value visitType(QLSParser.TypeContext ctx) { return visitChildren(ctx); }
    @Override public Value visitStylesheet(QLSParser.StylesheetContext ctx) { return visitChildren(ctx); }
    @Override public Value visitPage(QLSParser.PageContext ctx) { return visitChildren(ctx); }
    @Override public Value visitSection(QLSParser.SectionContext ctx) { return visitChildren(ctx); }
    @Override public Value visitQuestion(QLSParser.QuestionContext ctx) { return visitChildren(ctx); }
    @Override public Value visitStatement(QLSParser.StatementContext ctx) { return visitChildren(ctx); }
    @Override public Value visitDefaultstyle(QLSParser.DefaultstyleContext ctx) { 
        
        logger.info( ctx.type().getText() );
        
        if ( "boolean".equals( ctx.type().getText() )) {

            String t = ctx.widget().QuotedString(0).getText();
            String f = ctx.widget().QuotedString(1).getText();
            
            styles.saveDefault( new Style( Type.Boolean, t, f ));
        }
        return visitChildren(ctx); 
    }
    @Override public Value visitAttribute(QLSParser.AttributeContext ctx) { return visitChildren(ctx); }
    @Override public Value visitWidget(QLSParser.WidgetContext ctx) { return visitChildren(ctx); }
}

    

