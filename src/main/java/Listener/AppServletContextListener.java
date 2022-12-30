package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {
	Logger logger = LogManager.getLogger();
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    	logger.info("Server Durduruldu");
    }


    public void contextInitialized(ServletContextEvent sce)  { 
    	logger.info("Server Başlatıldı");
    }
}
