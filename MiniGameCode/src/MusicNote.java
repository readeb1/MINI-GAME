import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Utility class to play musical notes.
 */
public class MusicNote {
    public static final int BUF_SIZE = 32000;
    public static final int SAMPLE_RATE = 44000;
    public static final int FREQUENCY_A4 = 440;
    public static final int DEFAULT_AMPLITUDE = 127;

    private static Map<String, Double> noteFrequencies = getNoteFrequencyMap(0, 7);
    private static SourceDataLine dataLine = initDataLine();
    private static byte[] buffer = new byte[BUF_SIZE];

    public static void main(String[] args) {
        // playNote("C3", 3);

        // Sort by frequency, and play all the notes.
        var invertedMap = inverseMap(noteFrequencies);
        var frequencyNoteList = new ArrayList<>(invertedMap.entrySet());
        frequencyNoteList.sort(Comparator.comparing(entry -> entry.getKey()));

        for (var pair : frequencyNoteList) {
            System.out.printf("%-5s: %s\n", Math.round(pair.getKey()), String.join(" ",
                    pair.getValue()));
            play(pair.getKey(), .1);
        }

        String[] notes = "C3 D3 E3 F3 G3 A3 B3 C4 D4 E4 F4 G4 A4 B4".split(" ");
        for (String note : notes) {
            System.out.println(noteFrequencies.get(note));
            // playNote(note,1);
        }
    }

    /**
     * Plays specified note for specified duration. See musical notations:
     * https://www.audiolabs-erlangen.de/resources/MIR/FMP/data/C1/FMP_C1_F02.png
     * 
     * @param note        Note to play. Example notes: "C4" is middle C, "C#4" is
     *                    middle C sharp, "Eb3" is baritone E flat, etc.
     * @param durationSec Duration (in seconds) to play note for.
     */
    public static void playNote(String note, double durationSec) {
        if (note == null || note.isBlank()) {
            play(0, durationSec);
            return;
        }

        Double frequency = noteFrequencies.get(note);

        if (frequency == null) {
            System.err.println("Unrecognized note: " + note);
            return;
        }

        play(frequency, durationSec);
    }

    /**
     * Plays note for 1 second. See {@link #playNote(String, double)} method.
     * 
     * @param note
     */
    public static void playNote(String note) {
        playNote(note, 1);
    }

    /**
     * Plays specified frequency for specified duration.
     * 
     * @param frequencyHz Frequency in Hz
     * @param durationSec Duraction in seconds.
     */
    public static void play(double frequencyHz, double durationSec) {
        // TODO: Parameter to specify whether to taper off at end, to reduce popping sound.
        playChord(durationSec, frequencyHz);
    }

    /**
     * Plays set of notes (chord) at the same time. See {@link #playChord(double, String...)}
     * @param durationSec Duration to play (seconds).
     * @param notes Notes to play.
     */
    public static void playChord(double durationSec, String... notes) {
        // Get frequencies.
        var frequencies = new double[notes.length];
        for (var i = 0; i < notes.length; i++) {
            var note = notes[i];

            if (note == null || note.isBlank()) {
                continue;
            }

            Double frequency = noteFrequencies.get(note);

            if (frequency == null) {
                System.err.println("Unrecognized note: " + note);
                continue;
            }

            frequencies[i] = noteFrequencies.get(note);
        }

        // Play chord.
        playChord(durationSec, frequencies);
    }

    /**
     * Plays set of frequencies (chord) at the same time.
     * @param durationSec Duration to play (seconds).
     * @param frequenciesHz Frequencies to play.
     */
    public static void playChord(double durationSec, double... frequenciesHz) {
        byte amp = DEFAULT_AMPLITUDE;   // TODO: Make into parameter.

        // TODO: Equalizer, or just bass boost.
        int countNonZero = 0;
        for (var freq : frequenciesHz) {
            if (freq > 0)
                countNonZero++;
        }

        var totalBaseAmplitude = moderateAmplitude(countNonZero * amp);
        double baseAmplitude = (double) totalBaseAmplitude / countNonZero;

        // long start = System.nanoTime();
        long beat = 0;
        // while (System.nanoTime() - start < durationSec * 1_000_000_000) {
        while ((double) (beat + buffer.length) / SAMPLE_RATE <= durationSec) {
            for (int i = 0; i < buffer.length; i++, beat++) {
                buffer[i] = (byte) combineAmplitudes(frequenciesHz, baseAmplitude, beat);
            }
            dataLine.write(buffer, 0, buffer.length);
        }

        // Remaining few beats fewer than buffer size:
        int i;
        for (i = 0; (double) beat / SAMPLE_RATE < durationSec; i++, beat++) {
            buffer[i] = (byte) combineAmplitudes(frequenciesHz, baseAmplitude, beat);
        }
        dataLine.write(buffer, 0, i);
    }

    /**
     * Add up amplitudes of each given frequency's amplitude at the 
     * @param frequenciesHz
     * @param baseAmplitude
     * @param beat
     * @return
     */
    private static double combineAmplitudes(double[] frequenciesHz, double baseAmplitude, long beat) {
        double amplitude = 0;
        for (var frequency : frequenciesHz) {
            if (frequency == 0)
                continue;
            amplitude += baseAmplitude * Math.sin(2 * Math.PI * frequency * beat / SAMPLE_RATE);
        }
        return amplitude;
    }

    public static byte moderateAmplitude(long amplitude) {
        var curveStrength = 10;
        var power = -127 * Math.exp(-curveStrength * amplitude) + 127;
        return (byte) Math.round(power);
    }

    private static SourceDataLine initDataLine() {
        AudioFormat aFormat = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);

        try {
            SourceDataLine line = AudioSystem.getSourceDataLine(aFormat);
            line.open(aFormat, BUF_SIZE);
            line.start();
            return line;
        } catch (LineUnavailableException exception) {
            System.err.println("Failed to open data line.");
            return null;
        }
    }

    private static Map<String, Double> getNoteFrequencyMap(int startScale, int endScale) {

        int offsetC = -9; // offset of C note relative to base A note in its scale.
        String[] noteFormatStrings = { "C%d", "C#%d", "D%d", "D#%d", "E%d", "F%d", "F#%d", "G%d", "G#%d", "A%d", "A#%d",
                "B%d" };

        Map<String, Double> noteFreqMap = new HashMap<String, Double>();

        for (int scale = startScale; scale <= endScale; scale++) {
            // Frequency of base A note in this scale.
            int frequencyA = scale >= 4 ? FREQUENCY_A4 << scale - 4 : FREQUENCY_A4 >> 4 - scale;

            for (int i = 0; i < noteFormatStrings.length; i++) {
                // Format note.
                String note = String.format(noteFormatStrings[i], scale);
                int offset = offsetC + i;
                // Calculate note frequency.
                double freq = getNoteFrequency(frequencyA, offset);
                // Store note-frequency pair in map.
                noteFreqMap.put(note, freq);

                // Also add equivalent note, if any.
                String equivalentNote = getEquivalentNote(note);
                if (equivalentNote != null) {
                    noteFreqMap.put(equivalentNote, freq);
                }
            }
        }

        return noteFreqMap;
    }

    /**
     * Calculates frequency of note at specified offset from specified base
     * frequency.
     * Eg., C4 is at an offset of 3 from A4 (A4, A4#, B4, C4), so frequency of C4 is
     * A4 * 2 ** (3/12) = 440 * 2 ** (3/12) ~ 523.28
     * 
     * @param baseFrequency
     * @param offset
     * @return
     */
    private static double getNoteFrequency(double baseFrequency, int offset) {
        return baseFrequency * Math.pow(2, (double) offset / 12.);
    }

    /**
     * 
     * @param note One of Cn, C#n, Dn, D#n, En, Fn, F#n, Gn, G#n, An, A#n, Bn.
     * @return
     */
    private static String getEquivalentNote(String note) {

        char naturalNote = note.charAt(0);

        // Special case sharp.
        if (note.startsWith("G#"))
            return "Ab" + note.substring(2);

        // All other sharps:
        if (note.charAt(1) == '#')
            // Return equivalent flat:
            return (char) (naturalNote + 1) + "b" + note.substring(2);

        // Naturals:
        int scale = Integer.parseInt(note.substring(1));
        switch (naturalNote) {
            case 'C':
                return "B#" + (scale - 1);
            case 'E':
                return "Fb" + scale;
            case 'F':
                return "E#" + scale;
            case 'B':
                return "Cb" + (scale + 1);
        }

        // No equivalent:
        return null;
    }

    /**
     * Creates and returns new map by inverting the key-value relationship, similar
     * to the inverse of a mathematical function.
     * In the new map, each value from the original map is mapped to the list of
     * keys associated with the value.
     * 
     * @param <S> Key type of original map.
     * @param <T> Value
     * @param map Original map.
     * @return Inverse map.
     */
    public static <S, T extends Comparable<T>> Map<T, List<S>> inverseMap(final Map<S, T> map) {

        Map<T, List<S>> invertedMap = new HashMap<>();
        for (Entry<S, T> entry : map.entrySet()) {
            S key = entry.getKey();
            T value = entry.getValue();
            if (invertedMap.get(value) == null) {
                invertedMap.put(value, new ArrayList<>());
            }

            invertedMap.get(value).add(key);
        }

        return invertedMap;
    }
}
