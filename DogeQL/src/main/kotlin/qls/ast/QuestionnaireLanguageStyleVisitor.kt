package qls.ast

import QuestionnaireLanguageStyleGrammarBaseVisitor
import QuestionnaireLanguageStyleGrammarParser
import qls.ast.model.*
import qls.ast.node.QlsNode

class QuestionnaireLanguageStyleVisitor : QuestionnaireLanguageStyleGrammarBaseVisitor<QlsNode>() {

    override fun visitStylesheet(ctx: QuestionnaireLanguageStyleGrammarParser.StylesheetContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val pages = context.page().map {
            it.accept(this) as Page
        }

        return StyleSheet(pages, name)
    }

    override fun visitPage(ctx: QuestionnaireLanguageStyleGrammarParser.PageContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        val styles = context.style().map {
            it.accept(this) as Style
        }

        return Page(styles, name)
    }

    override fun visitStyle(ctx: QuestionnaireLanguageStyleGrammarParser.StyleContext?): QlsNode {
        val context = ctx!!


        context.defaultAttributes()?.let {
            return it.accept(this) as DefaultAttributes
        }

        context.section()?.let {
            return it.accept(this) as Section
        }

        throw IllegalStateException("Unreachable state")
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

        throw IllegalStateException("Unreachable state")
    }

    override fun visitQuestion(ctx: QuestionnaireLanguageStyleGrammarParser.QuestionContext?): QlsNode {
        val context = ctx!!

        val name = context.NAME().text

        return Question(name)
    }
}