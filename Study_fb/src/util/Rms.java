package util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class Rms
    implements RecordFilter, RecordComparator
{
    /*
     * The RecordStore used for storing the game scores.
     */
    private RecordStore recordStore = null;

    /*
     * The player name to use when filtering.
     */
    public static String playerNameFilter = null;

    /*
     * Part of the RecordFilter interface.
     */
    public boolean matches(byte[] candidate)
	throws IllegalArgumentException
    {
	// If no filter set, nothing can match it.
	if (Rms.playerNameFilter == null) {
	    return false;
	}

	ByteArrayInputStream bais = new ByteArrayInputStream(candidate);
	DataInputStream inputStream = new DataInputStream(bais);
	String name = null;
	
	try {
	    name = inputStream.readUTF();
	}
	catch (EOFException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}
	catch (IOException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}
	return (Rms.playerNameFilter.equals(name));
    }

    /*
     * Part of the RecordComparator interface.
     */
    public int compare(byte[] rec1, byte[] rec2)
    {
	// Construct DataInputStreams for extracting the scores from
	// the records.
	ByteArrayInputStream bais1 = new ByteArrayInputStream(rec1);
	DataInputStream inputStream1 = new DataInputStream(bais1);
	ByteArrayInputStream bais2 = new ByteArrayInputStream(rec2);
	DataInputStream inputStream2 = new DataInputStream(bais2);
	int score1 = 0;
	int score2 = 0;
	try {
	    // Extract the scores.
	    score1 = inputStream1.readInt();
	    score2 = inputStream2.readInt();
	}
	catch (EOFException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}
	catch (IOException eofe) {
	    System.out.println(eofe);
	    eofe.printStackTrace();
	}

	// Sort by score
	if (score1 < score2) {
	    return RecordComparator.PRECEDES;
	}
	else if (score1 > score2) {
	    return RecordComparator.FOLLOWS;
	}
	else {
	    return RecordComparator.EQUIVALENT;
	}
    }

    /**
     * The constructor opens the underlying record store,
     * creating it if necessary.
     */
    public Rms()
    {
	//
	// Create a new record store for this example
	//
	try {
	    recordStore = RecordStore.openRecordStore("scores", true);
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
    }

    /**
     * Add a new score to the storage.
     *
     * @param score the score to store.
     * @param playerName the name of the play achieving this score.
     */
    public void addScore(int score, String playerName)
    {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	DataOutputStream outputStream = new DataOutputStream(baos);
	try {
	    // Push the score into a byte array.
	    outputStream.writeInt(score);
	    // Then push the player name.
	    outputStream.writeUTF(playerName);
	}
	catch (IOException ioe) {
	    System.out.println(ioe);
	    ioe.printStackTrace();
	}
    }

    /**
     * A helper method for the printScores methods.
     */
    private void printScoresHelper(RecordEnumeration re)
    {
	try {
	    while(re.hasNextElement()) {
		int id = re.nextRecordId();
		ByteArrayInputStream bais = new ByteArrayInputStream(recordStore.getRecord(id));
		DataInputStream inputStream = new DataInputStream(bais);
		try {
		    int score = inputStream.readInt();
		    String playerName = inputStream.readUTF();
		    System.out.println(playerName + " = " + score);
		}
		catch (EOFException eofe) {
		    System.out.println(eofe);
		    eofe.printStackTrace();
		}
	    }
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
	catch (IOException ioe) {
	    System.out.println(ioe);
	    ioe.printStackTrace();
	}
    }

    /**
     * This method prints all of the scores sorted by game score.
     */
    public void printScores()
    {
	try {
	    // Enumerate the records using the comparator implemented
	    // above to sort by game score.
	    RecordEnumeration re = recordStore.enumerateRecords(null, this, 
								true);
	    printScoresHelper(re);
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
    }

    /**
     * This method prints all of the scores for a given player,
     * sorted by game score.
     */
    public void printScores(String playerName)
    {
	try {
	    // Enumerate the records using the comparator and filter
	    // implemented above to sort by game score.
	    RecordEnumeration re = recordStore.enumerateRecords(this, this, 
								true);
	    printScoresHelper(re);
	}
	catch (RecordStoreException rse) {
	    System.out.println(rse);
	    rse.printStackTrace();
	}
    }

    public static void main(String[] args)
    {
	Rms rmsgs = new Rms();
	rmsgs.addScore(100, "Alice");
	rmsgs.addScore(120, "Bill");
	rmsgs.addScore(80, "Candice");
	rmsgs.addScore(40, "Dean");
	rmsgs.addScore(200, "Ethel");
	rmsgs.addScore(110, "Farnsworth");
	rmsgs.addScore(220, "Farnsworth");
	System.out.println("All scores");
	rmsgs.printScores();
	System.out.println("Farnsworth's scores");
	Rms.playerNameFilter = "Farnsworth";
	rmsgs.printScores("Farnsworth");
    }
}
