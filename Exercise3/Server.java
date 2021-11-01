package Exercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket aSocket;
    private ServerSocket serverSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public Server(){
        try {
            serverSocket = new ServerSocket(8099);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkPalindrome() {
        String line = "";
        while(true){
            try{
                line = socketIn.readLine();
                if (line.equals("QUIT")){
                    line = "Good Bye!";
                    socketOut.println(line);
                    break;
                }
                String linePal = "";
                String answer = "";

                char ch;

                int j = line.length();
//                for(int i = 0 ; i < line.length(); i++){
//                    ch = line.charAt(i);
//                    linePal.at(j) += ch;
//                }
                for(int i = line.length() - 1 ; i >= 0; i--){
                    ch = line.charAt(i);
                    linePal+=ch;
                    j--;
                }

                if(line.equals(linePal)){
                    answer = line + " is a palindrome.";
//                   socketOut.println(line + "is a palindrome. ");
                }
                else{
                    answer = line + " is not a palindrome.";
//                  socketOut.println(line + "is not a palindrome. ");
                }
                socketOut.println(answer);

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        //create instance of my server
        Server myServer = new Server();

        try {
            myServer.aSocket = myServer.serverSocket.accept(); //accepting connection
            myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
            myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(), true);
            myServer.checkPalindrome();
            myServer.socketIn.close();
            myServer.socketOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
