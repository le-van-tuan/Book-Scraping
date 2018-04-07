package vn.smartdev.book.manager.provider;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import vn.smartdev.book.manager.exception.TechnicalException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
public class CredentialsGoogleProvider {

    static final Log log = LogFactory.getFactory().getInstance(CredentialsGoogleProvider.class);

    private static final String APPLICATION_NAME = "Books-Downloading-Manager";

    private static final String SYSTEM_ROOT_DIRECTORY = "user.home";

    private static final String CHILD_ROOT_DIRECTORY = "Desktop/books";

    private static final String CLIENT_SECRET_DIRECTORY = "/client_secret.json";

    private static File DATA_STORE_DIR;

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static JsonFactory JSON_FACTORY;

    private static HttpTransport HTTP_TRANSPORT;

    private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE,
            DriveScopes.DRIVE_FILE,
            DriveScopes.DRIVE_METADATA_READONLY,
            DriveScopes.DRIVE_METADATA,
            DriveScopes.DRIVE_APPDATA);

    static {
        try {
            log.info("Loading instance for prepare authenticate on google APIs.");
            JSON_FACTORY = JacksonFactory.getDefaultInstance();
            DATA_STORE_DIR = new File(System.getProperty(SYSTEM_ROOT_DIRECTORY),CHILD_ROOT_DIRECTORY);
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Exception ex) {
            log.error("Could not load instance : " + ex.getMessage());
            throw new TechnicalException(ex);
        }
    }

    public static Credential authorize() {
        InputStream in = CredentialsGoogleProvider.class.getResourceAsStream(CLIENT_SECRET_DIRECTORY);
        GoogleClientSecrets clientSecrets;
        try {
            clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                            .setDataStoreFactory(DATA_STORE_FACTORY)
                            .setAccessType("offline")
                            .build();

            Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

            log.info("Credentials save to : " + DATA_STORE_DIR.getAbsolutePath());
            return credential;
        } catch (IOException e) {
            log.error("Could not authorize : " + e.getStackTrace());
            throw new TechnicalException(e);
        }
    }

    public static Drive getDriveService() {
        log.info("Getting driver service...");
        Credential credential = authorize();
        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
