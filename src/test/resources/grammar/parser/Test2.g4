grammar Test2;

program             : block DOT;

block               : constDeclaration? varDeclaration? procedure* operator ;

constDeclaration    : CONST (constAssign COMMA)* constAssign SEMICOLON ;
constAssign         : ID EQUAL NUMBER ;

varDeclaration      : VAR (varAssign)* ID SEMICOLON ;
varAssign           : ID COMMA;

procedure           : PROCEDURE ID SEMICOLON block SEMICOLON ;

operator            : operatorAssign
                    | operatorCall
                    | operatorBegin
                    | operatorIf
                    | operatorWhile
                    ;

operatorAssign      : ID ASSIGN expression ;
operatorCall        : CALL ID ;
operatorBegin       : BEGIN (operator SEMICOLON)* operator SEMICOLON? END ;
operatorIf          : IF condition THEN operator ;
operatorWhile       : WHILE condition DO operator ;

condition           : conditionOdd
                    | conditionComparison
                    ;

conditionOdd        : ODD expression ;
conditionComparison : expression comparison expression ;

comparison          : EQUAL
                    | NOTEQUAL
                    | LESSTHAN
                    | GREATTHAN
                    | LESSTHAN
                    | GREATOREQUALTHAN
                    | LESSOREQUALTHAN;

expression          : exprSign? summand (exprSign summand)*;

exprSign            : PLUS
                    | MINUS ;

summand             : multiplier (multSign multiplier)*;

multSign            : TIMES
                    | SLASH ;

multiplier          : multiplierId
                    | multiplierNumber
                    | multiplierExpression ;

multiplierId        : ID ;
multiplierNumber    : NUMBER ;
multiplierExpression: LPAREN expression RPAREN ;

BEGIN       : 'BEGIN';
CALL        : 'CALL';
CONST       : 'CONST';
DO          : 'DO';
END         : 'END';
IF          : 'IF';
ODD         : 'ODD';
PROCEDURE   : 'PROCEDURE';
THEN        : 'THEN';
VAR         : 'VAR';
WHILE       : 'WHILE';

PLUS        : '+';
MINUS       : '-';
TIMES       : '*';
SLASH       : '/';
LPAREN      : '(';
RPAREN      : ')';

COMMA       : ',';
DOT         : '.';
COLON       : ':';
SEMICOLON   : ';';
ASSIGN      : ':=';

EQUAL               : '=';
NOTEQUAL            : '!=';
LESSTHAN            : '<';
GREATTHAN           : '>';
LESSOREQUALTHAN     : '<=';
GREATOREQUALTHAN    : '>=';

ID          : [a-zA-Z]+;
NUMBER      : [0-9]+;
WS          : [ \n\t\r]+ -> skip;
