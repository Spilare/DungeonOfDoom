import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private boolean bool = false;


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

    void look(Map map, Player pc){
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
        Map map1 = new Map("Map1.txt");
        if (map1.GetBool() == false){
            return;
        }
        Player pc = new Player(map1, map1.GetLength(), map1.GetHeight());
        Main main = new Main();

        Scanner input = new Scanner(System.in);

        while (main.getbool() == false) {
            //map1.PrintWholeMap(pc); FOR TESTING
            String y = input.nextLine();
            if (y.contentEquals("MOVE N") == true || y.contentEquals("MOVE W") == true || y.contentEquals("MOVE S") == true || y.contentEquals("MOVE E") == true) {
                char z = y.charAt(5);
                pc.Move(z, map1);
            }
            else if(y.contentEquals("HELLO") == true){
                main.Hello(map1);
            }
            else if(y.contentEquals("GOLD") == true){
                main.gold(pc);
            }
            else if (y.contentEquals("PICKUP") == true){
                main.pickup(map1,pc,main);
            }
            else if (y.contentEquals("QUIT") == true) {
                main.quit(map1,pc);
            }
            else if (y.contentEquals("LOOK") == true) {
                main.look(map1,pc);
            }
            else {
                System.out.println("Incorrect Input");
            }

        }

        System.out.println("Press enter to exit:");
        input.nextLine();
        input.close();





    }
}
