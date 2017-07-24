package xyz.monkeycoding.services.utils;

import java.io.PrintStream;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.impl.LogFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleContextListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(LifecycleContextListener.class);

    private static final TimeZone TZ_UTC = TimeZone.getTimeZone("UTC");

    public LifecycleContextListener() {
        setPrintStream();
    }

    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.info("current timezone: {}", TimeZone.getDefault().toString());
        if (!TimeZone.getDefault().equals(TZ_UTC)) {
            System.setProperty("user.timezone", "UTC");
            TimeZone.setDefault(TZ_UTC);
            LOGGER.info("change timezone to UTC: {}", TimeZone.getDefault().toString());
        }

        LOGGER.info("start initialize the lifecycle listener.");
    }

    private void log(Object info) {
        LogFactoryImpl.getLog(getClass()).info(info);
    }

    void setPrintStream() {
        PrintStream printStream = new PrintStream(System.out) {
            public void println(boolean x) {
                log(Boolean.valueOf(x));
            }

            public void println(char x) {
                log(Character.valueOf(x));
            }

            public void println(char[] x) {
                log(x == null ? null : new String(x));
            }

            public void println(double x) {
                log(Double.valueOf(x));
            }

            public void println(float x) {
                log(Float.valueOf(x));
            }

            public void println(int x) {
                log(Integer.valueOf(x));
            }

            public void println(long x) {
                log(x);
            }

            public void println(Object x) {
                log(x);
            }

            public void println(String x) {
                log(x);
            }
        };
        System.setOut(printStream);
        System.setErr(printStream);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }

}