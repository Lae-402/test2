package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action  {
	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		// 学生Dao
    	StudentDao sDao = new StudentDao();

		Student student = new Student();

    	String no = "";                // 選択値：学生番号
    	boolean done = false;

    	// ２
    	// リクエストパラメータの取得
    	no = request.getParameter("no");

    	student.setNo(no);

    	done = sDao.delete(student);

    	if (done) {
			// JSPへフォワード
			request.getRequestDispatcher("student_delete_done.jsp").forward(request, response);
    	}
    }
}