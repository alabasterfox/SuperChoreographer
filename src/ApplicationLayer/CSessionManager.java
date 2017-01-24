/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLayer;

import gui.GMainWindow;

/**
 *
 * @author AlbertoVO5
 */
public class CSessionManager implements ICommon {
    
    private CSessionManager() {
        this.initialize();
    }
    
    public static CSessionManager getInstance() {
        return (instance == null) ? instance = new CSessionManager() : instance; 
    }

    @Override
    public void initialize() {
        // GUI ////
        mw = new GMainWindow();
        
    }
    
    // GUI /////
    private GMainWindow mw;
    // App Layer ////
    private static CSessionManager instance;
}
