# 🛵 Ping Mottu

Este projeto é uma API RESTful de monitoramento de motos, que utiliza Spring Boot e Gradle. Com ela, é possível acompanhar em tempo real a localização das motos via UWB, além de consultar o estado de cada moto e a filial onde está registrada.

---

## 👨‍💻 Integrantes

- RM558763 - Eric Issamu de Lima Yoshida
- RM555010 - Gustavo Matias Teixeira
- RM557515 - Gustavo Monção   

## 🏍️ Aplicação de Monitoramento de Motos (Java / Spring Boot)

Este repositório contém a aplicação de monitoramento de motos. Aqui estão as instruções para instalação, execução e teste da aplicação.

---

## 🌐 Pré-requisitos

Antes de iniciar, verifique se você possui:  

- **JDK 17** instalada
- **Gradle** instalado incluído no projeto  
- IDE como IntelliJ IDEA, Eclipse ou VS Code
- Plugin **Lombok** instalado na IDE

---
## 🚀 Passo a passo de instalação e execução

1. **Clonar o repositório**  
   ```bash
   git clone <link-do-repositorio>
   cd nome-do-repositorio
   ```

2. **Selecionar a JDK 17**
   - Certifique-se de que sua IDE ou terminal está usando o **Java 17**.

3. **Build do Gradle**

   - Se estiver usando terminal:
     ```bash
     ./gradlew build
     ```
     
Ou configure o build pela sua IDE.

4. **Instalar o Lombok**

- Se estiver usando IntelliJ IDEA ou Eclipse, instale o plugin **Lombok**.

- Certifique-se de habilitar a **annotation processing** na IDE.

5. **Executar a aplicação**

- Pelo terminal:
     ```bash
     ./gradlew bootRun
     ```
     
Ou execute a classe principal da aplicação na IDE (`@SpringBootApplication`).

---
## 🖥️ Testando a aplicação

1. Abra o navegador e acesse a **página principal HTML** (geralmente `http://localhost:8080`).

2. Faça login com:
   - **Usuário:** `user`
   - **Senha:** `user`
3. Navegue até uma página de **formulário** para testar o **CRUD** (Create, Read, Update, Delete).
4. Verifique se as operações estão funcionando corretamente na interface.

---

✅ Pronto! Sua aplicação Java Spring Boot está instalada, rodando e pronta para uso.
