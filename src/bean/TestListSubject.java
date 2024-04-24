package bean;

public class TestListSubject implements java.io.Serializable {

    // フィールド
    private String no;           // 科目番号
    private String name;         // 科目名
    private int entYear;         // 入学年度
    private String classNum;     // クラス番号
    private boolean isAtttend;   // 出席フラグ
    private School school;       // 学校

    // ゲッター

    // 科目番号のゲッター
    public String getNo() {
        return no;
    }

    // 科目名のゲッター
    public String getName() {
        return name;
    }

    // 入学年度のゲッター
    public int getEntYear() {
        return entYear;
    }

    // クラス番号のゲッター
    public String getClassNum() {
        return classNum;
    }

    // 出席フラグのゲッター
    public boolean isAtttend() {
        return isAtttend;
    }

    // 学校のゲッター
    public School getSchool() {
        return school;
    }

    // セッター

    // 科目番号のセッター
    public void setNo(String no) {
        this.no = no;
    }

    // 科目名のセッター
    public void setName(String name) {
        this.name = name;
    }

    // 入学年度のセッター
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    // クラス番号のセッター
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // 出席フラグのセッター
    public void setAtttend(boolean isAtttend) {
        this.isAtttend = isAtttend;
    }

    // 学校のセッター
    public void setSchool(School school) {
        this.school = school;
    }
}
