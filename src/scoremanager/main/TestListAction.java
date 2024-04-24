package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	// Dao初期化
    	ClassNumDao cNumDao = new ClassNumDao();
    	SubjectDao sDao = new SubjectDao();

    	// 初期化
    	List<String>classList = null;
    	List<Subject> subjectList = null;

    	// ログインユーザの所属学校の・・・
    	// クラス一覧
    	classList = cNumDao.filter( teacher.getSchool() );
    	// 科目一覧
    	subjectList = sDao.filter( teacher.getSchool() );

    	// フォワード
    	request.setAttribute( "classList", classList );
    	request.setAttribute( "subjectList", subjectList );
    	request.getRequestDispatcher("test_list.jsp").forward(request, response);

	}
}
