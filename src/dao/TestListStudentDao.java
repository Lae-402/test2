package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "SELECT subject.name, test.subject_cd, test.no, test.point "
			+ "FROM test "
			+ "INNER JOIN subject "
			+ "ON test.subject_cd = subject.cd "
			+ "AND subject.school_cd = ? ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 */
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		try {
			// リザルトセットを全件走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				TestListStudent testListStudent = new TestListStudent();
				// 学生インスタンスに検索結果をセット
				testListStudent.setSubjectName(rSet.getString("name"));
				testListStudent.setSubjectCd(rSet.getString("subject_cd"));
				testListStudent.setNum(rSet.getInt("no"));;
				testListStudent.setPoint(rSet.getInt("point"));;
				// リストに追加
				list.add(testListStudent);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * filterメソッド
	 */
	public List<TestListStudent> filter( Student student ) throws Exception {
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "AND test.student_no = ? ";
		// SQL文のソート
		String order = "ORDER BY subject_cd ASC, no ASC";

		try {
			System.out.println("student  :" +student);
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, student.getSchool().getCd());
			// プリペアードステートメントにバインド
			statement.setString(2, student.getNo());
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