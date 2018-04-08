package nl.khonraad.qls.parser;

import javax.inject.Inject;

import org.slf4j.Logger;

import nl.khonraad.qls.QLSBaseVisitor;
import nl.khonraad.qls.QLSParser;
import nl.khonraad.qls.language.StyleNode;
import nl.khonraad.qls.language.StylesInterpretor;
import nl.khonraad.qls.language.StyleNodeTree;
import nl.khonraad.qls.language.StyleNodeTree.NodeType;

public final class StylesVisitor extends QLSBaseVisitor<String> {

    @Inject
    Logger                    logger;

    @Inject
    private StylesInterpretor stylesInterpretor;

    StyleNodeTree<StyleNode>  treeNodePointer;

    @Override
    public String visitType( QLSParser.TypeContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public String visitStylesheet( QLSParser.StylesheetContext ctx ) {

        treeNodePointer = new StyleNodeTree<>( new StyleNode( NodeType.Stylesheet, ctx.Identifier().getText() ) );

        visitChildren( ctx );
        stylesInterpretor.declareAsStyleSheet( treeNodePointer );
        return ctx.Identifier().getText();

    }

    @Override
    public String visitPage( QLSParser.PageContext ctx ) {

        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Page, ctx.Identifier().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return ctx.Identifier().getText();
    }

    @Override
    public String visitSection( QLSParser.SectionContext ctx ) {

        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Section, ctx.QuotedString().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return ctx.QuotedString().getText();
    }

    @Override
    public String visitQuestion( QLSParser.QuestionContext ctx ) {

        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Question, ctx.Identifier().getText() ) );

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

        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.DefaultStyle, ctx.questionType().getText() ) );

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
        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Widget, ctx.widgetType().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return "";
    }

    @Override
    public String visitGivenWidth( QLSParser.GivenWidthContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Width, ctx.IntegerConstant().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenColor( QLSParser.GivenColorContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Color, ctx.HexConstant().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenFont( QLSParser.GivenFontContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.Font, ctx.QuotedString().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }

    @Override
    public String visitGivenFontsize( QLSParser.GivenFontsizeContext ctx ) {
        treeNodePointer = treeNodePointer.addChild( new StyleNode( NodeType.FontSize, ctx.IntegerConstant().getText() ) );

        visitChildren( ctx );

        treeNodePointer = treeNodePointer.parent();

        return visitChildren( ctx );
    }
}