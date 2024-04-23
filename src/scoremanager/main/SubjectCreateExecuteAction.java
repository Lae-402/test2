package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{



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
    	System.out.println( "リクエスト取得" );

    	subjectcd = request.getParameter("subjectcd");
    	subjectname = request.getParameter("subjectname");




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
    	}

    	System.out.println( "sutudentにセット" );

    	subject.setName(subjectname);
    	subject.setClassNum(subjectcd);

    	if ( sDao.get(subjectcd) != null ) {

    		request.setAttribute("subjected",subjectcd);
    		request.setAttribute("subjectname", subjectname);
    		errors.put( "error2", "科目コードが重複しています" );
    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("StudentCreate.action").forward(request, response);
    	}

    	System.out.println( "結果格納" );
    	done = sDao.save(student);

    	if (done) {
			// JSPへフォワード
			System.out.println( "create_doneにフォワード" );
			request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
    	}
    }
}