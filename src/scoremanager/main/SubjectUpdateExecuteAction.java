package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();

		// 学生Dao
    	SubjectDao sDao = new SubjectDao();
		Subject subject = new Subject();

		String cd = "";              // 入力値：氏名
    	String name = "";              // 入力値：氏名
    	boolean done = false;

    	// リクエストパラメータの取得
    	cd = request.getParameter("cd");
    	name = request.getParameter("name");

    	System.out.println( "subjectにセット" );
    	subject.setCd(cd);
    	subject.setName(name);
    	subject.setSchool(school);

    	System.out.println( "更新" );
    	done = sDao.save(subject);

    	if (done) {
			// JSPへフォワード
			request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    	}

    }
}