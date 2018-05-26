/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.StandardHealRequest;

/**
 *
 * @author Karl Huber
 */
public class StandardHealListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        System.out.println(object);
        if(object instanceof StandardHealRequest) {
            SendStandardHealResponse(connection.getID());
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].heal(Config.STANDARDHEAL_TOTALHEAL);
        }
    }
    
    public void SendStandardHealResponse(int id) {
        
    }
    
    public int getIndex (int id){
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.player[i];
            if (p!=null && p.connectionID == id) {
                index = i;
            }
        }
        return index;
    }
}
