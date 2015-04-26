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
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class TransparentPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    public void paintComponent(Graphics g) {//override paintComponent method to get a transparent panel with fill transparent rectangle 
        if (g instanceof Graphics2D) {
            final int R = 240;//red
            final int G = 240;//green
            final int B = 240;//blue
            final int transparency = 0;//transparency
            g.setColor(new Color(R, G, B, transparency));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}
