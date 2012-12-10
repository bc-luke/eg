package com.bigcommerce.eg.ast;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;

public class Entity extends CommonTree {
	
	private List<Attribute> attributes;
	
	public Entity addAttribute(Attribute attribute) {
		attributes.add(attribute);
		return this;
	}
}
