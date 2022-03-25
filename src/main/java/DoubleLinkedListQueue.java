import java.util.Comparator;

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
            throw new RuntimeException("Error al añadir un nodo: el nodo es nulo");
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
            throw new RuntimeException("Error al añadir un nodo: el nodo es nulo");
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
            throw new RuntimeException("Error al borrar un nodo: la lista no contiene nodos");
        } else if(numElem == 1) {
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
            throw new RuntimeException("Error al borrar un nodo: la lista no contiene nodos");
        }else if(numElem == 1) {
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
            throw new RuntimeException("Error al devolver el primer elemento: la lista no contiene nodos");
        }
        return first;
    }

    @Override
    public DequeNode peekLast() {
        if (numElem == 0) {
            throw new RuntimeException("Error al devolver el ultimo elemento: la lista no contiene nodos");
        }
        return last;
    }

    @Override
    public int size() {
        return numElem;
    }

    @Override
    public DequeNode getAt(int position) {
        DequeNode result = peekFirst();
        int i = 0;

        if (position > numElem-1 || position < 0) {
            throw new RuntimeException("Error: la posicion requerida no existe");
        }

        while (i < position && result.getNext() != null){
            result = result.getNext();
            i++;
        }

        return result;
    }

    @Override
    public DequeNode find(Object item) {
        DequeNode res = peekFirst();
        int i = 0;

        while (i<numElem && res.getItem() != item){
            if(res.getNext() == null){
                throw new RuntimeException("Error: el objeto buscado no esta en la lista");
            } else {
                res = res.getNext();
                i++;
            }
        }

        return res;
    }

    @Override
    public void delete(DequeNode node) {
        if (numElem == 0){
            throw new RuntimeException("Error al borrar un nodo: la lista no contiene nodos");
        }  else if (numElem == 1 && node.getItem().equals(peekFirst().getItem())){
            last = null;
            first = null;
            numElem -= 1;
        } else if (node.equals(peekFirst())) {
            deleteFirst();
        } else if (node.equals(peekLast())) {
            deleteLast();
        } else {
            DequeNode prev, curr, next;
            prev = peekFirst();
            curr = peekFirst().getNext();
            while (curr != null && !curr.getItem().equals(node.getItem())){
                prev = curr;
                curr = curr.getNext();
            }
            if (curr.getItem().equals(node.getItem())){
                next = curr.getNext();
                prev.setNext(next);
                next.setPrevious(prev);
                numElem -= 1;
            } else {
                throw new RuntimeException("Error: el nodo no esta en la lista");
            }
        }
    }

    @Override
    public void sort(Comparator comparator) {
        DoubleLinkedListQueue aux = new DoubleLinkedListQueue();
        DequeNode min = getMin(this, comparator);

        while (numElem != 0){
            DequeNode nuevo = new DequeNode<>(min.getItem(),null,null);
            aux.append(nuevo);
            this.delete(min);
            if(numElem>0){
                min = getMin(this, comparator);
            }
        }
        first = aux.peekFirst();
        last = aux.peekLast();
        numElem = aux.size();
    }

    private DequeNode<T> getMin (DoubleLinkedListQueue list, Comparator<Object> comparator){
        DequeNode aux = list.peekFirst().getNext();
        DequeNode res = list.peekFirst();

        if (res == null) {
            throw new RuntimeException("Error: la lista esta vacia");
        } else {
            while (aux != null && res != null && list.size() > 1) {
                if (comparator.compare(aux.getItem(), res.getItem()) < 0){
                    res = aux;
                } else {
                    aux = aux.getNext();
                }
            }
            return res;
        }
    }
}
