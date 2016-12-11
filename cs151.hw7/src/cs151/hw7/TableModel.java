package cs151.hw7;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel implements ModelListener {
	public static final String[] DEFAULT_COLUMNS = {"X", "Y", "Width", "Height"};
	
	private ArrayList<DShapeModel> models;
	
	public TableModel(){
		super();
		models = new ArrayList<>();
	}
	
	@Override
	public void modelChanged(DShapeModel model) {
		int index = models.indexOf(model);
		fireTableRowsUpdated(index,index);
	}

	public void addModel(DShapeModel model){
		models.add(0,model);
		model.addListener(this);
		fireTableDataChanged();
	}
	
	public void moveToBack(DShapeModel model){
		models.remove(model);
		models.add(model);
		fireTableDataChanged();
	}
	
	public void moveToFront(DShapeModel model){
		models.remove(model);
		models.add(0,model);
		fireTableDataChanged();
	}
	
	public void removeModel(DShapeModel model){
		models.remove(model);
		model.removeListener(this);
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return models.size();
	}

	@Override
	public int getColumnCount() {
		return DEFAULT_COLUMNS.length;
	}
	
	public String getColumnName(int col){
		return DEFAULT_COLUMNS[col];
	}

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DShapeModel model = models.get(rowIndex);
		if(columnIndex==0){
			return model.getX();
		}
		else if(columnIndex==1){
			return model.getY();
		}
		else if(columnIndex==2){
			return model.getWidth();
		}
		else if(columnIndex==3){
			return model.getHeight();
		}
		else{
		return null;
		}
	}

}
