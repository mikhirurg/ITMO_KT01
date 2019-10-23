package animal;

public abstract class AnimalAbstract implements Animal {
    private String says;

    AnimalAbstract(String s) {
        says = s;
    }

    protected void say(){
        System.out.println(says);
    }
    public String says(){
        return says;
    }
}
