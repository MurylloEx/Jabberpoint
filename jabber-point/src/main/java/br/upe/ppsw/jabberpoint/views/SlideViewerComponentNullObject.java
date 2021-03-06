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

package br.upe.ppsw.jabberpoint.views;

import java.awt.Graphics;

import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.presentations.PresentationNullObject;
import br.upe.ppsw.jabberpoint.models.slides.Slide;

public class SlideViewerComponentNullObject extends SlideViewerComponent {

  public SlideViewerComponentNullObject(Presentation presentation) {
    super(presentation);
  }

  @Override
  public void updateSlide(Slide slide) {
    //Comportamento padrão: não fazer nada.
  }

  @Override
  public void paintComponent(Graphics g) {
    //Comportamento padrão: não fazer nada.
  }

  @Override
  public Presentation getPresentation() {
    return new PresentationNullObject();
  }

  @Override
  public SlideViewerComponent setPresentation(Presentation presentation) {
    return this;
  }
  
}
