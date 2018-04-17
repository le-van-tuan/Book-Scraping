package vn.smartdev.book.manager.service;

import com.google.api.services.drive.model.File;
import vn.smartdev.book.manager.model.FilesDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public interface GoogleDriverService {

    List<FilesDriver> getAllDriverFiles() throws IOException;

    File createFolder(String name) throws IOException;

    InputStream exportFileToInputStream(String fileId) throws IOException;
}
