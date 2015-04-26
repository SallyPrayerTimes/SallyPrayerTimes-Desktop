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
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Panels.AthanPanel;
import Panels.InfoPanel;
import Panels.LanguagesPanel;
import Panels.LocationPanel;
import Panels.TimePanel;

public class SettingsForm extends JFrame implements Iconfig{

    private static final long serialVersionUID = 1L;

    private int numberPanel;//number of selected panel
    private int mouseInitX;//initial horizontal position
    private int mouseInitY;//initial vertical position

    private JLabel exitLabel;//exit 
    private JLabel titleForm;//title main form
    private JLabel locationPanelLabel;//location label
    private JLabel languagePanelLabel;//language label
    private JLabel timePanelLabel;//time label
    private JLabel athanPanelLabel;//athan label
    private JLabel infoPanelLabel;//information label
    private JLabel donationLabel;//donation label

    private Color fontColor;//font color
    private Color backColor;//back color
    private Color backColorHover;//back color hover 
    private Font font;//font

    private ImagePanel mainPanel;//main panel
    private TransparentPanel titlePanel;//top transparent panel
    private TransparentPanel menuPanel;//menu transparent panel
    private TransparentPanel contentPanel;//main transparent panel
    private JFrame mainFrame;//main form
    private final Image backgroundImage;//main form background image
    private final ImageIcon exitIcon;//exit label icon
    private ImageIcon donateLabelIcon;

    public SettingsForm(Image backgroundImage, ImageIcon exitIcon) {
        this.backgroundImage = backgroundImage;
        this.exitIcon = exitIcon;
        this.donateLabelIcon = new ImageIcon(getClass().getResource(donateButtonPath));
    }

    public void initMainForm() throws IOException {//create settings form
        this.mainFrame = new JFrame();
        this.mainFrame.setTitle(PropertiesHandler.getSingleton().getValue(1048));
        this.mainFrame.setSize(550, 380);
        this.mainFrame.setUndecorated(true);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setResizable(false);
        this.mainPanel = new ImagePanel();
        this.mainPanel.setPreferredSize(new Dimension(550, 380));
        this.mainPanel.setLayout(null);
        this.mainPanel.setImagePanel(backgroundImage);

        this.fontColor = Color.WHITE;
        this.backColor = new Color(59, 185, 255);
        this.backColorHover = new Color(142, 235, 236);
        this.font = new Font("TimesRoman", Font.ITALIC, 13);

        this.titlePanel = new TransparentPanel();//create top title panel
        this.titlePanel.setLayout(null);
        this.titlePanel.setBounds(0, 0, 550, 40);

        this.menuPanel = new TransparentPanel();//create menu panel
        this.menuPanel.setLayout(null);
        this.menuPanel.setBounds(0, 40, 150, 340);
        this.menuPanel.setBorder(new LineBorder(Color.WHITE, 1));

        this.contentPanel = new TransparentPanel();//create main panel
        this.contentPanel.setLayout(null);
        this.contentPanel.setBounds(150, 40, 400, 340);

        this.titleForm = new JLabel(PropertiesHandler.getSingleton().getValue(1048));//title form
        this.titleForm.setFont(font);
        this.titleForm.setForeground(fontColor);
        this.titleForm.setHorizontalAlignment(SwingConstants.CENTER);
        this.titleForm.setBounds(0, 0, 550, 40);

        this.exitLabel = new JLabel();//exit
        this.exitLabel.setIcon(exitIcon);
        this.exitLabel.setBounds(510, 0, 40, 40);

        this.locationPanelLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1064), SwingConstants.CENTER);//location menu label
        this.locationPanelLabel.setFont(font);
        this.locationPanelLabel.setForeground(fontColor);
        this.locationPanelLabel.setBackground(backColor);
        this.locationPanelLabel.setOpaque(true);
        this.locationPanelLabel.setBounds(10, 10, 130, 40);

        this.timePanelLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1065), SwingConstants.CENTER);//time menu label
        this.timePanelLabel.setFont(font);
        this.timePanelLabel.setForeground(fontColor);
        this.timePanelLabel.setBackground(backColor);
        this.timePanelLabel.setOpaque(true);
        this.timePanelLabel.setBounds(10, 60, 130, 40);

        this.athanPanelLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1066), SwingConstants.CENTER);//athan menu label
        this.athanPanelLabel.setFont(font);
        this.athanPanelLabel.setForeground(fontColor);
        this.athanPanelLabel.setBackground(backColor);
        this.athanPanelLabel.setOpaque(true);
        this.athanPanelLabel.setBounds(10, 110, 130, 40);

        this.languagePanelLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1067), SwingConstants.CENTER);//language menu label
        this.languagePanelLabel.setFont(font);
        this.languagePanelLabel.setForeground(fontColor);
        this.languagePanelLabel.setBackground(backColor);
        this.languagePanelLabel.setOpaque(true);
        this.languagePanelLabel.setBounds(10, 160, 130, 40);

        this.infoPanelLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1068), SwingConstants.CENTER);//information menu label
        this.infoPanelLabel.setFont(font);
        this.infoPanelLabel.setForeground(fontColor);
        this.infoPanelLabel.setBackground(backColor);
        this.infoPanelLabel.setOpaque(true);
        this.infoPanelLabel.setBounds(10, 210, 130, 40);
        
        this.donationLabel = new JLabel();//donation menu label
        this.donationLabel.setIcon(donateLabelIcon);
        this.donationLabel.setBounds(10, 270, 130, 60);
        
        this.donationLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=FJDAFTPKN2S2W";
				String os = System.getProperty("os.name").toLowerCase();
			    Runtime rt = Runtime.getRuntime();
			 
				try{
			 
				    if (os.indexOf( "win" ) >= 0) {
			 
				        // this doesn't support showing urls in the form of "page.html#nameLink" 
				        rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
			 
				    } else if (os.indexOf( "mac" ) >= 0) {
			 
				        rt.exec( "open " + url);
			 
			            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {
			 
				        // Do a best guess on unix until we get a platform independent way
				        // Build a list of browsers to try, in this order.
				        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
				       			             "netscape","opera","links","lynx"};
			 
				        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
				        StringBuffer cmd = new StringBuffer();
				        for (int i=0; i<browsers.length; i++)
				            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
			 
				        rt.exec(new String[] { "sh", "-c", cmd.toString() });
			 
			           } else {
			           }
			       }catch (Exception ex){}	
			}
		});

        this.locationPanelLabel.addMouseListener(new MouseAdapter() {//location menu click handle
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    numberPanel = 1;
                    languagePanelLabel.setBackground(backColor);
                    timePanelLabel.setBackground(backColor);
                    athanPanelLabel.setBackground(backColor);
                    infoPanelLabel.setBackground(backColor);
                    initLocationPanel();
                } catch (Exception e) {
                    try {
						JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {}
                }
            }
        });
        this.locationPanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                locationPanelLabel.setBackground(backColorHover);
            }
        });
        this.locationPanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                if (!(numberPanel == 1)) {
                    locationPanelLabel.setBackground(backColor);
                }
            }
        });

        this.languagePanelLabel.addMouseListener(new MouseAdapter() {//language menu click handle
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    numberPanel = 4;
                    locationPanelLabel.setBackground(backColor);
                    timePanelLabel.setBackground(backColor);
                    athanPanelLabel.setBackground(backColor);
                    infoPanelLabel.setBackground(backColor);
                    initLanguagePanel();
                } catch (IOException e) {
                    try {
                        try {
							JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
						} catch (Exception e1) {}
                    } catch (HeadlessException e1) {
                    }
                }
            }
        });
        this.languagePanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                languagePanelLabel.setBackground(backColorHover);
            }
        });
        this.languagePanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                if (!(numberPanel == 4)) {
                    languagePanelLabel.setBackground(backColor);
                }
            }
        });

        this.timePanelLabel.addMouseListener(new MouseAdapter() {//time menu click handle
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    numberPanel = 2;
                    locationPanelLabel.setBackground(backColor);
                    languagePanelLabel.setBackground(backColor);
                    athanPanelLabel.setBackground(backColor);
                    infoPanelLabel.setBackground(backColor);
                    initTimePanel();
                } catch (IOException e) {
                    try {
						JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {}
                }
            }
        });
        this.timePanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                timePanelLabel.setBackground(backColorHover);
            }
        });
        this.timePanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                if (!(numberPanel == 2)) {
                    timePanelLabel.setBackground(backColor);
                }
            }
        });

        this.athanPanelLabel.addMouseListener(new MouseAdapter() {//athan menu click handle
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    numberPanel = 3;
                    locationPanelLabel.setBackground(backColor);
                    languagePanelLabel.setBackground(backColor);
                    timePanelLabel.setBackground(backColor);
                    infoPanelLabel.setBackground(backColor);
                    initAthanPanel();
                } catch (IOException e) {
                    try {
						JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
					} catch (Exception e1) {}
                }
            }
        });
        this.athanPanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                athanPanelLabel.setBackground(backColorHover);
            }
        });
        this.athanPanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                if (!(numberPanel == 3)) {
                    athanPanelLabel.setBackground(backColor);
                }
            }
        });

        this.infoPanelLabel.addMouseListener(new MouseAdapter() {//information menu click handle
            @Override
            public void mouseClicked(MouseEvent arg0) {
                numberPanel = 5;
                locationPanelLabel.setBackground(backColor);
                languagePanelLabel.setBackground(backColor);
                timePanelLabel.setBackground(backColor);
                athanPanelLabel.setBackground(backColor);
                initInfoPanel();
            }
        });
        this.infoPanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                infoPanelLabel.setBackground(backColorHover);
            }
        });
        this.infoPanelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent arg0) {
                if (!(numberPanel == 5)) {
                    infoPanelLabel.setBackground(backColor);
                }
            }
        });

        this.exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exitLabelMouseClicked(e);
            }
        });

        this.mainFrame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mainFrameMouseDragged(e);
            }
        });
        this.mainFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainFrameMousePressed(e);
            }
        });
        this.mainFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mainFrameMouseReleased(e);
            }
        });

        //adding all labels to panels
        this.menuPanel.add(locationPanelLabel);
        this.menuPanel.add(languagePanelLabel);
        this.menuPanel.add(timePanelLabel);
        this.menuPanel.add(athanPanelLabel);
        this.menuPanel.add(infoPanelLabel);
        this.menuPanel.add(donationLabel);

        this.titlePanel.add(exitLabel);
        this.titlePanel.add(titleForm);

        this.mainPanel.add(menuPanel);
        this.mainPanel.add(contentPanel);
        this.mainFrame.add(titlePanel);
        this.mainFrame.add(mainPanel);
        this.pack();
        this.mainFrame.setVisible(true);
        
        initLocationPanel();

    }

    public void exitLabelMouseClicked(MouseEvent e) {//exit setting form handle
    	
    	if (this.mainFrame.isVisible()) {
            this.mainFrame.dispose();
            if (AthanPlayer.STARTED) {//kill AthanPlayer if started
                AthanPlayer.kill();
            }
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                	StartProgram.isSettingsClosed = true;
                    StartProgram.startProgrameMethode();
                }
            });
        } else {
            this.mainFrame.setVisible(true);
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

    public void initLocationPanel() {//initial location panel
        locationPanelLabel.setBackground(backColorHover);
        contentPanel.removeAll();//empty content panel
        try {
            contentPanel.add(new LocationPanel(backgroundImage));//adding location panel to content panel
        } catch (Exception e) {
            try {
				JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
			} catch (Exception e1) {}
        }
        mainFrame.repaint();
        contentPanel.revalidate();
        mainFrame.pack();
    }

    public void initLanguagePanel() throws IOException {//initial language panel
        languagePanelLabel.setBackground(backColorHover);
        contentPanel.removeAll();//empty language panel
        contentPanel.add(new LanguagesPanel(mainFrame, backgroundImage, exitIcon));//adding language panel to content panel
        mainFrame.repaint();
        contentPanel.revalidate();
        mainFrame.pack();
    }

    public void initTimePanel() throws IOException {//initial time panel
        timePanelLabel.setBackground(backColorHover);
        contentPanel.removeAll();//empty time panel
        TimePanel timePanel = new TimePanel(backgroundImage);//adding time panel to content panel
        contentPanel.add(timePanel);
        mainFrame.repaint();
        contentPanel.revalidate();
        mainFrame.pack();
    }

    public void initAthanPanel() throws IOException {//initial athan panel
        athanPanelLabel.setBackground(backColorHover);
        contentPanel.removeAll();//empty athan panel
        AthanPanel athanPanel = new AthanPanel(backgroundImage);//adding athan panel to content panel
        contentPanel.add(athanPanel);
        mainFrame.repaint();
        contentPanel.revalidate();
        mainFrame.pack();
    }

    public void initInfoPanel() {//initial information panel
        contentPanel.removeAll();//empty information panel
        contentPanel.add(new InfoPanel(backgroundImage));//adding information panel to content panel
        mainFrame.repaint();
        contentPanel.revalidate();
        mainFrame.pack();
    }

    public int getNumberPanel() {
        return numberPanel;
    }

    public void setNumberPanel(int numberPanel) {
        this.numberPanel = numberPanel;
    }

}
