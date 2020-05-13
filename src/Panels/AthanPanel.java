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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Classes.AthanPlayer;
import Classes.Iconfig;
import Classes.PropertiesHandler;
import Classes.TransparentPanel;
import Classes.UserConfig;
import Classes.XmlHandler;

public class AthanPanel extends TransparentPanel implements Iconfig{

    private static final long serialVersionUID = 1L;

    //athan play , stop , pause
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    //salat names
    private JLabel athanLabel;
    private JLabel fajrLabel;
    private JLabel shorou9Label;
    private JLabel duhrLabel;
    private JLabel asrLabel;
    private JLabel maghribLabel;
    private JLabel ishaaLabel;

    private JComboBox <String> athanComboBox;//athan list
    private JComboBox <String> fajrAthanTypeComboBox;//fajr athan type
    private JComboBox <String> shorou9AthanTypeComboBox;//shorou9 athan type
    private JComboBox <String> duhrAthanTypeComboBox;//duhr athan type
    private JComboBox <String> asrAthanTypeComboBox;//asr athan type
    private JComboBox <String> maghribAthanTypeComboBox;//maghrib athan type
    private JComboBox <String> ishaaAthanTypeComboBox;//ishaa athan type

    private Color color;

    private AthanPlayer athanPlayer;//player object to play athan

    public AthanPanel() throws IOException {//create AthanPanel object and set parameters

        this.setLayout(null);
        this.setVisible(true);
        this.setSize(400, 340);
        this.color = Color.WHITE;
        this.setOpaque(false);

        this.athanLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1066));
        this.athanLabel.setBounds(10, 10, 200, 30);
        this.athanLabel.setForeground(color);
        this.add(athanLabel);

        this.athanComboBox = new JComboBox<String>();
        this.athanComboBox.setBackground(color);
        this.athanComboBox.setOpaque(false);
        this.athanComboBox.setBounds(10, 50, 360, 30);
        this.athanComboBox.addItem("1- " + PropertiesHandler.getSingleton().getValue(1090));//ali_ben_ahmed_mala
        this.athanComboBox.addItem("2- " + PropertiesHandler.getSingleton().getValue(1091));//abd_el_basset_abd_essamad
        this.athanComboBox.addItem("3- " + PropertiesHandler.getSingleton().getValue(1092));//farou9_abd_errehmane_hadraoui
        this.athanComboBox.addItem("4- " + PropertiesHandler.getSingleton().getValue(1093));//mohammad_ali_el_banna
        this.athanComboBox.addItem("5- " + PropertiesHandler.getSingleton().getValue(1094));//mohammad_khalil_raml
        this.add(athanComboBox);
        this.athanComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//save selected user athan
                try {
                    String athanCombo = athanComboBox.getSelectedItem().toString();
                    char char1 = athanCombo.charAt(0);
                    switch (char1) {
                        case '1':
                            XmlHandler.getSingleton().setUserConfig(athan, ali_ben_ahmed_mala);
                            UserConfig.getSingleton().setAthan(ali_ben_ahmed_mala);
                            break;
                        case '2':
                            XmlHandler.getSingleton().setUserConfig(athan, abd_el_basset_abd_essamad);
                            UserConfig.getSingleton().setAthan(abd_el_basset_abd_essamad);
                            break;
                        case '3':
                            XmlHandler.getSingleton().setUserConfig(athan, farou9_abd_errehmane_hadraoui);
                            UserConfig.getSingleton().setAthan(farou9_abd_errehmane_hadraoui);
                            break;
                        case '4':
                            XmlHandler.getSingleton().setUserConfig(athan, mohammad_ali_el_banna);
                            UserConfig.getSingleton().setAthan(mohammad_ali_el_banna);
                            break;
                        case '5':
                            XmlHandler.getSingleton().setUserConfig(athan, mohammad_khalil_raml);
                            UserConfig.getSingleton().setAthan(mohammad_khalil_raml);
                            break;

                        default:
                            XmlHandler.getSingleton().setUserConfig(athan, ali_ben_ahmed_mala);
                            UserConfig.getSingleton().setAthan(ali_ben_ahmed_mala);
                            break;
                    }
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        //play button
        this.playButton = new JButton(new ImageIcon(getClass().getResource(playIcon)));
        this.playButton.setOpaque(false);
        this.playButton.setBounds(20, 120, 50, 30);
        this.playButton.setBackground(new Color(0, 0, 0, 0));
        this.playButton.setOpaque(false);
        this.playButton.setBorder(BorderFactory.createEmptyBorder());
        //pause button
        this.pauseButton = new JButton(new ImageIcon(getClass().getResource(pauseicon)));
        this.pauseButton.setOpaque(false);
        this.pauseButton.setBounds(140, 120, 50, 30);
        this.pauseButton.setBackground(new Color(0, 0, 0, 0));
        this.pauseButton.setOpaque(false);
        this.pauseButton.setBorder(BorderFactory.createEmptyBorder());
        //stop button
        this.stopButton = new JButton(new ImageIcon(getClass().getResource(stopIcon)));
        this.stopButton.setOpaque(false);
        this.stopButton.setBounds(260, 120, 50, 30);
        this.stopButton.setBackground(new Color(0, 0, 0, 0));
        this.stopButton.setOpaque(false);
        this.stopButton.setBorder(BorderFactory.createEmptyBorder());

        this.pauseButton.setEnabled(false);
        this.stopButton.setEnabled(false);
        //play button click handler
        this.playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
                stopButton.setEnabled(true);
                playButton_actionPerformed(e);
            }
        });

        //pause button click handler
        this.pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
                pauseButton_actionPerformed(e);
            }
        });

        //stop button click handler
        this.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(false);
                stopButton_actionPerformed(e);
            }
        });

        this.add(playButton);
        this.add(pauseButton);
        this.add(stopButton);
        //create slate names labels
        this.fajrLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1020));
        this.fajrLabel.setBounds(10, 200, 100, 30);
        this.fajrLabel.setForeground(color);
        this.add(fajrLabel);
        this.shorou9Label = new JLabel(PropertiesHandler.getSingleton().getValue(1021));
        this.shorou9Label.setBounds(120, 200, 100, 30);
        this.shorou9Label.setForeground(color);
        this.add(shorou9Label);
        this.duhrLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1022));
        this.duhrLabel.setBounds(230, 200, 100, 30);
        this.duhrLabel.setForeground(color);
        this.add(duhrLabel);
        this.asrLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1023));
        this.asrLabel.setBounds(10, 260, 100, 30);
        this.asrLabel.setForeground(color);
        this.add(asrLabel);
        this.maghribLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1024));
        this.maghribLabel.setBounds(120, 260, 100, 30);
        this.maghribLabel.setForeground(color);
        this.add(maghribLabel);
        this.ishaaLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1025));
        this.ishaaLabel.setBounds(230, 260, 100, 30);
        this.ishaaLabel.setForeground(color);
        this.add(ishaaLabel);

        this.fajrAthanTypeComboBox = new JComboBox<String>();
        this.fajrAthanTypeComboBox.setBackground(color);
        this.fajrAthanTypeComboBox.setBounds(10, 230, 100, 20);
        addAthanTypeToCombo(fajrAthanTypeComboBox);
        this.add(fajrAthanTypeComboBox);
        this.fajrAthanTypeComboBox.addActionListener(new ActionListener() {//save selected user fajr athan type
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(fajr_athan, getSelectedAthan(fajrAthanTypeComboBox.getSelectedItem().toString()));
                    UserConfig.getSingleton().setFajr_athan(getSelectedAthan(fajrAthanTypeComboBox.getSelectedItem().toString()));
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.shorou9AthanTypeComboBox = new JComboBox<String>();
        this.shorou9AthanTypeComboBox.setBackground(color);
        this.shorou9AthanTypeComboBox.setBounds(120, 230, 100, 20);
        addAthanTypeToCombo(shorou9AthanTypeComboBox);
        this.add(shorou9AthanTypeComboBox);
        this.shorou9AthanTypeComboBox.addActionListener(new ActionListener() {//save selected user shorou9 athan type
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(shorouk_athan, getSelectedAthan(shorou9AthanTypeComboBox.getSelectedItem().toString()));
                    UserConfig.getSingleton().setShorouk_athan(getSelectedAthan(shorou9AthanTypeComboBox.getSelectedItem().toString()));
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.duhrAthanTypeComboBox = new JComboBox<String>();
        this.duhrAthanTypeComboBox.setBackground(color);
        this.duhrAthanTypeComboBox.setBounds(230, 230, 100, 20);
        addAthanTypeToCombo(duhrAthanTypeComboBox);
        this.add(duhrAthanTypeComboBox);
        this.duhrAthanTypeComboBox.addActionListener(new ActionListener() {//save selected user duhr athan type
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(duhr_athan, getSelectedAthan(duhrAthanTypeComboBox.getSelectedItem().toString()));
                    UserConfig.getSingleton().setDuhr_athan(getSelectedAthan(duhrAthanTypeComboBox.getSelectedItem().toString()));
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.asrAthanTypeComboBox = new JComboBox<String>();
        this.asrAthanTypeComboBox.setBackground(color);
        this.asrAthanTypeComboBox.setBounds(10, 290, 100, 20);
        addAthanTypeToCombo(asrAthanTypeComboBox);
        this.add(asrAthanTypeComboBox);
        this.asrAthanTypeComboBox.addActionListener(new ActionListener() {//save selected user asr athan type
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(asr_athan, getSelectedAthan(asrAthanTypeComboBox.getSelectedItem().toString()));
                    UserConfig.getSingleton().setAsr_athan(getSelectedAthan(asrAthanTypeComboBox.getSelectedItem().toString()));
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.maghribAthanTypeComboBox = new JComboBox<String>();
        this.maghribAthanTypeComboBox.setBackground(color);
        this.maghribAthanTypeComboBox.setBounds(120, 290, 100, 20);
        addAthanTypeToCombo(maghribAthanTypeComboBox);
        this.add(maghribAthanTypeComboBox);
        this.maghribAthanTypeComboBox.addActionListener(new ActionListener() {//save selected user maghrib athan type
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(maghrib_athan, getSelectedAthan(maghribAthanTypeComboBox.getSelectedItem().toString()));
                    UserConfig.getSingleton().setMaghrib_athan(getSelectedAthan(maghribAthanTypeComboBox.getSelectedItem().toString()));
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.ishaaAthanTypeComboBox = new JComboBox<String>();
        this.ishaaAthanTypeComboBox.setBackground(color);
        this.ishaaAthanTypeComboBox.setBounds(230, 290, 100, 20);
        addAthanTypeToCombo(ishaaAthanTypeComboBox);
        this.add(ishaaAthanTypeComboBox);
        this.ishaaAthanTypeComboBox.addActionListener(new ActionListener() {//save selected user ishaa athan type
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(ishaa_athan, getSelectedAthan(ishaaAthanTypeComboBox.getSelectedItem().toString()));
                    UserConfig.getSingleton().setIshaa_athan(getSelectedAthan(ishaaAthanTypeComboBox.getSelectedItem().toString()));
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });
        
        try {
        	 if(UserConfig.getSingleton().getAthan().equalsIgnoreCase(ali_ben_ahmed_mala)){
             	athanComboBox.setSelectedIndex(0);
             }else{
             	if(UserConfig.getSingleton().getAthan().equalsIgnoreCase(abd_el_basset_abd_essamad)){
                 	athanComboBox.setSelectedIndex(1);
                 }else{
                 	if(UserConfig.getSingleton().getAthan().equalsIgnoreCase(farou9_abd_errehmane_hadraoui)){
                     	athanComboBox.setSelectedIndex(2);
                     }else{
                     	if(UserConfig.getSingleton().getAthan().equalsIgnoreCase(mohammad_ali_el_banna)){
                         	athanComboBox.setSelectedIndex(3);
                         }else{
                         	if(UserConfig.getSingleton().getAthan().equalsIgnoreCase(mohammad_khalil_raml)){
                             	athanComboBox.setSelectedIndex(4);
                             }
                         }    
                     }
                 }
             }
            this.fajrAthanTypeComboBox.setSelectedIndex(getSelectedAthanType(UserConfig.getSingleton().getFajr_athan()));//set saved user fajr athan type
            this.shorou9AthanTypeComboBox.setSelectedIndex(getSelectedAthanType(UserConfig.getSingleton().getShorouk_athan()));//set saved user shorou9 athan type
            this.duhrAthanTypeComboBox.setSelectedIndex(getSelectedAthanType(UserConfig.getSingleton().getDuhr_athan()));//set saved user duhr athan type
            this.asrAthanTypeComboBox.setSelectedIndex(getSelectedAthanType(UserConfig.getSingleton().getAsr_athan()));//set saved user asr athan type
            this.maghribAthanTypeComboBox.setSelectedIndex(getSelectedAthanType(UserConfig.getSingleton().getMaghrib_athan()));//set saved user maghrib athan type
            this.ishaaAthanTypeComboBox.setSelectedIndex(getSelectedAthanType(UserConfig.getSingleton().getIshaa_athan()));//set saved user ishaa athan type
        } catch (Exception e) {
        }

    }

    private void addAthanTypeToCombo(JComboBox <String> combo) throws IOException {//adding translated athan types
        combo.addItem("1- " + PropertiesHandler.getSingleton().getValue(1095));
        combo.addItem("2- " + PropertiesHandler.getSingleton().getValue(1096));
        combo.addItem("3- " + PropertiesHandler.getSingleton().getValue(1097));
    }

    private int getSelectedAthanType(String athanType) {//get selected athan position
        int athanTypePosition = 0;
        if(athanType.equalsIgnoreCase(athan)){
        	athanTypePosition = 0;
        }else{
        	 if(athanType.equalsIgnoreCase(notification)){
             	athanTypePosition = 1;
             }else{
            	 if(athanType.equalsIgnoreCase(none)){
                  	athanTypePosition = 2;
                  }
             }
        }
       
        return athanTypePosition;
    }

    private String getSelectedAthan(String _athan) {//get selected athan type
        String athanType = "";
        try {
            char char1 = _athan.charAt(0);
            switch (char1) {
                case '1':
                    athanType = athan;
                    break;
                case '2':
                    athanType = notification;
                    break;
                case '3':
                    athanType = none;
                    break;
                default:
                    athanType = athan;
                    break;
            }
        } catch (Exception e1) {
            try {
                JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
            } catch (Exception e2) {
            }
        }
        return athanType;
    }

    private void playButton_actionPerformed(ActionEvent e)//play athan
    {
        String athan;
        char char1 = this.athanComboBox.getSelectedItem().toString().charAt(0);
        try {
            switch (char1) {
                case '1':
                    athan = ali_ben_ahmed_mala;
                    break;
                case '2':
                    athan = abd_el_basset_abd_essamad;
                    break;
                case '3':
                    athan = farou9_abd_errehmane_hadraoui;
                    break;
                case '4':
                    athan = mohammad_ali_el_banna;
                    break;
                case '5':
                    athan = mohammad_khalil_raml;
                    break;
                default:
                    athan = ali_ben_ahmed_mala;
                    break;
            }
        } catch (Exception ex) {
            athan = ali_ben_ahmed_mala;
        }
        this.athanPlayer = new AthanPlayer(athan);
        this.athanPlayer.play();
    }

    private void pauseButton_actionPerformed(ActionEvent e)//pause athan
    {
        this.athanPlayer.pause();
    }

    private void stopButton_actionPerformed(ActionEvent e)//stop athan
    {
        this.athanPlayer.stop();
    }

    public AthanPlayer getAthanPlayer() {
        return athanPlayer;
    }

    public void setAthanPlayer(AthanPlayer athanPlayer) {
        this.athanPlayer = athanPlayer;
    }

}
