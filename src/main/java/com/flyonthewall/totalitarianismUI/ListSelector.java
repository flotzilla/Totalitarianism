/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flyonthewall.totalitarianismUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author obyte
 */
public class ListSelector implements ListSelectionListener, KeyListener {

    private final Logger logger = LoggerFactory.getLogger(ViewerPanel.class);
    private JTable table;
    private DefaultTableModel defTabModel;
    private List selectedFilesList;

    public ListSelector(JTable table, DefaultTableModel defTabModel) {
        this.table = table;
        this.defTabModel = defTabModel;
    }

    public List getSelectedFilesList() {
        if (selectedFilesList == null) {
            logger.debug("selected files are not exists");
            return Collections.emptyList();
        }
        return selectedFilesList;
    }

    public void valueChanged(ListSelectionEvent e) {

        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length == 0) {
            logger.debug("nothing was selected");
        }

        logger.debug("Selected rows");
        for (int i = 0; i < selectedRows.length; i++) {
            int selIndex = selectedRows[i];
            logger.debug("Selected table index {} ", selIndex);

            Object valueAt = defTabModel.getValueAt(selIndex, 0);
        }
    }

    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}