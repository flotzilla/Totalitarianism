package com.flyonthewall.totalitarianism;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 0byte
 */
public class FmFile {

    private File file;
    private String filePath;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String fileDate;
    private boolean isFile;
    private boolean isDir;
    private static boolean binPrefix;

    public FmFile(String str) {
        this(new File(str));
    }

    public FmFile(File f) {
        this.file = f;
        filePath = file.getAbsolutePath();
        fileType = calcFileType(file);

        if (file.isDirectory()) {
            isFile = false;
            isDir = true;
            if (file.getAbsolutePath().length() == 3) {
                //construction file.getName() for path like C:\ 
                //returns empty string
                fileName = file.getAbsolutePath();
            } else {
                fileName = "[" + file.getName() + "]";
            }
            fileSize = "<Dir>";
        } else {
            isFile = true;
            isDir = false;
            fileName = file.getName();
            fileSize = fileSize(file);
        }

        fileDate = dateFormatter(file);
    }

    private static String calcFileType(File f) {
        if (f.isDirectory()) {
            return "";
        } else {
            int lastIndexOf = f.getName().lastIndexOf(".");
            if (lastIndexOf == -1) {
                return "";
            } else {
                return f.getName().substring(lastIndexOf + 1);
            }
        }
    }

    private static String dateFormatter(File f) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.format(f.lastModified());
    }

    public static String sizeFormat(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.2f %sB", bytes / Math.pow(unit, exp), pre);

    }

    private static String fileSize(File f) {
        if (!f.isDirectory()) {
            if (f.length() == 0) {
                return "0";
            } else {
                return " " + sizeFormat(f.length(), binPrefix);
            }
        } else {
            return sizeFormat(dirSizeCount(f), binPrefix);
        }
    }

    public FmFile[] getRoots() throws SecurityException, NullPointerException {
        File[] list = null;
        try {
            list = file.listFiles();
        } catch (SecurityException e) {
            throw e;
        } catch (NullPointerException npe) {
            throw npe;
        }

        FmFile[] fmFiles = new FmFile[list.length];
        for (int i = 0; i < fmFiles.length; i++) {
            fmFiles[i] = new FmFile(list[i]);
        }

        return fmFiles;
    }

    private static long dirSizeCount(File f) {
        File[] listFiles = f.listFiles();
        if (listFiles.length == 0) {
            return 0;
        } else {
            long totalSize = 0;
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    totalSize += listFiles[i].length();
                } else {
                    totalSize += dirSizeCount(listFiles[i]);
                }
            }
            return totalSize;
        }
    }

    public List<String> getFileParams() {
        List<String> paramsList = new ArrayList<String>(4);
        paramsList.add(fileName);
        paramsList.add(fileType);
        paramsList.add(fileSize);
        paramsList.add(fileDate);
        return paramsList;
    }

    public String getFileModifDate() {
        return fileDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isIsDir() {
        return isDir;
    }

    public boolean isIsFile() {
        return isFile;
    }

    public static boolean isBinPrefix() {
        return binPrefix;
    }

    public static void setBinPrefix(boolean binPrefix) {
        FmFile.binPrefix = binPrefix;
    }
}
