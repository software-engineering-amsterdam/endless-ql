package tool;

import javafx.scene.control.Alert;
import loader.QL.LoaderErrorListener;

public class DialogErrorListener implements LoaderErrorListener {
    @Override
    public void onError(String message) {
        System.out.println("error " + message);
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}
