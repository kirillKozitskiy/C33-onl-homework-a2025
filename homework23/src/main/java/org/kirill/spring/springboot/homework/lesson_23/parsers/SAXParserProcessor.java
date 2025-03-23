package org.kirill.spring.springboot.homework.lesson_23.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SAXParserProcessor {

    private final static String outputFile
            = "src/main/resources/firstName_lastName_title.txt";


    public SAXParserProcessor() {
        try(BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(outputFile))) {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                private String currentElement = "";
                private StringBuilder stringBuilder = new StringBuilder();
                private int lineNumber = 1;


                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentElement = qName;
                    stringBuilder.setLength(0);
                    try
                    {
                        if (qName.equals("author")) {
                            writer.write("Author");
                            writer.newLine();
                        }else if (qName.equals("lines")) {
                            writer.write("lines");
                            writer.newLine();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    stringBuilder.append(ch, start, length);
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {

                    String content = stringBuilder.toString().trim();

                    try {
                        if (!content.isEmpty()) {
                            switch (qName) {
                                case "firstName":
                                    writer.write("First Name: " + content);
                                    writer.newLine();
                                    break;
                                case "nationality":
                                    writer.write("Nationality: " + content);
                                    writer.newLine();
                                    break;
                                case "yearOfBirth":
                                    writer.write("Year Of Birth: " + content);
                                    writer.newLine();
                                    break;
                                case "yearOfDeath":
                                    writer.write("Year Of Death: " + content);
                                    writer.newLine();
                                    break;
                                case "title":
                                    writer.write("Title: " + content);
                                    writer.newLine();
                                    break;
                                case "source":
                                    writer.write("Source: " + content);
                                    writer.newLine();
                                    break;
                                case "line":
                                    writer.write("Line " + (lineNumber++) +": "+ content);
                                    writer.newLine();
                                    break;
                                default:
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            saxParser.parse("src/main/resources/lesson_23.xml", handler);
            System.out.println("SAX parser\nData successfully written to " + outputFile);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
