package club.charleyking.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import club.charleyking.entity.Category;
import club.charleyking.service.CategoryService;

public class CategoryTableModel implements TableModel {
	String[] columnNames = new String[] {"category name", "consum times"};
	public List<Category> cs = new CategoryService().list();
	
	public CategoryTableModel() {
	}
	
	@Override 
	public int getRowCount() {
		return cs.size();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override 
	public String getColumnName(int index) {
		return columnNames[index];
	}
	
	@Override 
	public Class<?> getColumnClass(int index ) {
		return String.class;
	}
	
	@Override 
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override 
	public Object getValueAt(int rowIndex, int columnIndex) {
		Category c = cs.get(rowIndex);
		if (0==columnIndex) {
			return c.name;
		}
		if (1==columnIndex) {
			return c.recordNumber;
		}
		return null;
	}
	
	@Override 
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}
	
	@Override 
	public void addTableModelListener(TableModelListener l) {
		
	}
	
	@Override 
	public void removeTableModelListener(TableModelListener l) {
		
	}
}
