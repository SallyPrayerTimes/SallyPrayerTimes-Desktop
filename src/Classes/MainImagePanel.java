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
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MainImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image imagePanel;
    private int actualPrayerTime;

    public MainImagePanel(Image image) {
        this.imagePanel = image;
    }
    
    public void setActualPrayerTime(int actualPrayerTime) {
        this.actualPrayerTime = actualPrayerTime;
    }
    
    public void setImagePanel(Image image) {
        this.imagePanel = image;
    }

    @Override
    public void paintComponent(Graphics g) {//override paintComponent method to get a panel with drawing image
        g.drawImage(imagePanel , 0, 0, this.getWidth(), this.getHeight(), null);
            
            int j = 30;
            int k = 100;
            for(int i = 0 ; i < 6 ; i++){
            	if(i==3){
            		k = 180;
            		j = 30;
            		}
            	
            	if(i == actualPrayerTime){
            		g.setColor(new Color(113, 198, 113, 150));
            	}
            	else{
            		g.setColor(new Color(152, 175, 199, 150));
            	}
            	
            	g.fillRect(j, k, 150, 60);
            	j+=170;
          
            }
       
    }

}
