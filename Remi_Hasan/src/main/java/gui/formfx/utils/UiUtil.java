package gui.formfx.utils;

import gui.formfx.field.FieldGroup;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by YoriChan on 2017/4/19
 * Some utils
 */
public class UiUtil {

    public static void addIfNotNull(Pane pane, Node... nodes) {
        for (Node n : nodes) {
            if (n != null)
                pane.getChildren().add(n);
        }
    }

    /**
     * 通过路径获取图片
     * @param path
     */
    public static ImageView getImg(String path) {
        return new ImageView(new Image(path));
    }

    public static Node getNodeById(String id, FieldGroup fieldGroup) {
        for (int i = 0; i < fieldGroup.size(); i++) {
            Node node = fieldGroup.get(i).getControl();
            if (node.getId() == null)
                continue;
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

}
