package symbole_table;

import java.util.HashMap;

public class SymbTable {

    private static SymbTable instance;

    private int currentBlock;

    private HashMap<Entry, HashMap<Integer, Symb>> symbols;

    private SymbTable() {
        symbols = new HashMap<>();
        currentBlock = 0;
    }

    public static SymbTable getInstance()
    {
        if (instance == null)
        {
            instance = new SymbTable();
        }
        return instance;
    }

    public void add(Entry entry, Symb symbol) {
        HashMap<Integer, Symb> entryMap = symbols.computeIfAbsent(entry, k -> new HashMap<>());

        if (entryMap.get(currentBlock) != null)
        {
            ErrorManager.getInstance().addError(String.format(
                    "The symbol %s already exists for the entry %s, block %d",
                    symbol, entry, currentBlock));
        }

        entryMap.put(currentBlock, symbol);
    }

    public Symb identifier(Entry entry) {
        HashMap<Integer, Symb> entryMap = symbols.get(entry);
        if (entryMap != null)
        {
            for (int i = currentBlock; i >= 0; i--) {
                Symb symb = entryMap.get(i);

                if (symb != null)
                {
                    return symb;
                }
            }
        }
        return null;
    }

    public void enterBlock()
    {
        currentBlock++;
    }

    public void exitBlock()
    {
        currentBlock--;
    }
}
