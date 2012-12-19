package com.bigcommerce.eg.target;

import java.io.File;
import java.io.FileWriter;

import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Model;

public class DotTarget extends AbstractTarget {

	protected DOTTreeGenerator dotTreeGenerator;
	
	public DotTarget() {
		super();
		this.dotTreeGenerator= new DOTTreeGenerator();
	}
	
	@Override
	public void generate(Model model) throws GenerationException {
        generate(model, super.getSourceFileParent(model));
	}

	@Override
	public void generate(Model model, String outputDirectory) throws GenerationException {
        // Which we can cause to generate the DOT specification
        // with the input file name suffixed with .dot. You can then use
        // the graphviz tools or zgrviewer (Java) to view the graphical
        // version of the dot file.
        //
		String source = model.getToken().getInputStream().getSourceName();
        String outputName = getAbsoluteOuputFilename(model, outputDirectory);

        System.out.println("    Producing AST dot (graphviz) file");

        // It produces a jguru string template.
        //
        StringTemplate st = dotTreeGenerator.toDOT(model, new CommonTreeAdaptor());

        // Create the output file and write the dot spec to it
        //
        try {
	        FileWriter outputStream = new FileWriter(outputName + ".dot");
	        outputStream.write(st.toString());
	        outputStream.close();
        } catch (Exception e) {
        	throw new GenerationException("Could not write the dot spec", e);
        }
        // Invoke dot to generate a .png file
        //
        System.out.println("    Producing png graphic for tree");
        long pStart = System.currentTimeMillis();
        try {
	        Process proc = Runtime.getRuntime().exec("dot -Tpng -o" + outputName + ".png " + outputName + ".dot");
	        proc.waitFor();
        } catch (Exception e) {
        	throw new GenerationException("Could not write the png graphic", e);
        }
        long stop = System.currentTimeMillis();
        System.out.println("      PNG graphic produced in " + (stop - pStart) + "ms.");
	}
	
	
	protected String getAbsoluteOuputFilename(Model model, String outputDirectory) {
		return outputDirectory + '/' + super.getExtensionlessSourceFileName(model);
	}

}
