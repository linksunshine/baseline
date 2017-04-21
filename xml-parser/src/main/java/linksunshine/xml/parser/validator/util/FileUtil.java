package linksunshine.xml.parser.validator.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ucmed on 2017/4/20.
 */
public class FileUtil {
    /**
     * ��ȡ�ļ���
     *
     * @param f �ļ��������װ������·��
     * @return
     * @throws IOException
     */
    public static InputStream readFile(String f) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResource(f)
                .openStream();
    }

    /**
     * ��ȡ�ļ���
     *
     * @param f �ļ��������װ������·��
     * @return
     * @throws IOException
     */
    public static URL readURL(String f) throws IOException {
        return Thread.currentThread().getContextClassLoader().getResource(f);
    }
}
