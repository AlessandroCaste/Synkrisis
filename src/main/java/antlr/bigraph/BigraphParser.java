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
public class BigraphParser extends Parser {
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
		DIGIT=46, PROBABILITY=47, IDENTIFIER=48;
	public static final int
		RULE_bigraph = 0, RULE_controls = 1, RULE_control_statements = 2, RULE_names = 3, 
		RULE_name_statements = 4, RULE_reactions = 5, RULE_reaction_statement = 6, 
		RULE_expression = 7, RULE_regions = 8, RULE_prefix = 9, RULE_links = 10, 
		RULE_model = 11, RULE_property = 12, RULE_property_statement = 13, RULE_boolean_expression = 14, 
		RULE_binary_operation = 15, RULE_term = 16, RULE_parameters_list = 17, 
		RULE_parameter = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"bigraph", "controls", "control_statements", "names", "name_statements", 
			"reactions", "reaction_statement", "expression", "regions", "prefix", 
			"links", "model", "property", "property_statement", "boolean_expression", 
			"binary_operation", "term", "parameters_list", "parameter"
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
			"EXISTS", "TRUE", "FALSE", "DIGIT", "PROBABILITY", "IDENTIFIER"
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

	public BigraphParser(TokenStream input) {
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterBigraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitBigraph(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitBigraph(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BigraphContext bigraph() throws RecognitionException {
		BigraphContext _localctx = new BigraphContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_bigraph);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
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
		public TerminalNode CONTROLS() { return getToken(BigraphParser.CONTROLS, 0); }
		public TerminalNode COLON() { return getToken(BigraphParser.COLON, 0); }
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterControls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitControls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitControls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControlsContext controls() throws RecognitionException {
		ControlsContext _localctx = new ControlsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_controls);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONTROLS:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				match(CONTROLS);
				setState(41);
				match(COLON);
				setState(42);
				control_statements();
				}
				break;
			case NAMES:
			case RULE:
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
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
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode DIGIT() { return getToken(BigraphParser.DIGIT, 0); }
		public TerminalNode ACTIVE() { return getToken(BigraphParser.ACTIVE, 0); }
		public TerminalNode PASSIVE() { return getToken(BigraphParser.PASSIVE, 0); }
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterControl_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitControl_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitControl_statements(this);
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
			setState(46);
			_la = _input.LA(1);
			if ( !(_la==ACTIVE || _la==PASSIVE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(47);
			match(IDENTIFIER);
			setState(48);
			match(DIGIT);
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACTIVE:
			case PASSIVE:
				{
				setState(49);
				control_statements();
				}
				break;
			case NAMES:
			case RULE:
			case MODEL:
				{
				setState(50);
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
		public TerminalNode NAMES() { return getToken(BigraphParser.NAMES, 0); }
		public TerminalNode COLON() { return getToken(BigraphParser.COLON, 0); }
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamesContext names() throws RecognitionException {
		NamesContext _localctx = new NamesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_names);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMES:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(NAMES);
				setState(54);
				match(COLON);
				setState(55);
				name_statements();
				}
				break;
			case RULE:
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
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
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode INNER() { return getToken(BigraphParser.INNER, 0); }
		public TerminalNode OUTER() { return getToken(BigraphParser.OUTER, 0); }
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterName_statements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitName_statements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitName_statements(this);
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
			setState(59);
			_la = _input.LA(1);
			if ( !(_la==INNER || _la==OUTER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(60);
			match(IDENTIFIER);
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INNER:
			case OUTER:
				{
				setState(61);
				name_statements();
				}
				break;
			case RULE:
			case MODEL:
				{
				setState(62);
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
		public TerminalNode RULE() { return getToken(BigraphParser.RULE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(BigraphParser.ASSIGNMENT, 0); }
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterReactions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitReactions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitReactions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReactionsContext reactions() throws RecognitionException {
		ReactionsContext _localctx = new ReactionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reactions);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(RULE);
				setState(66);
				match(IDENTIFIER);
				setState(67);
				match(ASSIGNMENT);
				setState(68);
				reaction_statement();
				setState(69);
				reactions();
				}
				break;
			case MODEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
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
		public TerminalNode ARROW() { return getToken(BigraphParser.ARROW, 0); }
		public TerminalNode LPAR() { return getToken(BigraphParser.LPAR, 0); }
		public TerminalNode PROBABILITY() { return getToken(BigraphParser.PROBABILITY, 0); }
		public TerminalNode RPAR() { return getToken(BigraphParser.RPAR, 0); }
		public Reaction_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reaction_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterReaction_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitReaction_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitReaction_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reaction_statementContext reaction_statement() throws RecognitionException {
		Reaction_statementContext _localctx = new Reaction_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_reaction_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			expression();
			setState(75);
			match(ARROW);
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(76);
				match(LPAR);
				setState(77);
				match(PROBABILITY);
				setState(78);
				match(RPAR);
				}
				break;
			}
			setState(81);
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
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode LSQ() { return getToken(BigraphParser.LSQ, 0); }
		public LinksContext links() {
			return getRuleContext(LinksContext.class,0);
		}
		public TerminalNode RSQ() { return getToken(BigraphParser.RSQ, 0); }
		public RegionsContext regions() {
			return getRuleContext(RegionsContext.class,0);
		}
		public PrefixContext prefix() {
			return getRuleContext(PrefixContext.class,0);
		}
		public TerminalNode DIGIT() { return getToken(BigraphParser.DIGIT, 0); }
		public TerminalNode DOLLAR() { return getToken(BigraphParser.DOLLAR, 0); }
		public TerminalNode LPAR() { return getToken(BigraphParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(BigraphParser.RPAR, 0); }
		public TerminalNode NIL() { return getToken(BigraphParser.NIL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		int _la;
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				match(IDENTIFIER);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSQ) {
					{
					setState(84);
					match(LSQ);
					setState(85);
					links();
					setState(86);
					match(RSQ);
					}
				}

				setState(92);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(90);
					regions();
					}
					break;
				case DOT:
					{
					setState(91);
					prefix();
					}
					break;
				case EOF:
				case COMMA:
				case RPAR:
				case ARROW:
				case RULE:
				case MODEL:
				case PROPERTY:
					break;
				default:
					break;
				}
				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(DIGIT);
				setState(97);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(95);
					regions();
					}
					break;
				case DOT:
					{
					setState(96);
					prefix();
					}
					break;
				case EOF:
				case COMMA:
				case RPAR:
				case ARROW:
				case RULE:
				case MODEL:
				case PROPERTY:
					break;
				default:
					break;
				}
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				match(DOLLAR);
				setState(100);
				match(DIGIT);
				setState(103);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(101);
					regions();
					}
					break;
				case DOT:
					{
					setState(102);
					prefix();
					}
					break;
				case EOF:
				case COMMA:
				case RPAR:
				case ARROW:
				case RULE:
				case MODEL:
				case PROPERTY:
					break;
				default:
					break;
				}
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				match(LPAR);
				setState(106);
				expression();
				setState(107);
				match(RPAR);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(108);
					regions();
					}
				}

				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 5);
				{
				setState(111);
				match(NIL);
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
		public TerminalNode LOR() { return getToken(BigraphParser.LOR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PAR() { return getToken(BigraphParser.PAR, 0); }
		public RegionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterRegions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitRegions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitRegions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegionsContext regions() throws RecognitionException {
		RegionsContext _localctx = new RegionsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_regions);
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				match(LOR);
				setState(118);
				expression();
				}
				break;
			case PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(PAR);
				setState(120);
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
		public TerminalNode DOT() { return getToken(BigraphParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixContext prefix() throws RecognitionException {
		PrefixContext _localctx = new PrefixContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_prefix);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(DOT);
			setState(124);
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
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode COMMA() { return getToken(BigraphParser.COMMA, 0); }
		public LinksContext links() {
			return getRuleContext(LinksContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(BigraphParser.VARIABLE, 0); }
		public TerminalNode UNLINKED() { return getToken(BigraphParser.UNLINKED, 0); }
		public LinksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_links; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterLinks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitLinks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitLinks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinksContext links() throws RecognitionException {
		LinksContext _localctx = new LinksContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_links);
		int _la;
		try {
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(IDENTIFIER);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(127);
					match(COMMA);
					setState(128);
					links();
					}
				}

				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				match(VARIABLE);
				setState(132);
				match(IDENTIFIER);
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(133);
					match(COMMA);
					setState(134);
					links();
					}
				}

				}
				break;
			case UNLINKED:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(UNLINKED);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(138);
					match(COMMA);
					setState(139);
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
		public TerminalNode MODEL() { return getToken(BigraphParser.MODEL, 0); }
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(BigraphParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BigraphParser.EOF, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public ModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModelContext model() throws RecognitionException {
		ModelContext _localctx = new ModelContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_model);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(MODEL);
			setState(145);
			match(IDENTIFIER);
			setState(146);
			match(ASSIGNMENT);
			setState(147);
			expression();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PROPERTY) {
				{
				{
				setState(148);
				property();
				}
				}
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(154);
			match(EOF);
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
		public TerminalNode PROPERTY() { return getToken(BigraphParser.PROPERTY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(BigraphParser.ASSIGNMENT, 0); }
		public Property_statementContext property_statement() {
			return getRuleContext(Property_statementContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(PROPERTY);
			setState(157);
			match(IDENTIFIER);
			setState(158);
			match(ASSIGNMENT);
			setState(159);
			property_statement();
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

	public static class Property_statementContext extends ParserRuleContext {
		public List<Boolean_expressionContext> boolean_expression() {
			return getRuleContexts(Boolean_expressionContext.class);
		}
		public Boolean_expressionContext boolean_expression(int i) {
			return getRuleContext(Boolean_expressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(BigraphParser.AND, 0); }
		public TerminalNode LOR() { return getToken(BigraphParser.LOR, 0); }
		public TerminalNode NOT() { return getToken(BigraphParser.NOT, 0); }
		public List<Property_statementContext> property_statement() {
			return getRuleContexts(Property_statementContext.class);
		}
		public Property_statementContext property_statement(int i) {
			return getRuleContext(Property_statementContext.class,i);
		}
		public TerminalNode IF() { return getToken(BigraphParser.IF, 0); }
		public TerminalNode THEN() { return getToken(BigraphParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(BigraphParser.ELSE, 0); }
		public Property_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterProperty_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitProperty_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitProperty_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Property_statementContext property_statement() throws RecognitionException {
		Property_statementContext _localctx = new Property_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_property_statement);
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
				case PROPERTY:
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
				property_statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				match(IF);
				setState(171);
				property_statement();
				setState(172);
				match(THEN);
				setState(173);
				property_statement();
				setState(174);
				match(ELSE);
				setState(175);
				property_statement();
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
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterBoolean_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitBoolean_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitBoolean_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_expressionContext boolean_expression() throws RecognitionException {
		Boolean_expressionContext _localctx = new Boolean_expressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_boolean_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			term();
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEQ) | (1L << GEQ) | (1L << LT) | (1L << GT) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(180);
				binary_operation();
				setState(181);
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
		public TerminalNode NEQ() { return getToken(BigraphParser.NEQ, 0); }
		public TerminalNode EQ() { return getToken(BigraphParser.EQ, 0); }
		public TerminalNode LEQ() { return getToken(BigraphParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(BigraphParser.GEQ, 0); }
		public TerminalNode LT() { return getToken(BigraphParser.LT, 0); }
		public TerminalNode GT() { return getToken(BigraphParser.GT, 0); }
		public Binary_operationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterBinary_operation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitBinary_operation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitBinary_operation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_operationContext binary_operation() throws RecognitionException {
		Binary_operationContext _localctx = new Binary_operationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_binary_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
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
		public TerminalNode LPAR() { return getToken(BigraphParser.LPAR, 0); }
		public Property_statementContext property_statement() {
			return getRuleContext(Property_statementContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(BigraphParser.RPAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public Parameters_listContext parameters_list() {
			return getRuleContext(Parameters_listContext.class,0);
		}
		public TerminalNode DOLLAR() { return getToken(BigraphParser.DOLLAR, 0); }
		public TerminalNode ARROW() { return getToken(BigraphParser.ARROW, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode DIGIT() { return getToken(BigraphParser.DIGIT, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_term);
		int _la;
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				match(LPAR);
				setState(188);
				property_statement();
				setState(189);
				match(RPAR);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(IDENTIFIER);
				setState(192);
				match(LPAR);
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << DOLLAR) | (1L << NIL) | (1L << DIGIT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(193);
					parameters_list();
					}
				}

				setState(196);
				match(RPAR);
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				match(DOLLAR);
				setState(198);
				match(IDENTIFIER);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ARROW) {
					{
					setState(199);
					match(ARROW);
					setState(200);
					term();
					}
				}

				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
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
		public List<TerminalNode> COMMA() { return getTokens(BigraphParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BigraphParser.COMMA, i);
		}
		public Parameters_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterParameters_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitParameters_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitParameters_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parameters_listContext parameters_list() throws RecognitionException {
		Parameters_listContext _localctx = new Parameters_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_parameters_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			parameter();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(207);
				match(COMMA);
				setState(208);
				parameter();
				}
				}
				setState(213);
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
		public TerminalNode DIGIT() { return getToken(BigraphParser.DIGIT, 0); }
		public TerminalNode DOLLAR() { return getToken(BigraphParser.DOLLAR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(BigraphParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BigraphListener ) ((BigraphListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BigraphVisitor ) return ((BigraphVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_parameter);
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(DIGIT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				match(DOLLAR);
				setState(216);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\62\u00df\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4"+
		"\3\4\5\4\66\n\4\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\3\6\3\6\5\6B\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\b\5\bR\n\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\5\t[\n\t\3\t\3\t\5\t_\n\t\3\t\3\t\3\t\5\td\n\t\3"+
		"\t\3\t\3\t\3\t\5\tj\n\t\3\t\3\t\3\t\3\t\5\tp\n\t\3\t\3\t\5\tt\n\t\5\t"+
		"v\n\t\3\n\3\n\3\n\3\n\5\n|\n\n\3\13\3\13\3\13\3\f\3\f\3\f\5\f\u0084\n"+
		"\f\3\f\3\f\3\f\3\f\5\f\u008a\n\f\3\f\3\f\3\f\5\f\u008f\n\f\5\f\u0091\n"+
		"\f\3\r\3\r\3\r\3\r\3\r\7\r\u0098\n\r\f\r\16\r\u009b\13\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17\u00a9\n\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00b4\n\17\3\20\3\20\3\20\3\20"+
		"\5\20\u00ba\n\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c5"+
		"\n\22\3\22\3\22\3\22\3\22\3\22\5\22\u00cc\n\22\3\22\5\22\u00cf\n\22\3"+
		"\23\3\23\3\23\7\23\u00d4\n\23\f\23\16\23\u00d7\13\23\3\24\3\24\3\24\3"+
		"\24\5\24\u00dd\n\24\3\24\2\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&\2\5\3\2\30\31\3\2\33\34\3\2&+\2\u00f2\2(\3\2\2\2\4.\3\2\2\2\6\60"+
		"\3\2\2\2\b;\3\2\2\2\n=\3\2\2\2\fJ\3\2\2\2\16L\3\2\2\2\20u\3\2\2\2\22{"+
		"\3\2\2\2\24}\3\2\2\2\26\u0090\3\2\2\2\30\u0092\3\2\2\2\32\u009e\3\2\2"+
		"\2\34\u00b3\3\2\2\2\36\u00b5\3\2\2\2 \u00bb\3\2\2\2\"\u00ce\3\2\2\2$\u00d0"+
		"\3\2\2\2&\u00dc\3\2\2\2()\5\4\3\2)\3\3\2\2\2*+\7\27\2\2+,\7\5\2\2,/\5"+
		"\6\4\2-/\5\b\5\2.*\3\2\2\2.-\3\2\2\2/\5\3\2\2\2\60\61\t\2\2\2\61\62\7"+
		"\62\2\2\62\65\7\60\2\2\63\66\5\6\4\2\64\66\5\b\5\2\65\63\3\2\2\2\65\64"+
		"\3\2\2\2\66\7\3\2\2\2\678\7\32\2\289\7\5\2\29<\5\n\6\2:<\5\f\7\2;\67\3"+
		"\2\2\2;:\3\2\2\2<\t\3\2\2\2=>\t\3\2\2>A\7\62\2\2?B\5\n\6\2@B\5\f\7\2A"+
		"?\3\2\2\2A@\3\2\2\2B\13\3\2\2\2CD\7\35\2\2DE\7\62\2\2EF\7\7\2\2FG\5\16"+
		"\b\2GH\5\f\7\2HK\3\2\2\2IK\5\30\r\2JC\3\2\2\2JI\3\2\2\2K\r\3\2\2\2LM\5"+
		"\20\t\2MQ\7\23\2\2NO\7\r\2\2OP\7\61\2\2PR\7\16\2\2QN\3\2\2\2QR\3\2\2\2"+
		"RS\3\2\2\2ST\5\20\t\2T\17\3\2\2\2UZ\7\62\2\2VW\7\13\2\2WX\5\26\f\2XY\7"+
		"\f\2\2Y[\3\2\2\2ZV\3\2\2\2Z[\3\2\2\2[^\3\2\2\2\\_\5\22\n\2]_\5\24\13\2"+
		"^\\\3\2\2\2^]\3\2\2\2^_\3\2\2\2_v\3\2\2\2`c\7\60\2\2ad\5\22\n\2bd\5\24"+
		"\13\2ca\3\2\2\2cb\3\2\2\2cd\3\2\2\2dv\3\2\2\2ef\7\21\2\2fi\7\60\2\2gj"+
		"\5\22\n\2hj\5\24\13\2ig\3\2\2\2ih\3\2\2\2ij\3\2\2\2jv\3\2\2\2kl\7\r\2"+
		"\2lm\5\20\t\2mo\7\16\2\2np\5\22\n\2on\3\2\2\2op\3\2\2\2pv\3\2\2\2qs\7"+
		"\24\2\2rt\5\22\n\2sr\3\2\2\2st\3\2\2\2tv\3\2\2\2uU\3\2\2\2u`\3\2\2\2u"+
		"e\3\2\2\2uk\3\2\2\2uq\3\2\2\2v\21\3\2\2\2wx\7\17\2\2x|\5\20\t\2yz\7\20"+
		"\2\2z|\5\20\t\2{w\3\2\2\2{y\3\2\2\2|\23\3\2\2\2}~\7\6\2\2~\177\5\20\t"+
		"\2\177\25\3\2\2\2\u0080\u0083\7\62\2\2\u0081\u0082\7\3\2\2\u0082\u0084"+
		"\5\26\f\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0091\3\2\2\2"+
		"\u0085\u0086\7\36\2\2\u0086\u0089\7\62\2\2\u0087\u0088\7\3\2\2\u0088\u008a"+
		"\5\26\f\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0091\3\2\2\2"+
		"\u008b\u008e\7\22\2\2\u008c\u008d\7\3\2\2\u008d\u008f\5\26\f\2\u008e\u008c"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u0080\3\2\2\2\u0090"+
		"\u0085\3\2\2\2\u0090\u008b\3\2\2\2\u0091\27\3\2\2\2\u0092\u0093\7\37\2"+
		"\2\u0093\u0094\7\62\2\2\u0094\u0095\7\7\2\2\u0095\u0099\5\20\t\2\u0096"+
		"\u0098\5\32\16\2\u0097\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c"+
		"\u009d\7\2\2\3\u009d\31\3\2\2\2\u009e\u009f\7 \2\2\u009f\u00a0\7\62\2"+
		"\2\u00a0\u00a1\7\7\2\2\u00a1\u00a2\5\34\17\2\u00a2\33\3\2\2\2\u00a3\u00a8"+
		"\5\36\20\2\u00a4\u00a5\7!\2\2\u00a5\u00a9\5\36\20\2\u00a6\u00a7\7\17\2"+
		"\2\u00a7\u00a9\5\36\20\2\u00a8\u00a4\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00b4\3\2\2\2\u00aa\u00ab\7\"\2\2\u00ab\u00b4\5\34"+
		"\17\2\u00ac\u00ad\7#\2\2\u00ad\u00ae\5\34\17\2\u00ae\u00af\7$\2\2\u00af"+
		"\u00b0\5\34\17\2\u00b0\u00b1\7%\2\2\u00b1\u00b2\5\34\17\2\u00b2\u00b4"+
		"\3\2\2\2\u00b3\u00a3\3\2\2\2\u00b3\u00aa\3\2\2\2\u00b3\u00ac\3\2\2\2\u00b4"+
		"\35\3\2\2\2\u00b5\u00b9\5\"\22\2\u00b6\u00b7\5 \21\2\u00b7\u00b8\5\"\22"+
		"\2\u00b8\u00ba\3\2\2\2\u00b9\u00b6\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\37"+
		"\3\2\2\2\u00bb\u00bc\t\4\2\2\u00bc!\3\2\2\2\u00bd\u00be\7\r\2\2\u00be"+
		"\u00bf\5\34\17\2\u00bf\u00c0\7\16\2\2\u00c0\u00cf\3\2\2\2\u00c1\u00c2"+
		"\7\62\2\2\u00c2\u00c4\7\r\2\2\u00c3\u00c5\5$\23\2\u00c4\u00c3\3\2\2\2"+
		"\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00cf\7\16\2\2\u00c7\u00c8"+
		"\7\21\2\2\u00c8\u00cb\7\62\2\2\u00c9\u00ca\7\23\2\2\u00ca\u00cc\5\"\22"+
		"\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cf"+
		"\7\60\2\2\u00ce\u00bd\3\2\2\2\u00ce\u00c1\3\2\2\2\u00ce\u00c7\3\2\2\2"+
		"\u00ce\u00cd\3\2\2\2\u00cf#\3\2\2\2\u00d0\u00d5\5&\24\2\u00d1\u00d2\7"+
		"\3\2\2\u00d2\u00d4\5&\24\2\u00d3\u00d1\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6%\3\2\2\2\u00d7\u00d5\3\2\2\2"+
		"\u00d8\u00dd\7\60\2\2\u00d9\u00da\7\21\2\2\u00da\u00dd\7\62\2\2\u00db"+
		"\u00dd\5\20\t\2\u00dc\u00d8\3\2\2\2\u00dc\u00d9\3\2\2\2\u00dc\u00db\3"+
		"\2\2\2\u00dd\'\3\2\2\2\35.\65;AJQZ^ciosu{\u0083\u0089\u008e\u0090\u0099"+
		"\u00a8\u00b3\u00b9\u00c4\u00cb\u00ce\u00d5\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}