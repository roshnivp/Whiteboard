package cs151.hw7;

import java.awt.Graphics;

public class DOval extends DShape{

	public DOval(){
		setModel(new DOvalModel());
	}
	
	public DOval(DOvalModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		g.setColor(model.getColor());
		g.fillOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
	}
}
