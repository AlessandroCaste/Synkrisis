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
		COMMA=1, COLON=2, DOT=3, ASSIGNMENT=4, WHITESP=5, ESCAPE=6, NEWLINE=7, 
		LSQ=8, RSQ=9, LPAR=10, RPAR=11, LOR=12, PAR=13, DOLLAR=14, UNLINKED=15, 
		ARROW=16, NIL=17, COMMENT=18, LINE_COMMENT=19, CONTROLS=20, ACTIVE=21, 
		PASSIVE=22, NAMES=23, INNER=24, OUTER=25, RULE=26, VARIABLE=27, MODEL=28, 
		MARKER=29, PROPERTIES=30, AND=31, NOT=32, IF=33, THEN=34, ELSE=35, LEQ=36, 
		GEQ=37, LT=38, GT=39, EQ=40, NEQ=41, SLASH=42, QUOTE=43, QUESTION=44, 
		PRODUCT=45, ADDITION=46, CONJUNCTION=47, SPOT=48, ACCEPTANCE=49, ACCNAME=50, 
		FIN=51, INF=52, SPOT_TRUE=53, SPOT_FALSE=54, PROP_LBRACK=55, PROP_RBRACK=56, 
		DIGIT=57, PROBABILITY=58, SPOT_IDENTIFIER=59, IDENTIFIER=60;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMA", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", "NEWLINE", 
			"LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", "UNLINKED", "ARROW", 
			"NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", "NAMES", 
			"INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "MARKER", "PROPERTIES", 
			"AND", "NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", 
			"SLASH", "QUOTE", "QUESTION", "PRODUCT", "ADDITION", "CONJUNCTION", "SPOT", 
			"ACCEPTANCE", "ACCNAME", "FIN", "INF", "SPOT_TRUE", "SPOT_FALSE", "PROP_LBRACK", 
			"PROP_RBRACK", "DIGIT", "PROBABILITY", "SPOT_IDENTIFIER", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", "'.'", "'='", "' '", "'\t'", null, "'['", "']'", 
			"'('", "')'", "'||'", "'|'", "'$'", "'-'", "'->'", "'nil'", null, null, 
			"'controls'", "'active'", "'passive'", "'names'", "'inner'", "'outer'", 
			"'rule'", "'@'", "'model'", "'marker'", "'properties'", "'&&'", "'!'", 
			"'if'", "'then'", "'else'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='", 
			"'/'", "'\"'", "'?'", "'*'", "'+'", "'&'", "'SPOT-ACCEPTANCE'", "'Acceptance'", 
			"'acc-name'", "'Fin'", "'Inf'", "'t'", "'f'", "'\\{'", "'\\}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", "NEWLINE", 
			"LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", "UNLINKED", "ARROW", 
			"NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", "NAMES", 
			"INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "MARKER", "PROPERTIES", 
			"AND", "NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", 
			"SLASH", "QUOTE", "QUESTION", "PRODUCT", "ADDITION", "CONJUNCTION", "SPOT", 
			"ACCEPTANCE", "ACCNAME", "FIN", "INF", "SPOT_TRUE", "SPOT_FALSE", "PROP_LBRACK", 
			"PROP_RBRACK", "DIGIT", "PROBABILITY", "SPOT_IDENTIFIER", "IDENTIFIER"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2>\u0194\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\5"+
		"\b\u008d\n\b\3\b\3\b\6\b\u0091\n\b\r\b\16\b\u0092\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\7\23\u00b3\n\23\f\23"+
		"\16\23\u00b6\13\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\7\24\u00bf\n\24"+
		"\f\24\16\24\u00c2\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3"+
		"\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3"+
		")\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66"+
		"\3\66\3\67\3\67\38\38\38\39\39\39\3:\6:\u0174\n:\r:\16:\u0175\3;\3;\3"+
		";\6;\u017b\n;\r;\16;\u017c\3<\6<\u0180\n<\r<\16<\u0181\3<\3<\6<\u0186"+
		"\n<\r<\16<\u0187\6<\u018a\n<\r<\16<\u018b\3=\3=\7=\u0190\n=\f=\16=\u0193"+
		"\13=\3\u00b4\2>\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\66k\67m8o9q:s;u<w=y>\3\2\6\4\2\f\f\17\17\5\2C\\aac|\4\2C\\c|\6\2"+
		"\62;C\\aac|\2\u019e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2"+
		"\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2"+
		"\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w"+
		"\3\2\2\2\2y\3\2\2\2\3{\3\2\2\2\5}\3\2\2\2\7\177\3\2\2\2\t\u0081\3\2\2"+
		"\2\13\u0083\3\2\2\2\r\u0087\3\2\2\2\17\u0090\3\2\2\2\21\u0096\3\2\2\2"+
		"\23\u0098\3\2\2\2\25\u009a\3\2\2\2\27\u009c\3\2\2\2\31\u009e\3\2\2\2\33"+
		"\u00a1\3\2\2\2\35\u00a3\3\2\2\2\37\u00a5\3\2\2\2!\u00a7\3\2\2\2#\u00aa"+
		"\3\2\2\2%\u00ae\3\2\2\2\'\u00bc\3\2\2\2)\u00c5\3\2\2\2+\u00ce\3\2\2\2"+
		"-\u00d5\3\2\2\2/\u00dd\3\2\2\2\61\u00e3\3\2\2\2\63\u00e9\3\2\2\2\65\u00ef"+
		"\3\2\2\2\67\u00f4\3\2\2\29\u00f6\3\2\2\2;\u00fc\3\2\2\2=\u0103\3\2\2\2"+
		"?\u010e\3\2\2\2A\u0111\3\2\2\2C\u0113\3\2\2\2E\u0116\3\2\2\2G\u011b\3"+
		"\2\2\2I\u0120\3\2\2\2K\u0123\3\2\2\2M\u0126\3\2\2\2O\u0128\3\2\2\2Q\u012a"+
		"\3\2\2\2S\u012d\3\2\2\2U\u0130\3\2\2\2W\u0132\3\2\2\2Y\u0134\3\2\2\2["+
		"\u0136\3\2\2\2]\u0138\3\2\2\2_\u013a\3\2\2\2a\u013c\3\2\2\2c\u014c\3\2"+
		"\2\2e\u0157\3\2\2\2g\u0160\3\2\2\2i\u0164\3\2\2\2k\u0168\3\2\2\2m\u016a"+
		"\3\2\2\2o\u016c\3\2\2\2q\u016f\3\2\2\2s\u0173\3\2\2\2u\u0177\3\2\2\2w"+
		"\u017f\3\2\2\2y\u018d\3\2\2\2{|\7.\2\2|\4\3\2\2\2}~\7<\2\2~\6\3\2\2\2"+
		"\177\u0080\7\60\2\2\u0080\b\3\2\2\2\u0081\u0082\7?\2\2\u0082\n\3\2\2\2"+
		"\u0083\u0084\7\"\2\2\u0084\u0085\3\2\2\2\u0085\u0086\b\6\2\2\u0086\f\3"+
		"\2\2\2\u0087\u0088\7\13\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\7\2\2\u008a"+
		"\16\3\2\2\2\u008b\u008d\7\17\2\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2"+
		"\2\u008d\u008e\3\2\2\2\u008e\u0091\7\f\2\2\u008f\u0091\7\17\2\2\u0090"+
		"\u008c\3\2\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\b\2\2\u0095"+
		"\20\3\2\2\2\u0096\u0097\7]\2\2\u0097\22\3\2\2\2\u0098\u0099\7_\2\2\u0099"+
		"\24\3\2\2\2\u009a\u009b\7*\2\2\u009b\26\3\2\2\2\u009c\u009d\7+\2\2\u009d"+
		"\30\3\2\2\2\u009e\u009f\7~\2\2\u009f\u00a0\7~\2\2\u00a0\32\3\2\2\2\u00a1"+
		"\u00a2\7~\2\2\u00a2\34\3\2\2\2\u00a3\u00a4\7&\2\2\u00a4\36\3\2\2\2\u00a5"+
		"\u00a6\7/\2\2\u00a6 \3\2\2\2\u00a7\u00a8\7/\2\2\u00a8\u00a9\7@\2\2\u00a9"+
		"\"\3\2\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7n\2\2\u00ad"+
		"$\3\2\2\2\u00ae\u00af\7\61\2\2\u00af\u00b0\7,\2\2\u00b0\u00b4\3\2\2\2"+
		"\u00b1\u00b3\13\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7"+
		"\u00b8\7,\2\2\u00b8\u00b9\7\61\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\b\23"+
		"\2\2\u00bb&\3\2\2\2\u00bc\u00c0\7%\2\2\u00bd\u00bf\n\2\2\2\u00be\u00bd"+
		"\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\b\24\2\2\u00c4(\3\2\2\2"+
		"\u00c5\u00c6\7e\2\2\u00c6\u00c7\7q\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9"+
		"\7v\2\2\u00c9\u00ca\7t\2\2\u00ca\u00cb\7q\2\2\u00cb\u00cc\7n\2\2\u00cc"+
		"\u00cd\7u\2\2\u00cd*\3\2\2\2\u00ce\u00cf\7c\2\2\u00cf\u00d0\7e\2\2\u00d0"+
		"\u00d1\7v\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7x\2\2\u00d3\u00d4\7g\2\2"+
		"\u00d4,\3\2\2\2\u00d5\u00d6\7r\2\2\u00d6\u00d7\7c\2\2\u00d7\u00d8\7u\2"+
		"\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7x\2\2\u00db\u00dc"+
		"\7g\2\2\u00dc.\3\2\2\2\u00dd\u00de\7p\2\2\u00de\u00df\7c\2\2\u00df\u00e0"+
		"\7o\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7u\2\2\u00e2\60\3\2\2\2\u00e3\u00e4"+
		"\7k\2\2\u00e4\u00e5\7p\2\2\u00e5\u00e6\7p\2\2\u00e6\u00e7\7g\2\2\u00e7"+
		"\u00e8\7t\2\2\u00e8\62\3\2\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7w\2\2\u00eb"+
		"\u00ec\7v\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7t\2\2\u00ee\64\3\2\2\2\u00ef"+
		"\u00f0\7t\2\2\u00f0\u00f1\7w\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7g\2\2"+
		"\u00f3\66\3\2\2\2\u00f4\u00f5\7B\2\2\u00f58\3\2\2\2\u00f6\u00f7\7o\2\2"+
		"\u00f7\u00f8\7q\2\2\u00f8\u00f9\7f\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb"+
		"\7n\2\2\u00fb:\3\2\2\2\u00fc\u00fd\7o\2\2\u00fd\u00fe\7c\2\2\u00fe\u00ff"+
		"\7t\2\2\u00ff\u0100\7m\2\2\u0100\u0101\7g\2\2\u0101\u0102\7t\2\2\u0102"+
		"<\3\2\2\2\u0103\u0104\7r\2\2\u0104\u0105\7t\2\2\u0105\u0106\7q\2\2\u0106"+
		"\u0107\7r\2\2\u0107\u0108\7g\2\2\u0108\u0109\7t\2\2\u0109\u010a\7v\2\2"+
		"\u010a\u010b\7k\2\2\u010b\u010c\7g\2\2\u010c\u010d\7u\2\2\u010d>\3\2\2"+
		"\2\u010e\u010f\7(\2\2\u010f\u0110\7(\2\2\u0110@\3\2\2\2\u0111\u0112\7"+
		"#\2\2\u0112B\3\2\2\2\u0113\u0114\7k\2\2\u0114\u0115\7h\2\2\u0115D\3\2"+
		"\2\2\u0116\u0117\7v\2\2\u0117\u0118\7j\2\2\u0118\u0119\7g\2\2\u0119\u011a"+
		"\7p\2\2\u011aF\3\2\2\2\u011b\u011c\7g\2\2\u011c\u011d\7n\2\2\u011d\u011e"+
		"\7u\2\2\u011e\u011f\7g\2\2\u011fH\3\2\2\2\u0120\u0121\7>\2\2\u0121\u0122"+
		"\7?\2\2\u0122J\3\2\2\2\u0123\u0124\7@\2\2\u0124\u0125\7?\2\2\u0125L\3"+
		"\2\2\2\u0126\u0127\7>\2\2\u0127N\3\2\2\2\u0128\u0129\7@\2\2\u0129P\3\2"+
		"\2\2\u012a\u012b\7?\2\2\u012b\u012c\7?\2\2\u012cR\3\2\2\2\u012d\u012e"+
		"\7#\2\2\u012e\u012f\7?\2\2\u012fT\3\2\2\2\u0130\u0131\7\61\2\2\u0131V"+
		"\3\2\2\2\u0132\u0133\7$\2\2\u0133X\3\2\2\2\u0134\u0135\7A\2\2\u0135Z\3"+
		"\2\2\2\u0136\u0137\7,\2\2\u0137\\\3\2\2\2\u0138\u0139\7-\2\2\u0139^\3"+
		"\2\2\2\u013a\u013b\7(\2\2\u013b`\3\2\2\2\u013c\u013d\7U\2\2\u013d\u013e"+
		"\7R\2\2\u013e\u013f\7Q\2\2\u013f\u0140\7V\2\2\u0140\u0141\7/\2\2\u0141"+
		"\u0142\7C\2\2\u0142\u0143\7E\2\2\u0143\u0144\7E\2\2\u0144\u0145\7G\2\2"+
		"\u0145\u0146\7R\2\2\u0146\u0147\7V\2\2\u0147\u0148\7C\2\2\u0148\u0149"+
		"\7P\2\2\u0149\u014a\7E\2\2\u014a\u014b\7G\2\2\u014bb\3\2\2\2\u014c\u014d"+
		"\7C\2\2\u014d\u014e\7e\2\2\u014e\u014f\7e\2\2\u014f\u0150\7g\2\2\u0150"+
		"\u0151\7r\2\2\u0151\u0152\7v\2\2\u0152\u0153\7c\2\2\u0153\u0154\7p\2\2"+
		"\u0154\u0155\7e\2\2\u0155\u0156\7g\2\2\u0156d\3\2\2\2\u0157\u0158\7c\2"+
		"\2\u0158\u0159\7e\2\2\u0159\u015a\7e\2\2\u015a\u015b\7/\2\2\u015b\u015c"+
		"\7p\2\2\u015c\u015d\7c\2\2\u015d\u015e\7o\2\2\u015e\u015f\7g\2\2\u015f"+
		"f\3\2\2\2\u0160\u0161\7H\2\2\u0161\u0162\7k\2\2\u0162\u0163\7p\2\2\u0163"+
		"h\3\2\2\2\u0164\u0165\7K\2\2\u0165\u0166\7p\2\2\u0166\u0167\7h\2\2\u0167"+
		"j\3\2\2\2\u0168\u0169\7v\2\2\u0169l\3\2\2\2\u016a\u016b\7h\2\2\u016bn"+
		"\3\2\2\2\u016c\u016d\7^\2\2\u016d\u016e\7}\2\2\u016ep\3\2\2\2\u016f\u0170"+
		"\7^\2\2\u0170\u0171\7\177\2\2\u0171r\3\2\2\2\u0172\u0174\4\62;\2\u0173"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2"+
		"\2\2\u0176t\3\2\2\2\u0177\u0178\7\62\2\2\u0178\u017a\5\7\4\2\u0179\u017b"+
		"\5s:\2\u017a\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017a\3\2\2\2\u017c"+
		"\u017d\3\2\2\2\u017dv\3\2\2\2\u017e\u0180\t\3\2\2\u017f\u017e\3\2\2\2"+
		"\u0180\u0181\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0189"+
		"\3\2\2\2\u0183\u0185\7/\2\2\u0184\u0186\t\3\2\2\u0185\u0184\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u018a\3\2"+
		"\2\2\u0189\u0183\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189\3\2\2\2\u018b"+
		"\u018c\3\2\2\2\u018cx\3\2\2\2\u018d\u0191\t\4\2\2\u018e\u0190\t\5\2\2"+
		"\u018f\u018e\3\2\2\2\u0190\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192"+
		"\3\2\2\2\u0192z\3\2\2\2\u0193\u0191\3\2\2\2\16\2\u008c\u0090\u0092\u00b4"+
		"\u00c0\u0175\u017c\u0181\u0187\u018b\u0191\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}