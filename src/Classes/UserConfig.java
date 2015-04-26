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

public class UserConfig {

    private String country;//user country
    private String city;//user city
    private String longitude;//user city longitude
    private String latitude;//user city latitude
    private String timezone;//user city time zone
    private String language;//user language
    private String hijri;//user hijri time adjustment
    private String typetime;//user type time
    private String mazhab;//user mazhab calculation
    private String calculationMethod;//user calculation method
    private String fajr_time;//user fajr time adjustment
    private String shorouk_time;//user shorou9 time adjustment
    private String duhr_time;//user duhr time adjustment
    private String asr_time;//user asr time adjustment
    private String maghrib_time;//user maghrib time adjustment
    private String ishaa_time;//user ishaa time adjustment
    private String fajr_athan;//user fajr athan type
    private String shorouk_athan;//user shorou9 athan type
    private String duhr_athan;//user duhr athan type
    private String asr_athan;//user asr athan type
    private String maghrib_athan;//user maghrib athan type
    private String ishaa_athan;//user ishaa athan type
    private String athan;//user selected athan
    private String time12or24;//user time 12 or 24 selected

    private static UserConfig userConfig;

    private UserConfig() {
    }

    public static UserConfig getSingleton() {
        if (userConfig == null) {
            userConfig = new UserConfig();
        }
        return userConfig;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHijri() {
        return hijri;
    }

    public void setHijri(String hijri) {
        this.hijri = hijri;
    }

    public String getTypetime() {
        return typetime;
    }

    public void setTypetime(String typetime) {
        this.typetime = typetime;
    }

    public String getMazhab() {
        return mazhab;
    }

    public void setMazhab(String mazhab) {
        this.mazhab = mazhab;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getFajr_athan() {
        return fajr_athan;
    }

    public void setFajr_athan(String fajr_athan) {
        this.fajr_athan = fajr_athan;
    }

    public String getShorouk_athan() {
        return shorouk_athan;
    }

    public void setShorouk_athan(String shorouk_athan) {
        this.shorouk_athan = shorouk_athan;
    }

    public String getDuhr_athan() {
        return duhr_athan;
    }

    public void setDuhr_athan(String duhr_athan) {
        this.duhr_athan = duhr_athan;
    }

    public String getAsr_athan() {
        return asr_athan;
    }

    public void setAsr_athan(String asr_athan) {
        this.asr_athan = asr_athan;
    }

    public String getMaghrib_athan() {
        return maghrib_athan;
    }

    public void setMaghrib_athan(String maghrib_athan) {
        this.maghrib_athan = maghrib_athan;
    }

    public String getIshaa_athan() {
        return ishaa_athan;
    }

    public void setIshaa_athan(String ishaa_athan) {
        this.ishaa_athan = ishaa_athan;
    }

    public String getFajr_time() {
        return fajr_time;
    }

    public void setFajr_time(String fajr_time) {
        this.fajr_time = fajr_time;
    }

    public String getShorouk_time() {
        return shorouk_time;
    }

    public void setShorouk_time(String shorouk_time) {
        this.shorouk_time = shorouk_time;
    }

    public String getDuhr_time() {
        return duhr_time;
    }

    public void setDuhr_time(String duhr_time) {
        this.duhr_time = duhr_time;
    }

    public String getAsr_time() {
        return asr_time;
    }

    public void setAsr_time(String asr_time) {
        this.asr_time = asr_time;
    }

    public String getMaghrib_time() {
        return maghrib_time;
    }

    public void setMaghrib_time(String maghrib_time) {
        this.maghrib_time = maghrib_time;
    }

    public String getIshaa_time() {
        return ishaa_time;
    }

    public void setIshaa_time(String ishaa_time) {
        this.ishaa_time = ishaa_time;
    }

    public String getAthan() {
        return athan;
    }

    public void setAthan(String athan) {
        this.athan = athan;
    }

    public String getTime12or24() {
        return time12or24;
    }

    public void setTime12or24(String time12or24) {
        this.time12or24 = time12or24;
    }

}
