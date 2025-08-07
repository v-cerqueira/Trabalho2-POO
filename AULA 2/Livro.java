/**
 * Classe que representa um livro no sistema de biblioteca
 * Demonstra os conceitos de encapsulamento, abstração e métodos
 */
public class Livro {
    // Atributos privados (encapsulamento)
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private double preco;
    private boolean disponivel;
    
    /**
     * Construtor que recebe todos os atributos como parâmetros
     * @param titulo Título do livro
     * @param autor Autor do livro
     * @param anoPublicacao Ano de publicação
     * @param preco Preço do livro
     * @param disponivel Se o livro está disponível
     */
    public Livro(String titulo, String autor, int anoPublicacao, double preco, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
        this.disponivel = disponivel;
    }
    
    // Getters e Setters para todos os atributos
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    /**
     * Método para emprestar o livro
     * Define o atributo disponivel como false
     */
    public void emprestar() {
        this.disponivel = false;
        System.out.println("Livro '" + titulo + "' foi emprestado.");
    }
    
    /**
     * Método para devolver o livro
     * Define o atributo disponivel como true
     */
    public void devolver() {
        this.disponivel = true;
        System.out.println("Livro '" + titulo + "' foi devolvido.");
    }
    
    /**
     * Calcula a idade do livro com base no ano atual
     * @param anoAtual Ano atual
     * @return Idade do livro em anos
     */
    public int obterIdade(int anoAtual) {
        return anoAtual - anoPublicacao;
    }
    
    /**
     * Aplica desconto no preço do livro
     * @param percentual Percentual de desconto (ex: 10.0 para 10%)
     */
    public void aplicarDesconto(double percentual) {
        if (percentual > 0 && percentual <= 100) {
            double desconto = preco * (percentual / 100.0);
            preco = preco - desconto;
            System.out.println("Desconto de " + percentual + "% aplicado ao livro '" + titulo + "'.");
            System.out.println("Novo preço: R$ " + String.format("%.2f", preco));
        } else {
            System.out.println("Percentual de desconto inválido!");
        }
    }
    
    /**
     * Sobrescreve o método toString para exibir detalhes do livro
     * @return String formatada com informações do livro
     */
    @Override
    public String toString() {
        String status = disponivel ? "Disponível" : "Emprestado";
        return String.format("Livro: %s | Autor: %s | Ano: %d | Preço: R$ %.2f | Status: %s", 
                           titulo, autor, anoPublicacao, preco, status);
    }
} 