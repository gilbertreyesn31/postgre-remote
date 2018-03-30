package login.sumit.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/loginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginRegister() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO cd = new CustomerDAOImpl();
		String userName = request.getParameter("username");
		String password = request.getParameter("password1");
		String submitType = request.getParameter("submit");
		Customer c = new Customer();
		c = cd.getCustomer(userName, password);
		// Customer c=cd.getCustomer(userName, password);

		if (submitType.equals("login") && c != null && c.getName() != null) {

			request.setAttribute("message", c.getName());
			request.getRequestDispatcher("welcome.jsp").forward(request, response);

		} else if (submitType.equals("register")) {

			c = new Customer();

			c.setName(request.getParameter("name"));
			c.setPassword(password);
			c.setUsername(userName);
			cd.insertCustomer(c);
			request.setAttribute("succesMessage", "Registration Done, please Login to continue!!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} else {
			request.setAttribute("message", "Data not Found, click on Register!!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
