
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

public class SubjectUpdateExecuteAction extends Action {

    // オーバーライド
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool(); // 教師の所属する学校を取得

        // 学生DAOのインスタンス化
        SubjectDao sDao = new SubjectDao();


        System.out.print("test");


        // 入力値の初期化
        String cd = ""; // 科目番号
        String name = ""; // 科目名
        boolean done = false; // 更新が成功したかのフラグ


       	// エラーメッセージ
    	Map<String, String> errors = new HashMap<>();


        // リクエストパラメータから入力値を取得
        System.out.print("test2");

		cd = request.getParameter("cd"); // 学生番号を取得
        name = request.getParameter("name"); // 氏名を取得


        if  ( sDao.get(cd,school) == null ) {
    		System.out.print("error2");
    		request.setAttribute("cd",cd);
    		request.setAttribute("name", name);
    		errors.put( "error2", "科目が存在していません" );

    		System.out.print("科目が存在していません");

    		request.setAttribute( "errors", errors );
    		request.getRequestDispatcher("SubjectUpdate.action").forward(request, response);

    	}else{        // 新しい学生オブジェクトを作成し、入力値をセット
            Subject subject = new Subject();
            System.out.print("test4");
            subject.setName(name);//科目名をセット
            subject.setCd(cd);//科目番号をセット

            subject.setSchool(school); // 学校情報を設定

            // 学生情報を更新し、結果を取得
            done = sDao.save(subject); // 学生情報をデータベースに保存
}



        // 更新が成功した場合は、更新完了画面にフォワード
        if (done) {
            request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        }
    }
}
