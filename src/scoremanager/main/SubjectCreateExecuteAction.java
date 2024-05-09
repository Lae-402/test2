package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction  extends Action {


	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	School school = teacher.getSchool();


		// 学生Dao
    	SubjectDao sDao = new SubjectDao();
		Subject subject = new Subject();


    	String subjectcd = "";              // 入力値：科目コード
    	String subjectname = "";         // 入力値：科目名

    	boolean done = false;
    	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();



    	// ２
    	// リクエストパラメータの取得
    	subjectcd = request.getParameter("cd");
    	subjectname = request.getParameter("name");

    	/*えらー
    	科目コード文字数エラー*/
    	// 条件：科目コード3文字
    	int num =subjectcd.length();
    	if ( num != 3 ) {
    		request.setAttribute("subjectcd", subjectcd);
    		request.setAttribute("subjectname", subjectname);
    		errors.put( "error1", "科目コードは3文字で入力してください" );
    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("SubjectCreate.action").forward(request, response);

    	}else if  ( sDao.get(subjectcd,school) != null ) {
    		request.setAttribute("subjectcd",subjectcd);
    		request.setAttribute("subjectname", subjectname);
    		errors.put( "error2", "科目コードが重複しています" );
    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
    	}else{


        	subject.setName(subjectname);
        	subject.setCd(subjectcd);
        	subject.setSchool( school );

        	done = sDao.save(subject);


    	}

    	if (done) {
			// JSPへフォワード
			request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
    	}
    }
}