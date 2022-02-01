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
 * Representa um item de {@link Slide} do tipo imagem.
 */
@Getter @Setter
@Accessors(chain = true)
public class BitmapItem extends SlideItem {

  private String imageName;

  /**
   * Cria uma nova instância de item de slide do tipo imagem, indicando sua posição em nível no
   * slide.
   * 
   * @param level o nível ocupado pelo item
   * @param string o nome do arquivo de imagem
   */
  public BitmapItem(int level, String name) {
    super(level, "image");
    this.setContent(this.imageName = name);
  }

  /**
   * Inicializa um item do tipo imagem no nível mais externo e sem dado de imagem associado.
   */
  public BitmapItem() {
    this(0, "");
  }

  @Override
  public BitmapItem setContent(String value){
    return setImageName(this.content = value);
  }

}
