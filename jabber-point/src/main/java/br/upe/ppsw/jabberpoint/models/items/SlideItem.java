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

package br.upe.ppsw.jabberpoint.models.items;

import lombok.Data;
import lombok.experimental.Accessors;
import br.upe.ppsw.jabberpoint.models.Style;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.slides.Slide;

/**
 * Representação abstrata de um item de {@link Slide} em uma {@link Presentation}
 */
@Data
@Accessors(chain = true)
public class SlideItem {

  protected int level;
  protected String kind;
  protected String content;

  /**
   * Cria uma nova instância de item em um slide, o {@link Style} aplicado está direatmente
   * relacionado a esta posição.
   * 
   * @param level
   */
  public SlideItem(int level, String kind) {
    this.level = level;
    this.kind = kind;
  }

  /**
   * Cria um {@link Slide} aplicando o nível mais externo.
   */
  public SlideItem(String kind) {
    this(0, kind);
  }
  
}
