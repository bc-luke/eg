package com.bigcommerce.eg.target;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Model;


public abstract class AbstractTarget implements Target {

	protected String lineSeparator;
	
	@Override
	public void generate(Model model) throws GenerationException {
        generate(model, FilenameHelper.getSourceFileParent(model));
	}
	
	public AbstractTarget() {
		super();
		lineSeparator = System.getProperty("line.separator");
	}

}
