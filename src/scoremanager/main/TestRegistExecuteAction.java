package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		// ログインユーザの情報
		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();

    	// 初期化===============================
    	// Dao
    	TestDao tDao = new TestDao();
    	ClassNumDao cNumDao = new ClassNumDao();
    	SubjectDao sDao = new SubjectDao();
    	// クラス
    	List<String>classList = null;
    	String in_classNum = "0";
    	// 科目
    	List<Subject> subjectList = null;
    	String in_subject = "0";
    	Subject subject = new Subject();
    	// 入学年度
    	List<Integer> entYearSet = new ArrayList<>();
    	String in_entYear = "0";
    	// 回数
    	List<Integer> noList = Arrays.asList( 1, 2 );
    	String in_no = "0";
    	// 登録・変更データ
    	List<Test> in_tests = new ArrayList<>();
    	List<Test> tests = null;
    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();
//    	errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
    	// フォワード用
    	String forward = "test_regist_done.jsp";

    	in_entYear = request.getParameter("f1");
    	in_classNum = request.getParameter("f2");
    	in_subject = request.getParameter("f3");
    	in_no = request.getParameter("f4");

    	subject = sDao.get( in_subject, school );

    	tests = tDao.filter(Integer.parseInt(in_entYear), in_classNum, subject, Integer.parseInt(in_no), school);

    	String in_p_no = null;
    	int in_p = -1;

    	// 入力処理
    	for ( Test t : tests ) {
    		in_p_no = "p" + t.getStudent().getNo();
    		// 条件：入力あり（空欄でない）
    		if ( request.getParameter( in_p_no ) != "" ) {
    			in_p = Integer.parseInt(request.getParameter( in_p_no ));
    			// 条件：入力値が不正
    			if ( in_p<0 || in_p>100  ) {
    				errors.put( in_p_no, "0~100の範囲で入力してください" );
    				forward = "test_regist.jsp";
    			}
    		// 条件：入力なし（空欄である）
    		} else {
    			in_p = 666;
    		}
    		t.setSchool(school);
    		t.setNo(Integer.parseInt(in_no));
    		t.setSubject(subject);
    		t.setPoint( in_p );
    		in_tests.add(t);
    	}

    	//****************************************************************
    	//ここでTestDaoのsaveにin_testsをわたす
    	tDao.save(in_tests);
    	//

    	// フォワード用=============================
    	// ログインユーザの所属学校のクラス一覧／科目一覧
    	classList = cNumDao.filter( teacher.getSchool() );
    	subjectList = sDao.filter( teacher.getSchool() );
    	// 10年前から今年まで年をリストに追加
    	LocalDate todaysDate = LocalDate.now();
    	int year = todaysDate.getYear();
    	for ( int i = year - 10; i < year+1; i++ ) {
    		entYearSet.add(i);
    	}

    	// レスポンス値をセット=======================
    	if ( subject != null ) {
    		request.setAttribute( "subject_name", subject.getName() );
    	}
    	request.setAttribute( "tests", tests );
    	request.setAttribute( "ent_year_list", entYearSet );
    	request.setAttribute( "in_ey", in_entYear );
    	request.setAttribute( "class_list", classList );
    	request.setAttribute( "f2", in_classNum );
    	request.setAttribute( "subject_list", subjectList );
    	request.setAttribute( "in_s", subject );
    	request.setAttribute( "no_list", noList );
    	request.setAttribute( "in_n", in_no );
    	request.setAttribute( "errors", errors );

    	// フォワード===============================
    	request.getRequestDispatcher(forward).forward(request, response);

	}
}