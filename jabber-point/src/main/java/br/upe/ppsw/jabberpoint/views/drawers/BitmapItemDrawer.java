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

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import br.upe.ppsw.jabberpoint.models.*;
import br.upe.ppsw.jabberpoint.models.interfaces.*;
import br.upe.ppsw.jabberpoint.models.items.*;
import br.upe.ppsw.jabberpoint.views.SlideViewerComponent;

import lombok.extern.java.Log;

import org.springframework.util.ResourceUtils;

/**
 * Esta classe implementa o IGenericItemDrawer que é a interface
 * que determina como um "pintor" de itens deve funcionar. A 
 * responsabilidade desta classe é desenhar na tela um item de
 * slide do tipo BITMAP e esta é a sua única função.
 * 
 * PRINCÍPIO APLICADO: Inversion of Dependency
 */
@Log
public class BitmapItemDrawer implements IGenericItemDrawer {
  
  public BitmapItemDrawer(){
    super();
  }

  public Rectangle getBounding(
    Graphics graphics, 
    SlideItem bitmapItem, 
    float scale,
    SlideViewerComponent viewer)
  {
    try {
      ITemplate template = viewer.getPresentation().getTemplate();
      Style style = template.getStyle(bitmapItem.getLevel());

      BufferedImage bufferedImage = ImageIO.read(
        ResourceUtils.getFile(bitmapItem.getContent()).getAbsoluteFile());
      
      return new Rectangle(
        (int)(style.getIndent() * scale), 0,
        (int)(bufferedImage.getWidth(viewer) * scale),
        ((int)(style.getLeading() * scale)) + 
        (int)(bufferedImage.getHeight(viewer) * scale));
    } catch (Exception e) {
      log.severe(String.format("Exception: %s", e.toString()));
      throw new RuntimeException(e);
    }
  }

  public void drawItem(
    Graphics graphics, 
    SlideItem bitmapItem, 
    Point position, 
    float scale, 
    SlideViewerComponent viewer) 
  {
    try {
      ITemplate template = viewer.getPresentation().getTemplate();
      Style bitmapStyle = template.getStyle(bitmapItem.getLevel());
      Point itemPoint = viewer.getPointFromScale(new Point(position.x, position.y), scale, bitmapStyle);

      BufferedImage bufferedImage = ImageIO.read(
        ResourceUtils.getFile(bitmapItem.getContent()).getAbsoluteFile());

      graphics.drawImage(bufferedImage, itemPoint.x, itemPoint.y, 
        (int)(bufferedImage.getWidth(viewer) * scale),
        (int)(bufferedImage.getHeight(viewer) * scale), viewer);
    } catch (Exception e) {
      log.severe(String.format("Exception: %s", e.toString()));
      throw new RuntimeException(e);
    }
  }

  public String supportedDrawer() {
    return "image";
  }

}
