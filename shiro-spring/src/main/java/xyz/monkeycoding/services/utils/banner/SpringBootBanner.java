package xyz.monkeycoding.services.utils.banner;

import java.io.PrintStream;


/**
 * @use
 * @project cn.rongcloud.rce-server
 * @author Created by CZN on 2017/7/24.
 */
public class SpringBootBanner implements Banner {

//    private static final String version = PropertiesUtils.loadProps("git.properties").getProperty("git.build.version");
    private static final String version = "1.0.0";
    private static final String[] BANNER = {
            ".-.   .-..-.  .-.",
            "|  `.'  | \\ \\/ / ",
            "| |\\ /| | / /\\ \\ ",
            "`-' ` `-'`-'  `-'",
            ":: RCE Version :: " + (version == null ? "" : " (v" + version + ")")};

    @Override
    public void printBanner(PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
    }
}
