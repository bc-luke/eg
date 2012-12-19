package com.bigcommerce.eg.target;

import java.io.File;

import org.antlr.runtime.tree.CommonTree;

public class FilenameHelper {
	
	public static String getSimpleFilename(CommonTree tree, String extension, String outputDirectory) {
		return outputDirectory + '/' + FilenameHelper.getExtensionlessSourceFilename(tree) + "." + extension;
	}
	
	public static String getSimpleFilename(CommonTree tree, String extension) {
		return FilenameHelper.getSimpleFilename(tree, extension, FilenameHelper.getSourceFileParent(tree));
	}
	
	public static boolean prepareDirectoryForFilename(String filename) {
		File file = new File(filename);
		File parentFile = file.getParentFile();
		boolean result = parentFile.mkdirs();
		return result;
	}
	
	protected static String getSourceFileParent(CommonTree tree) {
		String source = tree.getToken().getInputStream().getSourceName();
		File file = new File(source);
		return file.getParent();
	}
	
	protected static String getExtensionlessSourceFilename(CommonTree tree) {
		String sourceName = tree.getToken().getInputStream().getSourceName();
		File sourceFile = new File(sourceName);
		String sourceFilename = sourceFile.getName();
		String[] tokens = sourceFilename.split("\\.(?=[^\\.]+$)");
		if (tokens.length > 0) {
			return tokens[0];
		} else {
			return sourceFilename;
		}
	}
}
