package cs151.hw7;

/**
 * Created by Roshni Velluva Puthanidam on 28/11/16.
 */
import java.awt.*;

public class DRect extends DShape{

	public DRect(){
		setModel(new DRectModel());
	}
	
	public DRect(DRectModel model){
		super(model);
	}
    
    public void draw(Graphics g) {
        g.setColor(model.getColor());
        g.fillRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());
    }

}