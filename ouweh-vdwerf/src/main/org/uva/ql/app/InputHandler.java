package org.uva.ql.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class InputHandler {

    public String readFile(String location) {
        String result = "";
        try {
            byte[] a = Files.readAllBytes(Paths.get(location));
            result = new String(a);
        } catch (IOException e) {
            Logger.getGlobal().severe("There was an error reading the input file: " + e);
            e.printStackTrace();
        }
        return result;
    }
}
