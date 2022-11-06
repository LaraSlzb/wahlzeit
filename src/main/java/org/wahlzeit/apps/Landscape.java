package org.wahlzeit.apps;

import org.wahlzeit.main.LandscapeMain;
import org.wahlzeit.services.SysLog;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.File;

public class Landscape extends Wahlzeit{

    /**
     *
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();

            // configures log4j
            String contextPath = sc.getContextPath();
            System.setProperty("contextPath", contextPath);
            SysLog.logSysInfo("context-path", contextPath);

            // determines file system root path to resources
            File dummyFile = new File(sc.getRealPath("dummy.txt"));
            String rootDir = dummyFile.getParent();
            SysLog.logSysInfo("root-directory", rootDir);

            LandscapeMain.getInstance().startUp(false, rootDir);
        } catch (Exception ex) {
            SysLog.logThrowable(ex);
            throw new RuntimeException("End of story!", ex);
        }
    }
}