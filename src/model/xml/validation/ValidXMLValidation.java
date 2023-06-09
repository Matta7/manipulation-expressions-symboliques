package model.xml.validation;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Class that valid an XML using an XML Schema.
 */
public class ValidXMLValidation extends AbstractValidation {

    @Override
    public boolean isValid() {
        String xsdPath = "xml/schema/expression.xsd";

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(getFileName())));
        } catch (IOException | SAXException e) {
            throw new IllegalArgumentException("File is not valid.\n");
        }

        return super.isValid();
    }
}
