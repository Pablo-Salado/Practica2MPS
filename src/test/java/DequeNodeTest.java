import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DequeNodeTest {
    /*
    Lista a probar [3,5,7]
     */
    private DequeNode node;//5

    @Test
    public void shouldReturnNode() {
        node = new DequeNode(5, new DequeNode(7,null,node), new DequeNode(3,node,null));

        Object expectedValue = 5;
        Object obtainedValue = node.getItem();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnNextNode() {
        node = new DequeNode(5, new DequeNode(7,null,node), new DequeNode(3,node,null));

        Object expectedValue = 7;
        Object obtainedValue = node.getNext().getItem();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnNullWhenNextNodeOnLastNode() {
        node = new DequeNode(5, new DequeNode(7,null,node), new DequeNode(3,node,null));

        Object expectedValue = null;
        Object obtainedValue = node.getNext().getNext();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnPreviousNode() {
        node = new DequeNode(5, new DequeNode(7,null,node), new DequeNode(3,node,null));

        Object expectedValue = 3;
        Object obtainedValue = node.getPrevious().getItem();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnNullWhenPreviousNodeOnFirstNode() {
        node = new DequeNode(5, new DequeNode(7,null,node), new DequeNode(3,node,null));

        Object expectedValue = null;
        Object obtainedValue = node.getPrevious().getPrevious();

        assertEquals(expectedValue,obtainedValue);
    }
}
