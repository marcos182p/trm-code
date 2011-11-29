/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.problem.impl;

import trm.model.Problem;

/**
 *
 * @author ttmo
 */
public class ProblemImpl {

    protected ProblemImpl() {
    }

    protected Problem getProblem() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected boolean checkAnswer(Problem p, float angle, float intensity) {

        double Xf = p.getTarget().getX();
        double Yf = p.getTarget().getY();

        double X0 = p.getSource().getX();
        double Y0 = p.getSource().getY();


        double t = (Xf - X0) / (intensity * Math.cos(angle));

        double newYf = Y0 + (intensity * Math.sin(angle) * t) + ((p.getGravity() * Math.pow(t, 2)) / 2);

        if (Math.abs(Yf - newYf) < 0.0001) {
            return true;
        } else {
            return false;
        }



    }
}
