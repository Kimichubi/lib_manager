import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("Olá usuário, digite seu nome: ");
        String userName = input.nextLine();
        User user = new User(userName);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        int option = 0;
        do {
            exibirMenu(user.getName(), formattedDate);

            try {
                option = input.nextInt();
                input.nextLine(); // Consumir a quebra de linha
                switch (option) {
                    case 1:
                        adicionarLivro();
                        break;
                    case 2:
                        visualizarLivros();
                        break;
                    case 3:
                        atualizarLivro();
                        break;
                    case 4:
                        buscarLivroPorId();
                        break;
                    case 5:
                        removerLivro();
                        break;
                    case 6:
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Obrigado por usar KimasSoft! Salvando informações...");
                        System.out.println("-----------------------------------------------------");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, tente novamente.");
                input.next(); // Limpar a entrada inválida
            }
        } while (option != 6);
    }

    private static void exibirMenu(String nomeUsuario, String formattedDate) {
        System.out.println("-----------------------------------------------------");
        System.out.println("\nOlá " + nomeUsuario);
        System.out.println("Software desenvolvido por Kimichubi");
        System.out.println("O que devemos fazer hoje?");
        System.out.println("1: Adicionar novo livro");
        System.out.println("2: Visualizar livros no estoque");
        System.out.println("3: Atualizar informações de um livro");
        System.out.println("4: Buscar livro pela numeração única");
        System.out.println("5: Remover um livro do estoque");
        System.out.println("6: Sair do sistema, salvar informações no dia " + formattedDate);
        System.out.println("-----------------------------------------------------");
    }

    private static void adicionarLivro() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Adicionar novo livro");
        System.out.print("Por favor, digite o nome do Livro: ");
        String bookName = input.nextLine();
        System.out.print("Por favor, digite a descrição do Livro: ");
        String bookDescription = input.nextLine();
        System.out.print("Por favor, digite o preço do Livro: ");
        float bookPrice = input.nextFloat();
        System.out.print("Por favor, digite a quantidade do Livro em estoque: ");
        int bookQuantity = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        System.out.println("\nNome do livro: " + bookName);
        System.out.println("Descrição do livro: " + bookDescription);
        System.out.println("Preço do livro: " + bookPrice);
        System.out.println("Quantidade do livro: " + bookQuantity);
        System.out.print("Para salvar, digite 1. Para voltar ao menu, digite qualquer outra tecla: ");
        int confirm = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        if (confirm == 1) {
            Book bookToAdd = new Book(bookName, bookDescription, bookPrice, bookQuantity);
            books.add(bookToAdd);
            System.out.println("Livro salvo com sucesso.");
        } else {
            System.out.println("Operação cancelada.");
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Voltando ao menu...");
        System.out.println("-----------------------------------------------------");
    }

    private static void visualizarLivros() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Visualizando livros no estoque...");
        if (books.isEmpty()) {
            System.out.println("Nenhum livro no estoque.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println("\nChave única do livro: " + i);
                System.out.println("Nome: " + book.getName());
                System.out.println("Descrição: " + book.getDescription());
                System.out.println("Preço: " + book.getPrice());
                System.out.println("Quantidade: " + book.getQuantity());
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Pressione enter para voltar.");
        input.nextLine();
    }

    private static void atualizarLivro() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Atualizar informações de um livro");
        visualizarLivros();
        System.out.print("Digite a numeração única do livro que deseja editar: ");
        int bookId = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        if (bookId >= 0 && bookId < books.size()) {
            Book editableBook = books.get(bookId);
            System.out.println("\nEditando o livro: " + editableBook.getName());
            System.out.println("1: Nome");
            System.out.println("2: Descrição");
            System.out.println("3: Preço");
            System.out.println("4: Quantidade");
            System.out.println("5: Atualizar todos os campos");
            System.out.println("6: Voltar ao menu");
            System.out.print("Escolha uma opção: ");
            int editableOption = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha

            switch (editableOption) {
                case 1:
                    editableBook.setName(editarCampo(editableBook.getName(), "Nome"));
                    break;
                case 2:
                    editableBook.setDescription(editarCampo(editableBook.getDescription(), "Descrição"));
                    break;
                case 3:
                    System.out.print("Novo preço: ");
                    float newPrice = input.nextFloat();
                    editableBook.setPrice(newPrice);
                    break;
                case 4:
                    System.out.print("Nova quantidade: ");
                    int newQuantity = input.nextInt();
                    editableBook.setQuantity(newQuantity);
                    break;
                case 5:
                    System.out.print("Novo nome: ");
                    editableBook.setName(input.nextLine());
                    System.out.print("Nova descrição: ");
                    editableBook.setDescription(input.nextLine());
                    System.out.print("Novo preço: ");
                    editableBook.setPrice(input.nextFloat());
                    System.out.print("Nova quantidade: ");
                    editableBook.setQuantity(input.nextInt());
                    break;
                case 6:
                    System.out.println("Voltando ao menu...");
                    break;
                default:
                    System.out.println("Opção inválida, voltando ao menu...");
                    break;
            }
        } else {
            System.out.println("Erro: Índice inválido.");
        }
        System.out.println("-----------------------------------------------------");
    }

    private static void buscarLivroPorId() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Buscar livro pela numeração única");
        visualizarLivros();
        System.out.print("Digite a numeração única do livro que deseja visualizar: ");
        int bookId = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        if (bookId >= 0 && bookId < books.size()) {
            Book book = books.get(bookId);
            System.out.println("\nNome: " + book.getName());
            System.out.println("Descrição: " + book.getDescription());
            System.out.println("Preço: " + book.getPrice());
            System.out.println("Quantidade: " + book.getQuantity());
        } else {
            System.out.println("Erro: Índice inválido.");
        }
        System.out.println("-----------------------------------------------------");
    }

    private static void removerLivro() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Remover livro pela numeração única");
        visualizarLivros();
        System.out.print("Digite a numeração única do livro a ser removido: ");
        int bookId = input.nextInt();
        input.nextLine(); // Consumir a quebra de linha

        if (bookId >= 0 && bookId < books.size()) {
            Book bookToRemove = books.get(bookId);
            System.out.println("Remover o livro: " + bookToRemove.getName() + "? (1 para sim, qualquer outra tecla para cancelar)");
            int confirm = input.nextInt();
            input.nextLine(); // Consumir a quebra de linha

            if (confirm == 1) {
                books.remove(bookId);
                System.out.println("Livro removido.");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Erro: Índice inválido.");
        }
        System.out.println("-----------------------------------------------------");
    }

    private static String editarCampo(String atual, String nomeCampo) {
        System.out.println("Valor atual de " + nomeCampo + ": " + atual);
        System.out.print("Digite o novo valor (ou deixe vazio para manter o atual): ");
        String novoValor = input.nextLine();
        return novoValor.isEmpty() ? atual : novoValor;
    }

    // Classe Book
    static class Book {
        private String name;
        private String description;
        private float price;
        private int quantity;

        public Book(String name, String description, float price, int quantity) {
            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Classe User
    static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
