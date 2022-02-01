# Padrões de Projeto de Software

**Equipe:** Muryllo Oliveira

Esta página agrupa todos os diagramas do projeto de aplicação dos padrões Strategy + Bridge no projeto Jabberpoint. Em caso de necessidade de consulta, fizemos também uma página no Notion que pode ser acessada [clicando aqui](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1).

- **Nessa sprint (Sprint 2), foram feitas as seguintes atividades:**
    1. Atividade-01: Implementar o padrão Strategy;
    2. Atividade-02: Implementar um Dialog para carregar/salvar apresentações;
    3. Atividade-03: Acrescentar suporte a novos formatos;
    4. Atividade-04: Acrescentar suporte a templates de apresentações.

### Breve descrição do que foi feito:

Ao longo da disciplina os padrões Strategy e Bridge foram vistos e notou-se que é possível estender o comportamento do Jabberpoint para suportar novos formatos de arquivos para se carregar e salvar apresentações. A princípio, a aplicação não possuía essa funcionalidade de abrir/salvar pois tudo era feito de forma estática. Dessa forma, foi utilizado o JFileChooser para implementar as caixas de diálogo de Abrir arquivo e Salvar arquivo, que por fim se ramificam entre 3 diferentes implementações (estratégias), sendo elas: **XMLData**, **JSONData**, **HTMLData**. Cada estratégia mencionada adiciona o suporte ao formato de arquivo mencionado. Além do suporte à novos formatos de arquivos, nessa Sprint foi dada a ênfase no suporte à templates, como ocorre em softwares PowerPoint, Prezi, etc. Esse suporte foi alcançado graças à utilização do padrão Bridge e do uso da composição ao representar um template como uma interface genérica **ITemplate** que possua métodos adequados para que a apresentação obtenha os estilos adequados para se utilizar nos elementos gráficos (**TextItem** e **BitmapItem**). Para os usuários do sistema, ao clicar no botão "Arquivo > Novo" uma caixa de diálogo de seleção pergunta qual o template o usuário prefere para se iniciar sua nova apresentação, assim como ocorre no PowerPoint. As opções dadas, inicialmente a título de ilustraçção, são os templates: Default e Madison.

- **Diagramas ANTES da aplicação dos padrões Strategy + Bridge**
    - **Diagrama de Classes**

        [Diagrama de Classes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1#4d59465834fd42f08654f4b08d2b8778)

    - **Diagrama de Sequência**

        [Diagrama de Sequência - Transição dos Slides.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1#4908b404cec8488887a5d5c9d64d1f65)

        [Diagrama de Sequência - Barra de Menu - Arquivo.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1#2b6f14843756411592202095cb944920)

        [Diagrama de Sequência - Barra de Menu - Visualizar.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1#657c835af55447578e12d5ee17a94dd4)

        [Diagrama de Sequência - Barra de Menu - Ajuda.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1#c3642e79bdce4b6f87a1ea64da57226c)

- **Diagramas DEPOIS da aplicação dos padrões Strategy + Bridge**
    - **Diagrama de Classes**

        [Diagrama de Classes.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-ddc820a54da24fe4b5e481e8a0aa39f1#cc5a67634c964dd6b06ba826ae7759af)

    - **Diagrama de Sequência**

        [Diagrama de Sequência - Transição dos Slides.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-2-ddc820a54da24fe4b5e481e8a0aa39f1#0f58cd817f8c4e3c9186dd58c1e4fe12)

        [Diagrama de Sequência - Barra de Menu - Arquivo.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-2-ddc820a54da24fe4b5e481e8a0aa39f1#201a7181fe52466b8d62c90e6325bf21)

        [Diagrama de Sequência - Barra de Menu - Visualizar.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-2-ddc820a54da24fe4b5e481e8a0aa39f1#23e616048eb9423db3e944171c76c993)

        [Diagrama de Sequência - Barra de Menu - Ajuda.pdf](https://www.notion.so/Padr-es-de-Projeto-de-Software-Sprint-2-ddc820a54da24fe4b5e481e8a0aa39f1#597f431cab434fc88db0da8a87394671)

## Dependências utilizadas:

- jsoup (Parser HTML de alta performance);
- google/gson (Parser JSON oficial da Google);

## Aluno: Muryllo Pimenta de Oliveira