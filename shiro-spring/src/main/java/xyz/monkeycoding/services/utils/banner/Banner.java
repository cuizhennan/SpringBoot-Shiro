package xyz.monkeycoding.services.utils.banner;

import java.io.PrintStream;

/**
 * @use 设置启动banner
 * @project cn.rongcloud.rce-server
 * @author Created by CZN on 2017/7/24.
 */
public interface Banner {
    /**
     * Print the banner to the specified print stream.
     * 
     * @param out
     *            the output print stream
     */
    void printBanner(PrintStream out);
}
