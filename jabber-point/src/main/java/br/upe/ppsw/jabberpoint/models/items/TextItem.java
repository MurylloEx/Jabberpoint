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

import br.upe.ppsw.jabberpoint.models.slides.Slide;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Representa a informação de texto de um {@link Slide} em um {@link Slide}
 */
@Getter @Setter
@Accessors(chain = true)
public class TextItem extends SlideItem {

  private String text;

  /**
   * Cria uma nova instância de item de slide do tipo texto, indicando sua posição em nível no
   * slide.
   * 
   * @param level o nível ocupado pelo item
   * @param string o texto associado
   */
  public TextItem(int level, String text) {
    super(level, "text");
    this.text = text != null ? text : "No Text Given";
    this.text = this.text.length() != 0 ? this.text : " ";
    this.setContent(this.text);
  }

  /**
   * Inicializa um item do tipo texto no nível mais externo.
   */
  public TextItem() {
    this(0, "No Text Given");
  }

  @Override
  public TextItem setContent(String value){
    return setText(this.content = value);
  }

}
