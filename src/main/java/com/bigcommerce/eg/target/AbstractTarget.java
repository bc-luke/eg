package com.bigcommerce.eg.target;

public abstract class AbstractTarget implements Target {
	
	protected String outputPath; 
	
	public Target setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		return this;
	}
	
	public String getOutputPath() {
		return this.outputPath;
	}
}
