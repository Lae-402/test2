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
		// ログインユーザの情報
		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	// 初期化===============================
    	// Dao
    	ClassNumDao cNumDao = new ClassNumDao();
    	SubjectDao sDao = new SubjectDao();
    	// クラス一覧
    	List<String>classList = null;
    	// 科目一覧
    	List<Subject> subjectList = null;

    	// ログインユーザの所属学校のクラス一覧／科目一覧
    	classList = cNumDao.filter( teacher.getSchool() );
    	subjectList = sDao.filter( teacher.getSchool() );

    	// レスポンス値をセット=======================
    	request.setAttribute( "classList", classList );
    	request.setAttribute( "subjectList", subjectList );

    	// フォワード===============================
    	request.getRequestDispatcher("test_list.jsp").forward(request, response);

	}

	// Who are you?
	private void setTestListSubject (HttpServletRequest request, HttpServletResponse response) throws Exception{

	}

	// Who are you? vol.2
	private void setTestListStudent (HttpServletRequest request, HttpServletResponse response) throws Exception{

	}
}