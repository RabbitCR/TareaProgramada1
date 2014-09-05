package musicbox;

public class DoubleList {
    /**ATRIBUTOS DE LA CLASE DOUBLELIST*/
    private Node firstNode;
    private Node lastNode;
    private int size;

    private class Node {
        /**ATRIBUTOS DE LA CLASE NODE*/
        Object data1;
        Object data2;
        Object data3;
        Object data4;
        Object data5;
        Object data6;
        Object data7;
        Node next;
        Node previous;

        public Node() {
            this.data1 = null;
            this.data2 = null;
            this.data3 = null;
            this.data4 = null;
            this.data5 = null;
            this.data6 = null;
            this.data7 = null;
            this.next = null;
            this.previous = null;
        }

        public Node(Object imputData1, Object imputData2, Object imputData3, Object imputData4, Object imputData5, Object imputData6, Object imputData7) {
            this.data1 = imputData1;
            this.data2 = imputData2;
            this.data3 = imputData3;
            this.data4 = imputData4;
            this.data5 = imputData5;
            this.data6 = imputData6;
            this.data7 = imputData7;
            next = null;
            previous = null;
        }

        public Node(Object imputData1, Object imputData2, Object imputData3, Object imputData4, Object imputData5, Object imputData6, Object imputData7, Node nextNode) {
            this.data1 = imputData1;
            this.data2 = imputData2;
            this.data3 = imputData3;
            this.data4 = imputData4;
            this.data5 = imputData5;
            this.data6 = imputData6;
            this.data7 = imputData7;
            next = nextNode;
        }

        public Object getData1 () {
            return this.data1;
        }

        public Object getData2 () {
            return this.data2;
        }

        public Object getData3 () {
            return this.data3;
        }

        public Object getData4 () {
            return this.data4;
        }

        public Object getData5 () {
            return this.data5;
        }

        public Object getData6 () {
            return this.data6;
        }

        public Object getData7 () {
            return this.data7;
        }

        public void setData1 (Object data) {
            this.data1 = data;
        }

        public void setData2 (Object data) {
            this.data2 = data;
        }

        public void setData3 (Object data) {
            this.data3 = data;
        }

        public void setData4 (Object data) {
            this.data4 = data;
        }

        public void setData5 (Object data) {
            this.data5 = data;
        }

        public void setData6 (Object data) {
            this.data6 = data;
        }

        public void setData7 (Object data) {
            this.data7 = data;
        }

        public Object getData(int pos) {
            if (pos == 1){
                return this.data1;
            }
            if (pos == 2){
                return this.data2;
            }
            if (pos == 3){
                return this.data3;
            }
            if (pos == 4){
                return this.data4;
            }
            if (pos == 5){
                return this.data5;
            }
            if (pos == 6){
                return this.data6;
            }
            if (pos == 7){
                return this.data7;
            }
            else {
                throw new Error("Sorry! Position not found");
            }
        }

        public void setData(Object imputData1, Object imputData2, Object imputData3, Object imputData4, Object imputData5, Object imputData6, Object imputData7) {
            this.data1 = imputData1;
            this.data2 = imputData2;
            this.data3 = imputData3;
            this.data4 = imputData4;
            this.data5 = imputData5;
            this.data6 = imputData6;
            this.data7 = imputData7;
        }
    }
    /**CONSTRUCTOR DE LA CLASE DOUBLELIST*/
    public DoubleList () {
            this.firstNode = null;
            this.lastNode = null;
            this.size = 0;
    }

    /**MÉTODO PARA DETERMINAR SI LA LISTA ESTÁ VACÍA*/
    public boolean isEmpty() { 
        return firstNode == null;
    }

    /**MÉTODO PARA IMPRIMIR LA LISTA EN PANTALLA*/
    public void printList() { 
        if (isEmpty()) {
            throw new Error("Sorry! Empty List");
        }
        else {
            Node Actual = firstNode;

            while (Actual != null) {
                System.out.print (Actual.data1);
                System.out.print (Actual.data2);
                System.out.print (Actual.data3);
                System.out.print (Actual.data4);
                System.out.print (Actual.data5);
                System.out.print (Actual.data6);
                System.out.print (Actual.data7);
                System.out.println("\t");
                Actual = Actual.next;
            }
        }
    }

    /**MÉTODO PARA AGREGAR ELEMENTOS AL INICIO DE LA LISTA*/
    public void addBeginning(Object imputData1, Object imputData2, Object imputData3, Object imputData4, Object imputData5, Object imputData6, Object imputData7) {
        if (isEmpty()) {
            firstNode = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7);
            firstNode.previous = null;
        }	
        else {
            Node newNode = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7,firstNode);
            firstNode.previous = newNode;
            firstNode = newNode;
        }
    }

    /**MÉTODO PARA AGREGAR ELEMENTOS AL FINAL DE LA LISTA*/
    public void addEnd(Object imputData1, Object imputData2, Object imputData3, Object imputData4, Object imputData5, Object imputData6, Object imputData7) {
        if (isEmpty()) {
            firstNode = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7);
            firstNode.previous = null;
        }
        else {
            Node aux = firstNode;
            while(aux.next != null) {
                aux = aux.next;
            }
            aux.next = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7);
            aux.next.previous = aux;
        }
    }

    /**MÉTODO PARA AGREGAR ELEMENTOS EN UNA POSICIÓN ESPECÍFICA DE LA LISTA*/
    public void addPosition(Object imputData1, Object imputData2, Object imputData3, Object imputData4, Object imputData5, Object imputData6, Object imputData7, int pos) {
        if(isEmpty()) {
            firstNode = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7);
        }
        else {
            if(pos <= 1) {
                Node newNode = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7);
                newNode.next = firstNode;
                firstNode = newNode;
                firstNode.next.previous = firstNode;
            }
            else {
                Node aux = firstNode;
                int i = 2;
                while((i!=pos)&&(aux.next != null)) {
                    i++;
                    aux = aux.next;
                }
                Node newNode = new Node(imputData1, imputData2, imputData3, imputData4, imputData5, imputData6, imputData7);
                newNode.next = aux.next;
                aux.next = newNode;
                newNode.previous = aux;		
            }
        }
    }

    /**MÉTODO PARA ELEMINAR ELEMENTOS AL INICIO DE LA LISTA*/
    public void deleteBeginning() {
        if (isEmpty()) {
            throw new Error("Sorry! Not found elements");
        }
        else {
            if(firstNode.next == null) {
                firstNode = null;
            }
            else {
                firstNode = firstNode.next;
                firstNode.previous = null;
            }
        }	
    }

    /**MÉTODO PARA ELEMINAR ELEMENTOS AL FINAL DE LA LISTA*/
    public void deleteEnd() {
        if(isEmpty()) {
            throw new Error("Sorry! Not found elements");
        }
        else {
            if(firstNode.next == null) {
                firstNode = null;
            }
            else {
                Node actual = firstNode;
                while(actual.next!=null) {
                    actual = actual.next;
                }
                actual.previous.next = null;
            }
        }
    }

    /**MÉTODO PARA ELIMINAR ELEMENTOS EN UNA POSICIÓN ESPECÍFICA DE LA LISTA*/
    public void deletePosition(int pos) {
        if(isEmpty()) {
            throw new Error("Sorry! Not found elements");
        }
        else {	
            Node actual = firstNode;
            int i = 1;
            while((1!=pos)&&(actual.next != null)) {
                i++;
                actual = actual.next;
            }
            if (pos==1) {
                deleteBeginning();
            }
            else {
                if(i == pos) {
                    actual.previous.next = actual.next;
                    actual.next.previous = actual.previous;
                }
            }
        }
    }

    /**MÉTODO PARA INVERTIR LAS REFERENCIAS DE UNA LISTA*/
    public void reverse () {
        Node next = firstNode;
        Node previous = null;
        while(!(isEmpty())) {
            next = firstNode.next;
            firstNode.next = previous;
            previous = firstNode;
            firstNode = next;		
        }
        firstNode = previous;
    }

    /**MÉTODO PARA OBTENER EL TAMAÑO DE UNA LISTA*/
    public int size() {
        int i = 0;
        if (isEmpty()) {
            return i;
        }
        else {
            Node aux = firstNode;
            i++;
            aux = aux.next;
            while (aux!=null) {
                i++;
                aux = aux.next;		
            }
        }
        return i;	
    }

    /**MÉTODO PARA OBTENER EL ELEMENTO EN UNA POSICIÓN ESPECÍFICA DE LA LISTA*/
    public Object getElementPosition(int pos, int data) {
        if (pos >= this.size() || pos < 0) {
            return null;
        }
        Node actualNode = this.firstNode;
        for (int i = 0; i < pos ; i++) {
            actualNode = actualNode.next;
        }
        if (data == 1) {
            return actualNode.data1;
        }
        if (data == 2) {
            return actualNode.data2;
        }
        if (data == 3) {
            return actualNode.data3;
        }
        if (data == 4) {
            return actualNode.data4;
        }
        if (data == 5) {
            return actualNode.data5;
        }
        if (data == 6) {
            return actualNode.data6;
        }
        if (data == 7) {
            return actualNode.data7;
        }
        else {
            throw new Error();
        }
    }  

    /**MÉTODO PARA OBTENER EL NODO DE UNA POSICIÓN ESPECÍFICA DE LA LISTA*/
    public Node getNode(int pos) {
        if (pos >= this.size() || pos < 0) {
                return null;
        }
        Node actualNode = this.firstNode;
        for (int i = 0; i < pos ; i++) {
            actualNode = actualNode.next;
        }
        return actualNode;
    }

    /**MÉTODO PARA INTERCAMBIAR NODOS DE LA LISTA*/
    public void swap(Node nodex, Node nodey) {
        Object element1;
        Object element2;
        Object element3;
        Object element4;
        Object element5;
        Object element6;
        Object element7;
        element1 = nodex.data1;
        element2 = nodex.data2;
        element3 = nodex.data3;
        element4 = nodex.data4;
        element5 = nodex.data5;
        element6 = nodex.data6;
        element7 = nodex.data7;
        nodex.setData(nodey.getData1(), nodey.getData2(), nodey.getData3(), nodey.getData4(), nodey.getData5(), nodey.getData6(), nodey.getData7());
        nodey.setData(element1, element2, element3, element4, element5, element6, element7);
    }

    /**ALGORITMO BUBBLESORT APLICADO A STRINGS*/
    public void bubbleSort (int index) {
        for (int i = this.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if((this.getElementPosition(j + 1, index).toString().compareTo(this.getElementPosition(j, index).toString())) < 0) {
                    this.swap(this.getNode(j), this.getNode(j + 1));
                }
            }
        }
    }
}