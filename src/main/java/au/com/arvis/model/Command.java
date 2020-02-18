package au.com.arvis.model;


public class Command {

    private Operation operation;

    public Command(Operation operation){

        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
