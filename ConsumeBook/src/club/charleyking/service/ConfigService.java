package club.charleyking.service;

import club.charleyking.dao.ConfigDAO;
import club.charleyking.entity.Config;

public class ConfigService {
	public static final String budget = "budget";
	public static final String mysqlPath = "mysqlPath";
	public static final String defaultBudget = "500";
	
	static ConfigDAO dao = new ConfigDAO();
	static {
		init();
	}
	
	public static void init() {
		init(budget, defaultBudget);
		init(mysqlPath,"");
	}
	public static void init(String key, String value) {
		Config config = dao.getByKey(key);
		if (config == null) {
			Config con = new Config();
			con.setKey(key);
			con.setValue(value);
			dao.add(con);
		}
	}
	public String get(String key) {
		Config config = dao.getByKey(key);
		return config.getValue();
	}
	public void update(String key, String value) {
		Config config = dao.getByKey(key);
		config.setValue(value);
		dao.update(config);
	}
	public int getIntBudget() {
		return Integer.parseInt(get(budget));
	}
}
