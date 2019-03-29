grammar Bigraph;

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

reaction_statement : expression ARROW expression
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

model              : MODEL IDENTIFIER ASSIGNMENT expression (property)*;

property           : PROPERTY IDENTIFIER ASSIGNMENT property_statement;

property_statement : boolean_expression (AND boolean_expression | LOR boolean_expression)?
                   | NOT property_statement
                   | IF property_statement THEN property_statement ELSE property_statement
                   ;

boolean_expression : term (binary_operation term)?
                   ;

binary_operation   : (NEQ | EQ | LEQ | GEQ | LT | GT)
                   ;

term               : LPAR property_statement RPAR
                   | IDENTIFIER LPAR (parameters_list)? RPAR
                   | DOLLAR IDENTIFIER (ARROW term)?
                   | DIGIT
                   ;

parameters_list    : parameter (COMMA parameter)*
                   ;

parameter          : DIGIT | DOLLAR IDENTIFIER | expression
                   ;



/*
Lexer
*/

COMMA      : ',' ;
SEMI       : ';' -> skip;
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
LOR     : '||' ;
PAR     : '|' ;

DOLLAR   : '$' ;
UNLINKED : '-' ;
ARROW    : '->' ;
NIL         : 'nil' ;

COMMENT      : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '#' ~[\r\n]* -> skip;

CONTROLS    : 'controls';
ACTIVE      : 'active';
PASSIVE     : 'passive';

NAMES       : 'names';
INNER       : 'inner';
OUTER       : 'outer';
RULE        : 'rule';
VARIABLE    : '@';
MODEL       : 'model';
PROPERTY    : 'property';
AND         : '&&';
NOT         : '!';

IF      : 'if';
THEN    : 'then';
ELSE    : 'else';
LEQ     : '<=' ;
GEQ     : '>=' ;
LT      : '<' ;
GT      : '>' ;
EQ      : '==' ;
NEQ     : '!=' ;
FORALL  : 'forall' ;
EXISTS  : 'exists' ;
TRUE    : 'true' ;
FALSE   : 'false';

DIGIT       : ('0'..'9')+ ;
IDENTIFIER  : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;




