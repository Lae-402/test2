package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action  {
	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();
    	SubjectDao sDao = new SubjectDao();

		Subject subject = new Subject();

    	String cd = "";
    	boolean done = false;

    	// ２
    	// リクエストパラメータの取得
    	cd = request.getParameter("cd");

    	subject.setCd(cd);
    	subject.setSchool(school);

    	done = sDao.delete(subject);

    	if (done) {
			// JSPへフォワード
			request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    	}
    }
}