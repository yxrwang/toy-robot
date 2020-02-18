package au.com.arvis.model;

import au.com.arvis.business.EnvironmentAware;
import au.com.arvis.business.RobotOperation;
import au.com.arvis.exception.InvalidPosition;


public class Robot implements RobotOperation{

    private Position currentPosition = null;

    private Facing currentFacing = null;

    private EnvironmentAware environment;

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Facing getCurrentFacing() {
        return currentFacing;
    }

    public void setCurrentFacing(Facing currentFacing) {
        this.currentFacing = currentFacing;
    }

    public Robot(EnvironmentAware environment){

        this.environment = environment;
    }

    private boolean isPlaced(){

        return currentFacing != null && currentPosition != null;
    }

    @Override
    public void turnLeft() {

        if(isPlaced()){

            switch (currentFacing){

                case EAST:
                    currentFacing = Facing.NORTH;
                    break;
                case WEST:
                    currentFacing = Facing.SOUTH;
                    break;
                case NORTH:
                    currentFacing = Facing.WEST;
                    break;
                case SOUTH:
                    currentFacing = Facing.EAST;
                    break;
            }
        }


    }

    @Override
    public void turnRight() {

        if(isPlaced()){

            switch (currentFacing){

                case EAST:
                    currentFacing = Facing.SOUTH;
                    break;
                case WEST:
                    currentFacing = Facing.NORTH;
                    break;
                case SOUTH:
                    currentFacing = Facing.WEST;
                    break;
                case NORTH:
                    currentFacing = Facing.EAST;
                    break;
            }
        }

    }

    @Override
    public void place(Position position, Facing facing) throws InvalidPosition{

        if(position == null)
            throw new InvalidPosition();

        if(position.isValid() && environment.isSafe(position)){

            currentPosition = position;

        }else{

            throw new InvalidPosition();
        }

        if(facing != null){

            currentFacing = facing;
        }

    }

    @Override
    public void move() throws InvalidPosition {

        if(isPlaced()){

            Position destination = null;

            switch (currentFacing){

                case EAST:
                    destination = new Position(currentPosition.getX()+1, currentPosition.getY());
                    break;
                case WEST:
                    destination = new Position(currentPosition.getX()-1, currentPosition.getY());
                    break;
                case SOUTH:
                    destination = new Position(currentPosition.getX(), currentPosition.getY() - 1);
                    break;
                case NORTH:
                    destination = new Position(currentPosition.getX(), currentPosition.getY() + 1);
                    break;
            }

            place(destination, currentFacing);
        }


    }

    @Override
    public void report() {

        System.out.println(currentPosition.getX() + ", "+ currentPosition.getY() + ", "+ currentFacing.name());
    }
}
