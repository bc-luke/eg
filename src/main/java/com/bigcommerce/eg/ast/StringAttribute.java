package com.bigcommerce.eg.ast;

import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import com.bigcommerce.eg.EgParser;

public class StringAttribute extends Attribute {
	public StringAttribute(Token t) {
		super(t);
	}
	public StringAttribute(CommonTree node) {
		super(node);
	}
	
	public String getDefaultValue() {
		@SuppressWarnings("rawtypes")
		List list = this.getChildren();

		if (list == null) {
			return null;
		}

		for (Object o : list) {
			if (o instanceof CommonTree) {
				CommonTree node = ((CommonTree)o);
				Token t = node.getToken();
				if (t.getType() == EgParser.STRING_LITERAL) {
					return t.getText();
				}
			}
		}


		return null;
	}
	
	public boolean hasDefaultValue() {
		return getDefaultValue() != null;
	}

	public int getSize() {
		
		@SuppressWarnings("rawtypes")
		List list = this.getChildren();

		if (list == null) {
			return 0;
		}

		for (Object o : list) {
			if (o instanceof CommonTree) {
				CommonTree node = ((CommonTree)o);
				Token t = node.getToken();
				if (t.getType() == EgParser.INTEGER_LITERAL) {
					return Integer.parseInt(t.getText());
				}
			}
		}

		return 0;
	}
	
	public boolean hasSize() {
		return getSize() != 0;
	}

}
