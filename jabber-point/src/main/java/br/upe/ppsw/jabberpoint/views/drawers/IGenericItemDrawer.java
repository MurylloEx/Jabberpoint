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

import br.upe.ppsw.jabberpoint.models.items.SlideItem;
import br.upe.ppsw.jabberpoint.views.SlideViewerComponent;

/**
 * Esta é a interface que determina como um pintor de itens
 * deve ser e quais métodos deve possuir, sendo o principal
 * responsável pela inversão de dependência aplicada utilizando
 * o padrão strategy no SlideDrawer. Sua função é servir como
 * um instrumento para que o SlideDrawer utilize para pintar
 * elementos na tela sem se preocupar com detalhes internos
 * de outras classes.
 */
public interface IGenericItemDrawer {

  Rectangle getBounding(
    Graphics graphics, 
    SlideItem item, 
    float scale,
    SlideViewerComponent viewer);

  void drawItem(
    Graphics graphics, 
    SlideItem item, 
    Point position, 
    float scale, 
    SlideViewerComponent viewer);

  String supportedDrawer();
  
}
