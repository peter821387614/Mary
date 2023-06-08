public class queue8 {
int max = 9; //皇后個數
static int count = 0; //判斷有幾種解法
static int num = 0; //判斷檢測衝突次數
int[] arr = new int[max];
public static void main(String[] args) {
queue8 queue8 = new queue8();
queue8.check(1);
System.out.println(count);
System.out.println(num);
}
//遞迴
private void check(int n) {
//n 為1-8，代表第n+1個皇后
if (n == max) {
print(); //n=8 退出
return;
}
//依次放，並判斷OK?
for (int i = 1; i < max; i++) {
arr[n] = i;
if (adjust(n)) {
check(n + 1); //如果不衝突，接著放n+1
} else {
//關鍵
/*adjust(4)在i=0時不衝突，進入adjust（5），如果衝突了，即
adjust（5）=i(i=0)衝突了，將不執行adjust（6），而是進行for迴
圈，adjust（5）=i（i=1），繼續判斷衝突...

如果adjust（5）=i遍歷全部仍然衝突，將回溯到adjust(4)，進行遍
歷，即adjust(4)在i=1時
包含了多層巢狀*/
}
}
}
//檢測是否衝突
private boolean adjust(int n) {
num++;
for (int i = 1; i < n; i++) {
if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) 
//同一列或者||斜線 類似斜率 arr[i]代表的是第i+1個的所在列數+1
return false;
}
return true;
}
//列印輸出 ，每輸出一次，即為一種解法，參考check（）的該方法的使用
private void print() {
for (int i = 1; i < arr.length; i++) {
System.out.print(arr[i] + "  ");
}
count++;
System.out.println();
}
}
