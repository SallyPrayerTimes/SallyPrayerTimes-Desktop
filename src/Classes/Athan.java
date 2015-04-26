/*******************************Copyright Block*********************************
 *                                                                             *
 *                Sally Prayer Times Calculator (Final 1.2.15)                 *
 *           Copyright (C) 2015 http://www.sallyproject.altervista.org/        *
 *                         bibali1980@gmail.com                              *
 *                                                                             *
 *     This program is free software: you can redistribute it and/or modify    *
 *     it under the terms of the GNU General Public License as published by    *
 *      the Free Software Foundation, either version 3 of the License, or      *
 *                      (at your option) any later version.                    *
 *                                                                             *
 *       This program is distributed in the hope that it will be useful,       *
 *        but WITHOUT ANY WARRANTY; without even the implied warranty of       *
 *        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the        *
 *                 GNU General Public License for more details.                *
 *                                                                             *
 *      You should have received a copy of the GNU General Public License      *
 *      along with this program.  If not, see http://www.gnu.org/licenses      *
 *******************************************************************************/
package Classes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Athan extends JFrame {

    private static final long serialVersionUID = 1L;

    private JFrame mainFrame;//main fram of athan notification 
    private ImagePanel mainPanel;//athan form background image
    private Image backgroundImage;//background image
    private ImageIcon exitIcon;//exit icon
    private JLabel exitLabel;//exit
    private JLabel athanLabel;//actual athan
    private JButton stopAthanButton;//stop athan button
    private final int athanNameCode;//athan name properties code
    private final int screenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;//screen height
    private final int screenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;//screen width
    private Font font;//actual athan font
    private int mouseInitX;//initial horizontal position
    private int mouseInitY;//initial vertical position
    private String athanType;//athan type
    private AthanPlayer athanPlayer;//athanPlayer object to play athan

    public Athan(String athanType, int athanNameCode, ImageIcon exitIcon, Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.exitIcon = exitIcon;
        this.athanNameCode = athanNameCode;
        this.athanType = athanType;
    }

    //method to create athan form
    public void start() throws IOException {
        this.mainFrame = new JFrame();
        this.mainFrame.setAlwaysOnTop(true);
        this.mainFrame.setResizable(false);
        this.mainFrame.setUndecorated(true);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setBounds(screenWidth - 300, screenHeight - 300, 300, 300);
        this.mainFrame.setVisible(true);

        this.font = new Font("TimesRoman", Font.ITALIC, 20);

        this.mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    if (AthanPlayer.STARTED) {
                        AthanPlayer.kill();
                    }
                } catch (Exception e1) {
                }
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        this.mainFrame.addMouseMotionListener(new MouseAdapter() {//moving form with mouse
            @Override
            public void mouseDragged(MouseEvent e) {
                mainFrameMouseDragged(e);
            }
        });
        this.mainFrame.addMouseListener(new MouseAdapter() {//moving form with mouse
            @Override
            public void mousePressed(MouseEvent e) {
                mainFrameMousePressed(e);
            }
        });
        this.mainFrame.addMouseListener(new MouseAdapter() {//moving form with mouse
            @Override
            public void mouseReleased(MouseEvent e) {
                mainFrameMouseReleased(e);
            }
        });

        this.mainPanel = new ImagePanel();
        this.mainPanel.setPreferredSize(new Dimension(300, 300));
        this.mainPanel.setLayout(null);
        this.mainPanel.setImagePanel(backgroundImage);

        this.exitLabel = new JLabel();
        this.exitLabel.setIcon(exitIcon);
        this.exitLabel.setBounds(260, 0, 40, 40);
        this.exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exitLabelMouseClicked(e);//closing athan form and stop athan
            }
        });

        this.mainPanel.add(exitLabel);

        this.athanLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1047) + " " + PropertiesHandler.getSingleton().getValue(this.athanNameCode));//display actual prayer name
        this.athanLabel.setBounds(0, 150, 300, 50);
        this.athanLabel.setFont(font);
        this.athanLabel.setForeground(Color.WHITE);
        this.athanLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.stopAthanButton = new JButton(PropertiesHandler.getSingleton().getValue(1062));//stop athan
        this.stopAthanButton.setBounds(50, 200, 200, 30);
        this.stopAthanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAthan();
            }
        });

        this.mainPanel.add(athanLabel);
        this.mainPanel.add(stopAthanButton);
        this.mainFrame.add(mainPanel);

        if(athanType.equalsIgnoreCase("athan")){
        	this.athanPlayer = new AthanPlayer(UserConfig.getSingleton().getAthan());
        	startAthan();//if athan type is athan , play athan
        }else{
        	stopAthanButton.setVisible(false);
        }

        this.pack();

        this.mainFrame.setVisible(true);

    }

    public void startAthan() {
        this.athanPlayer.play();
    }

    public void stopAthan() {
        this.stopAthanButton.setEnabled(false);
        this.athanPlayer.stop();
    }

    public void exitLabelMouseClicked(MouseEvent e) {
        try {
            if (AthanPlayer.STARTED) {
                AthanPlayer.kill();
            }
            this.mainFrame.dispose();
        } catch (Exception e1) {
        }
    }

    public void mainFrameMousePressed(MouseEvent e) {//moving form with mouse
        this.mouseInitX = e.getX();
        this.mouseInitY = e.getY();
    }

    public void mainFrameMouseReleased(MouseEvent e) {//moving form with mouse
        this.mouseInitX = 0;
        this.mouseInitY = 0;
    }

    public void mainFrameMouseDragged(MouseEvent e) {//moving form with mouse
        int x = this.mainFrame.getLocationOnScreen().x;//main form actual x position
        int y = this.mainFrame.getLocationOnScreen().y;//main form actual y position
        this.mainFrame.setLocation(x + (e.getX()) - mouseInitX, y + (e.getY()) - mouseInitY);//moving form to new location of mouse
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public ImageIcon getExitIcon() {
        return exitIcon;
    }

    public void setExitIcon(ImageIcon exitIcon) {
        this.exitIcon = exitIcon;
    }

    public String getAthanType() {
        return athanType;
    }

    public void setAthanType(String athanType) {
        this.athanType = athanType;
    }

}
