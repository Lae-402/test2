package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();
    	SubjectDao sDao = new SubjectDao();

    	// 初期化
    	String cd = "";

    	cd = request.getParameter("cd");

    	System.out.println("cd :  " + cd);
    	System.out.println("school :  " + school);
    	Subject subject = sDao.get(cd, school);

    	System.out.println(subject);

    	request.setAttribute( "cd", cd );
    	request.setAttribute( "name", subject.getName() );

    	request.getRequestDispatcher("subject_delete.jsp").forward(request, response);

    }
}