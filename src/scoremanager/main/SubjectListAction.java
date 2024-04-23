package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	List<Subject> subjects = null; // 科目リスト

    	// 科目Dao
    	SubjectDao sDao = new SubjectDao();

		subjects = sDao.filter( teacher.getSchool() );

    	// レスポンス値をセット
    	request.setAttribute( "subject_list", subjects );

    	// ７
    	// JSPへフォワード
    	request.getRequestDispatcher("subject_list.jsp").forward(request, response);

    }
}