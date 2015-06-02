package com.flyonthewall.totalitarianismUI;

import com.flyonthewall.totalitarianism.FileManager;
import com.flyonthewall.totalitarianism.FmFile;
import java.io.File;

/**
 * @author obyte
 */
public class ManagerPanel extends javax.swing.JPanel {

    public ManagerPanel() {
        initComponents();

        File[] roots = FileManager.getRoots();
        if (roots[0].exists() && roots[1].exists()) {
            extendedTabbetPane1.addTab(roots[0].getPath(),
                    new ViewerPanel(new FmFile(roots[0]), extendedTabbetPane1));
            
            extendedTabbetPane1.addTab(roots[1].getPath(),
                    new ViewerPanel(new FmFile(roots[1]), extendedTabbetPane1));
        }
    }

    public DeviceButtonPanel getDeviceButtonPanel() {
        return deviceButtonPanel1;
    }

    public ExtendedTabbetPane getExtendedTabbetPane() {
        return extendedTabbetPane1;
    }

    public InformLabel getInformLabel() {
        return informLabel2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deviceButtonPanel1 = new com.flyonthewall.totalitarianismUI.DeviceButtonPanel(this);
        informLabel2 = new com.flyonthewall.totalitarianismUI.InformLabel();
        extendedTabbetPane1 = new com.flyonthewall.totalitarianismUI.ExtendedTabbetPane(this);

        informLabel2.setText("informLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deviceButtonPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(informLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 193, Short.MAX_VALUE))
            .addComponent(extendedTabbetPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deviceButtonPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extendedTabbetPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(informLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.flyonthewall.totalitarianismUI.DeviceButtonPanel deviceButtonPanel1;
    private com.flyonthewall.totalitarianismUI.ExtendedTabbetPane extendedTabbetPane1;
    private com.flyonthewall.totalitarianismUI.InformLabel informLabel2;
    // End of variables declaration//GEN-END:variables
}
