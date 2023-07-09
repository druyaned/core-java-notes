package com.github.druyaned.learn_java.vol2.chapter01;

/**
 * Provides the bro with <i>pogremuha</i> and <i>authority</i>.
 * 
 * @author druyaned
 */
public class Bro implements Comparable<Bro> {
    
    /** The authority level of the Bro: {@code LOW}, {@code MID}, {@code TOP}. */
    public static enum Authority { LOW, MID, TOP }
    
    private final String pogremuha;
    private final Authority authority;
    
    /**
     * Defines the bro.
     * 
     * @param pogremuha a suitable nickname of the bro.
     * @param authority a level of the authority of the bro.
     */
    public Bro(String pogremuha, Authority authority) {
        this.pogremuha = pogremuha;
        this.authority = authority;
    }

    /**
     * Returns the pogremuha of the Bro.
     * 
     * @return the pogremuha of the Bro.
     */
    public String getPogremuha() {
        return pogremuha;
    }
    
    /**
     * Returns the authority of the Bro.
     * 
     * @return the authority of the Bro.
     */
    public Authority getAuthority() {
        return authority;
    }
    
    @Override
    public String toString() {
        return "[pogremuha=" + pogremuha + ", authority=" + authority + "]";
    }
    
    @Override
    public int compareTo(Bro o) {
        int authorityComparison = getAuthority().compareTo(o.getAuthority());
        if (authorityComparison != 0)
            return authorityComparison;
        return pogremuha.compareTo(o.pogremuha);
    }

}
