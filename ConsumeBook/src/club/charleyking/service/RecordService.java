package club.charleyking.service;

import java.util.Date;

import club.charleyking.dao.RecordDAO;
import club.charleyking.entity.Category;
import club.charleyking.entity.Record;

public class RecordService {
	RecordDAO recordDao = new RecordDAO();
	public void add(int spend, Category c, String comment, Date date) {
		Record r = new Record();
		r.spend = spend;
		r.cid = c.id;
		r.comment = comment;
		r.date = date;
		recordDao.add(r);
	}
}
