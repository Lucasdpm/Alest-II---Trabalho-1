import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadMap {
    private PathType pathType;
    private char[][] map;
    private ArrayList<Integer> money;

    public ReadMap() {
        this.map = getMap();
        this.money = new ArrayList<Integer>();

        init();
    }

    public void init() {
        followPath(findStart());
    }

    public void followPath(int start) {
        char currentChar = map[start][0];
        int[] currentPos = { start, 0 };
        Direction dir = Direction.RIGHT;
        boolean isNumber = false;
        String stringNum = "";
        int number;

        while (currentChar != '#') {
            switch (currentChar) {
                case '-':
                    isNumber = false;
                    if (dir == Direction.RIGHT) {
                        currentPos[1]++;
                    } else if (dir == Direction.LEFT) {
                        currentPos[1]--;
                    }

                    break;

                case '|':
                    isNumber = false;
                    if (dir == Direction.UP) {
                        currentPos[0]--;
                    } else if (dir == Direction.DONW) {
                        currentPos[0]++;
                    }

                    break;

                case '/':
                    isNumber = false;
                    if (dir == Direction.RIGHT) {
                        currentPos[0]--;
                    } else if (dir == Direction.LEFT) {
                        currentPos[0]++;
                    } else if (dir == Direction.UP) {
                        currentPos[1]++;
                    } else if (dir == Direction.DONW) {
                        currentPos[1]--;
                    }

                    break;

                case '\\':
                    isNumber = false;
                    if (dir == Direction.RIGHT) {
                        currentPos[0]++;
                    } else if (dir == Direction.LEFT) {
                        currentPos[0]--;
                    } else if (dir == Direction.UP) {
                        currentPos[1]--;
                    } else if (dir == Direction.DONW) {
                        currentPos[1]++;
                    }

                    break;

                case '#':
                    break;

                default:
                    isNumber = true;
                    stringNum += currentChar;

                    break;
            }
        }
    }

    public int findStart() {
        char currentChar = map[0][0];
        int row = 0;
        while (currentChar != '-') {
            currentChar = map[row][0];
            row++;
        }
        return row;
    }

    public char[][] getMap() {
        String filePath = "D:\\SEMESTRE 3\\Alest II\\Trab1\\casos-cohen-noite\\casoG50.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            int countNum = 0;
            String[] mapSizeString = { "", "" };

            while (countNum < 2) {
                char currentChar = (char) br.read();

                if (currentChar == ' ' || currentChar == '\n') {
                    countNum++;
                } else {
                    mapSizeString[countNum] += currentChar;
                }
            }
            br.readLine();

            int[] mapSize = { Integer.parseInt(mapSizeString[0]), Integer.parseInt(mapSizeString[1]) };
            char[][] map = new char[mapSize[0]][mapSize[1]];

            for (int row = 0; row < mapSize[0] - 1; row++) {
                for (int col = 0; col < mapSize[1] - 1; col++) {
                    map[row][col] = (char) br.read();
                }
            }

            return map;

        } catch (Exception error) {
            System.out.println("ERRO: " + error);
        }

        return null;
    }
}
