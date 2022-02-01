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

/**
 * Esta classe representa a aplicação do padrão de projeto Null Object
 * na classe Presentation. O objetivo aqui é substituir o uso padrão do 
 * Null e adicionar comportamentos padrões à Presentation em branco.
 */
public class PresentationNullObject extends Presentation {

  public PresentationNullObject(){
    super();
  }

  @Override
  public Presentation append(Slide slide) {
    return new PresentationNullObject();
  }

  @Override
  public Presentation setTemplate(ITemplate template) {
    return this;
  }

  @Override
  public ITemplate getTemplate() {
    return new DefaultTemplate();
  }

  @Override
  public Presentation setTitle(String title) {
    return this;
  }

  @Override
  public String getTitle() {
    return "";
  }

  @Override
  public Presentation setSlides(ArrayList<Slide> slides) {
    return this;
  }

  @Override
  public ArrayList<Slide> getSlides() {
    return new ArrayList<Slide>();
  }
  
}
