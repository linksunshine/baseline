package linksunshine.xml.parser.validator;

import linksunshine.xml.parser.validator.util.FileUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ucmed on 2017/4/20.
 */
public class XmlLoader {
    private static final Logger LOG = Logger.getLogger(XmlLoader.class);
    private String path = "src/main/parse/validator-path";
    private static List<String> APIS = null;

    public Map<String, InputStream> loadFiles() throws DocumentException, IOException {
        LOG.info("api valid path: " + path);
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        SAXReader saxReader = new SAXReader();
        for (File temp : dir.listFiles()) {

        }

        getElements(dir, saxReader, null);

        //getElements(dir, saxReader, null);

        Map<String, InputStream> res = new HashMap<String, InputStream>();

        return null;
    }


    private void getElements(File dir,
                              SAXReader saxReader, String pack) throws DocumentException, IOException {

    }


    private void getElements1(File dir,
                             SAXReader saxReader, String pack) throws DocumentException, IOException {
        APIS = new ArrayList<String>();
        for (File temp : dir.listFiles()) {
            if (temp.isDirectory()) {
                getElements1(temp, saxReader,
                        pack == null ? temp.getName() : pack + File.separator
                                + temp.getName());
                continue;
            }
            if (!temp.getName().endsWith(".xml")) {
                continue;
            }
            LOG.info("execute file:" + temp.getPath());
            Document doc = saxReader.read(temp);
            Element r = doc.getRootElement();
            String name = temp.getName();
            if (APIS.contains(name)) {
                throw new RuntimeException("warn :  there is api with same name -- " + name);
            }

            APIS.add(name);
            FileUtil.readFile(temp.getPath());
        }
    }
}
