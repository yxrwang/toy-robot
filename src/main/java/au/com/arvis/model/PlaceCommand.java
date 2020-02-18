package au.com.arvis.model;

public class PlaceCommand extends Command{

    private PlaceOperationArgument placeOperationArgument;

    public PlaceCommand(PlaceOperationArgument placeOperationArgument){

        super(Operation.PLACE);

        this.placeOperationArgument = placeOperationArgument;
    }

    public PlaceOperationArgument getPlaceOperationArgument() {
        return placeOperationArgument;
    }
}
