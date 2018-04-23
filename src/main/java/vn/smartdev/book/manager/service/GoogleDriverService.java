package vn.smartdev.book.manager.service;

import com.google.api.services.drive.model.File;
import vn.smartdev.book.manager.model.DriveFileType;
import vn.smartdev.book.manager.model.FilesDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;


public interface GoogleDriverService {

    List<FilesDriver> getAllDriverFiles() throws IOException;

    File createFolder(String rootFolderId, String name) throws IOException;

    File uploadFileToGoogleDrive(String rootFolderID, String linkDownload, String name) throws IOException;

    String getDriveFileId(String name, DriveFileType fileType) throws IOException;

    String getRootFolderId() throws IOException;

    File createFolderIntoRootFolder(String name) throws IOException;
}
