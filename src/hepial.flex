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
%class Hepial
%line
%column
%cup

%%
"programme"             { System.out.println(yytext()); return new Symbol(sym.PRG);}
"debutprg"              {System.out.println(yytext()); return new Symbol(sym.STARTPRG);}
"finprg"                {System.out.println(yytext()); return new Symbol(sym.ENDPRG);}
"debutfonc"             {System.out.println(yytext()); return new Symbol(sym.STARTFUNC);}
"finfonc"               {System.out.println(yytext()); return new Symbol(sym.ENDFUNC);}
"constante"             {System.out.println(yytext()); return new Symbol(sym.CONSTANT);}
"\;"                    {System.out.println(yytext()); return new Symbol(sym.SEMICOLON);}
"\("                    {System.out.println(yytext()); return new Symbol(sym.OPENPARENT);}
"\)"                    {System.out.println(yytext()); return new Symbol(sym.CLOSEPARENT);}
"\="                    {System.out.println(yytext()); return new Symbol(sym.EQUAL);}
"\,"                    {System.out.println(yytext()); return new Symbol(sym.COMMA);}
".."                    {System.out.println(yytext()); return new Symbol(sym.DOUBLEPOINTS);}
"\["                    {System.out.println(yytext()); return new Symbol(sym.OPENBRACK);}
"\]"                    {System.out.println(yytext()); return new Symbol(sym.CLOSEBRACK);}
"\+"                    {System.out.println(yytext()); return new Symbol(sym.PLUS);}
"\-"                    {System.out.println(yytext()); return new Symbol(sym.MINUS);}
"\~"                    {System.out.println(yytext()); return new Symbol(sym.TILDA);}
"\*"                    {System.out.println(yytext()); return new Symbol(sym.TIMES);}
"non"                   {System.out.println(yytext()); return new Symbol(sym.NOT);}
"et"                    {System.out.println(yytext()); return new Symbol(sym.AND);}
"ou"                    {System.out.println(yytext()); return new Symbol(sym.OR);}
"\/"                    {System.out.println(yytext()); return new Symbol(sym.DIVIDE);}
"=="                    {System.out.println(yytext()); return new Symbol(sym.EQUALS);}
"<>"                    {System.out.println(yytext()); return new Symbol(sym.DIFF);}
"<"                     {System.out.println(yytext()); return new Symbol(sym.INF);}
">"                     {System.out.println(yytext()); return new Symbol(sym.SUP);}
"<="                    {System.out.println(yytext()); return new Symbol(sym.INFEQUAL);}
">="                    {System.out.println(yytext()); return new Symbol(sym.SUPEQUAL);}
"vrai"                  {System.out.println(yytext()); return new Symbol(sym.TRUESYM);}
"faux"                  {System.out.println(yytext()); return new Symbol(sym.FALSESYM);}
"tantque"               {System.out.println(yytext()); return new Symbol(sym.WHILESYM);}
"faire"                 {System.out.println(yytext()); return new Symbol(sym.DOSYM);}
"fintantque"            {System.out.println(yytext()); return new Symbol(sym.ENDWHILE);}
"si"                    {System.out.println(yytext()); return new Symbol(sym.IFSYM);}
"alors"                 {System.out.println(yytext()); return new Symbol(sym.THEN);}
"sinon"                 {System.out.println(yytext()); return new Symbol(sym.ELSESYM);}
"finsi"                 {System.out.println(yytext()); return new Symbol(sym.ENDIF);}
"pour"                  {System.out.println(yytext()); return new Symbol(sym.FORSYM);}
"allantde"              {System.out.println(yytext()); return new Symbol(sym.FROM);}
"a"                     {System.out.println(yytext()); return new Symbol(sym.TO);}
"finpour"               {System.out.println(yytext()); return new Symbol(sym.ENDFOR);}
"lire"                  {System.out.println(yytext()); return new Symbol(sym.READ);}
"ecrire"                {System.out.println(yytext()); return new Symbol(sym.WRITE);}
"retourne"              {System.out.println(yytext()); return new Symbol(sym.RETURNSYM);}
"entier"                {System.out.println(yytext()); return new Symbol(sym.TINTEGER,yytext());}
"booleen"               {System.out.println(yytext()); return new Symbol(sym.TBOOLEAN,yytext());}
[0-9]+                  {System.out.println(yytext()); return new Symbol(sym.INTEGERCONST,Integer.valueOf(yytext()).intValue());}
[\"]([^\"]|\"\")*[\"]   {System.out.println(yytext()); return new Symbol(sym.STRINGCONST,yytext());}
[a-zA-Z][a-zA-Z0-9]*    {System.out.println(yytext()); return new Symbol(sym.IDENT,yytext());}
\/\/([^\n]|[^\r\n])*    {System.out.println(yytext()); return new Symbol(sym.COMMENT);}
//don't check spaces, tab and carriage return
[\ |\t|\n|\r|\r\n]  { }