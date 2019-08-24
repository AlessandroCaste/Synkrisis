// Generated from /home/ale/Synkrisis/src/main/java/antlr/g4models/bigraph.g4 by ANTLR 4.7.2
package antlr.g4models;

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
		COMMA=1, COLON=2, DOT=3, ASSIGNMENT=4, WHITESP=5, ESCAPE=6, NEWLINE=7, 
		LSQ=8, RSQ=9, LPAR=10, RPAR=11, LOR=12, PAR=13, SEMI=14, DOLLAR=15, UNLINKED=16, 
		ARROW=17, NIL=18, COMMENT=19, LINE_COMMENT=20, CONTROLS=21, ACTIVE=22, 
		PASSIVE=23, NAMES=24, INNER=25, OUTER=26, RULE=27, VARIABLE=28, MODEL=29, 
		MARKER=30, PROPERTIES=31, AND=32, NOT=33, IF=34, THEN=35, ELSE=36, LEQ=37, 
		GEQ=38, LT=39, GT=40, EQ=41, NEQ=42, SLASH=43, QUOTE=44, QUESTION=45, 
		PRODUCT=46, ADDITION=47, CONJUNCTION=48, SPOT=49, ACCEPTANCE=50, ACCNAME=51, 
		FIN=52, INF=53, PROP_LBRACK=54, PROP_RBRACK=55, DIGIT=56, PROBABILITY=57, 
		IDENTIFIER=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMA", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", "NEWLINE", 
			"LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "SEMI", "DOLLAR", "UNLINKED", 
			"ARROW", "NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", 
			"NAMES", "INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "MARKER", "PROPERTIES", 
			"AND", "NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", 
			"SLASH", "QUOTE", "QUESTION", "PRODUCT", "ADDITION", "CONJUNCTION", "SPOT", 
			"ACCEPTANCE", "ACCNAME", "FIN", "INF", "PROP_LBRACK", "PROP_RBRACK", 
			"DIGIT", "PROBABILITY", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", "'.'", "'='", "' '", "'\t'", null, "'['", "']'", 
			"'('", "')'", "'||'", "'|'", "';'", "'$'", "'-'", "'->'", "'nil'", null, 
			null, "'controls'", "'active'", "'passive'", "'names'", "'inner'", "'outer'", 
			"'rule'", "'@'", "'model'", "'marker'", "'properties'", "'&&'", "'!'", 
			"'if'", "'then'", "'else'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='", 
			"'/'", "'\"'", "'?'", "'*'", "'+'", "'&'", "'SPOT-ACCEPTANCE'", "'Acceptance'", 
			"'acc-name'", "'Fin'", "'Inf'", "'\\{'", "'\\}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", "NEWLINE", 
			"LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "SEMI", "DOLLAR", "UNLINKED", 
			"ARROW", "NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", 
			"NAMES", "INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "MARKER", "PROPERTIES", 
			"AND", "NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", 
			"SLASH", "QUOTE", "QUESTION", "PRODUCT", "ADDITION", "CONJUNCTION", "SPOT", 
			"ACCEPTANCE", "ACCNAME", "FIN", "INF", "PROP_LBRACK", "PROP_RBRACK", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u017f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\3"+
		"\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\5\b\u0089\n\b"+
		"\3\b\3\b\6\b\u008d\n\b\r\b\16\b\u008e\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u00b1\n\24\f\24\16"+
		"\24\u00b4\13\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\7\25\u00bd\n\25\f\25"+
		"\16\25\u00c0\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%"+
		"\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-"+
		"\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67\38\38\38"+
		"\39\69\u016e\n9\r9\169\u016f\3:\3:\3:\6:\u0175\n:\r:\16:\u0176\3;\3;\7"+
		";\u017b\n;\f;\16;\u017e\13;\3\u00b2\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<\3\2\5\4\2\f\f\17\17\4\2C\\"+
		"c|\7\2//\62;C\\aac|\2\u0186\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
		"\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C"+
		"\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2"+
		"\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2"+
		"\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i"+
		"\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2"+
		"\2\2\3w\3\2\2\2\5y\3\2\2\2\7{\3\2\2\2\t}\3\2\2\2\13\177\3\2\2\2\r\u0083"+
		"\3\2\2\2\17\u008c\3\2\2\2\21\u0092\3\2\2\2\23\u0094\3\2\2\2\25\u0096\3"+
		"\2\2\2\27\u0098\3\2\2\2\31\u009a\3\2\2\2\33\u009d\3\2\2\2\35\u009f\3\2"+
		"\2\2\37\u00a1\3\2\2\2!\u00a3\3\2\2\2#\u00a5\3\2\2\2%\u00a8\3\2\2\2\'\u00ac"+
		"\3\2\2\2)\u00ba\3\2\2\2+\u00c3\3\2\2\2-\u00cc\3\2\2\2/\u00d3\3\2\2\2\61"+
		"\u00db\3\2\2\2\63\u00e1\3\2\2\2\65\u00e7\3\2\2\2\67\u00ed\3\2\2\29\u00f2"+
		"\3\2\2\2;\u00f4\3\2\2\2=\u00fa\3\2\2\2?\u0101\3\2\2\2A\u010c\3\2\2\2C"+
		"\u010f\3\2\2\2E\u0111\3\2\2\2G\u0114\3\2\2\2I\u0119\3\2\2\2K\u011e\3\2"+
		"\2\2M\u0121\3\2\2\2O\u0124\3\2\2\2Q\u0126\3\2\2\2S\u0128\3\2\2\2U\u012b"+
		"\3\2\2\2W\u012e\3\2\2\2Y\u0130\3\2\2\2[\u0132\3\2\2\2]\u0134\3\2\2\2_"+
		"\u0136\3\2\2\2a\u0138\3\2\2\2c\u013a\3\2\2\2e\u014a\3\2\2\2g\u0155\3\2"+
		"\2\2i\u015e\3\2\2\2k\u0162\3\2\2\2m\u0166\3\2\2\2o\u0169\3\2\2\2q\u016d"+
		"\3\2\2\2s\u0171\3\2\2\2u\u0178\3\2\2\2wx\7.\2\2x\4\3\2\2\2yz\7<\2\2z\6"+
		"\3\2\2\2{|\7\60\2\2|\b\3\2\2\2}~\7?\2\2~\n\3\2\2\2\177\u0080\7\"\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\b\6\2\2\u0082\f\3\2\2\2\u0083\u0084\7\13\2"+
		"\2\u0084\u0085\3\2\2\2\u0085\u0086\b\7\2\2\u0086\16\3\2\2\2\u0087\u0089"+
		"\7\17\2\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2"+
		"\u008a\u008d\7\f\2\2\u008b\u008d\7\17\2\2\u008c\u0088\3\2\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0091\b\b\2\2\u0091\20\3\2\2\2\u0092\u0093\7]\2\2"+
		"\u0093\22\3\2\2\2\u0094\u0095\7_\2\2\u0095\24\3\2\2\2\u0096\u0097\7*\2"+
		"\2\u0097\26\3\2\2\2\u0098\u0099\7+\2\2\u0099\30\3\2\2\2\u009a\u009b\7"+
		"~\2\2\u009b\u009c\7~\2\2\u009c\32\3\2\2\2\u009d\u009e\7~\2\2\u009e\34"+
		"\3\2\2\2\u009f\u00a0\7=\2\2\u00a0\36\3\2\2\2\u00a1\u00a2\7&\2\2\u00a2"+
		" \3\2\2\2\u00a3\u00a4\7/\2\2\u00a4\"\3\2\2\2\u00a5\u00a6\7/\2\2\u00a6"+
		"\u00a7\7@\2\2\u00a7$\3\2\2\2\u00a8\u00a9\7p\2\2\u00a9\u00aa\7k\2\2\u00aa"+
		"\u00ab\7n\2\2\u00ab&\3\2\2\2\u00ac\u00ad\7\61\2\2\u00ad\u00ae\7,\2\2\u00ae"+
		"\u00b2\3\2\2\2\u00af\u00b1\13\2\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b4\3"+
		"\2\2\2\u00b2\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b5\u00b6\7,\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\u00b9\b\24\2\2\u00b9(\3\2\2\2\u00ba\u00be\7%\2\2\u00bb\u00bd"+
		"\n\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\b\25"+
		"\2\2\u00c2*\3\2\2\2\u00c3\u00c4\7e\2\2\u00c4\u00c5\7q\2\2\u00c5\u00c6"+
		"\7p\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7q\2\2\u00c9"+
		"\u00ca\7n\2\2\u00ca\u00cb\7u\2\2\u00cb,\3\2\2\2\u00cc\u00cd\7c\2\2\u00cd"+
		"\u00ce\7e\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7x\2\2"+
		"\u00d1\u00d2\7g\2\2\u00d2.\3\2\2\2\u00d3\u00d4\7r\2\2\u00d4\u00d5\7c\2"+
		"\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7u\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9"+
		"\7x\2\2\u00d9\u00da\7g\2\2\u00da\60\3\2\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd"+
		"\7c\2\2\u00dd\u00de\7o\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7u\2\2\u00e0"+
		"\62\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7p\2\2\u00e3\u00e4\7p\2\2\u00e4"+
		"\u00e5\7g\2\2\u00e5\u00e6\7t\2\2\u00e6\64\3\2\2\2\u00e7\u00e8\7q\2\2\u00e8"+
		"\u00e9\7w\2\2\u00e9\u00ea\7v\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7t\2\2"+
		"\u00ec\66\3\2\2\2\u00ed\u00ee\7t\2\2\u00ee\u00ef\7w\2\2\u00ef\u00f0\7"+
		"n\2\2\u00f0\u00f1\7g\2\2\u00f18\3\2\2\2\u00f2\u00f3\7B\2\2\u00f3:\3\2"+
		"\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6\7q\2\2\u00f6\u00f7\7f\2\2\u00f7\u00f8"+
		"\7g\2\2\u00f8\u00f9\7n\2\2\u00f9<\3\2\2\2\u00fa\u00fb\7o\2\2\u00fb\u00fc"+
		"\7c\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7m\2\2\u00fe\u00ff\7g\2\2\u00ff"+
		"\u0100\7t\2\2\u0100>\3\2\2\2\u0101\u0102\7r\2\2\u0102\u0103\7t\2\2\u0103"+
		"\u0104\7q\2\2\u0104\u0105\7r\2\2\u0105\u0106\7g\2\2\u0106\u0107\7t\2\2"+
		"\u0107\u0108\7v\2\2\u0108\u0109\7k\2\2\u0109\u010a\7g\2\2\u010a\u010b"+
		"\7u\2\2\u010b@\3\2\2\2\u010c\u010d\7(\2\2\u010d\u010e\7(\2\2\u010eB\3"+
		"\2\2\2\u010f\u0110\7#\2\2\u0110D\3\2\2\2\u0111\u0112\7k\2\2\u0112\u0113"+
		"\7h\2\2\u0113F\3\2\2\2\u0114\u0115\7v\2\2\u0115\u0116\7j\2\2\u0116\u0117"+
		"\7g\2\2\u0117\u0118\7p\2\2\u0118H\3\2\2\2\u0119\u011a\7g\2\2\u011a\u011b"+
		"\7n\2\2\u011b\u011c\7u\2\2\u011c\u011d\7g\2\2\u011dJ\3\2\2\2\u011e\u011f"+
		"\7>\2\2\u011f\u0120\7?\2\2\u0120L\3\2\2\2\u0121\u0122\7@\2\2\u0122\u0123"+
		"\7?\2\2\u0123N\3\2\2\2\u0124\u0125\7>\2\2\u0125P\3\2\2\2\u0126\u0127\7"+
		"@\2\2\u0127R\3\2\2\2\u0128\u0129\7?\2\2\u0129\u012a\7?\2\2\u012aT\3\2"+
		"\2\2\u012b\u012c\7#\2\2\u012c\u012d\7?\2\2\u012dV\3\2\2\2\u012e\u012f"+
		"\7\61\2\2\u012fX\3\2\2\2\u0130\u0131\7$\2\2\u0131Z\3\2\2\2\u0132\u0133"+
		"\7A\2\2\u0133\\\3\2\2\2\u0134\u0135\7,\2\2\u0135^\3\2\2\2\u0136\u0137"+
		"\7-\2\2\u0137`\3\2\2\2\u0138\u0139\7(\2\2\u0139b\3\2\2\2\u013a\u013b\7"+
		"U\2\2\u013b\u013c\7R\2\2\u013c\u013d\7Q\2\2\u013d\u013e\7V\2\2\u013e\u013f"+
		"\7/\2\2\u013f\u0140\7C\2\2\u0140\u0141\7E\2\2\u0141\u0142\7E\2\2\u0142"+
		"\u0143\7G\2\2\u0143\u0144\7R\2\2\u0144\u0145\7V\2\2\u0145\u0146\7C\2\2"+
		"\u0146\u0147\7P\2\2\u0147\u0148\7E\2\2\u0148\u0149\7G\2\2\u0149d\3\2\2"+
		"\2\u014a\u014b\7C\2\2\u014b\u014c\7e\2\2\u014c\u014d\7e\2\2\u014d\u014e"+
		"\7g\2\2\u014e\u014f\7r\2\2\u014f\u0150\7v\2\2\u0150\u0151\7c\2\2\u0151"+
		"\u0152\7p\2\2\u0152\u0153\7e\2\2\u0153\u0154\7g\2\2\u0154f\3\2\2\2\u0155"+
		"\u0156\7c\2\2\u0156\u0157\7e\2\2\u0157\u0158\7e\2\2\u0158\u0159\7/\2\2"+
		"\u0159\u015a\7p\2\2\u015a\u015b\7c\2\2\u015b\u015c\7o\2\2\u015c\u015d"+
		"\7g\2\2\u015dh\3\2\2\2\u015e\u015f\7H\2\2\u015f\u0160\7k\2\2\u0160\u0161"+
		"\7p\2\2\u0161j\3\2\2\2\u0162\u0163\7K\2\2\u0163\u0164\7p\2\2\u0164\u0165"+
		"\7h\2\2\u0165l\3\2\2\2\u0166\u0167\7^\2\2\u0167\u0168\7}\2\2\u0168n\3"+
		"\2\2\2\u0169\u016a\7^\2\2\u016a\u016b\7\177\2\2\u016bp\3\2\2\2\u016c\u016e"+
		"\4\62;\2\u016d\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u016d\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170r\3\2\2\2\u0171\u0172\7\62\2\2\u0172\u0174\5\7\4\2"+
		"\u0173\u0175\5q9\2\u0174\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0174"+
		"\3\2\2\2\u0176\u0177\3\2\2\2\u0177t\3\2\2\2\u0178\u017c\t\3\2\2\u0179"+
		"\u017b\t\4\2\2\u017a\u0179\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017dv\3\2\2\2\u017e\u017c\3\2\2\2\13\2\u0088"+
		"\u008c\u008e\u00b2\u00be\u016f\u0176\u017c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}