package ApplicationLayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class CRecordButton implements ICommon {
    
    public CRecordButton(){
        this.initialize();
    }
    
    public void DrawRecordButton(Graphics g, int h, int w){
        if(c == null){
            c = new Point(w - 40, h + 110);
        }
        g.setColor(Color.red);
        g.fillOval(c.x-RING1/2, c.y-RING1/2, RING1, RING1);
        if(ANIMATION_STATE != 0){
            switch(ANIMATION_STATE){
                case 1:
                    g.setColor(Color.WHITE);
    //                g.drawOval(w - 40, h - 30, 15, 15);
                    g.drawOval(c.x-RING2/2, c.y-RING2/2, RING2, RING2);
                    break;

                case 2:
                    g.setColor(Color.WHITE);
    //                g.drawOval(w - 40 -1, h - 30 -1, 15 + 5, 15 + 5);
                    g.drawOval(c.x-RING2/2, c.y-RING2/2, RING2, RING2);
                    g.drawOval(c.x-RING3/2, c.y-RING3/2, RING3, RING3);
                    break;

                case 3:
                    g.setColor(Color.WHITE);
    //                g.drawOval(w - 40 -1, h - 30 - 1, 15 + 10, 15 + 10);
                    g.drawOval(c.x-RING2/2, c.y-RING2/2, RING2, RING2);
                    g.drawOval(c.x-RING3/2, c.y-RING3/2, RING3, RING3);
                    g.drawOval(c.x-RING4/2, c.y-RING4/2, RING4, RING4);
                    break;
                    
                case 4:
                    g.setColor(Color.WHITE);
                    g.drawOval(c.x-RING3/2, c.y-RING3/2, RING3, RING3);
                    g.drawOval(c.x-RING4/2, c.y-RING4/2, RING4, RING4);
                    break;
                    
                case 5:
                    g.setColor(Color.WHITE);
                    g.drawOval(c.x-RING4/2, c.y-RING4/2, RING4, RING4);
                    break;
            }
        }

    }
    
    public void PlayBroadcastAnimation(){
        if(ANIMATION_STATE > 5){
            ANIMATION_STATE = 0;
        }
        ANIMATION_STATE++;
    }
    
    public void StopBroadcastAnimation(){
        ANIMATION_STATE = 0; // return Record Button to regular state
    }
    
    @Override
    public void initialize() {
        ANIMATION_STATE = 0;
    }
    
    private int ANIMATION_STATE;
    private int RING1 = 20;
    private int RING2 = 30;
    private int RING3 = 40;
    private int RING4 = 50;
    private Point c;
}
