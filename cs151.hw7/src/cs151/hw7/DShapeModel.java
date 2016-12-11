package cs151.hw7;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Roshni Velluva Puthanidam on 28/11/16.
 */
public class DShapeModel {
    private int x;
    private int y;
    private int width;
    private int height;
    private int id;
    private Color color;
    private Rectangle shapeRectangle;
    private ArrayList<ModelListener> listeners = new ArrayList<>();

    public DShapeModel() {
        this.x = 10;
        this.y = 10;
        this.width = 20;
        this.height = 20;
        this.color = Color.GRAY;
    }
    
    public DShapeModel(DShapeModel model){
    	this.x = model.getX();
    	this.y = model.getY();
    	this.width = model.getWidth();
    	this.height = model.getHeight();
    	this.color = model.getColor();
    }
    
    public void setID(int id){
    	this.id = id;
    }
    
    public int getID(){
    	return this.id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getShapeRectangle() {
        return shapeRectangle;
    }

    public void setX(int x) {
        this.x = x;
        notifyListeners();
    }

    public void setY(int y) {
        this.y = y;
        notifyListeners();
    }

    public void setWidth(int width) {
        this.width = width;
        notifyListeners();
    }

    public void setHeight(int height) {
        this.height = height;
        notifyListeners();
    }

    public void setColor(Color color) {
        this.color = color;
        notifyListeners();
    }

    public void setShapeRectangle(Rectangle shapeRectangle) {
        this.shapeRectangle = shapeRectangle;
        notifyListeners();
    }
    
    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public ArrayList<ModelListener> getListeners() {
        return listeners;
    }

    public void addListener(ModelListener l){
    	listeners.add(l);
    }
    
    public void removeListener(ModelListener l){
    	listeners.remove(l);
    }
    public void notifyListeners(){
        for(ModelListener listener: listeners){
            listener.modelChanged(DShapeModel.this);
        }
    }
    
    public void mimic(DShapeModel other){
    	this.id = other.id;
    	this.setX((int)other.getBounds().getX());
    	this.setY((int)other.getBounds().getY());
    	this.color = other.color;
    	notifyListeners();
    }
  }


