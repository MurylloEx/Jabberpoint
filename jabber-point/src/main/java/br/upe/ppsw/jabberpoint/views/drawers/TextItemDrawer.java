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

import java.util.List;
import java.util.ArrayList;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.text.AttributedString;
import java.awt.geom.Rectangle2D;

import br.upe.ppsw.jabberpoint.models.*;
import br.upe.ppsw.jabberpoint.models.interfaces.*;
import br.upe.ppsw.jabberpoint.models.items.*;
import br.upe.ppsw.jabberpoint.views.*;

/**
 * Esta classe implementa o IGenericItemDrawer que é a interface
 * que determina como um "pintor" de itens deve funcionar. A 
 * responsabilidade desta classe é desenhar na tela um item de
 * texto do tipo TEXT e esta é a sua única função.
 * 
 * PRINCÍPIO APLICADO: Inversion of Dependency
 */
public class TextItemDrawer implements IGenericItemDrawer{

  public TextItemDrawer(){
    super();
  }

  public Rectangle getBounding(
    Graphics graphics, 
    SlideItem textItem, 
    float scale,
    SlideViewerComponent viewer)
  {
    ITemplate template = viewer.getPresentation().getTemplate();
    Style style = template.getStyle(textItem.getLevel());
    Graphics2D g2d = (Graphics2D)graphics;

    List<TextLayout> layouts = new ArrayList<TextLayout>();
    AttributedString attrStr = getAttributedString(textItem, style, scale);
    FontRenderContext fontRender = g2d.getFontRenderContext();
    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), fontRender);
    
    float wrappingWidth = (SlideViewerFrame.WIDTH - style.getIndent()) * scale;
    while (measurer.getPosition() < textItem.getContent().length()) {
      layouts.add(measurer.nextLayout(wrappingWidth));
    }

    int xsize = 0;
    int ysize = (int)(style.getLeading() * scale);
    
    for (TextLayout layout : layouts){
      Rectangle2D bounds = layout.getBounds();
      if (bounds.getWidth() > xsize)
        xsize = (int)bounds.getWidth();
      if (bounds.getHeight() > 0)
        ysize += bounds.getHeight();
      ysize += layout.getLeading() + layout.getDescent();
    }
    
    return new Rectangle((int)(style.getIndent() * scale), 0, xsize, ysize);
  }

  public AttributedString getAttributedString(SlideItem textItem, Style style, float scale) {
    AttributedString attrStr = new AttributedString(textItem.getContent());
    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, textItem.getContent().length());
    return attrStr;
  }

  public void drawItem(
    Graphics graphics, 
    SlideItem textItem, 
    Point position, 
    float scale,
    SlideViewerComponent viewer) 
  {
    if (textItem.getContent().length() != 0) {
      ITemplate template = viewer.getPresentation().getTemplate();
      Style textStyle = template.getStyle(textItem.getLevel());
      Graphics2D graphics2d = (Graphics2D)graphics;
      List<TextLayout> layouts = new ArrayList<TextLayout>();
      AttributedString attrStr = getAttributedString(textItem, textStyle, scale);
      FontRenderContext fontRender = graphics2d.getFontRenderContext();
      LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), fontRender);
      
      float wrappingWidth = (SlideViewerFrame.WIDTH - textStyle.getIndent()) * scale;
      while (measurer.getPosition() < textItem.getContent().length()) {
        layouts.add(measurer.nextLayout(wrappingWidth));
      }

      Point itemPoint = viewer.getPointFromScale(new Point(position.x, position.y), scale, textStyle);
      graphics2d.setColor(textStyle.getColor());
      
      for (TextLayout layout : layouts){
        itemPoint.y += layout.getAscent();
        layout.draw(graphics2d, itemPoint.x, itemPoint.y);
        itemPoint.y += layout.getDescent();
      }
    }
  }

  public String supportedDrawer() {
    return "text";
  }

}
