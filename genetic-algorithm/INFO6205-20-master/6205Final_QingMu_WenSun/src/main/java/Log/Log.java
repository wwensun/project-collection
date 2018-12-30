/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;  

/**
 *
 * @author jenni
 */
public class Log {
	private static Logger logger = Logger.getLogger(Log.class.getName());  
    static {  
        try {  
            FileHandler fileHandler = null;  
  
            fileHandler = new FileHandler("log" + System.currentTimeMillis() + ".log");
  
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            fileHandler.setFormatter(new Formatter() {  
                public synchronized String format(LogRecord record) {  
                    String dateFormat = sdf.format(record.getMillis());  
                    String source;  
                    if (record.getSourceClassName() != null) {  
                        source = record.getSourceClassName();  
                        if (record.getSourceMethodName() != null) {  
                            source += " " + record.getSourceMethodName();  
                        }  
                    } else {  
                        source = record.getLoggerName();  
                    }  
                    String message = formatMessage(record);  
                    String throwable = "";  
                    if (record.getThrown() != null) {  
                        StringWriter sw = new StringWriter();  
                        PrintWriter pw = new PrintWriter(sw);  
                        pw.println();  
                        record.getThrown().printStackTrace(pw);  
                        pw.close();  
                        throwable = sw.toString();
                    }  
                    StringBuilder sb = new StringBuilder();  
                    sb = sb.append(dateFormat).append("  ").append("class:").append(source).append(" ").append(record.getLoggerName()).append(" ")  
                            .append(record.getLevel().getLocalizedName()).append(" ").append(record.getMessage())  
                            .append(" ").append(throwable).append("\r\n");  
                    return String.format("%-8s", sb.toString());  
                }  
            });  
            logger.addHandler(fileHandler);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void warn(String message) {  
        logger.log(Level.WARNING, message);  
    }  
  
    public static void info(String message) {  
        logger.info(message);  
    }  
}
