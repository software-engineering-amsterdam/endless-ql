package QL.Evaluation.Values;

import QL.Evaluation.Value;

import java.time.LocalDate;

public class DateValue extends Value<LocalDate> {
    public DateValue(LocalDate value){
        super(value);
    }
}
