package com.flyonthewall.totalitarianismUI;

import com.flyonthewall.totalitarianism.FileManager;
import com.flyonthewall.totalitarianism.FmFile;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author obyte
 */
public class DeviceButtonPanel extends JPanel {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(DeviceButtonPanel.class);
    private JLabel informLabel;
    private List<DeviceButton> deviseButtonsList;
    private ManagerPanel parent;

    public DeviceButtonPanel(ManagerPanel parent) {
        this.parent = parent;
        initButtons();
    }

    private void initButtons() {
        deviseButtonsList = new ArrayList<DeviceButton>();

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 2, 0);
        setLayout(flowLayout);

        updateDevices();

        informLabel = new JLabel();
        add(informLabel);
        setVisible(true);
    }

    public void updateDevices() {
        deviseButtonsList.clear();

        File[] roots = FileManager.getRoots();

        for (File file : roots) {
            DeviceButton devButt = new DeviceButton(file);
            deviseButtonsList.add(devButt);
            add(devButt);
        }
    }

    public void updateInformLabel(File device) {
        String systemDisplayName = FileManager.getSystemDisplayName(device);
        informLabel.setText(systemDisplayName + " [ " + FileManager.getFreeSpace(device)
                + " of " + FileManager.getTotalSpace(device) + " free ]");
    }

    public List<DeviceButton> getDeviseButtonsList() {
        return deviseButtonsList;
    }

    public DeviceButton getSelectedDeviceButt(String path) {
        for (DeviceButton deviceButton : deviseButtonsList) {
            if (path.startsWith(deviceButton.getPath())) {
                return deviceButton;
            }
        }
        logger.debug("Cannot find partition");
        return null;
    }

    public class DeviceButton extends JButton {

        private String path;
        private File device;              

        public DeviceButton(File f) {
            device = f;
            path = f.getAbsolutePath().substring(0, 1);
            this.setFont(new java.awt.Font("Tahoma", 0, 9));
            setSize(41, 30);
            setText(path);
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    pressTheRedButton();
                }
            });
        }
        

        public String getPath() {
            return path;
        }

        public void updateButton() {
            updateInformLabel(device);

            Color oldColor = getBackground();
            for (DeviceButton deviceButton : deviseButtonsList) {
                deviceButton.setForeground(oldColor);
            }
            setForeground(Color.RED);
            
            ExtendedTabbetPane extendedTabbetPane = parent.getExtendedTabbetPane();
            if (device.getAbsolutePath().length() >3) {
                extendedTabbetPane.setTitleAt(extendedTabbetPane.getSelectedIndex(), path + ":"
                    + extendedTabbetPane.getSelectedViwerPanel().getFmF().getFileName());
            }else{
                extendedTabbetPane.setTitleAt(extendedTabbetPane.getSelectedIndex(), path + ":\\");
            }     
            
        }

        public void pressTheRedButton() {
            updateButton();            
            parent.getExtendedTabbetPane().getSelectedViwerPanel().clearTable();
            parent.getExtendedTabbetPane().getSelectedViwerPanel().openDir(new FmFile(device));
        }
    }
}
