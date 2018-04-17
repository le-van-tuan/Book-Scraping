package vn.smartdev.book.manager.service.impl;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import vn.smartdev.book.manager.model.FilesDriver;
import vn.smartdev.book.manager.provider.CredentialsGoogleProvider;
import vn.smartdev.book.manager.service.GoogleDriverService;
import vn.smartdev.book.manager.utils.GoogleDriverAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleDriverServiceImpl implements GoogleDriverService {

    static final Log log = LogFactory.getFactory().getInstance(GoogleDriverServiceImpl.class);

    private final Drive googleDriver = CredentialsGoogleProvider.getDriveService();

    /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<FilesDriver> getAllDriverFiles() throws IOException {
        log.info("Getting all driver file...");

        List<FilesDriver> filesDrivers = new ArrayList<>();

        FileList result = googleDriver.files().list().execute();

        List<File> filesFromDriver = result.getFiles();

        filesFromDriver.stream().forEach(file->{
            FilesDriver filesDriver = new FilesDriver();

            filesDriver.setId(file.getId());
            filesDriver.setName(file.getName());

            filesDrivers.add(filesDriver);
        });
        return filesDrivers;
    }

    /**
     *
     * @param name
     * @return
     * @throws IOException
     */
    @Override
    public File createFolder(String name) throws IOException {
        File fileMetadata = new File();

        fileMetadata.setName(name);
        fileMetadata.setMimeType(GoogleDriverAttributes.FOLDER_MINE_TYPE);

        return googleDriver.files().create(fileMetadata)
                .setFields("id")
                .execute();
    }

    @Override
    public InputStream exportFileToInputStream(String fileId) throws IOException {
        return googleDriver.files().export(fileId, GoogleDriverAttributes.PDF_APPLICATION_FILE_TYPE)
                .executeAsInputStream();
    }
}
