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

model returns [Model m] 
  : 
    ^(MODEL entity*)
  ;

entity returns [Entity e]
  : { $e = new Entity(); }
    IDENTIFIER (attribute { $e.addAttribute($attribute.a); })*
  ;
  
attribute returns [Attribute a]
  : ^(ATTRIBUTE IDENTIFIER ASTERISK? intType) 
    {
      $a = new IntAttribute();
      $a.setIdentifier($IDENTIFIER.text); 
      $a.setRequired($ASTERISK.text != null);
    }
  | ^(ATTRIBUTE IDENTIFIER ASTERISK? stringType) 
    {
      $a = new StringAttribute();
      $a.setIdentifier($IDENTIFIER.text); 
      $a.setRequired($ASTERISK.text != null);
      ((StringAttribute)$a).setDefaultValue($stringType.s.getDefaultValue());
      ((StringAttribute)$a).setSize($stringType.s.getSize());
    }
  ;

intType
   : INT (EQUALS INTEGER_LITERAL)?
   ;

stringType returns [StringTypeDeclaration s]
   : { $s = new StringTypeDeclaration(); }
     STRING 
     (
       LPAREN size=INTEGER_LITERAL RPAREN { $s.setSize(Integer.parseInt($size.text)); }
     )? 
     (
       defaultValue=STRING_LITERAL { $s.setDefaultValue($defaultValue.text); }
     )?
   ;


