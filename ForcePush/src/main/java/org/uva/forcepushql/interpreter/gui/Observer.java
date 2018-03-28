package org.uva.forcepushql.interpreter.gui;

import org.uva.forcepushql.interpreter.gui.questions.Radio;
import org.uva.forcepushql.interpreter.gui.questions.Textbox;

public abstract class Observer {
    public abstract void updateRadio(Radio radio);
    public abstract void updateTextbox(Textbox textbox);
}
