public class queue8 {
int max = 9; //�ӦZ�Ӽ�
static int count = 0; //�P�_���X�ظѪk
static int num = 0; //�P�_�˴��Ĭ𦸼�
int[] arr = new int[max];
public static void main(String[] args) {
queue8 queue8 = new queue8();
queue8.check(1);
System.out.println(count);
System.out.println(num);
}
//���j
private void check(int n) {
//n ��1-8�A�N���n+1�ӬӦZ
if (n == max) {
print(); //n=8 �h�X
return;
}
//�̦���A�çP�_OK?
for (int i = 1; i < max; i++) {
arr[n] = i;
if (adjust(n)) {
check(n + 1); //�p�G���Ĭ�A���۩�n+1
} else {
//����
/*adjust(4)�bi=0�ɤ��Ĭ�A�i�Jadjust�]5�^�A�p�G�Ĭ�F�A�Y
adjust�]5�^=i(i=0)�Ĭ�F�A�N������adjust�]6�^�A�ӬO�i��for�j
��Aadjust�]5�^=i�]i=1�^�A�~��P�_�Ĭ�...

�p�Gadjust�]5�^=i�M���������M�Ĭ�A�N�^����adjust(4)�A�i��M
���A�Yadjust(4)�bi=1��
�]�t�F�h�h�_��*/
}
}
}
//�˴��O�_�Ĭ�
private boolean adjust(int n) {
num++;
for (int i = 1; i < n; i++) {
if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) 
//�P�@�C�Ϊ�||�׽u �����ײv arr[i]�N���O��i+1�Ӫ��Ҧb�C��+1
return false;
}
return true;
}
//�C�L��X �A�C��X�@���A�Y���@�ظѪk�A�Ѧ�check�]�^���Ӥ�k���ϥ�
private void print() {
for (int i = 1; i < arr.length; i++) {
System.out.print(arr[i] + "  ");
}
count++;
System.out.println();
}
}
