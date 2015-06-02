package com.flyonthewall.totalitarianismUI;

import javax.swing.JLabel;

/**
 * @author obyte
 */
public class InformLabel extends JLabel {

    StringBuilder stB;

    public InformLabel() {
        stB = new StringBuilder();
    }

    public void updateInformation(int selectedFiles, int totalFiles,
            int selecredDirs, int totalDirs,
            String fileSizes, String totalFileSizes) {
        //stb clear
        stB.delete(0, stB.length());
        
        String fromString = " from ";
        stB.append(fileSizes);
        stB.append(fromString);
        stB.append(totalFileSizes);
        stB.append(" Files: ");
        stB.append(selectedFiles);
        stB.append(fromString);
        stB.append(totalFiles);
        stB.append(" Dirs: ");
        stB.append(selecredDirs);
        stB.append(fromString);
        stB.append(totalDirs);

        setText(stB.toString());
    }
}
