package bean;

public class Subject implements java.io.Serializable {

	//学生名
	private String name;
	//学校コード
	private String school;
	private String cd;

	//ゲッター
	public String getName() {
		return name;
	}
	public String getCd() {
		return cd;
	}
	public String getSchool() {
		return school;
	}

	//セッター
	public void setName(String name) {
		this.name = name;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public void setSchool(String school) {
		this.school = school;
	}
}

