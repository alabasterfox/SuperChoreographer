package ApplicationLayer;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Athlete implements ICommon {

    public Athlete(){
    }

    // Rendering
    private Color color;
    private int x;
    private int y;
    
    // Routine recordings
    private HashMap<Integer, Point> steps;
    private int maxStepsRecorded = 0;
    
    // State variables
    private boolean isSelected;
    private boolean hasRecording;
    private boolean isAtEndOfRecording;
    private boolean isMousedOver;
   
    // Athlete Information
    private String id;
    private String fname;
    private String lname;
    
    @Override
    public void initialize() {
        steps       = new HashMap<Integer, Point>();
        isSelected  = false;
    }
    
    public void SetStep(int keyframe, int x, int y){
        steps.put(keyframe, new Point(x, y));
        maxStepsRecorded = steps.size();
        if(hasRecording == false){
            hasRecording = true;
        }
    }
    
    public boolean HasRecording(){
        return hasRecording;
    }
    
    public Point GetRecordedStep(int keyframe){
        if(keyframe >= maxStepsRecorded -1){
            this.isAtEndOfRecording = true;
        }
        return steps.get(keyframe);
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the isSelected
     */
    public boolean IsSelected() {
        return isSelected;
    }

    /**
     * @param isSelected the isSelected to set
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @return the isAtEndOfRecording
     */
    public boolean IsAtEndOfRecording() {
        return isAtEndOfRecording;
    }

    /**
     * @param isAtEndOfRecording the isAtEndOfRecording to set
     */
    public void setIsAtEndOfRecording(boolean isAtEndOfRecording) {
        this.isAtEndOfRecording = isAtEndOfRecording;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String afname, String alname) {
        this.fname = afname;
        this.lname = alname;
    }

    public String getName(){
        if(lname == null){
            return String.format("%s", fname);
        }else{
            return String.format("%s %s", fname, lname);
        }
        
    }
    
    public String getFname() {
        return fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the isMousedOver
     */
    public boolean IsMousedOver() {
        return isMousedOver;
    }

    /**
     * @param isMousedOver the isMousedOver to set
     */
    public void setIsMousedOver(boolean isMousedOver) {
        this.isMousedOver = isMousedOver;
    }
}
