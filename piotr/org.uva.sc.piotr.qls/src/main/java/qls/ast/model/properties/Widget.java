package qls.ast.model.properties;

import qls.ast.model.properties.parameters.OptionalParameters;

public abstract class Widget extends TypeProperty {

    public Widget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    public abstract String getName();

    public abstract OptionalParameters getParameters();
}
