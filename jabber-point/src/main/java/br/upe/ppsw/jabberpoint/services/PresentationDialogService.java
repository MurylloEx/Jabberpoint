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

import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.presentations.PresentationNullObject;
import br.upe.ppsw.jabberpoint.views.SlideViewerFrame;

public class PresentationDialogService {
  
  public static JFileChooser openFileDialog(String title, String description, List<String> extensions){
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setDialogTitle(title);
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    for (String extension : extensions){
      if (!extension.equals("")){
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
          String.format(description + " (.%s)", extension), extension));
      }
    }
    return fileChooser;
  }

  public static Presentation openPresentation(String title, SlideViewerFrame frame){
    JFileChooser fileChooser = openFileDialog(title, "Carregar apresentação", 
      DataAssistantService.getAssistant().supportedLoadExtensions());

    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
      return DataAssistantService.load(fileChooser.getSelectedFile().getAbsolutePath());
    }
    return new PresentationNullObject();
  }

  public static void savePresentation(String title, SlideViewerFrame frame){
    JFileChooser fileChooser = openFileDialog(title, "Salvar como",
      DataAssistantService.getAssistant().supportedSaveExtensions());

    if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
      FileNameExtensionFilter filter = (FileNameExtensionFilter)fileChooser.getFileFilter();
      String filePath = fileChooser.getSelectedFile().getAbsolutePath() + "." + filter.getExtensions()[0];
      DataAssistantService.save(frame.getSlideViewerComponent().getPresentation(), filePath);
    }
  }

}
