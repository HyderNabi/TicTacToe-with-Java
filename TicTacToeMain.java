import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe extends Canvas
{
	public String Cross_OR_Not;		// STORES 'X' AND 'O';
	Font font;						// FORT
	static int Whos_Turn = 0 ;		// STORES WHETHER 'X' WILL BE RENDERED NEXT OR 'O'
	static int Level = 1;			// STORES THE LEVEL OF THE GAME
	TicTacToe()
	{
		font = new Font("Casteller",Font.BOLD,40);
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		Cross_OR_Not = "";
		addMouseListener(new MouseEvents()); 	// ADDS THE MOUSE LISTENER EVENT
	}
	public void paint(Graphics g)
	{
		setFont(font);
		g.drawString(Cross_OR_Not,getWidth()/2-10,getHeight()/2+10);	// DISPLAY THE 'X' OR 'O' AT THE MIDDLE OF THE CELL
	}
	
	class MouseEvents extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent me)
		{
			
			if(Cross_OR_Not.isEmpty()) {	// CELL WILL BE FILLED IFF IT IS EMPTY
				
				if(Whos_Turn == 0) {
					Cross_OR_Not = "X"; // THIS IS "X'S" TURN 
					Whos_Turn = 1;		// SET NEXT TURN FOR "O"
				}else {
					Cross_OR_Not = "0";	//THIS IS "O'S" TURN
					Whos_Turn = 0;		//SET TURN FOR "X"
				}
				repaint();	//REPAINTING TEH CANVAS
			}
		}
	}
}
//::::::::::::::::::MAIN CLASS STARTS FORM HERE::::::::::::::::::::::::
 class TicTacToeMain extends Canvas{
	public static String Player_Name[]; // STORES THE NAMES OF TWO PLAYERS
	public static TicTacToe cell[];	    // DEFINES 9 CELLS FOR TIC TAC TOE
	public static Font font;			// FONTS  				
	public static Frame frame;			// OUTER CONTAINER
	public static Panel paneldown;		// THIS PANEL HOLDS THE 3 X 3 CELLS OF TIC TAC TOE
	public static JButton resetGame;	// BUTTON USED TO RESET THE GAME
	
	
	public static void main(String args[])
	{
		//:::::::::::::::::::INITIALIZATIONS:::::::::::::::::::::::::
		frame = new Frame();
		paneldown = new Panel();
		resetGame = new JButton("RESET GAME");
		font = new Font("Ariel",Font.BOLD,15);
		
		//SET ATTRIBUTES OF FRAME CONTAINER
		frame.setSize(500, 700);
		frame.setLayout(new BoxLayout(frame,BoxLayout.Y_AXIS));
		frame.add(new TicTacToeMain());
		frame.setBackground(Color.LIGHT_GRAY);
		
		//SET ATTRIBUTES OF TEH RESET BUTTON
		resetGame.setAlignmentX(CENTER_ALIGNMENT);
		resetGame.setFont(font);
		frame.add(resetGame);
		
		//SET LAYOU FOR THE CELLS
		paneldown.setLayout(new GridLayout(3,3,10,10));		//LAYOUT FOR THE 3X3 CELLS
		
		
		
		//:::::::::::::::INFORMATION OF TEH PLAYERS::::::::::::::::::::::::://
		Player_Name = new String[2];
		Player_Name[0] = JOptionPane.showInputDialog("ENTER NAME OF FIRST PLAYER.");
		Player_Name[1] = JOptionPane.showInputDialog("ENTER NAME OF SECOND PLAYER.");
		
		//INITIALIZE THE CELLS
		cell = new TicTacToe[9];
		for(int i=0;i<9;i++)
		{
			cell[i] = new TicTacToe();
			paneldown.add(cell[i]);		//ADD CELLS TO THE PANEL 
		}
	
		frame.add(paneldown);		//ADD PANEL TO FRAME
		frame.setVisible(true);		//SET FRAME VISIBLE
		
		//:::::::ADD ACTION LISTENER TO THE RESET BUTTON:::::::::::::::://
		resetGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				for(int i=0;i<cell.length;i++)
				{
					if(cell[i].Cross_OR_Not != "") {	//IF CELL IS NOT EMPTY
						cell[i].Cross_OR_Not = "";		//THE MAKE IT EMPTY
						cell[i].repaint();				//REPAINT THE CANVAS
						count++;
					}
					
				}
				if(count!=0)	//IF WE RESET THE GAME WITH EMPTY CELLS , THE LEVEL NUMBER SHOULD NOT GO UP..
					TicTacToe.Level++;  //INCEMENTS THE CURRENT LEVEL UPON RESET
				
			}
		});
		
		//::::::::::::::DISPOSE THE WINDOW::::::::::::::::://
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
		
		
	}
	//::::::::::::::::DISPLAY THE STATUS::::::::::::::::::::::::::://
	public void paint(Graphics o)
	{
	    setFont(font);
		o.drawString("::: TWO PLAYER TIC TAC TOE :::",(getWidth()/2)-100,20);
		o.drawString("NEXT TURN : "+Player_Name[TicTacToe.Whos_Turn], 30, 40);
		o.drawString("LEVEL : "+TicTacToe.Level, 30, 60);
	
	}
	
	
	
}