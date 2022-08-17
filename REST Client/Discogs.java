import com.google.gson.Gson;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
public class Discogs {
    public String getArtistID(String query, String token) throws Exception {
        URL url = new URL("https://api.discogs.com/database/search?q=" +
                query + "&token=" + token);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }

    public String getArtists(int id) throws Exception {
        URL artistURL = new URL("https://api.discogs.com/artists/" + id);
        HttpURLConnection artistConnection = (HttpURLConnection) artistURL.openConnection();
        artistConnection.setRequestMethod("GET");
        artistConnection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(artistConnection.getInputStream()));
        String inp;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inp = bufferedReader.readLine()) != null) {
            stringBuilder.append(inp);
        }
        bufferedReader.close();
        artistConnection.disconnect();
        return stringBuilder.toString();
    }

    public String prepareInput(String input) {
        return input.trim().replace(" ", "+");
    }

    public static void main(String[] args) throws Exception {
        try {
            Path fileWithToken = Path.of("token.txt");
            String token = Files.readString(fileWithToken);
            System.out.println("Please enter the artist name: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Discogs discogs = new Discogs();
            String content = "";
            try {
                content = discogs.getArtistID(discogs.prepareInput(br.readLine()), token);
            } catch (Exception ex) {
                System.out.println("No internet connection");
                System.exit(-1);
            }
            Gson gson = new Gson();
            Artist artist = gson.fromJson(content, Artist.class);
            String result = discogs.getArtists(artist.getID());
            System.out.println(artist.getName() + " (ID=" + artist.getID() + ")");
            Band band = gson.fromJson(result, Band.class);
            ArrayList<Pair<Integer, String>> members = band.getArtistPair();
            HashMap<String, ArrayList<String>> groupsMap = new HashMap<>();
            for (Pair<Integer, String> art : members) {
                String res = discogs.getArtists(art.getFirst());
                Band b = gson.fromJson(res, Band.class);
                for (int i = 0; i < b.getGroups().size(); i++) {
                    groupsMap.putIfAbsent(b.getGroups().get(i), new ArrayList<>());
                    groupsMap.get(b.getGroups().get(i)).add(art.getSecond() + " (ID=" + art.getFirst() + ")");
                }
            }
            for (Map.Entry<String, ArrayList<String>> record : groupsMap.entrySet()) {
                if (record.getValue().size() <= 1 || record.getKey().compareTo(artist.getName()) == 0) {
                    continue;
                }
                System.out.println(record.getKey());
                Collections.sort(record.getValue());
                for (String member : record.getValue()) {
                    System.out.println(" -> " + member);
                }
            }
        } catch (IOException e) {
            System.out.println("Too many requests! Please wait a minute and then execute program one more time!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("The artist name you entered does not exist!");
        }
    }
}
