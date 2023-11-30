import java.util.Arrays;

public class DiskScheduling {

    public static void main(String[] args) {
        int[] requestQueue = {98, 183, 37, 122, 14, 124, 65, 67};
        int head = 53;

        System.out.println("Request Queue: " + Arrays.toString(requestQueue));
        System.out.println("Initial Head Position: " + head);

        // FCFS
        System.out.println("\nFCFS:");
        FCFS(requestQueue, head);

        // SSTF
        System.out.println("\nSSTF:");
        SSTF(requestQueue, head);

        // SCAN
        System.out.println("\nSCAN:");
        SCAN(requestQueue, head);

        // C-SCAN
        System.out.println("\nC-SCAN:");
        C_SCAN(requestQueue, head);
    }

    // First-Come, First-Served
    private static void FCFS(int[] requestQueue, int head) {
        int totalHeadMovement = 0;
        System.out.println("Order of Service:");

        for (int request : requestQueue) {
            int movement = Math.abs(request - head);
            totalHeadMovement += movement;
            System.out.print(request + " ");
            head = request;
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement);
    }

    // Shortest Seek Time First
    private static void SSTF(int[] requestQueue, int head) {
        int n = requestQueue.length;
        boolean[] visited = new boolean[n];
        int totalHeadMovement = 0;

        System.out.println("Order of Service:");

        for (int i = 0; i < n; i++) {
            int minDistance = Integer.MAX_VALUE;
            int index = -1;

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    int distance = Math.abs(requestQueue[j] - head);
                    if (distance < minDistance) {
                        minDistance = distance;
                        index = j;
                    }
                }
            }

            visited[index] = true;
            totalHeadMovement += minDistance;
            head = requestQueue[index];
            System.out.print(requestQueue[index] + " ");
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement);
    }

    // SCAN
    private static void SCAN(int[] requestQueue, int head) {
        int n = requestQueue.length;
        Arrays.sort(requestQueue);

        int totalHeadMovement = 0;
        System.out.println("Order of Service:");

        // Move towards higher track numbers
        for (int i = 0; i < n; i++) {
            if (requestQueue[i] >= head) {
                totalHeadMovement += Math.abs(requestQueue[i] - head);
                head = requestQueue[i];
                System.out.print(requestQueue[i] + " ");
            }
        }

        // Move towards lower track numbers
        for (int i = n - 1; i >= 0; i--) {
            if (requestQueue[i] < head) {
                totalHeadMovement += Math.abs(requestQueue[i] - head);
                head = requestQueue[i];
                System.out.print(requestQueue[i] + " ");
            }
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement);
    }

    // C-SCAN
    private static void C_SCAN(int[] requestQueue, int head) {
        int n = requestQueue.length;
        Arrays.sort(requestQueue);

        int totalHeadMovement = 0;
        System.out.println("Order of Service:");

        // Move towards higher track numbers
        for (int i = 0; i < n; i++) {
            if (requestQueue[i] >= head) {
                totalHeadMovement += Math.abs(requestQueue[i] - head);
                head = requestQueue[i];
                System.out.print(requestQueue[i] + " ");
            }
        }

        // Move towards lower track numbers (wrap around to the beginning)
        totalHeadMovement += (199 - head); // Assuming disk size is 200 tracks
        head = 0;  // Move to the beginning of the disk

        for (int i = 0; i < n; i++) {
            if (requestQueue[i] < head) {
                totalHeadMovement += Math.abs(requestQueue[i] - head);
                head = requestQueue[i];
                System.out.print(requestQueue[i] + " ");
            }
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement);
    }
}
