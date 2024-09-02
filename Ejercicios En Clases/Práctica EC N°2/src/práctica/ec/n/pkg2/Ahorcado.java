package práctica.ec.n.pkg2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Ahorcado extends UnicastRemoteObject implements IAhorcado{

    public Ahorcado() throws RemoteException {
        super();
    }
    
    @Override
    public void inciar() {
        this.words_clues = createWordsClues();
        this.attempts = 7;
        this.game_over = false;
        this.incorrect_letters = new ArrayList<>();
        selectWordAndClue();
    }

    @Override
    public Boolean esJuegoTerminado() {
        return this.isGameOver();
    }
    
    @Override
    public Respuesta adivinarLetra(char letra) throws RemoteException {
        this.guessLetter(letra);
        return new Respuesta(new String(this.word_guess), this.attempts);
    }

    @Override
    public Respuesta adivinarPalabra(String palabra) throws RemoteException {
        this.guessLetter(palabra.charAt(0));
        return new Respuesta(new String(this.word_guess), this.attempts);
    }
    
    private Map<String, String> words_clues;
    private String word_play;
    private String clue_play;
    private char[] word_guess;
    private int attempts;
    private boolean game_over;
    private ArrayList<Character> incorrect_letters;
    private HashMap<String, String> createWordsClues() {
        HashMap<String, String> w_c = new HashMap<>();
        w_c.put("C", "Padre de todo lenguaje moderno");
        w_c.put("ASSEMBLER", "Primer lenguaje de bajo nivel");
        w_c.put("POO", "Paradigma común en programación");
        w_c.put("COMPILADOR", "Traduce código fuente a máquina");
        w_c.put("ALGORITMO", "Instrucciones para resolver un problema");
        w_c.put("CPP", "Extensión moderna del lenguaje C");
        w_c.put("JAVA", "Escribe una vez, ejecuta en cualquier lugar");
        w_c.put("JAVASCRIPT", "Lenguaje web para interactividad y dinamismo");
        w_c.put("PYTHON", "Simplicidad y legibilidad en programación");
        w_c.put("PHP", "Desarrollo web del lado del servidor");
        w_c.put("SQL", "Gestión de bases de datos estructuradas");
        w_c.put("HTML", "Crea la estructura de páginas web");
        w_c.put("CSS", "Estilos para presentación de documentos HTML");
        return w_c;
    }
    
    private void selectWordAndClue() {
        Random rand = new Random();
        Object[] keys = words_clues.keySet().toArray();
        this.word_play = (String)keys[rand.nextInt(keys.length)];
        this.clue_play = words_clues.get(word_play);
        this.word_guess = new char[word_play.length()];
        Arrays.fill(word_guess, '_');
    }
    
    private boolean isGameOver() {
        return game_over;
    }
    
    private boolean isWordGuessed() {
        return word_play.equals(new String(word_guess));
    }
    
    private int getAttemptsAvailable() {
        return attempts;
    }
    
    private String getWordPlay() {
        return word_play;
    }
    
    private String getCluePlay() {
        return clue_play;
    }
    
    private ArrayList<Character> getIncorrectLetters() {
        return incorrect_letters;
    }
    
    private String getCurrentGuess() {
        return new String(word_guess);
    }
    
    private void guessLetter(char _letter) {
        boolean correct = false;

        for (int i = 0; i < word_play.length(); i++) {
            if (word_play.charAt(i) == _letter) {
                word_guess[i] = _letter;
                correct = true;
            }
        }
        if (!correct) {
            if (!incorrect_letters.contains(_letter)) {
                incorrect_letters.add(_letter);
                attempts--;
            }
        }
        if (isWordGuessed() || attempts <= 0) {
            game_over = true;
        }
    }
    
}
