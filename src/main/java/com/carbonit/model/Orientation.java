package com.carbonit.model;

public enum Orientation {
    N, S, E, W;

    public Orientation turnLeft() {
        switch (this) {
            case N: return W;
            case W: return S;
            case S: return E;
            case E: return N;
        }
        return this;
    }

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