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

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class ApplicationControl {
	
	private String applicationName;
	private File file;
	private FileChannel channel;
    private FileLock lock;
    
    public ApplicationControl(String applicationName){
    	this.applicationName = applicationName;
    }
    
    public boolean isApplicationRunning(){
    	try 
        {
           file = new File(System.getProperty("user.home"), applicationName);
           channel = new RandomAccessFile(file, "rw").getChannel();
   
           try 
           {
              lock = channel.tryLock();
           }
           catch (OverlappingFileLockException e) 
           {
              // already locked
              closeLock();
              return true;
           }
   
           if (lock == null) 
           {
              closeLock();
              return true;
           }
   
           Runtime.getRuntime().addShutdownHook
           (
              new Thread() 
              {
                 // destroy the lock when the JVM is closing
                 public void run() 
                 {
                    closeLock();
                    deleteFile();
                 }
              }
           );
   
           return false;
        }
        catch (Exception e) 
        {
           closeLock();
           return true;
        }
    }
    
    private void closeLock()
    {
       try 
       { 
          lock.release();
       }
       catch (Exception e) {}
  
       try 
       {
          channel.close(); 
       }
       catch (Exception e) {}
    }
  
    private void deleteFile()
    {
       try 
       { 
          file.delete(); 
       }
       catch (Exception e) {}
    }

}
