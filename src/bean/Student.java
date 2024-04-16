package bean;

import java.time.LocalDate;
//bean
public class Student implements java.io.Serializable {

	// 学生番号
	private String no;
	// 学生名
	private String name;
	// コース名
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;

	// ゲッター および セッター
	public String getNo() {
		return no;
	}
	public void setNo( String no ) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear( int entYear ) {
		this.entYear = entYear;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum( String classNum ) {
		this.classNum = classNum;
	}
	public boolean isAttend() {
		return isAttend;
	}
	public void setAttend( boolean isAttend ) {
		this.isAttend = isAttend;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool( School school ) {
		this.school = school;
	}
	public int getSchoolYear() {
		int schoolYear = LocalDate.now().minusMonths(3).getYear();
		return schoolYear;
	}

}