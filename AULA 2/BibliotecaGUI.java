import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.geom.RoundRectangle2D;

/**
 * Interface gráfica simplificada para o Sistema de Biblioteca
 * Design futurista com barras de rolagem e layout simplificado
 */
public class BibliotecaGUI extends JFrame {
    
    // Componentes da interface
    private Biblioteca biblioteca;
    private JTextField txtTitulo, txtAutor, txtAno, txtPreco, txtBusca;
    private JCheckBox chkDisponivel;
    private JTable tblLivros;
    private DefaultTableModel modeloTabela;
    private JLabel lblValorTotal, lblContadorLivros;
    private JTextArea txtAreaLog;
    
    // Cores do tema futurista
    private static final Color COR_FUNDO = new Color(30, 30, 47);      // #1e1e2f
    private static final Color COR_PAINEL = new Color(40, 40, 60);      // Painéis
    private static final Color COR_BORDA = new Color(0, 255, 255);      // #00ffff - Neon azul
    private static final Color COR_TEXTO = new Color(255, 255, 255);    // Branco
    private static final Color COR_TEXTO_SECUNDARIO = new Color(200, 200, 200); // Cinza claro
    private static final Color COR_BOTAO = new Color(0, 120, 200);      // Azul
    private static final Color COR_BOTAO_HOVER = new Color(0, 180, 255); // Azul neon
    private static final Color COR_CAMPO = new Color(50, 50, 70);       // Campos
    private static final Color COR_SELECAO = new Color(0, 150, 255);    // Seleção tabela
    
    public BibliotecaGUI() {
        biblioteca = new Biblioteca();
        configurarInterface();
        configurarEventos();
    }
    
    private void configurarInterface() {
        setTitle("Biblioteca POO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        
        // Layout principal simplificado
        setLayout(new BorderLayout(10, 10));
        
        // Painel superior - Título e contadores
        JPanel painelTitulo = criarPainelTitulo();
        add(painelTitulo, BorderLayout.NORTH);
        
        // Painel central com scroll
        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 10, 0));
        painelCentral.setBackground(COR_FUNDO);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Painel esquerdo com scroll
        JPanel painelEsquerdo = criarPainelEsquerdo();
        JScrollPane scrollEsquerdo = new JScrollPane(painelEsquerdo);
        scrollEsquerdo.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        scrollEsquerdo.getVerticalScrollBar().setUnitIncrement(16);
        painelCentral.add(scrollEsquerdo);
        
        // Painel direito com scroll
        JPanel painelDireito = criarPainelDireito();
        JScrollPane scrollDireito = new JScrollPane(painelDireito);
        scrollDireito.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        scrollDireito.getVerticalScrollBar().setUnitIncrement(16);
        painelCentral.add(scrollDireito);
        
        add(painelCentral, BorderLayout.CENTER);
        
        // Painel inferior - Log
        JPanel painelInferior = criarPainelInferior();
        add(painelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(COR_FUNDO);
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Título principal
        JLabel titulo = new JLabel("📚 BIBLIOTECA POO");
        titulo.setFont(new Font("Consolas", Font.BOLD, 24));
        titulo.setForeground(COR_BORDA);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        painel.add(titulo, gbc);
        
        // Contadores
        lblContadorLivros = new JLabel("Livros: 0/10");
        lblContadorLivros.setFont(new Font("Consolas", Font.BOLD, 14));
        lblContadorLivros.setForeground(COR_TEXTO_SECUNDARIO);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painel.add(lblContadorLivros, gbc);
        
        lblValorTotal = new JLabel("Valor Total: R$ 0,00");
        lblValorTotal.setFont(new Font("Consolas", Font.BOLD, 14));
        lblValorTotal.setForeground(COR_TEXTO_SECUNDARIO);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        painel.add(lblValorTotal, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelEsquerdo() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        // Seção de adicionar livro
        JPanel secaoAdicionar = criarSecaoAdicionar();
        painel.add(secaoAdicionar);
        painel.add(Box.createVerticalStrut(20));
        
        // Seção de busca
        JPanel secaoBusca = criarSecaoBusca();
        painel.add(secaoBusca);
        painel.add(Box.createVerticalStrut(20));
        
        // Seção de operações
        JPanel secaoOperacoes = criarSecaoOperacoes();
        painel.add(secaoOperacoes);
        
        return painel;
    }
    
    private JPanel criarSecaoAdicionar() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            "📖 ADICIONAR LIVRO",
            0, 0,
            new Font("Consolas", Font.BOLD, 14),
            COR_BORDA
        ));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        // Campos do formulário
        txtTitulo = criarCampoTexto("Título:");
        painel.add(txtTitulo.getParent());
        painel.add(Box.createVerticalStrut(10));
        
        txtAutor = criarCampoTexto("Autor:");
        painel.add(txtAutor.getParent());
        painel.add(Box.createVerticalStrut(10));
        
        txtAno = criarCampoTexto("Ano:");
        painel.add(txtAno.getParent());
        painel.add(Box.createVerticalStrut(10));
        
        txtPreco = criarCampoTexto("Preço (R$):");
        painel.add(txtPreco.getParent());
        painel.add(Box.createVerticalStrut(15));
        
        // Checkbox disponível
        chkDisponivel = new JCheckBox("Disponível");
        chkDisponivel.setSelected(true);
        chkDisponivel.setFont(new Font("Consolas", Font.PLAIN, 14));
        chkDisponivel.setForeground(COR_TEXTO);
        chkDisponivel.setBackground(COR_PAINEL);
        chkDisponivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(chkDisponivel);
        painel.add(Box.createVerticalStrut(15));
        
        // Botão adicionar
        JButton btnAdicionar = criarBotaoFuturista("➕ ADICIONAR");
        btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdicionar.addActionListener(e -> adicionarLivro());
        painel.add(btnAdicionar);
        
        return painel;
    }
    
    private JPanel criarSecaoBusca() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            "🔍 BUSCAR",
            0, 0,
            new Font("Consolas", Font.BOLD, 14),
            COR_BORDA
        ));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        txtBusca = criarCampoTexto("Título:");
        painel.add(txtBusca.getParent());
        painel.add(Box.createVerticalStrut(10));
        
        JButton btnBuscar = criarBotaoFuturista("🔍 BUSCAR");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.addActionListener(e -> buscarLivro());
        painel.add(btnBuscar);
        
        return painel;
    }
    
    private JPanel criarSecaoOperacoes() {
        JPanel painel = new JPanel();
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            "🔧 OPERAÇÕES",
            0, 0,
            new Font("Consolas", Font.BOLD, 14),
            COR_BORDA
        ));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        
        JButton btnEmprestar = criarBotaoFuturista("📤 EMPRESTAR");
        btnEmprestar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEmprestar.addActionListener(e -> emprestarLivro());
        
        JButton btnDevolver = criarBotaoFuturista("📥 DEVOLVER");
        btnDevolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDevolver.addActionListener(e -> devolverLivro());
        
        JButton btnDesconto = criarBotaoFuturista("💰 DESCONTO");
        btnDesconto.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDesconto.addActionListener(e -> aplicarDesconto());
        
        painel.add(btnEmprestar);
        painel.add(Box.createVerticalStrut(8));
        painel.add(btnDevolver);
        painel.add(Box.createVerticalStrut(8));
        painel.add(btnDesconto);
        
        return painel;
    }
    
    private JPanel criarPainelDireito() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título da tabela
        JLabel lblTitulo = new JLabel("📋 LIVROS NA BIBLIOTECA");
        lblTitulo.setFont(new Font("Consolas", Font.BOLD, 16));
        lblTitulo.setForeground(COR_BORDA);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(lblTitulo, BorderLayout.NORTH);
        
        // Tabela
        String[] colunas = {"Título", "Autor", "Ano", "Preço", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblLivros = new JTable(modeloTabela);
        configurarTabela();
        
        JScrollPane scrollPane = new JScrollPane(tblLivros);
        scrollPane.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 10, 10));
        painelBotoes.setBackground(COR_PAINEL);
        
        JButton btnAtualizar = criarBotaoFuturista("🔄 ATUALIZAR");
        btnAtualizar.addActionListener(e -> atualizarTabela());
        painelBotoes.add(btnAtualizar);
        
        JButton btnLimpar = criarBotaoFuturista("🧹 LIMPAR");
        btnLimpar.addActionListener(e -> limparFormulario());
        painelBotoes.add(btnLimpar);
        
        JButton btnTotal = criarBotaoFuturista("💰 TOTAL");
        btnTotal.addActionListener(e -> calcularValorTotal());
        painelBotoes.add(btnTotal);
        
        JButton btnDisponiveis = criarBotaoFuturista("📚 DISPONÍVEIS");
        btnDisponiveis.addActionListener(e -> listarLivrosDisponiveis());
        painelBotoes.add(btnDisponiveis);
        
        painel.add(painelBotoes, BorderLayout.SOUTH);
        
        return painel;
    }
    
    private void configurarTabela() {
        tblLivros.setFont(new Font("Consolas", Font.PLAIN, 12));
        tblLivros.setBackground(COR_CAMPO);
        tblLivros.setForeground(COR_TEXTO);
        tblLivros.setGridColor(COR_BORDA);
        tblLivros.setSelectionBackground(COR_SELECAO);
        tblLivros.setSelectionForeground(COR_TEXTO);
        tblLivros.setRowHeight(30);
        tblLivros.getTableHeader().setBackground(COR_PAINEL);
        tblLivros.getTableHeader().setForeground(COR_BORDA);
        tblLivros.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 12));
    }
    
    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new BorderLayout(10, 0));
        painel.setBackground(COR_PAINEL);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        // Área de log
        JPanel painelLog = new JPanel(new BorderLayout());
        painelLog.setBackground(COR_PAINEL);
        
        JLabel lblLog = new JLabel("📝 LOG DE OPERAÇÕES");
        lblLog.setFont(new Font("Consolas", Font.BOLD, 14));
        lblLog.setForeground(COR_BORDA);
        painelLog.add(lblLog, BorderLayout.NORTH);
        
        txtAreaLog = new JTextArea(6, 50);
        txtAreaLog.setFont(new Font("Consolas", Font.PLAIN, 11));
        txtAreaLog.setBackground(COR_CAMPO);
        txtAreaLog.setForeground(COR_TEXTO);
        txtAreaLog.setEditable(false);
        txtAreaLog.setBorder(BorderFactory.createLineBorder(COR_BORDA, 2));
        txtAreaLog.setLineWrap(true);
        txtAreaLog.setWrapStyleWord(true);
        
        JScrollPane scrollLog = new JScrollPane(txtAreaLog);
        scrollLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollLog.getVerticalScrollBar().setUnitIncrement(16);
        painelLog.add(scrollLog, BorderLayout.CENTER);
        
        painel.add(painelLog, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JTextField criarCampoTexto(String label) {
        JPanel painel = new JPanel(new BorderLayout(5, 0));
        painel.setBackground(COR_PAINEL);
        painel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Consolas", Font.PLAIN, 12));
        lbl.setForeground(COR_TEXTO);
        painel.add(lbl, BorderLayout.NORTH);
        
        JTextField campo = new JTextField(20);
        campo.setFont(new Font("Consolas", Font.PLAIN, 12));
        campo.setBackground(COR_CAMPO);
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_BORDA);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_BORDA, 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        campo.setEditable(true);
        campo.setEnabled(true);
        painel.add(campo, BorderLayout.CENTER);
        
        return campo;
    }
    
    private JButton criarBotaoFuturista(String texto) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(COR_BOTAO_HOVER);
                } else if (getModel().isRollover()) {
                    g2d.setColor(COR_BOTAO_HOVER);
                } else {
                    g2d.setColor(COR_BOTAO);
                }
                
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        botao.setFont(new Font("Consolas", Font.BOLD, 12));
        botao.setForeground(COR_TEXTO);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return botao;
    }
    
    private void configurarEventos() {
        atualizarContadores();
        adicionarLog("🚀 Sistema iniciado com sucesso!");
    }
    
    // Métodos de funcionalidade
    private void adicionarLivro() {
        try {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            int ano = Integer.parseInt(txtAno.getText().trim());
            double preco = Double.parseDouble(txtPreco.getText().trim());
            boolean disponivel = chkDisponivel.isSelected();
            
            if (titulo.isEmpty() || autor.isEmpty()) {
                mostrarMensagem("Título e autor são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Livro livro = new Livro(titulo, autor, ano, preco, disponivel);
            
            if (biblioteca.adicionarLivro(livro)) {
                adicionarLog("✅ Livro '" + titulo + "' adicionado!");
                limparFormulario();
                atualizarTabela();
                atualizarContadores();
                mostrarMensagem("Livro adicionado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                adicionarLog("❌ Biblioteca cheia!");
                mostrarMensagem("Biblioteca cheia!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            mostrarMensagem("Ano e preço devem ser números!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarLivro() {
        String termo = txtBusca.getText().trim();
        if (termo.isEmpty()) {
            mostrarMensagem("Digite um título!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Livro livro = biblioteca.buscarLivroPorTitulo(termo);
        if (livro != null) {
            adicionarLog("🔍 Encontrado: '" + livro.getTitulo() + "'");
            selecionarLivroNaTabela(livro);
            mostrarMensagem("Livro encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            adicionarLog("❌ Não encontrado: '" + termo + "'");
            mostrarMensagem("Livro não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void selecionarLivroNaTabela(Livro livro) {
        for (int i = 0; i < modeloTabela.getRowCount(); i++) {
            if (modeloTabela.getValueAt(i, 0).equals(livro.getTitulo())) {
                tblLivros.setRowSelectionInterval(i, i);
                break;
            }
        }
    }
    
    private void emprestarLivro() {
        Livro livro = obterLivroSelecionado();
        if (livro != null) {
            if (livro.isDisponivel()) {
                livro.emprestar();
                adicionarLog("📤 Emprestado: '" + livro.getTitulo() + "'");
                atualizarTabela();
                mostrarMensagem("Livro emprestado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                adicionarLog("⚠️ Já emprestado: '" + livro.getTitulo() + "'");
                mostrarMensagem("Livro já emprestado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            mostrarMensagem("Selecione um livro!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void devolverLivro() {
        Livro livro = obterLivroSelecionado();
        if (livro != null) {
            if (!livro.isDisponivel()) {
                livro.devolver();
                adicionarLog("📥 Devolvido: '" + livro.getTitulo() + "'");
                atualizarTabela();
                mostrarMensagem("Livro devolvido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                adicionarLog("⚠️ Já disponível: '" + livro.getTitulo() + "'");
                mostrarMensagem("Livro já disponível!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            mostrarMensagem("Selecione um livro!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void aplicarDesconto() {
        Livro livro = obterLivroSelecionado();
        if (livro == null) {
            mostrarMensagem("Selecione um livro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String percentualStr = JOptionPane.showInputDialog(this, "Digite o percentual de desconto:", "Desconto", JOptionPane.QUESTION_MESSAGE);
        if (percentualStr == null || percentualStr.trim().isEmpty()) {
            return;
        }
        
        try {
            double percentual = Double.parseDouble(percentualStr.trim());
            double precoAntes = livro.getPreco();
            livro.aplicarDesconto(percentual);
            adicionarLog("💰 Desconto " + percentual + "%: '" + livro.getTitulo() + "'");
            adicionarLog("   R$ " + String.format("%.2f", precoAntes) + " → R$ " + String.format("%.2f", livro.getPreco()));
            atualizarTabela();
            atualizarContadores();
            mostrarMensagem("Desconto aplicado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            mostrarMensagem("Percentual inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Livro obterLivroSelecionado() {
        int linhaSelecionada = tblLivros.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String titulo = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
            return biblioteca.buscarLivroPorTitulo(titulo);
        }
        return null;
    }
    
    private void listarLivrosDisponiveis() {
        adicionarLog("📚 Listando disponíveis...");
        atualizarTabela();
        mostrarMensagem("Tabela atualizada!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        
        Livro[] livros = biblioteca.getTodosLivros();
        for (Livro livro : livros) {
            if (livro != null) {
                String status = livro.isDisponivel() ? "Disponível" : "Emprestado";
                modeloTabela.addRow(new Object[]{
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getAnoPublicacao(),
                    String.format("R$ %.2f", livro.getPreco()),
                    status
                });
            }
        }
        
        adicionarLog("🔄 Tabela atualizada: " + biblioteca.getQuantidadeLivros() + " livros");
    }
    
    private void limparFormulario() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        txtPreco.setText("");
        chkDisponivel.setSelected(true);
        txtBusca.setText("");
        adicionarLog("🧹 Formulário limpo");
    }
    
    private void calcularValorTotal() {
        double total = biblioteca.calcularValorTotal();
        lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", total));
        adicionarLog("💰 Total: R$ " + String.format("%.2f", total));
    }
    
    private void atualizarContadores() {
        int quantidade = biblioteca.getQuantidadeLivros();
        int capacidade = biblioteca.getCapacidadeMaxima();
        lblContadorLivros.setText("Livros: " + quantidade + "/" + capacidade);
        calcularValorTotal();
    }
    
    private void adicionarLog(String mensagem) {
        String timestamp = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtAreaLog.append("[" + timestamp + "] " + mensagem + "\n");
        txtAreaLog.setCaretPosition(txtAreaLog.getDocument().getLength());
    }
    
    private void mostrarMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BibliotecaGUI gui = new BibliotecaGUI();
            gui.setVisible(true);
        });
    }
} 