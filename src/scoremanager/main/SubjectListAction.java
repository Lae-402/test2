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
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class SubjectListAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	String entYearStr = "";       // 入力値：入学年度
    	String classNum = "";         // 入力値：クラス番号
    	String isAttendStr = "";      // 入力値：在学フラグ
    	int entYear = 0;               // 入学年度
    	boolean isAttend = false;     // 在学フラグ
    	List<Student> students = null; // 学生リスト
    	// LocalDateインスタンス
    	LocalDate todaysDate = LocalDate.now();
    	// 現在の年を取得
    	int year = todaysDate.getYear();
    	// 学生Dao
    	StudentDao sDao = new StudentDao();
    	// クラス番号Daoを初期化
    	ClassNumDao cNumDao = new ClassNumDao();
    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();


    	// ２
    	// リクエストパラメータの取得
    	entYearStr = request.getParameter("f1");
    	classNum = request.getParameter("f2");
    	isAttendStr = request.getParameter("f3");

    	// *******************************
    	// *******************************
    	if ( entYearStr != null ) {
    		// 数値型に変換
    		entYear = Integer.parseInt( entYearStr );
    	}
    	if ( isAttendStr != null ) {
    		isAttend = true;
    	}
    	// *******************************
    	// *******************************

    	// ３
    	// ＤＢからデータ取得
    	// ログインユーザの学校コードをもとにクラス番号の一覧を取得
    	List<String>list = cNumDao.filter( teacher.getSchool() );

    	if ( entYear != 0 && !classNum.equals("0") ) {
    		// 入学年度とクラス番号を指定
    		students = sDao.filter( teacher.getSchool(), entYear, classNum, isAttend );
    	} else if ( entYear != 0 && classNum.equals("0") ) {
    		// 入学年度のみ指定
    		students = sDao.filter( teacher.getSchool(), entYear, isAttend );
    	// 条件：指定なし
    	} else if ( entYear==0 && classNum == null || entYear == 0 && classNum.equals("0") ) {
    		// 全学生情報を取得
    		students = sDao.filter( teacher.getSchool(), isAttend );
    	} else {
    		errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
    		request.setAttribute( "errors", errors );
    		// 全学生情報を取得
    		students = sDao.filter( teacher.getSchool(), isAttend );
    	}

    	// ４
    	//ビジネスロジック
    	// リストを初期化
    	List<Integer> entYearSet = new ArrayList<>();
    	// 10年前から1年後まで年をリストに追加
    	for ( int i = year - 10; i < year+1; i++ ) {
    		entYearSet.add(i);
    	}

    	// ５は？
    	// なくていいらしい

    	// ６
    	// レスポンス値をセット
    	// リクエストに入学年度をセット
    	request.setAttribute( "f1", entYear );
    	request.setAttribute( "f2", classNum );
    	if ( isAttendStr != null ) {
    		isAttend = true;
    		request.setAttribute( "f3", isAttendStr );
    	}
    	request.setAttribute( "students", students );
    	request.setAttribute( "class_num_set", list );
    	request.setAttribute( "ent_year_set", entYearSet );

    	// ７
    	// JSPへフォワード
    	request.getRequestDispatcher("student_list.jsp").forward(request, response);

    }
}