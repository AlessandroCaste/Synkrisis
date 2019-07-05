package antlr.bigraph;// Generated from C:/Users/Utente/Documents/GitHub/untitled/src\bigraph.g4 by ANTLR 4.7.2

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
public class bigraphParser extends Parser {
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
	public static final int
		RULE_bigraph = 0, RULE_controls = 1, RULE_control_statements = 2, RULE_names = 3, 
		RULE_name_statements = 4, RULE_reactions = 5, RULE_reaction_statement = 6, 
		RULE_expression = 7, RULE_regions = 8, RULE_prefix = 9, RULE_links = 10, 
		RULE_model = 11, RULE_marker = 12, RULE_marker_statement = 13, RULE_property = 14, 
		RULE_property_statements = 15, RULE_boolean_expression = 16, RULE_binary_operation = 17, 
		RULE_term = 18, RULE_parameters_list = 19, RULE_parameter = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"bigraph", "controls", "control_statements", "names", "name_statements", 
			"reactions", "reaction_statement", "expression", "regions", "prefix", 
			"links", "model", "marker", "marker_statement", "property", "property_statements", 
			"boolean_expression", "binary_operation", "term", "parameters_list", 
			"parameter"
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

	@Override
	public String getGrammarFileName() { return "bigraph.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public bigraphParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class BigraphContext extends ParserRuleContext {
		public ControlsContext controls() {
			return getRuleContext(ControlsContext.class,0);
		}
		public BigraphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bigraph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterBigraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitBigraph(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitBigraph(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BigraphContext bigraph() throws RecognitionException {
		BigraphContext _localctx = new BigraphContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_bigraph);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			controls();
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

	public static class ControlsContext extends ParserRuleContext {
		public TerminalNode CONTROLS() { return getToken(bigraphParser.CONTROLS, 0); }
		public TerminalNode COLON() { return getToken(bigraphParser.COLON, 0); }
		public Control_statementsContext control_statements() {
			return getRuleContext(Control_statementsContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public ControlsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterControls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitControls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitControls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControlsContext controls() throws RecognitionException {
		ControlsContext _localctx = new ControlsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_controls);
		try {
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONTROLS:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(CONTROLS);
				setState(45);
				match(COLON);
				setState(46);
				control_statements();
				}
				break;
			case NAMES:
			case RULE:
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				names();
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

	public static class Control_statementsContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode DIGIT() { return getToken(bigraphParser.DIGIT, 0); }
		public TerminalNode ACTIVE() { return getToken(bigraphParser.ACTIVE, 0); }
		public TerminalNode PASSIVE() { return getToken(bigraphParser.PASSIVE, 0); }
		public Control_statementsContext control_statements() {
			return getRuleContext(Control_statementsContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public Control_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_control_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterControl_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitControl_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitControl_statements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Control_statementsContext control_statements() throws RecognitionException {
		Control_statementsContext _localctx = new Control_statementsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_control_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_la = _input.LA(1);
			if ( !(_la==ACTIVE || _la==PASSIVE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(51);
			match(IDENTIFIER);
			setState(52);
			match(DIGIT);
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACTIVE:
			case PASSIVE:
				{
				setState(53);
				control_statements();
				}
				break;
			case NAMES:
			case RULE:
			case MODEL:
				{
				setState(54);
				names();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NamesContext extends ParserRuleContext {
		public TerminalNode NAMES() { return getToken(bigraphParser.NAMES, 0); }
		public TerminalNode COLON() { return getToken(bigraphParser.COLON, 0); }
		public Name_statementsContext name_statements() {
			return getRuleContext(Name_statementsContext.class,0);
		}
		public ReactionsContext reactions() {
			return getRuleContext(ReactionsContext.class,0);
		}
		public NamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamesContext names() throws RecognitionException {
		NamesContext _localctx = new NamesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_names);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(NAMES);
				setState(58);
				match(COLON);
				setState(59);
				name_statements();
				}
				break;
			case RULE:
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				reactions();
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

	public static class Name_statementsContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode INNER() { return getToken(bigraphParser.INNER, 0); }
		public TerminalNode OUTER() { return getToken(bigraphParser.OUTER, 0); }
		public Name_statementsContext name_statements() {
			return getRuleContext(Name_statementsContext.class,0);
		}
		public ReactionsContext reactions() {
			return getRuleContext(ReactionsContext.class,0);
		}
		public Name_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterName_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitName_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitName_statements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Name_statementsContext name_statements() throws RecognitionException {
		Name_statementsContext _localctx = new Name_statementsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_name_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			_la = _input.LA(1);
			if ( !(_la==INNER || _la==OUTER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(64);
			match(IDENTIFIER);
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INNER:
			case OUTER:
				{
				setState(65);
				name_statements();
				}
				break;
			case RULE:
			case MODEL:
				{
				setState(66);
				reactions();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ReactionsContext extends ParserRuleContext {
		public TerminalNode RULE() { return getToken(bigraphParser.RULE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(bigraphParser.ASSIGNMENT, 0); }
		public Reaction_statementContext reaction_statement() {
			return getRuleContext(Reaction_statementContext.class,0);
		}
		public ReactionsContext reactions() {
			return getRuleContext(ReactionsContext.class,0);
		}
		public ModelContext model() {
			return getRuleContext(ModelContext.class,0);
		}
		public ReactionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterReactions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitReactions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitReactions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactionsContext reactions() throws RecognitionException {
		ReactionsContext _localctx = new ReactionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reactions);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(RULE);
				setState(70);
				match(IDENTIFIER);
				setState(71);
				match(ASSIGNMENT);
				setState(72);
				reaction_statement();
				setState(73);
				reactions();
				}
				break;
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				model();
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

	public static class Reaction_statementContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ARROW() { return getToken(bigraphParser.ARROW, 0); }
		public TerminalNode LPAR() { return getToken(bigraphParser.LPAR, 0); }
		public TerminalNode PROBABILITY() { return getToken(bigraphParser.PROBABILITY, 0); }
		public TerminalNode RPAR() { return getToken(bigraphParser.RPAR, 0); }
		public Reaction_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reaction_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterReaction_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitReaction_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitReaction_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reaction_statementContext reaction_statement() throws RecognitionException {
		Reaction_statementContext _localctx = new Reaction_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_reaction_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			expression();
			setState(79);
			match(ARROW);
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(80);
				match(LPAR);
				setState(81);
				match(PROBABILITY);
				setState(82);
				match(RPAR);
				}
				break;
			}
			setState(85);
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode LSQ() { return getToken(bigraphParser.LSQ, 0); }
		public LinksContext links() {
			return getRuleContext(LinksContext.class,0);
		}
		public TerminalNode RSQ() { return getToken(bigraphParser.RSQ, 0); }
		public RegionsContext regions() {
			return getRuleContext(RegionsContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public TerminalNode DIGIT() { return getToken(bigraphParser.DIGIT, 0); }
		public TerminalNode DOLLAR() { return getToken(bigraphParser.DOLLAR, 0); }
		public TerminalNode LPAR() { return getToken(bigraphParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(bigraphParser.RPAR, 0); }
		public TerminalNode NIL() { return getToken(bigraphParser.NIL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				match(IDENTIFIER);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSQ) {
					{
					setState(88);
					match(LSQ);
					setState(89);
					links();
					setState(90);
					match(RSQ);
					}
				}

				setState(96);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(94);
					regions();
					}
					break;
				case DOT:
					{
					setState(95);
					prefix();
					}
					break;
				case EOF:
				case COMMA:
				case RPAR:
				case ARROW:
				case RULE:
				case MODEL:
				case MARKER:
				case PROPERTIES:
					break;
				default:
					break;
				}
				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(DIGIT);
				setState(101);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(99);
					regions();
					}
					break;
				case DOT:
					{
					setState(100);
					prefix();
					}
					break;
				case EOF:
				case COMMA:
				case RPAR:
				case ARROW:
				case RULE:
				case MODEL:
				case MARKER:
				case PROPERTIES:
					break;
				default:
					break;
				}
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				match(DOLLAR);
				setState(104);
				match(DIGIT);
				setState(107);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(105);
					regions();
					}
					break;
				case DOT:
					{
					setState(106);
					prefix();
					}
					break;
				case EOF:
				case COMMA:
				case RPAR:
				case ARROW:
				case RULE:
				case MODEL:
				case MARKER:
				case PROPERTIES:
					break;
				default:
					break;
				}
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				match(LPAR);
				setState(110);
				expression();
				setState(111);
				match(RPAR);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(112);
					regions();
					}
				}

				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(NIL);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(116);
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
		public TerminalNode LOR() { return getToken(bigraphParser.LOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR() { return getToken(bigraphParser.PAR, 0); }
		public RegionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterRegions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitRegions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitRegions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegionsContext regions() throws RecognitionException {
		RegionsContext _localctx = new RegionsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_regions);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(LOR);
				setState(122);
				expression();
				}
				break;
			case PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(PAR);
				setState(124);
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
		public TerminalNode DOT() { return getToken(bigraphParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_prefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(DOT);
			setState(128);
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
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode COMMA() { return getToken(bigraphParser.COMMA, 0); }
		public LinksContext links() {
			return getRuleContext(LinksContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(bigraphParser.VARIABLE, 0); }
		public TerminalNode UNLINKED() { return getToken(bigraphParser.UNLINKED, 0); }
		public LinksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_links; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterLinks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitLinks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitLinks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinksContext links() throws RecognitionException {
		LinksContext _localctx = new LinksContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_links);
		int _la;
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				match(IDENTIFIER);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(131);
					match(COMMA);
					setState(132);
					links();
					}
				}

				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(VARIABLE);
				setState(136);
				match(IDENTIFIER);
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(137);
					match(COMMA);
					setState(138);
					links();
					}
				}

				}
				break;
			case UNLINKED:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				match(UNLINKED);
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(142);
					match(COMMA);
					setState(143);
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

	public static class ModelContext extends ParserRuleContext {
		public TerminalNode MODEL() { return getToken(bigraphParser.MODEL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(bigraphParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MarkerContext marker() {
			return getRuleContext(MarkerContext.class,0);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_model);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(MODEL);
			setState(149);
			match(IDENTIFIER);
			setState(150);
			match(ASSIGNMENT);
			setState(151);
			expression();
			setState(152);
			marker();
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

	public static class MarkerContext extends ParserRuleContext {
		public TerminalNode MARKER() { return getToken(bigraphParser.MARKER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(bigraphParser.ASSIGNMENT, 0); }
		public Marker_statementContext marker_statement() {
			return getRuleContext(Marker_statementContext.class,0);
		}
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public MarkerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_marker; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterMarker(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitMarker(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitMarker(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MarkerContext marker() throws RecognitionException {
		MarkerContext _localctx = new MarkerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_marker);
		try {
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MARKER:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				match(MARKER);
				setState(155);
				match(IDENTIFIER);
				setState(156);
				match(ASSIGNMENT);
				setState(157);
				marker_statement();
				}
				break;
			case EOF:
			case PROPERTIES:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				property();
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

	public static class Marker_statementContext extends ParserRuleContext {
		public List<Boolean_expressionContext> boolean_expression() {
			return getRuleContexts(Boolean_expressionContext.class);
		}
		public Boolean_expressionContext boolean_expression(int i) {
			return getRuleContext(Boolean_expressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(bigraphParser.AND, 0); }
		public TerminalNode LOR() { return getToken(bigraphParser.LOR, 0); }
		public TerminalNode NOT() { return getToken(bigraphParser.NOT, 0); }
		public List<Marker_statementContext> marker_statement() {
			return getRuleContexts(Marker_statementContext.class);
		}
		public Marker_statementContext marker_statement(int i) {
			return getRuleContext(Marker_statementContext.class,i);
		}
		public TerminalNode IF() { return getToken(bigraphParser.IF, 0); }
		public TerminalNode THEN() { return getToken(bigraphParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(bigraphParser.ELSE, 0); }
		public Marker_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_marker_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterMarker_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitMarker_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitMarker_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Marker_statementContext marker_statement() throws RecognitionException {
		Marker_statementContext _localctx = new Marker_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_marker_statement);
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
			case DOLLAR:
			case DIGIT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(161);
				boolean_expression();
				setState(166);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case AND:
					{
					setState(162);
					match(AND);
					setState(163);
					boolean_expression();
					}
					break;
				case LOR:
					{
					setState(164);
					match(LOR);
					setState(165);
					boolean_expression();
					}
					break;
				case EOF:
				case RPAR:
				case THEN:
				case ELSE:
					break;
				default:
					break;
				}
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(NOT);
				setState(169);
				marker_statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				match(IF);
				setState(171);
				marker_statement();
				setState(172);
				match(THEN);
				setState(173);
				marker_statement();
				setState(174);
				match(ELSE);
				setState(175);
				marker_statement();
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

	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode PROPERTIES() { return getToken(bigraphParser.PROPERTIES, 0); }
		public TerminalNode COLON() { return getToken(bigraphParser.COLON, 0); }
		public Property_statementsContext property_statements() {
			return getRuleContext(Property_statementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(bigraphParser.EOF, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_property);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PROPERTIES:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				match(PROPERTIES);
				setState(180);
				match(COLON);
				setState(181);
				property_statements();
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(EOF);
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

	public static class Property_statementsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(bigraphParser.EOF, 0); }
		public Property_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterProperty_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitProperty_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitProperty_statements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Property_statementsContext property_statements() throws RecognitionException {
		Property_statementsContext _localctx = new Property_statementsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_property_statements);
		try {
			int _alt;
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
			case SEMI:
			case COLON:
			case DOT:
			case ASSIGNMENT:
			case WHITESP:
			case ESCAPE:
			case NEWLINE:
			case LSQ:
			case RSQ:
			case LPAR:
			case RPAR:
			case LOR:
			case PAR:
			case DOLLAR:
			case UNLINKED:
			case ARROW:
			case NIL:
			case COMMENT:
			case LINE_COMMENT:
			case CONTROLS:
			case ACTIVE:
			case PASSIVE:
			case NAMES:
			case INNER:
			case OUTER:
			case RULE:
			case VARIABLE:
			case MODEL:
			case MARKER:
			case PROPERTIES:
			case AND:
			case NOT:
			case IF:
			case THEN:
			case ELSE:
			case LEQ:
			case GEQ:
			case LT:
			case GT:
			case EQ:
			case NEQ:
			case DIGIT:
			case PROBABILITY:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(186); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(185);
						matchWildcard();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(188); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				} while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER );
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				match(EOF);
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

	public static class Boolean_expressionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Binary_operationContext binary_operation() {
			return getRuleContext(Binary_operationContext.class,0);
		}
		public Boolean_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterBoolean_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitBoolean_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitBoolean_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_expressionContext boolean_expression() throws RecognitionException {
		Boolean_expressionContext _localctx = new Boolean_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_boolean_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			term();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEQ) | (1L << GEQ) | (1L << LT) | (1L << GT) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(194);
				binary_operation();
				setState(195);
				term();
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

	public static class Binary_operationContext extends ParserRuleContext {
		public TerminalNode NEQ() { return getToken(bigraphParser.NEQ, 0); }
		public TerminalNode EQ() { return getToken(bigraphParser.EQ, 0); }
		public TerminalNode LEQ() { return getToken(bigraphParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(bigraphParser.GEQ, 0); }
		public TerminalNode LT() { return getToken(bigraphParser.LT, 0); }
		public TerminalNode GT() { return getToken(bigraphParser.GT, 0); }
		public Binary_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterBinary_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitBinary_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitBinary_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operationContext binary_operation() throws RecognitionException {
		Binary_operationContext _localctx = new Binary_operationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_binary_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEQ) | (1L << GEQ) | (1L << LT) | (1L << GT) | (1L << EQ) | (1L << NEQ))) != 0)) ) {
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

	public static class TermContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(bigraphParser.LPAR, 0); }
		public Marker_statementContext marker_statement() {
			return getRuleContext(Marker_statementContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(bigraphParser.RPAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public Parameters_listContext parameters_list() {
			return getRuleContext(Parameters_listContext.class,0);
		}
		public TerminalNode DOLLAR() { return getToken(bigraphParser.DOLLAR, 0); }
		public TerminalNode ARROW() { return getToken(bigraphParser.ARROW, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode DIGIT() { return getToken(bigraphParser.DIGIT, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_term);
		int _la;
		try {
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				match(LPAR);
				setState(202);
				marker_statement();
				setState(203);
				match(RPAR);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				match(IDENTIFIER);
				setState(206);
				match(LPAR);
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << DOLLAR) | (1L << NIL) | (1L << DIGIT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(207);
					parameters_list();
					}
				}

				setState(210);
				match(RPAR);
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				match(DOLLAR);
				setState(212);
				match(IDENTIFIER);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ARROW) {
					{
					setState(213);
					match(ARROW);
					setState(214);
					term();
					}
				}

				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(217);
				match(DIGIT);
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

	public static class Parameters_listContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(bigraphParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(bigraphParser.COMMA, i);
		}
		public Parameters_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterParameters_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitParameters_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitParameters_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameters_listContext parameters_list() throws RecognitionException {
		Parameters_listContext _localctx = new Parameters_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parameters_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			parameter();
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(221);
				match(COMMA);
				setState(222);
				parameter();
				}
				}
				setState(227);
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

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(bigraphParser.DIGIT, 0); }
		public TerminalNode DOLLAR() { return getToken(bigraphParser.DOLLAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(bigraphParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parameter);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(DIGIT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				match(DOLLAR);
				setState(230);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(231);
				expression();
				}
				break;
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u00ed\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\3\3\3\5\3\63"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\5\4:\n\4\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\3\6"+
		"\3\6\5\6F\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\5\bV\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\t_\n\t\3\t\3\t\5\tc\n\t\3\t\3"+
		"\t\3\t\5\th\n\t\3\t\3\t\3\t\3\t\5\tn\n\t\3\t\3\t\3\t\3\t\5\tt\n\t\3\t"+
		"\3\t\5\tx\n\t\5\tz\n\t\3\n\3\n\3\n\3\n\5\n\u0080\n\n\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\5\f\u0088\n\f\3\f\3\f\3\f\3\f\5\f\u008e\n\f\3\f\3\f\3\f\5\f"+
		"\u0093\n\f\5\f\u0095\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\5\16\u00a2\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u00a9\n\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00b4\n\17\3\20\3\20\3\20\3\20"+
		"\5\20\u00ba\n\20\3\21\6\21\u00bd\n\21\r\21\16\21\u00be\3\21\5\21\u00c2"+
		"\n\21\3\22\3\22\3\22\3\22\5\22\u00c8\n\22\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u00d3\n\24\3\24\3\24\3\24\3\24\3\24\5\24\u00da\n"+
		"\24\3\24\5\24\u00dd\n\24\3\25\3\25\3\25\7\25\u00e2\n\25\f\25\16\25\u00e5"+
		"\13\25\3\26\3\26\3\26\3\26\5\26\u00eb\n\26\3\26\3\u00be\2\27\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\5\3\2\30\31\3\2\33\34\3\2\',\2"+
		"\u0101\2,\3\2\2\2\4\62\3\2\2\2\6\64\3\2\2\2\b?\3\2\2\2\nA\3\2\2\2\fN\3"+
		"\2\2\2\16P\3\2\2\2\20y\3\2\2\2\22\177\3\2\2\2\24\u0081\3\2\2\2\26\u0094"+
		"\3\2\2\2\30\u0096\3\2\2\2\32\u00a1\3\2\2\2\34\u00b3\3\2\2\2\36\u00b9\3"+
		"\2\2\2 \u00c1\3\2\2\2\"\u00c3\3\2\2\2$\u00c9\3\2\2\2&\u00dc\3\2\2\2(\u00de"+
		"\3\2\2\2*\u00ea\3\2\2\2,-\5\4\3\2-\3\3\2\2\2./\7\27\2\2/\60\7\5\2\2\60"+
		"\63\5\6\4\2\61\63\5\b\5\2\62.\3\2\2\2\62\61\3\2\2\2\63\5\3\2\2\2\64\65"+
		"\t\2\2\2\65\66\7/\2\2\669\7-\2\2\67:\5\6\4\28:\5\b\5\29\67\3\2\2\298\3"+
		"\2\2\2:\7\3\2\2\2;<\7\32\2\2<=\7\5\2\2=@\5\n\6\2>@\5\f\7\2?;\3\2\2\2?"+
		">\3\2\2\2@\t\3\2\2\2AB\t\3\2\2BE\7/\2\2CF\5\n\6\2DF\5\f\7\2EC\3\2\2\2"+
		"ED\3\2\2\2F\13\3\2\2\2GH\7\35\2\2HI\7/\2\2IJ\7\7\2\2JK\5\16\b\2KL\5\f"+
		"\7\2LO\3\2\2\2MO\5\30\r\2NG\3\2\2\2NM\3\2\2\2O\r\3\2\2\2PQ\5\20\t\2QU"+
		"\7\23\2\2RS\7\r\2\2ST\7.\2\2TV\7\16\2\2UR\3\2\2\2UV\3\2\2\2VW\3\2\2\2"+
		"WX\5\20\t\2X\17\3\2\2\2Y^\7/\2\2Z[\7\13\2\2[\\\5\26\f\2\\]\7\f\2\2]_\3"+
		"\2\2\2^Z\3\2\2\2^_\3\2\2\2_b\3\2\2\2`c\5\22\n\2ac\5\24\13\2b`\3\2\2\2"+
		"ba\3\2\2\2bc\3\2\2\2cz\3\2\2\2dg\7-\2\2eh\5\22\n\2fh\5\24\13\2ge\3\2\2"+
		"\2gf\3\2\2\2gh\3\2\2\2hz\3\2\2\2ij\7\21\2\2jm\7-\2\2kn\5\22\n\2ln\5\24"+
		"\13\2mk\3\2\2\2ml\3\2\2\2mn\3\2\2\2nz\3\2\2\2op\7\r\2\2pq\5\20\t\2qs\7"+
		"\16\2\2rt\5\22\n\2sr\3\2\2\2st\3\2\2\2tz\3\2\2\2uw\7\24\2\2vx\5\22\n\2"+
		"wv\3\2\2\2wx\3\2\2\2xz\3\2\2\2yY\3\2\2\2yd\3\2\2\2yi\3\2\2\2yo\3\2\2\2"+
		"yu\3\2\2\2z\21\3\2\2\2{|\7\17\2\2|\u0080\5\20\t\2}~\7\20\2\2~\u0080\5"+
		"\20\t\2\177{\3\2\2\2\177}\3\2\2\2\u0080\23\3\2\2\2\u0081\u0082\7\6\2\2"+
		"\u0082\u0083\5\20\t\2\u0083\25\3\2\2\2\u0084\u0087\7/\2\2\u0085\u0086"+
		"\7\3\2\2\u0086\u0088\5\26\f\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u0095\3\2\2\2\u0089\u008a\7\36\2\2\u008a\u008d\7/\2\2\u008b\u008c"+
		"\7\3\2\2\u008c\u008e\5\26\f\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2"+
		"\u008e\u0095\3\2\2\2\u008f\u0092\7\22\2\2\u0090\u0091\7\3\2\2\u0091\u0093"+
		"\5\26\f\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2"+
		"\u0094\u0084\3\2\2\2\u0094\u0089\3\2\2\2\u0094\u008f\3\2\2\2\u0095\27"+
		"\3\2\2\2\u0096\u0097\7\37\2\2\u0097\u0098\7/\2\2\u0098\u0099\7\7\2\2\u0099"+
		"\u009a\5\20\t\2\u009a\u009b\5\32\16\2\u009b\31\3\2\2\2\u009c\u009d\7 "+
		"\2\2\u009d\u009e\7/\2\2\u009e\u009f\7\7\2\2\u009f\u00a2\5\34\17\2\u00a0"+
		"\u00a2\5\36\20\2\u00a1\u009c\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\33\3\2"+
		"\2\2\u00a3\u00a8\5\"\22\2\u00a4\u00a5\7\"\2\2\u00a5\u00a9\5\"\22\2\u00a6"+
		"\u00a7\7\17\2\2\u00a7\u00a9\5\"\22\2\u00a8\u00a4\3\2\2\2\u00a8\u00a6\3"+
		"\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b4\3\2\2\2\u00aa\u00ab\7#\2\2\u00ab"+
		"\u00b4\5\34\17\2\u00ac\u00ad\7$\2\2\u00ad\u00ae\5\34\17\2\u00ae\u00af"+
		"\7%\2\2\u00af\u00b0\5\34\17\2\u00b0\u00b1\7&\2\2\u00b1\u00b2\5\34\17\2"+
		"\u00b2\u00b4\3\2\2\2\u00b3\u00a3\3\2\2\2\u00b3\u00aa\3\2\2\2\u00b3\u00ac"+
		"\3\2\2\2\u00b4\35\3\2\2\2\u00b5\u00b6\7!\2\2\u00b6\u00b7\7\5\2\2\u00b7"+
		"\u00ba\5 \21\2\u00b8\u00ba\7\2\2\3\u00b9\u00b5\3\2\2\2\u00b9\u00b8\3\2"+
		"\2\2\u00ba\37\3\2\2\2\u00bb\u00bd\13\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0"+
		"\u00c2\7\2\2\3\u00c1\u00bc\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2!\3\2\2\2"+
		"\u00c3\u00c7\5&\24\2\u00c4\u00c5\5$\23\2\u00c5\u00c6\5&\24\2\u00c6\u00c8"+
		"\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8#\3\2\2\2\u00c9"+
		"\u00ca\t\4\2\2\u00ca%\3\2\2\2\u00cb\u00cc\7\r\2\2\u00cc\u00cd\5\34\17"+
		"\2\u00cd\u00ce\7\16\2\2\u00ce\u00dd\3\2\2\2\u00cf\u00d0\7/\2\2\u00d0\u00d2"+
		"\7\r\2\2\u00d1\u00d3\5(\25\2\u00d2\u00d1\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00dd\7\16\2\2\u00d5\u00d6\7\21\2\2\u00d6\u00d9\7"+
		"/\2\2\u00d7\u00d8\7\23\2\2\u00d8\u00da\5&\24\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00dd\7-\2\2\u00dc\u00cb\3\2"+
		"\2\2\u00dc\u00cf\3\2\2\2\u00dc\u00d5\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd"+
		"\'\3\2\2\2\u00de\u00e3\5*\26\2\u00df\u00e0\7\3\2\2\u00e0\u00e2\5*\26\2"+
		"\u00e1\u00df\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4"+
		"\3\2\2\2\u00e4)\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00eb\7-\2\2\u00e7\u00e8"+
		"\7\21\2\2\u00e8\u00eb\7/\2\2\u00e9\u00eb\5\20\t\2\u00ea\u00e6\3\2\2\2"+
		"\u00ea\u00e7\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb+\3\2\2\2 \629?ENU^bgms"+
		"wy\177\u0087\u008d\u0092\u0094\u00a1\u00a8\u00b3\u00b9\u00be\u00c1\u00c7"+
		"\u00d2\u00d9\u00dc\u00e3\u00ea";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}