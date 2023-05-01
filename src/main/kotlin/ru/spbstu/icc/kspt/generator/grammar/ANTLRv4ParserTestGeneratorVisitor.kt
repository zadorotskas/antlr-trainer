package ru.spbstu.icc.kspt.generator.grammar

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.TerminalNode
import ru.spbstu.icc.kspt.generator.grammar.ANTLRv4Parser.*
import ru.spbstu.icc.kspt.generator.model.*

class ANTLRv4ParserTestGeneratorVisitor : ANTLRv4ParserBaseVisitor<MutableList<Rule>>() {
    val tokens = mutableMapOf<String, LexerRuleRef>()
    val rules = mutableMapOf<String, RuleRef>()
    var isLexerGrammar: Boolean = false

    override fun aggregateResult(aggregate: MutableList<Rule>?, nextResult: MutableList<Rule>?): MutableList<Rule> {
        aggregate ?: return nextResult ?: return mutableListOf()
        nextResult ?: return aggregate

        aggregate.addAll(nextResult)
        return aggregate
    }

    override fun visitParserRuleSpec(ctx: ParserRuleSpecContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        val first = ctx.getChild(0)
        val ruleName = if (first is RuleModifiersContext) {
            ctx.getToken(RULE_REF, 1).text
        } else first.text

        var ruleBlock: ParseTree? = null
        var i = 0
        while(ruleBlock !is RuleBlockContext) {
            ruleBlock = ctx.getChild(i++)
        }

        val ruleAlts = visitRuleBlock(ruleBlock)
        val ruleRef = rules[ruleName]?.apply { updateAlts(ruleAlts) }
            ?: RuleRef(alts = ruleAlts).apply { rules[ruleName] = this }

        ruleRef.setText(ctx.text)
        return mutableListOf()
    }

    override fun visitAlternative(ctx: AlternativeContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            RuleAlt(
                elements = visitChildren(ctx)
            ).apply {
                this.setText(ctx.text)
            }
        )
    }

    override fun visitElement(ctx: ElementContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()

        val ebnfSuffix = ctx.getChild(1)?.text
        val token = visitChildren(ctx).first() as RuleWithSuffix

        token.suffix = ebnfSuffix
        return mutableListOf(token)
    }

    override fun visitEbnf(ctx: EbnfContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        val ebnfSuffix = ctx.getChild(1)?.text
        val token = visitChildren(ctx).first() as RuleBlock
        token.suffix = ebnfSuffix

        return mutableListOf(
            RuleBlockLink(
                ruleBlock = token
            ).apply {
                this.setText(ctx.text)
            }
        )
    }

    override fun visitRuleref(ctx: RulerefContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return super.visitRuleref(ctx)
    }

    override fun visitBlock(ctx: BlockContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            RuleBlock(alts = visitChildren(ctx)).apply {
                this.setText(ctx.text)
            }
        )
    }

    override fun visitLexerRuleSpec(ctx: LexerRuleSpecContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        val first = ctx.getChild(0) as TerminalNode
        val tokenName = if (first.symbol.type == FRAGMENT) {
            (ctx.getToken(TOKEN_REF, 1) as TerminalNode).text
        } else first.text

        val lexerRuleAlts = visitChildren(ctx)
        lexerRuleAlts.removeFirst()
        val tokenRef = tokens[tokenName]?.apply { updateAlts(lexerRuleAlts) }
            ?: LexerRuleRef(alts = lexerRuleAlts).apply { tokens[tokenName] = this }
        tokenRef.setText(ctx.text)
        return mutableListOf(tokenRef)
    }

    override fun visitLexerAltList(ctx: LexerAltListContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return visitChildren(ctx)
    }

    override fun visitLexerAlt(ctx: LexerAltContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            RuleAlt(
                elements = visitChildren(ctx)
            ).apply {
                this.setText(ctx.text)
            }
        )
    }

    override fun visitLexerElements(ctx: LexerElementsContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return visitChildren(ctx)
    }

    override fun visitLexerElement(ctx: LexerElementContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()

        val ebnfSuffix = ctx.getChild(1)?.text

        val token = visitChildren(ctx).first() as RuleWithSuffix
        token.suffix = ebnfSuffix
        token.setText(ctx.text)
        return mutableListOf(token)
    }

    override fun visitLexerBlock(ctx: LexerBlockContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            RuleBlock(alts = visitChildren(ctx)).apply {
                this.setText(ctx.text)
            }
        )
    }

    override fun visitLexerAtom(ctx: LexerAtomContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return visitChildren(ctx)
    }

    override fun visitTerminal(ctx: TerminalContext?): MutableList<Rule> {
        return super.visitTerminal(ctx)
    }

    override fun visitTerminal(node: TerminalNode?): MutableList<Rule> {
        node ?: return mutableListOf()
        return when (node.symbol.type) {
            LEXER -> mutableListOf<Rule>().apply { isLexerGrammar = true }
            LEXER_CHAR_SET -> mutableListOf(RuleRegex(node.text))
            STRING_LITERAL -> mutableListOf(RuleString(node.text.trim('\'')))
            TOKEN_REF -> {
                val tokenRef = tokens[node.text] ?: LexerRuleRef().apply { tokens[node.text] = this }
                val tokenRefLink = LexerRuleRefLink(tokenRef)
                tokenRefLink.setText(node.text)
                return mutableListOf(tokenRefLink)
            }
            RULE_REF -> {
                val ruleRef = rules[node.text] ?: RuleRef().apply { rules[node.text] = this }
                ruleRef.hasLink = true
                val ruleRefLink = RuleRefLink(ruleRef)
                ruleRefLink.setText(node.text)
                return mutableListOf(ruleRefLink)
            }
            else -> mutableListOf()
        }
    }

    override fun visitNotSet(ctx: NotSetContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            RuleNot(elements = visitChildren(ctx))
        )
    }

    override fun visitBlockSet(ctx: BlockSetContext?): MutableList<Rule> {
        ctx ?: return mutableListOf()
        return mutableListOf(
            RuleAlt(
                elements = visitChildren(ctx)
            )
        )
    }
}