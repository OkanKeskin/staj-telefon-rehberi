package User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Dao.NumberDao;
import Dao.UserDao;


public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();
	User user;
	UserDao userDao = UserDao.getInstance();
	NumberDao numberDao = NumberDao.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		System.setProperty("file.encoding","UTF-8");
		String action = req.getRequestURI().substring(req.getContextPath().length());
		if(req.getParameter("guncelle") != null) {
			action = action +"?guncelle";
		}
		switch(action) {
			case "/UserServlet":
				String name = req.getParameter("name");
				String surname = req.getParameter("surname");
				String tckn = req.getParameter("tckn");
				String number = req.getParameter("number");
				int id = 1;
				
				user = User.getInstance();
				user.setID(id);
				user.setName(name);
				user.setSurname(surname);
				user.setTckn(tckn);
				
				try {
					userDao.insertUser(user);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//number tablosuna ekleme.
				try {
					User newUser = userDao.selectUserForName(name, surname);
					numberDao.insertNumber(newUser.getId(), number);
				}
				catch(SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				logger.info(name+" isimli kayıt başarılı bir şekilde eklendi");
				resp.sendRedirect("/DatabaseUygulama/UserServlet?ok=1");
				break;
			case "/UserServlet?guncelle":
				try {
					updateUser(req, resp);
					logger.info("kayıt başarıyla güncellendi.");
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				break;
				
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String action = req.getRequestURI().substring(req.getContextPath().length());
		if(req.getParameter("ok") != null) {
			String ok = req.getParameter("ok");
			action = action +"?ok="+ ok;
		}
		if(req.getParameter("sil") != null) {
			action = action +"?sil";
		}
		
		if(req.getParameter("edit") != null) {
			action = action +"?edit";
		}
		if(req.getParameter("guncelle") != null) {
			action = action +"?guncelle";
		}
		try {
			switch(action) {
				case "/UserServlet?ok=1":
					listUser(req,resp);
					break;
				case "/UserServlet?sil":
					deleteUser(req, resp);
					logger.info("Kullanıcı silindi");
					break;
				case "/UserServlet?edit":
					showUpdatePage(req, resp);
					break;
				case "/UserServlet?guncelle":
					updateUser(req, resp);
					break;
			}
		}catch(Exception e) {
			
		}
	}
	
	private void listUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException, ClassNotFoundException {
        List<User> listUser = userDao.listUser();
        req.setAttribute("listUsers", listUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("UserList.jsp");
        dispatcher.forward(req, resp);
    }
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ClassNotFoundException {
		String sid = req.getParameter("sil");
		int id = Integer.parseInt(sid);
		userDao.deleteUser(id);
		resp.sendRedirect("/DatabaseUygulama/UserServlet?ok=1");
	}
	
	private void showUpdatePage(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(req.getParameter("edit"));
		User updateUser = userDao.selectUser(id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("UserUpdate.jsp");
		req.setAttribute("user", updateUser);
		dispatcher.forward(req, resp);
	}
	
	private void updateUser(HttpServletRequest req,HttpServletResponse resp) throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(req.getParameter("guncelle"));
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String tckn = req.getParameter("tckn");
		
		User user = User.getInstance();
		user.setID(id);
		user.setName(name);
		user.setSurname(surname);
		user.setTckn(tckn);
		userDao.updateUser(user);
		resp.sendRedirect("/DatabaseUygulama/UserServlet?ok=1");
	}
	
}
