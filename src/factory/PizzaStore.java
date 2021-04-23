package factory;

    public abstract class PizzaStore {

        public Pizza orderPizza(String type){
            Pizza pizza = this.makePizza(type);

            pizza.prepare();
            pizza.bake();
            pizza.box();

            return pizza;
        }

        public abstract Pizza makePizza(String type);
    }
