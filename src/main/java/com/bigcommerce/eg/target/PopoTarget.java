package com.bigcommerce.eg.target;

import java.io.FileWriter;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Attribute;
import com.bigcommerce.eg.ast.Entity;
import com.bigcommerce.eg.ast.Model;
import static com.google.common.base.CaseFormat.*;

public class PopoTarget extends AbstractTarget {

	@Override
	public void generate(Model model, String outputDirectory)
			throws GenerationException {

		String modelIdentifier = model.getIdentifier();
		
		String namespace = getNamespace(model);
		for (Entity entity : model.getEntities()) {
			String popoOutputName = getPopoFilename(entity, outputDirectory);
			String popo = generatePopo(entity, namespace);
			try {
		        FileWriter outputStream = new FileWriter(popoOutputName);
		        outputStream.write(popo.toString());
		        outputStream.close();
	        } catch (Exception e) {
	        	throw new GenerationException("Could not write the POPO", e);
	        }
		}
		

	}
	
	protected String generatePopo(Entity entity, String namespace) {
		StringBuilder popo = new StringBuilder("<?php");
		
		popo.append(lineSeparator)
			.append(lineSeparator)
			.append("namespace ")
			.append(namespace)
			.append(";")
			.append(lineSeparator)
			.append(lineSeparator)
			.append("class ")
			.append(LOWER_UNDERSCORE.to(UPPER_CAMEL, entity.getIdentifier()))
			.append(" {")
			.append(lineSeparator)
			.append(lineSeparator);
		
		for (Attribute attribute : entity.getAttributes()) {
			popo.append(generateProperty(attribute))
				.append(lineSeparator);
		}
		
		popo.append(lineSeparator)
			.append('}');
		
		
		return popo.toString();
	}
	
	protected String generateProperty(Attribute attribute) {
		StringBuilder property = new StringBuilder("\tprotected $");
		property
			.append(attribute.getIdentifier())
			.append(';');
		return property.toString();
	}
	
	protected String getNamespace(Model model) {
		String modelIdentifier = model.getIdentifier();
		return LOWER_UNDERSCORE.to(UPPER_CAMEL, modelIdentifier);
	}
	
	protected String getPopoFilename(Entity entity, String outputDirectory) {
		return outputDirectory + '/' + LOWER_UNDERSCORE.to(UPPER_CAMEL, entity.getIdentifier()) + ".php";
	}

}
