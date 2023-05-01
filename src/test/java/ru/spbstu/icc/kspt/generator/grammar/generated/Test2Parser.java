// Generated from C:/Users/zador/IdeaProjects/antlr-trainer/src/test/resources/grammar/parser\Test2.g4 by ANTLR 4.10.1
package ru.spbstu.icc.kspt.generator.grammar.generated;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Test2Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, CALL=2, CONST=3, DO=4, END=5, IF=6, ODD=7, PROCEDURE=8, THEN=9, 
		VAR=10, WHILE=11, PLUS=12, MINUS=13, TIMES=14, SLASH=15, LPAREN=16, RPAREN=17, 
		COMMA=18, DOT=19, COLON=20, SEMICOLON=21, ASSIGN=22, EQUAL=23, NOTEQUAL=24, 
		LESSTHAN=25, GREATTHAN=26, LESSOREQUALTHAN=27, GREATOREQUALTHAN=28, ID=29, 
		NUMBER=30, WS=31;
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_constDeclaration = 2, RULE_constAssign = 3, 
		RULE_varDeclaration = 4, RULE_varAssign = 5, RULE_procedure = 6, RULE_operator = 7, 
		RULE_operatorAssign = 8, RULE_operatorCall = 9, RULE_operatorBegin = 10, 
		RULE_operatorIf = 11, RULE_operatorWhile = 12, RULE_condition = 13, RULE_conditionOdd = 14, 
		RULE_conditionComparison = 15, RULE_comparison = 16, RULE_expression = 17, 
		RULE_exprSign = 18, RULE_summand = 19, RULE_multSign = 20, RULE_multiplier = 21, 
		RULE_multiplierId = 22, RULE_multiplierNumber = 23, RULE_multiplierExpression = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "constDeclaration", "constAssign", "varDeclaration", 
			"varAssign", "procedure", "operator", "operatorAssign", "operatorCall", 
			"operatorBegin", "operatorIf", "operatorWhile", "condition", "conditionOdd", 
			"conditionComparison", "comparison", "expression", "exprSign", "summand", 
			"multSign", "multiplier", "multiplierId", "multiplierNumber", "multiplierExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'BEGIN'", "'CALL'", "'CONST'", "'DO'", "'END'", "'IF'", "'ODD'", 
			"'PROCEDURE'", "'THEN'", "'VAR'", "'WHILE'", "'+'", "'-'", "'*'", "'/'", 
			"'('", "')'", "','", "'.'", "':'", "';'", "':='", "'='", "'!='", "'<'", 
			"'>'", "'<='", "'>='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "CALL", "CONST", "DO", "END", "IF", "ODD", "PROCEDURE", 
			"THEN", "VAR", "WHILE", "PLUS", "MINUS", "TIMES", "SLASH", "LPAREN", 
			"RPAREN", "COMMA", "DOT", "COLON", "SEMICOLON", "ASSIGN", "EQUAL", "NOTEQUAL", 
			"LESSTHAN", "GREATTHAN", "LESSOREQUALTHAN", "GREATOREQUALTHAN", "ID", 
			"NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Test2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Test2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode DOT() { return getToken(Test2Parser.DOT, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			block();
			setState(51);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ConstDeclarationContext constDeclaration() {
			return getRuleContext(ConstDeclarationContext.class,0);
		}
		public VarDeclarationContext varDeclaration() {
			return getRuleContext(VarDeclarationContext.class,0);
		}
		public List<ProcedureContext> procedure() {
			return getRuleContexts(ProcedureContext.class);
		}
		public ProcedureContext procedure(int i) {
			return getRuleContext(ProcedureContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONST) {
				{
				setState(53);
				constDeclaration();
				}
			}

			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VAR) {
				{
				setState(56);
				varDeclaration();
				}
			}

			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PROCEDURE) {
				{
				{
				setState(59);
				procedure();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			operator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclarationContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(Test2Parser.CONST, 0); }
		public List<ConstAssignContext> constAssign() {
			return getRuleContexts(ConstAssignContext.class);
		}
		public ConstAssignContext constAssign(int i) {
			return getRuleContext(ConstAssignContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(Test2Parser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(Test2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Test2Parser.COMMA, i);
		}
		public ConstDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterConstDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitConstDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitConstDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclarationContext constDeclaration() throws RecognitionException {
		ConstDeclarationContext _localctx = new ConstDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(CONST);
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(68);
					constAssign();
					setState(69);
					match(COMMA);
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(76);
			constAssign();
			setState(77);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstAssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public TerminalNode EQUAL() { return getToken(Test2Parser.EQUAL, 0); }
		public TerminalNode NUMBER() { return getToken(Test2Parser.NUMBER, 0); }
		public ConstAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterConstAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitConstAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitConstAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstAssignContext constAssign() throws RecognitionException {
		ConstAssignContext _localctx = new ConstAssignContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(ID);
			setState(80);
			match(EQUAL);
			setState(81);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(Test2Parser.VAR, 0); }
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(Test2Parser.SEMICOLON, 0); }
		public List<VarAssignContext> varAssign() {
			return getRuleContexts(VarAssignContext.class);
		}
		public VarAssignContext varAssign(int i) {
			return getRuleContext(VarAssignContext.class,i);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(VAR);
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(84);
					varAssign();
					}
					} 
				}
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(90);
			match(ID);
			setState(91);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarAssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public TerminalNode COMMA() { return getToken(Test2Parser.COMMA, 0); }
		public VarAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterVarAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitVarAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitVarAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarAssignContext varAssign() throws RecognitionException {
		VarAssignContext _localctx = new VarAssignContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(ID);
			setState(94);
			match(COMMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(Test2Parser.PROCEDURE, 0); }
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(Test2Parser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(Test2Parser.SEMICOLON, i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProcedureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterProcedure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitProcedure(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitProcedure(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureContext procedure() throws RecognitionException {
		ProcedureContext _localctx = new ProcedureContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_procedure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(PROCEDURE);
			setState(97);
			match(ID);
			setState(98);
			match(SEMICOLON);
			setState(99);
			block();
			setState(100);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public OperatorAssignContext operatorAssign() {
			return getRuleContext(OperatorAssignContext.class,0);
		}
		public OperatorCallContext operatorCall() {
			return getRuleContext(OperatorCallContext.class,0);
		}
		public OperatorBeginContext operatorBegin() {
			return getRuleContext(OperatorBeginContext.class,0);
		}
		public OperatorIfContext operatorIf() {
			return getRuleContext(OperatorIfContext.class,0);
		}
		public OperatorWhileContext operatorWhile() {
			return getRuleContext(OperatorWhileContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_operator);
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				operatorAssign();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				operatorCall();
				}
				break;
			case BEGIN:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				operatorBegin();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				operatorIf();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				operatorWhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorAssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(Test2Parser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OperatorAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterOperatorAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitOperatorAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitOperatorAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorAssignContext operatorAssign() throws RecognitionException {
		OperatorAssignContext _localctx = new OperatorAssignContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_operatorAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(ID);
			setState(110);
			match(ASSIGN);
			setState(111);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorCallContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(Test2Parser.CALL, 0); }
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public OperatorCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterOperatorCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitOperatorCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitOperatorCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorCallContext operatorCall() throws RecognitionException {
		OperatorCallContext _localctx = new OperatorCallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_operatorCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(CALL);
			setState(114);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorBeginContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(Test2Parser.BEGIN, 0); }
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public TerminalNode END() { return getToken(Test2Parser.END, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(Test2Parser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(Test2Parser.SEMICOLON, i);
		}
		public OperatorBeginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorBegin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterOperatorBegin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitOperatorBegin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitOperatorBegin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorBeginContext operatorBegin() throws RecognitionException {
		OperatorBeginContext _localctx = new OperatorBeginContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_operatorBegin);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(BEGIN);
			setState(122);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(117);
					operator();
					setState(118);
					match(SEMICOLON);
					}
					} 
				}
				setState(124);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(125);
			operator();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(126);
				match(SEMICOLON);
				}
			}

			setState(129);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorIfContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(Test2Parser.IF, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(Test2Parser.THEN, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public OperatorIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterOperatorIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitOperatorIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitOperatorIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorIfContext operatorIf() throws RecognitionException {
		OperatorIfContext _localctx = new OperatorIfContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_operatorIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(IF);
			setState(132);
			condition();
			setState(133);
			match(THEN);
			setState(134);
			operator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorWhileContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(Test2Parser.WHILE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode DO() { return getToken(Test2Parser.DO, 0); }
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public OperatorWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterOperatorWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitOperatorWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitOperatorWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorWhileContext operatorWhile() throws RecognitionException {
		OperatorWhileContext _localctx = new OperatorWhileContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operatorWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(WHILE);
			setState(137);
			condition();
			setState(138);
			match(DO);
			setState(139);
			operator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ConditionOddContext conditionOdd() {
			return getRuleContext(ConditionOddContext.class,0);
		}
		public ConditionComparisonContext conditionComparison() {
			return getRuleContext(ConditionComparisonContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_condition);
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ODD:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				conditionOdd();
				}
				break;
			case PLUS:
			case MINUS:
			case LPAREN:
			case ID:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				conditionComparison();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionOddContext extends ParserRuleContext {
		public TerminalNode ODD() { return getToken(Test2Parser.ODD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionOddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionOdd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterConditionOdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitConditionOdd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitConditionOdd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionOddContext conditionOdd() throws RecognitionException {
		ConditionOddContext _localctx = new ConditionOddContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_conditionOdd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(ODD);
			setState(146);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionComparisonContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public ConditionComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionComparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterConditionComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitConditionComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitConditionComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionComparisonContext conditionComparison() throws RecognitionException {
		ConditionComparisonContext _localctx = new ConditionComparisonContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_conditionComparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			expression();
			setState(149);
			comparison();
			setState(150);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(Test2Parser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(Test2Parser.NOTEQUAL, 0); }
		public TerminalNode LESSTHAN() { return getToken(Test2Parser.LESSTHAN, 0); }
		public TerminalNode GREATTHAN() { return getToken(Test2Parser.GREATTHAN, 0); }
		public TerminalNode GREATOREQUALTHAN() { return getToken(Test2Parser.GREATOREQUALTHAN, 0); }
		public TerminalNode LESSOREQUALTHAN() { return getToken(Test2Parser.LESSOREQUALTHAN, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << NOTEQUAL) | (1L << LESSTHAN) | (1L << GREATTHAN) | (1L << LESSOREQUALTHAN) | (1L << GREATOREQUALTHAN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<SummandContext> summand() {
			return getRuleContexts(SummandContext.class);
		}
		public SummandContext summand(int i) {
			return getRuleContext(SummandContext.class,i);
		}
		public List<ExprSignContext> exprSign() {
			return getRuleContexts(ExprSignContext.class);
		}
		public ExprSignContext exprSign(int i) {
			return getRuleContext(ExprSignContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(154);
				exprSign();
				}
			}

			setState(157);
			summand();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(158);
				exprSign();
				setState(159);
				summand();
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(Test2Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(Test2Parser.MINUS, 0); }
		public ExprSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterExprSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitExprSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitExprSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprSignContext exprSign() throws RecognitionException {
		ExprSignContext _localctx = new ExprSignContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprSign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SummandContext extends ParserRuleContext {
		public List<MultiplierContext> multiplier() {
			return getRuleContexts(MultiplierContext.class);
		}
		public MultiplierContext multiplier(int i) {
			return getRuleContext(MultiplierContext.class,i);
		}
		public List<MultSignContext> multSign() {
			return getRuleContexts(MultSignContext.class);
		}
		public MultSignContext multSign(int i) {
			return getRuleContext(MultSignContext.class,i);
		}
		public SummandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_summand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterSummand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitSummand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitSummand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SummandContext summand() throws RecognitionException {
		SummandContext _localctx = new SummandContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_summand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			multiplier();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIMES || _la==SLASH) {
				{
				{
				setState(169);
				multSign();
				setState(170);
				multiplier();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultSignContext extends ParserRuleContext {
		public TerminalNode TIMES() { return getToken(Test2Parser.TIMES, 0); }
		public TerminalNode SLASH() { return getToken(Test2Parser.SLASH, 0); }
		public MultSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multSign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterMultSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitMultSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitMultSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultSignContext multSign() throws RecognitionException {
		MultSignContext _localctx = new MultSignContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_multSign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_la = _input.LA(1);
			if ( !(_la==TIMES || _la==SLASH) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplierContext extends ParserRuleContext {
		public MultiplierIdContext multiplierId() {
			return getRuleContext(MultiplierIdContext.class,0);
		}
		public MultiplierNumberContext multiplierNumber() {
			return getRuleContext(MultiplierNumberContext.class,0);
		}
		public MultiplierExpressionContext multiplierExpression() {
			return getRuleContext(MultiplierExpressionContext.class,0);
		}
		public MultiplierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterMultiplier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitMultiplier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitMultiplier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplierContext multiplier() throws RecognitionException {
		MultiplierContext _localctx = new MultiplierContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_multiplier);
		try {
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				multiplierId();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				multiplierNumber();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(181);
				multiplierExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplierIdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(Test2Parser.ID, 0); }
		public MultiplierIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplierId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterMultiplierId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitMultiplierId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitMultiplierId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplierIdContext multiplierId() throws RecognitionException {
		MultiplierIdContext _localctx = new MultiplierIdContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_multiplierId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplierNumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(Test2Parser.NUMBER, 0); }
		public MultiplierNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplierNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterMultiplierNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitMultiplierNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitMultiplierNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplierNumberContext multiplierNumber() throws RecognitionException {
		MultiplierNumberContext _localctx = new MultiplierNumberContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_multiplierNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplierExpressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(Test2Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(Test2Parser.RPAREN, 0); }
		public MultiplierExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplierExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).enterMultiplierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Test2Listener ) ((Test2Listener)listener).exitMultiplierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Test2Visitor ) return ((Test2Visitor<? extends T>)visitor).visitMultiplierExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplierExpressionContext multiplierExpression() throws RecognitionException {
		MultiplierExpressionContext _localctx = new MultiplierExpressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_multiplierExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(LPAREN);
			setState(189);
			expression();
			setState(190);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001f\u00c1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0003\u00017\b"+
		"\u0001\u0001\u0001\u0003\u0001:\b\u0001\u0001\u0001\u0005\u0001=\b\u0001"+
		"\n\u0001\f\u0001@\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002H\b\u0002\n\u0002\f\u0002K\t\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0005\u0004V\b\u0004\n\u0004\f\u0004"+
		"Y\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007l\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0005\ny\b\n\n\n\f\n|\t\n\u0001"+
		"\n\u0001\n\u0003\n\u0080\b\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0003\r\u0090\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0003\u0011\u009c\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u00a2\b\u0011\n\u0011\f\u0011\u00a5\t\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u00ad\b\u0013\n\u0013\f\u0013\u00b0\t\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00b7\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0000\u0000\u0019\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000"+
		"\u0003\u0001\u0000\u0017\u001c\u0001\u0000\f\r\u0001\u0000\u000e\u000f"+
		"\u00b8\u00002\u0001\u0000\u0000\u0000\u00026\u0001\u0000\u0000\u0000\u0004"+
		"C\u0001\u0000\u0000\u0000\u0006O\u0001\u0000\u0000\u0000\bS\u0001\u0000"+
		"\u0000\u0000\n]\u0001\u0000\u0000\u0000\f`\u0001\u0000\u0000\u0000\u000e"+
		"k\u0001\u0000\u0000\u0000\u0010m\u0001\u0000\u0000\u0000\u0012q\u0001"+
		"\u0000\u0000\u0000\u0014t\u0001\u0000\u0000\u0000\u0016\u0083\u0001\u0000"+
		"\u0000\u0000\u0018\u0088\u0001\u0000\u0000\u0000\u001a\u008f\u0001\u0000"+
		"\u0000\u0000\u001c\u0091\u0001\u0000\u0000\u0000\u001e\u0094\u0001\u0000"+
		"\u0000\u0000 \u0098\u0001\u0000\u0000\u0000\"\u009b\u0001\u0000\u0000"+
		"\u0000$\u00a6\u0001\u0000\u0000\u0000&\u00a8\u0001\u0000\u0000\u0000("+
		"\u00b1\u0001\u0000\u0000\u0000*\u00b6\u0001\u0000\u0000\u0000,\u00b8\u0001"+
		"\u0000\u0000\u0000.\u00ba\u0001\u0000\u0000\u00000\u00bc\u0001\u0000\u0000"+
		"\u000023\u0003\u0002\u0001\u000034\u0005\u0013\u0000\u00004\u0001\u0001"+
		"\u0000\u0000\u000057\u0003\u0004\u0002\u000065\u0001\u0000\u0000\u0000"+
		"67\u0001\u0000\u0000\u000079\u0001\u0000\u0000\u00008:\u0003\b\u0004\u0000"+
		"98\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:>\u0001\u0000\u0000"+
		"\u0000;=\u0003\f\u0006\u0000<;\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000"+
		"\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000AB\u0003\u000e\u0007\u0000B\u0003"+
		"\u0001\u0000\u0000\u0000CI\u0005\u0003\u0000\u0000DE\u0003\u0006\u0003"+
		"\u0000EF\u0005\u0012\u0000\u0000FH\u0001\u0000\u0000\u0000GD\u0001\u0000"+
		"\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001"+
		"\u0000\u0000\u0000JL\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000"+
		"LM\u0003\u0006\u0003\u0000MN\u0005\u0015\u0000\u0000N\u0005\u0001\u0000"+
		"\u0000\u0000OP\u0005\u001d\u0000\u0000PQ\u0005\u0017\u0000\u0000QR\u0005"+
		"\u001e\u0000\u0000R\u0007\u0001\u0000\u0000\u0000SW\u0005\n\u0000\u0000"+
		"TV\u0003\n\u0005\u0000UT\u0001\u0000\u0000\u0000VY\u0001\u0000\u0000\u0000"+
		"WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XZ\u0001\u0000\u0000"+
		"\u0000YW\u0001\u0000\u0000\u0000Z[\u0005\u001d\u0000\u0000[\\\u0005\u0015"+
		"\u0000\u0000\\\t\u0001\u0000\u0000\u0000]^\u0005\u001d\u0000\u0000^_\u0005"+
		"\u0012\u0000\u0000_\u000b\u0001\u0000\u0000\u0000`a\u0005\b\u0000\u0000"+
		"ab\u0005\u001d\u0000\u0000bc\u0005\u0015\u0000\u0000cd\u0003\u0002\u0001"+
		"\u0000de\u0005\u0015\u0000\u0000e\r\u0001\u0000\u0000\u0000fl\u0003\u0010"+
		"\b\u0000gl\u0003\u0012\t\u0000hl\u0003\u0014\n\u0000il\u0003\u0016\u000b"+
		"\u0000jl\u0003\u0018\f\u0000kf\u0001\u0000\u0000\u0000kg\u0001\u0000\u0000"+
		"\u0000kh\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kj\u0001\u0000"+
		"\u0000\u0000l\u000f\u0001\u0000\u0000\u0000mn\u0005\u001d\u0000\u0000"+
		"no\u0005\u0016\u0000\u0000op\u0003\"\u0011\u0000p\u0011\u0001\u0000\u0000"+
		"\u0000qr\u0005\u0002\u0000\u0000rs\u0005\u001d\u0000\u0000s\u0013\u0001"+
		"\u0000\u0000\u0000tz\u0005\u0001\u0000\u0000uv\u0003\u000e\u0007\u0000"+
		"vw\u0005\u0015\u0000\u0000wy\u0001\u0000\u0000\u0000xu\u0001\u0000\u0000"+
		"\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{}\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}\u007f"+
		"\u0003\u000e\u0007\u0000~\u0080\u0005\u0015\u0000\u0000\u007f~\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000"+
		"\u0000\u0000\u0081\u0082\u0005\u0005\u0000\u0000\u0082\u0015\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0005\u0006\u0000\u0000\u0084\u0085\u0003\u001a"+
		"\r\u0000\u0085\u0086\u0005\t\u0000\u0000\u0086\u0087\u0003\u000e\u0007"+
		"\u0000\u0087\u0017\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u000b\u0000"+
		"\u0000\u0089\u008a\u0003\u001a\r\u0000\u008a\u008b\u0005\u0004\u0000\u0000"+
		"\u008b\u008c\u0003\u000e\u0007\u0000\u008c\u0019\u0001\u0000\u0000\u0000"+
		"\u008d\u0090\u0003\u001c\u000e\u0000\u008e\u0090\u0003\u001e\u000f\u0000"+
		"\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u008e\u0001\u0000\u0000\u0000"+
		"\u0090\u001b\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u0007\u0000\u0000"+
		"\u0092\u0093\u0003\"\u0011\u0000\u0093\u001d\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0003\"\u0011\u0000\u0095\u0096\u0003 \u0010\u0000\u0096\u0097"+
		"\u0003\"\u0011\u0000\u0097\u001f\u0001\u0000\u0000\u0000\u0098\u0099\u0007"+
		"\u0000\u0000\u0000\u0099!\u0001\u0000\u0000\u0000\u009a\u009c\u0003$\u0012"+
		"\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u00a3\u0003&\u0013\u0000"+
		"\u009e\u009f\u0003$\u0012\u0000\u009f\u00a0\u0003&\u0013\u0000\u00a0\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a1\u009e\u0001\u0000\u0000\u0000\u00a2\u00a5"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a4#\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0007\u0001\u0000\u0000\u00a7%\u0001\u0000"+
		"\u0000\u0000\u00a8\u00ae\u0003*\u0015\u0000\u00a9\u00aa\u0003(\u0014\u0000"+
		"\u00aa\u00ab\u0003*\u0015\u0000\u00ab\u00ad\u0001\u0000\u0000\u0000\u00ac"+
		"\u00a9\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000\u0000\u0000\u00ae"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af"+
		"\'\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\u0007\u0002\u0000\u0000\u00b2)\u0001\u0000\u0000\u0000\u00b3\u00b7\u0003"+
		",\u0016\u0000\u00b4\u00b7\u0003.\u0017\u0000\u00b5\u00b7\u00030\u0018"+
		"\u0000\u00b6\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b7+\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0005\u001d\u0000\u0000\u00b9-\u0001\u0000\u0000\u0000\u00ba"+
		"\u00bb\u0005\u001e\u0000\u0000\u00bb/\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0005\u0010\u0000\u0000\u00bd\u00be\u0003\"\u0011\u0000\u00be\u00bf\u0005"+
		"\u0011\u0000\u0000\u00bf1\u0001\u0000\u0000\u0000\r69>IWkz\u007f\u008f"+
		"\u009b\u00a3\u00ae\u00b6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}