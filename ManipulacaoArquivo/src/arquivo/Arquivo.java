/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author Hemerson
 */
public class Arquivo {

    String diretorio = "arquivo.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

    /**
     * Metodo que le de um arquivo externo
     */
    public void leitura() {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(diretorio));
            String linha = null;
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
            leitor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que escreve em um arquivo externo
     * FileWriter: true escreve a partir do que ja existe, false comeca do inicio
     * PrintWriter: escreve no arquivo
     */
    public void escrita() {

        try {
            //cria novo arquivo
            PrintWriter saida = new PrintWriter(new FileWriter(new File(diretorio), false));

            //escrita no arquivo
            for (int i = 0; i < 100; i++) {
                saida.println("Ola" + (i + 1));
            }
            //fim escrita no arquivo

            saida.close(); //fechar arquivo na ordem contraria que abriu
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que copia o conteudo do primeiro arquivo para o segundo
     * @param src arquivo de origem
     * @param dst arquivo de destino
     * @throws java.io.IOException
     */
    public void copy(File src, File dst) throws IOException {
        InputStream inputStream = new FileInputStream(src);
        OutputStream outputStream = new FileOutputStream(dst);
        int len;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }
}
