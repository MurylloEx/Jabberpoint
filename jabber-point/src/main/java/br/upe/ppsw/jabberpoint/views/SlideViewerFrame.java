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

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.slides.Slide;
import br.upe.ppsw.jabberpoint.controllers.KeyController;
import br.upe.ppsw.jabberpoint.controllers.MenuController;
import br.upe.ppsw.jabberpoint.controllers.SlideController;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Representa a janela de exibição ao usuário dos {@link Slide} de uma {@link Presentation};
 */
@Getter @Setter
@Accessors(chain = true)
public class SlideViewerFrame extends JFrame {

  public final static int WIDTH = 1200;
  public final static int HEIGHT = 800;

  private static SlideViewerFrame s_SlideViewerFrame = null;

  private SlideViewerComponent slideViewerComponent; 
  private SlideController slideController;

  private SlideViewerFrame(String title, Presentation presentation) {
    super(title);

    this.slideViewerComponent = new SlideViewerComponent(presentation);
    this.slideController = new SlideController();

    setVisible(true);
    setMenuBar(new MenuController());
    setSize(new Dimension(WIDTH, HEIGHT));

    getContentPane().add((SlideViewerComponent)slideViewerComponent);
    addKeyListener(new KeyController());
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  public static SlideViewerFrame getInstance(){
    return s_SlideViewerFrame;
  }

  public static void setInstance(SlideViewerFrame viewerFrame){
    s_SlideViewerFrame = viewerFrame;
  }

  public static SlideViewerFrame setInstance(String title, Presentation presentation){
    s_SlideViewerFrame = new SlideViewerFrame(title, presentation);
    return s_SlideViewerFrame;
  }

}
