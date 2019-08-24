grammar bigraph;

/*
Parser
*/

bigraph : controls;

controls           : CONTROLS COLON control_statements
                   | names
                   ;

control_statements : (ACTIVE | PASSIVE) IDENTIFIER DIGIT (control_statements | names)
                   ;

names              : NAMES COLON name_statements
                   | reactions
                   ;

name_statements    : (INNER | OUTER) IDENTIFIER (name_statements | reactions)
                   ;

reactions          : RULE IDENTIFIER ASSIGNMENT reaction_statement reactions
                   | model
                   ;

reaction_statement : expression (LPAR PROBABILITY RPAR)? ARROW expression
                   ;

expression         : IDENTIFIER (LSQ links RSQ)?   (regions | prefix)?
                   | DIGIT                         (regions | prefix)?
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

model              : MODEL IDENTIFIER ASSIGNMENT expression marker;

marker             : MARKER IDENTIFIER ASSIGNMENT marker_statement marker
                   | properties
                   ;

marker_statement   : boolean_expression (AND boolean_expression | LOR boolean_expression)?
                   | NOT marker_statement
                   | IF marker_statement THEN marker_statement ELSE marker_statement
                   ;

properties         : PROPERTIES COLON spot_statement
                   | spot_statement
                   ;

spot_statement     : SPOT ASSIGNMENT acc_name? acceptance extra_properties
                   | extra_properties
                   ;

// Spot makes use of hyphens (and apparently no digits) for its identifiers, so I create a new token
acc_name           : ACCNAME COLON IDENTIFIER (IDENTIFIER | DIGIT)+
                   ;


acceptance         : ACCEPTANCE COLON DIGIT acceptance_cond1
                   ;

acceptance_cond1   : acceptance_cond2 ((CONJUNCTION | PAR) acceptance_cond1)?
                   | LPAR acceptance_cond1 RPAR ((CONJUNCTION | PAR) acceptance_cond1)?
                   ;

acceptance_cond2   : (FIN|INF) LPAR (NOT)? IDENTIFIER (CONJUNCTION (NOT)? IDENTIFIER)* RPAR
                   | IDENTIFIER
                   ;


extra_properties   : IDENTIFIER LPAR IDENTIFIER RPAR ASSIGNMENT PROP_LBRACK extra_statements PROP_RBRACK extra_properties
                   | EOF
                   ;

extra_statements   : ~(PROP_RBRACK)*
                   ;

boolean_expression : term (binary_operation term)?
                   ;

binary_operation   : (NEQ | EQ | LEQ | GEQ | LT | GT)
                   ;

term               : LPAR marker_statement RPAR
                   | IDENTIFIER LPAR (parameters_list)? RPAR
                   | DOLLAR IDENTIFIER (ARROW term)?
                   | DIGIT
                   ;

parameters_list    : parameter (COMMA parameter)*
                   ;

parameter          : IDENTIFIER (LSQ links RSQ)?   (regions | prefix)?
                   | DIGIT                         (regions | prefix)?
                   | DOLLAR (DIGIT|IDENTIFIER)     (regions | prefix)?
                   | LPAR expression RPAR          (regions)?
                   | NIL                           (regions)?
                   ;



/*
Lexer
*/

COMMA      : ',' ;
COLON      : ':' ;
DOT        : '.' ;
ASSIGNMENT : '=';

WHITESP : ' ' -> skip ;
ESCAPE  : '\t' -> skip;
NEWLINE : ('\r'? '\n' | '\r')+ -> skip ;

LSQ     : '[' ;
RSQ     : ']' ;
LPAR    : '(' ;
RPAR    : ')' ;
LOR     : '||';
PAR     : '|' ;

SEMI     : ';'   ;
DOLLAR   : '$'   ;
UNLINKED : '-'   ;
ARROW    : '->'  ;
NIL      : 'nil' ;

COMMENT      : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '#' ~[\r\n]* -> skip  ;

CONTROLS    : 'controls';
ACTIVE      : 'active';
PASSIVE     : 'passive';

NAMES       : 'names';
INNER       : 'inner';
OUTER       : 'outer';
RULE        : 'rule' ;
VARIABLE    : '@';
MODEL       : 'model';
MARKER      : 'marker';
PROPERTIES  : 'properties';
AND         : '&&';
NOT         : '!';

IF      : 'if';
THEN    : 'then';
ELSE    : 'else';
LEQ     : '<=' ;
GEQ     : '>=' ;
LT      : '<'  ;
GT      : '>'  ;
EQ      : '==' ;
NEQ     : '!=' ;

// Necessary for properties specification
SLASH    : '/'  ;
QUOTE    : '"'  ;
QUESTION : '?'  ;
PRODUCT  : '*'  ;
ADDITION : '+'  ;
CONJUNCTION : '&' ;

SPOT       : 'SPOT-ACCEPTANCE'  ;
ACCEPTANCE : 'Acceptance' ;
ACCNAME    : 'acc-name' ;
FIN        : 'Fin' ;
INF        : 'Inf' ;

PROP_LBRACK : '\\{';
PROP_RBRACK : '\\}';


// Closing line
DIGIT       : ('0'..'9')+ ;
PROBABILITY : ('0' DOT DIGIT+) ;

IDENTIFIER      : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'_'|'-'|'0'..'9')* ;
