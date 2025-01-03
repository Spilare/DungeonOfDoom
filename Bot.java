
import java.util.Random;

public class Bot extends Player{
    
    private int type = 0;

    public Bot(int type, int Leng, int Height, Map map, Player pc,Main main){
        super(map, Leng, Height);
        if (type == 9) {
            super.removexy();
        }
        if (type != 0 && type != 1 && type != 2 && type != 9){
            System.out.println("Incorrect input to bot");
            main.endgame();
            return;
        }
        this.type = type;
        // Random rand = new Random();
        // int x = 0;
        // while (x == 0){
        //     this.localex = rand.nextInt(Leng-1);
        //     this.localey = rand.nextInt(Height-1);
        //     try {
        //         if (map.GetLocale(this.localex, this.localey) == '#' || map.GetLocale(this.localex, this.localey) == 'G' || (pc.GetLocalex() == this.localex && pc.GetLocaley() == this.localey)){
        //             x = 0;
        //         }
        //         else {
        //             x = 1;
        //         }
        //     }
        //     catch(Exception e)  {
        //         x = 0;
        //     }
        // }

    }

    int GetLocalex(){
        return super.GetLocalex();
    }

    int GetLocaley(){
        return super.GetLocaley();
    }


    
    void Movement(Map map, Player pc) {
        if (this.type == 0) {
            Random rand = new Random();
            int direc = rand.nextInt(3);

            if (direc == 0) {
                this.Move('N',map,0);
            }
            else if (direc == 1) {
                this.Move('E',map,0);
            }
            else if (direc == 2) {
                this.Move('S',map,0);
            }
            else if (direc == 3) {
                this.Move('W',map,0);
            }
        }
        else if (this.type == 1) {
            Random rand = new Random();

            int direc = rand.nextInt(3);
            if (pc.GetLocalex() >= (super.GetLocalex()-2) && pc.GetLocalex() <= (super.GetLocalex()+2) && pc.GetLocaley() >= (super.GetLocaley()-2) && pc.GetLocaley() <= (super.GetLocaley()+2)) {
                if (pc.GetLocalex() < super.GetLocalex() && map.GetLocale(super.GetLocalex()-1, super.GetLocaley()) != '#') {
                    this.Move('W',map,0);
                }
                else if (pc.GetLocalex() > super.GetLocalex() && map.GetLocale(super.GetLocalex()+1, super.GetLocaley()) != '#') {
                    this.Move('E',map,0);
                }
                else if (pc.GetLocaley() < super.GetLocaley() && map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#' && pc.GetLocalex() == super.GetLocalex()) {
                    this.Move('N',map,0);
                }
                else if (pc.GetLocaley() > super.GetLocaley() && map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#' && pc.GetLocalex() == super.GetLocalex()) {
                    this.Move('N',map,0);
                }
                else {
                    if (direc == 0) {
                        if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                            this.Move('N',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                            this.Move('E',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                            this.Move('S',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                            this.Move('W',map,0);
                        }
                        
                    }
                    else if (direc == 1) {
                        if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                            this.Move('E',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                            this.Move('S',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                            this.Move('W',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                            this.Move('N',map,0);
                        }
                    }
                    else if (direc == 2) {
                        if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                            this.Move('S',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                            this.Move('W',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                            this.Move('N',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                            this.Move('E',map,0);
                        }
                    }
                    else if (direc == 3) {
                        if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                            this.Move('W',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                            this.Move('N',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                            this.Move('E',map,0);
                        }
                        else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                            this.Move('S',map,0);
                        }
                    }
                }
            }
            else {
                if (direc == 0) {
                    if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                        this.Move('N',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                        this.Move('E',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                        this.Move('S',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                        this.Move('W',map,0);
                    }
                    
                }
                else if (direc == 1) {
                    if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                        this.Move('E',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                        this.Move('S',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                        this.Move('W',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                        this.Move('N',map,0);
                    }
                }
                else if (direc == 2) {
                    if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                        this.Move('S',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                        this.Move('W',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                        this.Move('N',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                        this.Move('E',map,0);
                    }
                }
                else if (direc == 3) {
                    if (map.GetLocale(super.GetLocalex() - 1, super.GetLocaley()) != '#'){
                        this.Move('W',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()-1) != '#'){
                        this.Move('N',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex() + 1, super.GetLocaley()) != '#'){
                        this.Move('E',map,0);
                    }
                    else if (map.GetLocale(super.GetLocalex(), super.GetLocaley()+1) != '#'){
                        this.Move('S',map,0);
                    }
                }
            }
        }
        return;
    }

    void UpdateBot(Map map, Player pc,Main main){
        if (pc.GetLocalex() == super.GetLocalex() && pc.GetLocaley() == super.GetLocaley()) {
            main.endgame();
            System.out.println("Game Over: Bot has caught you");
        }
        return;
    }


}
