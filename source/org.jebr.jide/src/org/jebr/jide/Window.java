/*
 * Code Name: Window.java
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
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.undo.UndoManager;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.thehowtotutorial.splashscreen.JSplash;


public class Window implements ActionListener, KeyListener {
	RSyntaxTextArea interpreterLine;
	protected File workPath;
	protected Color clrbrdr = Color.decode("#1A1A42");
	protected Color clrdark = Color.decode("#041B40");
	protected Color clrlight = Color.decode("#3299ff");
	protected  JTextPane textPane;
	protected StyledDocument doc;
	private JFrame frame;
	public String nameOfFile;
	protected UndoManager manager = new UndoManager();
	/*Check for project Activity*/
	protected boolean projectAvail = false;
	
	/*Default Scanners and streams for active project :)*/
	protected FileWriter sw = null;
	protected BufferedWriter bsw = null;
	protected FileWriter ow = null;
	protected BufferedWriter bow = null;
	protected FileWriter lw = null;
	protected BufferedWriter blw = null;
	protected Scanner lib_head = null; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				
				
				try {
					Window.splashWindow();
					Workspace new_work = new Workspace();
					
				} catch (Exception e) {
					//Error
					return;
				}
			//}
		//});
	}
	public void setSrc(File temp){
		try {
			sw = new FileWriter(temp);
			bsw = new BufferedWriter(sw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setOutput(File temp){
		try {
			ow = new FileWriter(temp);
			bow = new BufferedWriter(ow);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setLibWriter(File temp){
		try {
			lw = new FileWriter(temp);
			blw = new BufferedWriter(lw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setLibReader(File temp){
		try {
			lib_head = new Scanner(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public File getPath()
	{
		return workPath;
	}
	public void setPath(File path)
	{
		workPath = path;
	}
	protected void toggleProjectAvial(boolean toggle)
	{
		projectAvail = true;
	}
	protected void setStreams()
	{
		
	}
	protected void colorPane()
	{
		//create necessary styles for various characters
        javax.swing.text.Style style = textPane.addStyle("Yellow", null);
        StyleConstants.setForeground(style,Color.decode("#ffcc00"));

        //create array of characters to check for and style
        
        String[] lettersToEdit = new String[]{">?", "Error", "JIDE", "1.04", "Alpha", "Version", "using", "JINT", "0b00", "second", "revision", "-------------------------------------------------------"};
        
        //create arraylist to hold each character in textPane
        
        String s[] = textPane.getText().split("\\s");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(s));

        //get all text
        
        String text = textPane.getText();

       

        //declare variabe to hold position
        int position = 0;

        for (String s1 : strings) {//for each character in the textpane text
            for (String s2 : lettersToEdit) {//for each character in array to check (lettersToEdit)
                if (s2.toLowerCase().equalsIgnoreCase(s1)) {//if there was a match

                	
                    //check which chacacter we matched
                    
                    
                        doc.setCharacterAttributes(position, s1.length(), textPane.getStyle("Yellow"), true);
                        break;
                    
                }
            }
            if (s1.matches("^\\s*$")) 
            	continue;
            	
            //increase position after each character on textPane is parsed
            position = position+s1.length()+1;
            }
	}
	private void newProject(){
		NewProject window = new NewProject(workPath);
		while(!window.isReady()); //Yes, busy waiting... Good ol' days 
		
		if(window.getState()){ //new project initialized
				toggleProjectAvial(true);
						
				//set Scanners and Streams on the file
		        File checkSett1 = new File((window.getFile()).getAbsolutePath()+"/stdlib.jlb");
		        File checkSett2 = new File((window.getFile()).getAbsolutePath()+"/stdout.jo");
		        File checkSett3 = new File((window.getFile()).getAbsolutePath()+"/stdsrc.jsrc");
				
				this.setSrc(checkSett3);
		    	this.setOutput(checkSett2);
		    	this.setLibReader(checkSett1);
		    	
				textPane.setText(textPane.getText() + ">? Project Created..  " + "\r\n"+">?");
				//colorPane();
		}
		else
		{
			
		}
		
	}
	
	private void openProject(){
		File temp = null;
		final JFileChooser fc = new JFileChooser(getPath());
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
           temp = fc.getSelectedFile();
        }
        
        //Change 
        File checkSett = new File(temp.getAbsolutePath()+"/.sett");
        File checkSett1 = new File(temp.getAbsolutePath()+"/stdlib.jlb");
        File checkSett2 = new File(temp.getAbsolutePath()+"/stdout.jo");
        File checkSett3 = new File(temp.getAbsolutePath()+"/stdsrc.jsrc");
        //Four files
        if (checkSett.exists()&&checkSett1.exists()&&checkSett2.exists()&&checkSett3.exists())
        {
        	toggleProjectAvial(true);
        	this.setSrc(checkSett3);
        	this.setOutput(checkSett2);
        	this.setLibReader(checkSett1);
        	textPane.setText(textPane.getText() + ">? Project Opened..  " + "\r\n"+">?");
    		//colorPane();
        	nameOfFile = temp.getAbsolutePath();
        	
        }
        else
        {
        	JOptionPane.showMessageDialog(null, "Error E785: Not a valid Project", "Error", JOptionPane.WARNING_MESSAGE);
        }
        	
            
            //This is where a real application would open the file.
		
	}
	private void about(){
		AboutWin window = new AboutWin();

	}
	
	/**
	 * Create the application.
	 */
	public Window(File path) {

		setPath(path);
		initialize_frame();
	}
	
	
	public static void splashWindow()
	{	
           JSplash S = new JSplash(ClassLoader.getSystemResource("./splash.jpg"), false, true, false, "", null,null, null) ;
           S.splashOn();
           try {
           Thread.sleep(3000);
           S.splashOff();
           }
           catch(Exception s)
           {
        	  s.printStackTrace();
           }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void initialize_frame() {
		//run the splash window
		
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(clrdark);
		frame.setForeground(Color.decode("#212121"));
		frame.setTitle("JIDE° 1.04 Alpha (Community Version) - ");
		frame.setResizable(false);

		/** Overriding the default UIManagaer **/ /**
		UIManager.put("ScrollBar.trackHighlightForeground", (new Color(57,57,57))); 
	    UIManager.put("scrollbar", (new Color(57,57,57))); 
	    UIManager.put("ScrollBar.thumb", new ColorUIResource(new Color(57,57,57))); 
	    UIManager.put("ScrollBar.thumbHeight", 2); 
	    UIManager.put("ScrollBar.background", (new Color(57,57,57)));
	    UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.trackForeground", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.trackHighlight", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.foreground", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.shadow", new ColorUIResource(new Color(57,57,57)));
	    UIManager.put("ScrollBar.highlight", new ColorUIResource(new Color(57,57,57))); **/
		
		/** Images **/ 
		ImageIcon newfile = new ImageIcon("/BakIDE/Logo/File.png");
		ImageIcon savefile = new ImageIcon("/BakIDE/Logo/Save.png");
		ImageIcon openfile = new ImageIcon("/BakIDE/Logo/Open.png");
		ImageIcon undochange = new ImageIcon("/BakIDE/Logo/undo.png");
		ImageIcon redochange = new ImageIcon("/BakIDE/Logo/redo.png");
		ImageIcon cutfile = new ImageIcon("/BakIDE/Logo/cut.png");
		ImageIcon copyfile = new ImageIcon("/BakIDE/Logo/copy.png");
		ImageIcon pastefile = new ImageIcon("/BakIDE/Logo/paste.png");
		ImageIcon buildfile = new ImageIcon("/BakIDE/Logo/build.png");
		ImageIcon runfile = new ImageIcon("/BakIDE/Logo/run.png");
		ImageIcon codefile = new ImageIcon("/BakIDE/Logo/codescr.png");
		ImageIcon workfile = new ImageIcon("/BakIDE/Logo/workspacesett.png");
		ImageIcon imgsettings = new ImageIcon("/BakIDE/Logo/settings.png");
		ImageIcon imgexit = new ImageIcon("/BakIDE/Logo/exit.png");
		ImageIcon imghelp = new ImageIcon("/BakIDE/Logo/help.png");
		ImageIcon imgdonate = new ImageIcon("/BakIDE/Logo/donate.png");
		ImageIcon jebrLogo = new ImageIcon("/BakIDE/Logo/jebrlogo.png");
		ImageIcon sep = new ImageIcon("/BakIDE/Logo/seperator.png");
	    ImageIcon leafIcon = new ImageIcon("/BakIDE/Logo/src_file_15_20.png");
	    ImageIcon closednodeicon = new ImageIcon("/BakIDE/Logo/dir_file_20_20.png");
	    ImageIcon openednodeicon = new ImageIcon("/BakIDE/Logo/dir_open_file_20_20.png");
		
		/**Menu**/
			/*Menu's definitions*/
		UMenuBar menuBar = new UMenuBar();
		menuBar.setBackground(clrdark);
		menuBar.setForeground(clrlight);
		frame.setJMenuBar(menuBar);	
		 //File
		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(clrdark);
		mnFile.setForeground(clrlight);
		menuBar.add(mnFile);
			
		final JMenuItem mnitmNew = new JMenuItem("New	                   ");

		mnitmNew.setForeground(clrlight);
		mnitmNew.setMnemonic('N');
		mnFile.add(mnitmNew);
		mnitmNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newProject();
				
			}
		});
		
		final JMenuItem mnitmOpen = new JMenuItem("Open					");
		mnitmOpen.setForeground(clrlight);
		mnitmOpen.setMnemonic('O');
		mnFile.add(mnitmOpen);
		
		mnitmOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openProject();
				
			}
		});
		
		JMenuItem mnitmSave = new JMenuItem("Save					");
		mnitmSave.setForeground(clrlight);
		mnitmSave.setMnemonic('S');
		mnFile.add(mnitmSave);
		
		mnFile.add(new JSeparator());
		
		final JMenuItem mnitmExit = new JMenuItem("Exit						");
		mnitmExit.setForeground(clrlight);
		mnitmExit.setMnemonic('Q');
		mnFile.add(mnitmExit);
		
		mnitmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				
			}
		});
		
			//Edit
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setForeground(clrlight);
		menuBar.add(mnEdit);

		final JMenuItem mnitmUndo = new JMenuItem(UndoManagerHelper.getUndoAction(manager));//"Undo                       "
		mnitmUndo.setForeground(clrlight);
		mnitmUndo.setMnemonic('Z');
		mnEdit.add(mnitmUndo);
		mnitmUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UndoManagerHelper.getUndoAction(manager);
				
				}
			});
		
		final JMenuItem mnitmRedo = new JMenuItem("Redo						");
		mnitmRedo.setForeground(clrlight);
		mnitmRedo.setMnemonic('Y');
		mnitmRedo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Not Working well
				UndoManagerHelper.getRedoAction(manager);
				
			}
		});
		
		mnEdit.add(mnitmRedo);
		mnEdit.add(new JSeparator());
		
		final JMenuItem mnitmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mnitmCut.setText("Cut						");
		mnitmCut.setForeground(clrlight);
		mnitmCut.setMnemonic('X');
		mnEdit.add(mnitmCut);
		
		final JMenuItem mnitmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mnitmCopy.setText("Copy						");
		mnitmCopy.setForeground(clrlight);
		mnitmCopy.setMnemonic('C');
		mnEdit.add(mnitmCopy);
		
		final JMenuItem mnitmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mnitmPaste.setText("Paste						");
		mnitmPaste.setForeground(clrlight);
		mnitmPaste.setMnemonic('V');
		mnEdit.add(mnitmPaste);
		
		
			//Project
		JMenu mnProject = new JMenu("Project");
		mnProject.setForeground(clrlight);
		menuBar.add(mnProject);
		
		JMenuItem mnitmBuild = new JMenuItem("Build                       ");
		mnitmBuild.setForeground(clrlight);
		mnitmBuild.setMnemonic('B');
		mnProject.add(mnitmBuild);
		
		JMenuItem mnitmRun = new JMenuItem("Run                       ");
		mnitmRun.setForeground(clrlight);
		mnitmRun.setMnemonic('R');
		mnProject.add(mnitmRun);
		
		JMenuItem mnitmCoder = new JMenuItem("Show Coding Console            ");
		mnitmCoder.setForeground(clrlight);
		mnitmCoder.setMnemonic('W');
		mnProject.add(mnitmCoder);
		mnitmCoder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/**Changed**/
				System.out.println(workPath.toPath());
				consoleWindow workConsole = new consoleWindow(nameOfFile);
				
			}
		});
		
		JMenuItem mnitmWSS = new JMenuItem("Workspace Settings               ");
		mnitmWSS.setForeground(clrlight);
		mnitmWSS.setMnemonic('I');
		mnProject.add(mnitmWSS);
		
			//Help
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setForeground(clrlight);
		menuBar.add(mnHelp);
			
		
		final JMenuItem mnitmHelp = new JMenuItem("Get Help                       ");
		mnitmHelp.setForeground(clrlight);
		mnitmHelp.setMnemonic('H');
		mnHelp.add(mnitmHelp);
		
		mnitmHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				URI help_link = null;
				try {
					help_link = new URI("https://sites.google.com/site/jebrproject/home");
				} catch (URISyntaxException e1) {
					
					e1.printStackTrace();
				}
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(help_link);
			        } catch (Exception c) {
			            c.printStackTrace();
			        }
			    }
			}
		});
		
		
		JMenuItem mnitmSett = new JMenuItem("Settings                       ");
		mnitmSett.setForeground(clrlight);
		mnitmSett.setMnemonic('P');
		mnHelp.add(mnitmSett);
		
		JMenuItem mnitmAbout = new JMenuItem("About                       ");
		mnitmSett.setForeground(clrlight);
		mnHelp.add(mnitmAbout);
		mnitmAbout.setForeground(clrlight);
		mnitmAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				about();
				
			}
		});
		
		mnHelp.add(new JSeparator());
		
		JMenuItem mnitmDonate = new JMenuItem("Donate            ");
		mnitmDonate.setForeground(clrlight);
		mnitmDonate.setMnemonic('D');
		mnHelp.add(mnitmDonate);
		
		
		/** TOOLBAR **/
			/*Toolbar's definitions*/
		JToolBar toolBar=new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(clrlight);
		toolBar.setBorderPainted(false); //removes border under the toolbar
		frame.getContentPane().add(toolBar,BorderLayout.NORTH);
			/*Buttons' definitions*/
		JButton tlbarfilebtn = new JButton(newfile);
		JButton tlbaropenbtn = new JButton(openfile);
		JButton tlbarsavebtn = new JButton(savefile);
		JButton tlbarundobtn = new JButton(undochange);
		JButton tlbarredobtn = new JButton(redochange);
		JButton tlbarcutbtn = new JButton(cutfile);
		JButton tlbarcopybtn = new JButton(copyfile);
		JButton tlbarpastebtn = new JButton(pastefile);
		JButton tlbarbuildbtn = new JButton(buildfile);
		JButton tlbarrunbtn = new JButton(runfile);
		JButton tlbarcodescrbtn = new JButton(codefile);
		JButton tlbarworkspbtn = new JButton(workfile);
		JButton tlbarsettbtn = new JButton(imgsettings);
		JButton tlbarexitbtn = new JButton(imgexit);
		JButton tlbarhelpbtn = new JButton(imghelp);
		JButton tlbarjebrlogo = new JButton(jebrLogo);
		JButton tlbarsepbtn = new JButton(sep);
		JButton tlbardonatebtn = new JButton(imgdonate);
		
			/*Buttons' sizes, colors and borders*/
		tlbarjebrlogo.setSize(20, 20);
		tlbarjebrlogo.setBackground(clrlight);
		tlbarjebrlogo.setBorder(BorderFactory.createEmptyBorder());
		tlbarjebrlogo.setEnabled(false);
		
		tlbarfilebtn.setSize(20, 20);
		tlbarfilebtn.setBackground(clrlight);
		tlbarfilebtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarfilebtn.setMnemonic('N');
		tlbarfilebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmNew.doClick();
				
			}
		});
		
		tlbaropenbtn.setSize(20, 20);
		tlbaropenbtn.setBackground(clrlight);
		tlbaropenbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbaropenbtn.setMnemonic('O');
		tlbaropenbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmOpen.doClick();
				
			}
		});

		tlbarsavebtn.setSize(20, 20);
		tlbarsavebtn.setBackground(clrlight);
		tlbarsavebtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarsavebtn.setMnemonic('S');
		
		tlbarundobtn.setSize(20, 20);
		tlbarundobtn.setBackground(clrlight);
		tlbarundobtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarundobtn.setMnemonic('U');
		tlbarundobtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmUndo.doClick();
				
			}
		});
		
		tlbarredobtn.setSize(20, 20);
		tlbarredobtn.setBackground(clrlight);
		tlbarredobtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarredobtn.setMnemonic('Y');
		tlbarredobtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmRedo.doClick();
				
			}
		});
		
		tlbarcutbtn.setSize(20, 20);
		tlbarcutbtn.setBackground(clrlight);
		tlbarcutbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarcutbtn.setMnemonic('x');
		tlbarcutbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmCut.doClick();
				
			}
		});
		
		tlbarcopybtn.setSize(20, 20);
		tlbarcopybtn.setBackground(clrlight);
		tlbarcopybtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarcopybtn.setMnemonic('c');
		tlbarcopybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmCopy.doClick();
				
			}
		});
		
		tlbarpastebtn.setSize(20, 20);
		tlbarpastebtn.setBackground(clrlight);
		tlbarpastebtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarpastebtn.setMnemonic('v');
		tlbarpastebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmPaste.doClick();
				
			}
		});
		
		
		
		tlbarbuildbtn.setSize(20, 20);
		tlbarbuildbtn.setBackground(clrlight);
		tlbarbuildbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarbuildbtn.setMnemonic('b');
		
		tlbarrunbtn.setSize(20, 20);
		tlbarrunbtn.setBackground(clrlight);
		tlbarrunbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarrunbtn.setMnemonic('R');
		
		tlbarcodescrbtn.setSize(20, 20);
		tlbarcodescrbtn.setBackground(clrlight);
		tlbarcodescrbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarcodescrbtn.setMnemonic('w');
		
		tlbarworkspbtn.setSize(20, 20);
		tlbarworkspbtn.setBackground(clrlight);
		tlbarworkspbtn.setBorder(BorderFactory.createEmptyBorder());

		tlbarsettbtn.setSize(20, 20);
		tlbarsettbtn.setBackground(clrlight);
		tlbarsettbtn.setBorder(BorderFactory.createEmptyBorder());
		
		tlbarexitbtn.setSize(20, 20);
		tlbarexitbtn.setBackground(clrlight);
		tlbarexitbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarexitbtn.setMnemonic('e');
		tlbarexitbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmExit.doClick();
				
			}
		});
		
		tlbarhelpbtn.setSize(20, 20);
		tlbarhelpbtn.setBackground(clrlight);
		tlbarhelpbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarhelpbtn.setMnemonic('h');
		tlbarhelpbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mnitmHelp.doClick();
			}
		});
		
		tlbardonatebtn.setSize(20, 20);
		tlbardonatebtn.setBackground(clrlight);
		tlbardonatebtn.setBorder(BorderFactory.createEmptyBorder());
		
		tlbarsepbtn.setSize(20, 20);
		tlbarsepbtn.setBackground(clrlight);
		tlbarsepbtn.setBorder(BorderFactory.createEmptyBorder());
		tlbarsepbtn.setEnabled(false);
			/*Adding to the toolbar*/
		toolBar.addSeparator(); //Space	
		toolBar.add(tlbarjebrlogo);
		toolBar.add(tlbarfilebtn);
		toolBar.add(tlbaropenbtn);
		toolBar.add(new USeperator(clrlight));//Seperator
		toolBar.add(tlbarsavebtn);
		toolBar.add(tlbarundobtn);
		toolBar.add(tlbarredobtn);
		toolBar.add(tlbarcutbtn);
		toolBar.add(tlbarcopybtn);
		toolBar.add(tlbarpastebtn);
		toolBar.add(new USeperator(clrlight));//Seperator
		toolBar.add(tlbarbuildbtn);
		toolBar.addSeparator();	
		toolBar.add(tlbarrunbtn);
		toolBar.add(tlbarcodescrbtn);
		toolBar.add(tlbarworkspbtn);
		toolBar.add(new USeperator(clrlight));//Seperator
		toolBar.add(tlbarsettbtn);
		toolBar.add(tlbarhelpbtn);
		toolBar.add(tlbarexitbtn);
		toolBar.addSeparator();
		toolBar.addSeparator();
		toolBar.add(tlbardonatebtn);
		
		
		/** Setting Up & Partitioning the frame **/
		
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new BorderLayout());
		
		JPanel west = new JPanel();
		JPanel east = new JPanel();
		
		newPanel.add(west, BorderLayout.WEST);
		newPanel.add(west, BorderLayout.CENTER);
		newPanel.add(east, BorderLayout.EAST);
		
		frame.getContentPane().add(newPanel, BorderLayout.WEST);
	
		west.setBackground(clrdark);
		east.setBackground(clrdark);
		
		/** Directory Explorer **/
			/* Set Up */
		File root;
		root = new File(workPath.getAbsolutePath());
		UFileTreeModel model = new UFileTreeModel(root);
		final JTree tree = new JTree();
	    tree.setBackground(clrdark);
	    tree.setModel(model);
	    
	    
	    	/* Customizing */
	    		
	    		//Setting up colors
	    final DefaultTreeCellRenderer renderer = 
	            (DefaultTreeCellRenderer)(tree.getCellRenderer()); 
	    
	    
	    if (tree.getCellRenderer() instanceof DefaultTreeCellRenderer)
	    {
	        
	        renderer.setBackgroundNonSelectionColor(clrdark);
	        renderer.setBackgroundSelectionColor(clrlight);
	        renderer.setTextNonSelectionColor(clrlight);
	        renderer.setTextSelectionColor(clrdark	);
	    }
	    else
	    {
	        System.err.println("Sorry, no special colors today.");
	    }
	    		//Removing lines
	    tree.putClientProperty("JTree.lineStyle", "None");
	    
	    
	    		//Setting up Colors
	    if (leafIcon != null) {
	         renderer.setLeafIcon(leafIcon);
	         renderer.setClosedIcon(closednodeicon);
	         renderer.setOpenIcon(openednodeicon);
	        tree.setCellRenderer(renderer);
	    }
	    
	    // Adding the scroller
	    JScrollPane scrollpane = new JScrollPane(tree);
		
	    		//Forcing the size and removing borders
		Dimension x = new Dimension(250, 600);
		scrollpane.setPreferredSize(x);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());
		
				//Adding it to the frame
		west.add(scrollpane);		
	  
		
		      /** To the west we have to set a Container to contain to elements **/
		
		JPanel east_con = new JPanel();
		east_con.setLayout(new BorderLayout());
		east.add(east_con);
		
		JPanel east_up = new JPanel();
		JPanel east_down = new JPanel();
		east_up.setBackground(clrdark);
		east_down.setBackground(clrdark);
		
		
		east_con.add(east_up, BorderLayout.CENTER);
		east_con.add(east_down, BorderLayout.SOUTH);
		 
		
		doc = (StyledDocument) new DefaultStyledDocument();
        textPane = new JTextPane(doc);
        textPane.setBackground(clrdark);
        textPane.setForeground(clrlight);
        textPane.setText("JIDE 1.04 Alpha Version - using JINT 0b00 second revision");
        textPane.setText(textPane.getText() + "\r\n"  + "-------------------------------------------------------" );
        textPane.setText(textPane.getText() + "\r\n"  + ">? Hey, Jim. Create/Open a project first " + "\r\n");
        
        //colorPane(); // style the pane

		JScrollPane editorScrollPane = new JScrollPane(textPane);
		editorScrollPane.setBorder(BorderFactory.createLineBorder(clrlight, 2));
		textPane.setEditable(false);
		Dimension textPaneDimension = new Dimension(550, 475);
		textPane.setPreferredSize(textPaneDimension);
		textPane.setFont(new Font("Courier", 0, 16));
		east_up.add(editorScrollPane);
		
		
		interpreterLine = new RSyntaxTextArea(4,47);
		interpreterLine.getDocument().addUndoableEditListener(manager);
		AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory)TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/jebr", "org.jebr.jide.JebrToken");
        interpreterLine.setSyntaxEditingStyle("text/jebr");
        Dimension interpreterLineSize = new Dimension(10, 10);
		interpreterLine.setPreferredSize(interpreterLineSize);
		RTextScrollPane interPane = new RTextScrollPane(interpreterLine);
		//east_down.setPreferredSize(interpreterLineSize);
		
		interpreterLine.addKeyListener(this);
        
        //Theming the pane
        
	        interpreterLine.setBackground(clrdark);
	        interPane.setBackground(clrdark);
	        interpreterLine.setFont(new Font("Courier",Font.BOLD, 16));
	        interpreterLine.setForeground(clrlight);
	        Color par = Color.decode("#C0DEED");
	        Color reserved = Color.decode("#F74A6A");
	        Color data_type = Color.decode("#F5BB51");
	        Color int_color = Color.decode("#c84e4e");
	        Color string_color = Color.decode("#cf6015");
	        Color comm_color = Color.decode("#9a7d7e");
	        Color Highlight = Color.decode("#0B2C61");
	        interpreterLine.setTabLineColor(par);
	        interpreterLine.setCurrentLineHighlightColor(Highlight);
	        //interpreterLine.setAnimateBracketMatching(true);
	        SyntaxScheme scheme = interpreterLine.getSyntaxScheme();
	        scheme.getStyle(Token.RESERVED_WORD).foreground = reserved;
	        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT) .foreground = int_color;
	        scheme.getStyle(Token.SEPARATOR) .foreground = par; 
	        scheme.getStyle(Token.DATA_TYPE).foreground = data_type;
	        scheme.getStyle(Token.FUNCTION) .foreground = Color.GRAY;
	        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = string_color;
	        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).underline = true;
	        scheme.getStyle(Token.COMMENT_EOL).font = new Font("Georgia",
	        Font.ITALIC, 12);
	        scheme.getStyle(Token.COMMENT_EOL).foreground= comm_color;
	        
	        scheme.getStyle(Token.COMMENT_DOCUMENTATION).foreground= comm_color;
	        scheme.getStyle(Token.COMMENT_MULTILINE).foreground= comm_color;
	        
        
        //end of theming
	        east_down.add(interPane);
	        
	        frame.setVisible(true);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		 int key = e.getKeyCode();
	     if (key == KeyEvent.VK_ENTER) {
	        /**Changed**/
	    	 
	    	 PrintStream out = null;
	           try {
	        	   /*
	               File file = new File(nameOfFile+"/temp.c");
	               File file2 = new File(nameOfFile+"/symbol.txt");*/
	        	   
	               //String []lines1 =
	               BufferedReader in = new BufferedReader(new FileReader(nameOfFile+"/stdlib.jlb"));
	               String str;

	               List<String> list = new ArrayList<String>();
	               while((str = in.readLine()) != null){
	                   list.add(str);
	               }

	               String[] lines1 = list.toArray(new String[0]);

	               
	               
	               String []lines2 = interpreterLine.getText().split("\n");
	               int countlines = 0;
	               PrintWriter writer = new PrintWriter(new File(nameOfFile+"/stdlib.jlb"));
	               writer.print("");
	               writer.close();
	               interpreterLine.setText("");
	               FileWriter fw = new FileWriter(nameOfFile+"/stdlib.jlb",true); //the true will append the new data
	               //appends the string to the file
	               int countinner = 0;       
	               while (countlines<lines1.length+lines2.length)
	               {
	            	   if (countlines<lines1.length){
	                    fw.write(lines1[countlines]+"\n");
	            	   }
	            	   else
	            	   {
	            		   fw.write(lines2[countinner]+"\n");
	            		   countinner++;
	            		   
	            	   }
	                    countlines++;
	                    
	               }
	               fw.close();   
	               System.setOut(System.out);
	               
	               /**
	                * PARSING USING JINT
	                */
	               /**Changed**/
	               parser mx = new parser(new File(nameOfFile+"/stdlib.jlb"));
	               
	               mx.pars();
	               
	               Process p = Runtime.getRuntime().exec("xterm -hold -e spim read /BakIDE/test.s");
	            
	               
	               
	               Scanner z = new Scanner(new File("jout.txt"));/*
	               StyledDocument doc = textEditor2.getStyledDocument();
	               doc.remove(0, doc.getLength());
	               SimpleAttributeSet keyWord = new SimpleAttributeSet();
	               
	                   StyleConstants.setForeground(keyWord, Color.RED);
	                   StyleConstants.setBold(keyWord, true);
	                   int count = 0;
	                   textEditor2.setContentType("text/html");
	                   
	                   /**
	                    * Getting from the results file
	                    */
	               textPane.setText(textPane.getText() + "\n" + ">?" );
	               while(z.hasNextLine())
	               {
	                  
	                       textPane.setText(textPane.getText() + z.nextLine() + "\n" );
	                  
	                   
	               }
	               
	             
	           } catch (FileNotFoundException ex) {
	               //Logger.getLogger(JavaHighlighter.class.getName()).log(Level.SEVERE, null, ex);
	               System.setOut(System.out);
	               out.println("No file found");
	           } catch (IOException ex) {
	              // Logger.getLogger(JavaHighlighter.class.getName()).log(Level.SEVERE, null, ex);
	           } catch (NoSuchElementException ex){
	               
	           }
	           
	          
	    	 
	        }
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
