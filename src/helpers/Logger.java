package helpers;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String folder = "./logs/";

    public static void log(String text){
        log(text, TipoLog.INFO);
    }

    public static void log(Exception e){
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        log(errors.toString(), TipoLog.ERROR);
    }

    public static void log(String text, TipoLog tipo){
        String fileName = "log_"+getCurrentDate()+".txt";
        File logFile = new File(folder+fileName);

        try {
            Writer writer = new OutputStreamWriter(
                    new FileOutputStream(logFile, true), "UTF-8");
            BufferedWriter out = new BufferedWriter(writer);

            out.write("[" + (String.valueOf(tipo)) + "] " + getCurrentDateTime() + " " + text);
            out.newLine();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getCurrentDate(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private static String getCurrentDateTime(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
