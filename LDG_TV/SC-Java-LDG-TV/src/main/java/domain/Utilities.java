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
}
