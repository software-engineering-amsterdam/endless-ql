package main;

import model.Unit;

public interface Parser {
    Unit parse(String content);
}
