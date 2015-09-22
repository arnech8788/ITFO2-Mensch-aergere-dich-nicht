/*
 * Gameboard with image backgound.
 */
package com.mensch_aergere_dich_nicht.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

public class Board extends JFrame implements MouseListener{
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
    int offsetX = 35;
    int offsetY = 35;
    
    public Board(){
      super("Mensch ärgere dich nicht");
      
      try {                
          image = ImageIO.read(getClass().getResource("board600.jpg"));
       } catch (IOException ex) {
            // Exception...
       }  
      
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
      
      // New boardPanel.
      boardPanel = new JPanel();
      boardPanel.add(new JLabel(new ImageIcon(image)));
      
      boardPanel.addMouseListener(this);
      this.addMouseListener(this);
      
      // New main panel.
      mainPanel = new JPanel();
      
      // Add elements to main panel.
      mainPanel.add(scrollPane);
      
      // Add components to Gameboard.
      this.add(boardPanel, BorderLayout.WEST);
      this.add(mainPanel, BorderLayout.EAST);
      this.add(btnClose, BorderLayout.SOUTH);
      
      // Frame configuration.
      //this.setLayout(new GridLayout());
      this.setSize(850, 650);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.setResizable(false);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      this.setupFieldGrid();
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
    }
    
    private String getClickedField(int x, int y){
      int fieldX = (x - this.getOffsetX()) / 50;
      int fieldY = (y - this.getOffsetY()) / 50;
      //System.out.println(((x - this.getOffsetX()) % 50) + "/" + (y - this.getOffsetY()) % 50);
      String position = fieldX + "-" + fieldY;
            
      if(this.fieldGrid.containsKey(position) == true){
        int fieldnumber = this.fieldGrid.get(position);
        
        // Ignore click if clicked too far beyond field limits.
        if((x - this.getOffsetX()) % 50 > 44 || (y - this.getOffsetY()) % 50 > 44){ 
          fieldnumber = -1;
        }
        
        if(fieldnumber != -1) {
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
        if(posX >= this.getOffsetX() && posX < 570 && posY >= this.getOffsetY() && posY < 575){
          this.displayMessage("Mouse clicked at x=" + posX + " y=" + posY +". Feld " + clickedField);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        Board board = new Board();
    }
}

