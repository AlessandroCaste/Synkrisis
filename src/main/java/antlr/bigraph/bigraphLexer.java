// Generated from C:/Users/Utente/Documents/GitHub/Synkrisis/src/main/java/antlr/g4models\bigraph.g4 by ANTLR 4.7.2
package antlr.bigraph;

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
		GEQ=38, LT=39, GT=40, EQ=41, NEQ=42, SLASH=43, QUOTE=44, QUESTION=45, 
		PRODUCT=46, ADDITION=47, CONJUNCTION=48, PRISM=49, SPOT=50, ACCEPTANCE=51, 
		ACCNAME=52, FIN=53, INF=54, SPOT_TRUE=55, SPOT_FALSE=56, DIGIT=57, PROBABILITY=58, 
		SPOT_IDENTIFIER=59, IDENTIFIER=60;
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
			"SLASH", "QUOTE", "QUESTION", "PRODUCT", "ADDITION", "CONJUNCTION", "PRISM", 
			"SPOT", "ACCEPTANCE", "ACCNAME", "FIN", "INF", "SPOT_TRUE", "SPOT_FALSE", 
			"DIGIT", "PROBABILITY", "SPOT_IDENTIFIER", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "';'", "':'", "'.'", "'='", "' '", "'\t'", null, "'['", 
			"']'", "'('", "')'", "'||'", "'|'", "'$'", "'-'", "'->'", "'nil'", null, 
			null, "'controls'", "'active'", "'passive'", "'names'", "'inner'", "'outer'", 
			"'rule'", "'@'", "'model'", "'marker'", "'properties'", "'&&'", "'!'", 
			"'if'", "'then'", "'else'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='", 
			"'/'", "'\"'", "'?'", "'*'", "'+'", "'&'", "'PRISM'", "'SPOT-ACCEPTANCE'", 
			"'Acceptance'", "'acc-name'", "'Fin'", "'Inf'", "'t'", "'f'"
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
			"SLASH", "QUOTE", "QUESTION", "PRODUCT", "ADDITION", "CONJUNCTION", "PRISM", 
			"SPOT", "ACCEPTANCE", "ACCNAME", "FIN", "INF", "SPOT_TRUE", "SPOT_FALSE", 
			"DIGIT", "PROBABILITY", "SPOT_IDENTIFIER", "IDENTIFIER"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2>\u0198\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\t\5\t\u0091\n\t\3\t\3\t\6\t\u0095\n\t\r\t\16\t\u0096\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7"+
		"\24\u00b7\n\24\f\24\16\24\u00ba\13\24\3\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\7\25\u00c3\n\25\f\25\16\25\u00c6\13\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#"+
		"\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3)\3)\3*"+
		"\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66"+
		"\3\66\3\66\3\67\3\67\3\67\3\67\38\38\39\39\3:\6:\u0178\n:\r:\16:\u0179"+
		"\3;\3;\3;\6;\u017f\n;\r;\16;\u0180\3<\6<\u0184\n<\r<\16<\u0185\3<\3<\6"+
		"<\u018a\n<\r<\16<\u018b\6<\u018e\n<\r<\16<\u018f\3=\3=\7=\u0194\n=\f="+
		"\16=\u0197\13=\3\u00b8\2>\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>\3\2\6\4\2\f\f\17\17\5\2C\\aac|\4"+
		"\2C\\c|\6\2\62;C\\aac|\2\u01a2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2"+
		"i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\2y\3\2\2\2\3{\3\2\2\2\5}\3\2\2\2\7\u0081\3\2\2\2\t\u0083"+
		"\3\2\2\2\13\u0085\3\2\2\2\r\u0087\3\2\2\2\17\u008b\3\2\2\2\21\u0094\3"+
		"\2\2\2\23\u009a\3\2\2\2\25\u009c\3\2\2\2\27\u009e\3\2\2\2\31\u00a0\3\2"+
		"\2\2\33\u00a2\3\2\2\2\35\u00a5\3\2\2\2\37\u00a7\3\2\2\2!\u00a9\3\2\2\2"+
		"#\u00ab\3\2\2\2%\u00ae\3\2\2\2\'\u00b2\3\2\2\2)\u00c0\3\2\2\2+\u00c9\3"+
		"\2\2\2-\u00d2\3\2\2\2/\u00d9\3\2\2\2\61\u00e1\3\2\2\2\63\u00e7\3\2\2\2"+
		"\65\u00ed\3\2\2\2\67\u00f3\3\2\2\29\u00f8\3\2\2\2;\u00fa\3\2\2\2=\u0100"+
		"\3\2\2\2?\u0107\3\2\2\2A\u0112\3\2\2\2C\u0115\3\2\2\2E\u0117\3\2\2\2G"+
		"\u011a\3\2\2\2I\u011f\3\2\2\2K\u0124\3\2\2\2M\u0127\3\2\2\2O\u012a\3\2"+
		"\2\2Q\u012c\3\2\2\2S\u012e\3\2\2\2U\u0131\3\2\2\2W\u0134\3\2\2\2Y\u0136"+
		"\3\2\2\2[\u0138\3\2\2\2]\u013a\3\2\2\2_\u013c\3\2\2\2a\u013e\3\2\2\2c"+
		"\u0140\3\2\2\2e\u0146\3\2\2\2g\u0156\3\2\2\2i\u0161\3\2\2\2k\u016a\3\2"+
		"\2\2m\u016e\3\2\2\2o\u0172\3\2\2\2q\u0174\3\2\2\2s\u0177\3\2\2\2u\u017b"+
		"\3\2\2\2w\u0183\3\2\2\2y\u0191\3\2\2\2{|\7.\2\2|\4\3\2\2\2}~\7=\2\2~\177"+
		"\3\2\2\2\177\u0080\b\3\2\2\u0080\6\3\2\2\2\u0081\u0082\7<\2\2\u0082\b"+
		"\3\2\2\2\u0083\u0084\7\60\2\2\u0084\n\3\2\2\2\u0085\u0086\7?\2\2\u0086"+
		"\f\3\2\2\2\u0087\u0088\7\"\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\7\2\2"+
		"\u008a\16\3\2\2\2\u008b\u008c\7\13\2\2\u008c\u008d\3\2\2\2\u008d\u008e"+
		"\b\b\2\2\u008e\20\3\2\2\2\u008f\u0091\7\17\2\2\u0090\u008f\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0095\7\f\2\2\u0093\u0095\7\17"+
		"\2\2\u0094\u0090\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\b\t"+
		"\2\2\u0099\22\3\2\2\2\u009a\u009b\7]\2\2\u009b\24\3\2\2\2\u009c\u009d"+
		"\7_\2\2\u009d\26\3\2\2\2\u009e\u009f\7*\2\2\u009f\30\3\2\2\2\u00a0\u00a1"+
		"\7+\2\2\u00a1\32\3\2\2\2\u00a2\u00a3\7~\2\2\u00a3\u00a4\7~\2\2\u00a4\34"+
		"\3\2\2\2\u00a5\u00a6\7~\2\2\u00a6\36\3\2\2\2\u00a7\u00a8\7&\2\2\u00a8"+
		" \3\2\2\2\u00a9\u00aa\7/\2\2\u00aa\"\3\2\2\2\u00ab\u00ac\7/\2\2\u00ac"+
		"\u00ad\7@\2\2\u00ad$\3\2\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7n\2\2\u00b1&\3\2\2\2\u00b2\u00b3\7\61\2\2\u00b3\u00b4\7,\2\2\u00b4"+
		"\u00b8\3\2\2\2\u00b5\u00b7\13\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3"+
		"\2\2\2\u00b8\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bc\7,\2\2\u00bc\u00bd\7\61\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00bf\b\24\2\2\u00bf(\3\2\2\2\u00c0\u00c4\7%\2\2\u00c1\u00c3"+
		"\n\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\b\25"+
		"\2\2\u00c8*\3\2\2\2\u00c9\u00ca\7e\2\2\u00ca\u00cb\7q\2\2\u00cb\u00cc"+
		"\7p\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7t\2\2\u00ce\u00cf\7q\2\2\u00cf"+
		"\u00d0\7n\2\2\u00d0\u00d1\7u\2\2\u00d1,\3\2\2\2\u00d2\u00d3\7c\2\2\u00d3"+
		"\u00d4\7e\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7\7x\2\2"+
		"\u00d7\u00d8\7g\2\2\u00d8.\3\2\2\2\u00d9\u00da\7r\2\2\u00da\u00db\7c\2"+
		"\2\u00db\u00dc\7u\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de\7k\2\2\u00de\u00df"+
		"\7x\2\2\u00df\u00e0\7g\2\2\u00e0\60\3\2\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3"+
		"\7c\2\2\u00e3\u00e4\7o\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7u\2\2\u00e6"+
		"\62\3\2\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7p\2\2\u00ea"+
		"\u00eb\7g\2\2\u00eb\u00ec\7t\2\2\u00ec\64\3\2\2\2\u00ed\u00ee\7q\2\2\u00ee"+
		"\u00ef\7w\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f2\7t\2\2"+
		"\u00f2\66\3\2\2\2\u00f3\u00f4\7t\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7"+
		"n\2\2\u00f6\u00f7\7g\2\2\u00f78\3\2\2\2\u00f8\u00f9\7B\2\2\u00f9:\3\2"+
		"\2\2\u00fa\u00fb\7o\2\2\u00fb\u00fc\7q\2\2\u00fc\u00fd\7f\2\2\u00fd\u00fe"+
		"\7g\2\2\u00fe\u00ff\7n\2\2\u00ff<\3\2\2\2\u0100\u0101\7o\2\2\u0101\u0102"+
		"\7c\2\2\u0102\u0103\7t\2\2\u0103\u0104\7m\2\2\u0104\u0105\7g\2\2\u0105"+
		"\u0106\7t\2\2\u0106>\3\2\2\2\u0107\u0108\7r\2\2\u0108\u0109\7t\2\2\u0109"+
		"\u010a\7q\2\2\u010a\u010b\7r\2\2\u010b\u010c\7g\2\2\u010c\u010d\7t\2\2"+
		"\u010d\u010e\7v\2\2\u010e\u010f\7k\2\2\u010f\u0110\7g\2\2\u0110\u0111"+
		"\7u\2\2\u0111@\3\2\2\2\u0112\u0113\7(\2\2\u0113\u0114\7(\2\2\u0114B\3"+
		"\2\2\2\u0115\u0116\7#\2\2\u0116D\3\2\2\2\u0117\u0118\7k\2\2\u0118\u0119"+
		"\7h\2\2\u0119F\3\2\2\2\u011a\u011b\7v\2\2\u011b\u011c\7j\2\2\u011c\u011d"+
		"\7g\2\2\u011d\u011e\7p\2\2\u011eH\3\2\2\2\u011f\u0120\7g\2\2\u0120\u0121"+
		"\7n\2\2\u0121\u0122\7u\2\2\u0122\u0123\7g\2\2\u0123J\3\2\2\2\u0124\u0125"+
		"\7>\2\2\u0125\u0126\7?\2\2\u0126L\3\2\2\2\u0127\u0128\7@\2\2\u0128\u0129"+
		"\7?\2\2\u0129N\3\2\2\2\u012a\u012b\7>\2\2\u012bP\3\2\2\2\u012c\u012d\7"+
		"@\2\2\u012dR\3\2\2\2\u012e\u012f\7?\2\2\u012f\u0130\7?\2\2\u0130T\3\2"+
		"\2\2\u0131\u0132\7#\2\2\u0132\u0133\7?\2\2\u0133V\3\2\2\2\u0134\u0135"+
		"\7\61\2\2\u0135X\3\2\2\2\u0136\u0137\7$\2\2\u0137Z\3\2\2\2\u0138\u0139"+
		"\7A\2\2\u0139\\\3\2\2\2\u013a\u013b\7,\2\2\u013b^\3\2\2\2\u013c\u013d"+
		"\7-\2\2\u013d`\3\2\2\2\u013e\u013f\7(\2\2\u013fb\3\2\2\2\u0140\u0141\7"+
		"R\2\2\u0141\u0142\7T\2\2\u0142\u0143\7K\2\2\u0143\u0144\7U\2\2\u0144\u0145"+
		"\7O\2\2\u0145d\3\2\2\2\u0146\u0147\7U\2\2\u0147\u0148\7R\2\2\u0148\u0149"+
		"\7Q\2\2\u0149\u014a\7V\2\2\u014a\u014b\7/\2\2\u014b\u014c\7C\2\2\u014c"+
		"\u014d\7E\2\2\u014d\u014e\7E\2\2\u014e\u014f\7G\2\2\u014f\u0150\7R\2\2"+
		"\u0150\u0151\7V\2\2\u0151\u0152\7C\2\2\u0152\u0153\7P\2\2\u0153\u0154"+
		"\7E\2\2\u0154\u0155\7G\2\2\u0155f\3\2\2\2\u0156\u0157\7C\2\2\u0157\u0158"+
		"\7e\2\2\u0158\u0159\7e\2\2\u0159\u015a\7g\2\2\u015a\u015b\7r\2\2\u015b"+
		"\u015c\7v\2\2\u015c\u015d\7c\2\2\u015d\u015e\7p\2\2\u015e\u015f\7e\2\2"+
		"\u015f\u0160\7g\2\2\u0160h\3\2\2\2\u0161\u0162\7c\2\2\u0162\u0163\7e\2"+
		"\2\u0163\u0164\7e\2\2\u0164\u0165\7/\2\2\u0165\u0166\7p\2\2\u0166\u0167"+
		"\7c\2\2\u0167\u0168\7o\2\2\u0168\u0169\7g\2\2\u0169j\3\2\2\2\u016a\u016b"+
		"\7H\2\2\u016b\u016c\7k\2\2\u016c\u016d\7p\2\2\u016dl\3\2\2\2\u016e\u016f"+
		"\7K\2\2\u016f\u0170\7p\2\2\u0170\u0171\7h\2\2\u0171n\3\2\2\2\u0172\u0173"+
		"\7v\2\2\u0173p\3\2\2\2\u0174\u0175\7h\2\2\u0175r\3\2\2\2\u0176\u0178\4"+
		"\62;\2\u0177\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u0177\3\2\2\2\u0179"+
		"\u017a\3\2\2\2\u017at\3\2\2\2\u017b\u017c\7\62\2\2\u017c\u017e\5\t\5\2"+
		"\u017d\u017f\5s:\2\u017e\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u017e"+
		"\3\2\2\2\u0180\u0181\3\2\2\2\u0181v\3\2\2\2\u0182\u0184\t\3\2\2\u0183"+
		"\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2"+
		"\2\2\u0186\u018d\3\2\2\2\u0187\u0189\7/\2\2\u0188\u018a\t\3\2\2\u0189"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u018e\3\2\2\2\u018d\u0187\3\2\2\2\u018e\u018f\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190x\3\2\2\2\u0191\u0195\t\4\2\2"+
		"\u0192\u0194\t\5\2\2\u0193\u0192\3\2\2\2\u0194\u0197\3\2\2\2\u0195\u0193"+
		"\3\2\2\2\u0195\u0196\3\2\2\2\u0196z\3\2\2\2\u0197\u0195\3\2\2\2\16\2\u0090"+
		"\u0094\u0096\u00b8\u00c4\u0179\u0180\u0185\u018b\u018f\u0195\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}