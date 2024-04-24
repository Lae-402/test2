package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    // オーバーライド
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学生DAOとクラス番号DAOのインスタンス化
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        // 初期化
        String studentId = ""; // 選択された学生の学籍番号
        List<Student> students = new ArrayList<>(); // 学生情報を格納するリスト

        // リクエストパラメータから学籍番号を取得し、該当する学生情報をリストに追加
        studentId = request.getParameter("no");
        students.add(sDao.get(studentId)); // 学籍番号を元に学生情報を取得し、リストに追加

        // クラス一覧を取得
        List<String> classList = cNumDao.filter(teacher.getSchool()); // 教師の所属する学校のクラス一覧を取得

        // 学生情報とクラス一覧をリクエスト属性に設定
        request.setAttribute("student", students); // 学生情報をリクエスト属性に設定
        request.setAttribute("class_num_set", classList); // クラス一覧をリクエスト属性に設定

        // 学生情報更新画面にフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
