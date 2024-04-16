package bean;

public class School implements java.io.Serializable {

	private String cd;   // 学校コード
	private String name; // 学校名

	// ゲッター
	public String getCd() {
		return cd;
	}
	public String getName() {
		return name;
	}

	// セッター
	public void setCd(String cd) {
		this.cd = cd;
	}
	public void setName(String name) {
		this.name = name;
	}

}