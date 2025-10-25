public class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // Step 1: Build adjacency matrix of Manhattan distances
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dist[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                dist[j][i] = dist[i][j];
            }
        }

        // Step 2: Prim's algorithm initialization
        boolean[] visited = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;  // Start from node 0

        int totalCost = 0;

        // Step 3: Prim's algorithm main loop
        for (int i = 0; i < n; i++) {
            int u = -1;
            // Find unvisited node with smallest distance
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || minDist[j] < minDist[u])) {
                    u = j;
                }
            }

            visited[u] = true;
            totalCost += minDist[u];

            // Update distances to unvisited neighbors
            for (int v = 0; v < n; v++) {
                if (!visited[v] && dist[u][v] < minDist[v]) {
                    minDist[v] = dist[u][v];
                }
            }
        }

        return totalCost;
    }
}
