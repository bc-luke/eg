package com.bigcommerce.eg.ast;

import org.antlr.runtime.Token;

public class StringAttribute extends Attribute {
	public StringAttribute(Token t) {
		super(t);
	}
	public String getDefaultValue() {
		return "default";
	}

	public int getSize() {
		return 255;
	}

}
