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

import java.awt.Menu;
import java.awt.MenuBar;

/**
 *  Implementação dos mecanismos de controle navegacional através de 
 *  um menu superior de uma {@link Presentation}.
 */
public class MenuController extends MenuBar {

  /**
   * Representa o menu superior da tela de {@link Presentation}
   */
  public MenuController() 
  {
    super();

    Menu fileMenu = new Menu("Arquivo");
    Menu viewMenu = new Menu("Visualizar");
    Menu helpMenu = new Menu("Ajuda");
    
    fileMenu.add(new MenuFileOpenController("Abrir"));
    fileMenu.add(new MenuFileNewController("Novo"));
    fileMenu.add(new MenuFileSaveController("Salvar"));
    fileMenu.addSeparator();
    fileMenu.add(new MenuFileExitController("Sair"));

    viewMenu.add(new MenuViewNextController("Próximo"));
    viewMenu.add(new MenuViewPreviousController("Anterior"));
    viewMenu.add(new MenuViewGotoController("Pular para"));

    helpMenu.add(new MenuHelpAboutBoxController("Sobre"));
    
    add(fileMenu);
    add(viewMenu);
    setHelpMenu(helpMenu);
  }
  
}
