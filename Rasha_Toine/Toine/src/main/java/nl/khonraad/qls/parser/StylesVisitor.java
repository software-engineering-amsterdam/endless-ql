package nl.khonraad.qls.parser;

import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.qls.QLSBaseVisitor;
import nl.khonraad.qls.QLSParser;
import nl.khonraad.qls.language.Style;
import nl.khonraad.qls.language.StylesInterpretor;
import nl.khonraad.qls.language.StyleTree;
import nl.khonraad.qls.language.StyleTree.StyleType;

public final class StylesVisitor extends QLSBaseVisitor<String> {

    @Inject
    Logger                    logger;

    @Inject
    private StylesInterpretor stylesInterpretor;

    StyleTree<Style>          treeNodePointer;

    @Override
    public String visitType( QLSParser.TypeContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public String visitStylesheet( QLSParser.StylesheetContext ctx ) {

        treeNodePointer = new StyleTree<>( new Style( StyleType.Stylesheet, ctx.Identifier().getText() ) );

        visitChildren( ctx );
        stylesInterpretor.declareAsStyleSheet( treeNodePointer );
        return ctx.Identifier().getText();

    }

    @Override
    public String visitPage( QLSParser.PageContext ctx ) {

        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Page, ctx.Identifier().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return ctx.Identifier().getText();
    }

    @Override
    public String visitSection( QLSParser.SectionContext ctx ) {

        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Section, ctx.QuotedString().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return ctx.QuotedString().getText();
    }

    @Override
    public String visitQuestion( QLSParser.QuestionContext ctx ) {

        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Question, ctx.Identifier().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

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

        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.DefaultStyle, ctx.questionType().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

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
        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Widget, ctx.widgetType().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return "";
    }

    @Override
    public String visitGivenWidth( QLSParser.GivenWidthContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Width, ctx.IntegerConstant().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenColor( QLSParser.GivenColorContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Color, ctx.HexConstant().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenFont( QLSParser.GivenFontContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.Font, ctx.QuotedString().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenFontsize( QLSParser.GivenFontsizeContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new Style( StyleType.FontSize, ctx.IntegerConstant().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }
}