package org.wahlzeit.main;

import org.wahlzeit.agents.AgentManager;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.model.landscape.LandscapePhoto;
import org.wahlzeit.model.landscape.LandscapePhotoManager;
import org.wahlzeit.model.landscape.LandscapePhotoFactory;

import java.io.File;
import java.io.FileFilter;
import java.sql.SQLException;

public class LandscapeMain extends ServiceMain{
    /**
     *
     */
    protected static LandscapeMain instance = new LandscapeMain();

    /**
     *
     */
    public static LandscapeMain getInstance() {
        return instance;
    }
    public void setUpDatabase() throws SQLException {
        runScript("CreateTablesLandscape.sql");
    }

    /**
     *
     */
    public void tearDownDatabase() throws SQLException {
        runScript("DropTablesLandscape.sql");
    }
    public void startUp(boolean inProduction, String rootDir) throws Exception {
        isInProduction = inProduction;

        super.startUp(rootDir);

        if (!hasGlobals()) {
            tearDownDatabase();
            setUpDatabase();
        }

        loadGlobals();

        LandscapePhotoFactory.initialize();

        configureWebPartTemplateService();
        configureWebPartHandlers();
        configureLanguageModels();

        AgentManager am = AgentManager.getInstance();
        am.startAllThreads();
    }

    protected void createUser(String userName, String password, String emailAddress, String photoDir) throws Exception {
        UserManager userManager = UserManager.getInstance();
        long confirmationCode = userManager.createConfirmationCode();
        User user = new User(userName, password, emailAddress, confirmationCode);
        userManager.addUser(user);

        LandscapePhotoManager photoManager = LandscapePhotoManager.getInstance();
        File photoDirFile = new File(photoDir);
        FileFilter photoFileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().endsWith(".jpg");
            }
        };

        File[] photoFiles = photoDirFile.listFiles(photoFileFilter);
        for (int i = 0; i < photoFiles.length; i++) {
            LandscapePhoto newPhoto = photoManager.createPhoto(photoFiles[i]);
            user.addPhoto(newPhoto);
        }
    }
}