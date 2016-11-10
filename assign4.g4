/**
 * Define a grammar called AIL
 */
 
grammar assign4;
@header {
  
 
}
@members {
    
	}


program
	:block;

block
	: begin statementList end
	;
statementList
	:statements( statements)*;

statements:
	 variableDec
	 | inputDeclaration
	 | printDeclaration
	 | ifstatement
	 | assignmentDeclaration
	 | whilestatement
	 | subblock
     | descriptor
	 | forstatement
	 
	;

subblock: block;

ifstatement		
 : IF conditionblock (ELSE IF conditionblock)* ELSE  stat_block 
 ;


whilestatement
 : WHILE simpleExpression stat_block
 ;
conditionblock
 : simpleExpression stat_block
 ;

stat_block
 : block
 | statements;


variableDec	: 'integer' varint (',' varint )*
| 'real' varreal (	 ',' varreal )*;

varint:IDENT	#Intident
;
varreal:IDENT	#Realident
;
descriptor: 'descriptor' IDENT #DescriptorFunc;

inputDeclaration
:
 'input' inputVariable( ',' inputVariable )*;


inputVariable :IDENT  								#InputVar;
	

printDeclaration : 'print ' factor 					#PrintFactor
|'print ' expressionString						#PrintExpressionStringSimple
;

assignmentDeclaration:
    'let' IDENT '=' '(real)' simpleExpression  		#AssignRealSimpleExpresssion
    | 'let' IDENT '=' '(integer)' simpleExpression  #AssignIntSimpleExpression
	| 'let' IDENT '=' simpleExpression  #AssignSimpleExpression
	| IDENT '=' '(real)' simpleExpression  		#AssignRealSimpleExpresssion
    | IDENT '=' simpleExpression  #AssignSimpleExpression
	| IDENT '=' '(integer)' simpleExpression  #AssignIntSimpleExpression
	
	;
	
expressionString : 
	simpleExpression 					#ExpressionStringSimple
	| TEXT 								#ExpressionStringText ;


simpleExpression returns [Double value]
: first=term '+' next=simpleExpression  #SimpleExpressionAdd
| first=term '-' next=simpleExpression  #SimpleExpressionSub

| simpleExpression op=( NEQ | EQ ) simpleExpression              		#equalityExpr
| simpleExpression op=(  LT | GT | LTEQ | GTEQ) simpleExpression 		#relationalExpr
		
| term 									# SimpleExpressionTerm

;

forstatement
: FOR assignmentDeclaration TO simpleExpression assignmentDeclaration stat_block
;

term returns [Double value]
: first=factor '*' next=term 			#TermMul
| first=factor '/' next=term 			#TermDiv
| factor {$value = $factor.value;}		#TermFactor
;

factor returns [Double value]
: IDENT 								#FactorIdent
| INT 									#FactorInt
| REAL 									#FactorReal
;


begin: 'begin'							#BeginBlock ;

end: 'end' 								#EndBlock;


IF : 'if';

ELSE : 'else';

WHILE : 'while';

FOR : 'for';
TO : 'to';

EQ : '==';	

NEQ : '!=';


LT : '<';

GTEQ : '>=';

GT : '>';

LTEQ : '<=';

IDENT : ('a' .. 'z' | 'A' .. 'Z') ('a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_')* ;

SPACE: [ ] -> skip ;

TEXT  : '"' ('a'..'z' | 'A'..'Z' | '0' .. '9' | ' ' | '_' | ':')* '"' ;

INT : [0-9]+ ;


REAL: ([0-9]+'.'[0-9]*)
| ([0-9]*'.'[0-9]+);

WS  : [\n\r\t]+ -> skip ;
STRING  : ('a'..'z' | 'A'..'Z' | '0' .. '9' | '_')+ ;