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

import br.upe.ppsw.jabberpoint.models.interfaces.ILoadPresentation;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.slides.Slide;

/**
 * Representa os dados de uma {@link Presentation} de demonstração.
 * Aqui há a presença do princípio Open/Closed pois esta classe apenas
 * estende o comportamento de carregamento, não o de salvamento em disco.
 * Isso significa que a classe DemoData não implementa métodos que não
 * utiliza, preservando o princípio Open/Closed do SOLID.
 */
public class DemoData implements ILoadPresentation {

  public Presentation load(String unusedFilename){
    Presentation presentation = new Presentation();
    presentation.setTitle("Apresentação de Demonstração");
    presentation.append(new Slide()
      .setTitle("JabberPoint")
      .appendText(1, "Ferramenta de Apresentação de Slides")
      .appendText(2, "Copyright (c) 1996-now: Ian Darwin")
      .appendText(2, "Copyright (c) 2021-now:")
      .appendText(2, "Helaine Barreiros")
      .appendText(4, "JabberPoint execução de demonstração sem arquivos persistidos")
      .appendText(4, "exibição de apresentação com dados apenas em memória")
      .appendText(1, "Navegação:")
      .appendText(3, "Próximo slide: PgDn ou Enter")
      .appendText(3, "Slide Anterior: PgUp ou up-arrow")
      .appendText(3, "Parar: q ou Q"));

    presentation.append(new Slide()
      .setTitle("Demonstração dos níveis e estilos de uma apresentação")
      .appendText(1, "Nível 1")
      .appendText(2, "Nível 2")
      .appendText(1, "Novamente um item de Nível 1")
      .appendText(1, "Nível 1 tem Estilo número 1")
      .appendText(2, "Nível 2 tem Estilo número 2")
      .appendText(3, "Este é um ítem de Nível 3")
      .appendText(4, "E este é um ítem de Nível 4"));
      
    presentation.append(new Slide()
      .setTitle("Terceiro Slide")
      .appendText(1, "Para abrir uma nova apresentação,")
      .appendText(2, "utilize o menu File->Open.")
      .appendText(1, " ")
      .appendText(1, "Fim da Apresentação")
      .appendImage(1, "classpath:JabberPoint.jpg"));
  
    return presentation;
  }

  public String supportedExtension() {
    return "";
  }

}
