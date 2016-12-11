package cs151.hw7;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import cs151.hw7.Whiteboard.Message;

public class Canvas extends JPanel{
	ArrayList<DShape> shapes;
	DShape selected;
	Whiteboard whiteboard;
	Dragger drag = new Dragger();
	public Canvas(Whiteboard w) {
		super();
		whiteboard  = w;
		this.setPreferredSize(new Dimension(400,400));
		this.setBackground(Color.WHITE);
		shapes = new ArrayList<>();
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
	}
			
	public void addShape(DShape shape){
		addShape(shape.getModel());
		whiteboard.sendMessage(Message.ADD, shape.getModel());
	}
	public void clear() {
		if(shapes.size() > 0) {
			if(DShape.isSelected) {
				//DataTable.removeRow(shapes.indexOf(selectedShape));
				shapes.remove(selected);
			}
			selected = null;
			DShape.isSelected = false;
			repaint();
		}
	}
	public void deleteShape(){
		shapes.remove((DShape)selected);
		whiteboard.sendMessage(Message.REMOVE, selected.getModel());
		selected = null;
	}
	
	public void deleteShape(DShape shape){
		shapes.remove(shape);
		whiteboard.sendMessage(Message.REMOVE, shape.getModel());
	}
	
	public DShape findShape(DShapeModel shape){
		for(DShape dshape : shapes){
			if (dshape.getModel().equals(shape)){
				whiteboard.sendMessage(Message.CHANGE, shape);
				return dshape;
			}
		}
		
		return null;
	}
	
	public DShape findShapebyID(DShapeModel shape){
		for(DShape dshape : shapes){
			if(dshape.getModel().getID() == shape.getID()){
				return dshape;
			}
		}
		return null;
	}
	
	public ArrayList<DShape> getShapes(){
		return shapes;
	}
	public void addShape(DShapeModel shape){
		if (shape instanceof DRectModel) {
			DRectModel rectModel = (DRectModel) shape;
			DRect rect = new DRect(rectModel);
			shapes.add(rect);
		}
		else if(shape instanceof DOvalModel){
			DOvalModel ovalModel = (DOvalModel) shape;
			DOval oval = new DOval(ovalModel);
			shapes.add(oval);
		}
		else if(shape instanceof DLineModel){
			DLineModel lineModel = (DLineModel) shape;
			DLine line = new DLine(lineModel);
			shapes.add(line);
		}
		else if(shape instanceof DTextModel){
			DTextModel textModel = (DTextModel) shape;
			DText text = new DText(textModel);
			shapes.add(text);
		}
	}
	
	public void paintSelected(Color c){
		selected.getModel().setColor(c);
		whiteboard.sendMessage(Message.CHANGE, selected.getModel());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(DShape shape: shapes){
			if (shape instanceof DRect){
				DRect rect = (DRect)shape;
				rect.draw(g);
			}
			else if(shape instanceof DOval){
				DOval oval = (DOval)shape;
				oval.draw(g);
			}
			else if(shape instanceof DLine){
				DLine line= (DLine) shape;
				line.draw(g);

			}
			else if(shape instanceof DText){
				DText text= (DText) shape;
				text.draw(g);

			}
		}
		if(selected != null){
			Point[] knobs = selected.getKnobs();
			for(int i=0; i < knobs.length; i++){
				g.setColor(Color.BLACK);
				g.fillRect((int)knobs[i].getX()-4,(int)knobs[i].getY()-4,9,9);
			}
		}
	}
	
	public void renewData(DShape shape){
		DShape dshape = findShapebyID(shape.getModel());
		dshape.setModel(shape.getModel());
	}
	
	public void disableFunctions(){
		this.removeMouseListener(drag);
		this.removeMouseMotionListener(drag);
	}
	
	public DShape moveToBack(){
		shapes.remove(selected);
		shapes.add(shapes.size()-1, selected);
		whiteboard.sendMessage(Message.BACK, selected.getModel());
		return selected;
	}
	
	public DShape moveToBack(DShape shape){
		shapes.remove(shape);
		shapes.add(shapes.size()-1,shape);
		whiteboard.sendMessage(Message.BACK, shape.getModel());
		return shape;
	}
	
	public DShape moveToFront(){
		shapes.remove(selected);
		shapes.add(selected);
		whiteboard.sendMessage(Message.FRONT, selected.getModel());
		return selected;
	}
	
	public DShape moveToFront(DShape shape){
		shapes.remove(shape);
		shapes.add(shape);
		whiteboard.sendMessage(Message.FRONT, shape.getModel());
		return shape;
	}
	
	private class Dragger implements MouseListener, MouseMotionListener {
		int distX;
		int distY;
		public void mousePressed(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			selected = null;
			for(DShape shape : shapes){
				if(shape.getModel().getBounds().contains(new Point(x,y))){
					selected = shape;
					distX = e.getX() - shape.getModel().getX(); 
					distY = e.getY() - shape.getModel().getY();
				}
			} 
			repaint();
		}
		public void mouseDragged(MouseEvent e){
			if(selected != null){
			int x = e.getX() - distX;
			int y = e.getY() - distY;
			selected.getModel().setX(x);
			selected.getModel().setY(y);
			repaint();
			whiteboard.sendMessage(Message.CHANGE, selected.getModel());
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
}
