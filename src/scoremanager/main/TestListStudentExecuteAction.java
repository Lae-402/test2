package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ログインユーザの情報を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// Daoオブジェクトの初期化
		ClassNumDao cNumDao = new ClassNumDao();
		StudentDao stDao = new StudentDao();
		SubjectDao suDao = new SubjectDao();
		TestListStudentDao tDao = new TestListStudentDao();

		// 入学年度のリストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// クラスのリストを初期化
		List<String> classList = null;
		// 科目のリストを初期化
		List<Subject> subjectList = null;
		// 学生番号を初期化
		String studentNo = null;
		// 学生名を初期化
		String studentName = null;
		// 得点のリストを初期化
		List<TestListStudent> testListStudent = null;
		// エラーメッセージを初期化
		Map<String, String> errors = new HashMap<>();

		// リクエストパラメータから学生番号を取得
		studentNo = request.getParameter("student_no");

		// 学生情報の検索
		Student student = stDao.get(studentNo);
		if (student != null) {
			// 学生名を取得
			studentName = student.getName();
			// 学生の成績を取得
			testListStudent = tDao.filter(student);
			System.out.println("student: " + testListStudent);
		}

		// 10年前から10年後までの年をリストに追加
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}


		// ログインユーザの所属学校のクラス一覧を取得
		classList = cNumDao.filter(teacher.getSchool());
		// ログインユーザの所属学校の科目一覧を取得
		subjectList = suDao.filter(teacher.getSchool());


		// リクエスト属性に値を設定
		// リクエスト属性に入学年度のリストを設定
		//一覧表示用
		request.setAttribute("ent_year_set", entYearSet);
		// リクエスト属性にクラスのリストを設定
		//一覧表示用
		request.setAttribute("classList", classList);
		// リクエスト属性に科目のリストを設定
		//一覧表示用
		request.setAttribute("subjectList", subjectList);
		// リクエスト属性に学生名を設定
		request.setAttribute("student_name", studentName);
		// リクエスト属性に学生の成績リストを設定
		request.setAttribute("test_list", testListStudent);


		// 学生が存在しない場合の処理
		if (studentName == null) {
			System.out.println("分岐１");
			// エラーメッセージを設定
			errors.put("no_stu", "成績情報が存在しませんでした");
			request.setAttribute( "errors", errors );
			// JSPにフォワード
			request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
		} else {
			// 学生が存在する場合の処理
			System.out.println("分岐２");
			// JSPにフォワード
			request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
		}
	}
}
