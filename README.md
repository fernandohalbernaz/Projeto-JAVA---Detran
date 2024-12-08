# Sistema de Controle de Multas de Trânsito

## 👥 Integrantes do Grupo

- Felipe Czymoch Ibanez
- Fernando Heztler Albernaz

## 📋 Descrição do Projeto

Este projeto foi desenvolvido como parte do Trabalho 2 da disciplina **Programação Orientada a Objetos** no curso de Engenharia da Computação, ministrada pelo Prof. Dr. Eduardo Takeo Ueda. Ele consiste na criação de um sistema em **Java** para gerenciamento de multas de trânsito na cidade de São Paulo, a pedido do **DETRAN/SP**.

O sistema modela e gerencia ocorrências de trânsito relacionadas a infrações como excesso de velocidade, desrespeito ao rodízio e tráfego em corredores de ônibus. Inclui funcionalidades para registro, visualização e processamento de ocorrências, além de filtragem de multas por data e placa.

Aqui está um passo a passo simples para rodar o sistema de controle de multas:

-------------------------------------------------------------------------------------------------------------

### **Como Rodar o Sistema de Controle de Multas**

1. **Baixe e instale uma IDE Java**
   - Recomendamos o [Eclipse](https://www.eclipse.org/downloads/).
   - Certifique-se de que o Java Development Kit (JDK) está instalado no seu computador. Se ainda não tiver, baixe a versão mais recente (https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Faça o download do código**
   - Baixe os arquivos do repositório como um arquivo `.zip`.
   - Extraia o conteúdo para uma pasta no seu computador.

3. **Abra o projeto na IDE**
   - Abra a IDE instalada.
   - Clique em **Open Project** e selecione a pasta onde você extraiu os arquivos do projeto.
   - Aguarde enquanto a IDE configura o ambiente do projeto.

4. **Compile o código**
   - Na IDE, localize a classe principal, geralmente chamada `Main.java`.
   - Certifique-se de que todas as dependências estão corretas.
   - Clique em **Build** ou **Run** (o botão de execução na IDE).

5. **Insira as informações**
   - Ao rodar o sistema, você verá opções para:
     - Cadastrar ocorrências de trânsito.
     - Visualizar multas processadas e não processadas.
     - Cadastrar novas regras.
   - Siga as instruções exibidas na tela para interagir com o sistema.

6. **Teste funcionalidades**
   - Experimente cadastrar algumas ocorrências para verificar se as multas são geradas corretamente.
   - Utilize a funcionalidade de busca para localizar multas por data ou placa de veículo.

-------------------------------------------------------------------------------------------------------------

Este guia deve facilitar o uso do sistema mesmo para iniciantes. Se precisar de ajuda com algum passo, avise!

## 🎯 Objetivo

O objetivo é implementar um sistema funcional e modular, utilizando conceitos avançados de programação orientada a objetos, incluindo:
- Uso de classes abstratas e especializadas.
- Leitura e manipulação de dados de arquivos externos.
- Desenvolvimento de uma interface interativa para usuários.
- Aplicação de validações e regras de negócio conforme especificado.

## 🛠️ Funcionalidades

1. **Cadastro de Ocorrências**: Permite registrar ocorrências de trânsito.
2. **Visualização de Ocorrências**: Lista as ocorrências processadas e não processadas.
3. **Processamento de Multas**: Aplica regras pré-definidas para gerar multas.
4. **Filtragem**: Busca multas por data e placa de veículo.
5. **Cadastro de Novas Regras**: Permite a inclusão de novas regras de multas no sistema.
6. **Validação de Entradas**: Garante a consistência dos dados inseridos.
7. **Exportação de Dados**: Geração de relatórios para análise.

## 🛠️ Estrutura do Projeto

- **Classes principais**:
  - `Ocorrencia`: Representa uma ocorrência de trânsito.
  - `BaseDeDados`: Gerencia as listas de ocorrências e multas.
  - `RegraMulta` (abstrata): Define métodos para cálculo e descrição de multas.
  - Subclasses de `RegraMulta`: Implementam regras específicas (velocidade, rodízio, corredor de ônibus).
  - `Multa`: Contém informações sobre as multas geradas.

## 🚀 Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Git e GitHub**: Controle de versão e compartilhamento do código.
- **Ferramentas de desenvolvimento**: IDEs como IntelliJ IDEA ou Eclipse.


## 📜 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos.


