package dao;

public class Snippet {
	HttpSession session = request.getSession();
	Teacher teacher = (Teacher)session.getAttribute("user");
}

