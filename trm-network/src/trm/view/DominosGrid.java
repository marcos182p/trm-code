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
        rightOrientation = nextOrientation(nextRightPosition.y, nextRightPosition.x, rightOrientation);
        jumpRight();
    }

    public Orientation nextOrientation(int row, int col, Orientation currentOrientation) {
        Orientation orientation = currentOrientation;
        if (paintPosition(row, col, orientation)) {
            return orientation;
        } else {
            return nextOrientation(row, col, Orientation.clockwise(currentOrientation));
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

    public boolean paintPosition(int row, int col, Orientation orientation) {
        System.out.println("called paintPosition");
        grid[row][col]++;
        switch (orientation) {
            case NORTH:
                if (row - 1 < 0) {
                    return false;
                }
                nextRightPosition.y--;
                nextRightPosition.x += rightJump;
                grid[row - 1][col]++;
                break;
            case EAST:
                if (col + 1 >= cols) {
                    return false;
                }
                nextRightPosition.x ++;
                nextRightPosition.y += rightJump;
                grid[row][col + 1]++;
                break;
            case SOUTH:
                if (row + 1 >= rows) {
                    return false;
                }
                nextRightPosition.y ++;
                nextRightPosition.x += rightJump;
                grid[row + 1][col]++;
                break;
            case WEST:
                if (col - 1 < 0) {
                    return false;
                }
                nextRightPosition.x--;
                nextRightPosition.y += rightJump;
                grid[row][col - 1]++;
                break;
        }
        return true;
    }
}
