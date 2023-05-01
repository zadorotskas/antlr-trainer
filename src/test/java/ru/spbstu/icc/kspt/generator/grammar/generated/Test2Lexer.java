// Generated from C:/Users/zador/IdeaProjects/antlr-trainer/src/test/resources/grammar/parser\Test2.g4 by ANTLR 4.10.1
package ru.spbstu.icc.kspt.generator.grammar.generated;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Test2Lexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BEGIN", "CALL", "CONST", "DO", "END", "IF", "ODD", "PROCEDURE", "THEN", 
			"VAR", "WHILE", "PLUS", "MINUS", "TIMES", "SLASH", "LPAREN", "RPAREN", 
			"COMMA", "DOT", "COLON", "SEMICOLON", "ASSIGN", "EQUAL", "NOTEQUAL", 
			"LESSTHAN", "GREATTHAN", "LESSOREQUALTHAN", "GREATOREQUALTHAN", "ID", 
			"NUMBER", "WS"
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


	public Test2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Test2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001f\u00ae\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0002\u001e\u0007\u001e\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001c\u0004\u001c\u009f\b\u001c\u000b\u001c\f\u001c\u00a0\u0001\u001d"+
		"\u0004\u001d\u00a4\b\u001d\u000b\u001d\f\u001d\u00a5\u0001\u001e\u0004"+
		"\u001e\u00a9\b\u001e\u000b\u001e\f\u001e\u00aa\u0001\u001e\u0001\u001e"+
		"\u0000\u0000\u001f\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f"+
		"\u0001\u0000\u0003\u0002\u0000AZaz\u0001\u000009\u0003\u0000\t\n\r\r "+
		" \u00b0\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000"+
		"\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000"+
		"!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001"+
		"\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000"+
		"\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000"+
		"\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003"+
		"\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000"+
		"\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000"+
		"\u0000=\u0001\u0000\u0000\u0000\u0001?\u0001\u0000\u0000\u0000\u0003E"+
		"\u0001\u0000\u0000\u0000\u0005J\u0001\u0000\u0000\u0000\u0007P\u0001\u0000"+
		"\u0000\u0000\tS\u0001\u0000\u0000\u0000\u000bW\u0001\u0000\u0000\u0000"+
		"\rZ\u0001\u0000\u0000\u0000\u000f^\u0001\u0000\u0000\u0000\u0011h\u0001"+
		"\u0000\u0000\u0000\u0013m\u0001\u0000\u0000\u0000\u0015q\u0001\u0000\u0000"+
		"\u0000\u0017w\u0001\u0000\u0000\u0000\u0019y\u0001\u0000\u0000\u0000\u001b"+
		"{\u0001\u0000\u0000\u0000\u001d}\u0001\u0000\u0000\u0000\u001f\u007f\u0001"+
		"\u0000\u0000\u0000!\u0081\u0001\u0000\u0000\u0000#\u0083\u0001\u0000\u0000"+
		"\u0000%\u0085\u0001\u0000\u0000\u0000\'\u0087\u0001\u0000\u0000\u0000"+
		")\u0089\u0001\u0000\u0000\u0000+\u008b\u0001\u0000\u0000\u0000-\u008e"+
		"\u0001\u0000\u0000\u0000/\u0090\u0001\u0000\u0000\u00001\u0093\u0001\u0000"+
		"\u0000\u00003\u0095\u0001\u0000\u0000\u00005\u0097\u0001\u0000\u0000\u0000"+
		"7\u009a\u0001\u0000\u0000\u00009\u009e\u0001\u0000\u0000\u0000;\u00a3"+
		"\u0001\u0000\u0000\u0000=\u00a8\u0001\u0000\u0000\u0000?@\u0005B\u0000"+
		"\u0000@A\u0005E\u0000\u0000AB\u0005G\u0000\u0000BC\u0005I\u0000\u0000"+
		"CD\u0005N\u0000\u0000D\u0002\u0001\u0000\u0000\u0000EF\u0005C\u0000\u0000"+
		"FG\u0005A\u0000\u0000GH\u0005L\u0000\u0000HI\u0005L\u0000\u0000I\u0004"+
		"\u0001\u0000\u0000\u0000JK\u0005C\u0000\u0000KL\u0005O\u0000\u0000LM\u0005"+
		"N\u0000\u0000MN\u0005S\u0000\u0000NO\u0005T\u0000\u0000O\u0006\u0001\u0000"+
		"\u0000\u0000PQ\u0005D\u0000\u0000QR\u0005O\u0000\u0000R\b\u0001\u0000"+
		"\u0000\u0000ST\u0005E\u0000\u0000TU\u0005N\u0000\u0000UV\u0005D\u0000"+
		"\u0000V\n\u0001\u0000\u0000\u0000WX\u0005I\u0000\u0000XY\u0005F\u0000"+
		"\u0000Y\f\u0001\u0000\u0000\u0000Z[\u0005O\u0000\u0000[\\\u0005D\u0000"+
		"\u0000\\]\u0005D\u0000\u0000]\u000e\u0001\u0000\u0000\u0000^_\u0005P\u0000"+
		"\u0000_`\u0005R\u0000\u0000`a\u0005O\u0000\u0000ab\u0005C\u0000\u0000"+
		"bc\u0005E\u0000\u0000cd\u0005D\u0000\u0000de\u0005U\u0000\u0000ef\u0005"+
		"R\u0000\u0000fg\u0005E\u0000\u0000g\u0010\u0001\u0000\u0000\u0000hi\u0005"+
		"T\u0000\u0000ij\u0005H\u0000\u0000jk\u0005E\u0000\u0000kl\u0005N\u0000"+
		"\u0000l\u0012\u0001\u0000\u0000\u0000mn\u0005V\u0000\u0000no\u0005A\u0000"+
		"\u0000op\u0005R\u0000\u0000p\u0014\u0001\u0000\u0000\u0000qr\u0005W\u0000"+
		"\u0000rs\u0005H\u0000\u0000st\u0005I\u0000\u0000tu\u0005L\u0000\u0000"+
		"uv\u0005E\u0000\u0000v\u0016\u0001\u0000\u0000\u0000wx\u0005+\u0000\u0000"+
		"x\u0018\u0001\u0000\u0000\u0000yz\u0005-\u0000\u0000z\u001a\u0001\u0000"+
		"\u0000\u0000{|\u0005*\u0000\u0000|\u001c\u0001\u0000\u0000\u0000}~\u0005"+
		"/\u0000\u0000~\u001e\u0001\u0000\u0000\u0000\u007f\u0080\u0005(\u0000"+
		"\u0000\u0080 \u0001\u0000\u0000\u0000\u0081\u0082\u0005)\u0000\u0000\u0082"+
		"\"\u0001\u0000\u0000\u0000\u0083\u0084\u0005,\u0000\u0000\u0084$\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0005.\u0000\u0000\u0086&\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0005:\u0000\u0000\u0088(\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0005;\u0000\u0000\u008a*\u0001\u0000\u0000\u0000\u008b\u008c\u0005"+
		":\u0000\u0000\u008c\u008d\u0005=\u0000\u0000\u008d,\u0001\u0000\u0000"+
		"\u0000\u008e\u008f\u0005=\u0000\u0000\u008f.\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0005!\u0000\u0000\u0091\u0092\u0005=\u0000\u0000\u00920\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0005<\u0000\u0000\u00942\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0005>\u0000\u0000\u00964\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0005<\u0000\u0000\u0098\u0099\u0005=\u0000\u0000\u00996\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0005>\u0000\u0000\u009b\u009c\u0005=\u0000"+
		"\u0000\u009c8\u0001\u0000\u0000\u0000\u009d\u009f\u0007\u0000\u0000\u0000"+
		"\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a1:\u0001\u0000\u0000\u0000\u00a2\u00a4\u0007\u0001\u0000\u0000\u00a3"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"<\u0001\u0000\u0000\u0000\u00a7\u00a9\u0007\u0002\u0000\u0000\u00a8\u00a7"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00a8"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0006\u001e\u0000\u0000\u00ad>\u0001"+
		"\u0000\u0000\u0000\u0004\u0000\u00a0\u00a5\u00aa\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}