import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
    private BufferedReader socketInput;
    private PrintWriter socketOutput;
    private BufferedReader standardInput;

    public Client(String serverName, int portNumber){
        try {
            clientSocket = new Socket(serverName, portNumber);

            // keyboard reader
            standardInput = new BufferedReader(new InputStreamReader(System.in));

            socketOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            socketInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for client to send and receive messages to and from the server
     */
    public void runClient() {
        System.out.println("Connected to server, waiting on other player.");

        try{
            while(true){

                String response = "";
                // Read a print responses frm the server, Null means stop reading
                while(true){

                    response = socketInput.readLine();
                    // full message has been received from the server
                    if(response.contains("\0")){
                        response = response.replace("\0", "");
                        System.out.println(response);
                        break; // break out of inner while
                    }

                    // game is over, end communication
                    if(response.equals("QUIT")){
                        return; // breaks out of both while loops
                    }
                    System.out.println(response);
                }

                // Read input from user and send to server only if colon flag is specified
                if(response.contains(":")) {
                    response = standardInput.readLine();
                    socketOutput.println(response);
                    socketOutput.flush();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            // close communication
            socketOutput.close();
        }

    }

    public static void main(String[] args){
        Client client = new Client("localhost", 9090);

        client.runClient();
    }

}
