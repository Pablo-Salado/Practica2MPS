import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListQueueTest {

    private DoubleLinkedListQueue list;
    private DequeNode node;
    private DequeNode node2;

    @BeforeEach
    public void setUp(){
        list = new DoubleLinkedListQueue();
        node = new DequeNode(5,null,null);
        node2 = new DequeNode(7,null,null);
    }
    @AfterEach
    public void finish(){
        list = null;
        node = null;
        node2 = null;
    }
    @Test //1
    public void shouldReturnTrueIfNodeAddedOnFirstPosition(){

        list.append(node2); //añadimos un nodo a la lista
        list.appendLeft(node); // añadimos el nodo a comprobar al principio

        Object expectedValue = node;
        Object obtainedValue = list.peekFirst(); // comprobamos el primer valor de la lista

        assertEquals(expectedValue,obtainedValue);
    }
    @Test //2
    public void shouldReturnErrorIfNodeToAddAtFirstOfTheListIsNull(){
        node = new DequeNode(null,null,null);

        assertThrows(RuntimeException.class, () -> list.appendLeft(node));
    }
    @Test //3
    public void shouldReturnTrueIfNodeAddedOnLastPosition(){
        list.appendLeft(node2); //añadimos un nodo a la lista
        list.append(node); // añadimos el nodo a comprobar al final

        Object expectedValue = node;
        Object obtainedValue = list.peekLast(); // comprobamos el primer valor de la lista

        assertEquals(expectedValue,obtainedValue);
    }
    @Test //4
    public void shouldReturnTrueIfFirstNodeWasDeleted(){
        list.appendLeft(node2);
        list.append(node);
        list.deleteFirst();

        Object expectedValue = node;

        Object obtainedValue = list.peekFirst();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 5
    public void shouldReturnErrorIfNodeToAddAtLastOfTheListIsNull(){
        node = new DequeNode(null,null,null);

        assertThrows(RuntimeException.class, () -> list.append(node));
    }
    @Test // 6
    public void shouldReturnTrueIfLastNodeWasDeleted(){
        list.appendLeft(node2);
        list.append(node);

        Object deletedValue = list.peekLast();
        list.deleteLast();
        Object obtainedValue = list.peekLast();

        assertNotEquals(deletedValue,obtainedValue);
    }

    @Test //7
    public void shouldReturnErrorIfDeletingFirstOrLastNodeFromAnEmptyList(){

        assertThrows(RuntimeException.class, () -> list.deleteLast());
        assertThrows(RuntimeException.class, () -> list.deleteFirst());

    }

    @Test //8
    public void shouldReturnTheFirstNodeOnTheList(){

        list.appendLeft(node2);
        list.append(node);

        Object expectedValue = node2;
        Object obtainedValue = list.peekFirst();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 9
    public void shouldReturnTheLastNodeOnTheList(){
        list.append(node);
        list.appendLeft(node2);

        Object expectedValue = node;
        Object obtainedValue = list.peekLast();
        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 10
    public void shouldReturnErrorWhenPeekOnEmtyList(){

        assertThrows(RuntimeException.class, () -> list.peekFirst());
        assertThrows(RuntimeException.class, () -> list.peekLast());
    }

    @Test //11
    public void shouldReturnTwoIfTheSizeOfTheListIsTwo(){

        list.append(node2);
        list.append(node);

        Object expectedValue = 2;
        Object obtainedValue = list.size();
        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 12
    public void shouldReturn0WhenDeletingTheLastNodeOnTheList(){
        list.append(node);
        list.delete(node);
        Object expectedValue = 0;
        Object obtainedValue = list.size();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 13
    public void shouldReturnTrueWhenDeletingTheFirstNodeOnTheList(){
        list.append(node);
        list.deleteFirst();
        Object expectedValue = 0;
        Object obtainedValue = list.size();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 14
    public void shouldReturnTrueWhenDeletingTheLastNodeOnTheList(){
        list.append(node);
        list.deleteLast();
        Object expectedValue = 0;
        Object obtainedValue = list.size();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 15
    public void shouldReturnTrueWhenAccesingTheExpectedNode(){
        list.append(node);
        list.append(node2);

        Object expectedValue = node2;
        Object obtainedValue = list.getAt(1);

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 16
    public void shouldReturnTrueWhenFindingTheExpectedNode(){
        list.append(node);
        list.append(node2);

        Object expectedValue = node2;
        Object obtainedValue = list.find(7);

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 17
    public void shouldReturnErrorIfDeletingANodeFromAnEmptyList(){

        assertThrows(RuntimeException.class, () -> list.delete(node));

    }
    @Test // 18
    public void shouldReturnTrueWhenDeletingTheFirstNodeOnTheListUsingDelete(){
        list.append(node);
        list.append(node2);
        list.delete(node);
        Object expectedValue = node2;
        Object obtainedValue = list.getAt(0);

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 19
    public void shouldReturnTrueWhenDeletingTheLastNodeOnTheListUsingDelete(){
        list.append(node);
        list.append(node2);
        list.delete(node2);
        Object expectedValue = node;
        Object obtainedValue = list.getAt(list.size()-1);

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 20
    public void shouldReturnTrueWhenDeletingANodeOnAListWithMoreThanOneElement(){
        DequeNode node3 = new DequeNode(10, null, null);
        list.append(node);
        list.append(node2);
        list.append(node3);
        list.delete(node2);
        Object expectedValue = node;
        Object obtainedValue = list.peekLast().getPrevious();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test // 21
    public void shouldReturnTrueWhenSortingCorrectlyAList() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        list.append(node2);
        list.append(node);
        list.append(new DequeNode(6, null, null));

        list.sort(comparator);

        int i = 0;
        while (i < list.size()-1){
            assertTrue((Integer) list.getAt(i).getItem() <= (Integer) list.getAt(i+1).getItem());
            i++;
        }
    }
    @Test // 22
    public void shouldReturnErrorWhenAccesingAWrongPosition(){
        assertThrows(RuntimeException.class, () -> list.getAt(list.size()+1));
        assertThrows(RuntimeException.class, () -> list.getAt(-1));
    }
    @Test // 23
    public void shouldReturnErrorWhenFindingANonExistenItem(){
        list.append(node);
        list.append(node2);
        assertThrows(RuntimeException.class, () -> list.find(10));
    }

    @Test // 24
    public void shouldReturnErrorWhenDeletingANonExistentItem(){
        list.append(node);
        list.append(node2);
        assertThrows(RuntimeException.class, () -> list.delete(new DequeNode(10,null,null)));
    }

    @Test // 25
    public void shouldReturnErrorWhenSortingAnEmptyList(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        assertThrows(RuntimeException.class, () -> list.sort(comparator));

    }


}
