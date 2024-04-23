package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	SubjectDao sDao = new SubjectDao();

    	// 初期化
    	String cd = "";
    	List<Subject> subject = new ArrayList<Subject>(1);

    	// 選択した学生の学生情報をstudentに
    	cd = request.getParameter("cd");
    	subject.add(sDao.get(cd, teacher.getSchool()));

    	request.setAttribute( "subject", subject );

    	request.getRequestDispatcher("subject_update.jsp").forward(request, response);

    }
}