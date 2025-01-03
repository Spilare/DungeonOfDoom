import java.util.Random;



public class Player {

    private int localex;
    private int localey;
    private int gold = 0;
    
    // innit function which randomises starting location
    public Player(Map map, int Leng, int Height) {
        Random rand = new Random();
        int x = 0;
        while (x == 0){
            // creates random ints for x and y position using the height and length of the map
            // important as the map does not need to be rectangular the leng is dependant on the first line of the map
            this.localex = rand.nextInt(Leng-1);
            this.localey = rand.nextInt(Height-1);
            // below checks that the location is not a wall or gold, it does not need to check for the bot as the bot itself ensures it wont be ontop of the player
            try {
                if (map.GetLocale(this.localex, this.localey) == '#' || map.GetLocale(this.localex, this.localey) == 'G'){
                    x = 0;
                }
                else {
                    x = 1;
                }
            }
            catch(StringIndexOutOfBoundsException e)  {
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

    // this function is used to remove the bot or player from the game (bot as it extends the player class)
    void removexy() {
        this.localex = -300;
        this.localey = -300;
        return;
    }

    // function to move the player in a direction, the int talk specifies whether the function should display success or fail
    void Move(char direction, Map map, int talk) {

        if (direction == 'S') {
            // each direction if contains detection for if the location moving to is a wall and if so doesnt move and says fail if talk is 1
            try {
                if (map.GetLocale(this.localex, this.localey + 1) == '#') {
                    if (talk == 1) {
                        System.out.println("Fail");
                    }
                }
                else {
                    this.localey = this.localey + 1;
                    if (talk == 1) {
                        System.out.println("Success");
                    }
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                if (talk == 1) {
                    System.out.println("Fail");
                }
            }
        }
        else if (direction == 'E') {
            try {
                if (map.GetLocale(this.localex + 1, this.localey) == '#') {
                    if (talk == 1) {
                        System.out.println("Fail");
                    }
                }
                else {
                    this.localex = this.localex + 1;
                    if (talk == 1) {
                        System.out.println("Success");
                    }
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                if (talk == 1) {
                    System.out.println("Fail");
                }
            }
        }
        else if (direction == 'N') {
            try {
                if (map.GetLocale(this.localex, this.localey - 1) == '#') {
                    if (talk == 1) {
                        System.out.println("Fail");
                    }
                }
                else {
                    this.localey = this.localey - 1;
                    if (talk == 1) {
                        System.out.println("Success");
                    }
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                if (talk == 1) {
                    System.out.println("Fail");
                }
            }
        }
        else if (direction == 'W') {
            try {
                if (map.GetLocale(this.localex - 1, this.localey) == '#') {
                    if (talk == 1) {
                        System.out.println("Fail");
                    }
                }
                else {
                    this.localex = this.localex - 1;
                    if (talk == 1) {
                        System.out.println("Success");
                    }
                }
            }
            catch (Exception StringIndexOutOfBoundsException) {
                if (talk == 1) {
                    System.out.println("Fail");
                }
            }
        }
        // catch case incase of errors in the input
        else {
            System.out.println("Error in input");
        }
    }

    // function to increment the gold, it contains amount in case of future desire for larger gold piles
    void IncreaseGold(int amount){
        this.gold = this.gold + amount;
        return;
    }

}
