package com.company.Microsoft;

import java.util.LinkedList;
import java.util.Queue;

public class Search {

    public static void main(String[] args) {
        int[][] map = new int[][]{{0, 1, 0, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}};
        System.out.println(bfs(map, new int[]{0, 0}, new int[]{1, 3}));
    }

//[[0,1][0,0]]
//    0 1 0 0
//    0 0 1 0
//    1 0 0 0


    public static int bfs(int[][] map, int[] begin, int[] end) {
        if (null == map) {
            return 0;
        }
        // 定义四个移动方向
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int len = map.length;
        int high = map[0].length;
        int[][] minPath = new int[len][high];
        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < high; j++) {
                minPath[i][j] = Integer.MAX_VALUE;
            }
        }

        // 起点
        minPath[begin[0]][begin[1]] = 0;

        que.offer(begin);
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur == end) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];

                if ((x >= 0 && x < len) && (y >= 0 && y < high) && map[x][y] == 0) {
                    // minPath[0][1] = 1
                    minPath[x][y] = minPath[cur[0]][cur[1]] + 1;
                    que.offer(new int[]{x, y});
                    map[cur[0]][cur[1]] = 1;
                }
            }
        }

        return minPath[end[0]][end[1]];
    }
}
