package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	StudentDao sDao = new StudentDao();
    	ClassNumDao cNumDao = new ClassNumDao();

    	// 初期化
    	String no = "";
    	Student student = null;

    	// 選択した学生の学生情報をstudentに
    	no = request.getParameter("no");
    	student = sDao.get(no);

    	// クラス一覧
    	List<String>list = cNumDao.filter( teacher.getSchool() );

    	request.setAttribute( "ent_year", student.getEntYear() );
    	request.setAttribute( "no", student.getNo() );
    	request.setAttribute( "name", student.getName() );
    	request.setAttribute( "num", student.getClassNum() );
    	request.setAttribute( "is_attend", student.isAttend() );
    	request.setAttribute( "class_num_set",list );

    	request.getRequestDispatcher("student_update.jsp").forward(request, response);

    }
}