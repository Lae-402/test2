package bean;

public class Test implements java.io.Serializable {

    // フィールド
    private Student student;   // 生徒
    private String classNum;   // クラス番号
    private Subject subject;   // 科目
    private School school;     // 学校
    private int no;            // 番号
    private int point;         // ポイント

    // ゲッター

    // 生徒のゲッター
    public Student getStudent() {
        return student;
    }

    // クラス番号のゲッター
    public String getClassNum() {
        return classNum;
    }

    // 科目のゲッター
    public Subject getSubject() {
        return subject;
    }

    // 学校のゲッター
    public School getSchool() {
        return school;
    }

    // 番号のゲッター
    public int getNo() {
        return no;
    }

    // ポイントのゲッター
    public int getPoint() {
        return point;
    }

    // セッター

    // 生徒のセッター
    public void setStudent(Student student) {
        this.student = student;
    }

    // クラス番号のセッター
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // 科目のセッター
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    // 学校のセッター
    public void setSchool(School school) {
        this.school = school;
    }

    // 番号のセッター
    public void setNo(int no) {
        this.no = no;
    }

    // ポイントのセッター
    public void setPoint(int point) {
        this.point = point;
    }
}