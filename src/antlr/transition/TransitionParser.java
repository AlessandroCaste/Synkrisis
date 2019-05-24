// Generated from /home/ale/Synkrisis/src/core/g4model/Transition.g4 by ANTLR 4.7.2
package antlr.transition;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TransitionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SEMI=2, COLON=3, DOT=4, ASSIGNMENT=5, STATE=6, WHITESP=7, ESCAPE=8, 
		NEWLINE=9, LSQ=10, RSQ=11, LPAR=12, RPAR=13, LOR=14, PAR=15, DOLLAR=16, 
		UNLINKED=17, ARROW=18, NIL=19, COMMENT=20, LINE_COMMENT=21, VARIABLE=22, 
		PROPERTIES=23, DIGIT=24, IDENTIFIER=25;
	public static final int
		RULE_transition = 0, RULE_expression = 1, RULE_regions = 2, RULE_prefix = 3, 
		RULE_links = 4, RULE_state = 5, RULE_properties = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"transition", "expression", "regions", "prefix", "links", "state", "properties"
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

	@Override
	public String getGrammarFileName() { return "Transition.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TransitionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TransitionContext extends ParserRuleContext {
		public List<StateContext> state() {
			return getRuleContexts(StateContext.class);
		}
		public StateContext state(int i) {
			return getRuleContext(StateContext.class,i);
		}
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_transition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(14);
				state();
				}
				}
				setState(17); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STATE );
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
		public TerminalNode IDENTIFIER() { return getToken(TransitionParser.IDENTIFIER, 0); }
		public TerminalNode LSQ() { return getToken(TransitionParser.LSQ, 0); }
		public LinksContext links() {
			return getRuleContext(LinksContext.class,0);
		}
		public TerminalNode RSQ() { return getToken(TransitionParser.RSQ, 0); }
		public RegionsContext regions() {
			return getRuleContext(RegionsContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public TerminalNode DOLLAR() { return getToken(TransitionParser.DOLLAR, 0); }
		public TerminalNode DIGIT() { return getToken(TransitionParser.DIGIT, 0); }
		public TerminalNode LPAR() { return getToken(TransitionParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(TransitionParser.RPAR, 0); }
		public TerminalNode NIL() { return getToken(TransitionParser.NIL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		int _la;
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				match(IDENTIFIER);
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSQ) {
					{
					setState(20);
					match(LSQ);
					setState(21);
					links();
					setState(22);
					match(RSQ);
					}
				}

				setState(28);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(26);
					regions();
					}
					break;
				case DOT:
					{
					setState(27);
					prefix();
					}
					break;
				case EOF:
				case STATE:
				case RPAR:
				case PROPERTIES:
					break;
				default:
					break;
				}
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				match(DOLLAR);
				setState(31);
				match(DIGIT);
				setState(34);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(32);
					regions();
					}
					break;
				case DOT:
					{
					setState(33);
					prefix();
					}
					break;
				case EOF:
				case STATE:
				case RPAR:
				case PROPERTIES:
					break;
				default:
					break;
				}
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				match(LPAR);
				setState(37);
				expression();
				setState(38);
				match(RPAR);
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(39);
					regions();
					}
				}

				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				match(NIL);
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(43);
					regions();
					}
				}

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

	public static class RegionsContext extends ParserRuleContext {
		public TerminalNode LOR() { return getToken(TransitionParser.LOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR() { return getToken(TransitionParser.PAR, 0); }
		public RegionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterRegions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitRegions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitRegions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegionsContext regions() throws RecognitionException {
		RegionsContext _localctx = new RegionsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_regions);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(LOR);
				setState(49);
				expression();
				}
				break;
			case PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(PAR);
				setState(51);
				expression();
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

	public static class PrefixContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(TransitionParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_prefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(DOT);
			setState(55);
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

	public static class LinksContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(TransitionParser.IDENTIFIER, 0); }
		public TerminalNode COMMA() { return getToken(TransitionParser.COMMA, 0); }
		public LinksContext links() {
			return getRuleContext(LinksContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(TransitionParser.VARIABLE, 0); }
		public TerminalNode UNLINKED() { return getToken(TransitionParser.UNLINKED, 0); }
		public LinksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_links; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterLinks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitLinks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitLinks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinksContext links() throws RecognitionException {
		LinksContext _localctx = new LinksContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_links);
		int _la;
		try {
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(IDENTIFIER);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(58);
					match(COMMA);
					setState(59);
					links();
					}
				}

				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(VARIABLE);
				setState(63);
				match(IDENTIFIER);
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(64);
					match(COMMA);
					setState(65);
					links();
					}
				}

				}
				break;
			case UNLINKED:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				match(UNLINKED);
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(69);
					match(COMMA);
					setState(70);
					links();
					}
				}

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

	public static class StateContext extends ParserRuleContext {
		public TerminalNode STATE() { return getToken(TransitionParser.STATE, 0); }
		public TerminalNode DIGIT() { return getToken(TransitionParser.DIGIT, 0); }
		public TerminalNode COLON() { return getToken(TransitionParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public StateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StateContext state() throws RecognitionException {
		StateContext _localctx = new StateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_state);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(STATE);
			setState(76);
			match(DIGIT);
			setState(77);
			match(COLON);
			setState(78);
			expression();
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PROPERTIES) {
				{
				setState(79);
				properties();
				}
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

	public static class PropertiesContext extends ParserRuleContext {
		public TerminalNode PROPERTIES() { return getToken(TransitionParser.PROPERTIES, 0); }
		public TerminalNode COLON() { return getToken(TransitionParser.COLON, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(TransitionParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(TransitionParser.IDENTIFIER, i);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TransitionListener ) ((TransitionListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TransitionVisitor ) return ((TransitionVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(PROPERTIES);
			setState(83);
			match(COLON);
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				match(IDENTIFIER);
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\\\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\6\2\22\n\2\r\2\16\2\23"+
		"\3\3\3\3\3\3\3\3\3\3\5\3\33\n\3\3\3\3\3\5\3\37\n\3\3\3\3\3\3\3\3\3\5\3"+
		"%\n\3\3\3\3\3\3\3\3\3\5\3+\n\3\3\3\3\3\5\3/\n\3\5\3\61\n\3\3\4\3\4\3\4"+
		"\3\4\5\4\67\n\4\3\5\3\5\3\5\3\6\3\6\3\6\5\6?\n\6\3\6\3\6\3\6\3\6\5\6E"+
		"\n\6\3\6\3\6\3\6\5\6J\n\6\5\6L\n\6\3\7\3\7\3\7\3\7\3\7\5\7S\n\7\3\b\3"+
		"\b\3\b\6\bX\n\b\r\b\16\bY\3\b\2\2\t\2\4\6\b\n\f\16\2\2\2g\2\21\3\2\2\2"+
		"\4\60\3\2\2\2\6\66\3\2\2\2\b8\3\2\2\2\nK\3\2\2\2\fM\3\2\2\2\16T\3\2\2"+
		"\2\20\22\5\f\7\2\21\20\3\2\2\2\22\23\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2"+
		"\2\24\3\3\2\2\2\25\32\7\33\2\2\26\27\7\f\2\2\27\30\5\n\6\2\30\31\7\r\2"+
		"\2\31\33\3\2\2\2\32\26\3\2\2\2\32\33\3\2\2\2\33\36\3\2\2\2\34\37\5\6\4"+
		"\2\35\37\5\b\5\2\36\34\3\2\2\2\36\35\3\2\2\2\36\37\3\2\2\2\37\61\3\2\2"+
		"\2 !\7\22\2\2!$\7\32\2\2\"%\5\6\4\2#%\5\b\5\2$\"\3\2\2\2$#\3\2\2\2$%\3"+
		"\2\2\2%\61\3\2\2\2&\'\7\16\2\2\'(\5\4\3\2(*\7\17\2\2)+\5\6\4\2*)\3\2\2"+
		"\2*+\3\2\2\2+\61\3\2\2\2,.\7\25\2\2-/\5\6\4\2.-\3\2\2\2./\3\2\2\2/\61"+
		"\3\2\2\2\60\25\3\2\2\2\60 \3\2\2\2\60&\3\2\2\2\60,\3\2\2\2\61\5\3\2\2"+
		"\2\62\63\7\20\2\2\63\67\5\4\3\2\64\65\7\21\2\2\65\67\5\4\3\2\66\62\3\2"+
		"\2\2\66\64\3\2\2\2\67\7\3\2\2\289\7\6\2\29:\5\4\3\2:\t\3\2\2\2;>\7\33"+
		"\2\2<=\7\3\2\2=?\5\n\6\2><\3\2\2\2>?\3\2\2\2?L\3\2\2\2@A\7\30\2\2AD\7"+
		"\33\2\2BC\7\3\2\2CE\5\n\6\2DB\3\2\2\2DE\3\2\2\2EL\3\2\2\2FI\7\23\2\2G"+
		"H\7\3\2\2HJ\5\n\6\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2K;\3\2\2\2K@\3\2\2\2"+
		"KF\3\2\2\2L\13\3\2\2\2MN\7\b\2\2NO\7\32\2\2OP\7\5\2\2PR\5\4\3\2QS\5\16"+
		"\b\2RQ\3\2\2\2RS\3\2\2\2S\r\3\2\2\2TU\7\31\2\2UW\7\5\2\2VX\7\33\2\2WV"+
		"\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\17\3\2\2\2\20\23\32\36$*.\60\66"+
		">DIKRY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}