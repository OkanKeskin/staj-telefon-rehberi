package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Dao.LoginDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginDao loginDao = LoginDao.getInstance();
	Logger logger = LogManager.getLogger();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();  
		String kadi = req.getParameter("kadi");
		String sifre = req.getParameter("sifre");
		PrintWriter writer = resp.getWriter();
		boolean giris = false;
		try {
			giris = loginDao.userControl(kadi, sifre);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch blockdi
			e.printStackTrace();
		}
		
		//Giriş Başarılı
		if(giris == true) {
	        session.setAttribute("kadi",kadi);
	        logger.info(kadi+" adlı kullanıcı giriş yaptı");
	        resp.sendRedirect(req.getContextPath() + "/home.jsp");
		}
		else {
			writer.print("yanlış");
		}
	}

}
