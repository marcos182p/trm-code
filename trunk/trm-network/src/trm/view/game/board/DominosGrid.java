package trm.view.game.board;

import trm.view.game.utils.GameSide;
import trm.view.game.utils.Orientation;
import java.awt.Point;

/**
 * @author TRM
 * @version 0.99
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
    private boolean first;

    private int rows;
    private int cols;

    public DominosGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
       
        reset();
    }

    public void reset() {
        this.leftPosition = new Point(rows/2, cols/2);
        this.rightPosition = leftPosition.getLocation();
        this.nextLeftPosition = leftPosition;
        this.leftOrientation = Orientation.EAST;
        this.rightOrientation = Orientation.EAST;
        this.rightJump = 1;
        this.leftJump = 1;
        this.nextLeftPosition = leftPosition.getLocation();
        this.nextRightPosition = rightPosition.getLocation();
        first = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 0;
            }
        }
    }
    private void jumpRight() {
        rightJump = -1*rightJump;
    }
    private void jumpLeft() {
        leftJump = -1*leftJump;
    }
    public void putRight() {
        if(first) {
            this.nextLeftPosition.y++;
            jumpLeft();
            first = false;
        }
        this.rightPosition = this.nextRightPosition.getLocation();
        
        rightOrientation = paintNextRight(rightPosition.y, rightPosition.x, rightOrientation);
        
        jumpRight();
    }
    public void putLeft() {
         if(first) {
            this.nextRightPosition.y++;
            jumpRight();
            first = false;
        }
        this.leftPosition = this.nextLeftPosition.getLocation();
        

        leftOrientation = paintNextLeft(leftPosition.y, leftPosition.x, leftOrientation);

        jumpLeft();
    }

    public Orientation paintNextRight(int row, int col, Orientation orientation) {
       
        if (markPositionRight(row, col, orientation)) {
            return orientation;
        } else {
            return paintNextRight(row, col, Orientation.clockwise(orientation));
        }
    }
    public Orientation paintNextLeft(int row, int col, Orientation orientation) {

        if (markPositionLeft(row, col, orientation)) {
            return orientation;
        } else {
            return paintNextLeft(row, col, Orientation.clockwise(orientation));
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

    public boolean markPositionRight(int row, int col, Orientation orientation) {
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
             return markPositionRight(row, col, orientation);
             
        }
        return false;
    }

    public boolean markPositionLeft(int row, int col, Orientation orientation) {
        grid[row][col]++;
        Point secondPosition = new Point(col, row);
        Point neighbor = new Point(col, row);
        
        switch (orientation) {
            case NORTH:
                if (row + 1 >= rows) {
                    return false;
                }
                secondPosition.y++;
                neighbor.y = secondPosition.y;
                neighbor.x += leftJump;
                break;
            case EAST:
                if (col - 1 < 0) {
                    return false;
                }
                secondPosition.x --;
                neighbor.x = secondPosition.x;
                neighbor.y += leftJump;
                break;
            case SOUTH:
                if (row - 1 < 0) {
                    return false;
                }
                secondPosition.y --;
                neighbor.y = secondPosition.y;
                neighbor.x += leftJump;
                break;
            case WEST:
                if (col + 1 >= cols) {
                    return false;
                }
                secondPosition.x++;
                neighbor.x = secondPosition.x;
                neighbor.y += leftJump;
                break;
        }

        if(safe(secondPosition)) {
            grid[secondPosition.y][secondPosition.x] ++;
            if(verify(neighbor)) {
                this.nextLeftPosition = neighbor.getLocation();
                return true;
            }
             grid[secondPosition.y][secondPosition.x] --;
             jumpLeft();
             return markPositionLeft(row, col, orientation);

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
