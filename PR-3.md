# Padrões de Projeto de Software - Sprint 3

🤓 **Aluno:** Muryllo Oliveira

Esta página agrupa todos os diagramas da aplicação dos princípios **SOLID** e refatoração para tornar o sistema ainda mais próximo do padrão **MVC** no projeto Jabberpoint. Em caso de necessidade de consulta, foi criada também uma página no Notion que pode ser acessada [clicando aqui](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8).

- 📌 **Nessa sprint, foram feitas as seguintes atividades:**

    1. **Atividade 01:** Analisar e refatorar o código do JabberPoint através da aplicação dos princípios SOLID;

    2. **Atividade 02:** Reler o artigo sobre MVC e refatorar o código do JabberPoint para adequá-lo a utilização deste padrão;

    3. **Atividade 03:** Criação dos diagramas de classes e de Sequência após a refatoração;

    4. **Atividade 04:** Melhorias na aplicação de determinados padrões e remoção do Fluent Interface.

- 📂 **Breve descrição do que foi feito:**
    - Ao longo desta **Sprint 3**, foram feitas grandes mudanças na aplicação **Jabberpoint** para que ela contemple o nível mais fino possível de granularidade. Diversas melhorias foram feitas ao se aplicar o **Single Responsibility Principle**. Foram removidos métodos que não condizem com a responsabilidade das classes **Presentation**, **Slide**, **SlideItem**, **BitmapItem**, **TextItem**, **SlideViewerComponent**, **MenuController** e demais classes.
    - Foram realizadas modificações para manter a **consistência** e **integridade conceitual** da **modelagem orientada a objetos**, como por exemplo a preservação do **Open/Closed** ao tornar classes como **DemoData** agora mais coerentes, no sentido de implementar apenas os métodos que realmente deve implementar e remover os métodos que não faziam nada.
    - Foram realizadas tantas remoções de trechos de código inúteis e divididos em demais classes que ao se observar por exemplo as classes **BitmapItem**, **TextItem**, **SlideItem**, **Presentation**, **Slide**, **SlideViewerComponent**, **KeyController** e **SlideViewerFrame** percebe-se que as classes se tornaram muito mais legíveis. Por que? Isso ocorreu pois apenas os métodos que eles devem possuir foram mantidos de acordo com sua responsabilidade na modelagem.
    - Foi introduzido o padrão **Strategy** no desenho dos itens de Slide, junto com o **Dependency Inversion Principle** que fizeram com que o sistema estivesse o menos acoplado possível. A brilhante ideia do **Strategy** fez com que fosse possível adicionar mais estratégias de desenhar elementos na tela apenas implementando uma interface e adicionando a devida classe na lista de estratégias possíveis. Esse conceito foi fielmente obtido a partir da interface **IGenericItemDrawer**. Agora o **Jabberpoint** está aberto à adição de futuros **SlideItem's**!
    - O conceito de Controllers nunca foi tão bem dividido no projeto, após esta sprint foram feitas inúmeras mudanças de tal forma que os Menus tivessem seus devidos Controllers padronizados utilizando **Hook Methods** e o **Template Pattern**, além de dividir a **responsabilidade** de avançar e voltar um slide da apresentação numa nova classe chamada **SlideController**.
    - Na parte dos IData's, foi aplicado o **Interface Segregation Principle** para que fosse possível aplicar o padrão **Strategy** de forma correta sem obrigar que classes implementem tanto os métodos de **CARREGAR** e **SALVAR**, simultaneamente, uma apresentação. Agora é possível escolher um ou outro, ou até mesmo, ambos.
    - Houve a aplicação do padrão **Strategy** até mesmo no **KeyController**. Observe o seguinte trecho de código:

        ```java
        (...)
        public void keyPressed(KeyEvent keyEvent) {
          switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
              presentation.nextSlide();
              break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
              presentation.prevSlide();
              break;
            case 'q':
            case 'Q':
              System.exit(0);
              break; //fix?
            default:
              break;
          }
        }
        (...)
        ```

        Fica evidente no código acima que a aplicação possuía problemas na especificação até das rotas. Não era flexível, ou seja, precisava codificar dentro de uma estrutura de controle o que a aplicação devia fazer ao pressionar uma tecla. Tudo isso foi corrigido aplicando o **Strategy** de tal forma que esse comportamento seja melhor descrito e mais facilmente alterável, como sugere o código a seguir:

        ```java
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
        ```

    - Essas não foram nem de longe as únicas mudanças. Para se aplicar corretamente o padrão MVC, é necessário também ter uma aplicação **RESILIENTE**, **MADURA** e bem **COMPARTIMENTADA**. Por falar em resiliência e compartimentação, uma das partes fundamentais que uma aplicação deve possuir é um **tratamento de exceções apropriado**. Isso foi feito nessa Sprint ao se aplicar o **tratamento global de exceções**. Agora o Jabberpoint converte exceções especializadas em exceções genéricas do tipo **RuntimeException** e as captura em um callback genérico definido assim que a aplicação é iniciada.

- 🖋 **Em síntese, qual a minha opinião a respeito dos resultados obtidos?**

    - Depois de estudar, amadurecer os conceitos de MVC, os padrões de projetos vistos em sala de aula e ter a oportunidade de aplicá-los em ambiente real, como no Jabberpoint, posso afirmar que a aplicação já está muito mais próxima do padrão de uma aplicação Robusta, Manutenível, Minimalista e Elegante do que o observado nos primeiros dias da disciplina quando foi apresentado o projeto. Toda a aplicação estava contida em um único pacote, repleta de referências cíclicas, más práticas de programação, *casts* explícitos e insegurança de tipagem, má utilização do polimorfismo dinâmico e uma péssima modelagem de orientação a objetos. Como consideração final, deixo a seguir uma série de diagramas que mostram como a aplicação era antes da Sprint 3 e como ela está agora. A diferença é assustadora no diagrama de classes, enquanto no Diagrama de Sequência é extremamente notável a presença do padrão MVC e suas devidas camadas de responsabilidade.

- **Diagramas ANTES da aplicação dos princípios SOLID e melhorias no MVC**
    - **Diagrama de Classes**

        [Diagrama de Classes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#7d50f8b96acd4676b61894324528d13a)

    - **Diagrama de Sequência**

        [Diagrama de Sequência - Transições dos Slides.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#788919dd1c4241b691c09a82b3d14b34)

        [Diagrama de Sequência - Barra de Menu - Arquivo.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#fd52d3457601451e966d3ed6bdf2d697)

        [Diagrama de Sequência - Barra de Menu - Visualizar.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#8028ae5fb34d4e6b995ffe6dea03d4f2)

        [Diagrama de Sequência - Barra de Menu - Ajuda.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#062f89abc80f4fe7a749c5c5f4adf653)

- **Diagramas DEPOIS da aplicação dos princípios SOLID e melhorias no MVC**
    - **Diagrama de Pacotes**

        [Diagrama de Pacotes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#4c62159ed9f24c1fb62cfcf086499cf5)

    - **Diagrama de Classes**

        [Diagrama de Classes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#6336b2a893d540649933d4ea84cb8674)

    - **Diagrama de Sequência**

        [Diagrama de Sequência - Barra de Menu (Arquivo).pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#c9ee210a59fd44f18e10974e397887a3)

        [Diagrama de Sequência - Barra de Menu (Visualizar).pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#5930e97df787436c8605e9cc9f21666f)

        [Diagrama de Sequência - Barra de Menu (Ajuda).pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#384689544fe740beb5950737980b8fd8)

        [Diagrama de Sequência - Transições dos Slides.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-3-88f98f127af6470ca9d25e86885586e8#fe972d7b7e334309aee2ef1406bdc5f5)

## Aluno: Muryllo Pimenta de Oliveira