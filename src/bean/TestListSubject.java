package bean;

import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements java.io.Serializable {

    // フィールド
    private int entYear; // 入学年度
    private String studentNo; // 学籍番号
    private String studentName; // 学生名
    private String classNum; // クラス番号
    private Map<Integer, Integer> point; // 成績

    // コンストラクタ
    public TestListSubject() {
        this.point = new HashMap<>();
    }

    // ゲッターとセッター
    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // 指定した回数の成績を取得する
    public String getPoint(int key) {
        return point.get(key) != null ? point.get(key).toString() : null;
    }

    // 成績を追加または更新する
    public void putPoint(int key, int value) {
        point.put(key, value);
    }

    // 全ての成績を取得する
    public Map<Integer, Integer> getPoints() {
        return point;
    }

    // 全ての成績を設定する
    public void setPoints(Map<Integer, Integer> point) {
        this.point = point;
    }
}
