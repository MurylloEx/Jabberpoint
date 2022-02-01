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

package br.upe.ppsw.jabberpoint.models.slides;

import java.util.ArrayList;

import br.upe.ppsw.jabberpoint.models.items.SlideItem;

/**
 * Esta classe representa a aplicação do padrão de projeto Null Object
 * na classe Slide. O objetivo aqui é substituir o uso padrão do Null
 * e adicionar comportamentos padrões ao Slide em branco.
 */
public class SlideNullObject extends Slide {

  public SlideNullObject(){
    super();
  }

  @Override
  public Slide append(SlideItem anItem) {
    return this;
  }

  @Override
  public Slide appendText(int level, String text) {
    return this;
  }

  @Override
  public Slide appendImage(int level, String imageName){
    return this;
  }

  @Override
  public SlideItem getSlideItem(int number) {
    return null;
  }

  @Override
  public Slide setTitle(String title) {
    return this;
  }

  @Override
  public String getTitle() {
    return "";
  }

  @Override
  public Slide setSlideItems(ArrayList<SlideItem> slideItems) {
    return this;
  }

  @Override
  public ArrayList<SlideItem> getSlideItems() {
    return new ArrayList<SlideItem>();
  }
  
}
