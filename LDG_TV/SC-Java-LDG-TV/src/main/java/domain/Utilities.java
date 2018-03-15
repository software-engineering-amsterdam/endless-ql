package domain;

import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class Utilities {

    /**
     * Reads the specified file based on file path.
     * @param filepath file path of the file to be read
     * @return Lines of text or Empty if the file can't be read
     */
    public static Optional<String> readFile(String filepath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line = br.readLine();

            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

            return Optional.of(sb.toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    /**
     * Checks if a string is numeric
     * Source: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
