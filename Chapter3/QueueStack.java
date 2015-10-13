package Chapter3;

/* A queue built from two stacks*/
public class QueueStack<T> {
    Stack<T> newest;
    Stack<T> oldest;

    public QueueStack() {
        newest = new Stack<T>();
        oldest = new Stack<T>();
    }

    public void enqeue(T toAdd) {
        newest.push(toAdd);
    }

    private void shift() {
        if (oldest.isEmpty()) {
            while (!newest.isEmpty()) {
                oldest.push(newest.pop());
            }
        }
    }

    public T peek() {
        shift();
        return oldest.peek();
    }

    public T dequeue() {
        shift();
        return oldest.pop();
    }

}
