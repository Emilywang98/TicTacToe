[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6128869&assignment_repo_type=AssignmentRepo)

## Tic-Tac-Toe Game
Using thread pools that supports multiple games simultaneously. Each game has it's own thread and the communication between the players is managed through the user of sockets. Utilizes client and servers where the client side holds the user interface (command line interface) and the server side implements the Runnable interface. 

The server utilizes ServerSocket which waits for requests to come in over the network, and ExecutorService which is an interface that allows the execution of tasks on threads asynchronously. 

