package ru.spbstu.icc.kspt.generator.grammar

import org.antlr.v4.runtime.tree.TerminalNode
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4Parser.*
import ru.spbstu.icc.kspt.generator.model.*

class ANTLRv4ParserTestGeneratorVisitor : ANTLRv4ParserBaseVisitor<MutableList<Token>>() {
    val tokens = mutableMapOf<String, TokenRef>()
    var isLexerGrammar: Boolean = false

    override fun aggregateResult(aggregate: MutableList<Token>?, nextResult: MutableList<Token>?): MutableList<Token> {
        aggregate ?: return nextResult ?: return mutableListOf()
        nextResult ?: return aggregate

        aggregate.addAll(nextResult)
        return aggregate
    }

    override fun visitLexerRuleSpec(ctx: LexerRuleSpecContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        val first = ctx.getChild(0) as TerminalNode
        val tokenName = if (first.symbol.type == FRAGMENT) {
            (ctx.getToken(TOKEN_REF, 1) as TerminalNode).text
        } else first.text

        val tokenAlts = visitChildren(ctx)
        tokenAlts.removeFirst()
        val tokenRef = tokens[tokenName]?.apply { updateAlts(tokenAlts) }
            ?: TokenRef(
                alts = tokenAlts
            ).apply { tokens[tokenName] = this }
        return mutableListOf(tokenRef)
    }

    override fun visitLexerAltList(ctx: LexerAltListContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        return visitChildren(ctx)
    }

    override fun visitLexerAlt(ctx: LexerAltContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            TokenAlt(
                elements = visitChildren(ctx)
            )
        )
    }

    override fun visitLexerElements(ctx: LexerElementsContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        return visitChildren(ctx)
    }

    override fun visitLexerElement(ctx: LexerElementContext?): MutableList<Token> {
        ctx ?: return mutableListOf()

        val ebnfSuffix = ctx.getChild(1)?.text

        val token = visitChildren(ctx).first() as TokenWithSuffix
        token.suffix = ebnfSuffix
        return mutableListOf(token)
    }

    override fun visitLexerBlock(ctx: LexerBlockContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            TokenBlock(alts = visitChildren(ctx))
        )
    }

    override fun visitLexerAtom(ctx: LexerAtomContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        return visitChildren(ctx)
    }

    override fun visitTerminal(ctx: TerminalContext?): MutableList<Token> {
        return super.visitTerminal(ctx)
    }

    override fun visitTerminal(node: TerminalNode?): MutableList<Token> {
        node ?: return mutableListOf()
        return when (node.symbol.type) {
            LEXER -> mutableListOf<Token>().apply { isLexerGrammar = true }
            LEXER_CHAR_SET -> mutableListOf(TokenRegex(node.text))
            STRING_LITERAL -> mutableListOf(TokenString(node.text.trim('\'')))
            TOKEN_REF -> {
                val tokenRef = tokens[node.text] ?: TokenRef().apply { tokens[node.text] = this }
                val tokenRefLink = TokenRefLink(tokenRef)
                return mutableListOf(tokenRefLink)
            }
            else -> mutableListOf()
        }
    }

    override fun visitNotSet(ctx: NotSetContext?): MutableList<Token> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            TokenNot(elements = visitChildren(ctx))
        )
    }
}