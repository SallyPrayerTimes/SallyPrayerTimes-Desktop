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

public interface Iconfig {
	
	public final String ali_ben_ahmed_mala = "ali_ben_ahmed_mala";
	public final String ali_ben_ahmed_mala_mp3 = "/Athan/ali_ben_ahmed_mala.mp3";
	public final String abd_el_basset_abd_essamad = "abd_el_basset_abd_essamad";
	public final String abd_el_basset_abd_essamad_mp3 = "/Athan/abd_el_basset_abd_essamad.mp3";
	public final String farou9_abd_errehmane_hadraoui = "farou9_abd_errehmane_hadraoui";
	public final String farou9_abd_errehmane_hadraoui_mp3 = "/Athan/farou9_abd_errehmane_hadraoui.mp3";
	public final String mohammad_ali_el_banna = "mohammad_ali_el_banna";
	public final String mohammad_ali_el_banna_mp3 = "/Athan/mohammad_ali_el_banna.mp3";
	public final String mohammad_khalil_raml = "mohammad_khalil_raml";
	public final String mohammad_khalil_raml_mp3 = "/Athan/mohammad_khalil_raml.mp3";
	
	public final String fajrBackground = "/Images/Background/fajrBackground.png";
	public final String shorou9Background = "/Images/Background/shorou9Background.png";
	public final String duhrBackground = "/Images/Background/duhrBackground.png";
	public final String asrBackground = "/Images/Background/asrBackground.png";
	public final String maghribBackground = "/Images/Background/maghribBackground.png";
	public final String ishaaBackground = "/Images/Background/ishaaBackground.png";
	
	public final String location_menu_icon = "/Images/Settings/location_menu_icon.png";
	public final String time_menu_icon = "/Images/Settings/time_menu_icon.png";
	public final String athan_menu_icon = "/Images/Settings/athan_menu_icon.png";
	public final String language_menu_icon = "/Images/Settings/language_menu_icon.png";
	public final String information_menu_icon = "/Images/Settings/information_menu_icon.png";
	
	public final String menu_button_background = "/Images/Settings/menu_button_background.png";
	
	public final String exiticon = "/Images/Settings/exit.png";
	public final String settingsIconPath = "/Images/Settings/settings.png";
	public final String locationInternetIconSetting = "/Images/Settings/location_internet_settings.png";
	public final String locationInternetIconMain = "/Images/Settings/location_internet_main.png";
	public final String loaderSettingsPath = "/Images/Settings/loaderSettings.gif";
	public final String loaderMainPath = "/Images/Settings/loaderMain.gif";
	public final String donateButtonPath = "/Images/Settings/donate_button.png";
	
	public final String nextDayPrayerTimesIcon = "/Images/Settings/nextDayPrayersTimesIcon.png";
	public final String previousDayPrayerTimesIcon = "/Images/Settings/previousDayPrayerTimesIcon.png";
	public final String actualDayPrayerTimesIcon = "/Images/Settings/actualDayPrayersTimesIcon.png";
	
	public final String athanOnIconPath = "/Images/Settings/athan_on.png";
	public final String athanOffIconPath = "/Images/Settings/athan_off.png";
	public final String athanNotificationIconPath = "/Images/Settings/athan_notification.png";
	
	public final String radio_button_selected = "/Images/Settings/radio_button_selected.png";
	public final String radio_button_unselected = "/Images/Settings/radio_button_unselected.png";
	
	public final String sallyIcon = "/Images/Settings/sally_icon.png";
	public final String athan = "athan";
	public final String notification = "notification";
	public final String none = "none";
	public final String UmmAlQuraUniv = "UmmAlQuraUniv";// Umm al-Qura, Makkah
	public final String EgytionGeneralAuthorityofSurvey = "EgytionGeneralAuthorityofSurvey";// Egyptian General Authority of Survey
	public final String UnivOfIslamicScincesKarachi = "UnivOfIslamicScincesKarachi";// University of Islamic Sciences, Karachi
	public final String IslamicSocietyOfNorthAmerica = "IslamicSocietyOfNorthAmerica";// Islamic Society of North America (ISNA)
	public final String MuslimWorldLeague = "MuslimWorldLeague";// Muslim World League (MWL)
	public final String shafi3i = "shafi3i";//shafi3i calculation method
	public final String hanafi = "hanafi";//hanafi calculation method
	public final String sayfi = "sayfi";//sayfi type time
	public final String standard = "standard";//standard type time
	public final String ENG_properties = "/Properties/ENG.properties";
	public final String IT_properties = "/Properties/IT.properties";
	public final String AR_properties = "/Properties/AR.properties";
	public final String FR_properties = "/Properties/FR.properties";
	public final String it = "it";
	public final String ar = "ar";
	public final String fr = "fr";
	public final String eng = "eng";
	public final String startError = "corrupt sallyprayertimes.xml file , delete it if exists and restart or check your permission";
	public final String sallyUserConfig_xml = ".sallyprayertimes.xml";
	public final String sallyUserConfig_xml_2 = "\\.sallyprayertimes.xml";
	public final String permission_error = "cannot create sallyprayertimes.xml file , check your permission";
	public final String countries_xml = "/XmlFiles/countries.xml";
	public final String xmlFiles_cities = "/XmlFiles/cities/";
	public final String name = "name";
	public final String city = "city";
	public final String daylight = "daylight";
	public final String latitude = "latitude";
	public final String longitude = "longitude";
	public final String state = "state";
	public final String timezone = "timezone";
	public final String country = "country";
	public final String language = "language";
	public final String hijri = "hijri";
	public final String typetime = "typetime";
	public final String mazhab = "mazhab";
	public final String calculationMethod = "calculationMethod";
	public final String fajr_time = "fajr_time";
	public final String shorouk_time = "shorouk_time";
	public final String duhr_time = "duhr_time";
	public final String asr_time = "asr_time";
	public final String maghrib_time = "maghrib_time";
	public final String ishaa_time = "ishaa_time";
	public final String fajr_athan = "fajr_athan";
	public final String shorouk_athan = "shorouk_athan";
	public final String duhr_athan = "duhr_athan";
	public final String asr_athan = "asr_athan";
	public final String maghrib_athan = "maghrib_athan";
	public final String ishaa_athan = "ishaa_athan";
	public final String time12or24 = "time12or24";
	public final String userConfig = "userConfig";
	public final String playIcon = "/Images/Settings/play.png";
	public final String pauseicon = "/Images/Settings/pause.png";
	public final String stopIcon = "/Images/Settings/stop.png";
	public final String applicationVersion = " : Final 10.05.20 \n\n\n";
	public final String email = "  bibali1980@gmail.com \n";
	public final String sallyWebSite = "  http://sallyprayertimes.altervista.org \n";
	public final String sallyfacebookPage = "  https://www.facebook.com/sallyprayertimes \n";
}
