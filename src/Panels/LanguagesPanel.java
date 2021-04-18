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
package Panels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Classes.Iconfig;
import Classes.PropertiesHandler;
import Classes.SettingsForm;
import Classes.TransparentPanel;
import Classes.UserConfig;
import Classes.XmlHandler;

public class LanguagesPanel extends TransparentPanel implements Iconfig{

    private static final long serialVersionUID = 1L;
    private Color color;

    private JLabel moreLanguageLabel;
    private JLabel languageTitleLabel;
    private JRadioButton arRadioButton;//Arabic language
    private JRadioButton engRadioButton;//English language
    private JRadioButton frRadioButton;//French language
    private JRadioButton itRadioButton;//Italian language
    private JRadioButton trRadioButton;//Turkish language

    private SettingsForm settingsForm;//settings form
    private Image backgroundImage;
    private ImageIcon exitIcon;

    //create LanguagePanel and set parameters
    public LanguagesPanel(final SettingsForm settingsForm, final Image backgroundImage , final ImageIcon exitIcon) throws IOException {

        this.settingsForm = settingsForm;
        this.backgroundImage = backgroundImage;
        this.exitIcon = exitIcon;
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(400, 340);
        this.color = Color.WHITE;
        this.setOpaque(false);

        this.languageTitleLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1067));
        this.languageTitleLabel.setBounds(200, 20, 100, 20);
        this.languageTitleLabel.setForeground(color);
        this.add(languageTitleLabel);

        this.arRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1085)); 
        this.arRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.arRadioButton.setForeground(color);
        this.arRadioButton.setOpaque(false);
        this.arRadioButton.setBounds(30, 50, 100, 20);
        this.add(arRadioButton);
        this.arRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(language, ar);//save selected Arabic language
                    UserConfig.getSingleton().setLanguage(ar);
                    refresh();
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.engRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1086));
        this.engRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.engRadioButton.setForeground(color);
        this.engRadioButton.setOpaque(false);
        this.engRadioButton.setBounds(30, 100, 100, 20);
        this.add(engRadioButton);
        this.engRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(language, eng);//save selected English language
                    UserConfig.getSingleton().setLanguage(eng);
                    refresh();
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.frRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1087));
        this.frRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.frRadioButton.setForeground(color);
        this.frRadioButton.setOpaque(false);
        this.frRadioButton.setBounds(30, 150, 100, 20);
        this.add(frRadioButton);
        this.frRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(language, fr);//save selected French language
                    UserConfig.getSingleton().setLanguage(fr);
                    refresh();
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.itRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1088));
        this.itRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.itRadioButton.setForeground(color);
        this.itRadioButton.setOpaque(false);
        this.itRadioButton.setBounds(30, 200, 100, 20);
        this.add(itRadioButton);
        this.itRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(language, it);//save selected Italien language
                    UserConfig.getSingleton().setLanguage(it);
                    refresh();
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });
        
        this.trRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1116));
        this.trRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.trRadioButton.setForeground(color);
        this.trRadioButton.setOpaque(false);
        this.trRadioButton.setBounds(30, 250, 100, 20);
        this.add(trRadioButton);
        this.trRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(language, tr);//save selected Italien language
                    UserConfig.getSingleton().setLanguage(tr);
                    refresh();
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        ButtonGroup bg = new ButtonGroup();
        bg.add(arRadioButton);
        bg.add(engRadioButton);
        bg.add(frRadioButton);
        bg.add(itRadioButton);
        bg.add(trRadioButton);

        this.moreLanguageLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1089));
        this.moreLanguageLabel.setForeground(color);
        this.moreLanguageLabel.setBounds(30, 300, 300, 20);
        this.add(moreLanguageLabel);

        try {
        	if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(ar)){
        		arRadioButton.setSelected(true);
        		arRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
            }else{
            	if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(eng)){
            		engRadioButton.setSelected(true);
            		engRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                }else{
                	if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(fr)){
                		frRadioButton.setSelected(true);
                		frRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                    }else{
                    	if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(it)){
                    		itRadioButton.setSelected(true);
                    		itRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                        }
                    	else{
                        	if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(tr)){
                        		trRadioButton.setSelected(true);
                        		trRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                            }
                        }
                    }
                }
            }
        	
        } catch (Exception e) {
        }

    }

    //regenerate program with new selected language
    public void refresh() {

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        PropertiesHandler.getSingleton().setParameters(UserConfig.getSingleton());

                        settingsForm.initLanguagePanel();
                        settingsForm.changeMenuTitleLanguage();

                    } catch (Exception e) {
                        try {
                            JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e1) {
                        }
                    }
                }
            });

    }

    public JLabel getMoreLanguageLabel() {
        return moreLanguageLabel;
    }

    public void setMoreLanguageLabel(JLabel moreLanguageLabel) {
        this.moreLanguageLabel = moreLanguageLabel;
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
}
