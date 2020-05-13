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
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class StartProgram implements Iconfig{

    public static void main(String[] args) {
    	try{
    	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    	}catch(Exception ex){}
        startProgrameMethode();
    }

    public static void startProgrameMethode(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    	XmlHandler xmlHandler = XmlHandler.getSingleton();//get unique object xmlhandler
                    	UserConfig userConfig = xmlHandler.getUserConfig();//get unique object userConfig
                    	PropertiesHandler propertiesHandler = PropertiesHandler.getSingleton();//get unique object propertiesHandler
                        propertiesHandler.setParameters(userConfig);//passing userConfig object to propertiesHandler class for getting user selected language for translation

                    	ApplicationControl applicationControl = new ApplicationControl(".sally");
                    	if(applicationControl.isApplicationRunning()){
                    		JOptionPane.showMessageDialog(null,propertiesHandler.getValue(1102),propertiesHandler.getValue(1103),JOptionPane.INFORMATION_MESSAGE);
                    	    System.exit(0);
                    	}
                    	else
                    	{
                        	PrayersTimes prayersTimes = new PrayersTimes(Calendar.getInstance());//get object prayersTimes
                        	HijriTime hijriTime = new HijriTime(Calendar.getInstance());//get object hijriTime
                        	MiladiTime miladiTime = new MiladiTime(Calendar.getInstance());//get object miladiTime

                            MainForm mainForm = new MainForm(prayersTimes, hijriTime, miladiTime);//create main form with all calculated prayer times in selected language
                            mainForm.getMainFram().setVisible(true);
                    	}

                } catch (Exception e) {
                    try {
                        JOptionPane.showMessageDialog(null, startError, "Error", JOptionPane.ERROR_MESSAGE);//error if sallyUserConfig.xml corrupt
                    } catch (HeadlessException e1) {
                    }
                    System.exit(0);
                }
            }
        });
    }

}
