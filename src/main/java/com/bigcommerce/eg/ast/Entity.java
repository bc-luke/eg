package com.bigcommerce.eg.ast;

import java.util.LinkedList;
import java.util.List;

public class Entity {
	
	private List<Attribute> attributes;
	
	public Entity() {
		attributes = new LinkedList<Attribute>();
	}
	
	public Entity addAttribute(Attribute attribute) {
		attributes.add(attribute);
		return this;
	}
}
