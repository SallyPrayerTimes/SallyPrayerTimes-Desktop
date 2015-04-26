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

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class AthanPlayer implements Runnable , Iconfig{

    private static AdvancedPlayer player;//object player to play athan
    private static AudioDevice device;//AudioDevice object 
    private static int position = 0;//position of athan
    public static boolean STARTED = false;//if athan started or not
    private Thread athanPlayerThread = null;//thread to play athan
    private String athanName;//athan name

    private InputStream inputStream;//InputStream represent mp3 athan
    private FactoryRegistry factoryRegistry;//FactoryRegistry object


    public AthanPlayer(String athanName) {
        this.athanName = athanName;
    }
    
    //playing athan method
    public void play() {
        try {
        	
            String athan = ali_ben_ahmed_mala_mp3;//athan path
            
            if(athanName.equalsIgnoreCase(ali_ben_ahmed_mala)){
            	athan = ali_ben_ahmed_mala_mp3;
            }else{
            	if(athanName.equalsIgnoreCase(abd_el_basset_abd_essamad)){
                	athan = abd_el_basset_abd_essamad_mp3;
                }else{
                	if(athanName.equalsIgnoreCase(farou9_abd_errehmane_hadraoui)){
                    	athan = farou9_abd_errehmane_hadraoui_mp3;
                    }else{
                    	if(athanName.equalsIgnoreCase(mohammad_ali_el_banna)){
                        	athan = mohammad_ali_el_banna_mp3;
                        }else{
                        	if(athanName.equalsIgnoreCase(mohammad_khalil_raml)){
                            	athan = mohammad_khalil_raml_mp3;
                            }
                        }
                        
                    }
                    
                }
                
            }

            this.inputStream = getClass().getResourceAsStream(athan);
            this.factoryRegistry = FactoryRegistry.systemRegistry();
            AthanPlayer.device = factoryRegistry.createAudioDevice();
            AthanPlayer.player = new AdvancedPlayer(inputStream, AthanPlayer.device);
            AthanPlayer.player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackStarted(PlaybackEvent playbackEvent) {
                    //System.out.println("Playback started");
                }

                @Override
                public void playbackFinished(PlaybackEvent playbackEvent) {
                    //System.out.println("Playback finished");
                }
            });

            this.athanPlayerThread = new Thread(this);
            this.athanPlayerThread.start();
        } catch (JavaLayerException e) {
        }
    }

    public static void kill() {//killing athan playing
        try {
            AthanPlayer.device.close();
            AthanPlayer.player.close();
            AthanPlayer.position = 0;
            AthanPlayer.STARTED = false;
        } catch (Exception e) {
        }
    }

    public void pause()//pause athan
    {
        try {
            AthanPlayer.position = device.getPosition() / 26;// a frame is roughly 26 ms;  this is not very precise.
            AthanPlayer.player.stop();
            AthanPlayer.STARTED = true;
        } catch (Exception e) {
        }
    }

    public void stop()//stop athan
    {
        try {
            AthanPlayer.device.close();
            AthanPlayer.player.close();
            AthanPlayer.position = 0;
            AthanPlayer.STARTED = false;
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {//run playing thread
        try {
            AthanPlayer.STARTED = true;
            AthanPlayer.player.play(AthanPlayer.position, Integer.MAX_VALUE);
        } catch (JavaLayerException e) {
        }
    }

    public String getAthanName() {
        return athanName;
    }

    public void setAthanName(String athanName) {
        this.athanName = athanName;
    }

}
