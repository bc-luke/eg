package com.bigcommerce.eg.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class Entity extends CommonTree {

	public Entity(Token t) {
		super(t);
	}
	
	public List<Attribute> getAttributes() {
		@SuppressWarnings("rawtypes")
		List list = this.getChildren();
		List<Attribute> attributes = new ArrayList<Attribute>();
		for (Object o : list) {
			if (o instanceof Attribute) {
				attributes.add((Attribute)o);
			}
		}
		return attributes;
	}

}
