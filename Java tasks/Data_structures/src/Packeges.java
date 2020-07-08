import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;


public class Packeges {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        int packagesCount = in.nextInt();

        int cpuTime = 0;

        Queue<Integer> queue = new LinkedBlockingQueue<>(size);
        for (int i = 0 ; i < packagesCount; i++){
            int arrival = in.nextInt();
            int duration = in.nextInt();

        }
    }
}
