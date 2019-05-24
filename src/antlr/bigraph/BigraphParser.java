package antlr.bigraph;// Generated from C:/Users/caste/IdeaProjects/Synkrisis/src\Bigraph.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
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
		DIGIT=46, IDENTIFIER=47;
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

	@Override
	public String getGrammarFileName() { return "core/g4model/Bigraph.g4"; }

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
			setState(76);
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
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				match(IDENTIFIER);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSQ) {
					{
					setState(79);
					match(LSQ);
					setState(80);
					links();
					setState(81);
					match(RSQ);
					}
				}

				setState(87);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(85);
					regions();
					}
					break;
				case DOT:
					{
					setState(86);
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
				setState(89);
				match(DIGIT);
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
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				match(DOLLAR);
				setState(95);
				match(DIGIT);
				setState(98);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOR:
				case PAR:
					{
					setState(96);
					regions();
					}
					break;
				case DOT:
					{
					setState(97);
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
				setState(100);
				match(LPAR);
				setState(101);
				expression();
				setState(102);
				match(RPAR);
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(103);
					regions();
					}
				}

				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				match(NIL);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOR || _la==PAR) {
					{
					setState(107);
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
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(LOR);
				setState(113);
				expression();
				}
				break;
			case PAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				match(PAR);
				setState(115);
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
			setState(118);
			match(DOT);
			setState(119);
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
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(IDENTIFIER);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(122);
					match(COMMA);
					setState(123);
					links();
					}
				}

				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(VARIABLE);
				setState(127);
				match(IDENTIFIER);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(128);
					match(COMMA);
					setState(129);
					links();
					}
				}

				}
				break;
			case UNLINKED:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				match(UNLINKED);
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
			setState(139);
			match(MODEL);
			setState(140);
			match(IDENTIFIER);
			setState(141);
			match(ASSIGNMENT);
			setState(142);
			expression();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PROPERTY) {
				{
				{
				setState(143);
				property();
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(149);
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
			setState(151);
			match(PROPERTY);
			setState(152);
			match(IDENTIFIER);
			setState(153);
			match(ASSIGNMENT);
			setState(154);
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
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
			case DOLLAR:
			case DIGIT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				boolean_expression();
				setState(161);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case AND:
					{
					setState(157);
					match(AND);
					setState(158);
					boolean_expression();
					}
					break;
				case LOR:
					{
					setState(159);
					match(LOR);
					setState(160);
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
				setState(163);
				match(NOT);
				setState(164);
				property_statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(165);
				match(IF);
				setState(166);
				property_statement();
				setState(167);
				match(THEN);
				setState(168);
				property_statement();
				setState(169);
				match(ELSE);
				setState(170);
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
			setState(174);
			term();
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LEQ) | (1L << GEQ) | (1L << LT) | (1L << GT) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(175);
				binary_operation();
				setState(176);
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
			setState(180);
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
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				match(LPAR);
				setState(183);
				property_statement();
				setState(184);
				match(RPAR);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				match(IDENTIFIER);
				setState(187);
				match(LPAR);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << DOLLAR) | (1L << NIL) | (1L << DIGIT) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(188);
					parameters_list();
					}
				}

				setState(191);
				match(RPAR);
				}
				break;
			case DOLLAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				match(DOLLAR);
				setState(193);
				match(IDENTIFIER);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ARROW) {
					{
					setState(194);
					match(ARROW);
					setState(195);
					term();
					}
				}

				}
				break;
			case DIGIT:
				enterOuterAlt(_localctx, 4);
				{
				setState(198);
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
			setState(201);
			parameter();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(202);
				match(COMMA);
				setState(203);
				parameter();
				}
				}
				setState(208);
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
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				match(DIGIT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				match(DOLLAR);
				setState(211);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u00da\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4"+
		"\3\4\5\4\66\n\4\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\3\6\3\6\5\6B\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\5\tV\n\t\3\t\3\t\5\tZ\n\t\3\t\3\t\3\t\5\t_\n\t\3\t\3\t\3\t\3\t\5\te\n"+
		"\t\3\t\3\t\3\t\3\t\5\tk\n\t\3\t\3\t\5\to\n\t\5\tq\n\t\3\n\3\n\3\n\3\n"+
		"\5\nw\n\n\3\13\3\13\3\13\3\f\3\f\3\f\5\f\177\n\f\3\f\3\f\3\f\3\f\5\f\u0085"+
		"\n\f\3\f\3\f\3\f\5\f\u008a\n\f\5\f\u008c\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u0093"+
		"\n\r\f\r\16\r\u0096\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\5\17\u00a4\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u00af\n\17\3\20\3\20\3\20\3\20\5\20\u00b5\n\20\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c0\n\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u00c7\n\22\3\22\5\22\u00ca\n\22\3\23\3\23\3\23\7\23\u00cf\n"+
		"\23\f\23\16\23\u00d2\13\23\3\24\3\24\3\24\3\24\5\24\u00d8\n\24\3\24\2"+
		"\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\5\3\2\30\31\3\2\33"+
		"\34\3\2&+\2\u00ec\2(\3\2\2\2\4.\3\2\2\2\6\60\3\2\2\2\b;\3\2\2\2\n=\3\2"+
		"\2\2\fJ\3\2\2\2\16L\3\2\2\2\20p\3\2\2\2\22v\3\2\2\2\24x\3\2\2\2\26\u008b"+
		"\3\2\2\2\30\u008d\3\2\2\2\32\u0099\3\2\2\2\34\u00ae\3\2\2\2\36\u00b0\3"+
		"\2\2\2 \u00b6\3\2\2\2\"\u00c9\3\2\2\2$\u00cb\3\2\2\2&\u00d7\3\2\2\2()"+
		"\5\4\3\2)\3\3\2\2\2*+\7\27\2\2+,\7\5\2\2,/\5\6\4\2-/\5\b\5\2.*\3\2\2\2"+
		".-\3\2\2\2/\5\3\2\2\2\60\61\t\2\2\2\61\62\7\61\2\2\62\65\7\60\2\2\63\66"+
		"\5\6\4\2\64\66\5\b\5\2\65\63\3\2\2\2\65\64\3\2\2\2\66\7\3\2\2\2\678\7"+
		"\32\2\289\7\5\2\29<\5\n\6\2:<\5\f\7\2;\67\3\2\2\2;:\3\2\2\2<\t\3\2\2\2"+
		"=>\t\3\2\2>A\7\61\2\2?B\5\n\6\2@B\5\f\7\2A?\3\2\2\2A@\3\2\2\2B\13\3\2"+
		"\2\2CD\7\35\2\2DE\7\61\2\2EF\7\7\2\2FG\5\16\b\2GH\5\f\7\2HK\3\2\2\2IK"+
		"\5\30\r\2JC\3\2\2\2JI\3\2\2\2K\r\3\2\2\2LM\5\20\t\2MN\7\23\2\2NO\5\20"+
		"\t\2O\17\3\2\2\2PU\7\61\2\2QR\7\13\2\2RS\5\26\f\2ST\7\f\2\2TV\3\2\2\2"+
		"UQ\3\2\2\2UV\3\2\2\2VY\3\2\2\2WZ\5\22\n\2XZ\5\24\13\2YW\3\2\2\2YX\3\2"+
		"\2\2YZ\3\2\2\2Zq\3\2\2\2[^\7\60\2\2\\_\5\22\n\2]_\5\24\13\2^\\\3\2\2\2"+
		"^]\3\2\2\2^_\3\2\2\2_q\3\2\2\2`a\7\21\2\2ad\7\60\2\2be\5\22\n\2ce\5\24"+
		"\13\2db\3\2\2\2dc\3\2\2\2de\3\2\2\2eq\3\2\2\2fg\7\r\2\2gh\5\20\t\2hj\7"+
		"\16\2\2ik\5\22\n\2ji\3\2\2\2jk\3\2\2\2kq\3\2\2\2ln\7\24\2\2mo\5\22\n\2"+
		"nm\3\2\2\2no\3\2\2\2oq\3\2\2\2pP\3\2\2\2p[\3\2\2\2p`\3\2\2\2pf\3\2\2\2"+
		"pl\3\2\2\2q\21\3\2\2\2rs\7\17\2\2sw\5\20\t\2tu\7\20\2\2uw\5\20\t\2vr\3"+
		"\2\2\2vt\3\2\2\2w\23\3\2\2\2xy\7\6\2\2yz\5\20\t\2z\25\3\2\2\2{~\7\61\2"+
		"\2|}\7\3\2\2}\177\5\26\f\2~|\3\2\2\2~\177\3\2\2\2\177\u008c\3\2\2\2\u0080"+
		"\u0081\7\36\2\2\u0081\u0084\7\61\2\2\u0082\u0083\7\3\2\2\u0083\u0085\5"+
		"\26\f\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u008c\3\2\2\2\u0086"+
		"\u0089\7\22\2\2\u0087\u0088\7\3\2\2\u0088\u008a\5\26\f\2\u0089\u0087\3"+
		"\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b{\3\2\2\2\u008b\u0080"+
		"\3\2\2\2\u008b\u0086\3\2\2\2\u008c\27\3\2\2\2\u008d\u008e\7\37\2\2\u008e"+
		"\u008f\7\61\2\2\u008f\u0090\7\7\2\2\u0090\u0094\5\20\t\2\u0091\u0093\5"+
		"\32\16\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7\2"+
		"\2\3\u0098\31\3\2\2\2\u0099\u009a\7 \2\2\u009a\u009b\7\61\2\2\u009b\u009c"+
		"\7\7\2\2\u009c\u009d\5\34\17\2\u009d\33\3\2\2\2\u009e\u00a3\5\36\20\2"+
		"\u009f\u00a0\7!\2\2\u00a0\u00a4\5\36\20\2\u00a1\u00a2\7\17\2\2\u00a2\u00a4"+
		"\5\36\20\2\u00a3\u009f\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2"+
		"\u00a4\u00af\3\2\2\2\u00a5\u00a6\7\"\2\2\u00a6\u00af\5\34\17\2\u00a7\u00a8"+
		"\7#\2\2\u00a8\u00a9\5\34\17\2\u00a9\u00aa\7$\2\2\u00aa\u00ab\5\34\17\2"+
		"\u00ab\u00ac\7%\2\2\u00ac\u00ad\5\34\17\2\u00ad\u00af\3\2\2\2\u00ae\u009e"+
		"\3\2\2\2\u00ae\u00a5\3\2\2\2\u00ae\u00a7\3\2\2\2\u00af\35\3\2\2\2\u00b0"+
		"\u00b4\5\"\22\2\u00b1\u00b2\5 \21\2\u00b2\u00b3\5\"\22\2\u00b3\u00b5\3"+
		"\2\2\2\u00b4\u00b1\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\37\3\2\2\2\u00b6"+
		"\u00b7\t\4\2\2\u00b7!\3\2\2\2\u00b8\u00b9\7\r\2\2\u00b9\u00ba\5\34\17"+
		"\2\u00ba\u00bb\7\16\2\2\u00bb\u00ca\3\2\2\2\u00bc\u00bd\7\61\2\2\u00bd"+
		"\u00bf\7\r\2\2\u00be\u00c0\5$\23\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2"+
		"\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00ca\7\16\2\2\u00c2\u00c3\7\21\2\2\u00c3"+
		"\u00c6\7\61\2\2\u00c4\u00c5\7\23\2\2\u00c5\u00c7\5\"\22\2\u00c6\u00c4"+
		"\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00ca\7\60\2\2"+
		"\u00c9\u00b8\3\2\2\2\u00c9\u00bc\3\2\2\2\u00c9\u00c2\3\2\2\2\u00c9\u00c8"+
		"\3\2\2\2\u00ca#\3\2\2\2\u00cb\u00d0\5&\24\2\u00cc\u00cd\7\3\2\2\u00cd"+
		"\u00cf\5&\24\2\u00ce\u00cc\3\2\2\2\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1%\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d8"+
		"\7\60\2\2\u00d4\u00d5\7\21\2\2\u00d5\u00d8\7\61\2\2\u00d6\u00d8\5\20\t"+
		"\2\u00d7\u00d3\3\2\2\2\u00d7\u00d4\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8\'"+
		"\3\2\2\2\34.\65;AJUY^djnpv~\u0084\u0089\u008b\u0094\u00a3\u00ae\u00b4"+
		"\u00bf\u00c6\u00c9\u00d0\u00d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}