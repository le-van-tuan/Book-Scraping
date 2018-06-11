package vn.smartdev.book.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import vn.smartdev.book.manager.entity.BookDetail;
import vn.smartdev.book.manager.entity.ObjectMapper;
import vn.smartdev.book.manager.entity.builder.ObjectMapperBuilder;
import vn.smartdev.book.manager.service.BookService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/admin-console")
    public String showAdminConsole() {
        return "admin-console";
    }

    @RequestMapping(value = "/books")
    public String getAllBooks(ModelMap modelMap){
        List<BookDetail> bookDetails = bookService.getAllBooks();

        modelMap.addAttribute("books", bookDetails);

        writingDataToCsv(bookDetails);

        return "all-books";
    }

    private void writingDataToCsv(List<BookDetail> bookDetails) {
        ICsvBeanWriter beanWriter = null;
        CellProcessor[] cellProcessors = new CellProcessor[]{
                null, // ID
                null, // Book name
                null, // Book Detail ID
                null, // Book reference id
                null, // Author
                null, // publication_date
                null, // pages
                null, // languages,
                null, // file size
                null, // file format
                null, // category
                null, // driver, directory
                null, // book description
                null, // image
                null, // link download
                null, // state
                null, // drive file id
                null // drive file name
        };
        try {
            beanWriter = new CsvBeanWriter(new FileWriter("book-content.csv"), CsvPreference.STANDARD_PREFERENCE);
            String[] header = {
                    "bookId",
                    "bookName",
                    "bookDetailId",
                    "bookReferenceId",
                    "author",
                    "publicationDate",
                    "pages",
                    "languages",
                    "fileSize",
                    "fileFormat",
                    "category",
                    "driveDirectory",
                    "bookDescription",
                    "images",
                    "linkDownload",
                    "state",
                    "driveFileId",
                    "driveFileName"};

            beanWriter.writeHeader(header);

            List<ObjectMapper> objectMappers = new ArrayList<>();

            for(BookDetail bd : bookDetails){
                ObjectMapper objectMapper = ObjectMapperBuilder.anObjectMapper()
                        .withBookId(bd.getBook().getId())
                        .withBookName(bd.getBook().getName())
                        .withBookDetailId(bd.getId())
                        .withBookReferenceId(bd.getBook().getId())
                        .withAuthor(bd.getAuthor())
                        .withPublicationDate(bd.getPublicationDate())
                        .withPages(String.valueOf(bd.getPages()))
                        .withLanguages(bd.getLanguages())
                        .withFileSize(bd.getFileSize())
                        .withFileFormat(bd.getFileFormat())
                        .withCategory(bd.getCategory())
                        .withDriveDirectory(bd.getDriverDirectory())
                        .withBookDescription(bd.getBookDescription())
                        .withImages(bd.getImage())
                        .withLinkDownload(bd.getLinkDownload())
                        .withState(bd.getState().name())
                        .withDriveFileId(bd.getDriveFileId())
                        .withDriveFileName(bd.getDriveFileName())
                        .build();

                objectMappers.add(objectMapper);
            }

            for (ObjectMapper om : objectMappers){
                beanWriter.write(om, header, cellProcessors);
            }
            System.out.println("finish write data to csv file.");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(beanWriter != null){
                try {
                    beanWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
