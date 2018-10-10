//***********************************************************************
// SpaceShip.java                                        
// Angie Cruz Martinez                                                           
// Christopher Bryan Rosana                                
// Junnel Reboquio                             
// CS-111B    Section 002 11:10 - 1:00PM                   
//***********************************************************************
//Process Algorithm:
//1. Initialize variables and objects needed for the Applet
//2. Create Listeners in init()
//3. Create components (Spaceship, Lasers) in paint method
//4  Create handlers for events(2 buttons, 1 mouse event, 1 mouse motion)
//5. Add components to panel
//6. Add panel to frame
//***********************************************************************

import java.applet.Applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SpaceShip extends Applet 
{
   private final int APPLET_WIDTH = 300;
   private final int APPLET_HEIGHT = 300;
   private int count = 0;
   private Point clickPoint = null;
   private int mouseX, mouseY;
   private int x2, xDirection; //drawLine's random second x-point
   private int y2, yDirection; //drawLine's random second y-point
   private boolean soundIsOn = true; //Sound (On/Off) Flag
   AudioClip audioClip;
   Button butn1 = new Button("Reset");
   Button butn2 = new Button("Sound On");
   //Label lab = new Label("Count: " + count);
   
   //--------------------------------------------------------
   //  Applet registers listeners for all mouse related events.
   //--------------------------------------------------------
   public void init()
   {
      addMouseListener (new MouseHandler());
      addMouseMotionListener (new MouseMotionHandler());
      //butn1.addActionListener(new Butn1Handler());
      butn2.addActionListener(new Butn2Handler());
      audioClip = getAudioClip(getCodeBase(), "bonk.au");
      //add(lab);
      add(butn1);
      add(butn2);
      setBackground (Color.white);
      setSize (APPLET_WIDTH, APPLET_HEIGHT);
   }

   //--------------------------------------------------------
   //  Paint Component where the program draws Spaceship
   //   and Lasers
   //--------------------------------------------------------
   public void paint (Graphics page)
   {
    
    //generate a random x coordinate using math.random
    xDirection = (int) (Math.random() * 2);

    if(xDirection == 0)
        x2 = (int) (300 + Math.random() * 300);
    else
        x2 = ((int) (300 + Math.random() * 300)) * -1;


    //generate a random y coordinate using math.random
    yDirection = (int) (Math.random() * 2);

    if(yDirection == 0)
        y2 = (int) (300 + Math.random() * 300);
    else
        y2 = ((int) (300 + Math.random() * 300)) * -1;  
    
    //Nested if-else to randomize color and location of laser
    if (clickPoint != null)
         {
            if (count % 4 == 1 || count == 1)
            {
            page.setColor (Color.green);
            page.drawLine (clickPoint.x, clickPoint.y, x2, y2);
            }
            
            else if (count % 2 == 0 && count % 4 != 0)
            {
            page.setColor (Color.blue);
            page.drawLine (clickPoint.x, clickPoint.y, x2, y2);
            }
            
            else if (count % 2 == 1)
            {
            page.setColor (Color.orange);
            page.drawLine (clickPoint.x, clickPoint.y, x2, y2);
            }
            
            else if (count % 4 == 0)
            {
            page.setColor (Color.red);
            page.drawLine (clickPoint.x, clickPoint.y, x2, y2);
            }
         } 
 
      //draw Spaceship
      page.setColor (Color.green);
      page.fillOval(mouseX-8, mouseY-17, 17, 12); //Spaceship head
      page.setColor (Color.yellow);
      page.fillOval(mouseX-25, mouseY-12, 50, 25); //Spaceship body
      
      //shot counter on string (a trial)
      page.setColor(Color.black);
      page.drawString("Count: " + count, 5, 280);
      

        }
    //Butn1Handler resets the click counter back to zero
    
    /*
    private class Butn1Handler implements ActionListener 
    {

        public void actionPerformed(ActionEvent e) 
        {
            count = 0;
            lab.setText("Count: " + count);
        }
    */
    //Butn2Handler toggles the sound on or off
    private class Butn2Handler implements ActionListener 
    {

        public void actionPerformed(ActionEvent e) 
        {
            if (soundIsOn == true)
            {
			    butn2.setLabel("Sound Off");
                soundIsOn = false;
            }
            else 
                if (soundIsOn == false)
                {
                    butn2.setLabel("Sound On");
                    soundIsOn = true;
                }
        }
    }
    //--------------------------------------------------------
    // MouseHandler is an inner class listening for mouse events
    //--------------------------------------------------------
   private class MouseHandler implements MouseListener
   {
        //Every mouse click updates count, label, and calls repaint() to screen
        public void mouseClicked (MouseEvent event) 
        {
            clickPoint = event.getPoint();
            count++;
            //lab.setText("Count: " + count);
            if (soundIsOn == true)
                audioClip.play();
            repaint();
        }
	    //-----------------------------------------------------
	    //  Provide empty definitions for unused event methods.
   	    //-----------------------------------------------------
   	    public void mouseReleased (MouseEvent event) {}
   	    public void mouseEntered (MouseEvent event) {}
   	    public void mouseExited (MouseEvent event) {}
        public void mousePressed (MouseEvent event){}
    }

    //--------------------------------------------------------
    // MouseMotionHandler is an inner class listening for mouse 
    // motion events
    //--------------------------------------------------------
   private class MouseMotionHandler implements MouseMotionListener
   {
	//------------------------------------------------------
   	// Listens for MouseMotion events
   	// Gets the x and y coordinates of where mouse pointer is
    // clickPoint goes back to null so lasers disappear once
    // the mouse moves again 
   	//-------------------------------------------------------
	    public void mouseMoved (MouseEvent event) 
        {
            mouseX = event.getX();
            mouseY = event.getY();
            clickPoint = null;
            repaint();
        }
    //----------------------------------------------------
   	//  Provide empty definitions for unused event methods
   	//----------------------------------------------------
        public void mouseDragged (MouseEvent event)	{}
    }
 } //end RubberLines class