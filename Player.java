import java.util.Random;



public class Player {

    private int localex;
    private int localey;
    private int gold;
    
    public Player(Map map, int Leng, int Height) {
        Random rand = new Random();
        int x = 0;
        while (x == 0){
            this.localex = rand.nextInt(Leng-1);
            this.localey = rand.nextInt(Height-1);
            if (map.GetLocale(this.localex, this.localey) == '#' || map.GetLocale(this.localex, this.localey) == 'G'){
                x = 0;
            }
            else {
                x = 1;
            }
        }

    }

    int GetLocalex() {
        return this.localex;
    }
    int GetLocaley() {
        return this.localey;
    }



}
