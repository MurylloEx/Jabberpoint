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

import java.util.HashMap;
import java.lang.Runnable;
import java.awt.event.KeyEvent;

import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;

/**
 * Esta classe delega aos devidos métodos, services ou controllers 
 * uma ação que deve ser executada de acordo com uma tecla pressionada.
 * O nome deste conceito é Tabela de Despacho e é muito utilizado no C/C++.
 * 
 * Leia mais sobre essa viagem aqui: 
 * https://stackoverflow.com/questions/4480334/how-to-call-a-method-stored-in-a-hashmap-java
 */
public class KeyRouterStrategies {
  
  private HashMap<Integer, Runnable> strategies;

  public KeyRouterStrategies(){
    SlideController controller = SlideViewerFrame.getInstance().getSlideController();

    this.strategies = new HashMap<Integer, Runnable>();
    
    strategies.put(KeyEvent.VK_PAGE_DOWN, () -> controller.nextSlide());
    strategies.put(KeyEvent.VK_DOWN,      () -> controller.nextSlide());
    strategies.put(KeyEvent.VK_ENTER,     () -> controller.nextSlide());
    strategies.put(KeyEvent.VK_PLUS,      () -> controller.nextSlide());
    strategies.put(KeyEvent.VK_PAGE_UP,   () -> controller.previousSlide());
    strategies.put(KeyEvent.VK_UP,        () -> controller.previousSlide());
    strategies.put(KeyEvent.VK_MINUS,     () -> controller.previousSlide());
    strategies.put(KeyEvent.VK_Q,         () -> System.exit(0));
  }

  public void forward(int keyCode){
    if (strategies.containsKey(keyCode))
      strategies.get(keyCode).run();
  }

}
