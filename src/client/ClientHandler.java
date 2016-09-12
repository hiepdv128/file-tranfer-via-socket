package client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by DaoHiep on 12/09/2016.
 */
public class ClientHandler {

    private final String HOST_NAME = "localhost";
    private final int PORT = 8800;
    private Socket socket;

    public static void main(String[] args) {

        new ClientHandler().clientReceiveFile("file-receive.rar");

    }

    public void clientReceiveFile(String filePath) {
        byte[] buff = new byte[2048];
        try (Socket socketClient = socket = new Socket(HOST_NAME, PORT);
             InputStream inputStream = socketClient.getInputStream();
             BufferedOutputStream streamWriteFile = new BufferedOutputStream(new FileOutputStream(filePath))) {
            int byteReaded;
            while ((byteReaded = inputStream.read(buff)) > 0) {
                streamWriteFile.write(buff, 0, byteReaded);
                streamWriteFile.flush();
            }

            System.out.println("Received file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
