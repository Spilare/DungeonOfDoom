import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;




public class Map {

    private String mapname;
    private String[] Mapdetails = {};
    private int Gold = 0;
    private int Length = 0;
    private int Height = 0;
    private boolean bool = false;


    // Innit function that instantiates the map details into relative information
    public Map(String mapnamein) throws FileNotFoundException {
        this.mapname = mapnamein;
        // Below is code to ensure the file path for maps is correct
        String filepath = new File("DungeonOfDoom.jar").getAbsolutePath();
        StringBuilder filepathbld = new StringBuilder(filepath);
        filepathbld.delete(filepath.length()-17,filepath.length());
        filepath = filepathbld.toString();
        String addition = "DoDmaps";
        filepath = filepath.concat(addition);
        char slash = filepath.charAt(filepath.length()-8);
        String slashs = "" + slash;
        filepath = filepath.concat(slashs);
        File map = new File(filepath.concat(mapnamein));
        Scanner input = new Scanner(map);


        int i = 0;
        String name = input.nextLine();
        // Collecting the first two input information of the map, ensures the map is correctly formatted
        if (name.contains("name") != true){
            System.out.println("Map incorrect format");
            input.close();
            return;
        }
        this.mapname = name.replace("name ", "");
        String Gld = input.next();
        if (Gld.contains("win") != true){
            System.out.println("Map incorrect format");
            input.close();
            return;
        }
        try{
            this.Gold = input.nextInt();
        }
        catch (Exception e){
            System.out.println("Map incorrect format");
            input.close();
            return;
        }
        input.nextLine();
        String mapplace = "";
        // This function collects all of the map into a single string with fake seperators that are taken away later
        while (input.hasNextLine()){
            String ln = input.nextLine();
            String sep = "/n";
            ln = ln.concat(sep);
            mapplace = mapplace.concat(ln);
            if (i == 0){
                this.Length = mapplace.length() - 2;
            }
            i += 1;
        }
        // below splits the map into its relative rows
        this.Mapdetails = mapplace.split("/n");
        this.Height = i;
        input.close();
        this.bool = true;
        return;
    }

    public String[] GetMap() {
        return this.Mapdetails;
    }
    public int GetLength() {
        return this.Length;
    }
    public int GetHeight(){
        return this.Height;
    }
    public String GetName(){
        return this.mapname;
    }
    public boolean GetBool(){
        return this.bool;
    }
    public int GetGold(){
        return this.Gold;
    }


    // y starts at 0
    // function below returns the feature at a specific location, if it does not exist then it is a wall
    public char GetLocale(int x, int y) {
        String[] arr = this.Mapdetails;
        char info;
        try {
            info = arr[y].charAt(x);
        }
        catch (StringIndexOutOfBoundsException e) {
            info = '#';
        }
        return info;
    }

    // function to be able to update map location, this is used to place the bot at specific locations as needed
    public void UpdateMap(int x, int y, char c){
        
        String[] arr = this.Mapdetails;
        StringBuilder mp = new StringBuilder(arr[y]);
        mp.setCharAt(x, c);
        this.Mapdetails[y] = mp.toString();
        return;
    }

    // This function was used during testing to ensure everything is properly placed
    public void PrintWholeMap(Player pc, Bot bot){
        int xloc = pc.GetLocalex();
        int yloc = pc.GetLocaley();
        int xbot = bot.GetLocalex();
        int ybot = bot.GetLocaley();
        // below is the code to place the player and bot into the map
        String[] arr = this.Mapdetails;
        String place = arr[yloc];
        String placebot = arr[ybot];
        StringBuilder mapstr = new StringBuilder(place);
        StringBuilder mapstrbot = new StringBuilder(placebot);
        mapstr.setCharAt(xloc, 'P');
        mapstrbot.setCharAt(xbot, 'B');
        place = mapstr.toString();
        placebot = mapstrbot.toString();
        
        int i = 0;
        while (i < arr.length) {
            if (i == bot.GetLocaley()) {
                System.out.println(placebot);
            }
            else if (i == yloc){
                System.out.println(place);
            }
            else{
                System.out.println(arr[i]);
            }
            i += 1;
        }
    }

    



}

