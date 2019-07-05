package antlr.bigraph;// Generated from C:/Users/Utente/Documents/GitHub/untitled/src\bigraph.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class bigraphLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SEMI=2, COLON=3, DOT=4, ASSIGNMENT=5, WHITESP=6, ESCAPE=7, NEWLINE=8, 
		LSQ=9, RSQ=10, LPAR=11, RPAR=12, LOR=13, PAR=14, DOLLAR=15, UNLINKED=16, 
		ARROW=17, NIL=18, COMMENT=19, LINE_COMMENT=20, CONTROLS=21, ACTIVE=22, 
		PASSIVE=23, NAMES=24, INNER=25, OUTER=26, RULE=27, VARIABLE=28, MODEL=29, 
		MARKER=30, PROPERTIES=31, AND=32, NOT=33, IF=34, THEN=35, ELSE=36, LEQ=37, 
		GEQ=38, LT=39, GT=40, EQ=41, NEQ=42, DIGIT=43, PROBABILITY=44, IDENTIFIER=45;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMA", "SEMI", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", "NEWLINE", 
			"LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", "UNLINKED", "ARROW", 
			"NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", "NAMES", 
			"INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "MARKER", "PROPERTIES", 
			"AND", "NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", 
			"DIGIT", "PROBABILITY", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "';'", "':'", "'.'", "'='", "' '", "'\t'", null, "'['", 
			"']'", "'('", "')'", "'||'", "'|'", "'$'", "'-'", "'->'", "'nil'", null, 
			null, "'controls'", "'active'", "'passive'", "'names'", "'inner'", "'outer'", 
			"'rule'", "'@'", "'model'", "'marker'", "'properties'", "'&&'", "'!'", 
			"'if'", "'then'", "'else'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "SEMI", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", 
			"NEWLINE", "LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", "UNLINKED", 
			"ARROW", "NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", 
			"NAMES", "INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "MARKER", "PROPERTIES", 
			"AND", "NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", 
			"DIGIT", "PROBABILITY", "IDENTIFIER"
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


	public bigraphLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "bigraph.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2/\u0129\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\5\ts\n\t\3\t\3\t\6\tw\n\t\r\t\16\tx\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7"+
		"\24\u0099\n\24\f\24\16\24\u009c\13\24\3\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\7\25\u00a5\n\25\f\25\16\25\u00a8\13\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#"+
		"\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3*"+
		"\3*\3*\3+\3+\3+\3,\6,\u0118\n,\r,\16,\u0119\3-\3-\3-\6-\u011f\n-\r-\16"+
		"-\u0120\3.\3.\7.\u0125\n.\f.\16.\u0128\13.\3\u009a\2/\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/\3\2\5\4\2\f\f\17\17\4\2C\\c|\6\2\62;C\\aac|\2\u0130"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3]\3\2\2\2\5_\3\2\2\2\7c\3"+
		"\2\2\2\te\3\2\2\2\13g\3\2\2\2\ri\3\2\2\2\17m\3\2\2\2\21v\3\2\2\2\23|\3"+
		"\2\2\2\25~\3\2\2\2\27\u0080\3\2\2\2\31\u0082\3\2\2\2\33\u0084\3\2\2\2"+
		"\35\u0087\3\2\2\2\37\u0089\3\2\2\2!\u008b\3\2\2\2#\u008d\3\2\2\2%\u0090"+
		"\3\2\2\2\'\u0094\3\2\2\2)\u00a2\3\2\2\2+\u00ab\3\2\2\2-\u00b4\3\2\2\2"+
		"/\u00bb\3\2\2\2\61\u00c3\3\2\2\2\63\u00c9\3\2\2\2\65\u00cf\3\2\2\2\67"+
		"\u00d5\3\2\2\29\u00da\3\2\2\2;\u00dc\3\2\2\2=\u00e2\3\2\2\2?\u00e9\3\2"+
		"\2\2A\u00f4\3\2\2\2C\u00f7\3\2\2\2E\u00f9\3\2\2\2G\u00fc\3\2\2\2I\u0101"+
		"\3\2\2\2K\u0106\3\2\2\2M\u0109\3\2\2\2O\u010c\3\2\2\2Q\u010e\3\2\2\2S"+
		"\u0110\3\2\2\2U\u0113\3\2\2\2W\u0117\3\2\2\2Y\u011b\3\2\2\2[\u0122\3\2"+
		"\2\2]^\7.\2\2^\4\3\2\2\2_`\7=\2\2`a\3\2\2\2ab\b\3\2\2b\6\3\2\2\2cd\7<"+
		"\2\2d\b\3\2\2\2ef\7\60\2\2f\n\3\2\2\2gh\7?\2\2h\f\3\2\2\2ij\7\"\2\2jk"+
		"\3\2\2\2kl\b\7\2\2l\16\3\2\2\2mn\7\13\2\2no\3\2\2\2op\b\b\2\2p\20\3\2"+
		"\2\2qs\7\17\2\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2tw\7\f\2\2uw\7\17\2\2vr\3"+
		"\2\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{\b\t\2\2{\22"+
		"\3\2\2\2|}\7]\2\2}\24\3\2\2\2~\177\7_\2\2\177\26\3\2\2\2\u0080\u0081\7"+
		"*\2\2\u0081\30\3\2\2\2\u0082\u0083\7+\2\2\u0083\32\3\2\2\2\u0084\u0085"+
		"\7~\2\2\u0085\u0086\7~\2\2\u0086\34\3\2\2\2\u0087\u0088\7~\2\2\u0088\36"+
		"\3\2\2\2\u0089\u008a\7&\2\2\u008a \3\2\2\2\u008b\u008c\7/\2\2\u008c\""+
		"\3\2\2\2\u008d\u008e\7/\2\2\u008e\u008f\7@\2\2\u008f$\3\2\2\2\u0090\u0091"+
		"\7p\2\2\u0091\u0092\7k\2\2\u0092\u0093\7n\2\2\u0093&\3\2\2\2\u0094\u0095"+
		"\7\61\2\2\u0095\u0096\7,\2\2\u0096\u009a\3\2\2\2\u0097\u0099\13\2\2\2"+
		"\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u009b\3\2\2\2\u009a\u0098"+
		"\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7,\2\2\u009e"+
		"\u009f\7\61\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\b\24\2\2\u00a1(\3\2\2"+
		"\2\u00a2\u00a6\7%\2\2\u00a3\u00a5\n\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8"+
		"\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8"+
		"\u00a6\3\2\2\2\u00a9\u00aa\b\25\2\2\u00aa*\3\2\2\2\u00ab\u00ac\7e\2\2"+
		"\u00ac\u00ad\7q\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7v\2\2\u00af\u00b0"+
		"\7t\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7u\2\2\u00b3"+
		",\3\2\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7e\2\2\u00b6\u00b7\7v\2\2\u00b7"+
		"\u00b8\7k\2\2\u00b8\u00b9\7x\2\2\u00b9\u00ba\7g\2\2\u00ba.\3\2\2\2\u00bb"+
		"\u00bc\7r\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7u\2\2\u00be\u00bf\7u\2\2"+
		"\u00bf\u00c0\7k\2\2\u00c0\u00c1\7x\2\2\u00c1\u00c2\7g\2\2\u00c2\60\3\2"+
		"\2\2\u00c3\u00c4\7p\2\2\u00c4\u00c5\7c\2\2\u00c5\u00c6\7o\2\2\u00c6\u00c7"+
		"\7g\2\2\u00c7\u00c8\7u\2\2\u00c8\62\3\2\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb"+
		"\7p\2\2\u00cb\u00cc\7p\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7t\2\2\u00ce"+
		"\64\3\2\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2\7v\2\2\u00d2"+
		"\u00d3\7g\2\2\u00d3\u00d4\7t\2\2\u00d4\66\3\2\2\2\u00d5\u00d6\7t\2\2\u00d6"+
		"\u00d7\7w\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9\7g\2\2\u00d98\3\2\2\2\u00da"+
		"\u00db\7B\2\2\u00db:\3\2\2\2\u00dc\u00dd\7o\2\2\u00dd\u00de\7q\2\2\u00de"+
		"\u00df\7f\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7n\2\2\u00e1<\3\2\2\2\u00e2"+
		"\u00e3\7o\2\2\u00e3\u00e4\7c\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6\7m\2\2"+
		"\u00e6\u00e7\7g\2\2\u00e7\u00e8\7t\2\2\u00e8>\3\2\2\2\u00e9\u00ea\7r\2"+
		"\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed\7r\2\2\u00ed\u00ee"+
		"\7g\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7k\2\2\u00f1"+
		"\u00f2\7g\2\2\u00f2\u00f3\7u\2\2\u00f3@\3\2\2\2\u00f4\u00f5\7(\2\2\u00f5"+
		"\u00f6\7(\2\2\u00f6B\3\2\2\2\u00f7\u00f8\7#\2\2\u00f8D\3\2\2\2\u00f9\u00fa"+
		"\7k\2\2\u00fa\u00fb\7h\2\2\u00fbF\3\2\2\2\u00fc\u00fd\7v\2\2\u00fd\u00fe"+
		"\7j\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7p\2\2\u0100H\3\2\2\2\u0101\u0102"+
		"\7g\2\2\u0102\u0103\7n\2\2\u0103\u0104\7u\2\2\u0104\u0105\7g\2\2\u0105"+
		"J\3\2\2\2\u0106\u0107\7>\2\2\u0107\u0108\7?\2\2\u0108L\3\2\2\2\u0109\u010a"+
		"\7@\2\2\u010a\u010b\7?\2\2\u010bN\3\2\2\2\u010c\u010d\7>\2\2\u010dP\3"+
		"\2\2\2\u010e\u010f\7@\2\2\u010fR\3\2\2\2\u0110\u0111\7?\2\2\u0111\u0112"+
		"\7?\2\2\u0112T\3\2\2\2\u0113\u0114\7#\2\2\u0114\u0115\7?\2\2\u0115V\3"+
		"\2\2\2\u0116\u0118\4\62;\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119"+
		"\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011aX\3\2\2\2\u011b\u011c\7\62\2\2"+
		"\u011c\u011e\5\t\5\2\u011d\u011f\5W,\2\u011e\u011d\3\2\2\2\u011f\u0120"+
		"\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121Z\3\2\2\2\u0122"+
		"\u0126\t\3\2\2\u0123\u0125\t\4\2\2\u0124\u0123\3\2\2\2\u0125\u0128\3\2"+
		"\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\\\3\2\2\2\u0128\u0126"+
		"\3\2\2\2\13\2rvx\u009a\u00a6\u0119\u0120\u0126\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}