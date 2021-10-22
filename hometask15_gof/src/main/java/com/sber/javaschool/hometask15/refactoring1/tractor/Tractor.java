package com.sber.javaschool.hometask15.refactoring1.tractor;

import com.sber.javaschool.hometask15.refactoring1.Orientation;
import com.sber.javaschool.hometask15.refactoring1.Position;
import com.sber.javaschool.hometask15.refactoring1.commands.Command;

public interface Tractor {
    void move(Command command);

    Position getField();

    Position getPosition();

    Orientation getOrientation();

    void setOrientation(Orientation orientation);
}
