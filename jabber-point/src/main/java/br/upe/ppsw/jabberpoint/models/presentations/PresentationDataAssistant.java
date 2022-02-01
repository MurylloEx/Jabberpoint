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

package br.upe.ppsw.jabberpoint.models.presentations;

import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;

import br.upe.ppsw.jabberpoint.views.DemoData;
import br.upe.ppsw.jabberpoint.models.interfaces.*;
import br.upe.ppsw.jabberpoint.models.serializers.*;

/**
 * Esta classe representa a aplicação do Padrão Strategy para 
 * carregar uma apresentação ou salvá-la utilizando diferentes
 * formatos de arquivos/serializadores. Aqui há a presença
 * do princípio da Segregação de Interfaces pois foi necessário
 * dividir a interface IData em mais interfaces de forma a 
 * obter uma granularidade mais fina e interfaces menores.
 */
public class PresentationDataAssistant {
  
  private HashMap<String, ISavePresentation> saveStrategies;
  private HashMap<String, ILoadPresentation> loadStrategies;

  public PresentationDataAssistant(){
    this.saveStrategies = new HashMap<>();
    this.loadStrategies = new HashMap<>();
    
    XMLData xml = new XMLData();
    HTMLData html = new HTMLData();
    JSONData json = new JSONData();
    DemoData demo = new DemoData();

    saveStrategies.put(json.supportedExtension(), json);
    saveStrategies.put(xml.supportedExtension(), xml);
    saveStrategies.put(html.supportedExtension(), html);

    loadStrategies.put(json.supportedExtension(), json);
    loadStrategies.put(xml.supportedExtension(), xml);
    loadStrategies.put(html.supportedExtension(), html);
    loadStrategies.put(demo.supportedExtension(), demo);
  }

  public List<String> supportedSaveExtensions(){
    return saveStrategies.keySet().stream().collect(Collectors.toList());
  }

  public List<String> supportedLoadExtensions(){
    return loadStrategies.keySet().stream().collect(Collectors.toList());
  }

  public Presentation create(){
    return new Presentation();
  }
  
  public Presentation load(String file){
    return getLoadPresentationFromExtension(file).load(file);
  }

  public void save(Presentation presentation, String fileName){
    getSavePresentationFromExtension(fileName).save(presentation, fileName);
  }

  public final String getExtension(String filename){
    return FilenameUtils.getExtension(filename);
  }
  
  protected final ISavePresentation getSavePresentationFromExtension(String file){
    ISavePresentation iData = this.saveStrategies.get(getExtension(file));
    if (iData == null)
      throw new RuntimeException("Não foi possível salvar pois esse formato de arquivo não suportado.");
    return iData;
  }

  protected final ILoadPresentation getLoadPresentationFromExtension(String file){
    ILoadPresentation iData = this.loadStrategies.get(getExtension(file));
    if (iData == null)
      throw new RuntimeException("Não foi possível carregar pois esse formato de arquivo não suportado.");
    return iData;
  }

}
