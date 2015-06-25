package sequence;

public class CountingSort {
	public static int[] countingSort(int[] a){
		int b[] = new int[a.length];
        int max = a[0],min = a[0];
        for(int i:a){
            if(i>max){
                max=i;
            }
            if(i<min){
                min=i;
            }
        }
        
        //����k�Ĵ�С��Ҫ����������У�Ԫ�ش�С�ļ�ֵ��+1
        int k=max-min+1;
        int c[]=new int[k];
        for(int i=0;i<a.length;++i){
            c[a[i]-min]+=1;//�Ż����ĵط�����С������c�Ĵ�С
        }
        int index = 0;
        for (int i = 0; i < c.length; i++) {
        	for (int j = 0; j < c[i]; j++) {
        		b[index] = i + min;
        		index ++;
			}
		}
        
        return b;
	}
	
	public static void main(String[] args) {
		int a[]={100,93,97,92,96,99,92,89,93,97,90,94,92,95};
        int b[]=countingSort(a);
        
        System.out.println(a.length);
		printArray(a);
		printArray(b);

	}

	static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
}
