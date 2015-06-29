/*
 * Code Name: UndoManagerHelper.java
 * Created By: 'Umar A.Abu Baker, Fahed N.Shehab <http://www.geekybedouin.com>, <>
 * Copyright (c) 2014 'Umar A.Abu Baker and Fahed N.Shehab <umr.baker@gmail.com>, <>
 * 
 * 
 * Implemented from the article: http://www.java2s.com/Code/Java/Swing-JFC/UndoExample7.htm
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

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class UndoManagerHelper {

	  public static Action getUndoAction(UndoManager manager, String label) {
	    return new UndoAction(manager, label);
	  }

	  public static Action getUndoAction(UndoManager manager) {
	    return new UndoAction(manager, "Undo");
	  }

	  public static Action getRedoAction(UndoManager manager, String label) {
	    return new RedoAction(manager, label);
	  }

	  public static Action getRedoAction(UndoManager manager) {
	    return new RedoAction(manager, "Redo");
	  }

	  private abstract static class UndoRedoAction extends AbstractAction {
	    UndoManager undoManager = new UndoManager();

	    String errorMessage = "Cannot undo";

	    String errorTitle = "Undo Problem";

	    protected UndoRedoAction(UndoManager manager, String name) {
	      super(name);
	      undoManager = manager;
	    }

	    public void setErrorMessage(String newValue) {
	      errorMessage = newValue;
	    }

	    public void setErrorTitle(String newValue) {
	      errorTitle = newValue;
	    }

	    protected void showMessage(Object source) {
	      if (source instanceof Component) {
	        JOptionPane.showMessageDialog((Component) source, errorMessage,
	            errorTitle, JOptionPane.WARNING_MESSAGE);
	      } else {
	        System.err.println(errorMessage);
	      }
	    }
	  }

	  public static class UndoAction extends UndoRedoAction {
	    public UndoAction(UndoManager manager, String name) {
	      super(manager, name);
	      setErrorMessage("Cannot undo");
	      setErrorTitle("Undo Problem");
	    }

	    public void actionPerformed(ActionEvent actionEvent) {
	      try {
	        undoManager.undo();
	      } catch (CannotUndoException cannotUndoException) {
	        showMessage(actionEvent.getSource());
	      }
	    }
	  }

	  public static class RedoAction extends UndoRedoAction {
	    String errorMessage = "Cannot redo";

	    String errorTitle = "Redo Problem";

	    public RedoAction(UndoManager manager, String name) {
	      super(manager, name);
	      setErrorMessage("Cannot redo");
	      setErrorTitle("Redo Problem");
	    }

	    public void actionPerformed(ActionEvent actionEvent) {
	      try {
	        undoManager.redo();
	      } catch (CannotRedoException cannotRedoException) {
	        showMessage(actionEvent.getSource());
	      }
	    }
	  }

	}