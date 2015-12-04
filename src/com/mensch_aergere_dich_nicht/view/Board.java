/*
 * Gameboard.
 */
package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;

import com.mensch_aergere_dich_nicht.models.*;

import java.awt.*;
import java.util.*;
import java.util.Map.Entry;
//import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Board extends JFrame implements MouseListener, ActionListener{
	private static final boolean FALSE = false;
	private static final boolean TRUE = false;
	JLayeredPane boardPane;
	JButton btnClose;
    JPanel mainPanel;
    JPanel boardPanel;
    JTextArea msgBox;
    JScrollPane scrollPane;
    BufferedImage image = null;
    // Grid map.
    Map<String, Integer> fieldGrid = new HashMap<String, Integer>();
    Map<Integer, int[]> fieldCoordinates= new HashMap<Integer, int[]>();
    // Panel offset for top left corner of grid area.
    int offsetX = 0; //35
    int offsetY = 0;
    Map<Integer, Field> fields;
    Map<String, Player> players;
    Listener clickListener;
    CubeView cube;
    
    public Board(Map<Integer, Field> f, Map<String, Player> p, Listener l){
      super("Mensch ärgere dich nicht");
      this.fields = f;
      this.players = p;
      this.clickListener = l;
      
      // Setup field grid.
      this.setupFieldGrid();
      
      /*
      try {                
          image = ImageIO.read(getClass().getResource("board600.jpg"));
       } catch (IOException ex) {
            // Exception...
       }  
      */
      
      btnClose = new JButton("Spiel benden");
      btnClose.setPreferredSize(new Dimension(40, 20));
      btnClose.addActionListener(this);
      
      // Message box (10 rows, 40 columns).
      msgBox = new JTextArea(30,20);
      //msgBox.setText("");
      // Use line wrap (wrap at word boundries).
      msgBox.setLineWrap(true);
      msgBox.setWrapStyleWord(true);
      
      // Add scrollbar.
      scrollPane = new JScrollPane(msgBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      // New boardPane.
      boardPane = new JLayeredPane();
      boardPane.setPreferredSize(new Dimension(600,600));
                        
      // New boardPanel.
      //boardPanel = new JPanel();
      //boardPanel.add(new JLabel(new ImageIcon(image)));
      
      //this.drawBoard();
      boardPane.addMouseListener(this);
      this.addMouseListener(this);
      
      //boardPanel.addMouseListener(this);
      //this.addMouseListener(this);
      
      // TEST new piecePanel.
      //PiecePanel playerRed = new PiecePanel(Color.RED, 40,40);
      
      // Add panels to boardPane.
      //boardPanel.setBounds(0,0,600,600);
      //boardPane.add(boardPanel, new Integer(0));
      //int[] coordinates = this.getFieldCoordinates(25);
  	  //int x = coordinates[0];
  	  //int y = coordinates[1];
      //playerRed.setBounds(x-25,y-21,80,80);
      //playerRed.setOpaque(false);
      //boardPane.add(playerRed, new Integer(1));
      
      // New main panel.
      mainPanel = new JPanel();
      
      // Add elements to main panel.
      mainPanel.add(scrollPane);
      
      // Add components to Gameboard.
      this.add(boardPane, BorderLayout.WEST);
      //this.add(boardPanel, BorderLayout.WEST);
      this.add(mainPanel, BorderLayout.EAST);
      this.add(btnClose, BorderLayout.SOUTH);

      // Frame configuration.
      //this.setLayout(new GridLayout());
      this.setSize(850, 650);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private JLayeredPane getBoardPane(){
    	return this.boardPane;
    }
    
    /*
     * Draw single field on gameboard.
     */
    private void drawField(JLayeredPane pane, int fieldnumber, Color color, Color border, String fieldtype){
    	int[] coordinates = this.getFieldCoordinates(fieldnumber);
    	int x = coordinates[0];
    	int y = coordinates[1];

    	FieldPanel field = new FieldPanel(border, color, 40,40, fieldtype); 
    	field.setBounds(x,y,100,100); 
    	field.setOpaque(false); 
    	pane.add(field, new Integer(1));
    }
    
    /*
     * Draw text on gameboard.
     */
    private void drawText(JLayeredPane pane, String text, int x, int y, int h, int w, Color c){
    	//TextPanel text = new TextPanel(text, x, y, c);
    	TextLabel newtext = new TextLabel(text, x, y, c);
    	newtext.setBounds(x,y,h,w); 
    	newtext.setOpaque(false);
    	pane.add(newtext, new Integer(1));
    }
    
    private void drawCube(JLayeredPane pane, int x, int y){
    	this.cube = new CubeView(x, y);
    	pane.add(cube, new Integer(1));
    }
    
    public int getRoll(){
    	this.cube.getCube().throwCube();
    	this.drawBoard();
    	return this.cube.getCubeNumber();
    }
    
    /*
     * Draw gameboard.
     */
    public void drawBoard(){
    	JLayeredPane pane = this.getBoardPane();
    	pane.removeAll();
    	
    	// Board: fields & figures.
    	for(int i=0; i<=39; i++){
    	  Color color = Color.WHITE;
    	  
    	  Field field = this.fields.get(i);
    	  Color figureColor = null;
    	  
    	  if(!field.isFree()){
    	    Figure figure = field.getFigure();
    	    figureColor = figure.getFigureColor();
    	  }
    	  
    	  	  
    	  if(figureColor != null){
    		  // Draw figure.
        	  color = figureColor;
        	  this.drawField(pane, i, color, Color.BLACK, "figure");
          }  
    	  else {
    		  // Draw field.
    		  switch(i){
	      	    case 0:
	      		  color = Color.BLACK;
	      		  break;
	      	    case 10:
	        		  color = Color.YELLOW;
	        		  break;
	      	    case 20:
	        		  color = Color.GREEN;
	        		  break;
	      	    case 30:
	        		  color = Color.RED;
	        		  break;
	        		default:
	        		  color = Color.WHITE;
	        		  break;
	      	  }
    		  
    	      this.drawField(pane, i, color, Color.BLACK, "field");
    	  }
    	}
    	
    	// Houses.
    	//@TODO: Setzen von Figuren im Haus.
    	/*
    	this.drawField(pane, 101, Color.BLACK, Color.BLACK, "field");
    	this.drawField(pane, 102, Color.BLACK, Color.BLACK, "field");
    	this.drawField(pane, 103, Color.BLACK, Color.BLACK, "field");
    	this.drawField(pane, 104, Color.BLACK, Color.BLACK, "field");
    	
    	this.drawField(pane, 201, Color.YELLOW, Color.BLACK, "field");
    	this.drawField(pane, 202, Color.YELLOW, Color.BLACK, "field");
    	this.drawField(pane, 203, Color.YELLOW, Color.BLACK, "field");
    	this.drawField(pane, 204, Color.YELLOW, Color.BLACK, "field");
    	
    	this.drawField(pane, 301, Color.GREEN, Color.BLACK, "field");
    	this.drawField(pane, 302, Color.GREEN, Color.BLACK, "field");
    	this.drawField(pane, 303, Color.GREEN, Color.BLACK, "field");
    	this.drawField(pane, 304, Color.GREEN, Color.BLACK, "field");
    	
    	this.drawField(pane, 401, Color.RED, Color.BLACK, "field");
    	this.drawField(pane, 402, Color.RED, Color.BLACK, "field");
    	this.drawField(pane, 403, Color.RED, Color.BLACK, "field");
    	this.drawField(pane, 404, Color.RED, Color.BLACK, "field");
    	*/
    	
    	// Cube.
		// yellow: 130, 20
		// green: 380, 20
		// red: 380, 480
		// black: 130, 480
		//this.drawCube(pane, 130, 480);
		
    	// Start.
    	//@TODO: Setzen von Figuren auf Startfeldern.
    	for (Map.Entry<String, Player> entry : players.entrySet()){
    		Player player = entry.getValue();
    		int figuresAtStartPosition = player.getCountOfFiguresAtStartPosition();
    		Map<Integer, Figure> houseFigures = player.getHouseFigures();
    		Color color = player.getPlayerColor();
    		String playerName = player.getPlayerName();
    		    		
    		// Figures at house position.
			Boolean[] house = {false,false,false,false,false};
			//System.out.println(""+house[0]+" "+house[1]+" "+house[2]+" "+house[3]+" "+house[4]);
			for(Entry<Integer, Figure> hf: houseFigures.entrySet()){
    			int houseField = hf.getValue().getNumber();
    			house [houseField] = true;        			
    		}
			
			// Get player status (active/not active)
			Boolean activePlayer = player.isActive();
			
    		if(color == Color.BLACK){
    			// Cube.
    			if(activePlayer == TRUE){
    			  this.drawCube(pane, 130, 480);
    			}
    			
    			// Player name.
    			this.drawText(pane, playerName, 20, 570, 100, 20, Color.BLACK);
    			
    			// Figures at start position.
    			if(figuresAtStartPosition == 0){
    				this.drawField(pane, 1001, Color.BLACK, Color.BLACK, "field");
    				this.drawField(pane, 1002, Color.BLACK, Color.BLACK, "field");
    				this.drawField(pane, 1003, Color.BLACK, Color.BLACK, "field");
    				this.drawField(pane, 1004, Color.BLACK, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 1){
    				this.drawField(pane, 1001, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1002, Color.BLACK, Color.BLACK, "field");
    				this.drawField(pane, 1003, Color.BLACK, Color.BLACK, "field");
    				this.drawField(pane, 1004, Color.BLACK, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 2){
    				this.drawField(pane, 1001, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1002, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1003, Color.BLACK, Color.BLACK, "field");
    				this.drawField(pane, 1004, Color.BLACK, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 3){
    				this.drawField(pane, 1001, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1002, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1003, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1004, Color.BLACK, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 4){
    				this.drawField(pane, 1001, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1002, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1003, Color.BLACK, Color.BLACK, "figure");
    				this.drawField(pane, 1004, Color.BLACK, Color.BLACK, "figure");
    			}
    			
    			// Figures at house position.
    			if(house[1] == true){
    			    this.drawField(pane, 101, Color.BLACK, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 101, Color.BLACK, Color.BLACK, "field");
    			}
    			if(house[2] == true){
    			    this.drawField(pane, 102, Color.BLACK, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 102, Color.BLACK, Color.BLACK, "field");
    			}
    			if(house[3] == true){
    			    this.drawField(pane, 103, Color.BLACK, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 103, Color.BLACK, Color.BLACK, "field");
    			}
    			if(house[4] == true){
    			    this.drawField(pane, 104, Color.BLACK, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 104, Color.BLACK, Color.BLACK, "field");
    			}
    		}
    		
    		if(color == Color.YELLOW){
    			// Cube.
    			if(activePlayer == TRUE){
    			  this.drawCube(pane, 130, 20);
    			}
    			
    			// Player name.
    			this.drawText(pane, playerName, 20, 120, 80, 20, Color.BLACK);
    			
    			// Figures at start position.
    			if(figuresAtStartPosition == 0){
    				this.drawField(pane, 2001, Color.YELLOW, Color.BLACK, "field");
    				this.drawField(pane, 2002, Color.YELLOW, Color.BLACK, "field");
    				this.drawField(pane, 2003, Color.YELLOW, Color.BLACK, "field");
    				this.drawField(pane, 2004, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 1){
    				this.drawField(pane, 2001, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2002, Color.YELLOW, Color.BLACK, "field");
    				this.drawField(pane, 2003, Color.YELLOW, Color.BLACK, "field");
    				this.drawField(pane, 2004, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 2){
    				this.drawField(pane, 2001, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2002, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2003, Color.YELLOW, Color.BLACK, "field");
    				this.drawField(pane, 2004, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 3){
    				this.drawField(pane, 2001, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2002, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2003, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2004, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 4){
    				this.drawField(pane, 2001, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2002, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2003, Color.YELLOW, Color.BLACK, "figure");
    				this.drawField(pane, 2004, Color.YELLOW, Color.BLACK, "figure");
    			}
    			
    			// Figures at house position.
    			if(house[1] == true){
    			    this.drawField(pane, 201, Color.YELLOW, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 201, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(house[2] == true){
    			    this.drawField(pane, 202, Color.YELLOW, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 202, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(house[3] == true){
    			    this.drawField(pane, 203, Color.YELLOW, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 203, Color.YELLOW, Color.BLACK, "field");
    			}
    			if(house[4] == true){
    			    this.drawField(pane, 204, Color.YELLOW, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 204, Color.YELLOW, Color.BLACK, "field");
    			}
    		}
    		
    		if(color == Color.GREEN){
    			// Cube.
    			if(activePlayer == TRUE){
    			  this.drawCube(pane, 380, 20);
    			}
    			
    			// Player name.
    			this.drawText(pane, playerName, 470, 120, 80, 20, Color.BLACK);
    			
    			// Figures at start position.
    			if(figuresAtStartPosition == 0){
    				this.drawField(pane, 3001, Color.GREEN, Color.BLACK, "field");
    				this.drawField(pane, 3002, Color.GREEN, Color.BLACK, "field");
    				this.drawField(pane, 3003, Color.GREEN, Color.BLACK, "field");
    				this.drawField(pane, 3004, Color.GREEN, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 1){
    				this.drawField(pane, 3001, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3002, Color.GREEN, Color.BLACK, "field");
    				this.drawField(pane, 3003, Color.GREEN, Color.BLACK, "field");
    				this.drawField(pane, 3004, Color.GREEN, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 2){
    				this.drawField(pane, 3001, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3002, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3003, Color.GREEN, Color.BLACK, "field");
    				this.drawField(pane, 3004, Color.GREEN, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 3){
    				this.drawField(pane, 3001, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3002, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3003, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3004, Color.GREEN, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 4){
    				this.drawField(pane, 3001, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3002, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3003, Color.GREEN, Color.BLACK, "figure");
    				this.drawField(pane, 3004, Color.GREEN, Color.BLACK, "figure");
    			}
    			
    			// Figures at house position.
    			if(house[1] == true){
    			    this.drawField(pane, 301, Color.GREEN, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 301, Color.GREEN, Color.BLACK, "field");
    			}
    			if(house[2] == true){
    			    this.drawField(pane, 302, Color.GREEN, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 302, Color.GREEN, Color.BLACK, "field");
    			}
    			if(house[3] == true){
    			    this.drawField(pane, 303, Color.GREEN, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 303, Color.GREEN, Color.BLACK, "field");
    			}
    			if(house[4] == true){
    			    this.drawField(pane, 304, Color.GREEN, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 304, Color.GREEN, Color.BLACK, "field");
    			}
    		}
    		
    		if(color == Color.RED){
    			// Cube.
    			// yellow: 130, 20
    			// green: 380, 20
    			// red: 380, 480
    			// black: 130, 480
    			if(activePlayer == TRUE){
    			  this.drawCube(pane, 380, 480);
    			}
    			
    			// Player name.
    			this.drawText(pane, playerName, 470, 570, 80, 20, Color.BLACK);
    			
    			// Figures at start position.
    			if(figuresAtStartPosition == 0){
    				this.drawField(pane, 4001, Color.RED, Color.BLACK, "field");
    				this.drawField(pane, 4002, Color.RED, Color.BLACK, "field");
    				this.drawField(pane, 4003, Color.RED, Color.BLACK, "field");
    				this.drawField(pane, 4004, Color.RED, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 1){
    				this.drawField(pane, 4001, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4002, Color.RED, Color.BLACK, "field");
    				this.drawField(pane, 4003, Color.RED, Color.BLACK, "field");
    				this.drawField(pane, 4004, Color.RED, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 2){
    				this.drawField(pane, 4001, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4002, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4003, Color.RED, Color.BLACK, "field");
    				this.drawField(pane, 4004, Color.RED, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 3){
    				this.drawField(pane, 4001, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4002, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4003, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4004, Color.RED, Color.BLACK, "field");
    			}
    			if(figuresAtStartPosition == 4){
    				this.drawField(pane, 4001, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4002, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4003, Color.RED, Color.BLACK, "figure");
    				this.drawField(pane, 4004, Color.RED, Color.BLACK, "figure");
    			}
    			
    			// Figures at house position.
    			if(house[1] == true){
    			    this.drawField(pane, 401, Color.RED, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 401, Color.RED, Color.BLACK, "field");
    			}
    			if(house[2] == true){
    			    this.drawField(pane, 402, Color.RED, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 402, Color.RED, Color.BLACK, "field");
    			}
    			if(house[3] == true){
    			    this.drawField(pane, 403, Color.RED, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 403, Color.RED, Color.BLACK, "field");
    			}
    			if(house[4] == true){
    			    this.drawField(pane, 404, Color.RED, Color.BLACK, "figure");
    			} else {
    				this.drawField(pane, 404, Color.RED, Color.BLACK, "field");
    			}
    		}
    	}
    	
    	/*
    	// Player names.
    	for (Map.Entry<String, Player> entry : this.players.entrySet()){
    		Player player = entry.getValue();
    		Color playerColor = player.getPlayerColor();
    		String playerName = player.getPlayerName();
    		int n = 0;
    		
    		if(playerColor == Color.YELLOW){ n = 1;};
    		if(playerColor == Color.GREEN){ n = 2;};
    		if(playerColor == Color.BLACK){ n = 3;};
    		if(playerColor == Color.RED){ n = 4;};
    		
    		switch(n){
    		  case 1:
    			  // Yellow.
    			  this.drawText(pane, playerName, 20, 120, 80, 20, Color.BLACK);
    			break;
    		  case 2:
    			  // Green.
    			  this.drawText(pane, playerName, 470, 120, 80, 20, Color.BLACK);
      			break;
    		  case 3:
    			  // Black.
    			  this.drawText(pane, playerName, 20, 570, 100, 20, Color.BLACK);
      			break;
    		  case 4:
    			  // Red.
    			  this.drawText(pane, playerName, 470, 570, 80, 20, Color.BLACK);
      			break;
    		}
    	}
    	*/
    }
    
    private int getOffsetX(){
      return this.offsetX;
    }
    
    private int getOffsetY(){
      return this.offsetY;
    }
    
    public void displayMessage(String msg){
      this.msgBox.append("\n" + msg);
      this.msgBox.setCaretPosition(this.msgBox.getDocument().getLength());
    }
    
    private void setupFieldCoordinates(int fieldnumber, String position){
      String[] gridpos = position.split("-");
      
      int x = this.getOffsetX() + Integer.parseInt(gridpos[0]) *50;
      int y = this.getOffsetY() + Integer.parseInt(gridpos[1]) *50;
      int[] coords = {x, y};
      
      this.fieldCoordinates.put(fieldnumber, coords);
    }
    
    private int[] getFieldCoordinates(int fieldnumber){
      return this.fieldCoordinates.get(fieldnumber);
    }
    
    private void setupFieldOnGrid(String position, int fieldnumber){
      this.fieldGrid.put(position, fieldnumber); 
      this.setupFieldCoordinates(fieldnumber, position);
    }
    
    private void setupFieldGrid(){
      this.setupFieldOnGrid("4-10", 0); 
      this.setupFieldOnGrid("4-9", 1);
      this.setupFieldOnGrid("4-8", 2);
      this.setupFieldOnGrid("4-7", 3);
      this.setupFieldOnGrid("4-6", 4);
      this.setupFieldOnGrid("3-6", 5);
      this.setupFieldOnGrid("2-6", 6);
      this.setupFieldOnGrid("1-6", 7);
      this.setupFieldOnGrid("0-6", 8);
      this.setupFieldOnGrid("0-5", 9);
      this.setupFieldOnGrid("0-4", 10);
      this.setupFieldOnGrid("1-4", 11);
      this.setupFieldOnGrid("2-4", 12);
      this.setupFieldOnGrid("3-4", 13);
      this.setupFieldOnGrid("4-4", 14);
      this.setupFieldOnGrid("4-3", 15);
      this.setupFieldOnGrid("4-2", 16);
      this.setupFieldOnGrid("4-1", 17);
      this.setupFieldOnGrid("4-0", 18);
      this.setupFieldOnGrid("5-0", 19);
      this.setupFieldOnGrid("6-0", 20);
      this.setupFieldOnGrid("6-1", 21);
      this.setupFieldOnGrid("6-2", 22);
      this.setupFieldOnGrid("6-3", 23);
      this.setupFieldOnGrid("6-4", 24);
      this.setupFieldOnGrid("7-4", 25);
      this.setupFieldOnGrid("8-4", 26);
      this.setupFieldOnGrid("9-4", 27);
      this.setupFieldOnGrid("10-4", 28);
      this.setupFieldOnGrid("10-5", 29);
      this.setupFieldOnGrid("10-6", 30);
      this.setupFieldOnGrid("9-6", 31);
      this.setupFieldOnGrid("8-6", 32);
      this.setupFieldOnGrid("7-6", 33);
      this.setupFieldOnGrid("6-6", 34);
      this.setupFieldOnGrid("6-7", 35);
      this.setupFieldOnGrid("6-8", 36);
      this.setupFieldOnGrid("6-9", 37);
      this.setupFieldOnGrid("6-10", 38);
      this.setupFieldOnGrid("5-10", 39);
      
      // House of player 1 (black).
      this.setupFieldOnGrid("5-9", 101);
      this.setupFieldOnGrid("5-8", 102);
      this.setupFieldOnGrid("5-7", 103);
      this.setupFieldOnGrid("5-6", 104);
      // House of player 2 (yellow).
      this.setupFieldOnGrid("1-5", 201);
      this.setupFieldOnGrid("2-5", 202);
      this.setupFieldOnGrid("3-5", 203);
      this.setupFieldOnGrid("4-5", 204);
      // House of player 3 (green).
      this.setupFieldOnGrid("5-1", 301);
      this.setupFieldOnGrid("5-2", 302);
      this.setupFieldOnGrid("5-3", 303);
      this.setupFieldOnGrid("5-4", 304);
      // House of player 4 (red).
      this.setupFieldOnGrid("9-5", 401);
      this.setupFieldOnGrid("8-5", 402);
      this.setupFieldOnGrid("7-5", 403);
      this.setupFieldOnGrid("6-5", 404);
      
      // Start of player 1.
      this.setupFieldOnGrid("0-9", 1001);
      this.setupFieldOnGrid("1-9", 1002);
      this.setupFieldOnGrid("0-10", 1003);
      this.setupFieldOnGrid("1-10", 1004);
      // Start of player 2.
      this.setupFieldOnGrid("0-0", 2001);
      this.setupFieldOnGrid("1-0", 2002);
      this.setupFieldOnGrid("0-1", 2003);
      this.setupFieldOnGrid("1-1", 2004);
      // Start of player 3.
      this.setupFieldOnGrid("9-0", 3001);
      this.setupFieldOnGrid("10-0", 3002);
      this.setupFieldOnGrid("9-1", 3003);
      this.setupFieldOnGrid("10-1", 3004);
      // Start of player 4.
      this.setupFieldOnGrid("9-9", 4001);
      this.setupFieldOnGrid("9-10", 4002);
      this.setupFieldOnGrid("10-9", 4003);
      this.setupFieldOnGrid("10-10", 4004);
    }
    
    private String getClickedField(int x, int y){
      int fieldX = (x - this.getOffsetX()) / 50;
      int fieldY = (y - this.getOffsetY()) / 50;
      
      
      if((x - this.getOffsetX()) % 50 <= 20) {
    	  fieldX -= 1;
      }
      
      if((y - this.getOffsetX()) % 50 <= 20) {
    	  fieldY -= 1;
      }
      
      //System.out.println(((x - this.getOffsetX()) / 50) + "/" + (y - this.getOffsetY()) / 50);
      //System.out.println(((x - this.getOffsetX()) % 50) + "/" + (y - this.getOffsetY()) % 50);
      String position = fieldX + "-" + fieldY;
      
      if(this.fieldGrid.containsKey(position) == true){
        int fieldnumber = this.fieldGrid.get(position);
        
        int[] pos = this.getFieldCoordinates(fieldnumber);
        //System.out.println(""+pos[0]+"/"+pos[1]);
        
        // Ignore click if clicked too far beyond field limits.
        //if((x - this.getOffsetX()) % 50 > 44 || (y - this.getOffsetY()) % 50 > 44){ 
        int posx = pos[0];
        int posy = pos[1];
        // Folgenden Vergleich erweitern: && x >= posx <= posx+50 && y >= posy <= posy+50
        
        //if((x - this.getOffsetX()) % 50 < 20 || (y - this.getOffsetY()) % 50 < 20){
        if(x < posx || x > posx+60 || y < posy || y > posy+60){
          fieldnumber = -1;
        }
        
        if(fieldnumber != -1 && fieldnumber < 1000) {
          return "" + fieldnumber;
        }
      }
      
      return "-1";
    }
    
    
//    private void drawPiece(Color color, int field){
//    	int[] coordinates = this.getFieldCoordinates(field);
//    	int posX = coordinates[0];
//    	int posY = coordinates[1];
//    	
//    	// Draw piece.
//    	this.drawField(boardPane, field, color);
//    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int posX = e.getX();
        int posY = e.getY();
        
        String clickedField = this.getClickedField(posX, posY);
        
        // Ignore clicks beyond gameboard limits.
        //if(posX >= this.getOffsetX() && posX < 570 && posY >= this.getOffsetY() && posY < 575){
        if(posX >= this.getOffsetX() && posX < 600 && posY >= this.getOffsetY() && posY < 600){
          //this.displayMessage("Mouse clicked at x=" + posX + " y=" + posY +". Feld " + clickedField);
          //this.displayMessage("Feld " + clickedField);
          this.clickListener.fieldClicked(Integer.valueOf(clickedField));
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
    	test();
    }

    private static void test(){
    	// Players
    	String[] playerNames = {"Gernhart Reinholzen","Lassmiranda den si Villia","Timo Beil","Anne Theke"};//Playernames
    	
    	Map<String, Player> players = new HashMap<String, Player>();
        
        int iOffset = 0;// Offset of Fieldposition.
		boolean bIsComputer = false; // Is Computer?
        
		Color[] colorArray = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW };//Color of Players
				
		for (int i = 0; i < playerNames.length; i++)
		{
			Player player = new Player(colorArray[i],bIsComputer,iOffset,playerNames[i]);
			players.put(player.getPlayerColor().toString(), player);  // = new Player();
			iOffset += 10;
		}
        
		// Fields
		int fieldCount = 40;
		HashMap<Integer,Field> fields = new HashMap<Integer,Field>();
		int iFieldCount = fieldCount; //Number of Fields ( without House )
		Field.Type type;
		
		for (Integer i = 0; i < iFieldCount; i++)
		{
			if (i % 10 == 0) 
			{ 
				type = Field.Type.START; 
			}
			else
			{
				type = Field.Type.NORMAL;
			}
			fields.put(i, new Field(type,i));
		}
		
		// Gamehandler
		Options op = new Options();

		Gamehandler gh = new Gamehandler(op, 
				new String[] {"Gernhart Reinholzen","Lassmiranda den si Villia","Timo Beil","Anne Theke"}, null);
		
		// Board
        Board board = new Board(fields, players, gh);
        
        // Draw.
        board.drawBoard();
        
        // Test.
        //Map<String, Player> play = gh.getPlayers();
        //Player p1 = play.get("Anne Theke");
        //System.out.println(play);
        //Map<Integer, Figure> figures = p1.getFigures();
        //Figure f1a = figures.get(1);
        //f1a.setSteps(3);
        
        board.drawBoard();
    }
    
    class TextLabel extends JLabel {
         public TextLabel(String s, int x, int y, Color c) {
            super(s);
            //this.setHorizontalAlignment(SwingConstants.CENTER);
            this.setHorizontalAlignment(SwingConstants.LEFT);
            this.setOpaque(true);
 
            this.setBounds(x, y, 50, 50);
        }
    }
 /*   
    class TextPanelxx extends JPanel {
    	String text;
    	int posX;
    	int posY;
    	Color textColor;
    	
    	public TextPanel(String s, int x, int y, Color c){
    	  super(true);
    	  this.text	= s;
    	  this.posX = x;
    	  this.posY = y;
    	  this.textColor = c;
    	}
    	
    	@Override
		protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		Graphics2D g2d = (Graphics2D)g;
    		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	    g2d.setPaint(Color.black);
    	    g2d.drawString(this.text, this.posX, this.posY);
    		//g.setColor(this.textColor);
    	    //g.drawString(this.text, this.posX, this.posY);
    	}
    }
*/    
	class PiecePanel extends JPanel {
		Color color;
		int posX;
		int posY;
		
		public PiecePanel(Color c, int x, int y){
			this.color = c;
			this.posX = x;
			this.posY = y;
		}
		
		@Override
		protected void paintComponent(Graphics g) { 
			super.paintComponent(g);
			g.setColor(this.color);
			g.fillOval(this.posX/2, this.posY/2, this.posX, this.posY);
		} 
	}
	
	class FieldPanel extends JPanel {
		Color color;
		Color border;
		int posX;
		int posY;
		String fieldtype;
		
		public FieldPanel(Color b, Color c, int x, int y, String ft){
			this.border = b;
			this.color = c;
			this.posX = x;
			this.posY = y;
			this.fieldtype = ft;
		}
		
		@Override
		protected void paintComponent(Graphics g) { 
			super.paintComponent(g);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
			g2d.setColor(this.color);
			g2d.fillOval(this.posX/2, this.posY/2, this.posX, this.posY);
			g2d.setColor(this.border);
			g2d.drawOval(this.posX/2, this.posY/2, this.posX, this.posY);

			if(this.fieldtype == "figure"){
				if(this.color == Color.BLACK){
					g2d.setColor(Color.WHITE);
				}
				else {
					g2d.setColor(Color.BLACK);	
				}
				
				g2d.fillOval(this.posX -10, this.posY -10, this.posX/2, this.posY/2);
				g2d.setColor(this.color);
				g2d.fillOval(this.posX -7, this.posY -7, this.posX/2 -4, this.posY/2 -4);
				//g2d.fillOval(this.posX -8, this.posY -8, this.posX/2 -4, this.posY/2 -4);
			}
		} 
	}
	
	// Close button action listener.
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == this.btnClose){
			this.clickListener.closeGame();
        }
	}
}