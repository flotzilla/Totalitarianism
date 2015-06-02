package com.flyonthewall.totalitarianismUI;

import com.flyonthewall.totalitarianism.FileManager;
import com.flyonthewall.totalitarianism.FmFile;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 0byte
 */
public class ViewerPanel extends JPanel {

    private ExtendedTabbetPane parent;
    private FmFile fmF; //file opened now in tab
    private FmFile[] fileArray;
    private String currentFolderName;
    private DefaultTableModel tableModel;
    private final Logger logger = LoggerFactory.getLogger(ViewerPanel.class);
    private ListSelectionModel selectionModel;

    public ViewerPanel(FmFile file, ExtendedTabbetPane parent) {
        this.parent = parent;
        fmF = file;
        initComponents();
        initTab();
    }

    public void clearTable() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    public FmFile getFmF() {
        return fmF;
    }

    public String getCurrentFolderName() {
        return currentFolderName;
    }

    public void updateTabUI() {
        if (parent.getSelectedIndex() > -1) {
            try {
                parent.getParentPanel().getDeviceButtonPanel().
                        getSelectedDeviceButt(fmF.getFilePath()).updateButton();
                logger.debug("Current fmf is {}", fmF.getFileName());
            } catch (NullPointerException e) {
                logger.error("Device not found, {}", e);
            }
        }
    }

    private void setCurrentFolderName(String currentFolderName) {
        this.currentFolderName = currentFolderName;
        pathTextField.setText(this.currentFolderName);
    }

    private void initTab() {
        setDefKeyboardkeys();
        tableModel = (DefaultTableModel) jTable1.getModel();
        selectionModel = jTable1.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelector(jTable1, tableModel));

        TableRowSorter<DefaultTableModel> sorted = new TableRowSorter<DefaultTableModel>(tableModel);
//        sorted.setComparator(, null);
        jTable1.setRowSorter(sorted);

        jTable1.setColumnSelectionAllowed(false);
        jTable1.setDefaultRenderer(String.class, new myTableCellRender());
        openDir(fmF);       
    }

    public void openDir(FmFile fmFile) {
        try {
            FmFile[] roots = fmFile.getRoots();

            logger.debug("Previous fmf path {}", fmFile.getFilePath());
            logger.debug("Previous fmf name {}", fmFile.getFileName());
            fmF = fmFile;

            logger.debug("Curr fmf name {}", fmFile.getFileName());
            logger.debug("Curr fmf path {}", fmFile.getFilePath());

            fileArray = roots;
            for (FmFile f : fileArray) {
                Object[] toArray = f.getFileParams().toArray();

                tableModel.addRow(toArray);
            }
            setCurrentFolderName(fmF.getFilePath());

        } catch (SecurityException e) {
            logger.error("Security exception at file {}", fmFile.getFilePath());
            JOptionPane.showMessageDialog(null, "Cannot open dir",
                    "Permission denied", JOptionPane.ERROR_MESSAGE);

            openDir(fmF);
        } catch (NullPointerException npe) {
            logger.error("Directory doesn't exist", fmFile.getFilePath());
            JOptionPane.showMessageDialog(null, "Cannot open dir",
                    "Directory doesn't exist", JOptionPane.ERROR_MESSAGE);

            openDir(fmF);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pathTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable1.setBackground(new java.awt.Color(223, 223, 223));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Size", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setFillsViewportHeight(true);
        jTable1.setInheritsPopupMenu(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);

        pathTextField.setAutoscrolls(false);
        pathTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                openAdress(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setText("*");
        jButton1.setBorder(null);
        jButton1.setFocusable(false);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton2.setText("H");
        jButton2.setBorder(null);
        jButton2.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pathTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathTextField)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void openAdress(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_openAdress
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            String text = pathTextField.getText();
            if (!text.isEmpty()) {
                File file = new File(text);
                if (file.exists() || file.isDirectory()) {
                    clearTable();
                    openDir(new FmFile(file));
                    updateTabUI();
                }
            }
            //else do nothing, stay in this catalog
        }
    }//GEN-LAST:event_openAdress
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField pathTextField;
    // End of variables declaration//GEN-END:variables

    private void setDefKeyboardkeys() {
        jTable1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                "openSelectedItem");
        jTable1.getActionMap().put("openSelectedItem", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                openSelectedItem();
            }
        });

        jTable1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0),
                "goHigher");
        jTable1.getActionMap().put("goHigher", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                logger.debug("backspace pressed");
                goHigher();
            }
        });

        //open new tab
        jTable1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                InputEvent.CTRL_MASK), "duplicateTab");
        jTable1.getActionMap().put("duplicateTab", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                addSameTab();
            }
        });

        jTable1.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                InputEvent.CTRL_MASK), "closeTab");
        jTable1.getActionMap().put("closeTab", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (parent.getTabCount() > 0) {
                    parent.removeTabAt(parent.getSelectedIndex());
                }

            }
        });
    }

    private void openSelectedItem() {
        int selectedRow = jTable1.getSelectedRow();
        File file = fileArray[selectedRow].getFile();
        if (file.isFile()) {
            FileManager.openFile(file);
        } else {
            clearTable();
            openDir(fileArray[selectedRow]);
            updateTabUI();
        }
    }

    private void goHigher() {
        File[] roots = FileManager.getRoots();
        boolean isRootdir = false;
        for (File file : roots) {
            if (fmF.getFilePath().endsWith(file.getAbsolutePath())) {
                isRootdir = true;
                logger.debug("You cannot fo over root dir");
            }
        }
        if (!isRootdir) {
            clearTable();
            openDir(FileManager.getParentDir(fmF));
            updateTabUI();
        }
    }

    public void addSameTab() {
        parent.addTab(fmF.getFileName(), new ViewerPanel(fmF, parent));
        parent.setSelectedIndex(parent.getTabCount() - 1);
        updateTabUI();
    }

    private void goToLastItem() {
    }
}
