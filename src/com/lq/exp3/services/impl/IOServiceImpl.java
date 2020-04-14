package com.lq.exp3.services.impl;

import com.lq.exp3.entity.Inventory;
import com.lq.exp3.services.IOService;

public class IOServiceImpl implements IOService {
    @Override
    public int outCargo(String itemId, String customId) {
        return 0;
    }

    @Override
    public int inCargo(String itemId) {
        return 0;
    }

    @Override
    public int newCargo(Inventory inventory) {
        return 0;
    }

    @Override
    public int delCargo(String itemId) {
        return 0;
    }
}
