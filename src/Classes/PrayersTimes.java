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

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class PrayersTimes implements Iconfig{

    private int year;//actual year
    private int month;//actual month
    private int day;//actual day
    private int totalMinutesFajr;//total fajr minutes
    private int totalMinutesShorouk;//total shorou9 minutes
    private int totalMinutesDuhr;//total duhr minutes
    private int totalMinutesAsr;//total asr minutes
    private int totalMinutesMaghrib;//total maghrib minutes
    private int totalMinutesIshaa;//total ishaa minutes
    private int[] allPrayrTimesInMinutes;//all prayer times in minutes
    private int time12or24 = 24;//calculate prayer times in 12h or 24H

    private String calculationMethod;
    private String mazhab;
    private String typeTime;

    private final double DegToRad = 0.017453292;
    private final double RadToDeg = 57.29577951;
    private double longitude;//longitude
    private double latitude;//latitude
    private double zoneTime;//time zone
    private double fajr;//fajr time
    private double shorou9;//shorou9 time
    private double duhr;//duhr time
    private double asr;//asr time
    private double maghrib;//maghrib time
    private double ishaa;//ishaa time
    private double dec;
    private double fajrAlt;
    private double ishaAlt;
    private double fajrTime;//fajr time adjustment
    private double shoroukTime;//shorou9 time adjustment
    private double duhrTime;//duhr time adjustment
    private double asrTime;//asr time adjustment
    private double maghribTime;//maghrib time adjustment
    private double ishaaTime;//ishaa time adjustment
    private NumberFormat formatter;

    public PrayersTimes(Calendar cal) throws IOException{
    	this.year = cal.get(Calendar.YEAR);
    	this.month = cal.get(Calendar.MONTH);
    	this.day = cal.get(Calendar.DAY_OF_MONTH);
    	setParameters(UserConfig.getSingleton());
    }

    public void setParameters(UserConfig userConfig) throws IOException {//set all parameters to PrayersTimes object for calculate the prayer times

    	this.time12or24 = Integer.valueOf(userConfig.getTime12or24());//set 12or24 H user selected
        this.longitude = Double.valueOf(userConfig.getLongitude());//set longitude from user selected city longitude
        this.latitude = Double.valueOf(userConfig.getLatitude());//set latitude from user selected city latitude
        this.zoneTime = Double.valueOf(userConfig.getTimezone());//set zone time from user selected city zone time
        this.calculationMethod = userConfig.getCalculationMethod();//set calendar from user selected calendar
        this.mazhab = userConfig.getMazhab();//set mazhab from user selected mazhab
        this.typeTime = userConfig.getTypetime();//set typetime from user selected type time

        this.fajrTime = Double.valueOf(userConfig.getFajr_time().toString());//set fajrTime adjustment
        this.shoroukTime = Double.valueOf(userConfig.getShorouk_time().toString());//set shorou9Time adjustment
        this.duhrTime = Double.valueOf(userConfig.getDuhr_time().toString());//set duhrTime adjustment
        this.asrTime = Double.valueOf(userConfig.getAsr_time().toString());//set asrTime adjustment
        this.maghribTime = Double.valueOf(userConfig.getMaghrib_time().toString());//set maghribTime adjustment
        this.ishaaTime = Double.valueOf(userConfig.getIshaa_time().toString());//set ishaaTime adjustment

        this.init();
        adjustTimes();
    }

    public void init() {//initial calculation
        this.formatter = new DecimalFormat("00");

        // ---------------------- Calculation Functions -----------------------
        // References:
        // http://www.icoproject.org/
        // http://qasweb.org/
        double julianDay = (367 * year) - (int) (((year + (int) (((month + 1) + 9) / 12)) * 7) / 4) + (int) (275 * (month + 1) / 9) + day - 730531.5;
        double sunLength = removeDublication(280.461 + 0.9856474 * julianDay);
        double middleSun = removeDublication(357.528 + 0.9856003 * julianDay);
        double lambda = removeDublication(sunLength + 1.915 * Math.sin(middleSun * DegToRad) + 0.02 * Math.sin(2 * middleSun * DegToRad));
        double obliquity = 23.439 - 0.0000004 * julianDay;
        double alpha = RadToDeg * Math.atan(Math.cos(obliquity * DegToRad) * Math.tan(lambda * DegToRad));

        if (lambda > 90 && lambda < 180) {
            alpha += 180;
        } else if (lambda > 180 && lambda < 360) {
            alpha += 360;
        }

        double st = removeDublication(100.46 + 0.985647352 * julianDay);

        this.dec = RadToDeg * Math.asin(Math.sin(obliquity * DegToRad) * Math.sin(lambda * DegToRad));

        double noon = alpha - st;
        if (noon < 0) {
            noon += 360;
        }
        double UTNoon = noon - longitude;

        double localNoon = (UTNoon / 15) + zoneTime;

        this.duhr = localNoon;
        this.maghrib = localNoon + equation(-0.8333) / 15;
        this.shorou9 = localNoon - equation(-0.8333) / 15;

        if (this.calculationMethod.equalsIgnoreCase(UmmAlQuraUniv)) {
            this.fajrAlt = -18.5;// fajr was 19 degrees before 1430 hijri
        } else if (this.calculationMethod.equalsIgnoreCase(EgytionGeneralAuthorityofSurvey)) {
            this.fajrAlt = -19.5;
            this.ishaAlt = -17.5;
        } else if (this.calculationMethod.equalsIgnoreCase(MuslimWorldLeague)) {
            this.fajrAlt = -18;
            this.ishaAlt = -17;
        } else if (this.calculationMethod.equalsIgnoreCase(IslamicSocietyOfNorthAmerica)) {
            this.fajrAlt = ishaAlt = -15;
        } else if (this.calculationMethod.equalsIgnoreCase(UnivOfIslamicScincesKarachi)) {
            this.fajrAlt = ishaAlt = -18;
        } else if (this.calculationMethod.equalsIgnoreCase(FederationofIslamicOrganizationsinFrance)) {
            this.fajrAlt = ishaAlt = -12;
        } else if (this.calculationMethod.equalsIgnoreCase(TheMinistryofAwqafandIslamicAffairsinKuwait)) {
            this.fajrAlt = -18;
            this.ishaAlt = -17.5;
        }

        this.fajr = localNoon - equation(fajrAlt) / 15;

        this.ishaa = localNoon + equation(ishaAlt) / 15;

        if (this.calculationMethod.equalsIgnoreCase(UmmAlQuraUniv)) {
			try {
				HijriTime hijriTime = new HijriTime(Calendar.getInstance());
	        	if(hijriTime.isRamadan()) 
	        	{
	        		this.ishaa = maghrib + 2;
	        	}
	        	else 
	        	{
	        		this.ishaa = maghrib + 1.5;
	        	}
			} catch (IOException e) {
	            this.ishaa = maghrib + 1.5;
			}
        }
        double asrAlt;

        if (this.mazhab.equalsIgnoreCase(hanafi)) {
            asrAlt = 90 - RadToDeg * Math.atan(2 + Math.tan(abs(latitude - dec) * DegToRad));
        } else {
            asrAlt = 90 - RadToDeg * Math.atan(1 + Math.tan(abs(latitude - dec) * DegToRad));
        }

        this.asr = localNoon + equation(asrAlt) / 15;

        if (this.typeTime.equalsIgnoreCase(sayfi)) {
            this.fajr += 1;
            this.shorou9 += 1;
            this.duhr += 1;
            this.asr += 1;
            this.maghrib += 1;
            this.ishaa += 1;
        }
    }

    public double removeDublication(double d) {
        if (d > 360) {
            d /= 360;
            d -= (int) (d);
            d *= 360;
        }
        return d;
    }

    public double equation(double d) {
        return RadToDeg
                * Math.acos((Math.sin(d * DegToRad) - Math
                        .sin(dec * DegToRad) * Math.sin(latitude * DegToRad))
                        / (Math.cos(dec * DegToRad) * Math.cos(latitude
                                * DegToRad)));
    }

    public double abs(double d) {
        if (d < 0) {
            return -d;
        } else {
            return d;
        }
    }

    public String getFajrFinalTime() { //get final fajr time    
        int salatHour = totalMinutesFajr / 60;
        int salatMinutes = totalMinutesFajr % 60;

        String salatAM_PM;

        if (salatHour >= 12) {
            salatAM_PM = "pm";
        } else {
            salatAM_PM = "am";
        }

        if (time12or24 == 12 && salatHour > 12) {
            salatHour -= 12;
        }

        salatAM_PM = (time12or24 == 12) ? salatAM_PM : "";

        return formatter.format(salatHour) + ":" + formatter.format(salatMinutes) + " " + salatAM_PM;
    }

    public String getShorou9FinalTime() {//get final shorou9 time  
        int salatHour = totalMinutesShorouk / 60;
        int salatMinutes = totalMinutesShorouk % 60;

        String salatAM_PM;

        if (salatHour >= 12) {
            salatAM_PM = "pm";
        } else {
            salatAM_PM = "am";
        }

        if (time12or24 == 12 && salatHour > 12) {
            salatHour -= 12;
        }

        salatAM_PM = (time12or24 == 12) ? salatAM_PM : "";

        return formatter.format(salatHour) + ":" + formatter.format(salatMinutes) + " " + salatAM_PM;
    }

    public String getDuhrFinalTime() {//get final duhr time  
        int salatHour = totalMinutesDuhr / 60;
        int salatMinutes = totalMinutesDuhr % 60;

        String salatAM_PM;

        if (salatHour >= 12) {
            salatAM_PM = "pm";
        } else {
            salatAM_PM = "am";
        }

        if (time12or24 == 12 && salatHour > 12) {
            salatHour -= 12;
        }

        salatAM_PM = (time12or24 == 12) ? salatAM_PM : "";

        return formatter.format(salatHour) + ":" + formatter.format(salatMinutes) + " " + salatAM_PM;
    }

    public String getAsrFinalTime() {//get final asr time  
        int salatHour = totalMinutesAsr / 60;
        int salatMinutes = totalMinutesAsr % 60;

        String salatAM_PM;

        if (salatHour >= 12) {
            salatAM_PM = "pm";
        } else {
            salatAM_PM = "am";
        }

        if (time12or24 == 12 && salatHour > 12) {
            salatHour -= 12;
        }

        salatAM_PM = (time12or24 == 12) ? salatAM_PM : "";

        return formatter.format(salatHour) + ":" + formatter.format(salatMinutes) + " " + salatAM_PM;
    }

    public String getMaghribFinalTime() {//get final maghrib time  
        int salatHour = totalMinutesMaghrib / 60;
        int salatMinutes = totalMinutesMaghrib % 60;

        String salatAM_PM;

        if (salatHour >= 12) {
            salatAM_PM = "pm";
        } else {
            salatAM_PM = "am";
        }

        if (time12or24 == 12 && salatHour > 12) {
            salatHour -= 12;
        }

        salatAM_PM = (time12or24 == 12) ? salatAM_PM : "";

        return formatter.format(salatHour) + ":" + formatter.format(salatMinutes) + " " + salatAM_PM;
    }

    public String getIshaaFinalTime() {//get final ishaa time  
        int salatHour = totalMinutesIshaa / 60;
        int salatMinutes = totalMinutesIshaa % 60;

        String salatAM_PM;

        if (salatHour >= 12) {
            salatAM_PM = "pm";
        } else {
            salatAM_PM = "am";
        }

        if (time12or24 == 12 && salatHour > 12) {
            salatHour -= 12;
        }

        salatAM_PM = (time12or24 == 12) ? salatAM_PM : "";

        return formatter.format(salatHour) + ":" + formatter.format(salatMinutes) + " " + salatAM_PM;
    }

    public static int getMinutes(double d) {//get minutes from calculated prayer time
        int h = (int) d;
        int m = (int) (Math.ceil((d - h) * 60));

        return (h * 60) + m;
    }

    public void adjustTimes() {//get all prayer times in minutes after adjustment

        this.allPrayrTimesInMinutes = new int[6];

        this.totalMinutesFajr = (int) (getMinutes(fajr) + fajrTime);//adjust fajr time
        this.totalMinutesShorouk = (int) (getMinutes(shorou9) + shoroukTime);//adjust shorou9 time
        this.totalMinutesDuhr = (int) (getMinutes(duhr) + duhrTime);//adjust duhr time
        this.totalMinutesAsr = (int) (getMinutes(asr) + asrTime);//adjust asr time
        this.totalMinutesMaghrib = (int) (getMinutes(maghrib) + maghribTime);//adjust maghrib time
        this.totalMinutesIshaa = (int) (getMinutes(ishaa) + ishaaTime);//adjust ishaa time

        this.allPrayrTimesInMinutes[0] = this.totalMinutesFajr;
        this.allPrayrTimesInMinutes[1] = this.totalMinutesShorouk;
        this.allPrayrTimesInMinutes[2] = this.totalMinutesDuhr;
        this.allPrayrTimesInMinutes[3] = this.totalMinutesAsr;
        this.allPrayrTimesInMinutes[4] = this.totalMinutesMaghrib;
        this.allPrayrTimesInMinutes[5] = this.totalMinutesIshaa;

        boolean b = true;

        while (b) {//adjust prayer times
            for (int i = 0; i < allPrayrTimesInMinutes.length; i++) {
                if (allPrayrTimesInMinutes[i] > 1440) {
                    for (int j = 0; j < allPrayrTimesInMinutes.length; j++) {
                        allPrayrTimesInMinutes[j] -= 720;
                    }
                    break;
                }
            }

            b = false;
        }

        this.totalMinutesFajr = allPrayrTimesInMinutes[0];//totlae fajr time in minutes after adjusment
        this.totalMinutesShorouk = allPrayrTimesInMinutes[1];//totlae shorou9 time in minutes after adjusment
        this.totalMinutesDuhr = allPrayrTimesInMinutes[2];//totlae duhr time in minutes after adjusment
        this.totalMinutesAsr = allPrayrTimesInMinutes[3];//totlae asr time in minutes after adjusment
        this.totalMinutesMaghrib = allPrayrTimesInMinutes[4];//totlae maghrib time in minutes after adjusment
        this.totalMinutesIshaa = allPrayrTimesInMinutes[5];//totlae ishaa time in minutes after adjusment
    }

    public int[] getAllPrayrTimesInMinutes() {//get all prayer times in minutes after adjustment
        return allPrayrTimesInMinutes;
    }

    public void setAllPrayrTimesInMinutes(int[] allPrayrTimesInMinutes) {
        this.allPrayrTimesInMinutes = allPrayrTimesInMinutes;
    }

    public int getTotaleMinutesFajr() {
        return totalMinutesFajr;
    }

    public void setTotaleMinutesFajr(int totaleMinutesFajr) {
        this.totalMinutesFajr = totaleMinutesFajr;
    }

    public int getTotaleMinutesShorouk() {
        return totalMinutesShorouk;
    }

    public void setTotaleMinutesShorouk(int totaleMinutesShorouk) {
        this.totalMinutesShorouk = totaleMinutesShorouk;
    }

    public int getTotaleMinutesDuhr() {
        return totalMinutesDuhr;
    }

    public void setTotaleMinutesDuhr(int totaleMinutesDuhr) {
        this.totalMinutesDuhr = totaleMinutesDuhr;
    }

    public int getTotaleMinutesAsr() {
        return totalMinutesAsr;
    }

    public void setTotaleMinutesAsr(int totaleMinutesAsr) {
        this.totalMinutesAsr = totaleMinutesAsr;
    }

    public int getTotaleMinutesMaghrib() {
        return totalMinutesMaghrib;
    }

    public void setTotaleMinutesMaghrib(int totaleMinutesMaghrib) {
        this.totalMinutesMaghrib = totaleMinutesMaghrib;
    }

    public int getTotaleMinutesIshaa() {
        return totalMinutesIshaa;
    }

    public void setTotaleMinutesIshaa(int totaleMinutesIshaa) {
        this.totalMinutesIshaa = totaleMinutesIshaa;
    }

    public Double getFajrTime() {
        return fajrTime;
    }

    public void setFajrTime(Double fajrTime) {
        this.fajrTime = fajrTime;
    }

    public Double getShoroukTime() {
        return shoroukTime;
    }

    public void setShoroukTime(Double shoroukTime) {
        this.shoroukTime = shoroukTime;
    }

    public Double getDuhrTime() {
        return duhrTime;
    }

    public void setDuhrTime(Double duhrTime) {
        this.duhrTime = duhrTime;
    }

    public Double getAsrTime() {
        return asrTime;
    }

    public void setAsrTime(Double asrTime) {
        this.asrTime = asrTime;
    }

    public Double getMaghribTime() {
        return maghribTime;
    }

    public void setMaghribTime(Double maghribTime) {
        this.maghribTime = maghribTime;
    }

    public Double getIshaaTime() {
        return ishaaTime;
    }

    public void setIshaaTime(Double ishaaTime) {
        this.ishaaTime = ishaaTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getZoneTime() {
        return zoneTime;
    }

    public void setZoneTime(double zoneTime) {
        this.zoneTime = zoneTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getMazhab() {
        return mazhab;
    }

    public void setMazhab(String mazhab) {
        this.mazhab = mazhab;
    }

    public String getTypeTime() {
        return typeTime;
    }

    public void setTypeTime(String typeTime) {
        this.typeTime = typeTime;
    }

    public double getFajr() {
        return fajr;
    }

    public void setFajr(double fajr) {
        this.fajr = fajr;
    }

    public double getShorou9() {
        return shorou9;
    }

    public void setShorou9(double shorou9) {
        this.shorou9 = shorou9;
    }

    public double getDuhr() {
        return duhr;
    }

    public void setDuhr(double duhr) {
        this.duhr = duhr;
    }

    public double getAsr() {
        return asr;
    }

    public void setAsr(double asr) {
        this.asr = asr;
    }

    public double getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(double maghrib) {
        this.maghrib = maghrib;
    }

    public double getIshaa() {
        return ishaa;
    }

    public void setIshaa(double isha) {
        this.ishaa = isha;
    }

    public double getDec() {
        return dec;
    }

    public void setDec(double dec) {
        this.dec = dec;
    }

    public double getFajrAlt() {
        return fajrAlt;
    }

    public void setFajrAlt(double fajrAlt) {
        this.fajrAlt = fajrAlt;
    }

    public double getIshaAlt() {
        return ishaAlt;
    }

    public void setIshaAlt(double ishaAlt) {
        this.ishaAlt = ishaAlt;
    }

    public String getStandard() {
        return standard;
    }

    public String getShafi3i() {
        return shafi3i;
    }

    public int getTime12or24() {
        return time12or24;
    }

    public void setTime12or24(int time12or24) {
        this.time12or24 = time12or24;
    }

}
