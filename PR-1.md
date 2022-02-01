# Padrões de Projeto de Software

**Equipe:** Muryllo Oliveira e Luiz Gustavo

Esta página agrupa todos os diagramas do projeto de aplicação do padrão MVC no projeto Jabberpoint. Em caso de necessidade de consulta, fizemos também uma página no Notion que pode ser acessada [clicando aqui](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a).

- **Análise do Sistema**
    1. Ao longo da refatoração do sistema foram identificados inúmeros problemas no código-fonte que colocam o desenvolvedor em uma situação de difícil manutenibilidade do sistema pois o código possuía alto acoplamento e as entidades principais do sistema possuíam dependências em forma de atributos que vinham com atribuições nulas. Dessa forma, foi essencial aplicar o padrão de projeto **Null Object** para evitar NullPointerException's.
    2. Outro padrão aplicado foi o dos decoradores do Lombok que se mostraram efetivos ao remover invólucros getters e setters da visão do código na refatoração para diminuir verbosidade do código de encapsulamento das propriedades das classes.
    3. Foi notado que o método draw se repete em mais de uma classe sendo assim aplicado o padrão **Template Method**.
    4. Foi observado que o sistema possuía muitas constantes definidas em lugares arbitrários ao longo das classes do sistema sem necessidade e agregar benefícios aparentes, sendo assim removidos na refatoração final e constantes relacionadas à configuração/referência de arquivos externos adicionados à uma classe específica de configuração denominada JabberConfig.
    5. Ao longo da aplicação do padrão **MVC** foi notado que a classe **MenuController** possuía muitas atividades sendo feitas diretamente em seu construtor. Nisso surgiu a necessidade de dividi-lo em mais métodos para melhorar sua legibilidade e evitar quebra de código por acoplamento. Esse detalhe foi fundamental para a aplicação **CORRETA** do padrão MVC.
    6. Todos os itens da barra de menu do sistema foram componentizados em classes que herdam de uma classe em comum chamada **MenuComponentTemplate** que implementa o padrão de projeto **Template Method** e em especial um método chamado **menuAction** que se repetia em todos os componentes de menu, esse método se chamava **actionPerformed**. Logo, o **menuAction** é um **Hook Method** utilizado na classe **MenuComponentTemplate**.
    7. No arquivo XMLAccessor, posteriormente renomeado como PresentationAccessor, o Template Method substituiu a necessidade do seguinte trecho de código:

        ```java
        (...)
                SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
                out.print("<item kind=");

                if (slideItem instanceof TextItem) {
                  out.print("\\"text\\" level=\\"" + slideItem.getLevel() + "\\">");
                  out.print(((TextItem) slideItem).getText());
                } else {
                  if (slideItem instanceof BitmapItem) {
                    out.print("\\"image\\" level=\\"" + slideItem.getLevel() + "\\">");
                    out.print(((BitmapItem) slideItem).getImageName());
                  } else {
                    System.out.println("Ignoring " + slideItem);
                  }
                }
                out.println("</item>");
        (...)

        ```

        pela seguinte declaração:

        ```java
        serializedItems.add(slideItem.serialize());
        ```

        Isso é possível pois cada classe especializadora serializa a si própria, conforme descrito em SlideItem.java pelo método:

        ```java
        public abstract String serialize();
        ```

- **Diagramas ANTES da refatoração e aplicação do padrão MVC**
    - **Diagrama de Classes**

        [Diagrama de classes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#6cffb8b37ba74042bfd30310f71e7e74)

    - **Diagrama de Sequência**

        [Diagrama de Sequência - Transição dos Slides.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#1069a4ad1cdc4b5da1cae8b3270d74fa)

        [Diagrama de Sequência - Barra de Menu.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#ac052f23d5914040ace8fa2a85988e65)

- **Diagramas DEPOIS da refatoração e aplicação do padrão MVC**
    - **Diagrama de Classes**

        [Diagrama de Classes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#b73e6f3613f0458db2c0bc4bab0ef5d0)

    - **Diagrama de Sequência**

        [Diagrama de Sequência - Transição dos Slides.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-1-237e5be902104163897cfbd79e8dad0a#d47870125f4f41c1b24818d61225a01d)

        [Diagrama de Sequência - Barra de Menu - Arquivo.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#071eeee1e2e24b639d18c26e1fb14a1e)

        [Diagrama de Sequência - Barra de Menu - Visualizar.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#95a7ebf3182b45eab1f7398c871882de)

        [Diagrama de Sequência - Barra de Menu - Ajuda.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-237e5be902104163897cfbd79e8dad0a#e0f79d32cee8456fb2275dfbc80dba56)