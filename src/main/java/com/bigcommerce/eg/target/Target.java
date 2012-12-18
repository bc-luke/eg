package com.bigcommerce.eg.target;

import java.io.IOException;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Model;

public interface Target {
	public Target setOutputPath(String path);
	public void generate(Model model) throws GenerationException;
}
