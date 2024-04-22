package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class SubjectCreateExecuteAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{



		/*
		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();*/

		// 学生Dao
    	StudentDao sDao = new StudentDao();
		Student student = new Student();

 /*   	int entYear = 0;       // 入力値：入学年度
    	String no = "";   */             // 入力値：学生番号
    	String subjectcd = "";              // 入力値：科目コード
    	String subjectname = "";         // 入力値：科目名
/*    	boolean isAttend = true;     // 在学フラグ：一律でtrue
    	boolean done = false;*/

    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();



    	// ２
    	// リクエストパラメータの取得
    	System.out.println( "リクエスト取得" );
    /*	entYear = Integer.parseInt( request.getParameter("ent_year") );
    	no = request.getParameter("no");*/
    	subjectcd = request.getParameter("name");
    	subjectname = request.getParameter("class_num");




    	/*えらー
    	科目コード文字数エラー*/
    	// 条件：入学年度の指定が無い
    	if ( subjectcd == 0 ) {
    		request.setAttribute("no", no);
    		request.setAttribute("name", subjectcd);
    		request.setAttribute("class_num", classNum);
    		errors.put( "error1", "入学年度を指定してください" );
    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("StudentCreate.action").forward(request, response);
    	}

    	System.out.println( "sutudentにセット" );
    	student.setEntYear(entYear);
    	student.setNo(no);
    	student.setName(subjectcd);
    	student.setClassNum(classNum);
    	student.setAttend(isAttend);
    	student.setSchool(school);

    	if ( sDao.get(no) != null ) {
    		request.setAttribute("ent_year", entYear);
    		request.setAttribute("no", no);
    		request.setAttribute("name",subjectcd);
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