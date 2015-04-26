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
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler implements Iconfig{

    private final Properties properties;//Properties object
    private InputStream inputStream;//InputStream represent language file
    private static PropertiesHandler propertiesHandler;//object handle translation

    public static PropertiesHandler getSingleton() throws IOException {//get single object of PropertiesHandler
        if (propertiesHandler == null) {
            propertiesHandler = new PropertiesHandler();
        }
        return propertiesHandler;
    }

    public PropertiesHandler() throws IOException {
        this.properties = new Properties();//create Properties object
    }

    public String getLanguagePath(String language) {//get user selected language path
        String languagePath = ENG_properties;
        if(language.equalsIgnoreCase(it)){
        	languagePath = IT_properties;
        }else{
        	if(language.equalsIgnoreCase(ar)){
            	languagePath = AR_properties;
            }else{
            	if(language.equalsIgnoreCase(fr)){
                	languagePath = FR_properties;
                }else{
                	if(language.equalsIgnoreCase(eng)){
                    	languagePath = ENG_properties;
                    }
                }
                
            }
            
        }
        
        return languagePath;
    }

    public String getValue(int i) {//get translated value from code of term
        String key = String.valueOf(i);
        return properties.getProperty(key).trim();
    }

    public void setParameters(UserConfig userConfig) throws IOException {//pass userConfig object to get user selected language
        this.inputStream = getClass().getResourceAsStream(getLanguagePath(userConfig.getLanguage()));
        properties.load(inputStream);
    }
}
