public class Artist {
    Results[] results;
    public int getID() {
        return results[0].getID();
    }
    public String getName() {
        return results[0].getTitle();
    }
}
