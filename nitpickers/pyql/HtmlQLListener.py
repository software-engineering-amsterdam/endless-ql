import sys
from antlr4 import *
from QLParser import QLParser
from QLListener import QLListener


class HtmlQLListener(QLListener):
    def __init__(self, output):
        self.output = output
        self.output.write('<html><head><meta charset="UTF-8"/></head><body>')

    def enterName(self, ctx: QLParser.NameContext):
        self.output.write("<strong>")

    def exitName(self, ctx: QLParser.NameContext):
        self.output.write(ctx.WORD().getText())
        self.output.write("</strong> ")

    def enterColor(self, ctx: ChatParser.ColorContext):
        color = ctx.WORD().getText()
        ctx.text = '<span style="color: ' + color + '">'

    def exitColor(self, ctx: QLParser.ColorContext):
        ctx.text += ctx.message().text
        ctx.text += '</span>'

    def exitEmoticon(self, ctx: QLParser.EmoticonContext):
        emoticon = ctx.getText()

    def exitEmoticon(self, ctx: QLParser.EmoticonContext):
        emoticon = ctx.getText()

        if emoticon == ':-)' or emoticon == ':)':
            ctx.text = "🙂"

        if emoticon == ':-(' or emoticon == ':(':
            ctx.text = "🙁"

    def enterLink(self, ctx: QLParser.LinkContext):
        ctx.text = '<a href="%s">%s</a>' % (ctx.TEXT()[1], (ctx.TEXT()[0]))

    def exitMessage(self, ctx: QLParser.MessageContext):
        text = ''

        for child in ctx.children:
            if hasattr(child, 'text'):
                text += child.text
            else:
                text += child.getText()

        if isinstance(ctx.parentCtx, QLParser.LineContext) is False:
            ctx.text = text
        else:
            self.output.write(text)
            self.output.write("</p>")

    def enterCommand(self, ctx: QLParser.CommandContext):
        if ctx.SAYS() is not None:
            self.output.write(ctx.SAYS().getText() + ':' + '<p>')

        if ctx.SHOUTS() is not None:
            self.output.write(ctx.SHOUTS().getText() + ':' + '<p style="text-transform: uppercase">')

    def exitChat(self, ctx: QLParser.ChatContext):
        self.output.write("</body></html>")