package antlr.remscheld;

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
public class remscheldParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NTREE=1, LPAR=2, RPAR=3, LSQUARE=4, RSQUARE=5, COMMA=6, WHITESP=7, NEWLINE=8, 
		ID=9;
	public static final int
		RULE_remscheld = 0, RULE_node = 1, RULE_identifiers = 2, RULE_children = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"remscheld", "node", "identifiers", "children"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'NTree'", "'('", "')'", "'['", "']'", "','", "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NTREE", "LPAR", "RPAR", "LSQUARE", "RSQUARE", "COMMA", "WHITESP", 
			"NEWLINE", "ID"
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
	public String getGrammarFileName() { return "remscheld.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public remscheldParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class RemscheldContext extends ParserRuleContext {
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(remscheldParser.EOF, 0); }
		public RemscheldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remscheld; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).enterRemscheld(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).exitRemscheld(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof remscheldVisitor ) return ((remscheldVisitor<? extends T>)visitor).visitRemscheld(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemscheldContext remscheld() throws RecognitionException {
		RemscheldContext _localctx = new RemscheldContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_remscheld);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			node();
			setState(9);
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

	public static class NodeContext extends ParserRuleContext {
		public TerminalNode NTREE() { return getToken(remscheldParser.NTREE, 0); }
		public TerminalNode LPAR() { return getToken(remscheldParser.LPAR, 0); }
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(remscheldParser.RPAR, 0); }
		public ChildrenContext children() {
			return getRuleContext(ChildrenContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(remscheldParser.COMMA, 0); }
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).exitNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof remscheldVisitor ) return ((remscheldVisitor<? extends T>)visitor).visitNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_node);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			match(NTREE);
			setState(12);
			match(LPAR);
			setState(13);
			identifiers();
			setState(14);
			match(RPAR);
			setState(15);
			children();
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(16);
				match(COMMA);
				setState(17);
				node();
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

	public static class IdentifiersContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(remscheldParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(remscheldParser.ID, i);
		}
		public TerminalNode COMMA() { return getToken(remscheldParser.COMMA, 0); }
		public IdentifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).enterIdentifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).exitIdentifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof remscheldVisitor ) return ((remscheldVisitor<? extends T>)visitor).visitIdentifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifiersContext identifiers() throws RecognitionException {
		IdentifiersContext _localctx = new IdentifiersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_identifiers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(ID);
			setState(21);
			match(COMMA);
			setState(22);
			match(ID);
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

	public static class ChildrenContext extends ParserRuleContext {
		public TerminalNode LSQUARE() { return getToken(remscheldParser.LSQUARE, 0); }
		public TerminalNode RSQUARE() { return getToken(remscheldParser.RSQUARE, 0); }
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public ChildrenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_children; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).enterChildren(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof remscheldListener ) ((remscheldListener)listener).exitChildren(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof remscheldVisitor ) return ((remscheldVisitor<? extends T>)visitor).visitChildren(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChildrenContext children() throws RecognitionException {
		ChildrenContext _localctx = new ChildrenContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_children);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(LSQUARE);
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NTREE) {
				{
				setState(25);
				node();
				}
			}

			setState(28);
			match(RSQUARE);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13!\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\25\n\3"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\5\5\35\n\5\3\5\3\5\3\5\2\2\6\2\4\6\b\2\2\2\36"+
		"\2\n\3\2\2\2\4\r\3\2\2\2\6\26\3\2\2\2\b\32\3\2\2\2\n\13\5\4\3\2\13\f\7"+
		"\2\2\3\f\3\3\2\2\2\r\16\7\3\2\2\16\17\7\4\2\2\17\20\5\6\4\2\20\21\7\5"+
		"\2\2\21\24\5\b\5\2\22\23\7\b\2\2\23\25\5\4\3\2\24\22\3\2\2\2\24\25\3\2"+
		"\2\2\25\5\3\2\2\2\26\27\7\13\2\2\27\30\7\b\2\2\30\31\7\13\2\2\31\7\3\2"+
		"\2\2\32\34\7\6\2\2\33\35\5\4\3\2\34\33\3\2\2\2\34\35\3\2\2\2\35\36\3\2"+
		"\2\2\36\37\7\7\2\2\37\t\3\2\2\2\4\24\34";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}