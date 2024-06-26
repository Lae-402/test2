package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction  extends Action {

	// オーバーライド
	@Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println( "CreateActionの実行" );
    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");
    	// LocalDateインスタンス
    	LocalDate todaysDate = LocalDate.now();
    	// 現在の年を取得
    	int year = todaysDate.getYear();
    	// クラス番号Daoを初期化
    	ClassNumDao cNumDao = new ClassNumDao();


    	// ３
    	// ＤＢからデータ取得
    	// ログインユーザの学校コードをもとにクラス番号の一覧を取得
    	List<String>list = cNumDao.filter( teacher.getSchool() );

    	List<Integer> entYearSet = new ArrayList<>();
    	// 10年前から1年後まで年をリストに追加
    	for ( int i = year - 10; i < year+11; i++ ) {
    		entYearSet.add(i);
    	}
    	request.setAttribute( "class_num_set", list );
    	request.setAttribute( "ent_year_set", entYearSet );
    	request.setAttribute( "errors", request.getAttribute("errors") );
    	request.setAttribute( "ent_year", request.getAttribute("ent_year") );
    	request.setAttribute( "no", request.getAttribute("no") );
    	request.setAttribute( "name", request.getAttribute("name") );
    	request.setAttribute( "class_num", request.getAttribute("class_num") );

    	// ７
    	// JSPへフォワード
    	request.getRequestDispatcher("student_create.jsp").forward(request, response);

    }
}