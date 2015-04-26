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
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

public class MainForm extends JFrame implements Iconfig{

    private static final long serialVersionUID = 1L;
    private int mouseInitX;//initial horizontal position
    private int mouseInitY;//initial vertical position
    private String missingPropertiesValue;//translated "missing" term
    private String toPropertiesValue;//translated "to" term
    private Font font;//font
    private Font locationFont;//location font
    private Font fontMiladiHijriTimes;//font of miladi hijri times
    private Font digitalClockfont;//font of digital clock
    private Font fontNextPrayer;//font of next prayer label
    private JLabel exitLabel;//exit
    
    private JLabel hijriTime;//hijri date
    private JLabel miladiTime;//miladi date
    private JLabel donateLabel;//donate label
    private JLabel location;//location name
    private JLabel locationFromInternet;//get location from internet
    private JLabel fajrName;//fajr name
    private JLabel fajrTime;//fajr time
    private JLabel shorou9Name;//shorou9 name
    private JLabel shorou9Time;//shorou9 time
    private JLabel duhrName;//duhr name
    private JLabel duhrTime;//duhr time
    private JLabel asrName;//asr name
    private JLabel asrTime;//asr time
    private JLabel maghribName;//maghrib name
    private JLabel maghribTime;//maghrib time
	private JLabel ishaaName;//ishaa name
    private JLabel ishaaTime;//ishaa time
    private JLabel digitalClock;//digital clock label
    private JLabel nextPrayer;//next prayer label
    private JLabel settings;// settings

    private JFrame mainFrame;//main form
    private MainImagePanel mainPanel;//main panel
    private String locationValue;//location value
    private PopupMenu popupMenu;//PopupMenu to crying tray icon
    private MenuItem sallyMenuItem;//sally MenuItem in tray icon
    private MenuItem settingsMenuItem;//setting MenuItem in tray icon
    private MenuItem exitMenuItem;//exit MenuItem in try icon
    public static TrayIcon trayIcon;//TrayIcon object
    private SystemTray systemTray;//SystemTray object
    private Image backgroundImage;//background main form
    private ImageIcon exitIcon;//exit icon
    private ImageIcon locationfromInternetIconMain;
    private ImageIcon settingsIcon;//get location from internet icon
    private PrayersTimes prayersTimes;
    private MiladiTime miladiTimeObj;
    private HijriTime hijriTimeObj;
    private PrayerTimesHandler prayerTimesHandler;//thread to calculate next prayer and showing missing time to next prayer and play athan

    private String country = "";//user country
    private String city = "";//user city
    private String longitude = "";//user city longitude
    private String latitude = "";//user city latitude
    private String timezone = "";//user city time zone
    private ImageIcon loaderMain;
    
    private ImageIcon athanOnIcon;
    private ImageIcon athanOffIcon;
    private ImageIcon athanNotificationIcon;
    
    private JLabel fajrAthan;
    private JLabel shorou9Athan;
    private JLabel duhrAthan;
    private JLabel asrAthan;
    private JLabel maghribAthan;
	private JLabel ishaaAthan;
	private Image trayIconIcon;
	
    private JLabel nextDayPrayerTimes;
	private JLabel previousDayPrayerTimes;
	private JLabel actualDayPrayerTimes;
	private int nextPreviousDay = 0;
	
    public MainForm(PrayersTimes prayersTimes, HijriTime hijriTime, MiladiTime miladiTime) throws IOException {
        //passing all parameters
        this.locationValue = UserConfig.getSingleton().getCountry() + " - " + UserConfig.getSingleton().getCity();//set location value
        this.prayersTimes = prayersTimes;
        this.miladiTimeObj = miladiTime;
        this.hijriTimeObj = hijriTime;
        this.exitIcon = new ImageIcon(getClass().getResource(exiticon));//exit label icon
        this.settingsIcon = new ImageIcon(getClass().getResource(settingsIconPath));//settings label icon
        this.locationfromInternetIconMain = new ImageIcon(getClass().getResource(locationInternetIconMain));
        this.loaderMain = new ImageIcon(getClass().getResource(loaderMainPath));
        this.athanOnIcon = new ImageIcon(getClass().getResource(athanOnIconPath));
        this.athanOffIcon = new ImageIcon(getClass().getResource(athanOffIconPath));
        this.athanNotificationIcon = new ImageIcon(getClass().getResource(athanNotificationIconPath));
        this.trayIconIcon = new ImageIcon(getClass().getResource(sallyIcon)).getImage();//icon of tray icon
        this.prayerTimesHandler = new PrayerTimesHandler(this, prayersTimes);//create prayer times handler thread
        this.initMainForm();
    }

    private void initMainForm() throws IOException {//create sally form
        this.mainFrame = new JFrame();
        switch (prayerTimesHandler.getActualPrayerTime()) {//set background
		case 0:this.backgroundImage = new ImageIcon(getClass().getResource(duhrBackground)).getImage();break;
		case 1:this.backgroundImage = new ImageIcon(getClass().getResource(shorou9Background)).getImage();break;
		case 2:this.backgroundImage = new ImageIcon(getClass().getResource(fajrBackground)).getImage();break;
		case 3:this.backgroundImage = new ImageIcon(getClass().getResource(ishaaBackground)).getImage();break;
		case 4:this.backgroundImage = new ImageIcon(getClass().getResource(maghribBackground)).getImage();break;
		case 5:this.backgroundImage = new ImageIcon(getClass().getResource(asrBackground)).getImage();break;
		default:this.backgroundImage = new ImageIcon(getClass().getResource(shorou9Background)).getImage();break;
        }

        this.mainPanel = new MainImagePanel(getBackgroundImage());
        this.mainPanel.setActualPrayerTime(prayerTimesHandler.getActualPrayerTime());
        this.mainPanel.setLayout(null);
        this.font = new Font("TimesRoman", Font.ITALIC, 15);
        this.locationFont = new Font("TimesRoman", Font.ITALIC, 22);
        this.digitalClockfont = new Font("TimesRoman", Font.ROMAN_BASELINE, 20);
        this.fontMiladiHijriTimes = new Font("TimesRoman", Font.ROMAN_BASELINE, 13);
        this.fontNextPrayer = new Font("TimesRoman", Font.ROMAN_BASELINE, 20);
        this.exitLabel = new JLabel();
        this.hijriTime = new JLabel("", SwingConstants.RIGHT);
        this.miladiTime = new JLabel();
        this.location = new JLabel(locationValue);
        this.location.setHorizontalAlignment(SwingConstants.CENTER);
        this.locationFromInternet = new JLabel();
        
        //get all translated salat names
        this.fajrName = new JLabel(PropertiesHandler.getSingleton().getValue(1020));
        this.fajrName.setHorizontalAlignment(SwingConstants.CENTER);
        this.fajrTime = new JLabel();
        this.fajrTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.shorou9Name = new JLabel(PropertiesHandler.getSingleton().getValue(1021));
        this.shorou9Name.setHorizontalAlignment(SwingConstants.CENTER);
        this.shorou9Time = new JLabel();
        this.shorou9Time.setHorizontalAlignment(SwingConstants.CENTER);
        this.duhrName = new JLabel(PropertiesHandler.getSingleton().getValue(1022));
        this.duhrName.setHorizontalAlignment(SwingConstants.CENTER);
        this.duhrTime = new JLabel();
        this.duhrTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.asrName = new JLabel(PropertiesHandler.getSingleton().getValue(1023));
        this.asrName.setHorizontalAlignment(SwingConstants.CENTER);
        this.asrTime = new JLabel();
        this.asrTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.maghribName = new JLabel(PropertiesHandler.getSingleton().getValue(1024));
        this.maghribName.setHorizontalAlignment(SwingConstants.CENTER);
        this.maghribTime = new JLabel();
        this.maghribTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.ishaaName = new JLabel(PropertiesHandler.getSingleton().getValue(1025));
        this.ishaaName.setHorizontalAlignment(SwingConstants.CENTER);
        this.ishaaTime = new JLabel();
        this.ishaaTime.setHorizontalAlignment(SwingConstants.CENTER);

        this.digitalClock = new JLabel();
        this.nextPrayer = new JLabel();

        this.settings = new JLabel();

        this.exitLabel.setIcon(exitIcon);
        this.settings.setIcon(settingsIcon);
        this.locationFromInternet.setIcon(locationfromInternetIconMain);
        
        this.nextDayPrayerTimes = new JLabel(new ImageIcon(getClass().getResource(nextDayPrayerTimesIcon)));
        this.previousDayPrayerTimes = new JLabel(new ImageIcon(getClass().getResource(previousDayPrayerTimesIcon)));
        this.actualDayPrayerTimes = new JLabel(new ImageIcon(getClass().getResource(actualDayPrayerTimesIcon)));
        
        this.previousDayPrayerTimes.setBounds(480, 275, 15, 15);
        this.actualDayPrayerTimes.setBounds(500, 275, 15, 15);
        this.nextDayPrayerTimes.setBounds(520, 275, 15, 15);
        
        this.nextDayPrayerTimes.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
        		nextPreviousDay++;
        		Calendar cal= Calendar.getInstance();
        		cal.add(Calendar.DATE, nextPreviousDay);
        		try {
            		PrayersTimes prayerTimes = new PrayersTimes(cal);
            		MiladiTime miladiTime = new MiladiTime(cal);
            		HijriTime hijriTime = new HijriTime(cal);
					setAllLabelsTimesHijriMiladiValues2(prayerTimes, hijriTime, miladiTime);
				} catch (IOException e1) {}
        	}
		});
        this.previousDayPrayerTimes.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
        		nextPreviousDay--;
        		Calendar cal= Calendar.getInstance();
        		cal.add(Calendar.DATE, nextPreviousDay);
        		try {
            		PrayersTimes prayerTimes = new PrayersTimes(cal);
            		MiladiTime miladiTime = new MiladiTime(cal);
            		HijriTime hijriTime = new HijriTime(cal);
					setAllLabelsTimesHijriMiladiValues2(prayerTimes, hijriTime, miladiTime);
				} catch (IOException e1) {}
        	}
		});
        this.actualDayPrayerTimes.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e){
        		try {
        			nextPreviousDay = 0;
					setAllLabelsTimesHijriMiladiValues(prayersTimes, hijriTimeObj, miladiTimeObj);
				} catch (IOException e1) {}//set all values to all labels
        	}
		});
        
        //set foreground color to all salat names and times
        this.duhrName.setForeground(Color.WHITE);
        this.shorou9Name.setForeground(Color.WHITE);
        this.fajrName.setForeground(Color.WHITE);
        this.ishaaName.setForeground(Color.WHITE);
        this.maghribName.setForeground(Color.WHITE);
        this.asrName.setForeground(Color.WHITE);

        this.duhrTime.setForeground(Color.WHITE);
        this.shorou9Time.setForeground(Color.WHITE);
        this.fajrTime.setForeground(Color.WHITE);
        this.ishaaTime.setForeground(Color.WHITE);
        this.maghribTime.setForeground(Color.WHITE);
        this.asrTime.setForeground(Color.WHITE);
        this.miladiTime.setForeground(Color.WHITE);
        this.hijriTime.setForeground(Color.WHITE);
        this.location.setForeground(Color.WHITE);
        this.digitalClock.setForeground(Color.WHITE);
        this.nextPrayer.setForeground(Color.WHITE);

        //set font to all salat names and times
        this.duhrName.setFont(font);
        this.shorou9Name.setFont(font);
        this.fajrName.setFont(font);
        this.ishaaName.setFont(font);
        this.maghribName.setFont(font);
        this.asrName.setFont(font);
        this.duhrTime.setFont(font);
        this.shorou9Time.setFont(font);
        this.fajrTime.setFont(font);
        this.ishaaTime.setFont(font);
        this.maghribTime.setFont(font);
        this.asrTime.setFont(font);
        this.digitalClock.setFont(digitalClockfont);
        this.nextPrayer.setFont(fontNextPrayer);
        this.miladiTime.setFont(fontMiladiHijriTimes);
        this.hijriTime.setFont(fontMiladiHijriTimes);
        this.location.setFont(locationFont);

        //set position color to all salat names and times
        this.exitLabel.setBounds(510, 0, 40, 40);
        if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(ar))
        {
        	this.hijriTime.setBounds(100, 3, 250, 20);
            this.miladiTime.setBounds(20, 3, 250, 20);
        }
        else
        {
        	this.hijriTime.setBounds(150, 3, 250, 20);
            this.miladiTime.setBounds(5, 3, 250, 20);
        }
        this.location.setBounds(0, 40, 550, 30);
        
        this.donateLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1106));
        this.donateLabel.setFont(fontMiladiHijriTimes);
        this.donateLabel.setBounds(420, 3, 50, 20);
        this.donateLabel.setForeground(Color.WHITE);
        this.donateLabel.addMouseListener(new MouseAdapter() {
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

        this.locationFromInternet.setBounds(260, 70, 20, 20);
        this.locationFromInternet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
            	new Thread(new Runnable() {
					
					@Override
					public void run() {
						if(locationFromInternet.isEnabled())
						{
							locationFromInternet.setEnabled(false);
							locationFromInternet.setIcon(loaderMain);
			            	locationFromInternetLabelMouseClicked(e);
						}
					}
				}).start();
            }
        });
        
        this.fajrAthan = new JLabel();
        this.fajrAthan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
					allAthanClickHandler(0);
				} catch (Exception e1) {}
            }
        });
        this.shorou9Athan = new JLabel();
        this.shorou9Athan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                allAthanClickHandler(1);
			} catch (Exception e1) {}
            }
        });
        this.duhrAthan = new JLabel();
        this.duhrAthan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                allAthanClickHandler(2);
			} catch (Exception e1) {}
            }
        });
        this.asrAthan = new JLabel();
        this.asrAthan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                allAthanClickHandler(3);
			} catch (Exception e1) {}
            }
        });
        this.maghribAthan = new JLabel();
        this.maghribAthan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                allAthanClickHandler(4);
			} catch (Exception e1) {}
            }
        });
        this.ishaaAthan = new JLabel();
        this.ishaaAthan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                allAthanClickHandler(5);
			} catch (Exception e1) {}
            }
        });
        
        this.duhrAthan.setBounds(152, 132, 28, 28);
        this.shorou9Athan.setBounds(322, 132, 28, 28);
        this.fajrAthan.setBounds(492, 132, 28, 28);
        this.ishaaAthan.setBounds(152, 212, 28, 28);
        this.maghribAthan.setBounds(322, 212, 28, 28);
        this.asrAthan.setBounds(492, 212, 28, 28);

        setAllAthanIcons();
    	
        this.duhrName.setBounds(30, 100, 150, 30);
        this.duhrTime.setBounds(30, 130, 150, 30);
        this.shorou9Name.setBounds(200, 100, 150, 30);
        this.shorou9Time.setBounds(200, 130, 150, 30);
        this.fajrName.setBounds(370, 100, 150, 30);
        this.fajrTime.setBounds(370, 130, 150, 30);
        
        this.ishaaName.setBounds(30, 180, 150, 30);
        this.ishaaTime.setBounds(30, 210, 150, 30);
        this.maghribName.setBounds(200, 180, 150, 30);
        this.maghribTime.setBounds(200, 210, 150, 30);
        this.asrName.setBounds(370, 180, 150, 30);
        this.asrTime.setBounds(370, 210, 150, 30);
        
        this.digitalClock.setBounds(10, 250, 200, 50);
        this.nextPrayer.setBounds(150, 262, 350, 30);

        this.settings.setBounds(470, 0, 40, 40);
        this.settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingsLabelMouseClicked(e);
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
        
        this.mainFrame.setSize(550, 300);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setUndecorated(true);
        this.mainFrame.setResizable(false);
        
        //adding all labels objects to main panel
        this.mainPanel.add(exitLabel);
        this.mainPanel.add(hijriTime);
        this.mainPanel.add(miladiTime);
        this.mainPanel.add(location);
        this.mainPanel.add(locationFromInternet);
        
        this.mainPanel.add(duhrName);
        this.mainPanel.add(duhrTime);
        this.mainPanel.add(shorou9Name);
        this.mainPanel.add(shorou9Time);
        this.mainPanel.add(fajrName);
        this.mainPanel.add(fajrTime);
        this.mainPanel.add(ishaaName);
        this.mainPanel.add(ishaaTime);
        this.mainPanel.add(maghribName);
        this.mainPanel.add(maghribTime);
        this.mainPanel.add(asrName);
        this.mainPanel.add(asrTime);
        this.mainPanel.add(digitalClock);
        this.mainPanel.add(nextPrayer);
        this.mainPanel.add(settings);
        this.mainPanel.add(donateLabel);
        
        this.mainPanel.add(fajrAthan);
        this.mainPanel.add(shorou9Athan);
        this.mainPanel.add(duhrAthan);
        this.mainPanel.add(asrAthan);
        this.mainPanel.add(maghribAthan);
        this.mainPanel.add(ishaaAthan);
        
        this.mainPanel.add(nextDayPrayerTimes);
        this.mainPanel.add(previousDayPrayerTimes);
        this.mainPanel.add(actualDayPrayerTimes);
        
        this.mainFrame.add(mainPanel);
        this.pack();

        trayConfig();//create try icon

        this.setAllLabelsTimesHijriMiladiValues(prayersTimes, hijriTimeObj, miladiTimeObj);//set all values to all labels
        this.prayerTimesHandler.start();//start prayer times handler thread
    }

    public void settingsLabelMouseClicked(MouseEvent e) {//create setting form with location panel selected
        if (this.mainFrame.isVisible()) {
            this.mainFrame.dispose(); 
            java.awt.EventQueue.invokeLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        new SettingsForm(getBackgroundImage() , exitIcon).initMainForm();
                    } catch (IOException e) {
                        try {
                            JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e1) {
                        }
                    }
                }
            });
        } else {
            this.mainFrame.setVisible(true);
        }
    }

	public void exitLabelMouseClicked(MouseEvent e) {//hid main form and display try icon
        
        if (!SystemTray.isSupported()) {
            System.exit(0);
        } else {
            if (this.mainFrame.isVisible()) {
                this.mainFrame.dispose();
            }
            if (MainForm.trayIcon == null) {
                try {
                    try{
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }catch(Exception ex){}
                    initTray();
                } catch (IOException e1) {
                    System.exit(0);
                }
            }
        }
    }
    
    public void locationFromInternetLabelMouseClicked(MouseEvent e)
    {
    	try{
    	if(getLocationFromIP())
    	{     	
    		this.locationFromInternet.setEnabled(true);
    		this.locationFromInternet.setIcon(locationfromInternetIconMain);
        	int result = JOptionPane.showConfirmDialog(null, PropertiesHandler.getSingleton().getValue(1049)+" : "+country+"\n"+PropertiesHandler.getSingleton().getValue(1050)+" : "+city+"\n"+PropertiesHandler.getSingleton().getValue(1051)+" : "+longitude+"\n"+PropertiesHandler.getSingleton().getValue(1052)+" : "+latitude+"\n"+PropertiesHandler.getSingleton().getValue(1053)+" : "+timezone ,PropertiesHandler.getSingleton().getValue(1049)+"-"+PropertiesHandler.getSingleton().getValue(1050)+"-"+PropertiesHandler.getSingleton().getValue(1051)+"-"+PropertiesHandler.getSingleton().getValue(1052), JOptionPane.OK_CANCEL_OPTION);
        	
        	if(result == JOptionPane.OK_OPTION)
        	{
        		UserConfig.getSingleton().setCountry(country);
            	UserConfig.getSingleton().setCity(city);
            	UserConfig.getSingleton().setLongitude(longitude);
            	UserConfig.getSingleton().setLatitude(latitude);
            	UserConfig.getSingleton().setTimezone(timezone);
            	
            	XmlHandler.getSingleton().addUserConfig(UserConfig.getSingleton());
            	
        		mainFrame.dispose();
                if (AthanPlayer.STARTED) {//kill AthanPlayer if started
                    AthanPlayer.kill();
                }
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                     	StartProgram.isSettingsClosed = true;
                        StartProgram.startProgrameMethode();
                    } catch (Exception e) {
                        try {
                            JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e1) {
                        }
                    }
                }
            });
        	}else{
        		this.locationFromInternet.setEnabled(true);
        		this.locationFromInternet.setIcon(locationfromInternetIconMain);
        		}
    	}
    	else
    	{
    		this.locationFromInternet.setIcon(locationfromInternetIconMain);
    		this.locationFromInternet.setEnabled(true);
    		JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1104), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
    	}
    	}catch(Exception ex){
    		this.locationFromInternet.setEnabled(true);
    		this.locationFromInternet.setIcon(locationfromInternetIconMain);
    	}
    }
    
    public boolean getLocationFromIP() {
        boolean b = false;
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();

			URL url = new URL("http://freegeoip.net/json/"+ip);
	        BufferedReader inn = new BufferedReader(new InputStreamReader(url.openStream()));
	        String s = inn.readLine();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			
			country = jsonObject.get("country_name").toString();
			city = jsonObject.get("city").toString();
			longitude = jsonObject.get("longitude").toString();
			latitude = jsonObject.get("latitude").toString();
			
			TimeZone tz = TimeZone.getTimeZone((String)jsonObject.get("time_zone"));
			
			timezone = String.valueOf(((tz.getRawOffset()) / (60 * 60 * 1000D)));

			if(!country.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !longitude.equalsIgnoreCase("") && !latitude.equalsIgnoreCase("") && !timezone.equalsIgnoreCase(""))
			{
				b = true;
			}
		} catch (Exception e) {
			b = false;
			this.locationFromInternet.setEnabled(true);
			this.locationFromInternet.setIcon(locationfromInternetIconMain);
		}
		return b;
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

    public void setAllLabelsTimesHijriMiladiValues(PrayersTimes prayersTimes, HijriTime hijriTime, MiladiTime miladiTime) throws IOException {//set all prayers times and hijri and miladi times 
        this.shorou9Time.setText(prayersTimes.getShorou9FinalTime());
        this.fajrTime.setText(prayersTimes.getFajrFinalTime());
        this.duhrTime.setText(prayersTimes.getDuhrFinalTime());
        this.asrTime.setText(prayersTimes.getAsrFinalTime());
        this.maghribTime.setText(prayersTimes.getMaghribFinalTime());
        this.ishaaTime.setText(prayersTimes.getIshaaFinalTime());
        //set hijri and miladi times
        this.miladiTime.setText(miladiTime.getMiladiTime());
        this.hijriTime.setText(hijriTime.getHijriTime());
    }
    
    public void setAllLabelsTimesHijriMiladiValues2(PrayersTimes prayersTimes, HijriTime hijriTime, MiladiTime miladiTime) throws IOException {//set all prayers times and hijri and miladi times 
        this.shorou9Time.setText(prayersTimes.getShorou9FinalTime());
        this.fajrTime.setText(prayersTimes.getFajrFinalTime());
        this.duhrTime.setText(prayersTimes.getDuhrFinalTime());
        this.asrTime.setText(prayersTimes.getAsrFinalTime());
        this.maghribTime.setText(prayersTimes.getMaghribFinalTime());
        this.ishaaTime.setText(prayersTimes.getIshaaFinalTime());
        //set hijri and miladi times
        this.miladiTime.setText(miladiTime.getMiladiTime());
        this.hijriTime.setText(hijriTime.getHijriTime());
    }

    public void trayConfig() throws IOException {//create trayconfig

        this.popupMenu = new PopupMenu();

        this.sallyMenuItem = new MenuItem();//create sally MenuItem
        this.settingsMenuItem = new MenuItem();//create setting MenuItem
        this.exitMenuItem = new MenuItem();//create exit MenuItem

        //adding all MenuItem in PopupMenu with separator
        this.popupMenu.add(sallyMenuItem);
        this.popupMenu.addSeparator();
        this.popupMenu.add(settingsMenuItem);
        this.popupMenu.addSeparator();
        this.popupMenu.add(exitMenuItem);

        this.sallyMenuItem.setLabel(PropertiesHandler.getSingleton().getValue(1061));
        this.sallyMenuItem.addActionListener(new ActionListener() {//display main form on sally MenuItem click
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!mainFrame.isVisible()) {
                    mainFrame.setVisible(true);
                    pack();
                }
            }
        });

        this.settingsMenuItem.setLabel(PropertiesHandler.getSingleton().getValue(1048));
        this.settingsMenuItem.addActionListener(new ActionListener() {//display setting form on setting MenuItem click
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	if (mainFrame.isVisible())
            	  {
                    mainFrame.dispose();
                    }
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new SettingsForm(getBackgroundImage(), exitIcon).initMainForm();
                        } catch (IOException e) {
                            try {
                                JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                            } catch (Exception e1) {
                            }
                        }
                    }
                });
            }
        });
        this.exitMenuItem.setLabel(PropertiesHandler.getSingleton().getValue(1063));
        this.exitMenuItem.addActionListener(new ActionListener() {//closing sally program on exit MenuItem click
            @Override
            public void actionPerformed(ActionEvent arg0) {
                systemTray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

    public void initTray() throws IOException {
        MainForm.trayIcon = null;
        this.systemTray = SystemTray.getSystemTray();
        
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					MainForm.trayIcon.displayMessage(PropertiesHandler.getSingleton().getValue(1061), PropertiesHandler.getSingleton().getValue(1098)+" "+PropertiesHandler.getSingleton().getValue(1099),TrayIcon.MessageType.INFO);
				} catch (Exception e1) {}
            }
        };
        
        MouseListener mouseListener = new MouseListener() {
            
            public void mouseClicked(MouseEvent e) { 
                if (!mainFrame.isVisible()){
                    mainFrame.setVisible(true);
                }
            }

            public void mouseEntered(MouseEvent e) { 
            	//mouseEntered not supported
            	/*
            	try {
					MainForm.trayIcon.displayMessage(PropertiesHandler.getSingleton().getValue(1061), PropertiesHandler.getSingleton().getValue(1098)+" "+PropertiesHandler.getSingleton().getValue(1099),TrayIcon.MessageType.INFO);
				} catch (Exception e1) {}
				*/
            }
            public void mouseExited(MouseEvent e) {}//mouseExited not supported
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        };

        MainForm.trayIcon = new TrayIcon(trayIconIcon, PropertiesHandler.getSingleton().getValue(1061), popupMenu);//create tray icon
        MainForm.trayIcon.setImageAutoSize(true);
        MainForm.trayIcon.addActionListener(actionListener);
        MainForm.trayIcon.addMouseListener(mouseListener);

        try {
            systemTray.add(MainForm.trayIcon);
            MainForm.trayIcon.displayMessage(PropertiesHandler.getSingleton().getValue(1061), PropertiesHandler.getSingleton().getValue(1098)+" "+PropertiesHandler.getSingleton().getValue(1099),TrayIcon.MessageType.INFO);
        } catch (Exception e1){}
    }

    public void startAthan(int actualPrayerNameCode) throws IOException {//start athan with actual salat code
        String athanType;
        switch (actualPrayerNameCode) {
            case 1020:
                athanType = UserConfig.getSingleton().getFajr_athan();
                break;
            case 1021:
                athanType = UserConfig.getSingleton().getShorouk_athan();
                break;
            case 1022:
                athanType = UserConfig.getSingleton().getDuhr_athan();
                break;
            case 1023:
                athanType = UserConfig.getSingleton().getAsr_athan();
                break;
            case 1024:
                athanType = UserConfig.getSingleton().getMaghrib_athan();
                break;
            case 1025:
                athanType = UserConfig.getSingleton().getIshaa_athan();
                break;
            default:
                athanType = athan;
                break;
        }
        if (!athanType.equalsIgnoreCase(none)) {
        	new Athan(athanType, actualPrayerNameCode, exitIcon, getBackgroundImage()).start();
        }
    }
    
    public void setAllAthanIcons()
    {
    	if(UserConfig.getSingleton().getFajr_athan().equalsIgnoreCase(athan))
    	{
            this.fajrAthan.setIcon(athanOnIcon);
    	}else if(UserConfig.getSingleton().getFajr_athan().equalsIgnoreCase(notification))
    	{
    		this.fajrAthan.setIcon(athanNotificationIcon);
    	}else
    	{
    		this.fajrAthan.setIcon(athanOffIcon);
    	}
    	
    	if(UserConfig.getSingleton().getShorouk_athan().equalsIgnoreCase(athan))
    	{
            this.shorou9Athan.setIcon(athanOnIcon);
    	}else if(UserConfig.getSingleton().getShorouk_athan().equalsIgnoreCase(notification))
    	{
    		this.shorou9Athan.setIcon(athanNotificationIcon);
    	}else
    	{
    		this.shorou9Athan.setIcon(athanOffIcon);
    	}
    	
    	if(UserConfig.getSingleton().getDuhr_athan().equalsIgnoreCase(athan))
    	{
            this.duhrAthan.setIcon(athanOnIcon);
    	}else if(UserConfig.getSingleton().getDuhr_athan().equalsIgnoreCase(notification))
    	{
    		this.duhrAthan.setIcon(athanNotificationIcon);
    	}else
    	{
    		this.duhrAthan.setIcon(athanOffIcon);
    	}
    	
    	if(UserConfig.getSingleton().getAsr_athan().equalsIgnoreCase(athan))
    	{
            this.asrAthan.setIcon(athanOnIcon);
    	}else if(UserConfig.getSingleton().getAsr_athan().equalsIgnoreCase(notification))
    	{
    		this.asrAthan.setIcon(athanNotificationIcon);
    	}else
    	{
    		this.asrAthan.setIcon(athanOffIcon);
    	}
    	
    	if(UserConfig.getSingleton().getMaghrib_athan().equalsIgnoreCase(athan))
    	{
            this.maghribAthan.setIcon(athanOnIcon);
    	}else if(UserConfig.getSingleton().getMaghrib_athan().equalsIgnoreCase(notification))
    	{
    		this.maghribAthan.setIcon(athanNotificationIcon);
    	}else
    	{
    		this.maghribAthan.setIcon(athanOffIcon);
    	}
    	
    	if(UserConfig.getSingleton().getIshaa_athan().equalsIgnoreCase(athan))
    	{
            this.ishaaAthan.setIcon(athanOnIcon);
    	}else if(UserConfig.getSingleton().getIshaa_athan().equalsIgnoreCase(notification))
    	{
    		this.ishaaAthan.setIcon(athanNotificationIcon);
    	}else
    	{
    		this.ishaaAthan.setIcon(athanOffIcon);
    	}
    }
    
    public void allAthanClickHandler(int athanName) throws ParserConfigurationException, SAXException, IOException, TransformerException
    {
    	switch (athanName) {
		case 0:
			if(UserConfig.getSingleton().getFajr_athan().equalsIgnoreCase(athan))
	    	{
				UserConfig.getSingleton().setFajr_athan(none);
				XmlHandler.getSingleton().setUserConfig(fajr_athan , none);
	            this.fajrAthan.setIcon(athanOffIcon);
	    	}else if(UserConfig.getSingleton().getFajr_athan().equalsIgnoreCase(none))
	    	{
				UserConfig.getSingleton().setFajr_athan(notification);
				XmlHandler.getSingleton().setUserConfig(fajr_athan , notification);
	            this.fajrAthan.setIcon(athanNotificationIcon);
	    	}else
	    	{
	    		UserConfig.getSingleton().setFajr_athan(athan);
	    		XmlHandler.getSingleton().setUserConfig(fajr_athan , athan);
	    		this.fajrAthan.setIcon(athanOnIcon);
	    	}
			break;
			
		case 1:
			if(UserConfig.getSingleton().getShorouk_athan().equalsIgnoreCase(athan))
	    	{
				UserConfig.getSingleton().setShorouk_athan(none);
				XmlHandler.getSingleton().setUserConfig(shorouk_athan , none);
	            this.shorou9Athan.setIcon(athanOffIcon);
	    	}else if(UserConfig.getSingleton().getShorouk_athan().equalsIgnoreCase(none))
	    	{
				UserConfig.getSingleton().setShorouk_athan(notification);
				XmlHandler.getSingleton().setUserConfig(shorouk_athan , notification);
	            this.shorou9Athan.setIcon(athanNotificationIcon);
	    	}else
	    	{
	    		UserConfig.getSingleton().setShorouk_athan(athan);
	    		XmlHandler.getSingleton().setUserConfig(shorouk_athan , athan);
	    		this.shorou9Athan.setIcon(athanOnIcon);
	    	}
			break;
			
		case 2:
			if(UserConfig.getSingleton().getDuhr_athan().equalsIgnoreCase(athan))
	    	{
				UserConfig.getSingleton().setDuhr_athan(none);
				XmlHandler.getSingleton().setUserConfig(duhr_athan , none);
	            this.duhrAthan.setIcon(athanOffIcon);
	    	}else if(UserConfig.getSingleton().getDuhr_athan().equalsIgnoreCase(none))
	    	{
				UserConfig.getSingleton().setDuhr_athan(notification);
				XmlHandler.getSingleton().setUserConfig(duhr_athan , notification);
	            this.duhrAthan.setIcon(athanNotificationIcon);
	    	}else
	    	{
	    		UserConfig.getSingleton().setDuhr_athan(athan);
	    		XmlHandler.getSingleton().setUserConfig(duhr_athan , athan);
	    		this.duhrAthan.setIcon(athanOnIcon);
	    	}
			break;
			
		case 3:
			if(UserConfig.getSingleton().getAsr_athan().equalsIgnoreCase(athan))
	    	{
				UserConfig.getSingleton().setAsr_athan(none);
				XmlHandler.getSingleton().setUserConfig(asr_athan , none);
	            this.asrAthan.setIcon(athanOffIcon);
	    	}else if(UserConfig.getSingleton().getAsr_athan().equalsIgnoreCase(none))
	    	{
				UserConfig.getSingleton().setAsr_athan(notification);
				XmlHandler.getSingleton().setUserConfig(asr_athan , notification);
	            this.asrAthan.setIcon(athanNotificationIcon);
	    	}else
	    	{
	    		UserConfig.getSingleton().setAsr_athan(athan);
	    		XmlHandler.getSingleton().setUserConfig(asr_athan , athan);
	    		this.asrAthan.setIcon(athanOnIcon);
	    	}
			break;
			
		case 4:
			if(UserConfig.getSingleton().getMaghrib_athan().equalsIgnoreCase(athan))
	    	{
				UserConfig.getSingleton().setMaghrib_athan(none);
				XmlHandler.getSingleton().setUserConfig(maghrib_athan , none);
	            this.maghribAthan.setIcon(athanOffIcon);
	    	}else if(UserConfig.getSingleton().getMaghrib_athan().equalsIgnoreCase(none))
	    	{
				UserConfig.getSingleton().setMaghrib_athan(notification);
				XmlHandler.getSingleton().setUserConfig(maghrib_athan , notification);
	            this.maghribAthan.setIcon(athanNotificationIcon);
	    	}else
	    	{
	    		UserConfig.getSingleton().setMaghrib_athan(athan);
	    		XmlHandler.getSingleton().setUserConfig(maghrib_athan , athan);
	    		this.maghribAthan.setIcon(athanOnIcon);
	    	}
			break;
			
		case 5:
			if(UserConfig.getSingleton().getIshaa_athan().equalsIgnoreCase(athan))
	    	{
				UserConfig.getSingleton().setIshaa_athan(none);
				XmlHandler.getSingleton().setUserConfig(ishaa_athan , none);
	            this.ishaaAthan.setIcon(athanOffIcon);
	    	}else if(UserConfig.getSingleton().getIshaa_athan().equalsIgnoreCase(none))
	    	{
				UserConfig.getSingleton().setIshaa_athan(notification);
				XmlHandler.getSingleton().setUserConfig(ishaa_athan , notification);
	            this.ishaaAthan.setIcon(athanNotificationIcon);
	    	}else
	    	{
	    		UserConfig.getSingleton().setIshaa_athan(athan);
	    		XmlHandler.getSingleton().setUserConfig(ishaa_athan , athan);
	    		this.ishaaAthan.setIcon(athanOnIcon);
	    	}
			break;

		default:
			break;
		}
    }

    public String getMissingPropertiesValue() {
        return missingPropertiesValue;
    }

    public void setMissingPropertiesValue(String missingPropertiesValue) {
        this.missingPropertiesValue = missingPropertiesValue;
    }

    public String getToPropertiesValue() {
        return toPropertiesValue;
    }

    public void setToPropertiesValue(String toPropertiesValue) {
        this.toPropertiesValue = toPropertiesValue;
    }

    public JLabel getDigitalClockLabel() {
        return digitalClock;
    }

    public JLabel getNextPrayerLabel() {
        return nextPrayer;
    }

    public JFrame getMainFram() {
        return mainFrame;
    }

    public SystemTray getSystemTry() {
        return systemTray;
    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

    public PrayersTimes getPrayersTimes() {
        return prayersTimes;
    }

    public void setPrayersTimes(PrayersTimes prayersTimes) {
        this.prayersTimes = prayersTimes;
    }

    public MiladiTime getMiladiTimeObj() {
        return miladiTimeObj;
    }

    public void setMiladiTimeObj(MiladiTime miladiTimeObj) {
        this.miladiTimeObj = miladiTimeObj;
    }

    public HijriTime getHijriTimeObj() {
        return hijriTimeObj;
    }

    public void setHijriTimeObj(HijriTime hijriTimeObj) {
        this.hijriTimeObj = hijriTimeObj;
    }

    public MainImagePanel getMainPanel() {
		return mainPanel;
	}

	public JLabel getDigitalClock() {
        return digitalClock;
    }

    public void setDigitalClock(JLabel digitalClock) {
        this.digitalClock = digitalClock;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
    

    public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
}
