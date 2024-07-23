WorldPoint bank = new WorldPoint(2728, 3493, 0);
WorldPoint wc1 = new WorldPoint(2726, 3476, 0);
WorldPoint wc2 = new WorldPoint(2719, 3480, 0);

if (!v.getInventory().inventoryFull() && v.getLocalPlayer().hasAnimation(-1)) {
    if (v.getWalking().nearPosition(wc1, 8)) {
        if (client.getBoostedSkillLevel(Skill.WOODCUTTING) <= 14) {
            v.getWoodcutting().chop(1276,1278);
        } else if (client.getBoostedSkillLevel(Skill.WOODCUTTING) >= 15) {
            v.getWoodcutting().chop(10820);
        }
    } else if (!v.getWalking().nearPosition(wc1, 8)) {
        if (client.getBoostedSkillLevel(Skill.WOODCUTTING) <= 14) {
            v.getWalking().walkR(wc1, 3);
        } else if (client.getBoostedSkillLevel(Skill.WOODCUTTING) >= 14) {
            v.getWalking().walkR(wc2, 1);
        }
    }
} else if (v.getInventory().inventoryFull() && v.getLocalPlayer().hasAnimation(-1)) {
    if (!v.getWalking().nearPosition(bank, 1)) {
        v.getWalking().walkR(bank, 1);
    } else if (v.getWalking().nearPosition(bank, 1)) {
        v.getBank().open();
        v.getCallbacks().afterTicks(5, () -> {
            v.getBank().deposit(1521, 28);
            v.getBank().deposit(1511, 28);
            v.getBank().close();
            v.getCallbacks().afterTicks(0, () -> {
                v.getWalking().turnRunningOn();
                if (client.getBoostedSkillLevel(Skill.WOODCUTTING) <= 14) {
                    v.getWalking().walkR(wc1, 1);
                } else if (client.getBoostedSkillLevel(Skill.WOODCUTTING) >= 14) {
                    v.getWalking().walkR(wc2, 1);
                }
            });
        });
    }
}