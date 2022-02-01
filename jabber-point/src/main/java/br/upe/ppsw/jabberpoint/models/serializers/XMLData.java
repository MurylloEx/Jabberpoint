/**
 * <p>
 * UPE - Campus Garanhuns Curso de Engenharia de Software Disciplina de Padrões de Projeto de
 * Software Copyright 2021 the original author or authors.
 * </p>
 * 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * </p>
 *
 * @author Ian F. Darwin, hbarreiros, MurylloEx
 */

package br.upe.ppsw.jabberpoint.models.serializers;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;

import br.upe.ppsw.jabberpoint.models.items.*;
import br.upe.ppsw.jabberpoint.models.slides.*;
import br.upe.ppsw.jabberpoint.models.interfaces.*;
import br.upe.ppsw.jabberpoint.models.presentations.*;

import lombok.extern.java.Log;

/**
 * Representação de uma estratégia de serialização e
 * desserialização de arquivo XML.
 */
@Log
public class XMLData implements ISavePresentation, ILoadPresentation {

  private void loadSlideItem(Slide slide, Element item) {
    NamedNodeMap attributes = item.getAttributes();
    String leveltext = attributes.getNamedItem("level").getTextContent();
    String type = attributes.getNamedItem("kind").getTextContent();
    int level = Integer.parseInt(leveltext);

    if (type.equals("text")){
      slide.append(new TextItem(level, item.getTextContent()));
    } else 
    if (type.equals("image")){
      slide.append(new BitmapItem(level, item.getTextContent()));
    } 
  }

  /**
   * Carrega o corpo de dados de um elemento XML.
   * 
   * @param element O elemento XML.
   * @param tagName O nome da tag XML.
   * @return O corpo de dados do elemento XML.
   */
  private String readElement(Element element, String tagName) {
    return element.getElementsByTagName(tagName).item(0).getTextContent();
  }

  public String serialize(SlideItem item){
    return String.format("<item kind=\"%s\" level=\"%d\">%s</item>", 
      item.getKind(), item.getLevel(), item.getContent());
  }

  public Presentation load(String fileName) {
    Presentation presentation = new Presentation();
    try {
      Document document = DocumentBuilderFactory
        .newInstance()
        .newDocumentBuilder()
        .parse(new File(fileName));

      Element doc = document.getDocumentElement();
      NodeList slides = doc.getElementsByTagName("slide");
      presentation.setTitle(readElement(doc, "showtitle"));

      for (int slideIdx = 0; slideIdx < slides.getLength(); slideIdx++) {
        Element xmlSlide = (Element)slides.item(slideIdx);
        Slide slide = new Slide().setTitle(readElement(xmlSlide, "title"));
        NodeList slideItems = xmlSlide.getElementsByTagName("item");
        
        for (int itemIdx = 0; itemIdx < slideItems.getLength(); itemIdx++) {
          Element item = (Element)slideItems.item(itemIdx);
          loadSlideItem(slide, item);
        }

        presentation.append(slide);
      }
    } catch (Exception e) {
      log.severe(String.format("Error while loading presentation. %s", e.toString()));
      throw new RuntimeException(e);
    }
    return presentation;
  }

  public void save(Presentation presentation, String fileName) {
    try (PrintWriter xmlWriter = new PrintWriter(new FileWriter(fileName))){
      ArrayList<String> serializedSlides = new ArrayList<>();

      String xmlDocumentModel = 
        "<?xml version=\"1.0\"?>\n" +
        "<!DOCTYPE presentation>\n" +
        "<presentation>\n" +
        "<showtitle>%s</showtitle>\n%s\n" +
        "</presentation>";
  
      String xmlSlideModel = 
        "<slide>\n<title>%s</title>\n%s\n</slide>";

      for (Slide slide : presentation.getSlides()) {
        ArrayList<String> serializedItems = new ArrayList<>();
        for (SlideItem slideItem : ((Slide)slide).getSlideItems()) {
          serializedItems.add(serialize(slideItem));
        }
        String xmlItems = String.join("\n", serializedItems);
        serializedSlides.add(
          String.format(xmlSlideModel, ((Slide)slide).getTitle(), xmlItems));
      }

      String xmlSlides = String.join("\n", serializedSlides);
      xmlWriter.write(String.format(xmlDocumentModel, presentation.getTitle(), xmlSlides));
    }
    catch (Exception e){
      log.severe(String.format("Error while saving file. %s", e.toString()));
      throw new RuntimeException(e);
    }
  }

  public String supportedExtension() {
    return "xml";
  }

}
