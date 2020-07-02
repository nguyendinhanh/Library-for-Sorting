package merge;
import java.util.Random;
public class InsertionSort {

	public static void main(String[] args) {
		int[] data;
		long startTime, endTime, runTime;
		startTime = System.currentTimeMillis();
		data = generateData(9);
		mergeSort(data);
		for(int i=0;i<9;i++)
			System.out.println(data[i]);
		endTime = System.currentTimeMillis();
		runTime = endTime - startTime;
		System.out.print("Run time: " + runTime);
	}
	
	public static int[] generateData(int size) {
		int i;
		int[] list = new int[size];
		for(i = 0; i < size; i++) 
			list[i] = (int)(Math.random()*9);
		return list;
	}
	
	static void merge(int[] finishList, int [] lista, int [] listb) {
		int a = 0, b = 0, f = 0;
		for(f = 0; a < lista.length && b < listb.length ; f++) {
			if(lista[a] < listb[b]) {
				finishList[f] = lista[a];
				a+=1;
			}
			else {
				finishList[f] = listb[b];
				b+=1;
			}
		}
		while(a < lista.length) {
			finishList[f] = lista[a];
			a+=1;
			f+=1;
		}
		while(b < listb.length) {
			finishList[f] = listb[b];
			f+=1;
			b+=1;

		}
	}
	
	public static void insertionSort(int[] list){
		int i, key, j;
		for(i = 1; i < list.length; ++i) {
			key = list[i];
			j = i - 1;
			while(j>= 0 && list[j] > key) {
				list[j+1] = list[j];
				j = j - 1;
			}
			list[j + 1] = key;
		}
	}

	static void mergeSort(int[] list){
		if(list.length <= 10) 
			insertionSort(list);  

		else {
			int i,j;
			int[] half1, half2;
			half1 = new int[list.length/2];
			half2 = new int[list.length - half1.length];
			for(i = 0; i < half1.length; i++)
				half1[i] = list[i];
			for(j = 0; j < half2.length; j++)
				half2[j] = list[i++];

			if(half1.length > 1)
				mergeSort(half1);
			if(half2.length > 1)
				mergeSort(half2);
			merge(list, half1 ,half2);
		}
	}
}


