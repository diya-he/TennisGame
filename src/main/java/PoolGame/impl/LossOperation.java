package PoolGame.impl;

import PoolGame.WinStrategy;

public class LossOperation implements WinStrategy {
    @Override
    public int doOperation()
    {
//        System.out.println("你已失败");
        return 0;
    }


}
