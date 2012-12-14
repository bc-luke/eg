package com.bigcommerce.eg.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import com.bigcommerce.eg.EgParser;

public class Attribute extends CommonTree {


	public Attribute(Token t) {
		super(t);
	}

	public boolean isRequired() {
		
		@SuppressWarnings("rawtypes")
		List list = this.getChildren();
		for (Object o : list) {
			if (o instanceof CommonTree) {
				CommonTree node = ((CommonTree)o);
				Token t = node.getToken();
				if (t.getType() == EgParser.ASTERISK) {
					return true;
				}
			}
		}

		return false;
	}

	public String getIdentifier() {
		return "id";
	}

}
