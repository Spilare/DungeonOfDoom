import java.io.FileNotFoundException;
import java.util.Scanner;

// this class includes game logic and input functions
public class Main {

    private boolean bool = false;
    private boolean gamestart = false;

    // logic behind the hello command
    void Hello(Map map){
        int gold = map.GetGold();
        System.out.print("Gold to win: ");
        System.out.println(gold);
        return;
    }
    // logic behind gold command
    void gold(Player pc){
        System.out.print("Gold Owned: ");
        int gold = pc.GetGold();
        System.out.println(gold);
        return;
    }
    // logic behind pickup command
    void pickup(Map map, Player pc,Main main){
        // looks at x and y position for player and checks if that is gold in the map
        int locx = pc.GetLocalex();
        int locy = pc.GetLocaley();
        if (map.GetLocale(locx, locy) == 'G'){
            map.UpdateMap(locx, locy, '.');
            pc.IncreaseGold(1);
            System.out.println("Success");
        }
        else {
            System.out.println("Fail");
        }
        // displays the gold similar to gold command as required
        main.gold(pc);
        return;
    }
    // logic behind quit command
    void quit (Map map, Player pc){
        // gets the information of the map at the player position
        char pos = map.GetLocale(pc.GetLocalex(), pc.GetLocaley());
        // gets the amount of gold the player has and the required gold
        int GoldOwned = pc.GetGold();
        int GoldReq = map.GetGold();
        // checks if the position is an exit and that gold reached it greater than or equal to the needed amount
        if (pos == 'E' && GoldOwned >= GoldReq){
            this.bool = true;
            System.out.println("Win");
            return;
        }
        else {
            this.bool = true;
            System.out.println("Lose");
            return;
        }
    }


    boolean getbool(){
        return this.bool;
    }

    boolean getgame(){
        return this.gamestart;
    }

    // used to start the game after determining the map and bot difficulty
    void gamestart() {
        this.gamestart = true;
    }
    // used to end the game
    void endgame() {
        this.bool = true;
    }
    // logic for look command
    void look(Map map, Player pc, Bot bot){
        // x and i are variables that are itterated, start at -2 due to  the 5x5 radius, if at -2 the x and y can simply be added to the x and y position of the player
        int x = -2;
        int i = -2;
        int loc = 0;
        int xloc = pc.GetLocalex();
        int yloc = pc.GetLocaley();
        // char array for the information to be put into
        char[] line = {' ',' ',' ',' ',' '};
        while (x < 3) {
            // resetting variables for the imbedded while loops
            i = -2;
            loc = 0;
            line[0] = ' ';
            line[1] = ' ';
            line[2] = ' ';
            line[3] = ' ';
            line[4] = ' ';
            while (i < 3){
                // testing if the location exists, otherwise the space is a wall
                try{
                    line[loc] = (map.GetLocale(xloc + i, yloc + x));
                }
                catch (StringIndexOutOfBoundsException e) {
                    line[loc] = '#';
                }
                // displays the player in the middle
                if (x == 0 && i == 0) {
                    line[loc] = 'P';
                }
                // then checks if the bot is at the location
                else if (bot.GetLocalex() == xloc + i && bot.GetLocaley() == yloc + x) {
                    line[loc] = 'B';
                }
                // displays the string once itterated through the 5 elements
                if (i == 2) {
                    String outline = new String(line);
                    System.out.println(outline);
                }
                i = i+1;
                loc = loc+1;
            }
            x = x+1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
            // innitiate a main class to use the game logic functions
            Main main = new Main();
            Scanner input = new Scanner(System.in);
            String mapname = "";
            // initiates bot type, base value is 9 which means bot is off
            int type = 9;
            // while loop which ends when the game is turned on in main
            while (main.getgame() == false){
                System.out.println("Dungeon of Doom");
                System.out.println("Press enter to start:");
                input.nextLine();
                main.gamestart();
                // hold is just a variable that can be used to end the loop, allows miss inputs that dont end the game
                int hold = 0;
                while (hold == 0){
                    System.out.println("Choose your map:");
                    System.out.println("0 Custom Map");
                    System.out.println("1: Basic Map");
                    System.out.println("2: Maze of Mystery");
                    String mapin = input.nextLine();
                    if (mapin.contentEquals("1") == true){
                        mapname = "BasicMap.txt";
                        hold = 1;
                    }
                    else if (mapin.contentEquals("2") == true) {
                        mapname = "MazeoMystery.txt";
                        hold = 1;
                    }
                    else if (mapin.contentEquals("0") == true) {
                        System.out.println("Please enter filename including .txt");
                        mapname = input.nextLine();
                        hold = 1;
                    }
                    else {
                        System.out.println("Incorrect Input");
                    }
                }
                System.out.println("Please choose a Bot difficulty, 0 = Random, 1 = Player follower, 9 = No bot:");
                String boton = input.nextLine();
                if (boton.charAt(0) == '0') {
                    type = 0;
                }
                else if (boton.charAt(0) == '1') {
                    type = 1;
                }
                else {
                    type = 9;
                }
                System.out.println("Game Start");

            }
            // initiates map and ensures the map was correctly made, otherwise exits the game
            Map map1 = new Map(mapname);
            if (map1.GetBool() == false){
                input.close();
                return;
            }
            // initiates a player and bot
            Player pc = new Player(map1, map1.GetLength(), map1.GetHeight());
            Bot bot = new Bot(type,map1.GetLength(),map1.GetHeight(),map1,pc,main);
            
            // main game loop which ends when the endgame function is run
            while (main.getbool() == false) {
                //map1.PrintWholeMap(pc,bot); FOR TESTING
                String y = input.nextLine();
                // if statements for the different functions which then run their logic
                if (y.contentEquals("MOVE N") == true || y.contentEquals("MOVE W") == true || y.contentEquals("MOVE S") == true || y.contentEquals("MOVE E") == true) {
                    // takes the end character for the move direction
                    char z = y.charAt(5);
                    pc.Move(z, map1,1);
                    // bot functions run at end of each command to move the bot and update its information
                    bot.Movement(map1,pc);
                    bot.UpdateBot(map1,pc,main);
                }
                else if(y.contentEquals("HELLO") == true){
                    main.Hello(map1);
                    bot.Movement(map1,pc);
                    bot.UpdateBot(map1,pc,main);
                }
                else if(y.contentEquals("GOLD") == true){
                    main.gold(pc);
                    bot.Movement(map1,pc);
                    bot.UpdateBot(map1,pc,main);
                }
                else if (y.contentEquals("PICKUP") == true){
                    main.pickup(map1,pc,main);
                    bot.Movement(map1,pc);
                    bot.UpdateBot(map1,pc,main);
                }
                else if (y.contentEquals("QUIT") == true) {
                    // quit is the only function that does nothing to the bot
                    main.quit(map1,pc);
                }
                else if (y.contentEquals("LOOK") == true) {
                    bot.Movement(map1,pc);
                    bot.UpdateBot(map1,pc,main);
                    main.look(map1,pc,bot);
                }
                else {
                    System.out.println("Incorrect Input");
                    bot.Movement(map1,pc);
                    bot.UpdateBot(map1,pc,main);
                }
                

            }
            // ending the game output
            System.out.println("Press enter to exit:");
            input.nextLine();
            input.close();
            main.endgame();





    }
}
