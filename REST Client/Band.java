import java.util.ArrayList;
public class Band {
    String name;
    int id;
    Members[] members;
    Groups[] groups;
    public ArrayList<Pair<Integer, String>> getArtistPair() {
        ArrayList<Pair<Integer, String>> al = new ArrayList<>();
        for (Members member : members) {
            al.add(Pair.makePair(member.getId(), member.getName()));
        }
        return al;
    }
    public ArrayList<String> getGroups() {
        ArrayList<String> vector = new ArrayList<>();
        for (Groups group : groups) {
            vector.add(group.getName());
        }
        return vector;
    }
}