package bean;

public class Subject implements java.io.Serializable {

	//学生名
	private String name;
	//学校コード
	private School school;
	private String cd;

	//ゲッター
	public String getName() {
		return name;
	}
	public String getCd() {
		return cd;
	}
	public School getSchool() {
		return school;
	}

	//セッター
	public void setName(String name) {
		this.name = name;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public void setSchool(School school) {
		this.school = school;
	}
}

