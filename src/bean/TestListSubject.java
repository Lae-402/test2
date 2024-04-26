package bean;

import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements java.io.Serializable {

    // フィールド
    private int entYear; // 入学年度
    private String studentNo; // 学籍番号
    private String studentName; // 学生名
    private String classNum; // クラス番号
    private Map<Integer, Integer> points; // 成績

    // コンストラクタ
    public TestListSubject() {
        this.points = new HashMap<>();
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

    // 全ての成績を取得する
    public Map<Integer, Integer> getPoints() {
        return points;
    }

    // 全ての成績を設定する
    public void setPoints(Map<Integer, Integer> points) {
    	// 引数で受け取ったマップを自身に格納
        for ( Map.Entry<Integer, Integer> entry : points.entrySet() ) {
        	putPoint( entry.getKey(), entry.getValue() );
        }
    }

    // 指定した回数の成績を取得する
    public String getPoint(int key) {
        return points.get(key) != null ? points.get(key).toString() : null;
    }

    // 成績を追加または更新する
    public void putPoint(int key, int value) {
        this.points.put(key, value);
    }

}