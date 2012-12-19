package com.bigcommerce.eg.target;

import java.io.FileWriter;

import com.bigcommerce.eg.GenerationException;
import com.bigcommerce.eg.ast.Attribute;
import com.bigcommerce.eg.ast.Entity;
import com.bigcommerce.eg.ast.IntAttribute;
import com.bigcommerce.eg.ast.Model;
import com.bigcommerce.eg.ast.StringAttribute;

import static com.google.common.base.CaseFormat.*;

public class MySqlTarget extends FileTarget {
	
	@Override
	public void generate(Model model) throws GenerationException {
		
	}

	@Override
	public void generate(Model model, String outputDirectory)
			throws GenerationException {
		String sqlOutputName = FilenameHelper.getSimpleFilename(model, "sql", outputDirectory);
		
		StringBuilder sql = new StringBuilder("CREATE DATABASE " + model.getIdentifier() + ';' + lineSeparator);
		
		for (Entity entity : model.getEntities()) {
			sql.append(generateCreateTable(entity));
		}
		
		try {
	        FileWriter outputStream = new FileWriter(sqlOutputName);
	        outputStream.write(sql.toString());
	        outputStream.close();
        } catch (Exception e) {
        	throw new GenerationException("Could not write the dot spec", e);
        }
		
	}
	
	protected String generateCreateTable(Entity entity) {
		StringBuilder create = new StringBuilder("CREATE TABLE ");
		create.append(LOWER_CAMEL.to(LOWER_UNDERSCORE, entity.getIdentifier()))
			.append("(")
			.append(lineSeparator);
		
		boolean isFirst = true;
		for (Attribute attribute : entity.getAttributes()) {
			if (!isFirst) {
				create
					.append(",")
					.append(lineSeparator);
			}
			create.append(generateColumn(attribute));
			if (isFirst) {
				isFirst = false;
			}
		}
		create
			.append(lineSeparator)
			.append(");");
		return create.toString();
	}
	
	protected String generateColumn(Attribute attribute) {
		if (attribute instanceof IntAttribute) {
			return "  " + generateColumn((IntAttribute)attribute);
		} else if (attribute instanceof StringAttribute) {
			return "  " + generateColumn((StringAttribute)attribute);
		} else {
			return "";
		}
	}
	
	protected String generateColumn(IntAttribute attribute) {
		StringBuilder column = new StringBuilder(attribute.getIdentifier());
		column
			.append(" INT")
			.append(generateColumnModifiers(attribute));
		return column.toString();
	}
	
	protected String generateColumn(StringAttribute attribute) {
		StringBuilder column = new StringBuilder(attribute.getIdentifier());
		column.append(" VARCHAR");
		if (attribute.hasSize()) {
			column
				.append('(')
				.append(attribute.getSize())
				.append(')');
		}
		if (attribute.hasDefaultValue()) {
			column
				.append(" DEFAULT ")
				.append(attribute.getDefaultValue());
		}
		column.append(generateColumnModifiers(attribute));
		return column.toString();
	}
	
	protected String generateColumnModifiers(Attribute attribute) {
		StringBuilder modifiers = new StringBuilder();
		
		if (attribute.isRequired()) {
			modifiers.append(" NOT NULL");
		}
		if (attribute.getIdentifier().equals("id")) {
			modifiers.append(" PRIMARY KEY");
		}
		return modifiers.toString();
	}
	
	
	
}
