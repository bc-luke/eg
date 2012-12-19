package com.bigcommerce.eg.ast;

public class StringTypeDeclaration extends TypeDeclaration {
	
	public int getSize() {
		return 255;
	}

	public String getDefaultValue() {
		return "default";
	}

}
