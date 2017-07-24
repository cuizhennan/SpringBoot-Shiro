package xyz.monkeycoding.services.utils.banner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;

/**
 * @use
 * @project cn.rongcloud.rce-server
 * @author Created by CZN on 2017/7/24.
 */
public class BannerApplicationInitializer implements WebApplicationInitializer {
    private Banner banner;

    public BannerApplicationInitializer() {
        banner = new SpringBootBanner();
    }

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        container.addListener("xyz.monkeycoding.services.utils.LifecycleContextListener");
        banner.printBanner(System.out);
    }
}
