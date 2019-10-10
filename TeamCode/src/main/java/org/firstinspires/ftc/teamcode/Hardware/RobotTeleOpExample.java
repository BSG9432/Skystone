
//Something @TeleOp(name, group)
public class RobotTeleOpExample extends OpMode{
/*
The cool thing about using classes is the your class "Robot" is essentially it's own type now
bsgRobot comes from the classRobot, and we are creating a new Robot instance of a class.
bsgRobot now have access to all of the methods and variables inside of the Robot class
*/
Robot bsgRobot = new Robot();

@Override
    public void init() {
        bsgRobot.init(hardwareMap);    //Initialize all the robot components
    }
}
