import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    @Test
    public void shouldReturnTrueIfNodeAddedOnFirstPosition(){

        list.append(node2); //añadimos un nodo a la lista
        list.appendLeft(node); // añadimos el nodo a comprobar al principio

        Object expectedValue = node;
        Object obtainedValue = list.peekFirst(); // comprobamos el primer valor de la lista

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnErrorIfNodeToAddIsNull(){
        node = new DequeNode(null,null,null);

        assertThrows(RuntimeException.class, () -> list.append(node));
        assertThrows(RuntimeException.class, () -> list.appendLeft(node));
    }
    @Test
    public void shouldReturnTrueIfNodeAddedOnLastPosition(){
        list.appendLeft(node2); //añadimos un nodo a la lista
        list.append(node); // añadimos el nodo a comprobar al final

        Object expectedValue = node;
        Object obtainedValue = list.peekLast(); // comprobamos el primer valor de la lista

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnTrueIfFirstNodeWasDeleted(){
        list.appendLeft(node2);
        list.append(node);
        list.deleteFirst();

        Object expectedValue = node;

        Object obtainedValue = list.peekFirst();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturn0WhenDeletingTheLastNodeLeftOnTheList(){
        list.append(node);
        list.deleteFirst();
        Object expectedValue = 0;
        Object obtainedValue = list.size();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnTrueIfLastNodeWasDeleted(){
        list.appendLeft(node2);
        list.append(node);

        Object deletedValue = list.peekLast();
        list.deleteLast();
        Object obtainedValue = list.peekLast();

        assertNotEquals(deletedValue,obtainedValue);
    }

    @Test
    public void shouldReturnErrorIfDeletingANodeFromAnEmptyList(){

        assertThrows(RuntimeException.class, () -> list.deleteLast());
        assertThrows(RuntimeException.class, () -> list.deleteFirst());

    }

    @Test
    public void shouldReturnTheFirstNodeOnTheList(){

        list.appendLeft(node2);
        list.append(node);

        Object expectedValue = node2;
        Object obtainedValue = list.peekFirst();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnTheLastNodeOnTheList(){
        list.appendLeft(node2);
        list.append(node);

        Object expectedValue = node;
        Object obtainedValue = list.peekLast();
        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnErrorWhenPeekOnEmtyList(){

        assertThrows(RuntimeException.class, () -> list.peekFirst());
        assertThrows(RuntimeException.class, () -> list.peekLast());
    }

    @Test
    public void shouldReturnTwoIfTheSizeOfTheListIsTwo(){

        list.appendLeft(node2);
        list.append(node);

        Object expectedValue = 2;
        Object obtainedValue = list.size();
        assertEquals(expectedValue,obtainedValue);
    }


}
