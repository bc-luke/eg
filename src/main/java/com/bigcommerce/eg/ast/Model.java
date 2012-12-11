package com.bigcommerce.eg.ast;

import java.util.LinkedList;
import java.util.List;

public class Model {
	private List<Entity> entities;
	
	public Model() {
		entities = new LinkedList<Entity>();
	}
	
	public Model addEntity(Entity entity) {
		entities.add(entity);
		return this;
	}
}
