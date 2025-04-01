
class FenwickTree {
    private int[] fenwickTree;
    private int size;

    public FenwickTree(int n) {
        this.size = n;
        this.fenwickTree = new int[n + 1];
    }

    public void update(int index, int value) {
        while (index <= size) {
            fenwickTree[index] += value;
            index += index & -index;
        }
    }

    public int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += fenwickTree[index];
            index -= index & -index;
        }
        return sum;
    }

    public static void main(String[] args) {
        FenwickTree ft = new FenwickTree(5);
        
        ft.update(1, 5);
        ft.update(2, 3);
        ft.update(3, 7);
        
        System.out.println("Prefix sum up to index 3: " + ft.query(3));
        System.out.println("Prefix sum up to index 2: " + ft.query(2));
    }
}
