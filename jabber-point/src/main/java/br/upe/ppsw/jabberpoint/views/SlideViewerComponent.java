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

package br.upe.ppsw.jabberpoint.views;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;

import br.upe.ppsw.jabberpoint.models.*;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.slides.Slide;
import br.upe.ppsw.jabberpoint.models.slides.SlideNullObject;
import br.upe.ppsw.jabberpoint.views.drawers.SlideDrawer;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Representa o componente de apresentação dos {@link Slide} de uma {@link Presentation}.
 */
@Getter @Setter
@Accessors(chain = true)
public class SlideViewerComponent extends JComponent {
  
  protected Slide slide; 
  protected Presentation presentation;

  public SlideViewerComponent(Presentation presentation) {
    this.presentation = presentation;
    this.slide = new SlideNullObject();
    setBackground(Color.white);
  }

  public void updateSlide(Slide slide) {
    this.slide = slide;
    SlideViewerFrame.getInstance().setTitle(presentation.getTitle());
    repaint();
  }

  public float getScale(Rectangle area) {
    return Math.min(
      ((float)area.width) / ((float)SlideViewerFrame.WIDTH), 
      ((float)area.height) / ((float)SlideViewerFrame.HEIGHT));
  }

  public Point getPointFromScale(Point point, float scale, Style myStyle){
    return new Point(
      point.x + (int)(myStyle.getIndent() * scale), 
      point.y + (int)(myStyle.getLeading() * scale));
  }

  public void paintScreen(Graphics graphics){
    final int paginatorX = 1100;
    final int paginatorY = 20;
    
    SlideDrawer slideDrawer = new SlideDrawer();
    
    Rectangle slideArea = 
      new Rectangle(0, paginatorY, getWidth(), (getHeight() - paginatorY));
    
    slideDrawer.drawPaginator(graphics, new Point(paginatorX, paginatorY), this);
    slideDrawer.drawSlide(graphics, slideArea, this);
  }

  @Override
  public void paintComponent(Graphics graphics) {
    graphics.setColor(Color.white);
    graphics.fillRect(0, 0, getSize().width, getSize().height);
    if (presentation.getSlides().size() > 0){
      paintScreen(graphics);
    }
  }

}
