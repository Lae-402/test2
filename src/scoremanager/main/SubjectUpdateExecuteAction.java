package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
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
        StudentDao sDao = new StudentDao();

        // 入力値の初期化
        int entYear = 0; // 入学年度
        String studentNo = ""; // 学生番号
        String name = ""; // 氏名
        String classNum = ""; // クラス
        boolean isAttend = false; // 在学フラグ：一律でtrue
        boolean done = false; // 更新が成功したかのフラグ

        // リクエストパラメータから入力値を取得
        entYear = Integer.parseInt(request.getParameter("ent_year")); // 入学年度を取得
        studentNo = request.getParameter("no"); // 学生番号を取得
        name = request.getParameter("name"); // 氏名を取得
        classNum = request.getParameter("class_num"); // クラスを取得
        if (request.getParameter("is_attend").equals("t")) { // 在学フラグを取得
            isAttend = true;
        }

        // 新しい学生オブジェクトを作成し、入力値をセット
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(studentNo);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(school); // 学校情報を設定

        // 学生情報を更新し、結果を取得
        done = sDao.save(student); // 学生情報をデータベースに保存

        // 更新が成功した場合は、更新完了画面にフォワード
        if (done) {
            request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
        }
    }
}
