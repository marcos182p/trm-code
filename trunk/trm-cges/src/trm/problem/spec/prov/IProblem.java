/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.problem.spec.prov;

import trm.model.Problem;

/**
 *
 * @author ttmo
 */
public interface IProblem {

    public Problem getProblem();

    public boolean checkAnswer(Problem p, float angle, float intensity);
}
