package User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.NumberDao;


@WebServlet("/NumberServlet")
public class NumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NumberDao numberDao = NumberDao.getInstance();
	Numbers number;
    
    public NumberServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("file.encoding","UTF-8");
		String action = request.getRequestURI().substring(request.getContextPath().length());
		if(request.getParameter("id") != null) {
			action = action +"?id";
		}
		if(request.getParameter("delete") != null) {
			action = action + "?delete";
		}
		switch(action) {
			case "/NumberServlet?id":
				try {
					listNumber(request,response);
				} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
					e.printStackTrace();
				}
				break;
			case "/NumberServlet?delete":
				try {
					deleteNumber(request, response);
				} catch (ClassNotFoundException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String number = request.getParameter("number");
		int id = (int) session.getAttribute("id");
		try {
			if(number != "") {
				numberDao.insertNumber(id, number);
				response.sendRedirect(request.getContextPath() + "/NumberServlet"
						+ "?id="+id);
			}
			else {
				response.sendRedirect(request.getContextPath() + "/NumberServlet"
						+ "?id="+id);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void listNumber(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String sid = req.getParameter("id");
		int id = Integer.parseInt(sid);
		List<Numbers> listNumber = numberDao.listNumber(id);
		int array = 0;
		for(int i = 0;i<listNumber.size();i++) {
			array = listNumber.get(i).getUserId();
		}
		HttpSession session=req.getSession();  
        session.setAttribute("id",array);  
		RequestDispatcher dispatcher = req.getRequestDispatcher("NumberList.jsp");
		req.setAttribute("listNumber", listNumber);
		dispatcher.forward(req, resp);
	}
	
	private void deleteNumber(HttpServletRequest req,HttpServletResponse resp) throws ClassNotFoundException, SQLException, IOException {
		HttpSession session = req.getSession();
		String did = req.getParameter("delete");
		int id = Integer.parseInt(did);
		numberDao.deleteNumber(id);
		resp.sendRedirect("NumberServlet?id="+session.getAttribute("id"));
	}

}
