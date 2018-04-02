package Main;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * PathChooser is a simple implementation of the JFileChooser
 */
public class PathChooser {

    /**
     * Get the path for a specified file
     * @return The path to the file
     */
    public String getFilePath() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return getPath(jfc);
    }

    /**
     * Get the path for a specified file or directory
     * @return The path to the file or directory
     */
    public String getDirectoryPath(){
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        return getPath(jfc);
    }

    private String getPath(JFileChooser jfc){

        int returnValue = jfc.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION)
            return jfc.getSelectedFile().getAbsolutePath();
        else
            return "";
    }
}
