public class Main {

    public static void main(String[] args) {


        Graph graph = new Graph();

        try {
            graph.loadGraph();
        }catch (Exception e){
            e.printStackTrace();
        }


        graph.checkGraph();


    }
}
