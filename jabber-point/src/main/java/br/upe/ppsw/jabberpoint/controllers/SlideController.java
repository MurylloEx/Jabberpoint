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

package br.upe.ppsw.jabberpoint.controllers;

import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.slides.Slide;
import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;
import br.upe.ppsw.jabberpoint.views.SlideViewerComponent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Esta classe representa um controller de um Slide, sendo
 * utilizada para avançar, voltar ou saltar de slide na
 * apresentação atual.
 */
@Data
@Accessors(chain = true)
public class SlideController {

  private int slideCurrentNumber;

  public SlideController(){
    this.slideCurrentNumber = 0;
  }

  private SlideViewerComponent getViewerComponent(){
    return SlideViewerFrame.getInstance().getSlideViewerComponent();
  }

  private Presentation getPresentation(){
    return getViewerComponent().getPresentation();
  }

  /**
   * Atualiza o slide atual da apresentação e atualiza o componente de visualização de dados com as informações a serem atualizadas na tela.
   * 
   * @param number O número do slide.
   */
  public void setSlideNumber(int number) {
    slideCurrentNumber = number;
    getViewerComponent().updateSlide(getCurrentSlide());
  }
  
  /**
   * Move a apresentação para o slide anterior, caso o slide atual não seja o primeiro.
   */
  public void previousSlide() {
    if (slideCurrentNumber > 0) {
      setSlideNumber(slideCurrentNumber - 1);
    }
  }
  
  /**
   * Move a apresentação para o próximo slide, caso o slide atual não seja o último.
   */
  public void nextSlide() {
    if (slideCurrentNumber < (getPresentation().getSlides().size() - 1)) {
      setSlideNumber(slideCurrentNumber + 1);
    }
  }
  
  /**
   * Move a apresentação para o primeiro slide.
   */
  public void firstSlide() {
    setSlideNumber(0);
  }
  
  /**
   * Recupera o slide atual.
   * 
   * @return O {@link Slide} atual ou <code>null</code> caso não esteja sendo exibida alguma apresentação.
   */
  public Slide getCurrentSlide() {
    return getPresentation().getSlides().get(slideCurrentNumber);
  }
  
}
