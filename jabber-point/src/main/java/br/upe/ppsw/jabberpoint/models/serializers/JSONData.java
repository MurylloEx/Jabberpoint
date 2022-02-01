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

package br.upe.ppsw.jabberpoint.models.serializers;

import java.io.*;
import com.google.gson.Gson;

import br.upe.ppsw.jabberpoint.models.interfaces.*;
import br.upe.ppsw.jabberpoint.models.presentations.*;

import lombok.extern.java.Log;

/**
 * Representação de uma estratégia de serialização e
 * desserialização de arquivo JSON.
 */
@Log
public class JSONData implements ISavePresentation, ILoadPresentation {

	public Presentation load(String file){
    Presentation presentation = new PresentationNullObject();
    try {
      presentation = (new Gson()).fromJson(new FileReader(file), Presentation.class);
    } catch (Exception e) {
      log.severe(String.format("Error while loading presentation file. %s", e.toString()));
      throw new RuntimeException(e);
    }
    return presentation;
  }

	public void save(Presentation presentation, String file){
    try (PrintWriter jsonWriter = new PrintWriter(new FileWriter(file))){
      jsonWriter.write((new Gson()).toJson(presentation, Presentation.class));
      jsonWriter.flush();
    } catch (Exception e){
      log.severe(String.format("Error while saving file. %s", e.toString()));
      throw new RuntimeException(e);
    }
  }
  
  public String supportedExtension(){
    return "json";
  }
  
}