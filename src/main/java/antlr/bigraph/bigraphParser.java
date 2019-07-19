// Generated from C:/Users/Utente/Documents/GitHub/Synkrisis/src/main/java/antlr/g4models\bigraph.g4 by ANTLR 4.7.2
package antlr.bigraph;

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
		GEQ=38, LT=39, GT=40, EQ=41, NEQ=42, SLASH=43, QUOTE=44, QUESTION=45, 
		PRODUCT=46, ADDITION=47, CONJUNCTION=48, PRISM=49, SPOT=50, ACCEPTANCE=51, 
		ACCNAME=52, FIN=53, INF=54, SPOT_TRUE=55, SPOT_FALSE=56, DIGIT=57, PROBABILITY=58, 
		SPOT_IDENTIFIER=59, IDENTIFIER=60;
	public static final int
		RULE_bigraph = 0, RULE_controls = 1, RULE_control_statements = 2, RULE_names = 3, 
		RULE_name_statements = 4, RULE_reactions = 5, RULE_reaction_statement = 6, 
		RULE_expression = 7, RULE_regions = 8, RULE_prefix = 9, RULE_links = 10, 
		RULE_model = 11, RULE_marker = 12, RULE_marker_statement = 13, RULE_properties = 14, 
		RULE_spot_statement = 15, RULE_acc_name = 16, RULE_acceptance = 17, RULE_acceptance_cond1 = 18, 
		RULE_acceptance_cond2 = 19, RULE_prism_properties = 20, RULE_prism_statements = 21, 
		RULE_boolean_expression = 22, RULE_binary_operation = 23, RULE_term = 24, 
		RULE_parameters_list = 25, RULE_parameter = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"bigraph", "controls", "control_statements", "names", "name_statements", 
			"reactions", "reaction_statement", "expression", "regions", "prefix", 
			"links", "model", "marker", "marker_statement", "properties", "spot_statement", 
			"acc_name", "acceptance", "acceptance_cond1", "acceptance_cond2", "prism_properties", 
			"prism_statements", "boolean_expression", "binary_operation", "term", 
			"parameters_list", "parameter"
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
			setState(54);
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
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONTROLS:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(CONTROLS);
				setState(57);
				match(COLON);
				setState(58);
				control_statements();
				}
				break;
			case NAMES:
			case RULE:
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
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
			setState(62);
			_la = _input.LA(1);
			if ( !(_la==ACTIVE || _la==PASSIVE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(63);
			match(IDENTIFIER);
			setState(64);
			match(DIGIT);
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACTIVE:
			case PASSIVE:
				{
				setState(65);
				control_statements();
				}
				break;
			case NAMES:
			case RULE:
			case MODEL:
				{
				setState(66);
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
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(NAMES);
				setState(70);
				match(COLON);
				setState(71);
				name_statements();
				}
				break;
			case RULE:
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
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
			setState(75);
			_la = _input.LA(1);
			if ( !(_la==INNER || _la==OUTER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(76);
			match(IDENTIFIER);
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INNER:
			case OUTER:
				{
				setState(77);
				name_statements();
				}
				break;
			case RULE:
			case MODEL:
				{
				setState(78);
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
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(RULE);
				setState(82);
				match(IDENTIFIER);
				setState(83);
				match(ASSIGNMENT);
				setState(84);
				reaction_statement();
				setState(85);
				reactions();
				}
				break;
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
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
			setState(90);
			expression();
			setState(91);
			match(ARROW);
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(92);
				match(LPAR);
				setState(93);
				match(PROBABILITY);
				setState(94);
				match(RPAR);
				}
				break;
			}
			setState(97);
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
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				match(IDENTIFIER);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSQ) {
					{
					setState(100);
					match(LSQ);
					setState(101);
					links();
					setState(102);
					match(RSQ);
					}
				}

				setState(108);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(106);
					regions();
					}
					break;
				case DOT:
					{
					setState(107);
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
				case PRISM:
				case SPOT:
					break;
				default:
					break;
				}
				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				match(DIGIT);
				setState(113);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(111);
					regions();
					}
					break;
				case DOT:
					{
					setState(112);
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
				case PRISM:
				case SPOT:
					break;
				default:
					break;
				}
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(DOLLAR);
				setState(116);
				match(DIGIT);
				setState(119);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(117);
					regions();
					}
					break;
				case DOT:
					{
					setState(118);
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
				case PRISM:
				case SPOT:
					break;
				default:
					break;
				}
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(121);
				match(LPAR);
				setState(122);
				expression();
				setState(123);
				match(RPAR);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(124);
					regions();
					}
				}

				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 5);
				{
				setState(127);
				match(NIL);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(128);
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
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				match(LOR);
				setState(134);
				expression();
				}
				break;
			case PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				match(PAR);
				setState(136);
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
			setState(139);
			match(DOT);
			setState(140);
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
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				match(IDENTIFIER);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(143);
					match(COMMA);
					setState(144);
					links();
					}
				}

				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				match(VARIABLE);
				setState(148);
				match(IDENTIFIER);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(149);
					match(COMMA);
					setState(150);
					links();
					}
				}

				}
				break;
			case UNLINKED:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(UNLINKED);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(154);
					match(COMMA);
					setState(155);
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
			setState(160);
			match(MODEL);
			setState(161);
			match(IDENTIFIER);
			setState(162);
			match(ASSIGNMENT);
			setState(163);
			expression();
			setState(164);
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
		public MarkerContext marker() {
			return getRuleContext(MarkerContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
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
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MARKER:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				match(MARKER);
				setState(167);
				match(IDENTIFIER);
				setState(168);
				match(ASSIGNMENT);
				setState(169);
				marker_statement();
				setState(170);
				marker();
				}
				break;
			case EOF:
			case PROPERTIES:
			case PRISM:
			case SPOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				properties();
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
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
			case DOLLAR:
			case DIGIT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				boolean_expression();
				setState(180);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case AND:
					{
					setState(176);
					match(AND);
					setState(177);
					boolean_expression();
					}
					break;
				case LOR:
					{
					setState(178);
					match(LOR);
					setState(179);
					boolean_expression();
					}
					break;
				case EOF:
				case RPAR:
				case MARKER:
				case PROPERTIES:
				case THEN:
				case ELSE:
				case PRISM:
				case SPOT:
					break;
				default:
					break;
				}
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				match(NOT);
				setState(183);
				marker_statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
				match(IF);
				setState(185);
				marker_statement();
				setState(186);
				match(THEN);
				setState(187);
				marker_statement();
				setState(188);
				match(ELSE);
				setState(189);
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

	public static class PropertiesContext extends ParserRuleContext {
		public TerminalNode PROPERTIES() { return getToken(bigraphParser.PROPERTIES, 0); }
		public TerminalNode COLON() { return getToken(bigraphParser.COLON, 0); }
		public Spot_statementContext spot_statement() {
			return getRuleContext(Spot_statementContext.class,0);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_properties);
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PROPERTIES:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(PROPERTIES);
				setState(194);
				match(COLON);
				setState(195);
				spot_statement();
				}
				break;
			case EOF:
			case PRISM:
			case SPOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				spot_statement();
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

	public static class Spot_statementContext extends ParserRuleContext {
		public TerminalNode SPOT() { return getToken(bigraphParser.SPOT, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(bigraphParser.ASSIGNMENT, 0); }
		public AcceptanceContext acceptance() {
			return getRuleContext(AcceptanceContext.class,0);
		}
		public Prism_propertiesContext prism_properties() {
			return getRuleContext(Prism_propertiesContext.class,0);
		}
		public Acc_nameContext acc_name() {
			return getRuleContext(Acc_nameContext.class,0);
		}
		public Spot_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spot_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterSpot_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitSpot_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitSpot_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Spot_statementContext spot_statement() throws RecognitionException {
		Spot_statementContext _localctx = new Spot_statementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_spot_statement);
		int _la;
		try {
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SPOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(SPOT);
				setState(200);
				match(ASSIGNMENT);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ACCNAME) {
					{
					setState(201);
					acc_name();
					}
				}

				setState(204);
				acceptance();
				setState(205);
				prism_properties();
				}
				break;
			case EOF:
			case PRISM:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				prism_properties();
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

	public static class Acc_nameContext extends ParserRuleContext {
		public TerminalNode ACCNAME() { return getToken(bigraphParser.ACCNAME, 0); }
		public TerminalNode COLON() { return getToken(bigraphParser.COLON, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(bigraphParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(bigraphParser.IDENTIFIER, i);
		}
		public TerminalNode SPOT_IDENTIFIER() { return getToken(bigraphParser.SPOT_IDENTIFIER, 0); }
		public List<TerminalNode> DIGIT() { return getTokens(bigraphParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(bigraphParser.DIGIT, i);
		}
		public Acc_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acc_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterAcc_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitAcc_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitAcc_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Acc_nameContext acc_name() throws RecognitionException {
		Acc_nameContext _localctx = new Acc_nameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_acc_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(ACCNAME);
			setState(211);
			match(COLON);
			setState(212);
			_la = _input.LA(1);
			if ( !(_la==SPOT_IDENTIFIER || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(214); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(213);
				_la = _input.LA(1);
				if ( !(_la==DIGIT || _la==IDENTIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(216); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT || _la==IDENTIFIER );
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

	public static class AcceptanceContext extends ParserRuleContext {
		public TerminalNode ACCEPTANCE() { return getToken(bigraphParser.ACCEPTANCE, 0); }
		public TerminalNode COLON() { return getToken(bigraphParser.COLON, 0); }
		public TerminalNode DIGIT() { return getToken(bigraphParser.DIGIT, 0); }
		public Acceptance_cond1Context acceptance_cond1() {
			return getRuleContext(Acceptance_cond1Context.class,0);
		}
		public AcceptanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acceptance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterAcceptance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitAcceptance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitAcceptance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcceptanceContext acceptance() throws RecognitionException {
		AcceptanceContext _localctx = new AcceptanceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_acceptance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(ACCEPTANCE);
			setState(219);
			match(COLON);
			setState(220);
			match(DIGIT);
			setState(221);
			acceptance_cond1();
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

	public static class Acceptance_cond1Context extends ParserRuleContext {
		public Acceptance_cond2Context acceptance_cond2() {
			return getRuleContext(Acceptance_cond2Context.class,0);
		}
		public List<Acceptance_cond1Context> acceptance_cond1() {
			return getRuleContexts(Acceptance_cond1Context.class);
		}
		public Acceptance_cond1Context acceptance_cond1(int i) {
			return getRuleContext(Acceptance_cond1Context.class,i);
		}
		public TerminalNode CONJUNCTION() { return getToken(bigraphParser.CONJUNCTION, 0); }
		public TerminalNode PAR() { return getToken(bigraphParser.PAR, 0); }
		public TerminalNode LPAR() { return getToken(bigraphParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(bigraphParser.RPAR, 0); }
		public Acceptance_cond1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acceptance_cond1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterAcceptance_cond1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitAcceptance_cond1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitAcceptance_cond1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Acceptance_cond1Context acceptance_cond1() throws RecognitionException {
		Acceptance_cond1Context _localctx = new Acceptance_cond1Context(_ctx, getState());
		enterRule(_localctx, 36, RULE_acceptance_cond1);
		int _la;
		try {
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FIN:
			case INF:
			case SPOT_TRUE:
			case SPOT_FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				acceptance_cond2();
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PAR || _la==CONJUNCTION) {
					{
					setState(224);
					_la = _input.LA(1);
					if ( !(_la==PAR || _la==CONJUNCTION) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(225);
					acceptance_cond1();
					}
				}

				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				match(LPAR);
				setState(229);
				acceptance_cond1();
				setState(230);
				match(RPAR);
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PAR || _la==CONJUNCTION) {
					{
					setState(231);
					_la = _input.LA(1);
					if ( !(_la==PAR || _la==CONJUNCTION) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(232);
					acceptance_cond1();
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

	public static class Acceptance_cond2Context extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(bigraphParser.LPAR, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(bigraphParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(bigraphParser.IDENTIFIER, i);
		}
		public TerminalNode RPAR() { return getToken(bigraphParser.RPAR, 0); }
		public TerminalNode FIN() { return getToken(bigraphParser.FIN, 0); }
		public TerminalNode INF() { return getToken(bigraphParser.INF, 0); }
		public List<TerminalNode> NOT() { return getTokens(bigraphParser.NOT); }
		public TerminalNode NOT(int i) {
			return getToken(bigraphParser.NOT, i);
		}
		public List<TerminalNode> CONJUNCTION() { return getTokens(bigraphParser.CONJUNCTION); }
		public TerminalNode CONJUNCTION(int i) {
			return getToken(bigraphParser.CONJUNCTION, i);
		}
		public TerminalNode SPOT_TRUE() { return getToken(bigraphParser.SPOT_TRUE, 0); }
		public TerminalNode SPOT_FALSE() { return getToken(bigraphParser.SPOT_FALSE, 0); }
		public Acceptance_cond2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acceptance_cond2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterAcceptance_cond2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitAcceptance_cond2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitAcceptance_cond2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Acceptance_cond2Context acceptance_cond2() throws RecognitionException {
		Acceptance_cond2Context _localctx = new Acceptance_cond2Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_acceptance_cond2);
		int _la;
		try {
			setState(256);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FIN:
			case INF:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				_la = _input.LA(1);
				if ( !(_la==FIN || _la==INF) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(238);
				match(LPAR);
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(239);
					match(NOT);
					}
				}

				setState(242);
				match(IDENTIFIER);
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CONJUNCTION) {
					{
					{
					setState(243);
					match(CONJUNCTION);
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NOT) {
						{
						setState(244);
						match(NOT);
						}
					}

					setState(247);
					match(IDENTIFIER);
					}
					}
					setState(252);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(253);
				match(RPAR);
				}
				break;
			case SPOT_TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(SPOT_TRUE);
				}
				break;
			case SPOT_FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(255);
				match(SPOT_FALSE);
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

	public static class Prism_propertiesContext extends ParserRuleContext {
		public TerminalNode PRISM() { return getToken(bigraphParser.PRISM, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(bigraphParser.ASSIGNMENT, 0); }
		public Prism_statementsContext prism_statements() {
			return getRuleContext(Prism_statementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(bigraphParser.EOF, 0); }
		public Prism_propertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prism_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterPrism_properties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitPrism_properties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitPrism_properties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Prism_propertiesContext prism_properties() throws RecognitionException {
		Prism_propertiesContext _localctx = new Prism_propertiesContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_prism_properties);
		try {
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRISM:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				match(PRISM);
				setState(259);
				match(ASSIGNMENT);
				setState(260);
				prism_statements();
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
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

	public static class Prism_statementsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(bigraphParser.EOF, 0); }
		public Prism_statementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prism_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).enterPrism_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bigraphListener ) ((bigraphListener)listener).exitPrism_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bigraphVisitor ) return ((bigraphVisitor<? extends T>)visitor).visitPrism_statements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Prism_statementsContext prism_statements() throws RecognitionException {
		Prism_statementsContext _localctx = new Prism_statementsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_prism_statements);
		int _la;
		try {
			setState(271);
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
			case SLASH:
			case QUOTE:
			case QUESTION:
			case PRODUCT:
			case ADDITION:
			case CONJUNCTION:
			case PRISM:
			case SPOT:
			case ACCEPTANCE:
			case ACCNAME:
			case FIN:
			case INF:
			case SPOT_TRUE:
			case SPOT_FALSE:
			case DIGIT:
			case PROBABILITY:
			case SPOT_IDENTIFIER:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(265); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(264);
					matchWildcard();
					}
					}
					setState(267); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << SEMI) | (1L << COLON) | (1L << DOT) | (1L << ASSIGNMENT) | (1L << WHITESP) | (1L << ESCAPE) | (1L << NEWLINE) | (1L << LSQ) | (1L << RSQ) | (1L << LPAR) | (1L << RPAR) | (1L << LOR) | (1L << PAR) | (1L << DOLLAR) | (1L << UNLINKED) | (1L << ARROW) | (1L << NIL) | (1L << COMMENT) | (1L << LINE_COMMENT) | (1L << CONTROLS) | (1L << ACTIVE) | (1L << PASSIVE) | (1L << NAMES) | (1L << INNER) | (1L << OUTER) | (1L << RULE) | (1L << VARIABLE) | (1L << MODEL) | (1L << MARKER) | (1L << PROPERTIES) | (1L << AND) | (1L << NOT) | (1L << IF) | (1L << THEN) | (1L << ELSE) | (1L << LEQ) | (1L << GEQ) | (1L << LT) | (1L << GT) | (1L << EQ) | (1L << NEQ) | (1L << SLASH) | (1L << QUOTE) | (1L << QUESTION) | (1L << PRODUCT) | (1L << ADDITION) | (1L << CONJUNCTION) | (1L << PRISM) | (1L << SPOT) | (1L << ACCEPTANCE) | (1L << ACCNAME) | (1L << FIN) | (1L << INF) | (1L << SPOT_TRUE) | (1L << SPOT_FALSE) | (1L << DIGIT) | (1L << PROBABILITY) | (1L << SPOT_IDENTIFIER) | (1L << IDENTIFIER))) != 0) );
				}
				setState(269);
				match(EOF);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
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
		enterRule(_localctx, 44, RULE_boolean_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			term();
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEQ) | (1L << GEQ) | (1L << LT) | (1L << GT) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(274);
				binary_operation();
				setState(275);
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
		enterRule(_localctx, 46, RULE_binary_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
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
		enterRule(_localctx, 48, RULE_term);
		int _la;
		try {
			setState(298);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				match(LPAR);
				setState(282);
				marker_statement();
				setState(283);
				match(RPAR);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(IDENTIFIER);
				setState(286);
				match(LPAR);
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << DOLLAR) | (1L << NIL) | (1L << DIGIT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(287);
					parameters_list();
					}
				}

				setState(290);
				match(RPAR);
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(291);
				match(DOLLAR);
				setState(292);
				match(IDENTIFIER);
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ARROW) {
					{
					setState(293);
					match(ARROW);
					setState(294);
					term();
					}
				}

				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(297);
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
		enterRule(_localctx, 50, RULE_parameters_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			parameter();
			setState(305);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(301);
				match(COMMA);
				setState(302);
				parameter();
				}
				}
				setState(307);
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
		enterRule(_localctx, 52, RULE_parameter);
		try {
			setState(312);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				match(DIGIT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(309);
				match(DOLLAR);
				setState(310);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(311);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3>\u013d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\3\3\3\5\3?\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\5\4F\n\4\3\5\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\3\6\5\6R"+
		"\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7[\n\7\3\b\3\b\3\b\3\b\3\b\5\bb\n\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\tk\n\t\3\t\3\t\5\to\n\t\3\t\3\t\3\t\5\t"+
		"t\n\t\3\t\3\t\3\t\3\t\5\tz\n\t\3\t\3\t\3\t\3\t\5\t\u0080\n\t\3\t\3\t\5"+
		"\t\u0084\n\t\5\t\u0086\n\t\3\n\3\n\3\n\3\n\5\n\u008c\n\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\5\f\u0094\n\f\3\f\3\f\3\f\3\f\5\f\u009a\n\f\3\f\3\f\3\f\5"+
		"\f\u009f\n\f\5\f\u00a1\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\5\16\u00b0\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u00b7\n"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00c2\n\17\3\20"+
		"\3\20\3\20\3\20\5\20\u00c8\n\20\3\21\3\21\3\21\5\21\u00cd\n\21\3\21\3"+
		"\21\3\21\3\21\5\21\u00d3\n\21\3\22\3\22\3\22\3\22\6\22\u00d9\n\22\r\22"+
		"\16\22\u00da\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\5\24\u00e5\n\24\3"+
		"\24\3\24\3\24\3\24\3\24\5\24\u00ec\n\24\5\24\u00ee\n\24\3\25\3\25\3\25"+
		"\5\25\u00f3\n\25\3\25\3\25\3\25\5\25\u00f8\n\25\3\25\7\25\u00fb\n\25\f"+
		"\25\16\25\u00fe\13\25\3\25\3\25\3\25\5\25\u0103\n\25\3\26\3\26\3\26\3"+
		"\26\5\26\u0109\n\26\3\27\6\27\u010c\n\27\r\27\16\27\u010d\3\27\3\27\5"+
		"\27\u0112\n\27\3\30\3\30\3\30\3\30\5\30\u0118\n\30\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\5\32\u0123\n\32\3\32\3\32\3\32\3\32\3\32\5\32"+
		"\u012a\n\32\3\32\5\32\u012d\n\32\3\33\3\33\3\33\7\33\u0132\n\33\f\33\16"+
		"\33\u0135\13\33\3\34\3\34\3\34\3\34\5\34\u013b\n\34\3\34\2\2\35\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\t\3\2\30\31\3"+
		"\2\33\34\3\2=>\4\2;;>>\4\2\20\20\62\62\3\2\678\3\2\',\2\u0157\28\3\2\2"+
		"\2\4>\3\2\2\2\6@\3\2\2\2\bK\3\2\2\2\nM\3\2\2\2\fZ\3\2\2\2\16\\\3\2\2\2"+
		"\20\u0085\3\2\2\2\22\u008b\3\2\2\2\24\u008d\3\2\2\2\26\u00a0\3\2\2\2\30"+
		"\u00a2\3\2\2\2\32\u00af\3\2\2\2\34\u00c1\3\2\2\2\36\u00c7\3\2\2\2 \u00d2"+
		"\3\2\2\2\"\u00d4\3\2\2\2$\u00dc\3\2\2\2&\u00ed\3\2\2\2(\u0102\3\2\2\2"+
		"*\u0108\3\2\2\2,\u0111\3\2\2\2.\u0113\3\2\2\2\60\u0119\3\2\2\2\62\u012c"+
		"\3\2\2\2\64\u012e\3\2\2\2\66\u013a\3\2\2\289\5\4\3\29\3\3\2\2\2:;\7\27"+
		"\2\2;<\7\5\2\2<?\5\6\4\2=?\5\b\5\2>:\3\2\2\2>=\3\2\2\2?\5\3\2\2\2@A\t"+
		"\2\2\2AB\7>\2\2BE\7;\2\2CF\5\6\4\2DF\5\b\5\2EC\3\2\2\2ED\3\2\2\2F\7\3"+
		"\2\2\2GH\7\32\2\2HI\7\5\2\2IL\5\n\6\2JL\5\f\7\2KG\3\2\2\2KJ\3\2\2\2L\t"+
		"\3\2\2\2MN\t\3\2\2NQ\7>\2\2OR\5\n\6\2PR\5\f\7\2QO\3\2\2\2QP\3\2\2\2R\13"+
		"\3\2\2\2ST\7\35\2\2TU\7>\2\2UV\7\7\2\2VW\5\16\b\2WX\5\f\7\2X[\3\2\2\2"+
		"Y[\5\30\r\2ZS\3\2\2\2ZY\3\2\2\2[\r\3\2\2\2\\]\5\20\t\2]a\7\23\2\2^_\7"+
		"\r\2\2_`\7<\2\2`b\7\16\2\2a^\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\5\20\t\2d\17"+
		"\3\2\2\2ej\7>\2\2fg\7\13\2\2gh\5\26\f\2hi\7\f\2\2ik\3\2\2\2jf\3\2\2\2"+
		"jk\3\2\2\2kn\3\2\2\2lo\5\22\n\2mo\5\24\13\2nl\3\2\2\2nm\3\2\2\2no\3\2"+
		"\2\2o\u0086\3\2\2\2ps\7;\2\2qt\5\22\n\2rt\5\24\13\2sq\3\2\2\2sr\3\2\2"+
		"\2st\3\2\2\2t\u0086\3\2\2\2uv\7\21\2\2vy\7;\2\2wz\5\22\n\2xz\5\24\13\2"+
		"yw\3\2\2\2yx\3\2\2\2yz\3\2\2\2z\u0086\3\2\2\2{|\7\r\2\2|}\5\20\t\2}\177"+
		"\7\16\2\2~\u0080\5\22\n\2\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0086\3"+
		"\2\2\2\u0081\u0083\7\24\2\2\u0082\u0084\5\22\n\2\u0083\u0082\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085e\3\2\2\2\u0085p\3\2\2\2\u0085"+
		"u\3\2\2\2\u0085{\3\2\2\2\u0085\u0081\3\2\2\2\u0086\21\3\2\2\2\u0087\u0088"+
		"\7\17\2\2\u0088\u008c\5\20\t\2\u0089\u008a\7\20\2\2\u008a\u008c\5\20\t"+
		"\2\u008b\u0087\3\2\2\2\u008b\u0089\3\2\2\2\u008c\23\3\2\2\2\u008d\u008e"+
		"\7\6\2\2\u008e\u008f\5\20\t\2\u008f\25\3\2\2\2\u0090\u0093\7>\2\2\u0091"+
		"\u0092\7\3\2\2\u0092\u0094\5\26\f\2\u0093\u0091\3\2\2\2\u0093\u0094\3"+
		"\2\2\2\u0094\u00a1\3\2\2\2\u0095\u0096\7\36\2\2\u0096\u0099\7>\2\2\u0097"+
		"\u0098\7\3\2\2\u0098\u009a\5\26\f\2\u0099\u0097\3\2\2\2\u0099\u009a\3"+
		"\2\2\2\u009a\u00a1\3\2\2\2\u009b\u009e\7\22\2\2\u009c\u009d\7\3\2\2\u009d"+
		"\u009f\5\26\f\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3"+
		"\2\2\2\u00a0\u0090\3\2\2\2\u00a0\u0095\3\2\2\2\u00a0\u009b\3\2\2\2\u00a1"+
		"\27\3\2\2\2\u00a2\u00a3\7\37\2\2\u00a3\u00a4\7>\2\2\u00a4\u00a5\7\7\2"+
		"\2\u00a5\u00a6\5\20\t\2\u00a6\u00a7\5\32\16\2\u00a7\31\3\2\2\2\u00a8\u00a9"+
		"\7 \2\2\u00a9\u00aa\7>\2\2\u00aa\u00ab\7\7\2\2\u00ab\u00ac\5\34\17\2\u00ac"+
		"\u00ad\5\32\16\2\u00ad\u00b0\3\2\2\2\u00ae\u00b0\5\36\20\2\u00af\u00a8"+
		"\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\33\3\2\2\2\u00b1\u00b6\5.\30\2\u00b2"+
		"\u00b3\7\"\2\2\u00b3\u00b7\5.\30\2\u00b4\u00b5\7\17\2\2\u00b5\u00b7\5"+
		".\30\2\u00b6\u00b2\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00c2\3\2\2\2\u00b8\u00b9\7#\2\2\u00b9\u00c2\5\34\17\2\u00ba\u00bb\7"+
		"$\2\2\u00bb\u00bc\5\34\17\2\u00bc\u00bd\7%\2\2\u00bd\u00be\5\34\17\2\u00be"+
		"\u00bf\7&\2\2\u00bf\u00c0\5\34\17\2\u00c0\u00c2\3\2\2\2\u00c1\u00b1\3"+
		"\2\2\2\u00c1\u00b8\3\2\2\2\u00c1\u00ba\3\2\2\2\u00c2\35\3\2\2\2\u00c3"+
		"\u00c4\7!\2\2\u00c4\u00c5\7\5\2\2\u00c5\u00c8\5 \21\2\u00c6\u00c8\5 \21"+
		"\2\u00c7\u00c3\3\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\37\3\2\2\2\u00c9\u00ca"+
		"\7\64\2\2\u00ca\u00cc\7\7\2\2\u00cb\u00cd\5\"\22\2\u00cc\u00cb\3\2\2\2"+
		"\u00cc\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\5$\23\2\u00cf\u00d0"+
		"\5*\26\2\u00d0\u00d3\3\2\2\2\u00d1\u00d3\5*\26\2\u00d2\u00c9\3\2\2\2\u00d2"+
		"\u00d1\3\2\2\2\u00d3!\3\2\2\2\u00d4\u00d5\7\66\2\2\u00d5\u00d6\7\5\2\2"+
		"\u00d6\u00d8\t\4\2\2\u00d7\u00d9\t\5\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db#\3\2\2\2\u00dc"+
		"\u00dd\7\65\2\2\u00dd\u00de\7\5\2\2\u00de\u00df\7;\2\2\u00df\u00e0\5&"+
		"\24\2\u00e0%\3\2\2\2\u00e1\u00e4\5(\25\2\u00e2\u00e3\t\6\2\2\u00e3\u00e5"+
		"\5&\24\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00ee\3\2\2\2\u00e6"+
		"\u00e7\7\r\2\2\u00e7\u00e8\5&\24\2\u00e8\u00eb\7\16\2\2\u00e9\u00ea\t"+
		"\6\2\2\u00ea\u00ec\5&\24\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ee\3\2\2\2\u00ed\u00e1\3\2\2\2\u00ed\u00e6\3\2\2\2\u00ee\'\3\2\2\2"+
		"\u00ef\u00f0\t\7\2\2\u00f0\u00f2\7\r\2\2\u00f1\u00f3\7#\2\2\u00f2\u00f1"+
		"\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00fc\7>\2\2\u00f5"+
		"\u00f7\7\62\2\2\u00f6\u00f8\7#\2\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb\7>\2\2\u00fa\u00f5\3\2\2\2\u00fb"+
		"\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2"+
		"\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0103\7\16\2\2\u0100\u0103\79\2\2\u0101"+
		"\u0103\7:\2\2\u0102\u00ef\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2"+
		"\2\2\u0103)\3\2\2\2\u0104\u0105\7\63\2\2\u0105\u0106\7\7\2\2\u0106\u0109"+
		"\5,\27\2\u0107\u0109\7\2\2\3\u0108\u0104\3\2\2\2\u0108\u0107\3\2\2\2\u0109"+
		"+\3\2\2\2\u010a\u010c\13\2\2\2\u010b\u010a\3\2\2\2\u010c\u010d\3\2\2\2"+
		"\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0112"+
		"\7\2\2\3\u0110\u0112\7\2\2\3\u0111\u010b\3\2\2\2\u0111\u0110\3\2\2\2\u0112"+
		"-\3\2\2\2\u0113\u0117\5\62\32\2\u0114\u0115\5\60\31\2\u0115\u0116\5\62"+
		"\32\2\u0116\u0118\3\2\2\2\u0117\u0114\3\2\2\2\u0117\u0118\3\2\2\2\u0118"+
		"/\3\2\2\2\u0119\u011a\t\b\2\2\u011a\61\3\2\2\2\u011b\u011c\7\r\2\2\u011c"+
		"\u011d\5\34\17\2\u011d\u011e\7\16\2\2\u011e\u012d\3\2\2\2\u011f\u0120"+
		"\7>\2\2\u0120\u0122\7\r\2\2\u0121\u0123\5\64\33\2\u0122\u0121\3\2\2\2"+
		"\u0122\u0123\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u012d\7\16\2\2\u0125\u0126"+
		"\7\21\2\2\u0126\u0129\7>\2\2\u0127\u0128\7\23\2\2\u0128\u012a\5\62\32"+
		"\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u012d"+
		"\7;\2\2\u012c\u011b\3\2\2\2\u012c\u011f\3\2\2\2\u012c\u0125\3\2\2\2\u012c"+
		"\u012b\3\2\2\2\u012d\63\3\2\2\2\u012e\u0133\5\66\34\2\u012f\u0130\7\3"+
		"\2\2\u0130\u0132\5\66\34\2\u0131\u012f\3\2\2\2\u0132\u0135\3\2\2\2\u0133"+
		"\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\65\3\2\2\2\u0135\u0133\3\2\2"+
		"\2\u0136\u013b\7;\2\2\u0137\u0138\7\21\2\2\u0138\u013b\7>\2\2\u0139\u013b"+
		"\5\20\t\2\u013a\u0136\3\2\2\2\u013a\u0137\3\2\2\2\u013a\u0139\3\2\2\2"+
		"\u013b\67\3\2\2\2+>EKQZajnsy\177\u0083\u0085\u008b\u0093\u0099\u009e\u00a0"+
		"\u00af\u00b6\u00c1\u00c7\u00cc\u00d2\u00da\u00e4\u00eb\u00ed\u00f2\u00f7"+
		"\u00fc\u0102\u0108\u010d\u0111\u0117\u0122\u0129\u012c\u0133\u013a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}