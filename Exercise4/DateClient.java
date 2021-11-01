package Exercise4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DateClient {
    private PrintWriter socketOut;
    private Socket socket;
    private BufferedReader stdIn;
    private BufferedReader socketIn;

    public DateClient(String serverName, int portNumber) {
        try {
            socket = new Socket(serverName, portNumber);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public void communicate()  {
        String line = "";
        String response = "";

        while (true) {
            try {
                System.out.println("Please select an option (DATE/TIME): ");
                //waits here till user inputs
                line = stdIn.readLine();

                //if the user does not choose QUIT
                if (!line.equals("QUIT")){
                    //print line to client
                    System.out.println(line);
                    //sends line to server
                    socketOut.println(line);
                    //reads from server
                    response = socketIn.readLine();
                    //prints response to client
                    System.out.println(response);
                }else{
                    break;
                }
            } catch (IOException e) {
                System.out.println("Sending error: " + e.getMessage());
            }
        }
        try {
            stdIn.close();
            socketIn.close();
            socketOut.close();
        } catch (IOException e) {
            System.out.println("Closing error: " + e.getMessage());
        }

    }

    public static void main(String[] args) throws IOException  {
        DateClient aClient = new DateClient("localhost", 9090);
        aClient.communicate();
    }

    }
