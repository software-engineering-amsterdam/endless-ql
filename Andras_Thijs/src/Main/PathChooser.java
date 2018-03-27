package Main;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class PathChooser {


    public String getFilePath() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return getPath(jfc);
    }

    public String getDirectoryPath(){
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        return getPath(jfc);
    }

    private String getPath(JFileChooser jfc){

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION)
            return jfc.getSelectedFile().getAbsolutePath();
        else
            return "";
    }


}
