package tn.esprit.infini2.dto;

import tn.esprit.infini2.entities.ScoreType;

public class GeneralScoreStat {

    private int n;
    private String scoreTypeName;
    private String percent;


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getScoreTypeName() {
        return scoreTypeName;
    }

    public void setScoreTypeName(String scoreTypeName) {
        this.scoreTypeName = scoreTypeName;
    }
}
