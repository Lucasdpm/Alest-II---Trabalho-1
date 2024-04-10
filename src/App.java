public class App {
    public static void main(String args[]) {
        // passa por todos os arquivos de diferentes tamanhos
        for (int option = 0; option < 8; option++) {
            int x = 100;

            int count = 0;
            long sum = 0;
            // passa x vezes pelo mesmo arquivo para poder fazer a média de tempo
            for (int i = 0; i < x; i++) {
                long start = System.nanoTime();
                ReadMap rm = new ReadMap(option);
                long end = System.nanoTime();

                long delta = end - start;
                sum += delta;
                count = rm.mapSizeCount();
            }

            System.out.printf("%d ; %d\n", count, sum / x);
        }
    }
}