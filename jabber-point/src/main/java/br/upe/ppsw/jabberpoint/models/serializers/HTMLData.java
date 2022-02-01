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
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.upe.ppsw.jabberpoint.models.items.*;
import br.upe.ppsw.jabberpoint.models.slides.*;
import br.upe.ppsw.jabberpoint.models.interfaces.*;
import br.upe.ppsw.jabberpoint.models.presentations.*;

import lombok.extern.java.Log;

/**
 * Representação de uma estratégia de serialização e
 * desserialização de arquivo HTML.
 */
@Log
public class HTMLData implements ISavePresentation, ILoadPresentation {
  
  private void loadSlideItem(Slide slide, Element item) {
    int level = Integer.parseInt(item.attr("level"));
    String type = item.attr("kind");
    if (type.equals("text")){
      slide.append(new TextItem(level, item.text()));
    } else 
    if (type.equals("image")){
      slide.append(new BitmapItem(level, item.text()));
    } 
  }

	public Presentation load(String file){
    Presentation presentation = new Presentation();
    try{
      File fileObj = new File(file);
      Document htmlDoc = Jsoup.parse(fileObj, null);
      Elements title = htmlDoc.head().getElementsByTag("title");
      Elements slides = htmlDoc.body().select("div.slide");
      presentation.setTitle(title.get(0).text());
      for (Element slideData : slides){
        Slide slide = new Slide()
          .setTitle(slideData.select("div.slide-title").get(0).text());
        for (Element element : slideData.select("div.slide-item")){
          loadSlideItem(slide, element);
        }
        presentation.append(slide);
      }
    } catch (Exception e){
      log.severe(String.format("Error while loading HTML file. %s", e.toString()));
      throw new RuntimeException(e);
    }
    return presentation;
  }
  
	public void save(Presentation presentation, String file){
    try{
      File fileObj = new File(file);
      fileObj.createNewFile();
      Document htmlDoc = Jsoup.parse(fileObj, null);
      htmlDoc.head().appendElement("title").text(presentation.getTitle());
      for (Slide slide : presentation.getSlides()){
        Element slideDiv = htmlDoc.body().appendElement("div").attr("class", "slide");
        slideDiv.appendElement("div").attr("class", "slide-title").text(slide.getTitle());
        for (SlideItem item : slide.getSlideItems()){
          slideDiv.appendElement("div").text(item.getContent())
            .attr("class", "slide-item")
            .attr("kind", item.getKind())
            .attr("level", String.valueOf(item.getLevel()));
        }
      }
      try (PrintWriter writer = new PrintWriter(fileObj, "UTF-8")){
        writer.write(htmlDoc.html()) ;
        writer.flush();
      }
    } catch (Exception e){
      log.severe(String.format("Error while saving HTML file. %s", e.toString()));
      throw new RuntimeException(e);
    }
  }
 
  public String supportedExtension(){
    return "html";
  }

}
