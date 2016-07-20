package club.charleyking.serializable;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private transient String password;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setPass(String password) {
		this.password = password;
	}
	public String getPass() {
		return this.password;
	}
}
