package com.carbonit.model;
/**
 * Enumération représentant l'orientation d'un aventurier sur la carte.
 * N : Nord, S : Sud, E : Est, W : Ouest
 * Fournit des méthodes pour tourner à gauche ou à droite.
 */
public enum Orientation {
    N, S, E, W;
    /**
     * Retourne la nouvelle orientation après un tour à gauche.
     * Exemple : N -> W, W -> S, S -> E, E -> N
     */
    public Orientation turnLeft() {
        switch (this) {
            case N: return W;
            case W: return S;
            case S: return E;
            case E: return N;
        }
        return this;
    }
    /**
     * Retourne la nouvelle orientation après un tour à droite.
     * Exemple : N -> E, E -> S, S -> W, W -> N
     */
    public Orientation turnRight() {
        switch (this) {
            case N: return E;
            case E: return S;
            case S: return W;
            case W: return N;
        }
        return this;
    }
}