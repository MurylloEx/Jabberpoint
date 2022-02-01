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

package br.upe.ppsw.jabberpoint.models.presentations;

import java.util.ArrayList;

import br.upe.ppsw.jabberpoint.models.slides.Slide;
import br.upe.ppsw.jabberpoint.models.interfaces.ITemplate;
import br.upe.ppsw.jabberpoint.models.templates.DefaultTemplate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Representa uma Apresentação no sistema. Composta por um título, 
 * um template alterável e uma lista de slides com itens (textos 
 * e imagens). 
 */
@Data
@Accessors(chain = true)
public class Presentation {

  protected String title;
  protected ArrayList<Slide> slides;
  protected transient ITemplate template;

  /**
   * Inicializa uma apresentação.
   */
  public Presentation() {
    this.template = new DefaultTemplate();
    this.slides = new ArrayList<>();
  }
  
  /**
   * Adiciona um {@link Slide} à apresentação.
   * 
   * @param slide A instância do slide a ser adicionada.
   */
  public Presentation append(Slide slide) {
    slides.add(slide);
    return this;
  }
  
  public Presentation setTemplate(ITemplate template){
    this.template = template;
    return this;
  }

}
