grammar Transition;

/*
Parser
*/

transition : state+;

expression         : IDENTIFIER (LSQ links RSQ)?   (regions | prefix)?
                   | DOLLAR DIGIT                  (regions | prefix)?
                   | LPAR expression RPAR          (regions)?
                   | NIL                           (regions)?
                   ;

regions            : LOR expression
                   | PAR expression
                   ;

prefix             : DOT expression
                   ;

links              : IDENTIFIER          (COMMA links)?
                   | VARIABLE IDENTIFIER (COMMA links)?
                   | UNLINKED            (COMMA links)?
                   ;

state              : STATE DIGIT COLON expression (properties)?;

properties         : PROPERTIES COLON (IDENTIFIER)+;


/*
Lexer
*/

COMMA      : ',' ;
SEMI       : ';' -> skip;
COLON      : ':' ;
DOT        : '.' ;
ASSIGNMENT : '=';
STATE      : 'State';

WHITESP : ' ' -> skip ;
ESCAPE  : '\t' -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip ;

LSQ     : '[' ;
RSQ     : ']' ;
LPAR    : '(' ;
RPAR    : ')' ;
LOR     : '||' ;
PAR     : '|' ;

DOLLAR   : '$' ;
UNLINKED : '-' ;
ARROW    : '->' ;
NIL         : 'nil' ;

COMMENT      : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '#' ~[\r\n]* -> skip;

VARIABLE    : '@';
PROPERTIES    : 'Properties';


DIGIT       : ('0'..'9')+ ;
IDENTIFIER  : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;




