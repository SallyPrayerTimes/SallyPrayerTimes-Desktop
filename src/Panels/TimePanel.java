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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Classes.Iconfig;
import Classes.PropertiesHandler;
import Classes.TransparentPanel;
import Classes.UserConfig;
import Classes.XmlHandler;

public class TimePanel extends TransparentPanel implements Iconfig{

    private static final long serialVersionUID = 1L;
    private Color color;
    private JLabel calculationMethodsLabel;
    private JLabel mazhabLabel;
    private JLabel typeTimeLabel;
    private JLabel hijriAdjustmentLabel;
    private JLabel timeZoneAdjustmentLabel;
    private JLabel _time12or24;
    private JLabel fajrLabel;
    private JLabel shorou9Label;
    private JLabel duhrLabel;
    private JLabel asrLabel;
    private JLabel maghribLabel;
    private JLabel ishaaLabel;

    private JRadioButton standardTypeTimeRadioButton;
    private JRadioButton summeryTypeTimeRadioButton;
    private JRadioButton shafi3iMazhabRadioButton;
    private JRadioButton hanafiMazhabRadioButton;
    private JRadioButton time12or24_12;//12 h
    private JRadioButton time12or24_24;//24 H

    private JComboBox <Integer> hijriAdjustmentComboBox;//hijri time adjustment
    private JComboBox <Double> timeZoneAdjustmentComboBox;//time zone time adjustment
    private JComboBox <String> calculationMethodsComboBox;//calculation method adjustment
    private JComboBox <Integer> fajrAdjustmentComboBox;//fajr time adjustment
    private JComboBox <Integer> shorou9AdjustmentComboBox;//shorou9 time adjustment
    private JComboBox <Integer> duhrAdjustmentComboBox;//duhr time adjustment
    private JComboBox <Integer> asrAdjustmentComboBox;//asr time adjustment
    private JComboBox <Integer> maghribAdjustmentComboBox;//maghrib time adjustment
    private JComboBox <Integer> ishaaAdjustmentComboBox;//ishaa time adjustment

    public TimePanel() throws IOException {//create TimePanel object and set parameters
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(400, 340);
        this.color = Color.WHITE;
        this.setOpaque(false);

        this.calculationMethodsLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1071));
        this.calculationMethodsLabel.setBounds(10, 10, 200, 30);
        this.add(calculationMethodsLabel);

        this.mazhabLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1072));
        this.mazhabLabel.setBounds(10, 90, 100, 30);
        this.add(mazhabLabel);

        this.typeTimeLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1073));
        this.typeTimeLabel.setBounds(190, 90, 100, 30);
        this.add(typeTimeLabel);

        this.hijriAdjustmentLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1074));
        this.hijriAdjustmentLabel.setBounds(10, 180, 180, 30);
        this.add(hijriAdjustmentLabel);

        this.timeZoneAdjustmentLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1075));
        this.timeZoneAdjustmentLabel.setBounds(190, 180, 180, 30);
        this.add(timeZoneAdjustmentLabel);

        this.hijriAdjustmentComboBox = new JComboBox<Integer>();
        this.hijriAdjustmentComboBox.setBackground(color);
        this.hijriAdjustmentComboBox.setBounds(10, 210, 150, 20);
        addNumbersToCombo10(hijriAdjustmentComboBox);
        this.add(hijriAdjustmentComboBox);
        this.hijriAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(hijri, hijriAdjustmentComboBox.getSelectedItem().toString());//save selected hijri time adjustment
                    UserConfig.getSingleton().setHijri(hijriAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.timeZoneAdjustmentComboBox = new JComboBox<Double>();
        this.timeZoneAdjustmentComboBox.setBackground(color);
        this.timeZoneAdjustmentComboBox.setBounds(190, 210, 150, 20);
        addNumbersToCombo20(timeZoneAdjustmentComboBox);
        this.add(timeZoneAdjustmentComboBox);
        this.timeZoneAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(timezone, timeZoneAdjustmentComboBox.getSelectedItem().toString());//save selected time zone adjustment
                    UserConfig.getSingleton().setTimezone(timeZoneAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.fajrLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1020));
        this.fajrLabel.setBounds(10, 250, 50, 30);
        this.add(fajrLabel);

        this.fajrAdjustmentComboBox = new JComboBox<Integer>();
        this.fajrAdjustmentComboBox.setBackground(color);
        this.fajrAdjustmentComboBox.setBounds(10, 280, 50, 20);
        addNumbersToCombo60(fajrAdjustmentComboBox);
        this.add(fajrAdjustmentComboBox);
        this.fajrAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(fajr_time, fajrAdjustmentComboBox.getSelectedItem().toString());//save selected fajr time adjustment
                    UserConfig.getSingleton().setFajr_time(fajrAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.shorou9Label = new JLabel(PropertiesHandler.getSingleton().getValue(1021));
        this.shorou9Label.setBounds(70, 250, 50, 30);
        this.add(shorou9Label);

        this.shorou9AdjustmentComboBox = new JComboBox<Integer>();
        this.shorou9AdjustmentComboBox.setBackground(color);
        this.shorou9AdjustmentComboBox.setBounds(70, 280, 50, 20);
        addNumbersToCombo60(shorou9AdjustmentComboBox);
        this.add(shorou9AdjustmentComboBox);
        this.shorou9AdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(shorouk_time, shorou9AdjustmentComboBox.getSelectedItem().toString());//save selected shorou9 time adjustment
                    UserConfig.getSingleton().setShorouk_time(shorou9AdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.duhrLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1022));
        this.duhrLabel.setBounds(130, 250, 50, 30);
        this.add(duhrLabel);

        this.duhrAdjustmentComboBox = new JComboBox<Integer>();
        this.duhrAdjustmentComboBox.setBackground(color);
        this.duhrAdjustmentComboBox.setBounds(130, 280, 50, 20);
        addNumbersToCombo60(duhrAdjustmentComboBox);
        this.add(duhrAdjustmentComboBox);
        this.duhrAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(duhr_time, duhrAdjustmentComboBox.getSelectedItem().toString());//save selected duhr time adjustment
                    UserConfig.getSingleton().setDuhr_time(duhrAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.asrLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1023));
        this.asrLabel.setBounds(190, 250, 50, 30);
        this.add(asrLabel);

        this.asrAdjustmentComboBox = new JComboBox<Integer>();
        this.asrAdjustmentComboBox.setBackground(color);
        this.asrAdjustmentComboBox.setBounds(190, 280, 50, 20);
        addNumbersToCombo60(asrAdjustmentComboBox);
        this.add(asrAdjustmentComboBox);
        this.asrAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(asr_time, asrAdjustmentComboBox.getSelectedItem().toString());//save selected asr time adjustment
                    UserConfig.getSingleton().setAsr_time(asrAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.maghribLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1024));
        this.maghribLabel.setBounds(250, 250, 50, 30);
        this.add(maghribLabel);

        this.maghribAdjustmentComboBox = new JComboBox<Integer>();
        this.maghribAdjustmentComboBox.setBackground(color);
        this.maghribAdjustmentComboBox.setBounds(250, 280, 50, 20);
        addNumbersToCombo60(maghribAdjustmentComboBox);
        this.add(maghribAdjustmentComboBox);
        this.maghribAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(maghrib_time, maghribAdjustmentComboBox.getSelectedItem().toString());//save selected maghrib time adjustment
                    UserConfig.getSingleton().setMaghrib_time(maghribAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.ishaaLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1025));
        this.ishaaLabel.setBounds(310, 250, 50, 30);
        this.add(ishaaLabel);

        this.ishaaAdjustmentComboBox = new JComboBox<Integer>();
        this.ishaaAdjustmentComboBox.setBackground(color);
        this.ishaaAdjustmentComboBox.setBounds(310, 280, 50, 20);
        addNumbersToCombo60(ishaaAdjustmentComboBox);
        this.add(ishaaAdjustmentComboBox);
        this.ishaaAdjustmentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XmlHandler.getSingleton().setUserConfig(ishaa_time, ishaaAdjustmentComboBox.getSelectedItem().toString());//save selected ishaa time adjustment
                    UserConfig.getSingleton().setIshaa_time(ishaaAdjustmentComboBox.getSelectedItem().toString());
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.shafi3iMazhabRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1076));
        this.shafi3iMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.shafi3iMazhabRadioButton.setForeground(color);
        this.shafi3iMazhabRadioButton.setOpaque(false);
        this.shafi3iMazhabRadioButton.setBounds(10, 120, 120, 20);
        this.add(shafi3iMazhabRadioButton);
        this.shafi3iMazhabRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	shafi3iMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                	hanafiMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
                    XmlHandler.getSingleton().setUserConfig(mazhab, shafi3i);//save selected user mazhab shafi3i
                    UserConfig.getSingleton().setMazhab(shafi3i);
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.hanafiMazhabRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1077));
        this.hanafiMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.hanafiMazhabRadioButton.setForeground(color);
        this.hanafiMazhabRadioButton.setOpaque(false);
        this.hanafiMazhabRadioButton.setBounds(10, 150, 120, 20);
        this.add(hanafiMazhabRadioButton);
        this.hanafiMazhabRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	hanafiMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                	shafi3iMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
                    XmlHandler.getSingleton().setUserConfig(mazhab, hanafi);//save selected user mazhab hanafi
                    UserConfig.getSingleton().setMazhab(hanafi);
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.standardTypeTimeRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1078));
        this.standardTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.standardTypeTimeRadioButton.setForeground(color);
        this.standardTypeTimeRadioButton.setOpaque(false);
        this.standardTypeTimeRadioButton.setBounds(190, 120, 120, 20);
        this.add(standardTypeTimeRadioButton);
        this.standardTypeTimeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    standardTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                    summeryTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
                    XmlHandler.getSingleton().setUserConfig(typetime, standard);//save selected user type time standard
                    UserConfig.getSingleton().setTypetime(standard);
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.summeryTypeTimeRadioButton = new JRadioButton(PropertiesHandler.getSingleton().getValue(1079));
        this.summeryTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.summeryTypeTimeRadioButton.setForeground(color);
        this.summeryTypeTimeRadioButton.setOpaque(false);
        this.summeryTypeTimeRadioButton.setBounds(190, 150, 120, 20);
        this.add(summeryTypeTimeRadioButton);
        this.summeryTypeTimeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	summeryTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                	standardTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
                    XmlHandler.getSingleton().setUserConfig(typetime, sayfi);//save selected user type time summer
                    UserConfig.getSingleton().setTypetime(sayfi);
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(standardTypeTimeRadioButton);
        bg1.add(summeryTypeTimeRadioButton);
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(shafi3iMazhabRadioButton);
        bg2.add(hanafiMazhabRadioButton);

        this.calculationMethodsComboBox = new JComboBox<String>();
        
        if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(ar)) 
        {
        	((JLabel)this.calculationMethodsComboBox.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        }
        else
        {
        	((JLabel)this.calculationMethodsComboBox.getRenderer()).setHorizontalAlignment(JLabel.LEFT);
        }
        
        this.calculationMethodsComboBox.setBackground(color);
        this.calculationMethodsComboBox.setOpaque(false);
        this.calculationMethodsComboBox.setBounds(10, 50, 360, 30);
        this.calculationMethodsComboBox.addItem("1- " + PropertiesHandler.getSingleton().getValue(1082));//MuslimWorldLeague
        this.calculationMethodsComboBox.addItem("2- " + PropertiesHandler.getSingleton().getValue(1080));//EgyptionGeneralAuthorityofSurvey
        this.calculationMethodsComboBox.addItem("3- " + PropertiesHandler.getSingleton().getValue(1081));//IslamicSocietyOfNorthAmerica
        this.calculationMethodsComboBox.addItem("4- " + PropertiesHandler.getSingleton().getValue(1083));//UmmAlQuraUniv
        this.calculationMethodsComboBox.addItem("5- " + PropertiesHandler.getSingleton().getValue(1084));//UnivOfIslamicScincesKarachi     
        this.calculationMethodsComboBox.addItem("6- " + PropertiesHandler.getSingleton().getValue(1107));//FederationofIslamicOrganizationsinFrance
        this.calculationMethodsComboBox.addItem("7- " + PropertiesHandler.getSingleton().getValue(1108));//TheMinistryofAwqafandIslamicAffairsinKuwait
        this.calculationMethodsComboBox.addItem("8- " + PropertiesHandler.getSingleton().getValue(1109));//InstituteOfGeophysicsUniversityOfTehran
        this.calculationMethodsComboBox.addItem("9- " + PropertiesHandler.getSingleton().getValue(1110));//ShiaIthnaAshariLevaInstituteQum
        this.calculationMethodsComboBox.addItem("10- " + PropertiesHandler.getSingleton().getValue(1111));//GulfRegion
        this.calculationMethodsComboBox.addItem("11- " + PropertiesHandler.getSingleton().getValue(1112));//Qatar
        this.calculationMethodsComboBox.addItem("12- " + PropertiesHandler.getSingleton().getValue(1113));//MajlisUgamaIslamSingapuraSingapore
        this.calculationMethodsComboBox.addItem("13- " + PropertiesHandler.getSingleton().getValue(1114));//DirectorateOfReligiousAffairsTurkey
        this.calculationMethodsComboBox.addItem("14- " + PropertiesHandler.getSingleton().getValue(1115));//SpiritualAdministrationOfMuslimsOfRussia
        
        
        
        this.add(calculationMethodsComboBox);
        this.calculationMethodsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//calculation method handler
                try {
                    String calculationMethod = calculationMethodsComboBox.getSelectedItem().toString();
                    String startString = calculationMethod.substring(0,2);
                    switch (startString) {//save selected calculation method
                        case "1-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, MuslimWorldLeague);
                            UserConfig.getSingleton().setCalculationMethod(MuslimWorldLeague);
                            break;
                        case "2-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, EgytionGeneralAuthorityofSurvey);
                            UserConfig.getSingleton().setCalculationMethod(EgytionGeneralAuthorityofSurvey);
                            break;
                        case "3-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, IslamicSocietyOfNorthAmerica);
                            UserConfig.getSingleton().setCalculationMethod(IslamicSocietyOfNorthAmerica);
                            break;
                        case "4-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, UmmAlQuraUniv);
                            UserConfig.getSingleton().setCalculationMethod(UmmAlQuraUniv);
                            break;
                        case "5-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, UnivOfIslamicScincesKarachi);
                            UserConfig.getSingleton().setCalculationMethod(UnivOfIslamicScincesKarachi);
                            break;
                        case "6-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, FederationofIslamicOrganizationsinFrance);
                            UserConfig.getSingleton().setCalculationMethod(FederationofIslamicOrganizationsinFrance);
                            break;
                        case "7-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, TheMinistryofAwqafandIslamicAffairsinKuwait);
                            UserConfig.getSingleton().setCalculationMethod(TheMinistryofAwqafandIslamicAffairsinKuwait);
                            break;
                        case "8-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, InstituteOfGeophysicsUniversityOfTehran);
                            UserConfig.getSingleton().setCalculationMethod(InstituteOfGeophysicsUniversityOfTehran);
                            break;
                        case "9-":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, ShiaIthnaAshariLevaInstituteQum);
                            UserConfig.getSingleton().setCalculationMethod(ShiaIthnaAshariLevaInstituteQum);
                            break;
                        case "10":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, GulfRegion);
                            UserConfig.getSingleton().setCalculationMethod(GulfRegion);
                            break;
                        case "11":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, Qatar);
                            UserConfig.getSingleton().setCalculationMethod(Qatar);
                            break;
                        case "12":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, MajlisUgamaIslamSingapuraSingapore);
                            UserConfig.getSingleton().setCalculationMethod(MajlisUgamaIslamSingapuraSingapore);
                            break;
                        case "13":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, DirectorateOfReligiousAffairsTurkey);
                            UserConfig.getSingleton().setCalculationMethod(DirectorateOfReligiousAffairsTurkey);
                            break;
                        case "14":
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, SpiritualAdministrationOfMuslimsOfRussia);
                            UserConfig.getSingleton().setCalculationMethod(SpiritualAdministrationOfMuslimsOfRussia);
                            break;

                        default:
                            XmlHandler.getSingleton().setUserConfig(calculationMethod, MuslimWorldLeague);
                            UserConfig.getSingleton().setCalculationMethod(MuslimWorldLeague);
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

        this._time12or24 = new JLabel(" 12/24  h/H");
        this._time12or24.setBounds(10, 310, 200, 20);
        this._time12or24.setForeground(color);
        this.add(_time12or24);

        this.time12or24_12 = new JRadioButton("12 h");
        this.time12or24_12.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.time12or24_12.setForeground(color);
        this.time12or24_12.setOpaque(false);
        this.time12or24_12.setBounds(140, 310, 80, 20);
        this.add(time12or24_12);
        this.time12or24_12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	time12or24_12.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                	time12or24_24.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
                    XmlHandler.getSingleton().setUserConfig(time12or24, "12");//save selected user time12or24 12
                    UserConfig.getSingleton().setTime12or24("12");
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        this.time12or24_24 = new JRadioButton("24 H");
        this.time12or24_24.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
        this.time12or24_24.setForeground(color);
        this.time12or24_24.setOpaque(false);
        this.time12or24_24.setBounds(240, 310, 80, 20);
        this.add(time12or24_24);
        this.time12or24_24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	time12or24_24.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                	time12or24_12.setIcon(new ImageIcon(getClass().getResource(radio_button_unselected)));
                    XmlHandler.getSingleton().setUserConfig(time12or24, "24");//save selected user time12or24 24
                    UserConfig.getSingleton().setTime12or24("24");
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });

        ButtonGroup bg3 = new ButtonGroup();
        bg3.add(time12or24_12);
        bg3.add(time12or24_24);

        this.calculationMethodsLabel.setForeground(color);
        this.mazhabLabel.setForeground(color);
        this.typeTimeLabel.setForeground(color);
        this.hijriAdjustmentLabel.setForeground(color);
        this.timeZoneAdjustmentLabel.setForeground(color);
        this.fajrLabel.setForeground(color);
        this.shorou9Label.setForeground(color);
        this.duhrLabel.setForeground(color);
        this.asrLabel.setForeground(color);
        this.maghribLabel.setForeground(color);
        this.ishaaLabel.setForeground(color);

        try {
        	
        	 if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(MuslimWorldLeague)){
             	calculationMethodsComboBox.setSelectedIndex(0);
             }else{
            	 if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(EgytionGeneralAuthorityofSurvey)){
                  	calculationMethodsComboBox.setSelectedIndex(1);
                  }else{
                	  if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(IslamicSocietyOfNorthAmerica)){
                        	calculationMethodsComboBox.setSelectedIndex(2);
                        }else{
                        	if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(UmmAlQuraUniv)){
                            	calculationMethodsComboBox.setSelectedIndex(3);
                            }else{
                            	if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(UnivOfIslamicScincesKarachi)){
                                	calculationMethodsComboBox.setSelectedIndex(4);
                                }
                            	else 
                            	{
                            		if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(FederationofIslamicOrganizationsinFrance)){
                                    	calculationMethodsComboBox.setSelectedIndex(5);
                                    }
                            		else 
                            		{
                            			if(UserConfig.getSingleton().getCalculationMethod().equalsIgnoreCase(TheMinistryofAwqafandIslamicAffairsinKuwait)){
                                        	calculationMethodsComboBox.setSelectedIndex(6);
                                        }
                            		}
                            	}
                            }
                            
                        }  
                  }   
             }
             

            if(UserConfig.getSingleton().getMazhab().equalsIgnoreCase(shafi3i)){
            	shafi3iMazhabRadioButton.setSelected(true);
            	shafi3iMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
            }else{
            	if(UserConfig.getSingleton().getMazhab().equalsIgnoreCase(hanafi)){
                	hanafiMazhabRadioButton.setSelected(true);
                	hanafiMazhabRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                }
            }
            

            if(UserConfig.getSingleton().getTypetime().equalsIgnoreCase(standard)){
            	standardTypeTimeRadioButton.setSelected(true);
            	standardTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
            }else{
            	if(UserConfig.getSingleton().getTypetime().equalsIgnoreCase(sayfi)){
                	summeryTypeTimeRadioButton.setSelected(true);
                	summeryTypeTimeRadioButton.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                }
            }
            
            if(UserConfig.getSingleton().getTime12or24().equalsIgnoreCase("12")){
            	time12or24_12.setSelected(true);
            	time12or24_12.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
            }else{
            	if(UserConfig.getSingleton().getTime12or24().equalsIgnoreCase("24")){
                	time12or24_24.setSelected(true);
                	time12or24_24.setIcon(new ImageIcon(getClass().getResource(radio_button_selected)));
                }
            }

            this.hijriAdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getHijri().toString()));//set saved user hijri adjustment
            this.timeZoneAdjustmentComboBox.setSelectedItem(Double.valueOf(UserConfig.getSingleton().getTimezone().toString()));//set saved user time zone adjustment
            this.fajrAdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getFajr_time().toString()));//set saved user fajr time adjustment
            this.shorou9AdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getShorouk_time().toString()));//set saved user shorou9 time adjustment
            this.duhrAdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getDuhr_time().toString()));//set saved user duhr time adjustment
            this.asrAdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getAsr_time().toString()));//set saved user asr time adjustment
            this.maghribAdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getMaghrib_time().toString()));//set saved user maghrib time adjustment
            this.ishaaAdjustmentComboBox.setSelectedItem(Integer.valueOf(UserConfig.getSingleton().getIshaa_time().toString()));//set saved user ishaa time adjustment
        } catch (NumberFormatException e) {
        }

    }

    private void addNumbersToCombo10(JComboBox<Integer> combo) {//add numbers from -10 to 10
        int i;
        int j;
        for (i = 0; i <= 10; i++) {
            combo.addItem(i);
            if (i == 10) {
                for (j = -10; j <= 0; j++) {
                    combo.addItem(j);
                }
            }
        }
    }

    private void addNumbersToCombo20(JComboBox<Double> combo) {//add numbers from -20 to 20
        double i;
        double j;
        for (i = 0; i <= 20; i += 0.5) {
            combo.addItem(i);
            if (i == 20) {
                for (j = -20; j <= 0; j += 0.5) {
                    combo.addItem(j);
                }
            }
        }
    }

    private void addNumbersToCombo60(JComboBox<Integer> combo) {//add numbers from -60 to 60
        int i;
        int j;
        for (i = 0; i <= 60; i++) {
            combo.addItem(i);
            if (i == 60) {
                for (j = -60; j <= 0; j++) {
                    combo.addItem(j);
                }
            }
        }
    }


}
