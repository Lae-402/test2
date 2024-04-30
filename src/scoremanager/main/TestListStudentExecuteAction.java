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
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		// ログインユーザの情報
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	// 初期化==========================
    	// Dao
    	ClassNumDao cNumDao = new ClassNumDao();
    	StudentDao stDao = new StudentDao();
    	SubjectDao suDao = new SubjectDao();
    	TestListStudentDao tDao = new TestListStudentDao();
    	// 入学年度
    	List<Integer> entYearSet = new ArrayList<>();
    	// クラス
    	List<String>classList = null;
    	// 科目
    	List<Subject> subjectList = null;
    	// 学生番号
    	Student student = new Student();
    	String studentNo = null;
    	String studentName = null;
    	// 得点（検索結果）
    	List<TestListStudent> testListStudent = null;
    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();

    	// リクエストパラメータ取得================
    	studentNo = request.getParameter("student_no");

    	// 検索==============================
    	// 学生検索
    	student = stDao.get(studentNo);
    	if ( student != null ) {
			// 学生名を取得
    		studentName = stDao.get(studentNo).getName();
    		// 成績実行
    		testListStudent = tDao.filter( student );
    		System.out.println("student:  " + testListStudent);
    	}

    	// フォワード用========================
    	// 10年前から10年後まで年をリストに追加
    	LocalDate todaysDate = LocalDate.now();
    	int year = todaysDate.getYear();
    	for ( int i = year - 10; i < year+11; i++ ) {
    		entYearSet.add(i);
    	}
     	// ログインユーザの所属学校のクラス一覧／科目一覧
    	classList = cNumDao.filter( teacher.getSchool() );
    	subjectList = suDao.filter( teacher.getSchool() );

    	// レスポンス値をセット===================
    	request.setAttribute( "ent_year_set", entYearSet );
    	request.setAttribute( "class_list", classList );
    	request.setAttribute( "subject_list", subjectList );
    	request.setAttribute( "student_name", studentName );
    	request.setAttribute( "test_list", testListStudent );

    	// フォワード==========================
    	// 条件：選択された学生が存在しない
    	if ( studentName==null ) {
    		System.out.println("分岐１");
    		errors.put("no_stu", "学生が登録されていません");
    		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    	// 条件：選択された学生が存在する
    	} else {
    		System.out.println("分岐２");
    		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);    	}
	}

}
