package User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Dao.LoginDao;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginDao loginDao = LoginDao.getInstance();
    Logger logger = LogManager.getLogger();


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.setProperty("file.encoding","UTF-8");
		String kadi = req.getParameter("kadi");
		String sifre = req.getParameter("sifre");
		int id = 1;
		if(kadi != "" && sifre != "") {
			Login newLogin = Login.getInstance();
			newLogin.setId(id);
			newLogin.setKadi(kadi);
			newLogin.setSifre(sifre);
			try {
				loginDao.insertLogin(newLogin);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			logger.info(kadi +" adlı kullanıcı kayıt oldu.");
		}
		
		resp.sendRedirect("./girisYap.html");
	}

}
