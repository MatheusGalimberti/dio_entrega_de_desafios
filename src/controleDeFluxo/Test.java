package controleDeFluxo;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       /* String nome = sc.next();
        int id = sc.nextInt();

        String identificador = String.format(nome.toLowerCase()+ "-" + id)
                                .replace(" ", "");

        System.out.println(identificador);
        */

        String ip = sc.nextLine();

        String[] parteIp = ip.split("\\.");
        System.out.println(parteIp.length);


        sc.close();
    }
}
