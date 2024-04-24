package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();

		// 学生Dao
    	StudentDao sDao = new StudentDao();

		Student student = new Student();

    	int entYear = 0;       // 入力値：入学年度
    	String no = "";                // 入力値：学生番号
    	String name = "";              // 入力値：氏名
    	String classNum = "";         // 入力値：クラス
    	boolean isAttend = true;     // 在学フラグ：一律でtrue
    	boolean done = false;

    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();

    	// ２
    	// リクエストパラメータの取得
    	System.out.println( "リクエスト取得" );
    	entYear = Integer.parseInt( request.getParameter("ent_year") );
    	no = request.getParameter("no");
    	name = request.getParameter("name");
    	classNum = request.getParameter("class_num");

    	// 条件：入学年度の指定が無い
    	if ( entYear == 0 ) {
    		request.setAttribute("no", no);
    		request.setAttribute("name", name);
    		request.setAttribute("class_num", classNum);
    		errors.put( "error1", "入学年度を指定してください" );
    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("StudentCreate.action").forward(request, response);
    	}

    	System.out.println( "sutudentにセット" );
    	student.setEntYear(entYear);
    	student.setNo(no);
    	student.setName(name);
    	student.setClassNum(classNum);
    	student.setAttend(isAttend);
    	student.setSchool(school);

    	if ( sDao.get(no) != null ) {
    		System.out.print("error2");
    		request.setAttribute("ent_year", entYear);
    		request.setAttribute("no", no);
    		request.setAttribute("name", name);
    		request.setAttribute("class_num", classNum);
    		errors.put( "error2", "学生番号が重複しています" );
    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("StudentCreate.action").forward(request, response);
    	}

    	
    	
    	System.out.println( "結果格納" );
    	done = sDao.save(student);

    	if (done) {
			// JSPへフォワード
			System.out.println( "create_doneにフォワード" );
			request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
    	}
    }
}