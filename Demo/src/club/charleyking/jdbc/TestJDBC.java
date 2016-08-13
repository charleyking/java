package club.charleyking.jdbc;


public class TestJDBC {
	public static void main(String[] args) {
		String insertsql = "insert into charles values('wangjia', 'boy', 23)";
		//String deletesql = "delete from charles where name = 'wangjia'";
		Util.execute(insertsql);
	}
}
