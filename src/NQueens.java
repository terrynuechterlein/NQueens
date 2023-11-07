// Class: NQueens


//
// Author: Terry Nuechterlein
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.BoundedGrid;




import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.display.GridDisplay;
import edu.kzoo.grid.GridObject;
/**
 *  Environment-Based Applications:<br>
 *
 *    The NQueens class implements the N Queens problem.
 *
 *  @author Terry Nuechterlein with help from HARSHA
 *  @version 27 May 2020
 *  
 *  This is the NQueens class. This class constructss a board. It then adds and removes Queens to that board
 *  in a manner to solve the problem. The number of queens will be equal to the number of rows in the grid
 *  which is why we return the number of rows.
 **/


public class NQueens
{
    // Instance Variables: Encapsulated data for EACH NQueens problem
    private Grid board;
    private GridDisplay display;

  // constructor

    /** Constructs an object that solves the N Queens Problem.
     *    @param n the number of queens to be placed on an
     *              <code>n</code> x <code>n</code> board
     *    @param d an object that knows how to display an 
     *              <code>n</code> x <code>n</code> board and
     *              the queens on it
     **/
    public NQueens(int n, GridDisplay d)
    {
        board = new BoundedGrid(n, n);
        display = d;
        display.setGrid(board);
        display.showGrid();
    }

  // methods

    /** Returns the number of queens to be placed on the board. **/
    public int numQueens()
    {
        return board.numRows();   // replace this with something more useful
    }

    
   //we returne the amount of rows because that's how many queens there will be 
    
    
    
    
    /** Solves (or attempts to solve) the N Queens Problem. **/
    public boolean solve()
    {
    	
    	//int col = board.numCols();
    	//placeQueen(col);
      //  return true;   // replace this with something more useful
    	return placeQueen(0);
    }

    /** Attempts to place the <code>q</code>th queen on the board.
     *  (Precondition: <code>0 <= q < numQueens()</code>)
     *    @param q index of next queen to place
     **/
    private boolean placeQueen(int q)
    {
        // Queen q is placed in row q.  The only question is
        // which column she will be in.  Try them in turn.
        // Whenever we find a column that could work, put her
        // there and see if we can place the rest of the queens.
    	int row = board.numRows();
    	int col = board.numCols();
    	
    	if(q >= numQueens())
    	{
    		return true;
    	}
    	
    	for(int i = 0; i< col; i++)
    	{
    		Location loc = new Location(q, i);
    		
    		if (locationIsOK(loc))
    		{
    			addQueen(loc);
    			display.showGrid();
    			
               if(placeQueen(q+1))
               {
            	   return true;
               }
               else
               {
            	   removeQueen(loc);
               }
            	 
    		}

    	}
    	return false;
   
    }

    /** Determines whether a queen can be placed at the specified
     *  location.
     *    @param loc  the location to test
     **/
    private boolean locationIsOK(Location loc)
    {
        // Verify that another queen can't attack this location.
        // (Only queens in previous rows have been placed.)
        GridObject Arr[]= board.allObjects();
        for(GridObject x : Arr)
        {
        if(loc.row() == x.location().row() || loc.col() == x.location().col() || Math.abs((x.location().row()-loc.row())) == Math.abs((x.location().col()-loc.col()) ))
            return false;
        }
        return true;
    }

    /** Adds a queen to the specified location.
     *    @param loc  the location where the queen should be placed
     **/
    private void addQueen(Location loc)
    {
        new Queen(board, loc);      // queens add themselves to the board
    }

    /** Removes a queen from the specified location.
     *    @param loc  the location where the queen should be removed
     **/
    private void removeQueen(Location loc)
    {
       board.remove(loc);
       display.showGrid();
    }

}
