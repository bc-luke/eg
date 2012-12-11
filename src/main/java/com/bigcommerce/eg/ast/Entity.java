package com.bigcommerce.eg.ast;

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

public class Entity extends CommonTree {
	
	private List<Attribute> attributes;
	
	public Entity() {
		attributes = new LinkedList<Attribute>();
	}
	
	public Entity addAttribute(Attribute attribute) {
		attributes.add(attribute);
		return this;
	}
}
