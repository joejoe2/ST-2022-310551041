import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueTest {
    private static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{5, 4, 3, 2}, new int[]{2, 3, 4, 5}),
                Arguments.of(new int[]{-1, 0, 1}, new int[]{-1, 0, 1}),
                Arguments.of(new int[]{43, 13, 23, 51, 67}, new int[]{13, 23, 43, 51, 67}),
                Arguments.of(new int[]{2, 2, 3, 3, -3}, new int[]{-3, 2, 2, 3, 3}),
                Arguments.of(new int[]{7, 2, -5, 6, 6, 9}, new int[]{-5, 2, 6, 6, 7, 9})
        );
    }

    @ParameterizedTest
    @MethodSource("streamProvider")
    void TestPriorityQueue(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int[] result = new int[random_array.length];

        for (int n : random_array) {
            priorityQueue.add(n);
        }

        for (int i=0; priorityQueue.size()>0 ; i++){
            result[i] = priorityQueue.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    void nullPointerExceptionFromAdd(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        assertThrows(NullPointerException.class, ()->priorityQueue.add(null));
    }

    @Test
    void arrayStoreExceptionFromToArray(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(1);
        assertThrows(ArrayStoreException.class, ()->priorityQueue.toArray(new Double[]{}));
    }

    class Data{
        String val;
        int id;

        public Data(String val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    @Test
    void classCastExceptionFromAdd(){
        PriorityQueue<Data> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Data("dsffasf", 0));
        assertThrows(ClassCastException.class, ()->priorityQueue.add(new Data("dsffasf", 1)));
    }
}
