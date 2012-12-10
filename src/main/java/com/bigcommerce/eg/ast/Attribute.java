package com.bigcommerce.eg.ast;

import org.antlr.runtime.tree.CommonTree;

public class Attribute extends CommonTree {
	private boolean isRequired;
	private String identifier;

	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
}
