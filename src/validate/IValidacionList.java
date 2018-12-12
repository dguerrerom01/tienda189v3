package validate;

import error.Error;

import java.util.ArrayList;

public interface IValidacionList {
    public abstract ArrayList<Error> exec();
}
