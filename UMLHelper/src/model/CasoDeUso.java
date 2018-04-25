/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Antonio
 */
public class CasoDeUso extends JLabel{
    private Point initiPos = new Point(0, 0);
    private boolean dragging = false;
    Ellipse2D.Double casoDeUso;
    
    public CasoDeUso(){
        super();
        initListeners();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));       
        this.setSize(100, 100);
        System.out.println("Im creating a use case");
        repaint();
    }
    
    public void removerCasoDeUso(){
        Container cont = this.getParent();
        cont.remove(this);
        cont.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100,100); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        casoDeUso = new Ellipse2D.Double(200, 200, 100, 150);
        g2.setColor(Color.red);
        g2.fill(casoDeUso);
    }
    
    public void initListeners(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3){
                    JPopupMenu jPop = new JPopupMenu();
                    JMenuItem jMenu = new JMenuItem("Deletar");
                    jMenu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //System.out.println("You're asking to remove me");
                            removerCasoDeUso();                          
                        }
                    });
                    jPop.add(jMenu);
                    jPop.show(getParent(), getX()+75, getY());
                }
            }
            
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initiPos = e.getPoint();
                //repaint();
                dragging = true;
            }
            
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - initiPos.x;
                int dy = e.getY() - initiPos.y;
                setLocation(getX() + dx, getY() + dy);
                initiPos = e.getPoint();
                if(dragging){
                    repaint();
                }
            }
            
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false;
            }  
        });   
    }   
}