package spms.listeners;
/**
 * Created by 1001235 on 15. 5. 24..
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import spms.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.InputStream;

//import javax.annotation.Resources;

@WebListener()
public class ContextLoaderListener
        implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public ContextLoaderListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        try {
            applicationContext = new ApplicationContext();

            String resource = "spms/dao/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);

            ServletContext sc = sce.getServletContext();
            String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));

            applicationContext.prepareObjectsByProperties(propertiesPath);

            applicationContext.prepareObjectsByAnnotation("");

            applicationContext.injectDependency();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
