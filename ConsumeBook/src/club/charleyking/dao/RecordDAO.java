package club.charleyking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.charleyking.entity.Record;
import club.charleyking.util.DBUtil;
import club.charleyking.util.DateUtil;

public class RecordDAO {
	public int total() {
		int total = 0;
		try {
			Connection con = DBUtil.getConnection();
			Statement stm = con.createStatement();
			String sql = "select count(*) from record";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total: " + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	public void add(Record record) {
		String sql = "insert into record values(null, ?, ?, ?, ?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, record.spend);
			pstm.setInt(2, record.cid);
			pstm.setString(3, record.comment);
			pstm.setDate(4, DateUtil.util2sql(record.date));
			pstm.execute();
			
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				record.id = id;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Record record) {
		String sql = "update record set spend= ?, cid= ?, comment= ?, date= ? where id= ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, record.spend);
			pstm.setInt(2, record.cid);
			pstm.setString(3, record.comment);
			pstm.setDate(4, DateUtil.util2sql(record.date));
			pstm.setInt(5, record.id);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			Connection con = DBUtil.getConnection();
			Statement stm = con.createStatement();
			String sql = "delete from record where id = " +id;
			stm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Record get(int id) {
		Record record = null;
		try {
			Connection con  = DBUtil.getConnection();
			Statement stm = con.createStatement();
			String sql = "select * from record where id = " + id;
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				record = new Record();
				int spend = rs.getInt("spend");
				int cid = rs.getInt("cid");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");
				
				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
	
	public List<Record> list() {
		return list(0, Short.MAX_VALUE);
	}
	
	public List<Record> list(int start, int count) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record order by id desc limit ?, ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, start);
			pstm.setInt(2, count);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int spend = rs.getInt("spend");
				int cid = rs.getInt("cid");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");
				
				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> list(int cid) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where cid = ";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, cid);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int spend = rs.getInt("spend");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");
				
				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> listToday() {
		return list(DateUtil.getToday());
	}
	
	public List<Record> list(Date day) {
		List<Record> records = new ArrayList<Record>();
		String  sql = "select * from record where date = ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, DateUtil.util2sql(day));
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				int spend = rs.getInt("spend");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");
				
				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> listThisMonth() {
		return list(DateUtil.getMonthBegin(),DateUtil.getMonthEnd());
	}
	
	public List<Record> list(Date start, Date end) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where date >=? and date <= ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDate(1, DateUtil.util2sql(start));
			pstm.setDate(2, DateUtil.util2sql(end));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				int spend = rs.getInt("spend");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");
				
				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
}
