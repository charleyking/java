package club.charleyking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import club.charleyking.entity.Category;
import club.charleyking.util.DBUtil;

public class CategoryDAO {
	public int getTotal() {
		int total = 0;
		try {
			Connection con = DBUtil.getConnection();
			Statement stm = con.createStatement();
			String sql = "select count(*) from category";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total = :" + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Category category) {
		String sql = "insert into category values(null, ?)";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, category.name);
			pstm.execute();
			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				category.id = id;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Category category) {
		String sql = "update category set name = ? where id = ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, category.name);
			pstm.setInt(2, category.id);
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete (int id) {
		try {
			Connection con = DBUtil.getConnection();
			Statement stm = con.createStatement();
			String sql = "delete from category where id = " + id;
			stm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Category get(int id) {
		Category category = null;
		try {
			Connection con = DBUtil.getConnection();
			Statement stm = con.createStatement();
			String sql = "select * from category where id = " + id;
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				category = new Category();
				String name = rs.getString(2);
				category.name = name;
				category.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public List<Category> list() {
		return list(0, Short.MAX_VALUE);
	}
	
	public List<Category> list(int start, int count) {
		List<Category> categories = new ArrayList<Category>();
		String sql = "select * from category order by id desc limit ?, ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, start);
			pstm.setInt(2, count);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				category.id = id;
				category.name = name;
				categories.add(category);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
}
