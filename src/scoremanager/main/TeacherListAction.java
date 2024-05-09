package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
/*import dao.ClassNumDao;*/
import dao.TeacherDao;
import tool.Action;

public class TeacherListAction extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	List<Teacher> teachers = null; // 教師リスト

    	// 学生Dao
    	TeacherDao tDao = new TeacherDao();
/*    	// クラス番号Daoを初期化
    	ClassNumDao cNumDao = new ClassNumDao();*/
    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();
    		// 全学生情報を取得
    	teachers = tDao.filter( teacher.getSchool());

    	request.setAttribute( "teacher", teachers );


    	// ７
    	// JSPへフォワード
    	request.getRequestDispatcher("teacher_list.jsp").forward(request, response);

    }
}