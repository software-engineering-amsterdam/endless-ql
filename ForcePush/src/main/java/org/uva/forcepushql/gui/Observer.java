package org.uva.forcepushql.gui;

import org.uva.forcepushql.questions.Radio;
import org.uva.forcepushql.questions.Textbox;

public abstract class Observer {
    public abstract void updateRadio(Radio radio);
    public abstract void updateTextbox(Textbox textbox);
}
