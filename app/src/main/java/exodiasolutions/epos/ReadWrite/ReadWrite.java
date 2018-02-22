package exodiasolutions.epos.ReadWrite;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sunny on 07-09-2017.
 */

public class ReadWrite {
    public static void writeItems(String s,String data,File dir) {

        File filesDir = dir;
        File todoFile = new File(filesDir,data+".txt");
        try {


            FileUtils.writeStringToFile(todoFile ,s);   // TODO: add depenencies for fill utils
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readItems(String data,File dir)  {

        File filesDir = dir;
        File todoFile = new File(filesDir,data+".txt");
        try {
            return new String(FileUtils.readFileToString(todoFile));
        } catch (IOException e) {
            return "";
        }
    }
}
