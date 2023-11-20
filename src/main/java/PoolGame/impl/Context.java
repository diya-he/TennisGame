package PoolGame.impl;

import PoolGame.WinStrategy;

public class Context {

    public WinStrategy strategy;

    public Context(WinStrategy strategy)
    {
        this.strategy = strategy;
    }

    public int executeStrategy(){
        return strategy.doOperation();
    }
}
