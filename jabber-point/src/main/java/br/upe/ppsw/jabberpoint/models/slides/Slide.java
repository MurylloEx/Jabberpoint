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

package br.upe.ppsw.jabberpoint.models.slides;

import java.util.ArrayList;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.java.Log;

import org.springframework.util.ResourceUtils;

import br.upe.ppsw.jabberpoint.models.items.BitmapItem;
import br.upe.ppsw.jabberpoint.models.items.SlideItem;
import br.upe.ppsw.jabberpoint.models.items.TextItem;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;

/**
 * Representa os dados de um slide de uma {@link Presentation} composta por uma
 * lista de {@link SlideItem} e um {@link TextItem}.
 */
@Log
@Data
@Accessors(chain = true)
public class Slide {

  protected String title;
  protected ArrayList<SlideItem> slideItems;

  /**
   * Cria uma nova instância do slide inicializando a lista de itens.
   */
  public Slide() {
    this.slideItems = new ArrayList<SlideItem>();
  }

  /**
   * Adiciona um item ao slide.
   * 
   * @param anItem A instância de {@link SlideItem} contendo os dados do item de
   *               slide.
   */
  public Slide append(SlideItem anItem) {
    this.slideItems.add(anItem);
    return this;
  }
  
  /**
   * Adiciona um texto ao Slide.
   */
  public Slide appendText(int level, String text) {
    append(new TextItem(level, text));
    return this;
  }

  /**
   * Adiciona uma imagem ao Slide.
   */
  public Slide appendImage(int level, String imageName) {
    try{
      append(new BitmapItem(level, ResourceUtils
        .getFile(imageName)
        .getAbsolutePath()));
    } catch(Exception e){
      log.severe(String.format("Exception: %s", e.toString()));
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * Recupera um item de slide de acordo com a sua posição.
   * 
   * @param number A posição do item no {@link Slide}
   * @return O {@link SlideItem} na posição informada.
   * @throws ArrayIndexOutOfBoundsException caso a posição informada não seja
   *                                        válida.
   */
  public SlideItem getSlideItem(int number) {
    return slideItems.get(number);
  }

}
