package nl.khonraad.qls.ast;

import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.qls.QLSBaseVisitor;
import nl.khonraad.qls.QLSParser;
import nl.khonraad.qls.language.QLSInterpretor;
import nl.khonraad.qls.language.TreeNode;

public final class ExtendedQLSBaseVisitor extends QLSBaseVisitor<String> {

    @Inject
    Logger                 logger;

    @Inject
    private QLSInterpretor interpretor;

    TreeNode<String>       pointer;

    @Override
    public String visitType( QLSParser.TypeContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public String visitStylesheet( QLSParser.StylesheetContext ctx ) {

        pointer = new TreeNode<String>( "Stylesheet: " + ctx.Identifier().getText() );

        visitChildren( ctx );

        interpretor.declareStyleSheet( pointer );

        return ctx.Identifier().getText();

    }

    @Override
    public String visitPage( QLSParser.PageContext ctx ) {

        pointer = pointer.addChild( "Page: " + ctx.Identifier().getText() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return ctx.Identifier().getText();
    }

    @Override
    public String visitSection( QLSParser.SectionContext ctx ) {

        pointer = pointer.addChild( "Section: " + ctx.QuotedString().getText() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return ctx.QuotedString().getText();
    }

    @Override
    public String visitQuestion( QLSParser.QuestionContext ctx ) {

        pointer = pointer.addChild( "Question: " + ctx.Identifier().getText() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return ctx.Identifier().getText();
    }

    @Override
    public String visitSectionElements( QLSParser.SectionElementsContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public String visitSectionElement( QLSParser.SectionElementContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public String visitDefaultStyle( QLSParser.DefaultStyleContext ctx ) {

        pointer = pointer.addChild( "Defaultstyle: " + ctx.questionType().getText()  );

        visitChildren( ctx );

        pointer = pointer.parent();

        return ctx.questionType().getText();

    }

    @Override
    public String visitQuestionType( QLSParser.QuestionTypeContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public String visitStyling( QLSParser.StylingContext ctx ) {

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenWidgetType( QLSParser.GivenWidgetTypeContext ctx ) {
        pointer = pointer.addChild( "Widget:  - " + ctx.widgetType().getText());

        visitChildren( ctx );

        pointer = pointer.parent();

        return "";
    }

    @Override
    public String visitGivenWidth( QLSParser.GivenWidthContext ctx ) {
        pointer = pointer.addChild( "Width  - " + ctx.IntegerConstant() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenColor( QLSParser.GivenColorContext ctx ) {
        pointer = pointer.addChild( "Color  - " + ctx.HexConstant() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenFont( QLSParser.GivenFontContext ctx ) {
        pointer = pointer.addChild( "Font  - " + ctx.QuotedString() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenFontsize( QLSParser.GivenFontsizeContext ctx ) {
        pointer = pointer.addChild( "Fontsize  - " + ctx.IntegerConstant() );

        visitChildren( ctx );

        pointer = pointer.parent();

        return visitChildren( ctx );
    }

//    @Override
//    public String visitWidgetType( QLSParser.WidgetTypeContext ctx ) {
//        pointer = pointer.addChild( "WidgetType  - " + ctx.QuotedString() );
//
//        visitChildren( ctx );
//
//        pointer = pointer.parent();
//
//        return "";
//    }

}