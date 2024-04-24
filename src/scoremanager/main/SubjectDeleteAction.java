package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class SubjectDeleteAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

    	StudentDao sDao = new StudentDao();

    	// 初期化
    	String cd = "";
    	List<Student> student = new ArrayList<Student>(1);

    	// 選択した学生の学生情報をstudentに
    	cd = request.getParameter("cd");
    	student.add(sDao.get(no));

    	request.setAttribute( "student",student );

    	request.getRequestDispatcher("student_delete.jsp").forward(request, response);

    }
}