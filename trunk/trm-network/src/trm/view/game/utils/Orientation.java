/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.utils;

/**
 *
 * @author Rafael
 */
public enum Orientation {
    NORTH, EAST, WEST, SOUTH;

    public static Orientation clockwise(Orientation o) {
        switch(o) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                return NORTH;
        }
    }

    public static Orientation counterClockwise(Orientation o) {
        switch(o) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                return NORTH;
        }
    }
}
