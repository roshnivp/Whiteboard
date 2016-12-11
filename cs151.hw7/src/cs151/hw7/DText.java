package cs151.hw7;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class DText extends DShape {

	private double startingSize = 1.0;

	public DText() {
		setModel(new DTextModel());
	}

	public DText(DShapeModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	public Font computeFont(Graphics g){
		DTextModel textModel = (DTextModel) model;
		Font font = new Font(textModel.getFont(), Font.PLAIN, (int)startingSize);
		FontMetrics metrics = g.getFontMetrics(font);
		while(metrics.getHeight()<getModel().getBounds().getHeight()){
			startingSize=(startingSize*1.10)+1;
			font = new Font(textModel.getFont(),Font.PLAIN,(int)startingSize);
			metrics = g.getFontMetrics(font);
		}
		return font;
	}
	
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		DTextModel textModel = (DTextModel) model;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = computeFont(g);
		g2.setFont(font);
		g2.setColor(textModel.getColor());
		g2.drawString(textModel.getText(), textModel.getX(), textModel.getY()+textModel.getHeight());

	}
}
