package folder;import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int opcao;
        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Listar");
            System.out.println("2. Inserir");
            System.out.println("3. Excluir");
            System.out.println("4. Atualizar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    listarUsuarios(usuarioDAO);
                    break;
                case 2:
                    inserirUsuario(scanner, usuarioDAO);
                    break;
                case 3:
                    excluirUsuario(scanner, usuarioDAO);
                    break;
                case 4:
                    atualizarUsuario(scanner, usuarioDAO);
                    break;
                case 5:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void listarUsuarios(UsuarioDAO usuarioDAO) {
        List<Usuario> usuarios = usuarioDAO.get();
        System.out.println("----- Lista de Usuários -----");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        System.out.println("-----------------------------");
    }

    private static void inserirUsuario(Scanner scanner, UsuarioDAO usuarioDAO) {
        System.out.print("Digite o código do usuário: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        System.out.print("Digite o sexo do usuário (M/F): ");
        char sexo = scanner.nextLine().charAt(0);

        Usuario novoUsuario = new Usuario(codigo, nome, senha, sexo);
        boolean inserido = usuarioDAO.insert(novoUsuario);

        if (inserido) {
            System.out.println("Usuário inserido com sucesso!");
        } else {
            System.out.println("Falha ao inserir o usuário. Verifique os dados e tente novamente.");
        }
    }

    private static void excluirUsuario(Scanner scanner, UsuarioDAO usuarioDAO) {
        System.out.print("Digite o código do usuário que deseja excluir: ");
        int codigo = scanner.nextInt();
        boolean excluido = usuarioDAO.delete(codigo);

        if (excluido) {
            System.out.println("Usuário excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir o usuário. Verifique o código e tente novamente.");
        }
    }

    private static void atualizarUsuario(Scanner scanner, UsuarioDAO usuarioDAO) {
        System.out.print("Digite o código do usuário que deseja atualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        Usuario usuarioExistente = usuarioDAO.get(codigo);

        if (usuarioExistente != null) {
            System.out.print("Digite o novo nome do usuário (ou pressione Enter para manter o mesmo): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) {
                usuarioExistente.setNome(novoNome);
            }

            System.out.print("Digite a nova senha do usuário (ou pressione Enter para manter a mesma): ");
            String novaSenha = scanner.nextLine();
            if (!novaSenha.isEmpty()) {
                usuarioExistente.setSenha(novaSenha);
            }

            System.out.print("Digite o novo sexo do usuário (M/F ou pressione Enter para manter o mesmo): ");
            String novoSexo = scanner.nextLine();
            if (!novoSexo.isEmpty()) {
                usuarioExistente.setSexo(novoSexo.charAt(0));
            }

            boolean atualizado = usuarioDAO.update(usuarioExistente);

            if (atualizado) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar o usuário. Verifique os dados e tente novamente.");
            }
        } else {
            System.out.println("Usuário não encontrado com o código informado.");
        }
    }
}


public class Principal {

}
