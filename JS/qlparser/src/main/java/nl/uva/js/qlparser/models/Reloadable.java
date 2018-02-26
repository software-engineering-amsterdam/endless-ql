package nl.uva.js.qlparser.models;

import lombok.Getter;
import lombok.NonNull;

import java.util.function.Supplier;

public class Reloadable<T> {
    @NonNull private Supplier<T> supplier;
    @Getter private T value;

    public Reloadable(Supplier<T> supplier) {
        this.supplier = supplier;

        reload();
    }

    public void reload() {
        value = supplier.get();
    }
}
