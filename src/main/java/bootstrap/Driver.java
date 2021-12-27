package bootstrap;

import org.apache.log4j.*;
import org.slf4j.LoggerFactory;

public class Driver {
    static  Logger logger= (Logger) LoggerFactory.getLogger(Driver.class);
    public static void main(String[] args){
        System.out.println("Hello..");
        configureLogging("intro.log", "INFO");
        logger.info("Project started.");
    }
    public static String configureLogging(String logFile, String logLevel) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();
        switch (logLevel) {
            case "DEBUG": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR": {
                dailyRollingFileAppender.setThreshold(Level.toLevel((Priority.ERROR_INT)));
            }
            default: {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }

        System.out.println("Log files written out at " + logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();

    }
}
