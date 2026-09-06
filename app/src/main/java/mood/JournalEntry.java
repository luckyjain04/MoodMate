package mood;

public class JournalEntry {
    String entry, date;

    public JournalEntry(String entry, String date) {
        this.entry = entry;
        this.date = date;
    }

    public String getEntry() { return entry; }
    public String getDate() { return date; }
}
