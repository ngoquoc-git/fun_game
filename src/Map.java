import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Map {
    private char[][] map;
    private boolean[][] revealed;

    /**
     * Create a map sized 5x5
     * Reveal trace
     */
    public Map(){
        map= new char[5][5];
        revealed = new boolean[5][5];
    }

    /**
     * Load map for each level
     * Hidden map property with revealed = false
     * @param mapNum 1, 2, or 3 from map's text files
     */
    public void loadMap(int mapNum){
        try{
            File readMapFile = new File("Map" + mapNum + ".txt");
            Scanner sc = new Scanner(readMapFile);

            while(sc.hasNextLine()){
                for(int i = 0; i < map.length; i++){
                    String mapLine = sc.nextLine().replace(" ", "");
                    for(int j = 0; j < map.length; j++){
                        map[i][j] = mapLine.charAt(j);
                        revealed[i][j] = false;
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * Get char at point p
     * @param p location of character
     * @return character at a given point
     */
    public char getCharAtLoc(Point p) {return map[p.x][p.y];}

    public void displayMap(Point p){
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map.length; j++){
                if(p.x == i && p.y == j) System.out.print('*' + " ");
                else if (revealed[i][j]) System.out.print(map[i][j] + " ");
                else    System.out.print("X ");
            }
        }
    }
}
