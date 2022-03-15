import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DequeNodeTest {
    /*
    Lista a probar [3,5,7]
     */
    private DequeNode node;//5

    @BeforeEach
    public void setUp(){
        node = new DequeNode(5, new DequeNode(7,null,node), new DequeNode(3,node,null));
    }
    @AfterEach
    public void finish(){ node = null;}
    @Test
    public void shouldReturnNode() {

        Object expectedValue = 5;
        Object obtainedValue = node.getItem();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnNextNode() {

        Object expectedValue = 7;
        Object obtainedValue = node.getNext().getItem();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnNullWhenNextNodeOnLastNode() {

        Object expectedValue = null;
        Object obtainedValue = node.getNext().getNext();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnPreviousNode() {

        Object expectedValue = 3;
        Object obtainedValue = node.getPrevious().getItem();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnNullWhenPreviousNodeOnFirstNode() {

        Object expectedValue = null;
        Object obtainedValue = node.getPrevious().getPrevious();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnFalseIfNotFirstNode() {

        Object expectedValue = false;
        Object obtainedValue = node.isFirstNode();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnTrueIfFirstNode() {

        Object expectedValue = true;
        Object obtainedValue = node.getPrevious().isFirstNode();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnFalseIfNotLastNode() {

        Object expectedValue = false;
        Object obtainedValue = node.isLastNode();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnTrueIfLastNode() {

        Object expectedValue = true;
        Object obtainedValue = node.getNext().isLastNode();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnTrueIfNodeIsNotTerminal(){

        Object expectedValue = true;
        Object obtainedValue = node.isNotATerminalNode();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldReturnFalseIfNodeIsTerminal(){

        Object expectedValue = false;
        Object obtainedValue = node.getNext().isNotATerminalNode();

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldModifyNode(){
        Object newItem = 9;
        Object obtainedValue;
        node.setItem(newItem);
        if(node.getItem() == newItem){
            obtainedValue = true;
        }else{
            obtainedValue = false;
        }
        Object expectedValue = true;

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldModifyNextNode(){
        Object newItem = 10;
        Object obtainedValue;
        node.setNext(new DequeNode(newItem,null,node));

        if(node.getNext().getItem() == newItem){
            obtainedValue = true;
        }else{
            obtainedValue = false;
        }
        Object expectedValue = true;

        assertEquals(expectedValue,obtainedValue);
    }
    @Test
    public void shouldModifyPreviousNode(){
        Object newItem = 2;
        Object obtainedValue;
        node.setPrevious(new DequeNode(newItem,node,null));

        if(node.getPrevious().getItem() == newItem){
            obtainedValue = true;
        }else{
            obtainedValue = false;
        }
        Object expectedValue = true;

        assertEquals(expectedValue,obtainedValue);
    }
}
