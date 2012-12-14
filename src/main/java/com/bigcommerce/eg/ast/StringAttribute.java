package com.bigcommerce.eg.ast;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class StringAttribute extends Attribute {
	public StringAttribute(Token t) {
		super(t);
	}
	public StringAttribute(CommonTree node) {
		super(node);
	}
	public String getDefaultValue() {
		return "default";
	}

	public int getSize() {
		return 255;
	}

}
