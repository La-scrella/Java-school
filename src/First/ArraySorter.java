package First;

public class ArraySorter {
    public static <T extends Comparable<T>> void PrintArray(T[] array){
        for (T t : array) {
            System.out.print(t.toString() + ' ');
        }
        System.out.println();
    }

    public static <T extends Comparable<T>> T[] Sort(T[] array){
        int count = array.length;
        if (count > 1) {
            T buffer;
            for (int i = 0; i < count; i++) {
                boolean stopSort = true;
                for (int j = 0; j < count - i - 1; j++) {
                    if (array[j].compareTo(array[j + 1]) > 0){
                        buffer = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = buffer;
                        stopSort = false;
                    }
                }
                if (stopSort){
                    break;
                }
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> int IndexOf(T value, T[] array){
        int l = 0;
        int r = array.length - 1;
        int result;
        int compared;
        while (r - l >= 0)
        {
            result = l + (r - l) / 2;
            compared = value.compareTo(array[result]);
            if (compared == 0){
                while (result > 0 && value.compareTo(array[result - 1]) == 0){
                    result--;
                }
                return result;
            }
            if (compared > 0){
                l = result + 1;
            } else {
                r = result - 1;
            }
        }
        return -1;
    }
}
