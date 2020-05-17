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
import java.util.Calendar;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;

public class HijriTime {

    private Calendar calendar;//Calendar object
    private int day;//actual day
    private int month;//actual month
    private int year;//actual year
    private String[] hijriDaysNames;//hijri days names
    private String[] hijriMonthsNames;//hijri months names
    private DecimalFormat dayFormatter;//day formatter
    private DecimalFormat yearFormatter;//year formatter

    public HijriTime(Calendar cal) throws IOException{//create a single hijriTime object
    	this.calendar = cal;
        this.hijriDaysNames = getDaysNames();//getting translating hijri days names 
        this.hijriMonthsNames = getMonthsNames();//getting translating hijri months names 

        try{
        this.calendar.add(Calendar.DATE, Integer.valueOf(UserConfig.getSingleton().getHijri()));
        }catch(Exception e){
        	this.calendar = cal;
        }
      
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.year = calendar.get(Calendar.YEAR);
    }
    
    public String[] getDaysNames() throws IOException {//getting translating hijri days names 
        String[] daysNames = new String[8];
        int j = 1;
        for (int i = 1038; i < 1045; i++) {
            daysNames[j] = PropertiesHandler.getSingleton().getValue(i);
            j++;
        }
        return daysNames;
    }

    public String[] getMonthsNames() throws IOException {//getting translating hijri months names
        String[] monthsNames = new String[13];
        int j = 1;
        for (int i = 1026; i < 1038; i++) {
            monthsNames[j] = PropertiesHandler.getSingleton().getValue(i);
            j++;
        }
        return monthsNames;
    }

    public String getHijriTime(){////get final translated hijri date
        this.dayFormatter = new DecimalFormat("00");
        this.yearFormatter = new DecimalFormat("0000");

        Chronology iSOChronology = ISOChronology.getInstanceUTC();//get ISOChronology instance
        Chronology islamicChronology = IslamicChronology.getInstanceUTC();//get IslamicChronology instance

        LocalDate localDateISOChronology = new LocalDate(year, month, day, iSOChronology);//get local date
        LocalDate HijriDate = new LocalDate(localDateISOChronology.toDate(), islamicChronology);//get hijri date

        return hijriDaysNames[calendar.get(Calendar.DAY_OF_WEEK)] + " " + dayFormatter.format(HijriDate.getDayOfMonth()) + " "
                + hijriMonthsNames[HijriDate.getMonthOfYear()] + " " + yearFormatter.format(HijriDate.getYear());
    }
    
    public boolean isRamadan() 
    {
    	this.dayFormatter = new DecimalFormat("00");
        this.yearFormatter = new DecimalFormat("0000");

        Chronology iSOChronology = ISOChronology.getInstanceUTC();//get ISOChronology instance
        Chronology islamicChronology = IslamicChronology.getInstanceUTC();//get IslamicChronology instance

        LocalDate localDateISOChronology = new LocalDate(year, month, day, iSOChronology);//get local date
        LocalDate HijriDate = new LocalDate(localDateISOChronology.toDate(), islamicChronology);//get hijri date
        
        if(HijriDate.getMonthOfYear() == 9) 
        {
        	return true;
        }
        else 
        {
        	return false;
        }
    }

}
