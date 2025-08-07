/**
 * Classe que representa uma biblioteca no sistema
 * Demonstra manipulação de arrays, estruturas condicionais e modularização
 */
public class Biblioteca {
    // Atributos da biblioteca
    private Livro[] livros; // Array com capacidade para 10 livros
    private int quantidadeLivros; // Contador de livros adicionados
    
    /**
     * Construtor da biblioteca
     * Inicializa o array com capacidade para 10 livros
     */
    public Biblioteca() {
        this.livros = new Livro[10]; // Capacidade máxima de 10 livros
        this.quantidadeLivros = 0;
    }
    
    /**
     * Adiciona um livro ao array na próxima posição disponível
     * @param livro Livro a ser adicionado
     * @return true se o livro foi adicionado, false se a biblioteca está cheia
     */
    public boolean adicionarLivro(Livro livro) {
        // Verificação de nulidade
        if (livro == null) {
            System.out.println("Erro: Não é possível adicionar um livro nulo!");
            return false;
        }
        
        // Verifica se há espaço disponível
        if (quantidadeLivros < livros.length) {
            livros[quantidadeLivros] = livro;
            quantidadeLivros++;
            System.out.println("Livro '" + livro.getTitulo() + "' adicionado com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Biblioteca está cheia! Não é possível adicionar mais livros.");
            return false;
        }
    }
    
    /**
     * Busca um livro por título no array
     * @param titulo Título do livro a ser buscado
     * @return Livro encontrado ou null se não encontrado
     */
    public Livro buscarLivroPorTitulo(String titulo) {
        // Verificação de nulidade
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Erro: Título inválido para busca!");
            return null;
        }
        
        // Busca no array
        for (int i = 0; i < quantidadeLivros; i++) {
            if (livros[i] != null && livros[i].getTitulo().equalsIgnoreCase(titulo.trim())) {
                return livros[i];
            }
        }
        
        System.out.println("Livro com título '" + titulo + "' não encontrado.");
        return null;
    }
    
    /**
     * Lista todos os livros disponíveis (disponivel == true)
     */
    public void listarLivrosDisponiveis() {
        System.out.println("\n=== LIVROS DISPONÍVEIS ===");
        
        boolean encontrouDisponiveis = false;
        
        for (int i = 0; i < quantidadeLivros; i++) {
            if (livros[i] != null && livros[i].isDisponivel()) {
                System.out.println((i + 1) + ". " + livros[i].toString());
                encontrouDisponiveis = true;
            }
        }
        
        if (!encontrouDisponiveis) {
            System.out.println("Nenhum livro disponível no momento.");
        }
        System.out.println("==========================\n");
    }
    
    /**
     * Calcula o valor total de todos os livros armazenados
     * @return Soma dos preços de todos os livros
     */
    public double calcularValorTotal() {
        double valorTotal = 0.0;
        
        for (int i = 0; i < quantidadeLivros; i++) {
            if (livros[i] != null) {
                valorTotal += livros[i].getPreco();
            }
        }
        
        return valorTotal;
    }
    
    /**
     * Lista todos os livros da biblioteca
     */
    public void listarTodosLivros() {
        System.out.println("\n=== TODOS OS LIVROS ===");
        
        if (quantidadeLivros == 0) {
            System.out.println("Biblioteca vazia!");
        } else {
            for (int i = 0; i < quantidadeLivros; i++) {
                if (livros[i] != null) {
                    System.out.println((i + 1) + ". " + livros[i].toString());
                }
            }
        }
        System.out.println("=======================\n");
    }
    
    /**
     * Retorna a quantidade atual de livros na biblioteca
     * @return Número de livros
     */
    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }
    
    /**
     * Retorna a capacidade máxima da biblioteca
     * @return Capacidade máxima
     */
    public int getCapacidadeMaxima() {
        return livros.length;
    }
    
    /**
     * Retorna um livro específico pelo índice
     * @param indice Índice do livro
     * @return Livro no índice especificado ou null se inválido
     */
    public Livro getLivro(int indice) {
        if (indice >= 0 && indice < quantidadeLivros) {
            return livros[indice];
        }
        return null;
    }
    
    /**
     * Retorna todos os livros como array
     * @return Array com todos os livros
     */
    public Livro[] getTodosLivros() {
        Livro[] livrosRetorno = new Livro[quantidadeLivros];
        for (int i = 0; i < quantidadeLivros; i++) {
            livrosRetorno[i] = livros[i];
        }
        return livrosRetorno;
    }
} 