package template;

public class Client {

    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        Tea tea = new Tea();

        tea.prepareRecipe();
        coffee.prepareRecipe();
    }
}
