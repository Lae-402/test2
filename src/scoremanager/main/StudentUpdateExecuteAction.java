package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction  extends Action {

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
    	boolean isAttend = false;     // 在学フラグ：一律でtrue
    	boolean done = false;

    	// ２
    	// リクエストパラメータの取得
    	entYear = Integer.parseInt( request.getParameter("ent_year") );
    	no = request.getParameter("no");
    	name = request.getParameter("name");
    	classNum = request.getParameter("class_num");
    	if ( request.getParameter("is_attend") == "t" ) {
    		isAttend = true;
    	}

    	System.out.println( "sutudentにセット" );
    	student.setEntYear(entYear);
    	student.setNo(no);
    	student.setName(name);
    	student.setClassNum(classNum);
    	student.setAttend(isAttend);
    	student.setSchool(school);

    	System.out.println( "更新" );
    	done = sDao.save(student);

    	if (done) {
			// JSPへフォワード
			request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    	}
    }
}