package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class TestRegistAction extends Action {

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
    	// 検索結果
    	List<Test> tests = null;
    	// エラーメッセージ
    	String error = null;

    	in_entYear = request.getParameter("f1");
    	in_classNum = request.getParameter("f2");
    	in_subject = request.getParameter("f3");
    	in_no = request.getParameter("f4");

    	subject = sDao.get( in_subject, school );


    	System.out.println("☆ " + in_entYear);
    	System.out.println("☆ " + in_classNum);
    	System.out.println("☆ " + in_subject);
    	System.out.println("☆ " + in_no);

    	// 条件：検索後
    	if ( in_entYear!=null && in_classNum!=null && in_subject!=null && in_no!=null ) {
	    	// 条件：検索条件が全て選択されている
	    	if ( !in_entYear.equals("0") && !in_classNum.equals("0") && !in_subject.equals("0") && !in_no.equals("0") ) {
	    	    tests = tDao.filter(Integer.parseInt(in_entYear), in_classNum, subject, Integer.parseInt(in_no), school);
	    	// 条件：検索後、選択されていない検索条件がある
	    	} else {
	    	    error = "入学年度とクラスと科目と回数を選択してください";
	    	}
    	}
    	// 条件；

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
    	request.setAttribute( "error", error );

    	// フォワード===============================
    	request.getRequestDispatcher("test_regist.jsp").forward(request, response);

	}
}