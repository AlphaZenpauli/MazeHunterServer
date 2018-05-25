/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.game.player.abilities.abilityConfigs.DashConfig;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.dash.DashRequest;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class DashListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        System.out.println(object);
        if(object instanceof DashRequest) {
            Vector2 tempVelocity = Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].velocity;
            Main.MAIN_SINGLETON.game.player[getIndex(connection.getID())].position.add(tempVelocity.setLength(DashConfig.DASH_RANGE));
                SendDash(connection.getID());
        }
    }
    
    public void SendDash(int id) {
        Main.MAIN_SINGLETON.game.outputer.sendDashResponse(Main.MAIN_SINGLETON.game.player[getIndex(id)].position, Main.MAIN_SINGLETON.game.player[getIndex(id)].velocity, id);
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