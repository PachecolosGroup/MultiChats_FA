import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        ServerSocket serverSocket = null;

        ///Importante hacer que el numero del puerto sea el mismo que el del cliente para que esten en sintonia,

        serverSocket = new ServerSocket(1234);

        //Tnemos dos whiles en true, para que el servidor continue en servicio

        while (true){
            //Con este socket trabajanos cada vez que el cliente crea o particia en el chat
            socket = serverSocket.accept();

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            //Con este while, podemos mandar mensajes de manera constante.
            while (true){
                String msgFromClient = bufferedReader.readLine();

                System.out.println("Client: " + msgFromClient);
                bufferedWriter.write("Mensaje recibido.");
                bufferedWriter.newLine();
                //Con esto el servidor se limpiara, solo y cuando llegue el mensaje
                bufferedWriter.flush();

                //COn esto el cliente se retira o sale del chat
                if(msgFromClient.equalsIgnoreCase("Adios"))
                    break;

            }

            socket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedWriter.close();
            bufferedReader.close();

        }
    }
}
