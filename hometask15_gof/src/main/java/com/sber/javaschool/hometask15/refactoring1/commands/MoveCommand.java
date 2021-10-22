package com.sber.javaschool.hometask15.refactoring1.commands;

import com.sber.javaschool.hometask15.refactoring1.exceptions.TractorInDitchException;
import com.sber.javaschool.hometask15.refactoring1.tractor.Tractor;


public class MoveCommand implements Command {
    private final Tractor tractor;

    public MoveCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
        tractor.getOrientation().move(tractor.getPosition());
        if (tractor.getPosition().getX() > tractor.getField().getX() || tractor.getPosition().getY() > tractor.getField().getY()) {
            throw new TractorInDitchException();
        }
    }
}
