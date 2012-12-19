package com.bigcommerce.eg.target;


public abstract class FileTarget implements Target {

	protected String lineSeparator;
	
	public FileTarget() {
		super();
		lineSeparator = System.getProperty("line.separator");
	}

}
