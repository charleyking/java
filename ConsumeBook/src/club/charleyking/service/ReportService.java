package club.charleyking.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import club.charleyking.dao.RecordDAO;
import club.charleyking.entity.Record;
import club.charleyking.util.DateUtil;

public class ReportService {
	public int getDaySpend(Date date, List<Record> monthRawData) {
		int daySpend = 0;
		for (Record record: monthRawData) {
			if (record.date.equals(date)) {
				daySpend += record.spend;
			}
		}
		return daySpend;
	}
	
	public List<Record> listThisMonthRecords() {
		RecordDAO recordDao = new RecordDAO();
		List<Record> monthRawData = recordDao.listThisMonth();
		List<Record> result = new ArrayList<>();
		Date monthBegin = DateUtil.getMonthBegin();
		int monthTotalDay = DateUtil.getThisMonthTotalDay();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < monthTotalDay; i++) {
			Record record = new Record();
			cal.setTime(monthBegin);
			cal.add(Calendar.DATE, i);
			Date eachDayOfThisMonth = cal.getTime();
			int daySpend = getDaySpend(eachDayOfThisMonth, monthRawData);
			record.spend = daySpend;
			result.add(record);
		}
		return result;
	}
}
