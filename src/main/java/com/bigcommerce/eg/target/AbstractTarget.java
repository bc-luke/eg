package com.bigcommerce.eg.target;

import java.io.File;

import com.bigcommerce.eg.ast.Model;

public abstract class AbstractTarget implements Target {
	
	protected String getSourceFileParent(Model model) {
		String source = model.getToken().getInputStream().getSourceName();
		File file = new File(source);
		return file.getParent();
	}
	
	protected String getExtensionlessSourceFileName(Model model) {
		String sourceName = model.getToken().getInputStream().getSourceName();
		File sourceFile = new File(sourceName);
		String sourceFileName = sourceFile.getName();
		String[] tokens = sourceFileName.split("\\.(?=[^\\.]+$)");
		if (tokens.length > 0) {
			return tokens[0];
		} else {
			return sourceFileName;
		}
	}
	
}
