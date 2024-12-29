import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Map {

    private String mapname;
    private String Mapdetails = "";
    private int Length = 0;
    private int Height = 0;

    public Map(String mapnamein) throws FileNotFoundException {
        this.mapname = mapnamein;
        File map = new File(mapnamein);
        Scanner input = new Scanner(map);
        int i = 0;
        while (input.hasNextLine()){
            String ln = input.nextLine();
            String sep = "/n";
            ln = ln.concat(sep);
            this.Mapdetails = this.Mapdetails.concat(ln);
            if (i == 0){
                this.Length = this.Mapdetails.length() - 2;
            }
            i += 1;
        }
        this.Height = i;
        input.close();
    }
    public String GetMap() {
        return this.Mapdetails;
    }
    public int GetLength() {
        return this.Length;
    }
    public int GetHeight(){
        return this.Height;
    }
    // y starts at 0
    public char GetLocale(int x, int y) {
        int line = 32 * y;
        int loc = line + x;
        char info = this.Mapdetails.charAt(loc);
        return info;
    }
    public void PrintWholeMap(Player pc){
        int xloc = pc.GetLocalex();
        int yloc = pc.GetLocaley();
        int line = 32 * yloc;
        int loc = line + xloc;
        String map = this.Mapdetails;
        StringBuilder mapstr = new StringBuilder(map);
        mapstr.setCharAt(loc, 'P');
        map = mapstr.toString();
        String[] arr = map.split("/n");
        int i = 0;
        while (i < arr.length) {
            System.out.println(arr[i]);
            i += 1;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Map map1 = new Map("Map1.txt");
        Player pc = new Player(map1, map1.GetLength(), map1.GetHeight());

        String x = map1.GetMap();
        int y = map1.GetLength();
        char a = map1.GetLocale(29,1);
        int b = pc.GetLocalex();
        int c = pc.GetLocaley();
        System.out.println(a);
        System.out.println(x);
        System.out.println(y);
        System.out.println(b);
        System.out.println(c);
        map1.PrintWholeMap(pc);
        
    
    
    }



}

