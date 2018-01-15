/*
Project: hepial compiler
Description: This is a compiler for hepial a custom language
			This si the flex file that contain the lexical.
Author: Florian Hassler & Dimitri Lizzi
Date: 14.01.2018
 */

import java_cup.runtime.*;
import java.util.Vector;

%%
%class Smgl
%line
%column
%cup

%%
"programme"             { return new Symbol(sym.PRG);}
"debutprg"              { return new Symbol(sym.STARTPRG);}
"finprg"                { return new Symbol(sym.STARTPRG);}
"debutfonc"             { return new Symbol(sym.STARTFUNC);}
"finfonc"               { return new Symbol(sym.ENDFUNC);}
"constante"             { return new Symbol(sym.CONSTANT);}
"\;"                    { return new Symbol(sym.SEMICOLON);}
"\("                    { return new Symbol(sym.OPENPARENT);}
"\)"                    { return new Symbol(sym.CLOSEPARENT);}
"\="                    { return new Symbol(sym.EQUAL);}
"\,"                    { return new Symbol(sym.COMMA);}
"\:"                    { return new Symbol(sym.DOUBLEPOINTS);}
"\["                    { return new Symbol(sym.OPENBRACK);}
"\]"                    { return new Symbol(sym.CLOSEBRACK);}
"\+"                    { return new Symbol(sym.PLUS);}
"\-"                    { return new Symbol(sym.MINUS);}
"\~"                    { return new Symbol(sym.TILDA);}
"\*"                    { return new Symbol(sym.TIMES);}
"non"                   { return new Symbol(sym.NOT);}
"et"                    { return new Symbol(sym.AND);}
"ou"                    { return new Symbol(sym.OR);}
"\/"                    { return new Symbol(sym.DIVIDE);}
"=="                    { return new Symbol(sym.EQUALS);}
"<>"                    { return new Symbol(sym.DIFF);}
"<"                     { return new Symbol(sym.INF);}
">"                     { return new Symbol(sym.SUP);}
"<="                    { return new Symbol(sym.INFEQUAL);}
">="                    { return new Symbol(sym.SUPEQUAL);}
"vrai"                  { return new Symbol(sym.TRUESYM);}
"faux"                  { return new Symbol(sym.FALSESYM);}
"tantque"               { return new Symbol(sym.WHILESYM);}
"faire"                 { return new Symbol(sym.DOSYM);}
"fintantque"            { return new Symbol(sym.ENDWHILE);}
"si"                    { return new Symbol(sym.IFSYM);}
"alors"                 { return new Symbol(sym.THEN);}
"sinon"                 { return new Symbol(sym.ELSESYM);}
"finsi"                 { return new Symbol(sym.ENDIF);}
"pour"                  { return new Symbol(sym.FORSYM);}
"allantde"              { return new Symbol(sym.FROM);}
"a"                     { return new Symbol(sym.TO);}
"finpour"               { return new Symbol(sym.ENDFOR);}
"lire"                  { return new Symbol(sym.READ);}
"ecrire"                { return new Symbol(sym.WRITE);}
"retourne"              { return new Symbol(sym.RETURNSYM);}
[0-9]+                  { return new Symbol(sym.INTEGERCONST,yytext());}
[\"]([^\"]|\"\")*[\"]   { return new Symbol(sym.STRINGCONST,yytext());}
[a-zA-Z][a-zA-Z0-9]*    { return new Symbol(sym.IDENT,yytext());}
"entier"                { return new Symbol(sym.TINTEGER,yytext());}
"booleen"               { return new Symbol(sym.TBOOLEAN,yytext());}
\/\/[^\n|^\r\n]*        { return new Symbol(sym.COMMENT);}
//don't check spaces, tab and carriage return
[\ |\t|\n|\r|\r\n]  { }