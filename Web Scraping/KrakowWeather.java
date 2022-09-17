public class KrakowWeather {
    Forecasts[] forecasts;
    Location location;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(location);
        for (Forecasts forecast : forecasts) {
            stringBuilder.append(forecast.toString());
        }
        return stringBuilder.toString();
    }
    public static class Forecasts {
        Detailed detailed;

        @Override
        public String toString() {
            return detailed.toString();
        }
    }

    public static class Detailed {
        Reports[] reports;

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Reports report : reports) {
                stringBuilder.append(report.toString());
            }
            return stringBuilder.toString();
        }
    }

    public static class Reports {
        String localDate;
        int temperatureC;
        String timeslot;

        @Override
        public String toString() {
            return localDate + " -- " + timeslot + " -- " + temperatureC + "\u00B0C\n";
        }
    }

    public static class Location {
        String name;
        String container;

        @Override
        public String toString() {
            return name + ", " + container + "\n";
        }
    }
}
