package com.company.Microsoft;

public class FindWord {

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int len;
    public static int high;

//    w o d d;
//    o r d x;
//    a x d c;
//
    public static void main(String[] args) {
//        String word = "word";
        String word = "wox";
        char[][] map = {{'w', 'o', 'd', 'd'},{'o', 'r', 'd', 'x'},{'a', 'x', 'd', 'c'}};

        System.out.println(findWord(word, map));
    }


    public static boolean findWord(String word, char[][] map) {
        if (word == null || map == null) {
            return false;
        }

        len =  map.length;
        high = map[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < high; j++) {
                if (map[i][j] == word.charAt(0)) {
                    boolean res = dfs (word, map, i, j, 0);
                    if (res) {
                        return res;
                    }
                }
            }
        }

        return false;
    }

    public static boolean dfs(String word, char[][] map, int x, int y, int index) {
        if (x < 0 || x >= len || y < 0 || y >= high) {
            return false;
        }

        char tmp = map[x][y];
        if (word.charAt(index) != tmp) {
            return false;
        }

        if (word.charAt(index) == tmp && index == word.length() - 1) {
            return true;
        }
        map[x][y] = '#';

        boolean res = dfs(word, map, x + dx[0], y + dy[0], index + 1)
                || dfs(word, map, x + dx[1], y + dy[1], index + 1)
                || dfs(word, map, x + dx[2], y + dy[2], index + 1)
                || dfs(word, map, x + dx[3], y + dy[3], index + 1);


        map[x][y] = tmp;

        return res;
    }
}
