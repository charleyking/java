package club.charleyking.service;

import java.util.Collections;
import java.util.List;

import club.charleyking.dao.CategoryDAO;
import club.charleyking.dao.RecordDAO;
import club.charleyking.entity.Category;
import club.charleyking.entity.Record;

public class CategoryService {
	CategoryDAO categoryDao = new CategoryDAO();
	RecordDAO recordDao = new RecordDAO();
	public List<Category> list() {
		List<Category> cs = categoryDao.list();
		for (Category c : cs) {
			List<Record> rs = recordDao.list(c.id);
			c.recordNumber = rs.size();
		}
		Collections.sort(cs, (c1,c2) -> c2.recordNumber-c1.recordNumber);
		return cs;
	}
	
	public void add(String name) {
		Category c = new Category();
		c.setName(name);
		categoryDao.add(c);
	}
	
	public void update(int id, String name) {
		Category c = new Category();
		c.setName(name);
		c.setId(id);
		categoryDao.update(c);
	}
	
	public void delete(int id) {
		categoryDao.delete(id);
	}
}
