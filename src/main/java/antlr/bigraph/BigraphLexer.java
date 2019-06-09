package antlr.bigraph;// Generated from C:/Users/caste/IdeaProjects/Synkrisis/src\bigraph.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BigraphLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SEMI=2, COLON=3, DOT=4, ASSIGNMENT=5, WHITESP=6, ESCAPE=7, NEWLINE=8, 
		LSQ=9, RSQ=10, LPAR=11, RPAR=12, LOR=13, PAR=14, DOLLAR=15, UNLINKED=16, 
		ARROW=17, NIL=18, COMMENT=19, LINE_COMMENT=20, CONTROLS=21, ACTIVE=22, 
		PASSIVE=23, NAMES=24, INNER=25, OUTER=26, RULE=27, VARIABLE=28, MODEL=29, 
		PROPERTY=30, AND=31, NOT=32, IF=33, THEN=34, ELSE=35, LEQ=36, GEQ=37, 
		LT=38, GT=39, EQ=40, NEQ=41, FORALL=42, EXISTS=43, TRUE=44, FALSE=45, 
		DIGIT=46, IDENTIFIER=47;
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
			"INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "PROPERTY", "AND", "NOT", 
			"IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", "FORALL", 
			"EXISTS", "TRUE", "FALSE", "DIGIT", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "';'", "':'", "'.'", "'='", "' '", "'\t'", null, "'['", 
			"']'", "'('", "')'", "'||'", "'|'", "'$'", "'-'", "'->'", "'nil'", null, 
			null, "'controls'", "'active'", "'passive'", "'names'", "'inner'", "'outer'", 
			"'rule'", "'@'", "'model'", "'property'", "'&&'", "'!'", "'if'", "'then'", 
			"'else'", "'<='", "'>='", "'<'", "'>'", "'=='", "'!='", "'forall'", "'exists'", 
			"'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "SEMI", "COLON", "DOT", "ASSIGNMENT", "WHITESP", "ESCAPE", 
			"NEWLINE", "LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", "UNLINKED", 
			"ARROW", "NIL", "COMMENT", "LINE_COMMENT", "CONTROLS", "ACTIVE", "PASSIVE", 
			"NAMES", "INNER", "OUTER", "RULE", "VARIABLE", "MODEL", "PROPERTY", "AND", 
			"NOT", "IF", "THEN", "ELSE", "LEQ", "GEQ", "LT", "GT", "EQ", "NEQ", "FORALL", 
			"EXISTS", "TRUE", "FALSE", "DIGIT", "IDENTIFIER"
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


	public BigraphLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "antlr/g4models/bigraph.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0136\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\5\tw\n\t\3\t\3\t\6"+
		"\t{\n\t\r\t\16\t|\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\7\24\u009d\n\24\f\24\16\24\u00a0\13\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\7\25\u00a9\n\25\f\25\16\25\u00ac\13\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3"+
		"\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3)\3"+
		")\3)\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3"+
		"-\3.\3.\3.\3.\3.\3.\3/\6/\u012c\n/\r/\16/\u012d\3\60\3\60\7\60\u0132\n"+
		"\60\f\60\16\60\u0135\13\60\3\u009e\2\61\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W"+
		"-Y.[/]\60_\61\3\2\5\4\2\f\f\17\17\4\2C\\c|\6\2\62;C\\aac|\2\u013c\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3"+
		"\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2"+
		"\2\5c\3\2\2\2\7g\3\2\2\2\ti\3\2\2\2\13k\3\2\2\2\rm\3\2\2\2\17q\3\2\2\2"+
		"\21z\3\2\2\2\23\u0080\3\2\2\2\25\u0082\3\2\2\2\27\u0084\3\2\2\2\31\u0086"+
		"\3\2\2\2\33\u0088\3\2\2\2\35\u008b\3\2\2\2\37\u008d\3\2\2\2!\u008f\3\2"+
		"\2\2#\u0091\3\2\2\2%\u0094\3\2\2\2\'\u0098\3\2\2\2)\u00a6\3\2\2\2+\u00af"+
		"\3\2\2\2-\u00b8\3\2\2\2/\u00bf\3\2\2\2\61\u00c7\3\2\2\2\63\u00cd\3\2\2"+
		"\2\65\u00d3\3\2\2\2\67\u00d9\3\2\2\29\u00de\3\2\2\2;\u00e0\3\2\2\2=\u00e6"+
		"\3\2\2\2?\u00ef\3\2\2\2A\u00f2\3\2\2\2C\u00f4\3\2\2\2E\u00f7\3\2\2\2G"+
		"\u00fc\3\2\2\2I\u0101\3\2\2\2K\u0104\3\2\2\2M\u0107\3\2\2\2O\u0109\3\2"+
		"\2\2Q\u010b\3\2\2\2S\u010e\3\2\2\2U\u0111\3\2\2\2W\u0118\3\2\2\2Y\u011f"+
		"\3\2\2\2[\u0124\3\2\2\2]\u012b\3\2\2\2_\u012f\3\2\2\2ab\7.\2\2b\4\3\2"+
		"\2\2cd\7=\2\2de\3\2\2\2ef\b\3\2\2f\6\3\2\2\2gh\7<\2\2h\b\3\2\2\2ij\7\60"+
		"\2\2j\n\3\2\2\2kl\7?\2\2l\f\3\2\2\2mn\7\"\2\2no\3\2\2\2op\b\7\2\2p\16"+
		"\3\2\2\2qr\7\13\2\2rs\3\2\2\2st\b\b\2\2t\20\3\2\2\2uw\7\17\2\2vu\3\2\2"+
		"\2vw\3\2\2\2wx\3\2\2\2x{\7\f\2\2y{\7\17\2\2zv\3\2\2\2zy\3\2\2\2{|\3\2"+
		"\2\2|z\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\b\t\2\2\177\22\3\2\2\2\u0080\u0081"+
		"\7]\2\2\u0081\24\3\2\2\2\u0082\u0083\7_\2\2\u0083\26\3\2\2\2\u0084\u0085"+
		"\7*\2\2\u0085\30\3\2\2\2\u0086\u0087\7+\2\2\u0087\32\3\2\2\2\u0088\u0089"+
		"\7~\2\2\u0089\u008a\7~\2\2\u008a\34\3\2\2\2\u008b\u008c\7~\2\2\u008c\36"+
		"\3\2\2\2\u008d\u008e\7&\2\2\u008e \3\2\2\2\u008f\u0090\7/\2\2\u0090\""+
		"\3\2\2\2\u0091\u0092\7/\2\2\u0092\u0093\7@\2\2\u0093$\3\2\2\2\u0094\u0095"+
		"\7p\2\2\u0095\u0096\7k\2\2\u0096\u0097\7n\2\2\u0097&\3\2\2\2\u0098\u0099"+
		"\7\61\2\2\u0099\u009a\7,\2\2\u009a\u009e\3\2\2\2\u009b\u009d\13\2\2\2"+
		"\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009f\3\2\2\2\u009e\u009c"+
		"\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\7,\2\2\u00a2"+
		"\u00a3\7\61\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\b\24\2\2\u00a5(\3\2\2"+
		"\2\u00a6\u00aa\7%\2\2\u00a7\u00a9\n\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac"+
		"\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00ae\b\25\2\2\u00ae*\3\2\2\2\u00af\u00b0\7e\2\2"+
		"\u00b0\u00b1\7q\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4"+
		"\7t\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7u\2\2\u00b7"+
		",\3\2\2\2\u00b8\u00b9\7c\2\2\u00b9\u00ba\7e\2\2\u00ba\u00bb\7v\2\2\u00bb"+
		"\u00bc\7k\2\2\u00bc\u00bd\7x\2\2\u00bd\u00be\7g\2\2\u00be.\3\2\2\2\u00bf"+
		"\u00c0\7r\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7u\2\2\u00c2\u00c3\7u\2\2"+
		"\u00c3\u00c4\7k\2\2\u00c4\u00c5\7x\2\2\u00c5\u00c6\7g\2\2\u00c6\60\3\2"+
		"\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7o\2\2\u00ca\u00cb"+
		"\7g\2\2\u00cb\u00cc\7u\2\2\u00cc\62\3\2\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf"+
		"\7p\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7t\2\2\u00d2"+
		"\64\3\2\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7w\2\2\u00d5\u00d6\7v\2\2\u00d6"+
		"\u00d7\7g\2\2\u00d7\u00d8\7t\2\2\u00d8\66\3\2\2\2\u00d9\u00da\7t\2\2\u00da"+
		"\u00db\7w\2\2\u00db\u00dc\7n\2\2\u00dc\u00dd\7g\2\2\u00dd8\3\2\2\2\u00de"+
		"\u00df\7B\2\2\u00df:\3\2\2\2\u00e0\u00e1\7o\2\2\u00e1\u00e2\7q\2\2\u00e2"+
		"\u00e3\7f\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7n\2\2\u00e5<\3\2\2\2\u00e6"+
		"\u00e7\7r\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7q\2\2\u00e9\u00ea\7r\2\2"+
		"\u00ea\u00eb\7g\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee"+
		"\7{\2\2\u00ee>\3\2\2\2\u00ef\u00f0\7(\2\2\u00f0\u00f1\7(\2\2\u00f1@\3"+
		"\2\2\2\u00f2\u00f3\7#\2\2\u00f3B\3\2\2\2\u00f4\u00f5\7k\2\2\u00f5\u00f6"+
		"\7h\2\2\u00f6D\3\2\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7j\2\2\u00f9\u00fa"+
		"\7g\2\2\u00fa\u00fb\7p\2\2\u00fbF\3\2\2\2\u00fc\u00fd\7g\2\2\u00fd\u00fe"+
		"\7n\2\2\u00fe\u00ff\7u\2\2\u00ff\u0100\7g\2\2\u0100H\3\2\2\2\u0101\u0102"+
		"\7>\2\2\u0102\u0103\7?\2\2\u0103J\3\2\2\2\u0104\u0105\7@\2\2\u0105\u0106"+
		"\7?\2\2\u0106L\3\2\2\2\u0107\u0108\7>\2\2\u0108N\3\2\2\2\u0109\u010a\7"+
		"@\2\2\u010aP\3\2\2\2\u010b\u010c\7?\2\2\u010c\u010d\7?\2\2\u010dR\3\2"+
		"\2\2\u010e\u010f\7#\2\2\u010f\u0110\7?\2\2\u0110T\3\2\2\2\u0111\u0112"+
		"\7h\2\2\u0112\u0113\7q\2\2\u0113\u0114\7t\2\2\u0114\u0115\7c\2\2\u0115"+
		"\u0116\7n\2\2\u0116\u0117\7n\2\2\u0117V\3\2\2\2\u0118\u0119\7g\2\2\u0119"+
		"\u011a\7z\2\2\u011a\u011b\7k\2\2\u011b\u011c\7u\2\2\u011c\u011d\7v\2\2"+
		"\u011d\u011e\7u\2\2\u011eX\3\2\2\2\u011f\u0120\7v\2\2\u0120\u0121\7t\2"+
		"\2\u0121\u0122\7w\2\2\u0122\u0123\7g\2\2\u0123Z\3\2\2\2\u0124\u0125\7"+
		"h\2\2\u0125\u0126\7c\2\2\u0126\u0127\7n\2\2\u0127\u0128\7u\2\2\u0128\u0129"+
		"\7g\2\2\u0129\\\3\2\2\2\u012a\u012c\4\62;\2\u012b\u012a\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e^\3\2\2\2"+
		"\u012f\u0133\t\3\2\2\u0130\u0132\t\4\2\2\u0131\u0130\3\2\2\2\u0132\u0135"+
		"\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134`\3\2\2\2\u0135"+
		"\u0133\3\2\2\2\n\2vz|\u009e\u00aa\u012d\u0133\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}