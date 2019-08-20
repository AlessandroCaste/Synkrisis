grammar remscheld;

remscheld   : node EOF;
node        : NTREE LPAR identifiers RPAR children (COMMA node)?;
identifiers : ID COMMA ID;
children    : LSQUARE (node)? RSQUARE;




NTREE       : 'NTree';
LPAR        : '(';
RPAR        : ')';
LSQUARE     : '[';
RSQUARE     : ']';
COMMA       : ',';
WHITESP     : ' ' -> skip;
NEWLINE     : ('\r'? '\n' | '\r')+ -> skip ;
ID          : '"'('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'_'|'-'|' '|'0'..'9')* '"';