import java.util.Random;



public class Player {

    private int localex;
    private int localey;
    private int gold = 0;
    
    public Player(Map map, int Leng, int Height) {
        Random rand = new Random();
        int x = 0;
        while (x == 0){
            this.localex = rand.nextInt(Leng-1);
            this.localey = rand.nextInt(Height-1);
            try {
                if (map.GetLocale(this.localex, this.localey) == '#' || map.GetLocale(this.localex, this.localey) == 'G'){
                    x = 0;
                }
                else {
                    x = 1;
                }
            }
            catch(Exception e)  {
                x = 0;
            }
        }

    }

    int GetLocalex() {
        return this.localex;
    }
    int GetLocaley() {
        return this.localey;
    }
    int GetGold() {
        return this.gold;
    }

    void Move(char direction, Map map) {

        if (direction == 'S') {
            try {
                if (map.GetLocale(this.localex, this.localey + 1) == '#') {
                    System.out.println("Fail");
                }
                else {
                    this.localey = this.localey + 1;
                    System.out.println("Success");
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                System.out.println("Fail");
            }
        }
        else if (direction == 'E') {
            try {
                if (map.GetLocale(this.localex + 1, this.localey) == '#') {
                    System.out.println("Fail");
                }
                else {
                    this.localex = this.localex + 1;
                    System.out.println("Success");
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                System.out.println("Fail");
            }
        }
        else if (direction == 'N') {
            try {
                if (map.GetLocale(this.localex, this.localey - 1) == '#') {
                    System.out.println("Fail");
                }
                else {
                    this.localey = this.localey - 1;
                    System.out.println("Success");
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                System.out.println("Fail");
            }
        }
        else if (direction == 'W') {
            try {
                if (map.GetLocale(this.localex - 1, this.localey) == '#') {
                    System.out.println("Fail");
                }
                else {
                    this.localex = this.localex - 1;
                    System.out.println("Success");
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                System.out.println("Fail");
            }
        }
        else {
            System.out.println("Error in input");
        }
    }

    void IncreaseGold(int amount){
        this.gold = this.gold + amount;
        return;
    }

}
