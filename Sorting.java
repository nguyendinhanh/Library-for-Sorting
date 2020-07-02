import java.util.Random;
import java.util.Arrays;

public class Sorting {

	public static int partition(int[]array, int start, int end)
	{
		int last = array[end];
		int index = (start-1); 

		for (int i=start; i<=end-1; i++)
		{
			if (array[i] <= last)
			{
				index++;
				int temp = array[index];
				array[index] = array[i];
				array[i] = temp;
			}
		}
		int temp = array[index+1];
		array[index+1] = array[end];
		array[end] = temp;

		return index+1;
	}
	public static void quickSort(int[] array, int start, int end)
	{
		if (start < end)
		{
			int partitioningIndex = partition(array, start, end);
			quickSort(array, start, partitioningIndex-1);
			quickSort(array, partitioningIndex+1, end);
		}
	}


	public static void mergeSort(int[] array, int []scratch, int start, int end)
	{
		if ( start < end -1)
		{
			int midPoint = ( start + end )/2;
			mergeSort(array, scratch, start, midPoint);
			mergeSort(array, scratch, midPoint, end);
			merge(array, scratch, start, midPoint,end);
		}
	}
	private static void merge ( int[]array, int[]scratch, int start, int midPoint, int end){
		for ( int i = start; i < end ; ++i)
			scratch[i] = array[i];

		int index1=start;
		int index2=midPoint;

		for( int i =start; i < end;i++){
			if( index1>=midPoint)
				array[i]=scratch[index2++];
			else if ( index2 >= end)
				array[i] = scratch[index1++];
			if(scratch[index1] <= scratch[index2] )
				array[i] = scratch [index1++];
			else
				array[i] = scratch[index2++];
		}
	}


	public static void bubbleDown( int index)
	{
		int [] key = new int [10];
		int size = 0;

		while(2*index+1 < size )
		{
			int swap = index;
			if (key [ 2* index + 1 ] > key[swap])
				swap = 2*index+1;
			if( 2*index+2 < size && key[2*index+2] > key[swap])
				swap = 2*index+2;

			if ( swap == index)
				return ;

			int temp = key[index];
			key[index] = key[swap];
			key[swap] = temp; 
			index =swap;
		}
	}

	public static void heapSort(int[] array)
	{
		int mid = array.length /2;
		for (int i = mid - 1; i >= 0; i--)
			bubbleDown(i);

		for (int i=array.length-1; i>=0; i--)
		{

			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;


			bubbleDown(i);
		}
	}

	public static void radixSort(int[] array, int digits)
	{
		int [] scratch = new int [array.length];
		int [] buckets = new int [10];
		int power = 1; 
		for ( int i = 0 ; i < digits ; i++ ){
			for ( int value : array) {
				int number = ( value % (10 * power) / power) ;
				buckets[number]++;
			}
			int current = 0;
			int previous = 0;
			for(int j = 1 ; j < buckets.length ; j++){
				current = buckets[j];
				buckets[j] = previous;
				previous += current;
			}
			for ( int value : array) {
				int number = ( value % (10 * power) / power) ;
				scratch [ buckets[number]++] = value;
			}
		}
		for( int i = 0 ; i < array.length ; ++i )
			array[i] = scratch[i];
	}

	public static void main(String[] args) {

		int [] array = new int [10000];
		array =(int)(Math.random()*10000);
		int [] array1 = new int [100000];
		int [] array2 = new int [1000000];


		System.out.print("Array length: 10000");
		double start = System.nanoTime();
		quickSort(array,0,10000);
		double end = System.nanoTime();
		double quickTime = end-start; 
		System.out.format("Quicksort: %.4f \n" + "\t",quickTime );
		start = System.nanoTime();
		mergeSort(Arrays.copyOf(array, 0),0,0,10000);
		end = System.nanoTime();
		double mergeTime = end-start; 
		System.out.format("Merge Sort: %.4f \n" + "\t", mergeTime);
		start = System.nanoTime();
		heapSort(Arrays.copyOf(array, 0));
		end = System.nanoTime();
		double heapTime = end-start; 
		System.out.format("Heap Sort: %.4f \n" + "\t", heapTime);
		start = System.nanoTime();
		radixSort(Arrays.copyOf(array, 0),5);
		end = System.nanoTime();
		double radixTime = end-start; 
		System.out.format("Radix Sort: %.4f \n" + "\t", radixTime);

	}

}
