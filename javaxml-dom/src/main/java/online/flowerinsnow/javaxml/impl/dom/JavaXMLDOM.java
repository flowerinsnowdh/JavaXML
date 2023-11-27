package online.flowerinsnow.javaxml.impl.dom;

import online.flowerinsnow.javaxml.api.XMLDocument;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public abstract class JavaXMLDOM {
    public static XMLDocument wrap(Document document) {
        return new DOMXMLDocument(document);
    }

    public static XMLDocument parse(File file) throws ParserConfigurationException, IOException, SAXException {
        return wrap(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file));
    }

    public static XMLDocument parse(InputStream input) throws ParserConfigurationException, IOException, SAXException {
        return wrap(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input));
    }
}
