package tool;
import java.io.PrintWriter;

public class Page {
	public static void header(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset='UTF-8'>");
		out.print("<title>Servlet/JSP Sample Programs</title>");
		out.print("</head>");
		out.print("<body>");
	}
	public static void footer(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}
}