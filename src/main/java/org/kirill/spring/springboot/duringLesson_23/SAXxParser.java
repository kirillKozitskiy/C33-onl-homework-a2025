package org.kirill.spring.springboot.duringLesson_23;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXxParser {
    public static void main(String[] args) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            String xmlFile = "src/main/resources/students.xml";

            saxParser.parse(xmlFile, new StudentHandler());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class StudentHandler extends DefaultHandler {
    private String currentId;
    private String currentElement;
    private StringBuilder text;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        text = new StringBuilder();

        if(qName.equalsIgnoreCase("student")) {
            currentId = attributes.getValue("id");
            System.out.println(currentId);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      if(qName.equalsIgnoreCase("name")) {
          System.out.println(text.toString().trim());
      }
      else if(qName.equalsIgnoreCase("age")) {
          System.out.println(text.toString().trim());
      }
      else if(qName.equalsIgnoreCase("subject")) {
          System.out.println(text.toString().trim());
      }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      if(currentId != null) {
          text.append(ch, start, length);
      }
    }


}
