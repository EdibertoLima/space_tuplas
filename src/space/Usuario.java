/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

/**
 *
 * @author ediberto
 */
import java.util.Scanner;
import net.jini.space.JavaSpace;

public class Usuario {

    private static JavaSpace space;
    private String clientName;

    /**
     * @param args the command line arguments
     */
    public Usuario() {
        System.out.println("Procurando pelo servico JavaSpace...");
        Lookup finder = new Lookup(JavaSpace.class);
        space = (JavaSpace) finder.getService();
        if (space == null) {
            System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
            System.exit(-1);
        }
        System.out.println("O servico JavaSpace foi encontrado.");
    }

    public static void main(String[] args) {
        // TODO code application logic here

        Usuario u = new Usuario();
        Scanner scanner = new Scanner(System.in);
        Iterf_tuplas t = new Iterf_tuplas();
        while (true) {

            System.out.println("--------------Menu--------------");
            System.out.print("-------Client: ");

            System.out.println("-------------");
            System.out.println("1  - Iniciar chat");
            System.out.println("3  - criar usario");
            System.out.println("4  - Criar ambiente");
            System.out.println("5  - Adicionar usuario a um ambiente");
            System.out.println("6  - Trocar usuario de ambiente");
            System.out.println("7  - Listar usuarios de um ambiente");
            System.out.println("8  - Criar dispositivo");
            System.out.println("9  - Destruir Ambiente");
            System.out.println("10 - Adicionar dispositivo a um ambiente");
            System.out.println("11 - Listar dispositivos de um ambiente");
            System.out.println("12 - Trocar dispositivo de ambiente");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    //MessegerChat cht = new MessegerChat();
                    //cht.setVisible(true);
                    System.out.println("Digite nome do usuario para receber mensagem");
                    String aa = scanner.nextLine();
                    System.out.println("Digite nome do usuario para receber mensagem");
                    String bb = scanner.nextLine();
                    t.chat(space, aa, bb);

                    break;

                case "3":
                    System.out.println("Digite nome do usuario");
                    aa = scanner.nextLine();
                    t.AddUser(space, aa);
                    break;
                case "4":

                    String a = t.Add_Amb(space);
                    System.out.println("Ambiente " + a + " criado");

                    break;
                case "5":
                    System.out.println("Digite nome do usuario");
                    aa = scanner.nextLine();
                    System.out.println("Digite nome do Ambiente");
                    String b = scanner.nextLine();
                    t.AddUserAmb(space, aa, b);

                    break;
                case "6":
                    System.out.println("Digite nome do usuario");
                    aa = scanner.nextLine();
                    System.out.println("Digite nome do novo Ambiente");
                    b = scanner.nextLine();
                    t.TrocarAmbUser(space, aa, b);
                    break;
                case "7":
                    System.out.println("Digite nome do Ambiente");
                    b = scanner.nextLine();
                    t.ListUsers(space, b);
                    break;
                case "8":
                    System.out.println("Digite nome do Dispositivo");
                    b = scanner.nextLine();
                    t.AddDisp(space, b);
                    break;
                case "9":
                    System.out.println("Digite nome do Ambiente");
                    b = scanner.nextLine();
                    t.DeleteAmb(space, b);
                    break;
                case "10":
                    System.out.println("Digite nome do dispositivo");
                    aa = scanner.nextLine();
                    System.out.println("Digite nome do Ambiente");
                    b = scanner.nextLine();
                    t.AddDispAmb(space, aa, b);
                    break;
                case "11":
                    System.out.println("Digite nome do Ambiente");
                    b = scanner.nextLine();
                    t.ListDip(space, b);
                    break;
                case "12":
                    System.out.println("Digite nome do dispositivo");
                    aa = scanner.nextLine();
                    System.out.println("Digite nome do novo Ambiente");
                    b = scanner.nextLine();
                    t.TrocarAmbDisp(space, aa, b);
                    break;
            }
            System.out.println("precione a tecla enter para continuar..");
            scanner.nextLine();

        }
    }

}
