package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println( "subjectCreateActionの実行" );


    	// ＤＢからデータ取得
    	request.setAttribute( "errors", request.getAttribute("errors") );
   /* 	科目コード入力覧*/
    	request.setAttribute( "cd", request.getAttribute("cd") );
    /*	科目名入力*/
    	request.setAttribute( "name", request.getAttribute("name") );


    	// ７
    	// JSPへフォワード
    	request.getRequestDispatcher("subject_create.jsp").forward(request, response);
    }
}