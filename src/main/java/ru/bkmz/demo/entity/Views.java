package ru.bkmz.demo.entity;

public final class Views {
    public interface Id {
    }

    public interface IdName extends Id {
    }

    public interface FullMessage extends Id, IdName {
    }
}
