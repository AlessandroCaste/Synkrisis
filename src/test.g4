grammar test;

wow : textr
    | miaor
    ;
textr : TEXT;
miaor : MIAO;

TEXT  : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
MIAO : 'miao';
