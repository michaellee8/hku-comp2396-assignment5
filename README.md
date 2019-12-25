# TicTacToe Game

GitHub: https://github.com/michaellee8/hku-comp2396-assignment5

## Architecture
This game follows a client-server architecture.

### Server
The server component of this game act as a controller of the client. It accepts a `GameAction`, 
which includes the side that the player is assuming to, the a string action name and a string 
payload from the client, respond to the action, and then update the `GameState` accordingly, 
populate it back to the client. 
 
### Client
The client component of this game act as a renderer of the server. It receives `GameState` from 
server and render it accordingly, and sends back `GameAction` to the server upon user interaction 
or client events.

## How to run
1. Open a terminal in the root folder of this repo. It is assumed that you are using Linux.
2. `mkdir -p out`
3. Compile the code with `javac src/assignment5/*.java -d out`
4. `cd out`
5. Run the server component with `java assignment5.TicTacToeServer`
6. For each GUI client you need to start (mostly 2), open a new terminal in the root directory of 
this repo, and enter `cd out && java assignment5.TicTacToeClient && cd ../`.