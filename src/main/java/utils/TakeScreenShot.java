package utils;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenShot {
    public static void main(String[] args) {
        System.out.println( createFileName() );
    }

    public static void takeScreenShot(TakesScreenshot screenshot){
        String fileName = createFileName();
        File strFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(strFile, new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createFileName(){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = formater.format(date);
        String fileName = "test_logs/screenshot/"+currentDate+".png";
        return fileName;

    }
}

//        System.out.println(System.currentTimeMillis());
//        System.out.println(date);
//        System.out.println(currentDate);