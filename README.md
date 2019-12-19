# TicTacToe Game

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