import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("QL form file selector");

        // Build file selector
        Button fileSelectorButton = createFileSelectorButton(stage);

        // Put button inside a box with spacing
        VBox vBox = new VBox(35);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(fileSelectorButton);

        // Create entire scene
        Scene scene = new Scene(vBox, 300, 100);
        stage.setScene(scene);
        stage.show();
    }

    private Button createFileSelectorButton(Stage stage) {
        // Resource path TODO: remove after debugging
        File resourcesFile = new File(getClass().getResource("java/example.ql").getFile());
        File resourceDirectory = new File(resourcesFile.getParentFile().getAbsolutePath());

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(resourceDirectory);
        final Button openButton = new Button("Browse files...");

        openButton.setOnAction((event) -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Renderer renderer = new Renderer(file);
                renderer.renderForm(stage);
            }
        });

        return openButton;
    }
}