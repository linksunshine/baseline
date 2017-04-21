package linksunshine.xml.parser.validator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

/**
 * Created by ucmed on 2017/4/20.
 */
public class SchemaLoader {
    private static final Logger LOG = Logger.getLogger(SchemaLoader.class);
    SAXReader saxReader = new SAXReader();
    private String api_xsd = "META-INF/schema/1.0.xsd";


    public void validateAPIFile(String fileName, InputStream file) {
        try {
            if (saxReader == null)
                saxReader = new SAXReader();
            Document doc = saxReader.read(file);
            validateAPIFile(doc);
        } catch (DocumentException e) {
            LOG.error("api validator parse file error, file:" + fileName);
        } catch (Exception e) {
            LOG.error(fileName + e.getMessage());
        }
    }


    public void validateAPIFile(Document doc) throws Exception {
        SAXParser sAXParser;
        try {
            SAXParserFactory sAXParserFactory = SAXParserFactory.newInstance();
            sAXParser = sAXParserFactory.newSAXParser();
            sAXParserFactory.setValidating(true);
            sAXParser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource", api_xsd);
            sAXParser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            XMLErrorHandler xMLErrorHandler = new XMLErrorHandler();
            SAXValidator sAXValidator = new SAXValidator(sAXParser.getXMLReader());
            sAXValidator.setErrorHandler(xMLErrorHandler);
            sAXValidator.validate(doc);
            if (xMLErrorHandler.getErrors().hasContent()) {
                LOG.error("xml parse error:"
                        + xMLErrorHandler.getErrors().getStringValue());
                throw new Exception(" xml parse error");
            }
        } catch (ParserConfigurationException e) {
            LOG.error("xml definition parse error:" + e.getMessage());
        } catch (SAXException e) {
            LOG.error("xml definition parse error:" + e.getMessage());
        }
    }


}
