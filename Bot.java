
import java.util.Random;

public class Bot extends Player{
    
    // type used for the bots logic, either random movement, player follower or off
    private int type = 0;

    // bot innit uses the player innit but then checks it isnt on the player and sets the type
    public Bot(int type, int Leng, int Height, Map map, Player pc,Main main){
        super(map, Leng, Height);
        if (super.GetLocalex() == pc.GetLocalex() && super.GetLocaley() == pc.GetLocaley()) {
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
            else{
                // if the bot cant go anywhere turns it off, should never happen as would mean the player is also stuck
                super.removexy();
            }
        }
        if (type == 9) {
            super.removexy();
        }
        if (type != 0 && type != 1 && type != 2 && type != 9){
            System.out.println("Incorrect input to bot");
            main.endgame();
            return;
        }
        this.type = type;


    }

    int GetLocalex(){
        return super.GetLocalex();
    }

    int GetLocaley(){
        return super.GetLocaley();
    }


    // function to determine how the bot moves
    void Movement(Map map, Player pc) {
        // if type is 0 then bot moves completely randomly as below
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
        // player follower if type is 1
        else if (this.type == 1) {
            Random rand = new Random();

            int direc = rand.nextInt(3);
            // first checks if the player is in the 5x5 area around the bot
            if (pc.GetLocalex() >= (super.GetLocalex()-2) && pc.GetLocalex() <= (super.GetLocalex()+2) && pc.GetLocaley() >= (super.GetLocaley()-2) && pc.GetLocaley() <= (super.GetLocaley()+2)) {
                // if in the 5x5 the bot looks first if the player is to the left or right and moves accordingly, if the bot is on the same x as the player it will then move up or down accordingly
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
                    // this is just in case of confusion with the bot it will move randomly but not into walls
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
            // if player isnt around the bot will move randomlhy but never into a wall, it will always progress
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
    // this function checks if the bot is on the player, if so it ends the game as the bot caught the player
    void UpdateBot(Map map, Player pc,Main main){
        if (pc.GetLocalex() == super.GetLocalex() && pc.GetLocaley() == super.GetLocaley()) {
            main.endgame();
            System.out.println("Game Over: Bot has caught you");
        }
        
        return;
    }


}
