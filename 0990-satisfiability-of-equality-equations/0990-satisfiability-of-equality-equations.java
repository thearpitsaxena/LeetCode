class Solution {
    int[] parent;
    int[] rank;
    int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    void union(int x, int y){
        int xParent = find(x);
        int yParent = find(y);
        if(rank[xParent] < rank[yParent]){
            parent[xParent] = yParent;
        }
        else if(rank[xParent] > rank[yParent]){
            parent[yParent] = xParent;
        } else{
            parent[xParent] = yParent;
            rank[yParent]++;
        }
    }

    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        rank = new int[26];
        for(int i = 0;i < 26;i++){
            parent[i] = i;
        }
        for(String equation : equations){
            if(equation.charAt(1) == '='){
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                union(a,b);
            }
        }
        for(String equation : equations){
            if(equation.charAt(1) == '!'){
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                if(find(a) == find(b)) return false;
            }
        }
        return true;
    }
}