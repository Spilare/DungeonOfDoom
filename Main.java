import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private boolean bool = false;
    private boolean gamestart = false;


    void Hello(Map map){
        int gold = map.GetGold();
        System.out.print("Gold to win: ");
        System.out.println(gold);
        return;
    }
    void gold(Player pc){
        System.out.print("Gold Owned: ");
        int gold = pc.GetGold();
        System.out.println(gold);
        return;
    }
    void pickup(Map map, Player pc,Main main){
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
        main.gold(pc);
        return;
    }
    void quit (Map map, Player pc){
        char pos = map.GetLocale(pc.GetLocalex(), pc.GetLocaley());
        int GoldOwned = pc.GetGold();
        int GoldReq = map.GetGold();
        if (pos == 'E' && GoldOwned == GoldReq){
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

    void gamestart() {
        this.gamestart = true;
    }
    
    void endgame() {
        this.bool = true;
    }

    void look(Map map, Player pc, Bot bot){
        int x = -2;
        int i = -2;
        int loc = 0;
        int xloc = pc.GetLocalex();
        int yloc = pc.GetLocaley();
        char[] line = {' ',' ',' ',' ',' '};
        while (x < 3) {
            i = -2;
            loc = 0;
            line[0] = ' ';
            line[1] = ' ';
            line[2] = ' ';
            line[3] = ' ';
            line[4] = ' ';
            while (i < 3){
                
                try{
                    line[loc] = (map.GetLocale(xloc + i, yloc + x));
                }
                catch (Exception e) {
                    line[loc] = '#';
                }
                if (x == 0 && i == 0) {
                    line[loc] = 'P';
                }
                else if (bot.GetLocalex() == xloc + i && bot.GetLocaley() == yloc + x) {
                    line[loc] = 'B';
                }
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
            Main main = new Main();
            Scanner input = new Scanner(System.in);
            String mapname = "";
            int type = 9;
            while (main.getgame() == false){
                System.out.println("Dungeon of Doom");
                System.out.println("Press enter to start:");
                input.nextLine();
                main.gamestart();
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
            Map map1 = new Map(mapname);
            if (map1.GetBool() == false){
                input.close();
                return;
            }
            Player pc = new Player(map1, map1.GetLength(), map1.GetHeight());
            Bot bot = new Bot(type,map1.GetLength(),map1.GetHeight(),map1,pc,main);
            

            while (main.getbool() == false) {
                //map1.PrintWholeMap(pc,bot);
                String y = input.nextLine();
                if (y.contentEquals("MOVE N") == true || y.contentEquals("MOVE W") == true || y.contentEquals("MOVE S") == true || y.contentEquals("MOVE E") == true) {
                    char z = y.charAt(5);
                    pc.Move(z, map1,1);
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

            System.out.println("Press enter to exit:");
            input.nextLine();
            input.close();
            main.endgame();





    }
}
