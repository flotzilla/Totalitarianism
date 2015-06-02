package com.flyonthewall.totalitarianismUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTabbedPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author obyte
 */
public class ExtendedTabbetPane extends JTabbedPane {

    private final Logger logger = LoggerFactory.getLogger(ExtendedTabbetPane.class);
    private final ManagerPanel parentPanel;

    public ExtendedTabbetPane(final ManagerPanel parentPanel) {

        this.parentPanel = parentPanel;

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

                boolean isCursorOnTab = false;

                if (e.getClickCount() > 1) {
                    int tabCount = getTabCount();
                    for (int i = 0; i < tabCount; i++) {
                        //close tab
                        if (getBoundsAt(i).contains(e.getX(), e.getY())) {
                            isCursorOnTab = true;
                            if (i > 0) {
                                removeTabAt(i);
                                repaint();
                                logger.info("tab was deleted");
                            }
                            break;
                        }
                    }

                    //open new tab on double click on free space in tab
                    if (!isCursorOnTab) {
                        getSelectedViwerPanel().addSameTab();
                        setSelectedIndex(getTabCount() - 1);
                    }

                } else {
                    String filePath = getSelectedViwerPanel().getFmF().getFilePath();
                    try {
                        parentPanel.getDeviceButtonPanel().getSelectedDeviceButt(filePath).pressTheRedButton();
//                        updatePaneUI();
                    } catch (NullPointerException npe) {
                        logger.debug("Cannot find filePath {}", filePath);
                    }

                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });
    }

    public ViewerPanel getSelectedViwerPanel() {
        int selectedIndex = getSelectedIndex();
        return (ViewerPanel) getComponentAt(selectedIndex);
    }

    public void updatePaneUI() {
        int selectedIndex = getSelectedIndex();
        if (selectedIndex > -1) {
            setTitleAt(selectedIndex, getSelectedViwerPanel().getFmF().getFileName());
        }
    }

    public ManagerPanel getParentPanel() {
        return parentPanel;
    }
}
