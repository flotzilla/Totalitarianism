package com.flyonthewall.totalitarianism;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 0byte
 */
public class FileManager {

    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
    private static FileSystemView fsv = FileSystemView.getFileSystemView();
    private static SecurityManager secManager = System.getSecurityManager();

    public static void openFile(File file) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {

                JOptionPane.showMessageDialog(null, file.getAbsolutePath(),
                        "Error opening file", JOptionPane.ERROR_MESSAGE);
                logger.error("Error opening file {}", file.getAbsolutePath());
            }
        }
    }

    public static File[] getRoots() {
        return File.listRoots();
    }

    public static File[] getRootsHidden(File f) {
        return fsv.getFiles(f, false);
    }

    /**
     * will return system name of your device.
     *
     * @param f must be only one level of nesting, like "C:\\", but not
     * "C:\\Programs"
     * @return system name of your device.
     */
    public static String getSystemDisplayName(File f) {
        return fsv.getSystemDisplayName(f);
    }

    public static String getSystTypeDescr(File f) {
        return fsv.getSystemTypeDescription(f);
    }

    public static FmFile getParentDir(FmFile f) {
        return new FmFile(f.getFile().getParent());
    }

    public static void getParent4Parent() {
    }

    public static String getFreeSpace(File f) {
        return FmFile.sizeFormat(f.getFreeSpace(), false);
    }

    public static String getTotalSpace(File f) {
        return FmFile.sizeFormat(f.getTotalSpace(), false);
    }

    public static FileSystemView getFsv() {
        return fsv;
    }

    public static SecurityManager getSecManager() {
        if (secManager == null ) {
            logger.debug("Secrutity Manager is null");
            return null;
        }
        return secManager;
    }
}
