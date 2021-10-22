package com.sber.javaschool.hometask15.refactoring1.commands;

import com.sber.javaschool.hometask15.refactoring1.tractor.Tractor;

public class TurnCommand implements Command {
    private final Tractor tractor;

    public TurnCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
        tractor.setOrientation(tractor.getOrientation().turn());
    }
}
