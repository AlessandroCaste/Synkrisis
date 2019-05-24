// Generated from /home/ale/Synkrisis/src/core/g4model/Transition.g4 by ANTLR 4.7.2
package antlr.transition;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TransitionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SEMI=2, COLON=3, DOT=4, ASSIGNMENT=5, STATE=6, WHITESP=7, ESCAPE=8, 
		NEWLINE=9, LSQ=10, RSQ=11, LPAR=12, RPAR=13, LOR=14, PAR=15, DOLLAR=16, 
		UNLINKED=17, ARROW=18, NIL=19, COMMENT=20, LINE_COMMENT=21, VARIABLE=22, 
		PROPERTIES=23, DIGIT=24, IDENTIFIER=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMA", "SEMI", "COLON", "DOT", "ASSIGNMENT", "STATE", "WHITESP", "ESCAPE", 
			"NEWLINE", "LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", "UNLINKED", 
			"ARROW", "NIL", "COMMENT", "LINE_COMMENT", "VARIABLE", "PROPERTIES", 
			"DIGIT", "IDENTIFIER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "';'", "':'", "'.'", "'='", "'State'", "' '", "'\t'", null, 
			"'['", "']'", "'('", "')'", "'||'", "'|'", "'$'", "'-'", "'->'", "'nil'", 
			null, null, "'@'", "'Properties'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "SEMI", "COLON", "DOT", "ASSIGNMENT", "STATE", "WHITESP", 
			"ESCAPE", "NEWLINE", "LSQ", "RSQ", "LPAR", "RPAR", "LOR", "PAR", "DOLLAR", 
			"UNLINKED", "ARROW", "NIL", "COMMENT", "LINE_COMMENT", "VARIABLE", "PROPERTIES", 
			"DIGIT", "IDENTIFIER"
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


	public TransitionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Transition.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00a2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\5\nQ\n\n\3\n\3"+
		"\n\6\nU\n\n\r\n\16\nV\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\7\25w\n\25\f\25\16\25z\13\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\7\26\u0083\n\26\f\26\16\26\u0086\13\26\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\6\31\u0098\n\31\r\31\16\31\u0099\3\32\3\32\7\32\u009e\n\32\f\32\16\32"+
		"\u00a1\13\32\3x\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\3\2\5\4\2\f\f\17\17\4\2C\\c|\6\2\62;C\\aac|\2\u00a8\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\3\65\3\2\2\2\5\67\3\2\2\2\7;\3\2\2\2\t=\3\2\2\2\13?\3\2\2\2\r"+
		"A\3\2\2\2\17G\3\2\2\2\21K\3\2\2\2\23T\3\2\2\2\25Z\3\2\2\2\27\\\3\2\2\2"+
		"\31^\3\2\2\2\33`\3\2\2\2\35b\3\2\2\2\37e\3\2\2\2!g\3\2\2\2#i\3\2\2\2%"+
		"k\3\2\2\2\'n\3\2\2\2)r\3\2\2\2+\u0080\3\2\2\2-\u0089\3\2\2\2/\u008b\3"+
		"\2\2\2\61\u0097\3\2\2\2\63\u009b\3\2\2\2\65\66\7.\2\2\66\4\3\2\2\2\67"+
		"8\7=\2\289\3\2\2\29:\b\3\2\2:\6\3\2\2\2;<\7<\2\2<\b\3\2\2\2=>\7\60\2\2"+
		">\n\3\2\2\2?@\7?\2\2@\f\3\2\2\2AB\7U\2\2BC\7v\2\2CD\7c\2\2DE\7v\2\2EF"+
		"\7g\2\2F\16\3\2\2\2GH\7\"\2\2HI\3\2\2\2IJ\b\b\2\2J\20\3\2\2\2KL\7\13\2"+
		"\2LM\3\2\2\2MN\b\t\2\2N\22\3\2\2\2OQ\7\17\2\2PO\3\2\2\2PQ\3\2\2\2QR\3"+
		"\2\2\2RU\7\f\2\2SU\7\17\2\2TP\3\2\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW"+
		"\3\2\2\2WX\3\2\2\2XY\b\n\2\2Y\24\3\2\2\2Z[\7]\2\2[\26\3\2\2\2\\]\7_\2"+
		"\2]\30\3\2\2\2^_\7*\2\2_\32\3\2\2\2`a\7+\2\2a\34\3\2\2\2bc\7~\2\2cd\7"+
		"~\2\2d\36\3\2\2\2ef\7~\2\2f \3\2\2\2gh\7&\2\2h\"\3\2\2\2ij\7/\2\2j$\3"+
		"\2\2\2kl\7/\2\2lm\7@\2\2m&\3\2\2\2no\7p\2\2op\7k\2\2pq\7n\2\2q(\3\2\2"+
		"\2rs\7\61\2\2st\7,\2\2tx\3\2\2\2uw\13\2\2\2vu\3\2\2\2wz\3\2\2\2xy\3\2"+
		"\2\2xv\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7,\2\2|}\7\61\2\2}~\3\2\2\2~\177"+
		"\b\25\2\2\177*\3\2\2\2\u0080\u0084\7%\2\2\u0081\u0083\n\2\2\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\b\26\2\2\u0088,\3\2\2\2"+
		"\u0089\u008a\7B\2\2\u008a.\3\2\2\2\u008b\u008c\7R\2\2\u008c\u008d\7t\2"+
		"\2\u008d\u008e\7q\2\2\u008e\u008f\7r\2\2\u008f\u0090\7g\2\2\u0090\u0091"+
		"\7t\2\2\u0091\u0092\7v\2\2\u0092\u0093\7k\2\2\u0093\u0094\7g\2\2\u0094"+
		"\u0095\7u\2\2\u0095\60\3\2\2\2\u0096\u0098\4\62;\2\u0097\u0096\3\2\2\2"+
		"\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\62"+
		"\3\2\2\2\u009b\u009f\t\3\2\2\u009c\u009e\t\4\2\2\u009d\u009c\3\2\2\2\u009e"+
		"\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\64\3\2\2"+
		"\2\u00a1\u009f\3\2\2\2\n\2PTVx\u0084\u0099\u009f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}