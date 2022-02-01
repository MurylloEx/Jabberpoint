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

package br.upe.ppsw.jabberpoint.models.templates;

import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

import br.upe.ppsw.jabberpoint.models.interfaces.ITemplate;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.presentations.PresentationDataAssistant;

/**
 * Esta classe representa o gestor de templates de apresentação no
 * Jabberpoint. Sua responsabilidade é criar uma nova apresentação
 * com um template pré-definido pelo usuário.
 */
public class TemplatePresentation extends PresentationDataAssistant {
  
  private HashMap<String, ITemplate> supportedTemplates;

  public TemplatePresentation(){
    super();
    this.supportedTemplates = new HashMap<String, ITemplate>();

    DefaultTemplate defaultTemplate = new DefaultTemplate();
    MadisonTemplate madisonTemplate = new MadisonTemplate();
    
    supportedTemplates.put(defaultTemplate.supportedTemplate(), defaultTemplate);
    supportedTemplates.put(madisonTemplate.supportedTemplate(), madisonTemplate);
  }

  public List<String> supportedTemplates(){
    return supportedTemplates.keySet().stream().collect(Collectors.toList());
  }

  public final Presentation create(String templateName){
    return new Presentation().setTemplate(this.supportedTemplates.get(templateName));
  }

}
