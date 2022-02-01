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

import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.views.SlideViewerComponent;

import lombok.extern.java.Log;

/**
 * Classe de template dos Controllers do menu que descrevem o Hook Method.
 */
@Log
public abstract class MenuControllerTemplate extends MenuItem {
  
  // Hook method usado para modificar o comportamento do Item.
  protected abstract void menuAction(ActionEvent event);
  
  public MenuControllerTemplate(String name, MenuShortcut shortcut){
    super(name, shortcut);
    this.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent){
        try {
          menuAction(actionEvent);
        } catch (Exception e){
          log.severe(String.format("Exception: %s", e.toString()));
          throw new RuntimeException(e);
        }
      }
    });
  }
  
  protected SlideViewerFrame getParentFrame() {
    return SlideViewerFrame.getInstance();
  }
  
  public SlideViewerComponent getViewerComponent() {
    return SlideViewerFrame.getInstance().getSlideViewerComponent();
  }
  
  public MenuControllerTemplate setViewerComponent(SlideViewerComponent value) {
    SlideViewerFrame.setInstance(getParentFrame().setSlideViewerComponent(value));
    return this;
  }
  
  public Presentation getPresentation() {
    return this.getViewerComponent().getPresentation();
  }

  public MenuControllerTemplate setPresentation(Presentation value) {
    return this.setViewerComponent(this.getViewerComponent().setPresentation(value));
  }

}
