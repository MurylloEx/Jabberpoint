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

package br.upe.ppsw.jabberpoint.models;

import java.awt.Color;
import java.awt.Font;

import br.upe.ppsw.jabberpoint.models.items.SlideItem;
import br.upe.ppsw.jabberpoint.models.presentations.Presentation;
import br.upe.ppsw.jabberpoint.models.slides.Slide;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Representa uma folha de estilos que é aplicado aos {@link SlideItem} 
 * dos {@link Slide} de uma {@link Presentation}.
 */
@Data
@Accessors(chain = true)
public class Style {

  private Font font;
  private Color color;
  private int indent;
  private int leading;
  private int fontSize;

  public Style(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    this.leading = leading;
    this.fontSize = points;
    this.font = new Font("Helvetica", Font.BOLD, fontSize);
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }

}
