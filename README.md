# 📚 Sistema de Biblioteca Futurista

Sistema de biblioteca em Java com interface gráfica moderna e barras de rolagem.

## 🎨 Interface

- **Tema Escuro** com cores neon
- **Barras de Rolagem** em todos os painéis
- **Layout Simplificado** e intuitivo
- **Design Futurista** com botões arredondados

## 🏗️ Estrutura

### 📖 Classe `Livro`
- Atributos: titulo, autor, anoPublicacao, preco, disponivel
- Métodos: emprestar(), devolver(), obterIdade(), aplicarDesconto()

### 🏢 Classe `Biblioteca`
- Array de 10 livros
- Métodos: adicionarLivro(), buscarLivroPorTitulo(), calcularValorTotal()

### 🖥️ Classe `BibliotecaGUI`
- Interface gráfica com scroll
- Formulário de adição
- Tabela de livros
- Operações: emprestar, devolver, desconto
- Log de operações

## 🚀 Como Executar

```bash
# Compilar
javac *.java

# Executar
java BibliotecaGUI
```

## 📋 Funcionalidades

### ✅ Gestão de Livros:
- Adicionar livros
- Buscar por título
- Visualizar em tabela
- Operações de empréstimo/devolução

### ✅ Interface:
- Campos de texto funcionais
- Botões com hover
- Tabela com scroll
- Log automático
- Contadores em tempo real

## 🎯 Conceitos POO

- **Encapsulamento** - Atributos privados
- **Abstração** - Classes bem definidas
- **Arrays** - Manipulação de objetos
- **Estruturas Condicionais** - Validações
- **Interface Gráfica** - Java Swing

## 📊 Como Usar

1. **Adicionar:** Preencher formulário → Clicar "ADICIONAR"
2. **Buscar:** Digitar título → Clicar "BUSCAR"
3. **Operações:** Selecionar livro na tabela → Clicar operação
4. **Log:** Todas as ações são registradas automaticamente

Sistema completo e funcional! 🚀 