package druyaned.corejava.vol2.ch01streamapi;

/**
 * Provides the bro with <i>pogremuha</i> and <i>authority</i>.
 * @author druyaned
 */
public class Bro implements Comparable<Bro> {
    
    /** The authority level of the Bro: {@code LOW}, {@code MID}, {@code TOP}. */
    public static enum Authority {LOW, MID, TOP}
    
    private final String pogremuha;
    private final Authority authority;
    
    /**
     * Defines the bro.
     * @param pogremuha suitable nickname of the bro
     * @param authority level of authority of the bro
     */
    public Bro(String pogremuha, Authority authority) {
        this.pogremuha = pogremuha;
        this.authority = authority;
    }
    
    /**
     * Returns the pogremuha of the Bro.
     * @return pogremuha of the Bro
     */
    public String getPogremuha() {
        return pogremuha;
    }
    
    /**
     * Returns the authority of the Bro.
     * @return authority of the Bro
     */
    public Authority getAuthority() {
        return authority;
    }
    
    @Override public String toString() {
        return pogremuha + '(' + authority + ')';
    }
    
    @Override public int compareTo(Bro o) {
        int authorityComparison = getAuthority().compareTo(o.getAuthority());
        if (authorityComparison != 0) {
            return authorityComparison;
        }
        return pogremuha.compareTo(o.pogremuha);
    }
    
    public static Bro[] getBros() {
        Bro[] bros = {
            new Bro("Biloy", Bro.Authority.TOP),
            new Bro("Gora", Bro.Authority.LOW),
            new Bro("Pishniy", Bro.Authority.MID),
            new Bro("Grachok", Bro.Authority.TOP),
            new Bro("Griha", Bro.Authority.MID)
        };
        return bros;
    }
    
}
