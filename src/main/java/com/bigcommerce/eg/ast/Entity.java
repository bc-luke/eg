package com.bigcommerce.eg.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import com.bigcommerce.eg.EgParser;

public class Entity extends CommonTree {

	public Entity(Token t) {
		super(t);
	}
	
	public Entity(CommonTree node) {
		super(node);
	}

	@Override
	public void addChild(Tree t) {
		if (t instanceof Attribute) {
			String identifier = ((Attribute) t).getIdentifier();
			if (this.hasAttribute(identifier)) {
				throw new RuntimeException("Duplicate attribute \"" + identifier + "\" detected in entity \"" + getIdentifier() + "\"");
			}
		}
		super.addChild(t);
	}

	public List<Attribute> getAttributes() {

		List<Attribute> attributes = new ArrayList<Attribute>();

		@SuppressWarnings("rawtypes")
		List list = this.getChildren();
		if (list == null) {
			return null;
		}

		for (Object o : list) {
			if (o instanceof Attribute) {
				attributes.add((Attribute)o);
			}
		}

		if (attributes.size() == 0) {
			return null;
		}

		return attributes;
	}

	public Attribute getAttribute(String identifier) {

		List<Attribute> attributes = this.getAttributes();

		if (attributes == null) {
			return null;
		}

		for (Attribute attribute : attributes) {
			String existingIdentifier = attribute.getIdentifier();
			if (existingIdentifier.equals(identifier)) {
				return attribute;
			}
		}
		return null;

	}

	public boolean hasAttribute(String identifier) {
		return getAttribute(identifier) != null;
	}

	public String getIdentifier() {
		return this.getToken().getText();
	}

}
