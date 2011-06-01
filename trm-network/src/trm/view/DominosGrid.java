/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view;

import java.awt.Point;

/**
 *
 * @author Rafael
 */
public class DominosGrid {

    private int grid[][];
    private Point leftPosition;
    private Point rightPosition;
    private Orientation leftOrientation;
    private Orientation rightOrientation;
    private int rightJump;
    private int leftJump;
    private Point nextRightPosition;
    private Point nextLeftPosition;

    private int rows;
    private int cols;

    public DominosGrid(int rows, int cols) {
        this.grid = new int[rows][cols];
        this.leftPosition = new Point(cols / 2, rows / 2);
        this.rightPosition = leftPosition.getLocation();
        this.rows = rows;
        this.cols = cols;
        this.leftOrientation = Orientation.EAST;
        this.rightOrientation = Orientation.EAST;
        this.rightJump = 1;
        this.leftJump = 1;
        this.nextRightPosition = rightPosition;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 0;
            }
        }
    }
    private void jumpRight() {
        rightJump = -1*rightJump;
    }
    public void putRight() {
        this.rightPosition = this.nextRightPosition.getLocation();
        rightOrientation = paintNext(nextRightPosition.y, nextRightPosition.x, rightOrientation);
        
        jumpRight();
    }

    public Orientation paintNext(int row, int col, Orientation currentOrientation) {
        Orientation orientation = currentOrientation;
       
        if (markPosition(row, col, orientation)) {
            return orientation;
        } else {
            return paintNext(row, col, Orientation.clockwise(currentOrientation));
        }
    }

    public Point currentPoint(GameSide side) {
        if(side == GameSide.RIGHT) {
            return rightPosition;
        }else {
            return leftPosition;
        }
    }
    public Orientation currentOrientation(GameSide side) {
        if(side == GameSide.RIGHT) {
            return rightOrientation;
        }else {
            return leftOrientation;
        }
    }
    public int getValueAt(int row, int col) {
        return grid[row][col];
    }

    public boolean markPosition(int row, int col, Orientation orientation) {
        grid[row][col]++;
        Point secondPosition = new Point(col, row);
        Point neighbor = new Point(col, row);
        switch (orientation) {
            case NORTH:
                if (row - 1 < 0) {
                    return false;
                }
                secondPosition.y--;
                neighbor.y = secondPosition.y;
                neighbor.x += rightJump;
                break;
            case EAST:
                if (col + 1 >= cols) {
                    return false;
                }
                secondPosition.x ++;
                neighbor.x = secondPosition.x;
                neighbor.y += rightJump;
                break;
            case SOUTH:
                if (row + 1 >= rows) {
                    return false;
                }
                secondPosition.y ++;
                neighbor.y = secondPosition.y;
                neighbor.x += rightJump;
                break;
            case WEST:
                if (col - 1 < 0) {
                    return false;
                }
                secondPosition.x--;
                neighbor.x = secondPosition.x;
                neighbor.y += rightJump;
                break;
        }

        if(safe(secondPosition)) {
            grid[secondPosition.y][secondPosition.x] ++;
            if(verify(neighbor)) {
                this.nextRightPosition = neighbor.getLocation();
                return true;
            }
             grid[secondPosition.y][secondPosition.x] --;
             jumpRight();
             markPosition(row, col, orientation);
             
        }
        return false;
    }
    private boolean safe(Point p) {
        int row = p.y;
        int col = p.x;
        return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 0;
    }
    private boolean verify(Point p) {
        return (safe(p) &&
               ((p.y-1 >= 0 && grid[p.y-1][p.x] == 0) ||
               (p.y+1 < rows && grid[p.y+1][p.x] == 0) ||
               (p.x-1 >= 0 && grid[p.y][p.x-1] == 0) ||
               (p.x+1 < cols && grid[p.y][p.x+1] == 0)));
    }
}
