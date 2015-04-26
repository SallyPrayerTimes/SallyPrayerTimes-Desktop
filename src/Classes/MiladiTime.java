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
import java.util.Calendar;

public class MiladiTime {

    private int year;//actual year
    private int month;//actual month
    private int dayOfWeek;//actual day of week
    private int dayOfMonth;//actual day of month
    private StringBuffer stringBuffer;//actual miladi time 

    public MiladiTime(Calendar cal) throws IOException{//create single miladi object
        this.year = cal.get(Calendar.YEAR);//actual year
        this.month = cal.get(Calendar.MONTH);//actual month
        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//actual day of week
        this.dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);//actual day of month
    }

    public String getMonth(int i) throws IOException {//get translated month name
        String month1;
        month1 = "";
        int j = i;
        switch (j) {
            case 0:
                month1 = PropertiesHandler.getSingleton().getValue(1008);
                break;
            case 1:
                month1 = PropertiesHandler.getSingleton().getValue(1009);
                break;
            case 2:
                month1 = PropertiesHandler.getSingleton().getValue(1010);
                break;
            case 3:
                month1 = PropertiesHandler.getSingleton().getValue(1011);
                break;
            case 4:
                month1 = PropertiesHandler.getSingleton().getValue(1012);
                break;
            case 5:
                month1 = PropertiesHandler.getSingleton().getValue(1013);
                break;
            case 6:
                month1 = PropertiesHandler.getSingleton().getValue(1014);
                break;
            case 7:
                month1 = PropertiesHandler.getSingleton().getValue(1015);
                break;
            case 8:
                month1 = PropertiesHandler.getSingleton().getValue(1016);
                break;
            case 9:
                month1 = PropertiesHandler.getSingleton().getValue(1017);
                break;
            case 10:
                month1 = PropertiesHandler.getSingleton().getValue(1018);
                break;
            case 11:
                month1 = PropertiesHandler.getSingleton().getValue(1019);
                break;
        }
        return month1;
    }

    public String getDay(int i) throws IOException {//get translated day name
        String day = "";
        int j = i;
        switch (j) {
            case 1:
                day = PropertiesHandler.getSingleton().getValue(1001);
                break;
            case 2:
                day = PropertiesHandler.getSingleton().getValue(1002);
                break;
            case 3:
                day = PropertiesHandler.getSingleton().getValue(1003);
                break;
            case 4:
                day = PropertiesHandler.getSingleton().getValue(1004);
                break;
            case 5:
                day = PropertiesHandler.getSingleton().getValue(1005);
                break;
            case 6:
                day = PropertiesHandler.getSingleton().getValue(1006);
                break;
            case 7:
                day = PropertiesHandler.getSingleton().getValue(1007);
                break;
        }
        return day;
    }

    public String getMiladiTime() throws IOException {//get final translated miladi date
        this.stringBuffer = new StringBuffer();
        stringBuffer.append(getDay(dayOfWeek));
        stringBuffer.append(" ");
        stringBuffer.append(dayOfMonth);
        stringBuffer.append(" ");
        stringBuffer.append(getMonth(month));
        stringBuffer.append(" ");
        stringBuffer.append(year);
        return stringBuffer.toString();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

}
