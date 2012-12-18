package com.bigcommerce.eg.target;

import java.io.FileWriter;

import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.stringtemplate.StringTemplate;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Model;

public class DotTarget extends AbstractTarget {

	@Override
	public void generate(Model model) throws GenerationException {
		
		DOTTreeGenerator gen = new DOTTreeGenerator();

        // Which we can cause to generate the DOT specification
        // with the input file name suffixed with .dot. You can then use
        // the graphviz tools or zgrviewer (Java) to view the graphical
        // version of the dot file.
        //
		String source = model.getToken().getInputStream().getSourceName();
        source = source.substring(0, source.length()-3);
        String outputName = source + "dot";

        System.out.println("    Producing AST dot (graphviz) file");

        // It produces a jguru string template.
        //
        StringTemplate st = gen.toDOT(model, new CommonTreeAdaptor());

        // Create the output file and write the dot spec to it
        //
        try {
	        FileWriter outputStream = new FileWriter(outputName);
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
	        Process proc = Runtime.getRuntime().exec("dot -Tpng -o" + source + "png " + outputName);
	        proc.waitFor();
        } catch (Exception e) {
        	throw new GenerationException("Could not write the png graphic", e);
        }
        long stop = System.currentTimeMillis();
        System.out.println("      PNG graphic produced in " + (stop - pStart) + "ms.");
	}

}
