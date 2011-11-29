package component.problem.spec.prov;

import trm.model.Problem;

public interface IProblem {

    public Problem getProblem();

    public boolean checkAnswer(Problem p, float angle, float intensity);
}
