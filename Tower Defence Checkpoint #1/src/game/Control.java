package game;

public class Control implements Runnable {

    State state;
    View  view;
	
	public Control()
	{
		run();
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void run() 
	{
		System.out.println("Running...");
		state = new State();
	    view = new View(this, state);
	}

}
