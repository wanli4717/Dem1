package com.company.Amazon;

public class DictTree {

    int count;

    int predix;

    DictTree[] nextNode = new DictTree[26];

    public DictTree() {
        count = 0;
        predix = 0;
    }

    public static void addWord(DictTree root, String str) {
        if (null == root || null == str) {
            return;
        }

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.nextNode[chars[i] - 'a'] == null) {
                root.nextNode[chars[i] - 'a'] = new DictTree();
            }
            root = root.nextNode[chars[i] - 'a'];
            root.predix++;
        }
        System.out.println(str+"单词添加成功");

        root.count++;
    }

    public static int findWord(DictTree root, String str) {
        if (null == root || null == str) {
            return -1;
        }

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (root.nextNode[chars[i] - 'a'] == null) {
                System.out.println("该单词尚未加入字典中");

                return 0;
            }

            root = root.nextNode[chars[i]-'a'];
        }
        System.out.println("单词在词典中出现了"+root.count+"次");

        return 1;
    }

    public static void main(String[] args) {
        DictTree trie = new DictTree();
        trie.addWord(trie,"hello");
        trie.addWord(trie,"tire");
        trie.addWord(trie,"trie");
        trie.addWord(trie,"world");
        trie.findWord(trie, "tire");
    }
}
