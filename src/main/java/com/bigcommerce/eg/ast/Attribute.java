package com.bigcommerce.eg.ast;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class Attribute extends CommonTree {


	public Attribute(Token t) {
		super(t);
	}

	public boolean isRequired() {
		return true;
	}

	public String getIdentifier() {
		return "id";
	}

}
