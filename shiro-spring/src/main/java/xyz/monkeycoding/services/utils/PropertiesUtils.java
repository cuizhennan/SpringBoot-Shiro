package xyz.monkeycoding.services.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import com.google.common.collect.Lists;

/**
 * @Ues load 资源文件
 * @Author Created by CZN on 2017/4/14.
 */
public class PropertiesUtils {
//    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static final String DEFAULT_LOAD_FILENAME = "classpath:config.properties";

    public static final String CHECK_FILE_SUBFIX_REGEX = ".*\\.properties$";

    public static final String RUNTIME_SYSTEM_ENV_REGEX = "\\$\\{([^\\}]*)\\}";

    public static final String CHECK_CLASSPATH_REGEX = "classpath[^:]*:([^:]*\\.properties)";

    private static final String CHECK_SYS_FILE_REGEX = "(.*)\\.(?:properties|xml|txt)";

    private static Properties properties;

    static {
        properties = PropHolder.INSTANCE.loadProps(DEFAULT_LOAD_FILENAME);
    }

    public PropertiesUtils() {
    }

    private static class PropHolder {
        private static final PropertiesUtils INSTANCE = new PropertiesUtils();
    }

    /**
     * 默认配置加载
     * 
     * @param key
     * @return
     */
    public static String getKey(String key) {
        if (properties == null) {
            properties = PropHolder.INSTANCE.loadProps(DEFAULT_LOAD_FILENAME);
        }
        return properties.getProperty(key);
    }

    /**
     * 指定文件加载
     * 
     * @param resourceName
     * @return
     */
    public static Properties loadProps(String resourceName) {
        if (!RegularUtil.isMatch(CHECK_FILE_SUBFIX_REGEX, resourceName)) {
//            logger.error("Only support file suffix .properties, But this file suffix error: [{}]", resourceName);
            throw new IllegalArgumentException("Not Support File Type");
        }

        if (resourceName.startsWith("classpath")) {
            resourceName = RegularUtil.regexMatch(CHECK_CLASSPATH_REGEX, resourceName);
        }

        Properties prop = new Properties();
        Resource resource = null;
        // load system file
        if (resourceName.startsWith("file:")) {
            resource = new FileSystemResourceLoader().getResource(resourceName);
        }
        // load classpath file
        else {
            resource = new ClassPathResource(resourceName);
        }
        try {
            InputStream in = resource.getInputStream();
            prop.load(in);
            in.close();
            return prop;
        } catch (IOException ex) {
//            logger.error("load config fill failed: ", ex);
        }
        return null;
    }

}
