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

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import Classes.Iconfig;
import Classes.PropertiesHandler;
import Classes.TransparentPanel;

public class InfoPanel extends TransparentPanel implements Iconfig{

    private static final long serialVersionUID = 1L;
    private final JTextArea myInfo;//text information

    public InfoPanel() {//create InfoPanel object and set parameters
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(400, 340);
        this.myInfo = new JTextArea();
        this.myInfo.setEditable(false);
        this.myInfo.setFont(new Font("TimesRoman", Font.ROMAN_BASELINE, 14));
        this.myInfo.setBackground(new Color(59, 185, 255));
        this.myInfo.setBorder(new LineBorder(Color.WHITE,1));
        this.myInfo.setOpaque(false);
        this.myInfo.setForeground(Color.WHITE);
        this.setOpaque(false);
        
        //set information
        try {
			this.myInfo.setText("\n  " + PropertiesHandler.getSingleton().getValue(1098) + " \n\n");
	        this.myInfo.append("  " + PropertiesHandler.getSingleton().getValue(1099) + " \n\n");
	        this.myInfo.append("  " + PropertiesHandler.getSingleton().getValue(1100) + applicationVersion);
	        this.myInfo.append("  " + PropertiesHandler.getSingleton().getValue(1101) + " \n\n");
	        this.myInfo.append(email);
	        this.myInfo.append(sallyWebSite);
	        this.myInfo.append(sallyfacebookPage);

	        this.myInfo.setBounds(20, 20, 340, 270);
	        this.add(myInfo);
		} catch (Exception e) {}

    }
}
