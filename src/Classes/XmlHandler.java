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

import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlHandler implements Iconfig{

    private DocumentBuilderFactory documentBuilderFactory;//DocumentBuilderFactory object
    private DocumentBuilder documentBuilder;//DocumentBuilder object
    private Document document;//Document object
    private Element element;//Element object
    private NodeList countriesNodes;//countries nodes object
    private NodeList citiesNodes;//cities nodes object
    private NodeList userConfigNodes;//userConfig nodes object
    private File userConfigFile;//userConfigFile file
    private static XmlHandler xmlHandler;//object handle user configuration xml file

    public static XmlHandler getSingleton() {//get single object XmlHandler
        if (xmlHandler == null) {
            xmlHandler = new XmlHandler();
        }
        return xmlHandler;
    }

    public XmlHandler() {
        try {
        	//create sallyUserConfig.xml file in the some directory of program
            this.userConfigFile = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + sallyUserConfig_xml);
            if (!this.userConfigFile.exists()) {
                this.userConfigFile = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath() + sallyUserConfig_xml);
                addDefaultUserConfig();//if the first time of creation sallyUserConfig.xml file 
            }
        } catch (Exception e) {
        	//if you can not create sallyUserConfig.xml file in the some directory of program , create it in the user home directory
            this.userConfigFile = new File(System.getProperty("user.home") + sallyUserConfig_xml_2);
            if (!this.userConfigFile.exists()) {
                this.userConfigFile = new File(System.getProperty("user.home") + sallyUserConfig_xml_2);
                 try {
					addDefaultUserConfig();
				} catch (Exception e1) {}//if the first time of creation sallyUserConfig.xml file 
            }
        } finally {
            if (!this.userConfigFile.exists()) {
                try {
                    this.userConfigFile.createNewFile();
                    addDefaultUserConfig();
                } catch (Exception e) {
                    try {
                        JOptionPane.showMessageDialog(null, permission_error, "Error", JOptionPane.ERROR_MESSAGE);//error if cannot create sallyUserConfig.xml file
                    } catch (HeadlessException ee) {
                    }
                    System.exit(0);
                }
            }
        }
    }

    public ArrayList<Country> getAllCountries() throws ParserConfigurationException, SAXException, IOException {//get list for all countries from countries.xml file
        ArrayList<Country> countries = new ArrayList<Country>();
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse(getClass().getResourceAsStream(countries_xml));
        this.element = document.getDocumentElement();
        this.countriesNodes = element.getChildNodes();
        for (int i = 0; i < countriesNodes.getLength(); i++) {
            Node node = countriesNodes.item(i);
            if (node instanceof Element) {
                Country country = new Country();//create new country
                country.setName(((Element) node).getAttribute(name));//set country name
                countries.add(country);//adding country to countries list
            }
        }
        return countries;
    }

    public ArrayList<City> getAllCities(String cityName) throws ParserConfigurationException, SAXException, IOException {//get list for all cities from cities folder
        ArrayList<City> cities = new ArrayList<City>();
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        String cityFinal = cityName.replace(" ", "_").toLowerCase();
        this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse(getClass().getResourceAsStream(xmlFiles_cities + cityFinal + ".xml"));
        this.element = document.getDocumentElement();
        this.citiesNodes = document.getElementsByTagName(city);
        for (int i = 0; i < citiesNodes.getLength(); i++) {
            Node node = citiesNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                City city = new City();//ctreat new city
                Element element1;
                element1 = (Element) node;

                city.setName(element1.getAttribute(name));//set city name

                NodeList _daylight = element1.getElementsByTagName(daylight);
                Element daylightElement = (Element) _daylight.item(0);

                NodeList daylightList = daylightElement.getChildNodes();
                city.setDaylight(((Node) daylightList.item(0)).getNodeValue().trim());//set city daylight

                NodeList _latitude = element1.getElementsByTagName(latitude);
                Element latitudeElement = (Element) _latitude.item(0);

                NodeList latitudeList = latitudeElement.getChildNodes();
                city.setLatitude(((Node) latitudeList.item(0)).getNodeValue().trim());//set city latitude

                NodeList _longitude = element1.getElementsByTagName(longitude);
                Element longitudeElement = (Element) _longitude.item(0);

                NodeList longitudeList = longitudeElement.getChildNodes();
                city.setLongitude(((Node) longitudeList.item(0)).getNodeValue().trim());//set ciry longitude

                try {
                    NodeList _state = element1.getElementsByTagName(state);
                    Element stateElement = (Element) _state.item(0);
                    NodeList stateList = stateElement.getChildNodes();
                    city.setState(((Node) stateList.item(0)).getNodeValue().trim());//set city state
                } catch (Exception e) {
                    city.setState("");
                }

                NodeList _timezone = element1.getElementsByTagName(timezone);
                Element timezoneElement = (Element) _timezone.item(0);

                NodeList timezoneList = timezoneElement.getChildNodes();
                city.setTimezone(((Node) timezoneList.item(0)).getNodeValue().trim());//set city time zone

                cities.add(city);//adding city to cities list
            }
        }
        return cities;
    }

    public UserConfig getUserConfig() throws ParserConfigurationException, SAXException, IOException {//get saved user configuration from sallyUserConfig.xml file
        UserConfig userConfig = UserConfig.getSingleton();
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse(this.userConfigFile);
        this.element = document.getDocumentElement();
        this.userConfigNodes = document.getElementsByTagName("userConfig");
        for (int i = 0; i < userConfigNodes.getLength(); i++) {
            Node node = userConfigNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element2;
                element2 = (Element) node;

                NodeList _country = element2.getElementsByTagName(country);//get saved user country
                Element countryElement = (Element) _country.item(0);
                NodeList countryList = countryElement.getChildNodes();
                userConfig.setCountry(((Node) countryList.item(0)).getNodeValue().trim());

                NodeList _city = element2.getElementsByTagName(city);//get saved user city
                Element cityElement = (Element) _city.item(0);
                NodeList cityList = cityElement.getChildNodes();
                userConfig.setCity(((Node) cityList.item(0)).getNodeValue().trim());

                NodeList _longitude = element2.getElementsByTagName(longitude);//get saved user longitude
                Element longitudeElement = (Element) _longitude.item(0);
                NodeList longitudeList = longitudeElement.getChildNodes();
                userConfig.setLongitude(((Node) longitudeList.item(0)).getNodeValue().trim());

                NodeList _latitude = element2.getElementsByTagName(latitude);//get saved user latitude
                Element latitudeElement = (Element) _latitude.item(0);
                NodeList latitudeList = latitudeElement.getChildNodes();
                userConfig.setLatitude(((Node) latitudeList.item(0)).getNodeValue().trim());

                NodeList _timezone = element2.getElementsByTagName(timezone);//get saved user time zone
                Element timezoneElement = (Element) _timezone.item(0);
                NodeList timezoneList = timezoneElement.getChildNodes();
                userConfig.setTimezone(((Node) timezoneList.item(0)).getNodeValue().trim());

                NodeList _language = element2.getElementsByTagName(language);//get saved user language
                Element languageElement = (Element) _language.item(0);
                NodeList languageList = languageElement.getChildNodes();
                userConfig.setLanguage(((Node) languageList.item(0)).getNodeValue().trim());

                NodeList _hijri = element2.getElementsByTagName(hijri);//get saved user hijri adjustment
                Element hijriElement = (Element) _hijri.item(0);
                NodeList hijriList = hijriElement.getChildNodes();
                userConfig.setHijri(((Node) hijriList.item(0)).getNodeValue().trim());

                NodeList _typetime = element2.getElementsByTagName(typetime);//get saved user type time
                Element typetimeElement = (Element) _typetime.item(0);
                NodeList typetimeList = typetimeElement.getChildNodes();
                userConfig.setTypetime(((Node) typetimeList.item(0)).getNodeValue().trim());

                NodeList _mazhab = element2.getElementsByTagName(mazhab);//get saved user mazhab 
                Element mazhabElement = (Element) _mazhab.item(0);
                NodeList mazhabList = mazhabElement.getChildNodes();
                userConfig.setMazhab(((Node) mazhabList.item(0)).getNodeValue().trim());

                NodeList _calculationMethod = element2.getElementsByTagName(calculationMethod);//get saved user calculation method
                Element calculationMethodElement = (Element) _calculationMethod.item(0);
                NodeList calculationMethodList = calculationMethodElement.getChildNodes();
                userConfig.setCalculationMethod(((Node) calculationMethodList.item(0)).getNodeValue().trim());

                NodeList _fajr_time = element2.getElementsByTagName(fajr_time);//get saved user fajr time adjustment
                Element fajr_timeElement = (Element) _fajr_time.item(0);
                NodeList fajr_timeList = fajr_timeElement.getChildNodes();
                userConfig.setFajr_time(((Node) fajr_timeList.item(0)).getNodeValue().trim());

                NodeList _shorouk_time = element2.getElementsByTagName(shorouk_time);//get saved user shorou9 time adjustment
                Element shorouk_timeElement = (Element) _shorouk_time.item(0);
                NodeList shorouk_timeList = shorouk_timeElement.getChildNodes();
                userConfig.setShorouk_time(((Node) shorouk_timeList.item(0)).getNodeValue().trim());

                NodeList _duhr_time = element2.getElementsByTagName(duhr_time);//get saved user duhr time adjustment
                Element duhr_timeElement = (Element) _duhr_time.item(0);
                NodeList duhr_timeList = duhr_timeElement.getChildNodes();
                userConfig.setDuhr_time(((Node) duhr_timeList.item(0)).getNodeValue().trim());

                NodeList _asr_time = element2.getElementsByTagName(asr_time);//get saved user asr time adjustment
                Element asr_timeElement = (Element) _asr_time.item(0);
                NodeList asr_timeList = asr_timeElement.getChildNodes();
                userConfig.setAsr_time(((Node) asr_timeList.item(0)).getNodeValue().trim());

                NodeList _maghrib_time = element2.getElementsByTagName(maghrib_time);//get saved user maghrib time adjustment
                Element maghrib_timeElement = (Element) _maghrib_time.item(0);
                NodeList maghrib_timeList = maghrib_timeElement.getChildNodes();
                userConfig.setMaghrib_time(((Node) maghrib_timeList.item(0)).getNodeValue().trim());

                NodeList _ishaa_time = element2.getElementsByTagName(ishaa_time);//get saved user ishaa time adjustment
                Element ishaa_timeElement = (Element) _ishaa_time.item(0);
                NodeList ishaa_timeList = ishaa_timeElement.getChildNodes();
                userConfig.setIshaa_time(((Node) ishaa_timeList.item(0)).getNodeValue().trim());

                NodeList _fajr_athan = element2.getElementsByTagName(fajr_athan);//get saved user fajr athan type
                Element fajr_athanElement = (Element) _fajr_athan.item(0);
                NodeList fajr_athanList = fajr_athanElement.getChildNodes();
                userConfig.setFajr_athan(((Node) fajr_athanList.item(0)).getNodeValue().trim());

                NodeList _shorouk_athan = element2.getElementsByTagName(shorouk_athan);//get saved user shorou9 athan type
                Element shorouk_athanElement = (Element) _shorouk_athan.item(0);
                NodeList shorouk_athanList = shorouk_athanElement.getChildNodes();
                userConfig.setShorouk_athan(((Node) shorouk_athanList.item(0)).getNodeValue().trim());

                NodeList _duhr_athan = element2.getElementsByTagName(duhr_athan);//get saved user duhr athan type
                Element duhr_athanElement = (Element) _duhr_athan.item(0);
                NodeList duhr_athanList = duhr_athanElement.getChildNodes();
                userConfig.setDuhr_athan(((Node) duhr_athanList.item(0)).getNodeValue().trim());

                NodeList _asr_athan = element2.getElementsByTagName(asr_athan);//get saved user asr athan type
                Element asr_athanElement = (Element) _asr_athan.item(0);
                NodeList asr_athanList = asr_athanElement.getChildNodes();
                userConfig.setAsr_athan(((Node) asr_athanList.item(0)).getNodeValue().trim());

                NodeList _maghrib_athan = element2.getElementsByTagName(maghrib_athan);//get saved user maghrib athan type
                Element maghrib_athanElement = (Element) _maghrib_athan.item(0);
                NodeList maghrib_athanList = maghrib_athanElement.getChildNodes();
                userConfig.setMaghrib_athan(((Node) maghrib_athanList.item(0)).getNodeValue().trim());

                NodeList _ishaa_athan = element2.getElementsByTagName(ishaa_athan);//get saved user ishaa athan type
                Element ishaa_athanElement = (Element) _ishaa_athan.item(0);
                NodeList ishaa_athanList = ishaa_athanElement.getChildNodes();
                userConfig.setIshaa_athan(((Node) ishaa_athanList.item(0)).getNodeValue().trim());

                NodeList _athan = element2.getElementsByTagName(athan);//get saved user athan
                Element athanElement = (Element) _athan.item(0);
                NodeList athanList = athanElement.getChildNodes();
                userConfig.setAthan(((Node) athanList.item(0)).getNodeValue().trim());

                NodeList _time12or24 = element2.getElementsByTagName(time12or24);//get saved user time12or24
                Element time12or24Element = (Element) _time12or24.item(0);
                NodeList time12or24List = time12or24Element.getChildNodes();
                userConfig.setTime12or24(((Node) time12or24List.item(0)).getNodeValue().trim());

            }
        }
        return userConfig;
    }

    public void setUserConfig(String name, String value) throws ParserConfigurationException, SAXException, IOException, TransformerException {//save new user configuration to sallyUserConfig.xml file
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        this.documentBuilderFactory.setCoalescing(true);
        this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.parse(this.userConfigFile);
        Element element3;
        element3 = document.getDocumentElement();

        NodeList nodeList = element3.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals(name)) {
                nodeList.item(i).setTextContent(value);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
        String s = writer.toString();

        FileWriter fileWriter = new FileWriter(this.userConfigFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(s);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void addUserConfig(UserConfig userConfigObject) throws ParserConfigurationException, TransformerException, SAXException, IOException {//write all user configuration to sallyUserConfig.xml file for the first time
        createFileIfNotExist();
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
        this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
        this.document = documentBuilder.newDocument();

        Element rootElement = document.createElement(userConfig);
        this.document.appendChild(rootElement);

        Element countryElement = document.createElement(country);
        countryElement.appendChild(document.createTextNode(userConfigObject.getCountry()));
        rootElement.appendChild(countryElement);

        Element cityElement = document.createElement(city);
        cityElement.appendChild(document.createTextNode(userConfigObject.getCity()));
        rootElement.appendChild(cityElement);

        Element longitudeElement = document.createElement(longitude);
        longitudeElement.appendChild(document.createTextNode(userConfigObject.getLongitude()));
        rootElement.appendChild(longitudeElement);

        Element latitudeElement = document.createElement(latitude);
        latitudeElement.appendChild(document.createTextNode(userConfigObject.getLatitude()));
        rootElement.appendChild(latitudeElement);

        Element timezoneElement = document.createElement(timezone);
        timezoneElement.appendChild(document.createTextNode(userConfigObject.getTimezone()));
        rootElement.appendChild(timezoneElement);

        Element languageElement = document.createElement(language);
        languageElement.appendChild(document.createTextNode(userConfigObject.getLanguage()));
        rootElement.appendChild(languageElement);

        Element hijriElement = document.createElement(hijri);
        hijriElement.appendChild(document.createTextNode(userConfigObject.getHijri()));
        rootElement.appendChild(hijriElement);

        Element typetimeElement = document.createElement(typetime);
        typetimeElement.appendChild(document.createTextNode(userConfigObject.getTypetime()));
        rootElement.appendChild(typetimeElement);

        Element mazhabElement = document.createElement(mazhab);
        mazhabElement.appendChild(document.createTextNode(userConfigObject.getMazhab()));
        rootElement.appendChild(mazhabElement);

        Element calculationMethodElement = document.createElement(calculationMethod);
        calculationMethodElement.appendChild(document.createTextNode(userConfigObject.getCalculationMethod()));
        rootElement.appendChild(calculationMethodElement);

        Element fajr_timeElement = document.createElement(fajr_time);
        fajr_timeElement.appendChild(document.createTextNode(userConfigObject.getFajr_time()));
        rootElement.appendChild(fajr_timeElement);

        Element shorouk_timeElement = document.createElement(shorouk_time);
        shorouk_timeElement.appendChild(document.createTextNode(userConfigObject.getShorouk_time()));
        rootElement.appendChild(shorouk_timeElement);

        Element duhr_timeElement = document.createElement(duhr_time);
        duhr_timeElement.appendChild(document.createTextNode(userConfigObject.getDuhr_time()));
        rootElement.appendChild(duhr_timeElement);

        Element asr_timeElement = document.createElement(asr_time);
        asr_timeElement.appendChild(document.createTextNode(userConfigObject.getAsr_time()));
        rootElement.appendChild(asr_timeElement);

        Element maghrib_timeElement = document.createElement(maghrib_time);
        maghrib_timeElement.appendChild(document.createTextNode(userConfigObject.getMaghrib_time()));
        rootElement.appendChild(maghrib_timeElement);

        Element ishaa_timeElement = document.createElement(ishaa_time);
        ishaa_timeElement.appendChild(document.createTextNode(userConfigObject.getIshaa_time()));
        rootElement.appendChild(ishaa_timeElement);

        Element fajr_athanElement = document.createElement(fajr_athan);
        fajr_athanElement.appendChild(document.createTextNode(userConfigObject.getFajr_athan()));
        rootElement.appendChild(fajr_athanElement);

        Element shorouk_athanElement = document.createElement(shorouk_athan);
        shorouk_athanElement.appendChild(document.createTextNode(userConfigObject.getShorouk_athan()));
        rootElement.appendChild(shorouk_athanElement);

        Element duhr_athanElement = document.createElement(duhr_athan);
        duhr_athanElement.appendChild(document.createTextNode(userConfigObject.getDuhr_athan()));
        rootElement.appendChild(duhr_athanElement);

        Element asr_athanElement = document.createElement(asr_athan);
        asr_athanElement.appendChild(document.createTextNode(userConfigObject.getAsr_athan()));
        rootElement.appendChild(asr_athanElement);

        Element maghrib_athanElement = document.createElement(maghrib_athan);
        maghrib_athanElement.appendChild(document.createTextNode(userConfigObject.getMaghrib_athan()));
        rootElement.appendChild(maghrib_athanElement);

        Element ishaa_athanElement = document.createElement(ishaa_athan);
        ishaa_athanElement.appendChild(document.createTextNode(userConfigObject.getIshaa_athan()));
        rootElement.appendChild(ishaa_athanElement);

        Element athanElement = document.createElement(athan);
        athanElement.appendChild(document.createTextNode(userConfigObject.getAthan()));
        rootElement.appendChild(athanElement);

        Element time12or24Element = document.createElement(time12or24);
        time12or24Element.appendChild(document.createTextNode(userConfigObject.getTime12or24()));
        rootElement.appendChild(time12or24Element);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(this.userConfigFile);
        transformer.transform(source, result);
    }

    private void addDefaultUserConfig() throws ParserConfigurationException, TransformerException, SAXException, IOException {//adding the default user configuration for the first time
        UserConfig userConfig = UserConfig.getSingleton();
        userConfig.setCountry("Saudi Arabia");
        userConfig.setCity("Makkah");
        userConfig.setLongitude("39.8409");
        userConfig.setLatitude("21.4309");
        userConfig.setTimezone("3.0");
        userConfig.setCalculationMethod(MuslimWorldLeague);
        userConfig.setHijri("0");
        userConfig.setTypetime(standard);
        userConfig.setLanguage(eng);
        userConfig.setMazhab(shafi3i);
        userConfig.setFajr_athan(athan);
        userConfig.setShorouk_athan(athan);
        userConfig.setDuhr_athan(athan);
        userConfig.setAsr_athan(athan);
        userConfig.setMaghrib_athan(athan);
        userConfig.setIshaa_athan(athan);
        userConfig.setFajr_time("0");
        userConfig.setShorouk_time("0");
        userConfig.setDuhr_time("0");
        userConfig.setAsr_time("0");
        userConfig.setMaghrib_time("0");
        userConfig.setIshaa_time("0");
        userConfig.setAthan(ali_ben_ahmed_mala);
        userConfig.setTime12or24("24");
        addUserConfig(userConfig);
    }

    public void createFileIfNotExist() throws IOException, ParserConfigurationException, TransformerException, SAXException {//create sallyUserConfig.xml file if not exist
        if (!this.userConfigFile.exists()) {
            this.userConfigFile.createNewFile();
        }
    }

    public NodeList getCountriesNodes() {
        return countriesNodes;
    }

    public void setCountriesNodes(NodeList countriesNodes) {
        this.countriesNodes = countriesNodes;
    }

    public NodeList getCitiesNodes() {
        return citiesNodes;
    }

    public void setCitiesNodes(NodeList citiesNodes) {
        this.citiesNodes = citiesNodes;
    }

}
