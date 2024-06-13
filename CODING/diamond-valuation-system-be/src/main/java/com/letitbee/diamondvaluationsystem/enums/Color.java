package com.letitbee.diamondvaluationsystem.enums;

public enum Color {
    D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    public String getDisplayName() {
        return name();
    }
    @Override
    public String toString(){
        return name();
    }
}
