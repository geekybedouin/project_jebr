/*
 * Code Name: WorkSpace.java
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Workspace extends JDialog{
	
	protected File workspace = null ;
	protected String homedir = System.getProperty("user.home");
	final protected JTextField textField;
	
	protected String getHomedir(){
		return homedir;
	}
	protected String getpathToWorkFile(){
		return getHomedir()+"/.jide_1.04/workpath.in";
	}
	
	public File getPath()
	{
		return workspace;
	}
	
	public Workspace(){
		this.setTitle("Choose a Workspace..");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setBounds(450,250, 500, 250);
		this.setModal(true);
		this.setResizable(false);

		JLabel descLabel = new JLabel("\r\n" + "Please enter the specified path to your direcory: ");
		String regPath ="";
		Scanner pathReader = null;
		
		try{
			pathReader = new Scanner(new File(getpathToWorkFile()));
		}
		catch(Exception e){
			
		}
		
		if (pathReader == null){
			regPath ="                                                   ";
		}
		else
			if (pathReader.hasNext())
				regPath = pathReader.nextLine();
			else {	
				JOptionPane.showMessageDialog(null, "Critical Error: E215 WorkSpace Path not Found!!", "ERROR!", JOptionPane.ERROR_MESSAGE);
			}
				
		
	    textField = new JTextField(regPath);
		workspace = new File(textField.getText());
		textField.setColumns(20);
		JButton browse = new JButton("browse");
		final JButton ok = new JButton("Ok");
		JButton cancel = new JButton("Cancel");
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		JPanel south_east = new JPanel();
		
		Dimension panelDim = new Dimension(150, 50);
		north.setPreferredSize(panelDim);
		center.setPreferredSize(panelDim);
		
		this.getContentPane().add(north, BorderLayout.NORTH);
		this.getContentPane().add(center, BorderLayout.CENTER);
		this.getContentPane().add(south, BorderLayout.SOUTH);
		
		south.setLayout(new BorderLayout());
		south.add(south_east, BorderLayout.EAST);
		
		north.add(descLabel);
		center.add(textField);
		center.add(browse);
		south_east.add(ok);
		south_east.add(cancel);
		
		//Cancel Behaviour
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				dispose();
				
			}
		});
		
		browse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);

		        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		            workspace = fc.getSelectedFile();
		           
		            textField.setText(workspace.getAbsolutePath());
		            //This is where a real application would open the file.
		           
		        } else {
		            
		        }
				
			}
		});
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (textField.getText().equals("")){
			       ok.setEnabled(false);
			     }
			     else {
			       ok.setEnabled(true);
			    }

			  }
			});
		//Ok- Behaviour
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					/*if (textField)
					{
						JOptionPane.showMessageDialog(null, "Error 522: No Workspace Chosen", "Workspace Error!", JOptionPane.ERROR_MESSAGE);
					}
						*/
					if (workspace.exists()){
						File fout = new File(getpathToWorkFile());
						FileWriter fw = null;
						try {
							fw = new FileWriter(fout.getAbsoluteFile());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						BufferedWriter bw = new BufferedWriter(fw);
						try {
							bw.write(workspace.getAbsolutePath());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Window ide = new Window(workspace);
						setVisible(false); 
						dispose();
					}
					else
						workspace.mkdirs();
					
				
				
			}
		});
		
		this.setVisible(true);
		
	
	}
	
}
