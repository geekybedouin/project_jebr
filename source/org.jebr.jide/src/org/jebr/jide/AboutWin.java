/*
 * Code Name: AboutWin.java
 * Created By: 'Umar A.Abu Baker, Fahed N.Shehab <http://www.geekybedouin.com>, <>
 * Copyright (c) 2014 'Umar A.Abu Baker and Fahed N.Shehab <umr.baker@gmail.com>, <>
 * 
 * 
 * License: 
 * This file is part of Project Jebr.
    Project Jebr is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License.
    Project Jebr is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/gpl-3.0.txt>.
 * 
 * 
 * 
 * 
 * Version: 1.04(Delta)
 * Description: This file contains the source of JIDE(Jebr IDE) which is the official IDE for the Jebr language.
 * 
 * 
 * Wanna edit the code? READ FIRST:
 * 
 * For the brave souls who get this far: You are the chosen ones,
 * the valiant knights of programming who toil away, without rest,
 * fixing our most awful code. To you, true saviors, kings of men,
 * I say this: never gonna give you up, never gonna let you down,
 * never gonna run around and desert you. Never gonna make you cry,
 * never gonna say goodbye. Never gonna tell a lie and hurt you.
 * 
 * Now, take the oath:
 * 
 * Errors gather, and now my maintenance begins. It shall not end until my death.
 * I shall take no wife, hold no lands, father no children. I shall wear no crowns and win no glory.
 * I shall live and die maintaining this code. I am the sword in the darkness. I am the maintainer of the code.
 * I am the fire that burns against the cold, the light that brings the dawn, the horn that wakes the sleepers,
 * the shield that guards the realms of men. I pledge my life and honor to maintain this code,
 * for this night and all the nights to come.
 */


package org.jebr.jide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class AboutWin extends JDialog{
	
	public AboutWin()
	{
		this.setTitle("About..");
		this.setBounds(450,250,550,330);
		this.setResizable(false);
		
		JPanel north = new JPanel();
		JPanel west = new JPanel();
		JPanel south = new JPanel();
		JPanel center = new JPanel();
		
		JPanel south_east = new JPanel();
		
		this.getContentPane().add(north,BorderLayout.NORTH);
		this.getContentPane().add(west,BorderLayout.WEST);
		this.getContentPane().add(center,BorderLayout.CENTER);
		this.getContentPane().add(south,BorderLayout.SOUTH);
		
		south.setLayout(new BorderLayout());
		south.add(south_east,BorderLayout.EAST);
		
		JLabel Title = new JLabel("About JIDE 1.04 Alpha - Community Version");		
		ImageIcon myLogo = new ImageIcon(ClassLoader.getSystemResource("./jide.png"));
		JLabel logo = new JLabel(myLogo); 
		String text = "    JIDE core systems were forked from MEOW, which is a " +
				"very minimal development environment that 'Umar A.Abu Baker developed while taking " +
				"'Compiler Design' course. Based on it," +
				" JIDE, which is a bit more complicated IDE, was born to offer an interface for the interpreter and another one for JINT parser, where users can write classes" +
				" and functions. The first interface offers basic functionality and uses basic Java Swing components, while the " +
				"second interface offers syntax-highlighting and line-numbering. <br>" +
				"JIDE was developed in 2014-2015 at An-Najah National University - Computer Science Department.";
		JLabel desc = new JLabel("<html><div style=\"width:250px;\">"+ text + "</div></html>");
		JButton ok = new JButton("Informed");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
			}
		});
		
		north.add(Title);
		west.add(logo);
		south_east.add(ok);
		center.add(desc);
		
		
		this.setVisible(true);	
		
		
		
	}
}



