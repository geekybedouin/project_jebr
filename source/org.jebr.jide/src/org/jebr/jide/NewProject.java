/*
 * Code Name: NewProject.java
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
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewProject extends JDialog {
	
	protected boolean synch = false;
	protected boolean state = false; //if true project avail.. if false no project
	protected File writeFile = null;
	
	protected void changeBool(boolean temp)
	{
		synch = temp;
	}
	protected void changeState(boolean temp)
	{
		state = temp;
	}
	public boolean getState()
	{
		return state;
	}
	public boolean isReady()
	{
		return synch;
	}
	protected void setFile(File path){
		writeFile = path;
	}
	public File getFile()
	{
		return writeFile;
	}
	
	public NewProject(File Workspace)
	{
		this.setTitle("Create a Project");
		this.setBounds(450,250,500,250);
		this.setModal(true);
		this.setResizable(false);

		JLabel desc = new JLabel("Note that a directory will be created in the workspace path.");
		JLabel name = new JLabel("Name: ");
		
		final File work = Workspace;
		final JTextField nameText = new JTextField("");
		nameText.setColumns(30);
		JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");
		cancel.setSelected(true);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File temp = new File(work.getAbsolutePath() + "/" + nameText.getText());
				if(temp.exists()){
					JOptionPane.showMessageDialog(null, 
													"Wake up, Jim!! Project with the same name already Exists!!", 
													"Error Opening File"
													, JOptionPane.WARNING_MESSAGE);
					changeState(false);
					changeBool(true);
				}
				else{
					temp.mkdir();
					setFile(temp);
					try {
						 
					      File file = new File(temp.getAbsolutePath() + "/stdsrc.jsrc");
				 
					      if (file.createNewFile()){
					        System.out.println("File is created!");
					      }else{
					        System.out.println("File already exists.");
					      }
				 
				    	} catch (IOException s) {
					      s.printStackTrace();
					}
					try {
						 
						 File file = new File(temp.getAbsolutePath() + "/stdlib.jlb");
				 
					      if (file.createNewFile()){
					        System.out.println("File is created!");
					      }else{
					        System.out.println("File already exists.");
					      }
				 
				    	} catch (IOException s) {
					      s.printStackTrace();
					}
					try {
						 
					      File file = new File(temp.getAbsolutePath() + "/.sett");
				 
					      if (file.createNewFile()){
					        System.out.println("File is created!");
					      }else{
					        System.out.println("File already exists.");
					      }
				 
				    	} catch (IOException s) {
					      s.printStackTrace();
					}
					try {
						 
					      File file = new File(temp.getAbsolutePath() + "/stdout.jo");
				 
					      if (file.createNewFile()){
					        System.out.println("File is created!");
					      }else{
					        System.out.println("File already exists.");
					      }
				 
				    	} catch (IOException s) {
					      s.printStackTrace();
					}
					changeState(true);
					changeBool(true); //to make it ready :)
					setVisible(false);
					dispose();
				}
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeState(false);
				changeBool(true);
				setVisible(false);
				dispose();
			}
		});
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		JPanel south_east = new JPanel();
		
		south.setLayout(new BorderLayout());
		
		this.getContentPane().add(north, BorderLayout.NORTH);
		this.getContentPane().add(center, BorderLayout.CENTER);
		this.getContentPane().add(south, BorderLayout.SOUTH);
		south.add(south_east,BorderLayout.EAST);
		
		Dimension preferredSize = new Dimension(150,70);
		north.setPreferredSize(preferredSize);
		
		north.add(desc);
		center.add(name);
		center.add(nameText);
		south_east.add(ok);
		south_east.add(cancel);
		
		this.setVisible(true);
		
	}

}
