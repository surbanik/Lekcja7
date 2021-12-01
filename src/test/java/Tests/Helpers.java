package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Helpers {

    final static String DOWNLOAD_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "downloadedFiles";

    public static int countFilesInDownloadDirectory(){
        int count=0;
        try (Stream<Path> files = Files.list(Paths.get(DOWNLOAD_DIR))) {
            count = (int) files.count();

        }catch (java.io.IOException e){
            System.out.println(e.getMessage());
        }
        return count;
    }

    public static List<String> listFilesInDownloadDirectory() {
        return Stream.of(new File(DOWNLOAD_DIR).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

}
