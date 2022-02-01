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

package br.upe.ppsw.jabberpoint.views.drawers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;
import br.upe.ppsw.jabberpoint.models.items.*;
import br.upe.ppsw.jabberpoint.views.SlideViewerComponent;

/**
 * Nesta classe foi implementado o padrão Strategy para carregar as
 * estratégias de desenho dos itens de slide e também o desenho dos
 * slides. A responsabilidade de desenhar os elementos filhos do slide
 * foi delegada aos devidos Drawers dos itens de slide.
 * 
 * PRINCÍPIO APLICADO: Inversion of Dependency
 */
public class SlideDrawer {
  
  private HashMap<String, IGenericItemDrawer> supportedDrawers;

  public SlideDrawer(){
    this.supportedDrawers = new HashMap<String, IGenericItemDrawer>();

    IGenericItemDrawer bitmapDrawer = new BitmapItemDrawer();
    IGenericItemDrawer textDrawer = new TextItemDrawer();

    this.supportedDrawers.put(bitmapDrawer.supportedDrawer(), bitmapDrawer);
    this.supportedDrawers.put(textDrawer.supportedDrawer(), textDrawer);
  }

  public List<String> supportedTemplates(){
    return supportedDrawers.keySet().stream().collect(Collectors.toList());
  }

  public IGenericItemDrawer getDrawerFromItem(SlideItem slideItem){
    return this.supportedDrawers.get(slideItem.getKind());
  }

  public void drawPaginator(
    Graphics graphics, 
    Point position, 
    SlideViewerComponent viewer)
  {
    int slidePosition = SlideViewerFrame.getInstance().getSlideController().getSlideCurrentNumber() + 1;
    int numberOfSlides = viewer.getPresentation().getSlides().size();

    String labelText = String.format("Slide %d of %d", slidePosition, numberOfSlides);

    graphics.setFont(new Font("Dialog", Font.BOLD, 10));
    graphics.setColor(Color.black);
    graphics.drawString(labelText, position.x, position.y);
  }

  public void drawSlide(
    Graphics graphics, 
    Rectangle area, 
    SlideViewerComponent viewer)
  {
    SlideItem slideItem = new TextItem(0, viewer.getSlide().getTitle());
    Point position = new Point(area.x, area.y);
    IGenericItemDrawer itemDrawer = getDrawerFromItem(slideItem);
    float scale = viewer.getScale(area);
    
    itemDrawer.drawItem(graphics, slideItem, position, scale, viewer);
    area.y += itemDrawer.getBounding(graphics, slideItem, scale, viewer).height;

    for (int number = 0; number < viewer.getSlide().getSlideItems().size(); number++) {
      slideItem = viewer.getSlide().getSlideItems().get(number);
      itemDrawer = getDrawerFromItem(slideItem);
      position = new Point(area.x, area.y);
      
      itemDrawer.drawItem(graphics, slideItem, position, scale, viewer);
      area.y += itemDrawer.getBounding(graphics, slideItem, scale, viewer).height;
    }
  }

}
