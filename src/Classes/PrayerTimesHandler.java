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
import java.awt.Image;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;

public class PrayerTimesHandler extends Thread implements Iconfig{

    private int[] prayerTimesInMinutes;//all prayer times in minutes
    private int nextPrayerTimeInMinutes;//next prayer time in minutes
	private int totalMinutes;//next prayer time in minutes
	private int nextPrayerCode;

    private Calendar calendar;//Calendar object
    private int hour;// hour
    private int minutes;// minutes
    private int second;// seconds

    private String missingPropertiesValue;//translated "missing" term
	private String toPropertiesValue;//translated "to" term
	private String actualSalatNameProperties;//translated actual salat name


	private final MainForm mainForm;//main form
    private final DecimalFormat formatter;//formatter
    
    private int actualPrayerTime;
    private boolean isAthanStarted = false;//if athan started or not
    
	private boolean isTimePlus1 = true;

    public PrayerTimesHandler(MainForm mainForm, PrayersTimes prayersTimes) {
        this.prayerTimesInMinutes = new int[6];
        this.prayerTimesInMinutes = prayersTimes.getAllPrayrTimesInMinutes();//get all prayer times in minutes

        this.mainForm = mainForm;
        this.formatter = new DecimalFormat("00");
        
        Calendar calendar = Calendar.getInstance();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        
        this.calendar = calendar;
           
        getNextPrayer((hour * 60) + minutes);//get next prayer code,minutes,missing minutes
    }

    @Override
    public void run() {
         try {
            this.missingPropertiesValue = PropertiesHandler.getSingleton().getValue(1045);//translated "missing" term
            this.toPropertiesValue = PropertiesHandler.getSingleton().getValue(1046);//translated "to" term
            this.actualSalatNameProperties = PropertiesHandler.getSingleton().getValue(this.nextPrayerCode);//translated actual salat name
        } catch (Exception e) {
        }

        while (true) {
            try {
    	           Calendar calendar = Calendar.getInstance();
       	           this.hour = calendar.get(Calendar.HOUR_OF_DAY);
       	           this.minutes = calendar.get(Calendar.MINUTE);
       	           this.second = calendar.get(Calendar.SECOND);
       	           
       	           this.totalMinutes = (hour * 60) + minutes;
                
                this.mainForm.getDigitalClock().setText(this.formatter.format(this.hour) + ":" + this.formatter.format(this.minutes) + ":" + this.formatter.format(this.second));//display digital clock
                
                if (isAfterDay(calendar, this.calendar)) {//if hour is 24H calculate prayer times for next day
                	refreshNextDay();//calculate new value of prayer times for next day
                	this.calendar = calendar;
                }
                else
                {
                	if (this.totalMinutes == this.nextPrayerTimeInMinutes) {//if actual hour and actual minutes equal the next prayer time hour and minutes
                        
                    	this.mainForm.getNextPrayerLabel().setForeground(Color.RED);
                        this.mainForm.getNextPrayerLabel().setText(PropertiesHandler.getSingleton().getValue(1047) + " " + this.actualSalatNameProperties);
                        
                        if (this.isAthanStarted == false) {
                            
                        switch (this.nextPrayerCode) {//set background
                		case 1020:
                			Image fajrBackgroundImage = new ImageIcon(getClass().getResource(fajrBackground)).getImage(); 
                			this.mainForm.setBackgroundImage(fajrBackgroundImage);
                			this.mainForm.getMainPanel().setImagePanel(fajrBackgroundImage);
                			this.mainForm.getMainPanel().setActualPrayerTime(2);
                			this.mainForm.setBackgroundImage(fajrBackgroundImage);
                			break;
                		case 1021:
                			Image shorou9BackgroundImage = new ImageIcon(getClass().getResource(shorou9Background)).getImage();
                			this.mainForm.setBackgroundImage(shorou9BackgroundImage);
                			this.mainForm.getMainPanel().setImagePanel(shorou9BackgroundImage);
                			this.mainForm.getMainPanel().setActualPrayerTime(1);
                			this.mainForm.setBackgroundImage(shorou9BackgroundImage);
                			break;
                		case 1022:
                			Image duhrBackgroundImage = new ImageIcon(getClass().getResource(duhrBackground)).getImage();
                			this.mainForm.setBackgroundImage(duhrBackgroundImage);
                			this.mainForm.getMainPanel().setImagePanel(duhrBackgroundImage);
                			this.mainForm.getMainPanel().setActualPrayerTime(0);
                			this.mainForm.setBackgroundImage(duhrBackgroundImage);
                			break;
                		case 1023:
                			Image asrBackgroundImage = new ImageIcon(getClass().getResource(asrBackground)).getImage();
                			this.mainForm.setBackgroundImage(asrBackgroundImage);
                			this.mainForm.getMainPanel().setImagePanel(asrBackgroundImage);
                			this.mainForm.getMainPanel().setActualPrayerTime(5);
                			this.mainForm.setBackgroundImage(asrBackgroundImage);
                			break;
                		case 1024:
                			Image maghribBackgroundImage = new ImageIcon(getClass().getResource(maghribBackground)).getImage();
                			this.mainForm.setBackgroundImage(maghribBackgroundImage);
                			this.mainForm.getMainPanel().setImagePanel(maghribBackgroundImage);
                			this.mainForm.getMainPanel().setActualPrayerTime(4);
                			this.mainForm.setBackgroundImage(maghribBackgroundImage);
                			break;
                		case 1025:
                			Image ishaaBackgroundImage = new ImageIcon(getClass().getResource(ishaaBackground)).getImage();
                			this.mainForm.setBackgroundImage(ishaaBackgroundImage);
                			this.mainForm.getMainPanel().setImagePanel(ishaaBackgroundImage);
                			this.mainForm.getMainPanel().setActualPrayerTime(3);
                			this.mainForm.setBackgroundImage(ishaaBackgroundImage);
                			break;
                		default:
                			Image shorou9BackgroundImage2 = new ImageIcon(getClass().getResource(shorou9Background)).getImage();
                			this.mainForm.setBackgroundImage(shorou9BackgroundImage2);
                			this.mainForm.getMainPanel().setImagePanel(shorou9BackgroundImage2);
                			this.mainForm.getMainPanel().setActualPrayerTime(1);
                			this.mainForm.setBackgroundImage(shorou9BackgroundImage2);
                			break;
                        }
                            this.mainForm.getMainPanel().repaint();
                            
                            this.mainForm.startAthan(this.nextPrayerCode);//start athan
                            this.isAthanStarted = true;
                        }            
                        
                    } else {
                        if (this.totalMinutes == this.nextPrayerTimeInMinutes + 1 && isTimePlus1 == true) {//if salat time passing by 1 minute , getting new next paryer time
                        	
                        	this.isAthanStarted = false;
                        	getNextPrayer(this.totalMinutes);

                            this.actualSalatNameProperties = PropertiesHandler.getSingleton().getValue(this.nextPrayerCode);
                            
                            switch (this.nextPrayerCode) {//set background
                    		case 1021:
                    			Image fajrBackgroundImage = new ImageIcon(getClass().getResource(fajrBackground)).getImage(); 
                    			this.mainForm.getMainPanel().setImagePanel(fajrBackgroundImage);
                    			this.mainForm.getMainPanel().setActualPrayerTime(2);
                    			this.mainForm.setBackgroundImage(fajrBackgroundImage);
                    			break;
                    		case 1022:
                    			Image shorou9BackgroundImage = new ImageIcon(getClass().getResource(shorou9Background)).getImage();
                    			this.mainForm.getMainPanel().setImagePanel(shorou9BackgroundImage);
                    			this.mainForm.getMainPanel().setActualPrayerTime(1);
                    			this.mainForm.setBackgroundImage(shorou9BackgroundImage);
                    			break;
                    		case 1023:
                    			Image duhrBackgroundImage = new ImageIcon(getClass().getResource(duhrBackground)).getImage();
                    			this.mainForm.getMainPanel().setImagePanel(duhrBackgroundImage);
                    			this.mainForm.getMainPanel().setActualPrayerTime(0);
                    			this.mainForm.setBackgroundImage(duhrBackgroundImage);
                    			break;
                    		case 1024:
                    			Image asrBackgroundImage = new ImageIcon(getClass().getResource(asrBackground)).getImage();
                    			this.mainForm.getMainPanel().setImagePanel(asrBackgroundImage);
                    			this.mainForm.getMainPanel().setActualPrayerTime(5);
                    			this.mainForm.setBackgroundImage(asrBackgroundImage);
                    			break;
                    		case 1025:
                    			Image maghribBackgroundImage = new ImageIcon(getClass().getResource(maghribBackground)).getImage();
                    			this.mainForm.getMainPanel().setImagePanel(maghribBackgroundImage);
                    			this.mainForm.getMainPanel().setActualPrayerTime(4);
                    			this.mainForm.setBackgroundImage(maghribBackgroundImage);
                    			break;
                    		case 1020:
                    			Image ishaaBackgroundImage = new ImageIcon(getClass().getResource(ishaaBackground)).getImage();
                    			this.mainForm.getMainPanel().setImagePanel(ishaaBackgroundImage);
                    			this.mainForm.getMainPanel().setActualPrayerTime(3);
                    			this.mainForm.setBackgroundImage(ishaaBackgroundImage);
                    			break;
                    		default:
                    			Image shorou9BackgroundImagee = new ImageIcon(getClass().getResource(shorou9Background)).getImage();
                    			this.mainForm.getMainPanel().setImagePanel(shorou9BackgroundImagee);
                    			this.mainForm.getMainPanel().setActualPrayerTime(1);
                    			this.mainForm.setBackgroundImage(shorou9BackgroundImagee);
                    			break;
                            }
                            this.mainForm.getMainPanel().repaint();
                            isTimePlus1 = false;
                        }
                        else
                        {
                        	isTimePlus1 = true;
                        	
                        	if(this.nextPrayerTimeInMinutes > this.totalMinutes){
                        		this.mainForm.getNextPrayerLabel().setForeground(Color.WHITE);
                                this.mainForm.getNextPrayerLabel().setText(this.missingPropertiesValue + " " + this.formatter.format(((this.nextPrayerTimeInMinutes - 1) - this.totalMinutes) / 60) + ":" + this.formatter.format(((this.nextPrayerTimeInMinutes - 1) - this.totalMinutes) % 60) + ":"
                                        + this.formatter.format(this.second == 0 ? 0 : 60 - this.second) + " " + this.toPropertiesValue + " " + this.actualSalatNameProperties);
	                    	}
                        	else{
                        		getNextPrayer(this.totalMinutes);
                        	}
                        }
                    }
                }
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
        }
    }

    public void getNextPrayer(int totalMinutes2) {//get missing minutes to next time and next prayer time code name and his time in minutes
    	
          if (totalMinutes2 == 0 || totalMinutes2 == 1440 || (totalMinutes2 >= 0 && totalMinutes2 <= this.prayerTimesInMinutes[0])) {//if actual time is between 0 and fajr time , means that the next prayer time is fajr
            this.actualPrayerTime = 3;
        	this.nextPrayerCode = 1020;//fajr time code
            this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[0];
        } else {
            if (totalMinutes2 > this.prayerTimesInMinutes[0] && totalMinutes2 <= this.prayerTimesInMinutes[1]) {//if actual time is between fajr time and shorou9 time , means that the next prayer time is shorou9
            	this.actualPrayerTime = 2;
            	this.nextPrayerCode = 1021;//shorou9 time code
                this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[1];
            } else {
                if (totalMinutes2 > this.prayerTimesInMinutes[1] && totalMinutes2 <= this.prayerTimesInMinutes[2]) {//if actual time is between shorou9 time and duhr time , means that the next prayer time is duhr
                	this.actualPrayerTime = 1;
                	this.nextPrayerCode = 1022;//duhr time code
                    this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[2];
                } else {
                    if (totalMinutes2 > this.prayerTimesInMinutes[2] && totalMinutes2 <= this.prayerTimesInMinutes[3]) {//if actual time is between duhr and asr time , means that the next prayer time is asr
                    	this.actualPrayerTime = 0;
                    	this.nextPrayerCode = 1023;//asr time code
                        this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[3];
                    } else {
                        if (totalMinutes2 > this.prayerTimesInMinutes[3] && totalMinutes2 <= this.prayerTimesInMinutes[4]) {//if actual time is between asr and maghrib time , means that the next prayer time is maghrib
                        	this.actualPrayerTime = 5;
                        	this.nextPrayerCode = 1024;//maghrib time code
                            this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[4];
                        } else {
                            if (totalMinutes2 > this.prayerTimesInMinutes[4] && totalMinutes2 <= this.prayerTimesInMinutes[5]) {//if actual time is between maghrib and ishaa time , means that the next prayer time is ishaa
                            	this.actualPrayerTime = 4;
                            	this.nextPrayerCode = 1025;//ishaa time code
                                this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[5];
                            } else {
                                if (totalMinutes2 > this.prayerTimesInMinutes[5] && totalMinutes2 < 1440) {//if actual time is between ishaa and 24H  , means that the next prayer time is fajr
                                	this.actualPrayerTime = 3;
                                	this.nextPrayerCode = 1020;//fajr time code
                                    this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[0] + 1440;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void Refresh() throws IOException, InterruptedException {//regenerate main form with new prayer times for next day	
        
        Calendar calendar = Calendar.getInstance();
        this.calendar = calendar;
        
    	PrayersTimes prayersTimes = new PrayersTimes(this.calendar);//get object prayersTimes
    	this.prayerTimesInMinutes = prayersTimes.getAllPrayrTimesInMinutes();//get all prayer times in minutes
    	
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minutes = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
           
        getNextPrayer((hour * 60) + minutes);//get next prayer code,minutes,missing minutes
        
        try {
            this.missingPropertiesValue = PropertiesHandler.getSingleton().getValue(1045);//translated "missing" term
            this.toPropertiesValue = PropertiesHandler.getSingleton().getValue(1046);//translated "to" term
            this.actualSalatNameProperties = PropertiesHandler.getSingleton().getValue(this.nextPrayerCode);//translated actual salat name
        } catch (Exception e) {
        }
    	
    	HijriTime hijriTime = new HijriTime(Calendar.getInstance());//get object hijriTime
    	MiladiTime miladiTime = new MiladiTime(Calendar.getInstance());//get object miladiTime
    	this.mainForm.setAllLabelsTimesHijriMiladiValues(prayersTimes, hijriTime, miladiTime);//set all values to all labels
    }
    
    public void refreshNextDay() throws IOException, InterruptedException {//regenerate main form with new prayer times for next day	
    	PrayersTimes prayersTimes = new PrayersTimes(Calendar.getInstance());//get object prayersTimes
    	this.prayerTimesInMinutes = prayersTimes.getAllPrayrTimesInMinutes();//get all prayer times in minutes
    	
        this.actualPrayerTime = 3;
    	this.nextPrayerCode = 1020;//fajr time code
        this.nextPrayerTimeInMinutes = this.prayerTimesInMinutes[0];
       	
    	if(this.nextPrayerTimeInMinutes > this.totalMinutes){
    		this.mainForm.getNextPrayerLabel().setForeground(Color.WHITE);
            this.mainForm.getNextPrayerLabel().setText(this.missingPropertiesValue + " " + this.formatter.format(((this.nextPrayerTimeInMinutes - 1) - this.totalMinutes) / 60) + ":" + this.formatter.format(((this.nextPrayerTimeInMinutes - 1) - this.totalMinutes) % 60) + ":"
                    + this.formatter.format(this.second == 0 ? 0 : 60 - this.second) + " " + this.toPropertiesValue + " " + this.actualSalatNameProperties);
    	}
    	else{
    		getNextPrayer(0);
    	}
    	
    	HijriTime hijriTime = new HijriTime(Calendar.getInstance());//get object hijriTime
    	MiladiTime miladiTime = new MiladiTime(Calendar.getInstance());//get object miladiTime
    	this.mainForm.setAllLabelsTimesHijriMiladiValues(prayersTimes, hijriTime, miladiTime);//set all values to all labels
    }

	public int getActualPrayerTime() {
		return actualPrayerTime;
	}
	public void setActualPrayerTime(int actualPrayerTime) {
		this.actualPrayerTime = actualPrayerTime;
	}

    public boolean isAfterDay(Calendar cal1, Calendar cal2) {
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return false;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return true;
        return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
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


    public String getActualSalatNameProperties() {
		return actualSalatNameProperties;
	}

	public void setActualSalatNameProperties(String actualSalatNameProperties) {
		this.actualSalatNameProperties = actualSalatNameProperties;
	}
}
