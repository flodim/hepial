package symbole_table;

import java.util.ArrayList;
import java.util.List;

public class ErrorManager {
    private static ErrorManager instance;
    private List<String> errors;

    private ErrorManager() {
        errors = new ArrayList<>();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public static ErrorManager getInstance()
    {
        if (instance == null)
        {
            instance = new ErrorManager();
        }
        return instance;
    }
}
