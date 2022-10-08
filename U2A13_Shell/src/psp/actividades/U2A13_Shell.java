package psp.actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class U2A13_Shell {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
            String comando = teclado.next();
            ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));
            Process hijo = pb.start();
            recibeHijo(hijo);
        } catch (IOException ex) {
            System.err.println("Comando incorrecto");
            ex.printStackTrace();
        }

    }

    static void recibeHijo(Process p) throws IOException {
        BufferedReader processOutput
                = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String linea;
        while ((linea = processOutput.readLine()) != null) {
            System.out.println("> " + linea);
        }
        processOutput.close();
    }

}
