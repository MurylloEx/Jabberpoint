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

package br.upe.ppsw.jabberpoint.services;

import javax.swing.JOptionPane;

import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;

/**
 * Este serviço atua na criação de caixas de diálogos
 * genéricas utilizadas no Jabberpoint.
 */
public class DialogService {
  
  public static void showMessage(String title, String body, int type){
    JOptionPane.showMessageDialog(SlideViewerFrame.getInstance(), body, title, type);
  }

  public static Object showInput(String title, String body, String[] options, String defaultChoice, int type){
    return JOptionPane.showInputDialog(SlideViewerFrame.getInstance(), 
      body, title, type, null, options, defaultChoice);
  }

  public static int showNumberInput(String question){
    return Integer.parseInt(JOptionPane.showInputDialog(question));
  }

  public static void showError(String title, String body){
    showMessage(title, body, JOptionPane.ERROR_MESSAGE);
  }

  public static void showInfo(String title, String body){
    showMessage(title, body, JOptionPane.INFORMATION_MESSAGE);
  }

  public static String showTextInput(String title, String question, String[] options, String defaultChoice){
    return (String)showInput(title, question, options, defaultChoice, JOptionPane.QUESTION_MESSAGE);
  }

}
