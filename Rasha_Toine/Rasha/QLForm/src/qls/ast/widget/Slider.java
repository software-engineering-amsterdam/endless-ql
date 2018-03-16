package qls.ast.widget;

import java.util.List;

import qls.visiting.WidgetVisitor;

public  class Slider extends MultiOptionsWidget {
	//constructor
    public Slider(List<String> options, String defaultOption) {
      super(options, defaultOption);
    }
    
	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this,  ctx);
	}
}