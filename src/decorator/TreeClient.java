package decorator;

public class TreeClient {

    public static void main(String[] args) {
        ChristmasTree myTree = new TreeTopper(new TreeLights(new PineChristmasTree()));
        ChristmasTree yourTree = new TreeTopper(new PineChristmasTree());

        System.out.println("Let's decorate my tree: " + myTree.decorate());
        System.out.println("Let's decorate your tree: " + yourTree.decorate());
    }
}
