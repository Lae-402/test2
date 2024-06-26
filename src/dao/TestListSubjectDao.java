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
	private String baseSql = "SELECT student.ent_year, test.class_num, test.student_no, student.name, test.no, test.point "
			+ "FROM test "
			+ "INNER JOIN student "
			+ "ON test.student_no = student.no "
			+ "AND student.school_cd = ? ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 */
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		Integer tmp = 0;
		TestListSubject testListSubject = new TestListSubject();
		List<TestListSubject> list = new ArrayList<>();
		Map<Integer, Integer> points = new HashMap<>();
		Integer p = null;
		String sNo = "";
		try {
			// リザルトセットを全件走査
			while (rSet.next()) {
				sNo = rSet.getString("student_no");
				if ( tmp!=0  ) {
					if ( tmp==2 || testListSubject.getStudentNo() != sNo ) {
						testListSubject.setPoints(points);
						list.add(testListSubject);
						System.out.println("★add");
					}
				}
				// 学生インスタンスを初期化
				testListSubject = new TestListSubject();
				// 学生インスタンスに検索結果をセット
				testListSubject.setEntYear(rSet.getInt("ent_year"));
				testListSubject.setStudentNo(sNo);
				testListSubject.setStudentName(rSet.getString("name"));
				System.out.println(testListSubject.getStudentName()+"("+sNo+"）");
				testListSubject.setClassNum(rSet.getString("class_num"));
				if ( rSet.getInt("no") == 1 ) {
					p = rSet.getInt("point");
					if (rSet.wasNull()) {
						p = 666;
					}
					points.put(1, p);
//					testListSubject.setPoints(points);
					tmp = 1;
				} else {
					p = rSet.getInt("point");
					if (rSet.wasNull()) {
						p = 666;
					}
					points.put(2, p);
//					testListSubject.setPoints(points);
					tmp = 2;
				}
				System.out.println(tmp+" 回目："+testListSubject.getPoint(tmp));
			/*	testListSubject.setPoints(points);
				System.out.println("testsubjectfilter---"+testListSubject);*/
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		// 最後の追加処理
		System.out.println("★add");
		list.add(testListSubject);
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
		String condition = "AND student.ent_year = ? "
				+ "AND student.class_num = ? "
				+ "AND test.subject_cd = ? ";
		// SQL文のソート
		String order = "ORDER BY test.student_no ASC, test.no ASC;";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			/*System.out.println(statement);*/

			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントにバインド
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			statement.setString(4, subject.getCd());
			// プライベートステートメントを実行
			System.out.println(statement);
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet);
			/*System.out.println(list);*/
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