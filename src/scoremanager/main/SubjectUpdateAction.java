package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    // オーバーライド
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 学生DAOとクラス番号DAOのインスタンス化
        SubjectDao sDao = new SubjectDao();


        // 初期化
        String cd = ""; // 選択された学生の学籍番号
        List<Subject> subject = new ArrayList<>(); // 学生情報を格納するリスト

        // リクエストパラメータから学籍番号を取得し、該当する学生情報をリストに追加
        cd = request.getParameter("cd");
        subject.add(sDao.get(cd,school)); // 科目番号を元に学生情報を取得し、リストに追加


        // 学生情報とクラス一覧をリクエスト属性に設定
        request.setAttribute("subject", subject); // 学生情報をリクエスト属性に設定


        // 学生情報更新画面にフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
