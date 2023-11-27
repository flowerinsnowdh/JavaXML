import config.ConfigObject;
import online.flowerinsnow.javaxml.api.XMLDocument;
import online.flowerinsnow.javaxml.api.XMLElement;
import online.flowerinsnow.javaxml.impl.dom.JavaXMLDOM;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ConfigTest {
    @Test
    public void test() throws ParserConfigurationException, IOException, SAXException {
        /*
        XMLDocument document = JavaXMLDOM.parse(ConfigTest.class.getResourceAsStream("/configtest.xml"));
        XMLElement root = document.getRoot();
        XMLElement fieldA = root.getElementNotNull("fieldA");
        XMLElement fieldB = fieldA.getElementNotNull("fieldB");
        XMLElement fieldC = fieldA.getElementNotNull("fieldC");
        Boolean b = fieldA.getElementNotNull("fieldBoolean").getTextAsBoolean();
        System.out.println(fieldB.getTextString());
        System.out.println(fieldC.getTextString());
        System.out.println(b);
         */

        XMLDocument document = JavaXMLDOM.parse(ConfigTest.class.getResourceAsStream("/configtest.xml"));
        XMLElement root = document.getRoot();
        ConfigObject configObject = new ConfigObject();
        root.writeObject(configObject);
        System.out.println(configObject);
    }
}
