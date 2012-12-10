package com.bigcommerce.eg.ast;

public class StringTypeDeclaration {
	private int size;
	private String defaultValue;
	
	public int getSize() {
		return size;
	}

	public StringTypeDeclaration setSize(int size) {
		this.size = size;
		return this;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public StringTypeDeclaration setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

}
