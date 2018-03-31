package nl.khonraad.ql.cdi;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
 
@Decorator
public abstract class QuestionDecorator implements Visualizing{
 
    @Inject
    @Delegate
    @Any
    private Visualizing v;
 
    public String name(int id) {
        return "decorator: {" + v.name(id) + "}";
    }
}