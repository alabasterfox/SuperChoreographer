/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ApplicationLayer.Athlete;
import ApplicationLayer.CRecordButton;
import ApplicationLayer.ICommon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author AlbertoVO5
 */
public class TheMat extends javax.swing.JPanel implements ICommon, MouseListener, MouseMotionListener, ActionListener, KeyListener {

    /**
     * Creates new form TheMat
     */
    public TheMat() {
        initComponents();
        this.initialize();
    }

    public void SetMessager(JLabel em){
        out = em;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public void ConfigureMatArea(){
        mat_height              = 60;
        mat_width               = mat_height * 8;
        matdimensions           = new Dimension(mat_width, mat_height);
        this.setSize(matdimensions);
        this.setBackground(Color.BLUE);
    }
    
    private void PlaceAthleteOnMat(MouseEvent e){
        Athlete a = new Athlete();
            a.setId(UUID.randomUUID().toString());
switch(athletes.size()){
    case 0:
        a.setName("Simone", "Biles");
        break;
        
    case 1:
        a.setName("Gabby", "Douglas");
        break;
        
    case 2:
        a.setName("Dominique", "Dawes");
        break;
        
    case 3:
        a.setName("Aliya", "Mustafina");
        break;
        
    case 4:
        a.setName("Sophina", "DeJesus");
        break;
        
    case 5:
        a.setName("John", "Orozco");
        break;
        
    default:
        a.setFname(String.format("Athlete %d", athletes.size()-1));
        break;
}
//            a.setName(msg);
            a.setColor(Color.RED);
            a.setX(e.getX()-4);
            a.setY(e.getY()-6);
            a.initialize();
        athletes.put(a.getId(), a);
        msg = String.format("Placed %s on the mat", a.getFname());
        out.setText(msg);
System.out.println(msg);        
        this.repaint();
    }

    private void DrawPlayButton(Graphics g){
        g.setColor(Color.red);
//        g.drawOval(w - 10, h - 10, 5, 5);
    }
    
    private void RecordSteps(){
        
    }
    
    private boolean CheckForCollision(MouseEvent e){
            boolean collision = false;
            int x = e.getX();
            int y = e.getY();
            Iterator<Athlete> as = athletes.values().iterator();
            while(as.hasNext()){
                Athlete a = as.next();
                int x1 = a.getX() - b;
                int y1 = a.getY() - b;
                int x2 = x1 + d + b;
                int y2 = y1 + d + b;
                if(x1 <= x && x <= x2){
                    if(y1 <= y && y <= y2){
                        collision = true;
                        if(a.IsSelected()){
                            // deselect athlete and remove highlight
                            a.setIsSelected(false);
                            msg = String.format("Deselected %s", a.getFname());
                            out.setText(msg);
                            System.out.println(msg);
                        }else{
                            // Highlight the athlete as selected
                            a.setIsSelected(true);
                            msg = String.format("Selected %s", a.getFname());
                            out.setText(msg);
                            System.out.println(msg);
                        }
                    }else{
                        a.setIsSelected(false);
                    }
                }else{
                    a.setIsSelected(false);
                }
            }
            return collision;
    }
    
    private void CheckForMouseOver(MouseEvent e){
            boolean collision = false;
            int x = e.getX();
            int y = e.getY();
            Iterator<Athlete> as = athletes.values().iterator();
            while(as.hasNext()){
                Athlete a = as.next();
                int x1 = a.getX() - b - 15;
                int y1 = a.getY() - b - 20;
                int x2 = x1 + d + b + 20;
                int y2 = y1 + d + b + 20;
                if(x1 <= x && x <= x2){
                    if(y1 <= y && y <= y2){
                        collision = true;
                        a.setIsMousedOver(true); // if user hovers his/her mouse over an Athlete, highlight that athlete
                    }else{
                        a.setIsMousedOver(false);
                    }
                }else{
                    a.setIsMousedOver(false);
                }
            }
    }
    
    private void RewindAthletes(HashMap<String, Athlete> athletes){
        Iterator<Athlete> as = athletes.values().iterator();
        while(as.hasNext()){
            Athlete a = as.next();
            if(a.HasRecording()){
                if(a.IsAtEndOfRecording()){
                    a.setIsAtEndOfRecording(false);
                }
            }
        }
    }
 
    @Override
    public void initialize() {
        this.mp = new Point();
        this.athletes = new HashMap<String, Athlete>();
        this.ConfigureMatArea();
        ANIMATION_STATE = STOPPED;
        screen_area_dimensions = this.getSize();
        h = screen_area_dimensions.height;
        w = screen_area_dimensions.width;
        
        
        
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(ANIMATION_STATE){
            case PLAYBACK:
                
                break;
                
            default:
                if(this.CheckForCollision(e)){
                    this.repaint();
                }else{
                    this.PlaceAthleteOnMat(e);    
                }                
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Iterator<Athlete> as = athletes.values().iterator();
            while(as.hasNext()){
                Athlete a = as.next();
                if(a.IsSelected()){
                    a.setX(x-4);
                    a.setY(y-6);
                }
            }
            this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//System.out.println(String.format(" %d, %d", e.getX(), e.getY()));
        switch(ANIMATION_STATE){
            case RECORDING:

                break;
                
            case STOPPED:
            case PAUSED:
                this.CheckForMouseOver(e);
            break;
        }
        mp.setLocation(e.getX(), e.getY());
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        // set background color
        this.setBackground(mat_color);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(smstr);
       
        
        // Render Mats
        g2.setColor(mat_divider_color);
        g2.fillRect(20, 0, 4, this.getHeight());
        g2.fillRect(this.getWidth()/3, 0, 4, this.getHeight());
        g2.fillRect(this.getWidth()/2, 0, 4, this.getHeight());
        g2.fillRect(2 * this.getWidth()/3, 0, 4, this.getHeight());
        g2.fillRect(this.getWidth()-20, 0, 4, this.getHeight());
        
        // Render Athletes
        Iterator<Athlete> as = athletes.values().iterator();
        while(as.hasNext()){
            Athlete a = as.next();
            // center
            g2.setColor(athlete_info_text_color);
            g2.fillRect(a.getX()-4, a.getY()-6, d, d);
            // border
            g2.setStroke(bgstr); // give the borders a bigger thickness
            g2.setColor(athlete_color);
            g2.drawRect(a.getX()-4, a.getY()-6, d, d);
            g2.setStroke(smstr); // reset stroke for reset of render
            if(a.IsSelected()){
                g2.setColor(athlete_select_color);
                g2.drawRect(a.getX()-9, a.getY()-12, d+10, d+11);
                g2.drawRect(a.getX()-9-3, a.getY()-12-3, d+10+6, d+11+6);
            }
            // athlete info text
            if(a.IsMousedOver()){
                g2.setColor(mat_divider_color);
            }else{
                g2.setColor(athlete_info_text_color);
            }
            g2.drawString(a.getName(), a.getX()-4-30, a.getY()-6-8);
        }
        
        // Render Motion Controls
        switch(ANIMATION_STATE){
            case RECORDING:
                g2.setColor(Color.BLACK);
                g2.drawString("RECORDING", w - 80, h + 80);
                rb.DrawRecordButton(g, h, w);
                break;
        }
        
        // Track mouse
//        g.setColor(Color.red);
//        g.fillRect(mp.x-4, mp.y-6, 8, 8);

    }
    
    private int mat_height;
    private int mat_width;
    private Dimension screen_area_dimensions; // mat bounds
    private int h;
    private int w;
    private Dimension matdimensions;

    private int b               = 5; // click buffer
    public Point mp; // mouse position
    private int d               = 8; // dot size
    private HashMap<String, Athlete> athletes;
    private Timer tm            = new Timer(125, this);
    private Timer rbtm          = new Timer(250, this);
    private JLabel out;
    private String msg;
    
    // GUI Elements
    CRecordButton rb            = new CRecordButton();
    
    // Render
    BasicStroke bgstr           = new BasicStroke(2);
    BasicStroke smstr           = new BasicStroke(2);
    
    // Render Color scheme
    Color mat_color             = new Color(74, 123, 157); // Blue Yonder
    Color mat_divider_color     = new Color(250, 255, 216); // light Goldenrood Yellow
    Color athlete_color         = new Color(154, 168, 153); // Spanish Gray
    Color athlete_select_color  = new Color(236, 255, 176); // Pale Goldenrod
    Color athlete_info_text_color = new Color(84, 87, 124); // Purple Navy
    
    // Animations
    private int ANIMATION_STATE;
    private final int PAUSED    = 1;
    private final int STOPPED   = 2;
    private final int RECORDING = 3;
    private final int PLAYBACK  = 4;
    private int keyframe        = 0;
    private int maxStepsRecorded   = 0;
    private int stepsPlayedback = 0;

    @Override
    public void actionPerformed(ActionEvent e) {

        Iterator<Athlete> as;
        switch(ANIMATION_STATE){
            case RECORDING:
                if(e.getSource().equals(rbtm)){
                   rb.PlayBroadcastAnimation();
                   this.repaint();
                }else{
                    int x = mp.x;
                    int y = mp.y;
    //                msg = String.format("Recording keyframe %d: %d, %d", keyframe, x, y);
                    msg = String.format("Recording keyframe %d ", keyframe, x, y);
                    out.setText(msg);
    //System.out.println(msg);
                    as = athletes.values().iterator();
                    while(as.hasNext()){
                        Athlete a = as.next();
                        if(a.IsSelected()){
                            a.SetStep(keyframe, x, y);
                        }
                    } 
                    keyframe++;
                    if(keyframe > maxStepsRecorded){
                        maxStepsRecorded = keyframe;
                    }
                }
                break;
                
            case PLAYBACK:
                msg = String.format("Playing keyframe %d of %d", stepsPlayedback, maxStepsRecorded);
                out.setText(msg);
//System.out.println(msg);
                as = athletes.values().iterator();
                while(as.hasNext()){
                    Athlete a = as.next();
                    if(a.HasRecording()){
                        if(!a.IsAtEndOfRecording()){
                            a.setX(a.GetRecordedStep(stepsPlayedback).x);
                            a.setY(a.GetRecordedStep(stepsPlayedback).y);
                        }
                    }
                }
                stepsPlayedback++;
                if(stepsPlayedback >= maxStepsRecorded){
                    tm.stop(); // stop playback
                    ANIMATION_STATE = STOPPED;
                    stepsPlayedback = 0; // reset playback to 0 so we can play again if we want
                    msg = "Reached the end of playback";
                    out.setText(msg);
//System.out.println(msg);
                    this.RewindAthletes(athletes);
                }
                this.repaint();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
//System.out.println(String.format("Key: %d", e.getKeyCode()));
        if(e.getKeyCode() == 32){ // [Spacebar]
            switch(ANIMATION_STATE){
                case STOPPED:
                case PAUSED:
                    ANIMATION_STATE = RECORDING;
                    msg = "Recording...";
                    out.setText(msg);
//System.out.println(msg);
                    keyframe = 0; // start recording from step 1
                    tm.start();
                    rbtm.start();
                break;
                
                case RECORDING:
                    ANIMATION_STATE = STOPPED;
                    tm.stop();
                    rbtm.stop();
                    rb.StopBroadcastAnimation();
                    this.repaint();
                    msg = "Stopped recording";
                    out.setText(msg);
//System.out.println(msg);
                    break;
            }
        }
        if(e.getKeyCode() == 80){ // [p]
            switch(ANIMATION_STATE){
                case STOPPED:
                case PAUSED:
                    ANIMATION_STATE = PLAYBACK;
                    msg = "Playing...";
                    out.setText(msg);
//System.out.println(msg);
                    stepsPlayedback = 0; // playback from beginning means keyframe is 0
                    tm.start();
                break;
                
                case PLAYBACK:
                    ANIMATION_STATE = STOPPED;
                    tm.stop();
                    msg = "Stopped playback";
                    out.setText(msg);
//System.out.println(msg);
                    this.RewindAthletes(athletes);
                    break;  
            }
        }
    }
}
