package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		// ログインユーザの情報
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	// 初期化==========================
    	// Dao
    	ClassNumDao cNumDao = new ClassNumDao();
    	SubjectDao sDao = new SubjectDao();
    	TestListSubjectDao tDao = new TestListSubjectDao();
    	// 入学年度
    	List<Integer> entYearSet = new ArrayList<>();
    	String entYearStr = null;
    	int entYear = 0;
    	// クラス
    	List<String>classList = null;
    	String classNum = null;
    	// 科目
    	List<Subject> subjectList = null;
    	String subjectCd = null;
    	String subjectName = null;
    	// 得点（検索結果）
    	List<TestListSubject> testListSubject = null;
    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();

    	// リクエストパラメータ取得================
    	entYearStr = request.getParameter("ent_year");
    	classNum = request.getParameter("class_num");
    	subjectCd = request.getParameter("subject_cd");

    	// 入力値チェック=======================
    	// 条件：選択されていない項目がある
    	if ( entYearStr==null || classNum==null || subjectCd==null ) {
    		errors.put("error", "入学年度とクラスと科目を指定してください");
    		request.setAttribute( "errors", errors );

    	// 条件：全て選択されている
    	} else {
    		// 入学年度を数値型に変換
    		entYear = Integer.parseInt( entYearStr );
    		// 検索実行
    		System.out.println( "☆" + entYear );
    		System.out.println( "☆" + classNum );
    		System.out.println( "☆" + sDao.get(subjectCd, teacher.getSchool()) );
    		System.out.println( "☆" + teacher.getSchool() );
    		testListSubject = tDao.filter(entYear, classNum, sDao.get(subjectCd, teacher.getSchool()), teacher.getSchool());
    		// 科目名を取得
    		subjectName = sDao.get(subjectCd, teacher.getSchool()).getName();
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
    	subjectList = sDao.filter( teacher.getSchool() );

    	// レスポンス値をセット===================
    	request.setAttribute( "ent_year_set", entYearSet );
    	request.setAttribute( "ent_year", entYear );
    	request.setAttribute( "class_list", classList );
    	request.setAttribute( "class_num", classNum );
    	request.setAttribute( "subject_list", subjectList );
    	request.setAttribute( "subject_cd", subjectCd );
    	request.setAttribute( "subject_name", subjectName );
    	request.setAttribute( "test_list", testListSubject );

    	// フォワード==========================
    	System.out.println( errors.size() );
    	// 条件：全て選択されている
    	if ( errors.size()==0 ) {
    		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    	// 条件：選択されていない項目がある
    	} else {
    		request.getRequestDispatcher("test_list.jsp").forward(request, response);
    	}
	}

}
