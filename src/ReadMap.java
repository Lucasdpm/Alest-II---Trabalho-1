import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadMap {
    private char[][] map;
    private ArrayList<Integer> money;
    public int mapFollowPathCount;

    public ReadMap(int option) {
        this.map = getMap(option);
        this.money = new ArrayList<Integer>();

        init();
        int valorTotal = 0;
        for (int money : this.money) {
            valorTotal += money;
        }
        // System.out.println(valorTotal);
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
        int number = 0;
        boolean pathEnded = false;

        while (!pathEnded) {
            mapFollowPathCount++;

            switch (currentChar) {
                case '-', '|':
                    isNumber = false;
                    if (dir == Direction.RIGHT) {
                        currentPos[1]++;
                    } else if (dir == Direction.LEFT) {
                        currentPos[1]--;
                    } else if (dir == Direction.UP) {
                        currentPos[0]--;
                    } else if (dir == Direction.DOWN) {
                        currentPos[0]++;
                    }
                    break;

                case '/':
                    isNumber = false;
                    if (dir == Direction.RIGHT) {
                        currentPos[0]--;
                        dir = Direction.UP;
                    } else if (dir == Direction.LEFT) {
                        currentPos[0]++;
                        dir = Direction.DOWN;
                    } else if (dir == Direction.UP) {
                        dir = Direction.RIGHT;
                        currentPos[1]++;
                    } else if (dir == Direction.DOWN) {
                        currentPos[1]--;
                        dir = Direction.LEFT;
                    }
                    break;

                case '\\':
                    isNumber = false;
                    if (dir == Direction.RIGHT) {
                        currentPos[0]++;
                        dir = Direction.DOWN;
                    } else if (dir == Direction.LEFT) {
                        currentPos[0]--;
                        dir = Direction.UP;
                    } else if (dir == Direction.UP) {
                        currentPos[1]--;
                        dir = Direction.LEFT;
                    } else if (dir == Direction.DOWN) {
                        currentPos[1]++;
                        dir = Direction.RIGHT;
                    }
                    break;

                case '#':
                    isNumber = false;
                    pathEnded = true;
                    break;

                default:
                    isNumber = true;
                    stringNum += currentChar;

                    if (dir == Direction.RIGHT) {
                        currentPos[1]++;
                    } else if (dir == Direction.LEFT) {
                        currentPos[1]--;
                    }
                    if (dir == Direction.UP) {
                        currentPos[0]--;
                    } else if (dir == Direction.DOWN) {
                        currentPos[0]++;
                    }
                    break;
            }
            if (!isNumber && !stringNum.equals("")) {
                try {
                    number = Integer.parseInt(stringNum);
                } catch (Exception e) {
                    System.out.println("ERRO: " + e);
                }
                money.add(number);
                stringNum = "";
            }
            currentChar = map[currentPos[0]][currentPos[1]];
        }
    }

    public int findStart() {
        char currentChar = map[0][0];
        int row = 0;
        while (currentChar != '-') {
            currentChar = map[row][0];
            row++;
        }
        return row - 1;
    }

    public char[][] getMap(int option) {
        String filePath = "";
        switch (option) {
            case 0:
                filePath = "casos-cohen-noite\\casoG50.txt";
                break;
            case 1:
                filePath = "casos-cohen-noite\\casoG100.txt";
                break;
            case 2:
                filePath = "casos-cohen-noite\\casoG200.txt";
                break;
            case 3:
                filePath = "casos-cohen-noite\\casoG500.txt";
                break;
            case 4:
                filePath = "casos-cohen-noite\\casoG750.txt";
                break;
            case 5:
                filePath = "casos-cohen-noite\\casoG1000.txt";
                break;
            case 6:
                filePath = "casos-cohen-noite\\casoG1500.txt";
                break;
            case 7:
                filePath = "casos-cohen-noite\\casoG2000.txt";
                break;
            default:
                break;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            int countNum = 0;
            String[] mapSizeString = { "", "" };

            while (countNum < 2) {
                char currentChar = (char) br.read();

                if (currentChar == ' ' || currentChar == '\n' || currentChar == '\r') {
                    countNum++;
                } else {
                    mapSizeString[countNum] += currentChar;
                }
            }
            br.readLine();

            int[] mapSize = { Integer.parseInt(mapSizeString[0]), Integer.parseInt(mapSizeString[1]) };
            char[][] map = new char[mapSize[0]][mapSize[1]];

            char currentChar = 'A';
            for (int row = 0; row < mapSize[0]; row++) {
                for (int col = 0; col < mapSize[1]; col++) {

                    currentChar = (char) br.read();
                    if (currentChar != '\n' && currentChar != '\r') {
                        map[row][col] = currentChar;
                    } else {
                        col--;
                    }
                }
            }
            return map;

        } catch (Exception error) {
            System.out.println("ERRO: " + error);
        }

        return null;
    }

    public int mapTotalSizeCount(){return map.length*map.length;}

    public int mapSizeCount(){return map.length;}
}
