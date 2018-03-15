package qls.ast.widget;

import java.util.List;

import qls.visiting.WidgetVisitor;

public class RadioBtn extends MultiOptionsWidget {
	//constructor
    public RadioBtn(List<String> options, String defaultOption) {
      super(options, defaultOption);
    }
    
	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}