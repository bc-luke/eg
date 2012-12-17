parser grammar EgParser;

options {

    // Default language but name it anyway
    //
    language  = Java;

    // Produce an AST
    //
    output    = AST;

    // Use a superclass to implement all helper
    // methods, instance variables and overrides
    // of ANTLR default methods, such as error
    // handling.
    //
    superClass = AbstractEgParser;

    // Use the vocabulary generated by the accompanying
    // lexer. Maven knows how to work out the relationship
    // between the lexer and parser and will build the 
    // lexer before the parser. It will also rebuild the
    // parser if the lexer changes.
    //
    tokenVocab = EgLexer;
}

// Some imaginary tokens for tree rewrites
//
tokens {
    SCRIPT;
    ATTRIBUTE;
    ENTITY;
}

// What package should the generated source exist in?
//
@header {

    package com.bigcommerce.eg;
    
    import com.bigcommerce.eg.ast.*;
}


model
    : IDENTIFIER LBRACE entity* RBRACE  -> ^(IDENTIFIER entity*)
    ;

entity
    : IDENTIFIER LBRACE attribute* RBRACE -> ^(IDENTIFIER attribute*)
    ;

attribute
   : IDENTIFIER ASTERISK? COLON intType -> ^(IDENTIFIER ASTERISK? intType)
   | IDENTIFIER ASTERISK? COLON stringType -> ^(IDENTIFIER ASTERISK? stringType)
   ;


intType
   : INT (EQUALS! INTEGER_LITERAL)?
   ;

stringType
   : STRING (LPAREN INTEGER_LITERAL RPAREN)? (EQUALS! STRING_LITERAL)?
   ;