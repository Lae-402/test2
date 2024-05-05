package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {


	// ※getメソッドは使わないので作りません

	private String baseSql = "SELECT student.ent_year, test.class_num, test.student_no, student.name, test.subject_cd, test.no, test.point "
			+ "FROM test "
			+ "INNER JOIN student "
			+ "ON test.student_no = student.no "
			+ "AND student.school_cd = ? "
			+ "WHERE student.is_attend = true ";

	private List<Test> postFilter ( ResultSet rSet, School school ) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		try {
			// リザルトセットを全件走査
			while (rSet.next()) {
				// インスタンスを初期化
				Student student = new Student();
				Subject subject = new Subject();
				Test test = new Test();
				// 学生インスタンスに検索結果をセット
				student.setSchool(school);
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setNo(rSet.getString("student_no"));
				student.setName(rSet.getString("name"));
				subject.setCd(rSet.getString("subject_cd"));
				test.setSubject(subject);
				test.setStudent(student);

				Integer p = rSet.getInt("point");
				if (rSet.wasNull()) {
				    p = 666;
				}
				test.setPoint(p);
				// リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Test> filter ( int entYear, String classNum, Subject subject, int num, School school ) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		try {
			statement = connection.prepareStatement(""
					+ "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) "
					+ "SELECT student.no , subject.cd , ?, ?, null, student.class_num FROM student, subject "
					+ "WHERE student.school_cd = ? "
					+ "AND subject.school_cd = ? "
					+ "AND student.is_attend = true "
					+ "AND NOT EXISTS ( "
					+ 		"SELECT * FROM test "
					+ 		"WHERE student_no=student.no "
					+ 		"AND subject_cd=subject.cd "
					+ 		"AND school_cd=? "
					+ 		"AND no=? "
					+ 		"AND class_num=student.class_num "
					+ ");" );
			statement.setString( 1, school.getCd() );
			statement.setInt( 2, num );
			statement.setString( 3, school.getCd() );
			statement.setString( 4, school.getCd() );
			statement.setString( 5, school.getCd() );
			statement.setInt( 6, num );
			statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

		// SQL文の条件
		String condition = "AND student.ent_year = ? "
				+ "AND student.class_num = ? "
				+ "AND test.subject_cd = ? "
				+ "AND test.no = ? ";
		// SQL文のソート
		String order = "ORDER BY test.student_no ASC;";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントにバインド
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			statement.setString(4, subject.getCd());
			statement.setInt(5, num);
			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet, school);
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

	public boolean save ( List<Test> list ) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		try {
			for ( Test test : list ) {
				save( test, connection );
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}

		return true;
	}

	private boolean save ( Test test, Connection connection ) throws Exception {
		PreparedStatement statement = null;
		int count = 0;

		try {
			statement = connection.prepareStatement(
					"update test set point=? where student_no=? and subject_cd=? and school_cd=? and no=? and class_num=?;");
			System.out.println("\n★ " + test.getStudent().getName());
			if ( test.getPoint()==666 ) {
				statement.setObject( 1, null );
				System.out.println("point：null");
			} else {
				statement.setInt( 1, test.getPoint() );
				System.out.println("point："+test.getPoint());
			}
			statement.setString( 2, test.getStudent().getNo() );
			System.out.println("student_no：" + test.getStudent().getNo());
			statement.setString( 3, test.getSubject().getCd() );
			System.out.println("subject_cd：" + test.getSubject().getCd());
			statement.setString( 4, test.getSchool().getCd() );
			System.out.println("school_cd：" + test.getSchool().getCd());
			statement.setInt( 5, test.getNo() );
			System.out.println("no：" + test.getNo());
			statement.setString( 6, test.getStudent().getClassNum() );
			System.out.println("class_num：" + test.getStudent().getClassNum());

			// プリペアードステートメントを実行
			count = statement.executeUpdate();

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

		}
		return true;
	}

//	public boolean delete ( List<Test> list ) throws Exception {
//
//	}

//	private boolean delete ( Test test, Connection connecton ) throws Exception {
//
//	}

}
