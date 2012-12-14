package com.bigcommerce.eg.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class Model extends CommonTree {
	
	public Model(Token t) {
		super(t);
	}
	
	public Model(CommonTree node) {
		super(node);
	}
	
	public List<Entity> getEntities() {
		@SuppressWarnings("rawtypes")
		List list = this.getChildren();
		List<Entity> entities = new ArrayList<Entity>();
		for (Object o : list) {
			if (o instanceof Entity) {
				entities.add((Entity)o);
			}
		}
		return entities;
	}
}
