package slotMachine;

public class SlotMachineTest {

	public static void main(String[] args) {
		SlotMachineView theView = new SlotMachineView();
		SlotMachineModel theModel=new SlotMachineModel();
		SlotMachineController theController=new SlotMachineController(theView, theModel);
		theController.initialise();
	}
}
