package com.mensch_aergere_dich_nicht.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mensch_aergere_dich_nicht.models.Cube;

/**
 * Class represents a 2D Cube.
 * You can add it to a Frame. Before you add, you have to set the Frame Layout to null. If you click on the cube view you throw the cube and you see the new cube number on the screen.
 * @author tsawatzki
 *
 */
public class CubeView extends JPanel implements MouseListener{

	private int positionX;
	private int positionY;
	private final int cubeSizeHeight = 80;
	private final int cubeSizeWidth = 80;
	private Cube cube;
	private int cubeNumber;
	
	//Cube attributes
	private final int ovalWidth = 10;
	private final int ovalHeigth = 10;
	private final Point p1 = new Point (15,15);
	private final Point p2 = new Point (15,35);
	private final Point p3 = new Point (15,55);
	private final Point p4 = new Point (50,15);
	private final Point p5 = new Point (50,35);
	private final Point p6 = new Point (50,55);
	private final Point p7 = new Point (33,35);
	
	/**
	 * Constructor for CubeView.
	 * Build Up the Cube with a start number of 1
	 * 
	 * @param positionX
	 * Position X in Frame
	 * @param positionY
	 * Position Y in Frame
	 */
	public CubeView(int positionX, int positionY){
		cube = new Cube();
		this.positionX = positionX;
		this.positionY = positionY;	
		cubeNumber = 1;
		setLocation(positionX, positionY);
		setSize(cubeSizeWidth, cubeSizeHeight);	
		addMouseListener(this);
		setLayout(null);
	}
	
	/**
	 * Setter-Method. Sets variable cubeNumber to 1-6
	 * @param cubeNumber integer between 1-6
	 */
	private void setCubeNumber(int cubeNumber){
		this.cubeNumber = cubeNumber;
	}
	
	/**
	 * Getter-Method
	 * @return the actual cubeNumber 
	 */
	public int getCubeNumber(){
		return cubeNumber;
	}
	
	
	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		width = cubeSizeWidth;
		height = cubeSizeHeight;
		super.setSize(width, height);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, this.getBounds().width-1, this.getBounds().height-1, 10, 10);
		g.setColor(Color.white);
		g.fillRoundRect(1, 1, this.getBounds().width-2, this.getBounds().height-2, 10, 10);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		switch(cubeNumber){
		case 1:{
			g.drawOval((int)p7.getX(), (int)p7.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p7.getX(), (int)p7.getY(), ovalWidth, ovalHeigth);
		}break;
		
		case 2:{
			g.drawOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
		}break;
		
		case 3:{
			g.drawOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p7.getX(), (int)p7.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p7.getX(), (int)p7.getY(), ovalWidth, ovalHeigth);
		}break;
		
		case 4:{
			g.drawOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p3.getX(), (int)p3.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p3.getX(), (int)p3.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p4.getX(), (int)p4.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p4.getX(), (int)p4.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
		}break;
		
		case 5:{
			g.drawOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p3.getX(), (int)p3.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p3.getX(), (int)p3.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p4.getX(), (int)p4.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p4.getX(), (int)p4.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p7.getX(), (int)p7.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p7.getX(), (int)p7.getY(), ovalWidth, ovalHeigth);
		} break;
		
		case 6:{
			g.drawOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p1.getX(), (int)p1.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p2.getX(), (int)p2.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p2.getX(), (int)p2.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p3.getX(), (int)p3.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p3.getX(), (int)p3.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p4.getX(), (int)p4.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p4.getX(), (int)p4.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p5.getX(), (int)p5.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p5.getX(), (int)p5.getY(), ovalWidth, ovalHeigth);
			
			g.drawOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);
			g.fillOval((int)p6.getX(), (int)p6.getY(), ovalWidth, ovalHeigth);		
		}break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		setCubeNumber(cube.throwCube());
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}