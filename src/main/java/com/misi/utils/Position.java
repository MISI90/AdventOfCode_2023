package com.misi.utils;

public class Position {
    public int row;
    public int column;

    public Position() {
        this.row = 0;
        this.column = 0;
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31000 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Position{r=%d, c=%d}", row, column);
    }
}
