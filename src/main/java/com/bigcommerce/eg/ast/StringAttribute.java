package com.bigcommerce.eg.ast;

public class StringAttribute extends Attribute {
	private String defaultValue;
	private int size;
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public StringAttribute setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public int getSize() {
		return size;
	}

	public StringAttribute setSize(int size) {
		this.size = size;
		return this;
	}

}
