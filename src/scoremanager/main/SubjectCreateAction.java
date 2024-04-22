package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println( "CreateActionの実行" );
    	HttpSession session = request.getSession();
    	Student student = (Student)session.getAttribute("user");
/*    	// LocalDateインスタンス
    	LocalDate todaysDate = LocalDate.now();
    	// 現在の年を取得
    	int year = todaysDate.getYear();*/
    	// クラス番号Daoを初期化
    	SubjectDao subjectDao = new SubjectDao();


    	// ３
    	// ＤＢからデータ取得
    	// セッションの学校コードをもとに教科の一覧を取得
    	/*daoのfilterメゾットが必要*/
    	List<String>list = subjectDao.filter( student.getSchool() );



    	request.setAttribute( "errors", request.getAttribute("errors") );
   /* 	科目コード入力覧*/
    	request.setAttribute( "subjectcd", request.getAttribute("subjectcd") );
    /*	科目名入力*/
    	request.setAttribute( "subjectname", request.getAttribute("subjectname") );


    	// ７
    	// JSPへフォワード
    	request.getRequestDispatcher("subject_create.jsp").forward(request, response);

    }
}