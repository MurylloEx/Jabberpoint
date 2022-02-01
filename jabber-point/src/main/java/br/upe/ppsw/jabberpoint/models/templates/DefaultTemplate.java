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

import java.awt.Color;

import br.upe.ppsw.jabberpoint.models.Style;
import br.upe.ppsw.jabberpoint.models.interfaces.ITemplate;
import lombok.Getter;

public class DefaultTemplate implements ITemplate {
  
  @Getter
  private Style[] styles;

  public DefaultTemplate(){
    this.styles = new Style[] {
      new Style(0,  Color.red,   48, 20), //Nível 0
      new Style(20, Color.blue,  40, 10), //Nível 1
      new Style(50, Color.black, 36, 10), //Nível 2
      new Style(70, Color.black, 30, 10), //Nível 3
      new Style(90, Color.black, 24, 10)  //Nível 4
    };
  }

  public Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }
    return styles[level];
  }

  @Override
  public String supportedTemplate() {
    return "Default";
  }

}
