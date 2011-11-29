/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.problem.impl;

import trm.model.Problem;
import component.problem.spec.prov.IProblem;

/**
 *
 * @author ttmo
 */
public class FacadeIProblem implements IProblem {

    private ProblemImpl problemImpl;

    protected FacadeIProblem() {
        problemImpl = new ProblemImpl();
    }

    public Problem getProblem() {
        return problemImpl.getProblem();
    }

    public boolean checkAnswer(Problem p, float angle, float intensity) {
        return problemImpl.checkAnswer(p, angle, intensity);
    }
}
