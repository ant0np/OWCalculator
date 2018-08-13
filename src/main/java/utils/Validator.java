package utils;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validator {

    private static final Logger logger = Logger.getLogger(Validator.class.getName());

    private static final File SCHEMA = new File(System.getProperty("user.dir") +
            "\\src\\main\\resources\\schemas\\Calculator.xsd");

    public static boolean validate(File xml) {
        try {
            SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(SCHEMA)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (SAXException | IOException e ) {
            logger.log(Level.SEVERE, e.toString(), e);
        }
        return true;
    }
}
