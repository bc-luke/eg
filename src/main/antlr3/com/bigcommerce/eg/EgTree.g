tree grammar EgTree;

options {

    // Default but name it anyway
    //
    language   = Java;

    // Use the vocab from the parser (not the lexer)
    // The ANTLR Maven plugin knows how to work out the
    // relationships between the .g files and it will build
    // the tree parser after the parser. It will also rebuild
    // the tree parser if the parser is rebuilt.
    //
    tokenVocab = EgParser;

    // Use ANTLR built-in CommonToken for tree nodes
    //
    ASTLabelType = CommonTree;
}

// What package should the generated source exist in?
//
@header {

    package com.bigcommerce.eg;
    import com.bigcommerce.eg.ast.*;
}

model
  :
    ^(IDENTIFIER (
      entity 
    )*)
  ;

entity
  :
    ^(IDENTIFIER (attribute)*)
  ;
  
attribute
  : ^(IDENTIFIER ASTERISK? intType) 

  | ^(IDENTIFIER ASTERISK? stringType) 

  ;

intType
   : INT (EQUALS INTEGER_LITERAL)?
   ;

stringType
   : 
     STRING
     (
       LPAREN size=INTEGER_LITERAL RPAREN
     )? 
     (
       defaultValue=STRING_LITERAL
     )?
   ;


