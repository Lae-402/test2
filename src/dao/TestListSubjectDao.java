package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from test where school_cd=?  and deleted=false ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 */
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		Map<Integer, Integer> points = new HashMap<>();
		try {
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				TestListSubject testListSubject = new TestListSubject();
				// 学生インスタンスに検索結果をセット
				testListSubject.setEntYear(rSet.getInt("ent_year"));
				testListSubject.setStudentNo(rSet.getString("student_no"));
				testListSubject.setStudentName(rSet.getString("student_name"));
				testListSubject.setClassNum(rSet.getString("class_num"));
				if ( rSet.getInt("no") == 1 ) {
					points.put(1, rSet.getInt("point"));
					testListSubject.setPoints(points);
				} else {
					points.put(2, rSet.getInt("point"));
					testListSubject.setPoints(points);
				}
				// リストに追加
				list.add(testListSubject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * filterメソッド
	 */
	public List<TestListSubject> filter( int entYear, String classNum, Subject subject, School school ) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and ent_year=? and class_num=? and subject_cd=? ";
		// SQL文のソート
		String order = "order by student_no asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントに入学年度をバインド
			statement.setInt(2, entYear);
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(3, classNum);
			statement.setString(4, subject.getCd());
			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

}