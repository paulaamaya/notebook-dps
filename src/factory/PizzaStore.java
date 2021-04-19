package factory;

    public abstract class PizzaStore {

        public Pizza orderPizza(String type){
            Pizza pizza = this.factoryMethod(type);

            pizza.prepare();
            pizza.bake();
            pizza.box();

            return pizza;
        }

        public abstract Pizza factoryMethod(String type);
    }
