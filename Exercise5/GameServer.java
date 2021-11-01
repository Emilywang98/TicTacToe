import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private ServerSocket serverSocket;
    private ExecutorService pool;


    public GameServer(){
        try {
            serverSocket = new ServerSocket(9090);
            pool = Executors.newFixedThreadPool(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * runServer is responsible for accepting client requests and
     * adding and running the game on new threads as needed.
     */
    public void runServer(){
        try{

            while(true) {

                // wait for two players to connect
                Player xPlayer = new Player(serverSocket.accept(), 'X');
                Player oPlayer = new Player(serverSocket.accept(), 'O');

                // players connected, set the ref and players
                Referee theRef = new Referee();
                theRef.setxPlayer(xPlayer);
                theRef.setoPlayer(oPlayer);

                Game game = new Game(); // create instance of the game
                game.appointReferee(theRef);
                pool.execute(game); // start game on new thread
            }
        }catch (IOException e){
            e.printStackTrace();
            pool.shutdown();
        }

        pool.shutdown();
    }

    public static void main(String[] args){
        GameServer theServer = new GameServer();
        System.out.println("Server is running.");
        theServer.runServer();
    }
}
