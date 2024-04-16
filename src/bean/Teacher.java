package bean;

public class Teacher implements java.io.Serializable {

	private String id;       // 教員ID
	private String password; // パスワード
	private String name;     // 氏名
	private School school;   // 学校コード
	private boolean isAuthenticated;

	// ゲッター
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public School getSchool() {
		return school;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	// セッター
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

}