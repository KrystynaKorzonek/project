public class StringPair {
    public String polish;
    public String english;

    public StringPair(String polish, String english) {
        this.polish = polish;
        this.english = english;
    }

    public String get(Language lang) {
        switch (lang) {
            case POLISH -> {
                return polish;
            }
            case ENGLISH -> {
                return english;
            }
        }
        return null;
    }
}
