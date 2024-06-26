
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.Box;


import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
/**
 * Represents the control panel for the Tetris game
 */
public class ControlPanel extends JPanel
      implements ScoreDisplay
{
  private GamePanel whiteboard;
  private JSlider speedSlider;
  private JTextField scoreField, speedField;
  private Box box1, box2, box3;
  private JButton go;
  private final int minSpeed = 5, initSpeed = 50, maxSpeed = 195;
  long startTime;

  ControlPanel(GamePanel lpanel)
  {
    whiteboard = lpanel;
    this.setBackground(Color.lightGray);

  
    
    this.setLayout(new BorderLayout());
    box1 = Box.createHorizontalBox();
    box2 = Box.createHorizontalBox();
    box3 = Box.createVerticalBox();
    
    // setup GUI:   
   JLabel score = new JLabel("Score:");
   box1.add(score);
         
   scoreField = new JTextField("");
   scoreField.setBackground(Color.white);
   box1.add(scoreField);
   
   JLabel speed = new JLabel("Speed:");
   box2.add(speed);
   
   speedField = new JTextField("");
   speedField.addActionListener(new SpeedFieldListener()); 
   box2.add(speedField);
   
   box3.add(box1);
   box3.add(box2);
   add(box3, BorderLayout.NORTH);
 
   speedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed,maxSpeed, initSpeed);
   speedSlider.addChangeListener(new SliderListener());
   speedSlider.setMajorTickSpacing(95);
   speedSlider.setPaintTicks(true);
   speedSlider.setPaintLabels(true);
   speedSlider.setBackground(Color.lightGray);
   add(speedSlider, BorderLayout.CENTER);
   
  
   go = new JButton("GO");
   go.addActionListener(new GoButtonListener());
   add(go,BorderLayout.SOUTH);

   
   
  }

  // Starts a new game.
  public void newGame()
  {
	go.setText("Go");
	
    int speed = speedSlider.getValue();
    speedField.setText(String.format("%3d", speed));
    whiteboard.setSpeed(speed);

    whiteboard.newGame();
    requestFocus();
  }

  // Called from RamblecsKeyListener.
  public void slowDown()
  {
    // Get slider's current speed and reduce it by 10 units:
   int currentSpeed = speedSlider.getValue();
   speedSlider.setValue(currentSpeed-10);
  }

  // Called from RamblecsKeyListener.
  public void speedUp()
  {
    // Get slider's current speed and increase it by 10 units:
   int currentSpeed = speedSlider.getValue();
   speedSlider.setValue(currentSpeed+10);
  }

  // Called from GamePanel.
  public void update(int score)
  {
    scoreField.setText(String.format("%03d", score));
  }

  // Handles speed slider events.
  private class SliderListener implements ChangeListener
  {
    public void stateChanged(ChangeEvent e)
    {
      int speed = speedSlider.getValue();
      speedField.setText(String.format("%3d", speed));
      whiteboard.setSpeed(speed);
      requestFocus();
    }
  }

  // Handles speed field events.
  private class SpeedFieldListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      // When ENTER is pressed on speedField:
      // gets the text, tries to parse an int out of it, and
      // if successful, sets the slider to that value;
      // otherwise (if input error) sets the field text back
      // to the current speed slider setting.
      String k = speedField.getText();
      try{
    	 speedSlider.setValue(Integer.parseInt(k));
      }
      catch (Exception ex){
    	 int currentSpeed = speedSlider.getValue();
    	 String j = String.valueOf(currentSpeed);
    	 speedField.setText(j);
      }
    	requestFocus();
    }
  }
  
  private class GoButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String cmd = e.getActionCommand();
      if ("Go".equals(cmd))
      {
        whiteboard.dropCube();
        go.setText("Stop");
      }
      else
      {
        whiteboard.stopCube();
        go.setText("Go");
      }
      requestFocus();
    }
  }
}


