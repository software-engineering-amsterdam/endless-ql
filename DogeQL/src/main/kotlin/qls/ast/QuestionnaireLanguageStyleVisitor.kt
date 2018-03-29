package qls.ast

import QuestionnaireLanguageStyleGrammarBaseVisitor
import QuestionnaireLanguageStyleGrammarParser
import qls.ast.node.QlsNode
import qls.model.*

class QuestionnaireLanguageStyleVisitor : QuestionnaireLanguageStyleGrammarBaseVisitor<QlsNode>() {

    override fun visitStylesheet(ctx: QuestionnaireLanguageStyleGrammarParser.StylesheetContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val pages = context.page().map {
            visit(it) as Page
        }

        val styleSheet = StyleSheet(pages, name)

        return styleSheet
    }

    override fun visitPage(ctx: QuestionnaireLanguageStyleGrammarParser.PageContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val styles = context.style().map {
            visit(it) as Style
        }

        return Page(styles, name)
    }

    override fun visitStyle(ctx: QuestionnaireLanguageStyleGrammarParser.StyleContext?): QlsNode {
        val context = ctx!!


        context.defaultAttributes()?.let {
            return visit(it) as DefaultAttributes
        }

        context.section()?.let {
            return visit(it) as Section
        }

        return TODO() // should be unreachable
    }

    override fun visitSection(ctx: QuestionnaireLanguageStyleGrammarParser.SectionContext?): QlsNode {
        val context = ctx!!

        val name = context.LIT_STRING().text


        val elements = context.element().element().map {
            visit(it) as Element
        }

        return Section(name, elements)
    }

    override fun visitElement(ctx: QuestionnaireLanguageStyleGrammarParser.ElementContext?): QlsNode {
        val context = ctx!!

        context.defaultAttributes()?.let {
            return visit(it)
        }

        context.question()?.let {
            return visit(it)
        }

        context.section()?.let {
            return visit(it)
        }



        return TODO()
    }

    override fun visitQuestion(ctx: QuestionnaireLanguageStyleGrammarParser.QuestionContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        return Question(name)
    }
}