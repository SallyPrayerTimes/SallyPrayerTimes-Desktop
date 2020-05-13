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
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

import Classes.City;
import Classes.Country;
import Classes.Iconfig;
import Classes.PropertiesHandler;
import Classes.TransparentPanel;
import Classes.UserConfig;
import Classes.XmlHandler;

public class LocationPanel extends TransparentPanel implements Iconfig{

    private static final long serialVersionUID = 1L;
    private final Color color;
    private JList <String> countriesList;//countries names list
    private JList <String> citiesList;//cities names list
    private final DefaultListModel <String> countriesModel;
    private DefaultListModel <String> citiesModel;
    private final JScrollPane countriesListScrollPane;
    private final JScrollPane citiesListScrollPane;
    private ArrayList<Country> arrayCountries;//all countries objects
    private ArrayList<City> arrayCities;//all cities objects
    private final JLabel countriesLabel;
    private final JLabel citiesLabel;
    private final JLabel longitudeLabel;
    private final JLabel latitudeLabel;
    private JTextField longitudeValue;
    private JTextField latitudeValue;
    private final JLabel timezoneLabel;
    private JTextField timezoneValue;
    private final JButton applyButton;//set country , city , longitude , latitude and time zone from selected city

    private ArrayList<City> arrayCities2;
    private ImageIcon locationfromInternetIcon;//get location from internet icon
    private ImageIcon loaderIcon;
    private JLabel locationFromInternet;
    private JLabel getLocationFromInternet;
    
    private String country = "";//user country
    private String city = "";//user city
    private String longitude = "";//user city longitude
    private String latitude = "";//user city latitude
    private String timezone = "";//user city time zone

    public LocationPanel() throws IOException, ParserConfigurationException, SAXException {//create LocationPanel object and set parameters

    	this.locationfromInternetIcon = new ImageIcon(getClass().getResource(locationInternetIconSetting));//location from internet label icon
        this.loaderIcon = new ImageIcon(getClass().getResource(loaderSettingsPath));
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(400, 340);
        this.color = Color.WHITE;
        this.setOpaque(false);

        this.locationFromInternet = new JLabel();
        this.countriesLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1049));
        this.citiesLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1050));
        this.longitudeLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1051));
        this.latitudeLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1052));
        this.timezoneLabel = new JLabel(PropertiesHandler.getSingleton().getValue(1053));
        this.longitudeValue = new JTextField();
        this.latitudeValue = new JTextField();
        this.timezoneValue = new JTextField();
        this.getLocationFromInternet = new JLabel(PropertiesHandler.getSingleton().getValue(1105));
        this.getLocationFromInternet.setFont(new Font("TimesRoman", Font.ROMAN_BASELINE, 13));
        this.applyButton = new JButton(PropertiesHandler.getSingleton().getValue(1054));
        this.applyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {//Applay button click handler
                try {
                    applyButtonListener(e);
                } catch (Exception e1) {
                    try {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e2) {
                    }
                }
            }
        });
        
        if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(ar))
        {
            this.getLocationFromInternet.setBounds(50, 20, 280, 20);
            this.locationFromInternet.setBounds(10, 10, 30, 30);
        }
        else if(UserConfig.getSingleton().getLanguage().equalsIgnoreCase(eng))
        {
            this.getLocationFromInternet.setBounds(10, 20, 280, 20);
            this.locationFromInternet.setBounds(280, 10, 30, 30);
        }
        else
        {
        	this.getLocationFromInternet.setBounds(10, 20, 280, 20);
            this.locationFromInternet.setBounds(250, 10, 30, 30);
        }
        
        this.locationFromInternet.setIcon(locationfromInternetIcon);
        this.locationFromInternet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                 new Thread(new Runnable() {
					@Override
					public void run() {
						if(locationFromInternet.isEnabled())
						{
							locationFromInternet.setEnabled(false);
							locationFromInternet.setIcon(loaderIcon);
			            	locationFromInternetLabelMouseClicked(e);
						}
					}
				}).start();
            }
        });
        
        this.countriesLabel.setForeground(color);
        this.citiesLabel.setForeground(color);
        this.longitudeLabel.setForeground(color);
        this.latitudeLabel.setForeground(color);
        this.timezoneLabel.setForeground(color);
        this.longitudeValue.setForeground(Color.black);
        this.latitudeValue.setForeground(Color.black);
        this.timezoneValue.setForeground(Color.black);
        this.getLocationFromInternet.setForeground(color);

        this.countriesLabel.setBounds(5, 50, 100, 25);
        this.citiesLabel.setBounds(195, 50, 100, 25);
        
        this.longitudeLabel.setBounds(10, 240, 120, 30);
        this.latitudeLabel.setBounds(150, 240, 120, 30);
        this.timezoneLabel.setBounds(290, 240, 120, 30);
        
        this.longitudeValue.setBounds(10, 265, 120, 30);
        this.latitudeValue.setBounds(150, 265, 120, 30);
        this.timezoneValue.setBounds(290, 265, 60, 30);
        
        this.applyButton.setBounds(125, 300, 150, 30);

        this.countriesModel = new DefaultListModel<String>();
        this.citiesModel = new DefaultListModel<String>();

        this.countriesList = new JList<String>();
        this.countriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.countriesList.setSelectedIndex(0);

        this.countriesList.setSelectionBackground(Color.GRAY);
        this.countriesList.setSelectionForeground(Color.WHITE);

        this.citiesList = new JList<String>();
        this.citiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.citiesList.setSelectedIndex(0);

        this.citiesList.setSelectionBackground(Color.GRAY);
        this.citiesList.setSelectionForeground(Color.WHITE);

        this.countriesList.setModel(countriesModel);
        this.citiesList.setModel(citiesModel);

        this.arrayCountries = new ArrayList<Country>();
        this.arrayCountries = XmlHandler.getSingleton().getAllCountries();//get all countries names from country xml file 
        this.arrayCities = new ArrayList<City>();
        this.arrayCities = XmlHandler.getSingleton().getAllCities(arrayCountries.get(0).getName());//get all cities for first country from xml file

        for (Country country : arrayCountries) {
            countriesModel.addElement(country.getName());//set all countries names to countries model
        }

        this.countriesList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
            	if(!country.equalsIgnoreCase("") && countriesList.getSelectedIndex() == 0){

                }
            	else
            	{
                    citiesModel.clear();
                    try {
                        arrayCities2 = XmlHandler.getSingleton().getAllCities((String) countriesList.getSelectedValue());
                    } catch (Exception  e1) {
                        try {
                            JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e2) {
                        }
                    }
                    for (City city : arrayCities2) {
                        citiesModel.addElement(city.getName());//set all cities names to cities model for selected country
                    }
            	}
            }
        });

        for (City city : arrayCities) {
            citiesModel.addElement(city.getName());//set all cities names to cities model
        }

        this.citiesList.addListSelectionListener(new ListSelectionListener() {//set longitude , latitude and time zone from selected city

            @Override
            public void valueChanged(ListSelectionEvent e) {
            	if(!city.equalsIgnoreCase("") && citiesList.getSelectedIndex() == 0){
                }
            	else
            	{
            		applyButton.setEnabled(true);
                    if (arrayCities2 != null && arrayCities2.size() >= 1) {
                        if (citiesList != null && citiesList.getSelectedIndex() >= 0) {
                            City city = arrayCities2.get(citiesList.getSelectedIndex());
                            longitudeValue.setText(String.valueOf(Double.valueOf(city.getLongitude()) / 10000));
                            latitudeValue.setText(String.valueOf(Double.valueOf(city.getLatitude()) / 10000));
                            timezoneValue.setText(String.valueOf(Double.valueOf(city.getTimezone()) / 100));
                        }
                    }
            	}
            }
        });

        //set longitude , latitude and time zone values to labels from selected city
        this.longitudeValue.setText(String.valueOf(Double.valueOf(arrayCities.get(0).getLongitude()) / 10000));
        this.latitudeValue.setText(String.valueOf(Double.valueOf(arrayCities.get(0).getLatitude()) / 10000));
        this.timezoneValue.setText(String.valueOf(Double.valueOf(arrayCities.get(0).getTimezone()) / 100));

        this.countriesListScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.countriesListScrollPane.setViewportView(countriesList);
        this.countriesListScrollPane.setBounds(5, 80, 180, 150);

        this.citiesListScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.citiesListScrollPane.setViewportView(citiesList);
        this.citiesListScrollPane.setBounds(195, 80, 180, 150);

        this.add(countriesLabel);
        this.add(citiesLabel);
        this.add(countriesListScrollPane);
        this.add(citiesListScrollPane);
        this.add(longitudeLabel);
        this.add(longitudeValue);
        this.add(latitudeLabel);
        this.add(latitudeValue);
        this.add(timezoneLabel);
        this.add(timezoneValue);
        this.add(applyButton);
        this.add(locationFromInternet);
        this.add(getLocationFromInternet);

        this.setVisible(true);
    }

    public void applyButtonListener(ActionEvent e) throws HeadlessException, IOException, ParserConfigurationException, SAXException, TransformerException {

        if (countriesList.getSelectedValue() == null && citiesList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1056), PropertiesHandler.getSingleton().getValue(1055), JOptionPane.WARNING_MESSAGE);
        } else {
            if (countriesList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1057), PropertiesHandler.getSingleton().getValue(1055), JOptionPane.WARNING_MESSAGE);
            } else {
                if (citiesList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1058), PropertiesHandler.getSingleton().getValue(1055), JOptionPane.WARNING_MESSAGE);
                } else {
                    try {//set country , city , longitude , latitude and time zone from selected city
     
                    	UserConfig.getSingleton().setCountry((String) countriesList.getSelectedValue());
                        UserConfig.getSingleton().setCity((String) citiesList.getSelectedValue());
                        UserConfig.getSingleton().setLongitude(longitudeValue.getText());
                        UserConfig.getSingleton().setLatitude(latitudeValue.getText());
                        UserConfig.getSingleton().setTimezone(timezoneValue.getText());
                        
                        XmlHandler.getSingleton().addUserConfig(UserConfig.getSingleton());
                        
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1060), PropertiesHandler.getSingleton().getValue(1059), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Images/Settings/successDialog.png")));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1070), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
                    }
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
    		this.locationFromInternet.setIcon(locationfromInternetIcon);
    		int result = JOptionPane.showConfirmDialog(null, PropertiesHandler.getSingleton().getValue(1049)+" : "+country+"\n"+PropertiesHandler.getSingleton().getValue(1050)+" : "+city+"\n"+PropertiesHandler.getSingleton().getValue(1051)+" : "+longitude+"\n"+PropertiesHandler.getSingleton().getValue(1052)+" : "+latitude+"\n"+PropertiesHandler.getSingleton().getValue(1053)+" : "+timezone ,PropertiesHandler.getSingleton().getValue(1049)+"-"+PropertiesHandler.getSingleton().getValue(1050)+"-"+PropertiesHandler.getSingleton().getValue(1051)+"-"+PropertiesHandler.getSingleton().getValue(1052), JOptionPane.OK_CANCEL_OPTION);
        	
        	if(result == JOptionPane.OK_OPTION)
        	{
        		UserConfig.getSingleton().setCountry(country);
            	UserConfig.getSingleton().setCity(city);
            	UserConfig.getSingleton().setLongitude(longitude);
            	UserConfig.getSingleton().setLatitude(latitude);
            	UserConfig.getSingleton().setTimezone(timezone);
            	
            	
            	countriesModel.add(0,country);
            	citiesModel.add(0,city);
            	
            	citiesList.setSelectedIndex(0);
            	countriesList.setSelectedIndex(0);
            	
            	XmlHandler.getSingleton().addUserConfig(UserConfig.getSingleton());
            	
            	longitudeValue.setText(longitude);
                latitudeValue.setText(latitude);
                timezoneValue.setText(timezone);
                
                JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1060), PropertiesHandler.getSingleton().getValue(1059), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Images/Settings/successDialog.png")));
        	}else{
        		this.locationFromInternet.setEnabled(true);
        		this.locationFromInternet.setIcon(locationfromInternetIcon);
        		}
    	}
    	else
    	{
    		this.locationFromInternet.setEnabled(true);
    		this.locationFromInternet.setIcon(locationfromInternetIcon);
    		JOptionPane.showMessageDialog(null, PropertiesHandler.getSingleton().getValue(1104), PropertiesHandler.getSingleton().getValue(1069), JOptionPane.ERROR_MESSAGE);
    	}
    	}catch(Exception ex){
    		this.locationFromInternet.setEnabled(true);
    		this.locationFromInternet.setIcon(locationfromInternetIcon);
    	}
    }
    
    public boolean getLocationFromIP() {
        boolean b = false;
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();

			URL url = new URL("http://ip-api.com/json/"+ip);
	        BufferedReader inn = new BufferedReader(new InputStreamReader(url.openStream()));
	        String s = inn.readLine();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
			
			country = jsonObject.get("country").toString();
			city = jsonObject.get("city").toString();
			longitude = jsonObject.get("lon").toString();
			latitude = jsonObject.get("lat").toString();
			
            TimeZone tz = TimeZone.getTimeZone((String)jsonObject.get("timezone"));		
			
			//timezone = String.valueOf(((tz.getOffset(System.currentTimeMillis())) / (60 * 60 * 1000D)));
            
		    int offsetInMillis = tz.getOffset(System.currentTimeMillis());

		    String offset = String.format("%02d.%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
		    timezone = (offsetInMillis >= 0 ? "" : "-") + offset;

			if(!country.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !longitude.equalsIgnoreCase("") && !latitude.equalsIgnoreCase("") && !timezone.equalsIgnoreCase(""))
			{
				b = true;
			}
		} catch (Exception e) {
			b = false;
			this.locationFromInternet.setEnabled(true);
			this.locationFromInternet.setIcon(locationfromInternetIcon);
		}
		return b;
	}

	public ImageIcon getLocationfromInternetIcon() {
		return locationfromInternetIcon;
	}

	public void setLocationfromInternetIcon(ImageIcon locationfromInternetIcon) {
		this.locationfromInternetIcon = locationfromInternetIcon;
	}

	public ImageIcon getLoaderIcon() {
		return loaderIcon;
	}

	public void setLoaderIcon(ImageIcon loaderIcon) {
		this.loaderIcon = loaderIcon;
	}
}
