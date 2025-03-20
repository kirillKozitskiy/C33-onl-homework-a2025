package org.kirill.spring.springboot.duringLesson_23;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MyDOMParser {
    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            org.w3c.dom.Document document
                    = (org.w3c.dom.Document) builder.parse(new File("src/main/resources/students.xml"));

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();

            NodeList nodeList = root.getElementsByTagName("student");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element student = (Element) node;
                    String id = student.getAttribute("id");
                    System.out.println("\nStudent ID: " + id);

                    String name = student.getElementsByTagName("name").item(0).getTextContent();
                    String age = student.getElementsByTagName("age").item(0).getTextContent();
                    String subject = student.getElementsByTagName("subject").item(0).getTextContent();

                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Subject: " + subject);
                    
                    System.out.println("Root element: " + root.getNodeName());
                }
            }




        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}


