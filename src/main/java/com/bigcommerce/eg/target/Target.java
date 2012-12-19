package com.bigcommerce.eg.target;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Model;

public interface Target {
	public void generate(Model model) throws GenerationException;
	public void generate(Model model, String outputDirectory) throws GenerationException;
}
