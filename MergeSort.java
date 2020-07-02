
public class MergeSort extends Thread{
	int[] list;
	int level;
	public static int threadCount=0;

	public MergeSort(int[] data, int level) {
		list = data;
		this.level = level;
		threadCount++;
	}

	public void run() {
		// Make two Arrays
		int[] lista, listb;
		lista = new int[list.length/2];
		listb = new int[list.length-lista.length];
		int i,j;
		// Divide the list into two halves into the two arrays
		for (i=0; i<lista.length; i++)
			lista[i] = list[i];
		for (j=0; j<listb.length; j++)
			listb[j] = list[i++];

		// Sort the two halves
		MergeSort alpha=null, beta=null;
		if (level < ThreadedMergeSort.maxLevel) {//2 power of level >= MaxThreads(4)
			alpha = new MergeSort(lista, level+1);
			beta  = new MergeSort(listb, level+1);
			alpha.start();
			beta.start();
			try {
				alpha.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				beta.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			mergeSort(lista);
			mergeSort(listb);
		}

		
		// Merge the two halves back into the original list
		merge(list,lista,listb);
	}

	public void mergeSort(int[] list) {
		if (list.length<=1) {
			return;
		}
		int[] lista, listb;
		lista = new int[list.length/2];
		listb = new int[list.length-lista.length];
		int i,j;
		for (i=0; i<lista.length; i++)
			lista[i] = list[i];
		for (j=0; j<listb.length; j++)
			listb[j] = list[i++];
		mergeSort(lista);
		mergeSort(listb);
		merge(list,lista,listb);
	}

	public void merge(int[] list, int[] lista, int[] listb) {
		int a, b, f;
		a = b = f = 0;
		while (a<lista.length && b<listb.length) {
			if (lista[a]<=listb[b])
				list[f++] = lista[a++];
			else
				list[f++] = listb[b++];
		}
		while (a<lista.length)
			list[f++] = lista[a++];
		while (b<listb.length)
			list[f++] = listb[b++];
	}
}
