package cs151.hw7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javafx.scene.shape.Line;

public class DLineModel extends DShapeModel {
	Point p1 = new Point(getX(),getY());
	Point p2 = new Point(getX()+getWidth(),getY()+getHeight());
	public DLineModel() {
		super();
	}
	
	public void setX(int x){
		super.setX(x);
		p1 = new Point(getX(),getY());
		p2 = new Point(getX()+getWidth(),getY()+getHeight());
	}
	
	public void setY(int y){
		super.setY(y);
		p1 = new Point(getX(),getY());
		p2 = new Point(getX()+getWidth(),getY()+getHeight());
	
	}
	
	public Point getP1(){
		return p1;
	}

	public Point getP2(){
		return p2;
	}
}
