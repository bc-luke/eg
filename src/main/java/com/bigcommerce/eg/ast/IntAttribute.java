package com.bigcommerce.eg.ast;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class IntAttribute extends Attribute {
	public IntAttribute(Token t) {
		super(t);
	}
	public IntAttribute(CommonTree node) {
		super(node);
	}
}
