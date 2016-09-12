package server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;

/**
 * Created by DaoHiep on 12/09/2016.
 */
public class ServerHandler {

    private static int PORT = 8800;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        new ServerHandler().serverSendFile("C:/Users/DaoHiep/Downloads/Documents/Tuan-4.rar");
    }

    public void serverSendFile(String pathFile) {
        byte[] buff = new byte[2048];
        System.out.println("server waiting at port 8800");
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             OutputStream streamSender = serverSocket.accept().getOutputStream();
             BufferedInputStream streamReadFile = new BufferedInputStream(new FileInputStream(pathFile))) {

            int byteReaded;
            while ((byteReaded = streamReadFile.read(buff)) != -1) {
                streamSender.write(buff, 0, byteReaded);
                streamSender.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
