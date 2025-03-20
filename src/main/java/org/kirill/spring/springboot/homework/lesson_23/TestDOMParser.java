package org.kirill.spring.springboot.homework.lesson_23;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TestDOMParser {
//
    private final static String outputFile
            = "src/main/java/org/kirill/spring/springboot/homework/lesson_23/firstName_lastName_title.txt";

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("src/main/resources/lesson_23.xml");
            document.getDocumentElement().normalize();

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                Element root = document.getDocumentElement();
                System.out.println("Root element "+root.getNodeName());

                NodeList authorNode = document.getElementsByTagName("author");

                if (authorNode.getLength() > 0) {
                    Node author = authorNode.item(0);

                    if (author.getNodeType() == Node.ELEMENT_NODE) {
                        Element authorElement = (Element) author;
                        String firstName = authorElement.getElementsByTagName("firstName").item(0).getTextContent();
                        String lastName = authorElement.getElementsByTagName("lastName").item(0).getTextContent();
                        String nationality = authorElement.getElementsByTagName("nationality").item(0).getTextContent();
                        String yearOfBirth = authorElement.getElementsByTagName("yearOfDeath").item(0).getTextContent();

                        writer.write("Author details: ");
                        writer.newLine();
                        writer.write("First Name: "+firstName);
                        writer.newLine();
                        writer.write("Last Name: "+lastName);
                        writer.newLine();
                        writer.write("Nationality: "+nationality);
                        writer.newLine();
                        writer.write("Year of Birth: "+yearOfBirth);
                        writer.newLine();
                        writer.write("Year of Death: "+yearOfBirth);
                        writer.newLine();
                    }
                }

                NodeList lineNode = document.getElementsByTagName("line");
                writer.write("Sonnet Lines: ");
                writer.newLine();
                for (int i = 0; i < lineNode.getLength(); i++) {
                    Node line = lineNode.item(i);
                    if (line.getNodeType() == Node.ELEMENT_NODE) {
                        writer.write("Line " + (i + 1) + ": "+line.getTextContent());
                        writer.newLine();
                    }
                }
                writer.write("Content has been successfully written to the file.");
                System.out.println("Data successfully written to " + outputFile);
            }catch (Exception e) {
                e.printStackTrace();
            }
            //












        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




    }

}
