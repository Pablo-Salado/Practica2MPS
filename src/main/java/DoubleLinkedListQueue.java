public class DoubleLinkedListQueue<T> implements  DoubleEndedQueue{
    private DequeNode<T> first;
    private DequeNode<T> last;
    private int numElem;

    public DoubleLinkedListQueue (){
        first = null;
        last = null;
        numElem = 0;
    }

    private void appendFirst(DequeNode node){
        node.setPrevious(null);
        node.setNext(null);
        first = node;
        last = node;
    }

    @Override
    public void append(DequeNode node) {
        if (node.getItem() == null){
            throw new RuntimeException("Error al añadir un elemento: el elemento es nulo");
        }else if (numElem == 0){
            appendFirst(node);
        } else {
            last.setNext(node);
            node.setPrevious(last);
            node.setNext(null);
            last = node;
        }
        numElem += 1;
    }

    @Override
    public void appendLeft(DequeNode node) {
        if (node.getItem() == null){
            throw new RuntimeException("Error al añadir un elemento: el elemento es nulo");
        }else if (numElem == 0){
            appendFirst(node);
        } else {
            first.setPrevious(node);
            node.setPrevious(null);
            node.setNext(first);
            first = node;
        }
        numElem += 1;
    }

    @Override
    public void deleteFirst() {
        if (numElem == 0){
            throw new RuntimeException("Error al borrar un elemento: la lista no contiene elementos");
        } else if (numElem == 1){
            last = null;
            first = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }
        numElem -= 1;
    }

    @Override
    public void deleteLast() {
        if (numElem == 0){
            throw new RuntimeException("Error al borrar un elemento: la lista no contiene elementos");
        } else if (numElem == 1){
            last = null;
            first = null;
        } else {
            last = last.getPrevious();
            last.setNext(null);
        }
        numElem -= 1;
    }

    @Override
    public DequeNode peekFirst() {
        if (numElem == 0) {
            throw new RuntimeException("Error al devolver el primer elemento: la lista no contiene elementos");
        }
        return first;
    }

    @Override
    public DequeNode peekLast() {
        if (numElem == 0) {
            throw new RuntimeException("Error al devolver el ultimo elemento: la lista no contiene elementos");
        }
        return last;
    }

    @Override
    public int size() {
        return numElem;
    }

}
