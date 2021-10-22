package com.sber.javaschool.hometask15.refactoring1.tractor;

import com.sber.javaschool.hometask15.refactoring1.Orientation;
import com.sber.javaschool.hometask15.refactoring1.Position;
import com.sber.javaschool.hometask15.refactoring1.commands.Command;

public class TractorImpl implements Tractor {

    private final Position field;
    private final Position position;
    private Orientation orientation;

    public TractorImpl() {
        field = new Position(5, 5);
        orientation = Orientation.NORTH;
        position = new Position(0, 0);
    }

    public TractorImpl(Position field, Position position, Orientation orientation) {
        this.field = field;
        this.position = position;
        this.orientation = orientation;
    }

    @Override
    public void move(Command command) {
        command.execute();
    }

    @Override
    public Position getField() {
        return field;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
