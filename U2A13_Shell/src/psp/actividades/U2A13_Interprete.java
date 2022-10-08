package psp.actividades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class U2A13_Interprete {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String comando = "java psp.actividades.U2A13_Shell";
        ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));
        pb.directory(new File("build/classes"));

        try {
            Process hijo = pb.start();

            while (!comando.equals("exit")) {
                System.out.println("Introduce un comando: ");
                comando = teclado.next();
                enviaHijo(hijo, comando);

                recibeHijo(hijo);
            }
            System.out.println("La ejecuccion ha terminado");

        } catch (IOException ex) {
            System.err.println("Error al lanzar procesos");
            ex.printStackTrace();
        }
    }

    static void enviaHijo(Process p, String datos) throws UnsupportedEncodingException {
        PrintWriter toProcess = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                p.getOutputStream(), "UTF-8")), true);
        toProcess.println(datos);
    }

    static void recibeHijo(Process p) throws IOException {
        BufferedReader processOutput
                = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String linea;
        while ((linea = processOutput.readLine()) != null) {
            System.out.println(linea);
        }
        processOutput.close();
    }

}
