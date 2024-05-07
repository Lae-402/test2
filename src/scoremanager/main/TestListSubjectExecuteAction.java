package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;


public class TestListSubjectExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ログインユーザの情報を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// 初期化==========================
		// DAOインスタンスの生成
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();
		TestListSubjectDao tDao = new TestListSubjectDao();

		// 入学年度の選択肢
		List<Integer> entYearSet = new ArrayList<>();
		String entYearStr = null;
		int entYear = 0;

		// クラスの選択肢
		List<String> classList = null;
		String classNum = null;
		int classnum = 0;
		// 科目の選択肢

		List<Subject> subjectList = null;
/*		List<String> subjectNameList = new ArrayList<>();*/
		String subjectCd = null;
		int subjectcd = 0;
		String subjectName = null;

		// 得点（検索結果）
		List<TestListSubject> testListSubject = null;

		// エラーメッセージ
		Map<String, String> errors = new HashMap<>();

		// リクエストパラメータの取得================
		entYearStr = request.getParameter("ent_year");//入学年度
		classNum = request.getParameter("class_num");//クラス番号
		subjectCd = request.getParameter("subject_cd");//科目

		// 入力値のチェック=======================
		// 条件：選択されていない項目がある場合
		System.out.print(entYearStr);
		System.out.print(classNum);
		System.out.print(subjectCd);

		entYear = Integer.parseInt(entYearStr);
	/*	classnum = Integer.parseInt(classNum);
		subjectcd = Integer.parseInt(subjectCd);*/
		//どうもNullではなく0で選択されるみたいなので条件変更
		if ("0".equals(entYearStr) || "0".equals(classNum) || "0".equals(subjectCd)) {
			System.out.println("入力値チェックkエラー分岐");
			// エラーメッセージをセット
			errors.put("error", "入学年度とクラスと科目を選択してください");
			// エラーメッセージをリクエストにセット
			request.setAttribute("errors", errors);
			// 条件：全ての項目が選択されている場合
		} else {
			System.out.println("入力値チェック正常分岐");
			// 入学年度を数値型に変換
	/*		entYear = Integer.parseInt(entYearStr);*/
			// 検索実行
			testListSubject = tDao.filter(entYear, classNum, sDao.get(subjectCd, teacher.getSchool()),teacher.getSchool());
			// 科目名を取得
			subjectName = sDao.get(subjectCd, teacher.getSchool()).getName();
		}

		// フォワード用========================
		// 10年前から10年後までの年をリストに追加
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}
		// ログインユーザの所属学校のクラス一覧／科目一覧を取得
		classList = cNumDao.filter(teacher.getSchool());
		subjectList = sDao.filter(teacher.getSchool());
	/*	for ( Subject s : subjectList ) {
			subjectNameList.add( s.getName() );
		}
*/

		System.out.print("学年選択"+entYear);
		System.out.print("学年"+classNum);
		// レスポンス値をセット===================
		// 入学年度の選択肢
		request.setAttribute("ent_year_set", entYearSet);//入学年度選択用リスト
		//結果表示用
		request.setAttribute("entyear", entYear);//結果表示用　入学年度

		// クラスの選択肢
		request.setAttribute("classList", classList);//クラス選択用リスト
		//結果表示用
		request.setAttribute("classnum", classNum);//結果表示用 クラス番号

		// 科目の選択肢
		request.setAttribute("subjectList", subjectList);//科目洗濯用リスト
		//結果表示用
		request.setAttribute("subjectcd", subjectCd);//結果表示用 学生番号
		request.setAttribute("subjectname", subjectName);//結果表示用　学生氏名

		// 検索結果
		request.setAttribute("test_list", testListSubject);//結果表示用　


		// フォワード==========================
		// 条件：全ての項目が選択されている場合
		if (errors.size() == 0) {
			// 科目別成績一覧画面にフォワード
			request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
			// 条件：選択されていない項目がある場合
		} else {
			// 成績一覧画面にフォワード
			request.getRequestDispatcher("test_list.jsp").forward(request, response);
		}
	}
}
