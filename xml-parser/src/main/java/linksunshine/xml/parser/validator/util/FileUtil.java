package linksunshine.xml.parser.validator.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ucmed on 2017/4/20.
 */
public class FileUtil {
    /**
     * 读取文件流
     *
     * @param f 文件相对于类装载器的路径
     * @return
     * @throws IOException
     */
    public static InputStream readFile(String f) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResource(f)
                .openStream();
    }

    /**
     * 读取文件流
     *
     * @param f 文件相对于类装载器的路径
     * @return
     * @throws IOException
     */
    public static URL readURL(String f) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResource(f);
    }
}
