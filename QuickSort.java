
public class QuickSort {

	public static void main(String[] args) {
		int[] data;
		long startTime, endTime, runTime;
		startTime = System.currentTimeMillis();
		data = generateData(10000);
		quickSort(data, 0, data.length-1);
		for(int i = 0; i < 10000; i++)
			System.out.println(data[i]);
		endTime = System.currentTimeMillis();
		runTime = endTime - startTime;
		System.out.print("Run time: " + runTime);
	}
	
	public static int[] generateData(int size) {
		int i;
		int[] list = new int[size];
		for(i = 0; i < size; i++) 
			list[i] = (int)(Math.random()*10000);
		return list;
	}
	
	private static void quickSort(int[] data, int low, int high) {
		if(low >= high) return;
		int pivot = data[high];
		int i = low - 1;
		int t;
		for(int j = low; j < high; j++)
			if(data[j] <= pivot) {
				i++;
				t = data[i];
				data[i] = data[j];
				data[j] = t;
				}
		i++;
		data[high] = data[i];
		data[i] = pivot;
		quickSort(data, low, i-1);
		quickSort(data, i + 1, high);
	}
}

