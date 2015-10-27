/*
 * Gameboard.
 */
package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;

//import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Board extends JFrame implements MouseListener{
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
    
    public Board(){
      super("Mensch ärgere dich nicht");
      
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
      //btnClose.addActionListener(this);
      
      // Message box (10 rows, 40 columns).
      msgBox = new JTextArea(30,20);
      msgBox.setText("Mensch ärgere dich nicht!\n");
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
      
      this.drawBoard();
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
    private void drawField(JLayeredPane pane, int fieldnumber, Color color){
    	int[] coordinates = this.getFieldCoordinates(fieldnumber);
    	int x = coordinates[0];
    	int y = coordinates[1];

    	FieldPanel field = new FieldPanel(color, 40,40); 
    	field.setBounds(x,y,100,100); 
    	field.setOpaque(false); 
    	pane.add(field, new Integer(1));
    }
    
    /*
     * Draw gameboard.
     */
    public void drawBoard(){
    	JLayeredPane pane = this.getBoardPane();
    	
    	// Board.
    	for(int i=0; i<=39; i++){
    	  Color color;
    	  
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
    	  
    	  this.drawField(pane, i, color);
    	}
    	
    	// Houses.
    	this.drawField(pane, 101, Color.BLACK);
    	this.drawField(pane, 102, Color.BLACK);
    	this.drawField(pane, 103, Color.BLACK);
    	this.drawField(pane, 104, Color.BLACK);
    	
    	this.drawField(pane, 201, Color.YELLOW);
    	this.drawField(pane, 202, Color.YELLOW);
    	this.drawField(pane, 203, Color.YELLOW);
    	this.drawField(pane, 204, Color.YELLOW);
    	
    	this.drawField(pane, 301, Color.GREEN);
    	this.drawField(pane, 302, Color.GREEN);
    	this.drawField(pane, 303, Color.GREEN);
    	this.drawField(pane, 304, Color.GREEN);
    	
    	this.drawField(pane, 401, Color.RED);
    	this.drawField(pane, 402, Color.RED);
    	this.drawField(pane, 403, Color.RED);
    	this.drawField(pane, 404, Color.RED);
    	
    	// Start.
    	this.drawField(pane, 1001, Color.BLACK);
    	this.drawField(pane, 1002, Color.BLACK);
    	this.drawField(pane, 1003, Color.BLACK);
    	this.drawField(pane, 1004, Color.BLACK);
    	
    	this.drawField(pane, 2001, Color.YELLOW);
    	this.drawField(pane, 2002, Color.YELLOW);
    	this.drawField(pane, 2003, Color.YELLOW);
    	this.drawField(pane, 2004, Color.YELLOW);
    	
    	this.drawField(pane, 3001, Color.GREEN);
    	this.drawField(pane, 3002, Color.GREEN);
    	this.drawField(pane, 3003, Color.GREEN);
    	this.drawField(pane, 3004, Color.GREEN);
    	
    	this.drawField(pane, 4001, Color.RED);
    	this.drawField(pane, 4002, Color.RED);
    	this.drawField(pane, 4003, Color.RED);
    	this.drawField(pane, 4004, Color.RED);
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
        System.out.println(""+pos[0]+"/"+pos[1]);
        
        // Ignore click if clicked too far beyond field limits.
        //if((x - this.getOffsetX()) % 50 > 44 || (y - this.getOffsetY()) % 50 > 44){ 
        // TODO:
        // 1. Über Klick das Feld bestimmen.
        // 2. Position von Feld holen.
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
      
      return "-";
    }
    
    
    private void drawPiece(Color color, int field){
    	int[] coordinates = this.getFieldCoordinates(field);
    	int posX = coordinates[0];
    	int posY = coordinates[1];
    	
    	// Draw piece.
    	this.drawField(boardPane, field, color);
    }
    
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
          this.displayMessage("Mouse clicked at x=" + posX + " y=" + posY +". Feld " + clickedField);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        Board board = new Board();
    }

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
		int posX;
		int posY;
		
		public FieldPanel(Color c, int x, int y){
			this.color = c;
			this.posX = x;
			this.posY = y;
		}
		
		@Override
		protected void paintComponent(Graphics g) { 
			super.paintComponent(g);
			g.setColor(this.color);
			g.fillOval(this.posX/2, this.posY/2, this.posX, this.posY);
			g.setColor(Color.BLACK);
			g.drawOval(this.posX/2, this.posY/2, this.posX, this.posY);
		} 
	}
}