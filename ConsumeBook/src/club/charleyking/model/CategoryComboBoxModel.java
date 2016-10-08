package club.charleyking.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import club.charleyking.entity.Category;
import club.charleyking.service.CategoryService;

public class CategoryComboBoxModel implements ComboBoxModel<String> {
	public List<Category> cs = new CategoryService().list();
	public Category c;
	
	public CategoryComboBoxModel() {
		if (!cs.isEmpty()) {
			c = cs.get(0);
		}
	}
	
	@Override
	public int getSize() {
		return cs.size();
	}

	
	@Override
	public void addListDataListener(ListDataListener l) {
		
	}
	
	@Override 
	public void removeListDataListener(ListDataListener l) {
		
	}
	
	@Override 
	public void setSelectedItem(Object item) {
		c = (Category) item;
	}
	
	@Override 
	public Object getSelectedItem() {
		if (!cs.isEmpty()) {
			return c;
		} else {
			return null;
		}
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return cs.get(index).name;
	}
}
