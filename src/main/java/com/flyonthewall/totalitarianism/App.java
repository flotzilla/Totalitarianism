package com.flyonthewall.totalitarianism;

import com.flyonthewall.totalitarianismUI.MainWindow;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Hello world!
 *
 */
public class App {

    private static MainWindow mw;

    public static void main(String[] args) {
                try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                mw = new MainWindow();
                mw.setVisible(true);
                mw.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

    }
}
