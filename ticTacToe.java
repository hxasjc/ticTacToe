import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Font;

public class ticTacToe extends javax.swing.JFrame {
    static Random rand; // random object

    static char[][] playingBoard; // global variables
    static boolean playAI;
    static String currentPlayer;
    static boolean currentGameAI;
    static ArrayList<JButton> boardButtons;
    static boolean continuePlaying;

    static JButton A1Button; // ui parts
    static JButton A2Button;
    static JButton A3Button;
    static JButton B1Button;
    static JButton B2Button;
    static JButton B3Button;
    static JButton C1Button;
    static JButton C2Button;
    static JButton C3Button;
    static JButton aiToggleButton;
    static JButton resetBoard;
    static JLabel aiLabel;
    static JLabel resultLabel;

    public ticTacToe() { // create the ui
        initUI();
    }

    /*String[][] boardTemplate = {{"A1","A2","A3"},
                                  {"B1","B2","B3"},
                                  {"C1","C2","C3"}};
    AI check order: check for possible win, check for preventing player win, check for possible wins, pick random
    */
    /*int[][] winningSolution = {{0,0,3},
                                 {0,2,4},
                                 {0,0,1}};*/

    private void initUI() { // initialize the ui parts
        Font monospaceFont = new Font("Monospaced", 0, 11); // font for the 9 main buttons

        A1Button = new JButton(" "); // the 9 main buttons
        A1Button.setName("A1");
        A1Button.setFont(monospaceFont);
        A1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                A1Action(evt);
            }
        });

        A2Button = new JButton(" "); // inital text
        A2Button.setName("A2"); // name
        A2Button.setFont(monospaceFont); // font
        A2Button.addActionListener(new ActionListener() { // connect method
            public void actionPerformed(ActionEvent evt) {
                A2Action(evt);
            }
        });

        A3Button = new JButton(" ");
        A3Button.setName("A3");
        A3Button.setFont(monospaceFont);
        A3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                A3Action(evt);
            }
        });

        B1Button = new JButton(" ");
        B1Button.setName("B1");
        B1Button.setFont(monospaceFont);
        B1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                B1Action(evt);
            }
        });

        B2Button = new JButton(" ");
        B2Button.setName("B2");
        B2Button.setFont(monospaceFont);
        B2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                B2Action(evt);
            }
        });

        B3Button = new JButton(" ");
        B3Button.setName("B3");
        B3Button.setFont(monospaceFont);
        B3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                B3Action(evt);
            }
        });

        C1Button = new JButton(" ");
        C1Button.setName("C1");
        C1Button.setFont(monospaceFont);
        C1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                C1Action(evt);
            }
        });

        C2Button = new JButton(" ");
        C2Button.setName("C2");
        C2Button.setFont(monospaceFont);
        C2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                C2Action(evt);
            }
        });

        C3Button = new JButton(" ");
        C3Button.setName("C3");
        C3Button.setFont(monospaceFont);
        C3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                C3Action(evt);
            }
        });

        aiToggleButton = new JButton("Toggle AI (Enabled)"); // toggle ai button
        aiToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aiToggleAction(evt);
            }
        });

        resetBoard = new JButton("Reset Board"); // reset board button
        resetBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetBoardAction(evt);
            }
        });

        aiLabel = new JLabel("<html>Toggle to play against the AI or a friend<br/>Changing this will only take effect next game"); // ai toggle description

        resultLabel = new JLabel(""); // for showing the result of the game

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // tell the ui to close
        setTitle("Tic Tac Toe"); // set window title

        GroupLayout layout = new GroupLayout(getContentPane()); // fancy layout stuff i only sorta understand
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(aiToggleButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aiLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(A1Button)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(A2Button)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(A3Button))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(B1Button)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B2Button)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B3Button))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(C1Button)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C2Button)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(C3Button))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(resultLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetBoard)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(aiToggleButton)
                    .addComponent(aiLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(A1Button)
                    .addComponent(A2Button)
                    .addComponent(A3Button))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(B1Button)
                    .addComponent(B2Button)
                    .addComponent(B3Button))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(C1Button)
                    .addComponent(C2Button)
                    .addComponent(C3Button))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(resultLabel)
                    .addComponent(resetBoard))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();

        boardButtons = new ArrayList<JButton>(); // arraylist of buttons
        boardButtons.add(A1Button);
        boardButtons.add(A2Button);
        boardButtons.add(A3Button);
        boardButtons.add(B1Button);
        boardButtons.add(B2Button);
        boardButtons.add(B3Button);
        boardButtons.add(C1Button);
        boardButtons.add(C2Button);
        boardButtons.add(C3Button);

        //System.out.println(boardButtons);

        playAI = true; // makes sure ai is turned on

        newGame(); // starts the game
    }

    private void A1Action(ActionEvent evt) { // bind button click to function
        claimSquare(A1Button);
    }

    private void A2Action(ActionEvent evt) {
        claimSquare(A2Button);
    }

    private void A3Action(ActionEvent evt) {
        claimSquare(A3Button);
    }

    private void B1Action(ActionEvent evt) {
        claimSquare(B1Button);
    }

    private void B2Action(ActionEvent evt) {
        claimSquare(B2Button);
    }

    private void B3Action(ActionEvent evt) {
        claimSquare(B3Button);
    }

    private void C1Action(ActionEvent evt) {
        claimSquare(C1Button);
    }

    private void C2Action(ActionEvent evt) {
        claimSquare(C2Button);
    }

    private void C3Action(ActionEvent evt) {
        claimSquare(C3Button);
    }

    private void aiToggleAction(ActionEvent evt) { // toggle the ai status
        if (playAI) {
            playAI = false;
            aiToggleButton.setText("Toggle AI (Disabled)");
        } else {
            playAI = true;
            aiToggleButton.setText("Toggle AI (Enabled)");
        }
        System.out.println("Set AI Toggle: " + playAI); // print result for debug
    }

    private void resetBoardAction(ActionEvent evt) {
        newGame(); // resets the board
        System.out.println("Reset the Board");
    }

    public static void newGame() { // method for setting up and starting each round
        if (playAI) { // decides if it should use the ai for this round and prints result
            currentGameAI = true;
            System.out.println("Enabled the AI for this round");
        } else {
            currentGameAI = false;
            resultLabel.setText("Player 1's turn");
            currentPlayer = "player1";
            System.out.println("Disabled the AI for this round");
        }

        for (JButton button : boardButtons) { // resets all the buttons
            button.setText(" ");
            button.setEnabled(true);
            System.out.println("Reset button " + button.getName());
        }

        for (int i = 0; i <=2; i++) { // resets the array (declared in main())
            for (int j = 0; j <= 2; j++) {
                playingBoard[i][j] = ' ';
                System.out.println("Reset array slot (" + i + "," + j + ")");
            }
        }

        resultLabel.setText(""); // clear result

        continuePlaying = true; // allows the game to continue
    }

    public static void claimSquare(JButton button) { // allows the player to select a button
        if (currentGameAI == true) { // for when playing against ai
            button.setText("O"); // claims button
            System.out.println("Claimed square " + button.getName() + " for player"); // debug

            int row = Character.getNumericValue(button.getName().charAt(0)) - 10; // find array coordinates for button
            int column = Character.getNumericValue(button.getName().charAt(1)) - 1;

            playingBoard[row][column] = 'O'; // sets value in array

            checkLine("player1"); // checks if there is a complete line and sends whose turn it was last (player)
        } else {
            if (currentPlayer == "player1") {
                button.setText("O");
                System.out.println("Claimed square " + button.getName() + " for player 1");

                int row = Character.getNumericValue(button.getName().charAt(0)) - 10;
                int col = Character.getNumericValue(button.getName().charAt(1)) - 1;

                playingBoard[row][col] = 'O';

                checkLine("player1"); // checks if there is a complete line and sends whose turn it was last (player)
            } else {
                button.setText("X");
                System.out.println("Claimed square " + button.getName() + " for player 2");

                int row = Character.getNumericValue(button.getName().charAt(0)) - 10;
                int col = Character.getNumericValue(button.getName().charAt(1)) - 1;

                playingBoard[row][col] = 'X';
                
                checkLine("player2");
            }
        }
        button.setEnabled(false); // disable the button
    }

    public static char findRow(int row) { // for finding a button row from array row
        char charRow = 'Z'; // in case something goes wrong
        switch (row) {
            case 0:
                charRow = 'A';
                break;
            case 1:
                charRow = 'B';
                break;
            case 2:
                charRow = 'C';
                break;

            default:
                break;
        }
        return charRow;
    }
    
    public static void aiMove() { // finds a square for the ai to take
        String chosenButton = "";

        if (chosenButton == "") { // atempt to find a winning line to complete
            for (int i = 0; i <= 2; i++) { // find horizontal rows
                if (chosenButton == "") {
                    int emptySquares = 0; // variables
                    int aiSquares = 0;
                    int playerSquares = 0;
                    int chosenRow = 0;
                    int chosenCol = 0;

                    for (int j = 0; j <= 2; j++) { // checks each square in the row and counts how many of each type in the row
                        switch (playingBoard[i][j]) {
                        case 'X': // squares occupied by the ai
                            aiSquares++;
                            break;
                        case ' ': // empty squares
                            emptySquares++;
                            chosenRow = i; // array coordinates
                            chosenCol = j;

                        default: // player squares
                            playerSquares++;
                            break;
                        }
                    }
                    System.out.println("AI squares: " + aiSquares); // debug
                    System.out.println("Empty squares: " + emptySquares);
                    System.out.println("Player squares: " + playerSquares);

                    if (aiSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") { // double check to make sure the spot meets the criteria
                        char row = findRow(chosenRow);
                        int col = chosenCol + 1;
                        StringBuilder sb = new StringBuilder(); // build button name
                        sb.append(row);
                        sb.append(col);
                        chosenButton = sb.toString(); // sets string to choose the button

                        playingBoard[chosenRow][chosenCol] = 'X'; // set array square
                    }
                }
            }

            for (int i = 0; i <= 2; i++) { // same as horizontal pretty much (vertical)
                if (chosenButton == "") {
                    int emptySquares = 0;
                    int aiSquares = 0;
                    int playerSquares = 0;
                    int chosenRow = 0;
                    int chosenCol = 0;

                    for (int j = 0; j <= 2; j++) {
                        switch (playingBoard[j][i]) {
                        case 'X':
                            aiSquares++;
                            break;
                        case ' ':
                            emptySquares++;
                            chosenRow = j;
                            chosenCol = i;
                            break;

                        default:
                            playerSquares++;
                            break;
                        }
                    }
                    System.out.println("AI squares: " + aiSquares); // debug
                    System.out.println("Empty squares: " + emptySquares);
                    System.out.println("Player squares: " + playerSquares);

                    if (aiSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") {
                        char row = findRow(chosenRow);
                        int col = chosenCol + 1;
                        StringBuilder sb = new StringBuilder();
                        sb.append(row);
                        sb.append(col);
                        chosenButton = sb.toString();

                        playingBoard[chosenRow][chosenCol] = 'X';
                    }
                }
            }

            int[][] diag1 = {{0,0},{1,1},{2,2}};
            for (int j = 0; j <= 0; j++) { // diagonal 1
                //int emptySquares = 0;
                int aiSquares = 0;
                //int playerSquares = 0;
                int chosenRow = 0;
                int chosenCol = 0;
                
                for (int i = 0; i <= 2; i++) {
                    int posRow = diag1[i][0];
                    int posCol = diag1[i][1];

                    switch (playingBoard[posRow][posCol]) {
                    case 'X':
                        aiSquares++;
                        break;
                    case ' ':
                        //emptySquares++;
                        chosenRow = posRow;
                        chosenCol = posCol;
                        break;

                    default:
                        //playerSquares++;
                        break;
                    }
                }
                if (aiSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") {
                    char row = findRow(chosenRow);
                    int col = chosenCol + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(row);
                    sb.append(col);
                    chosenButton = sb.toString();

                    playingBoard[chosenRow][chosenCol] = 'X';
                }
            }

            int[][] diag2 = {{2,0},{1,1},{0,2}};
            for (int j = 0; j <= 0; j++) { // diagonal 2
                //int emptySquares = 0;
                int aiSquares = 0;
                //int playerSquares = 0;
                int chosenRow = 0;
                int chosenCol = 0;
                for (int i = 0; i <= 2; i++) {
                    int posRow = diag2[i][0];
                    int posCol = diag2[i][1];

                    switch (playingBoard[posRow][posCol]) {
                    case 'X':
                        aiSquares++;
                        break;
                    case ' ':
                        //emptySquares++;
                        chosenRow = posRow;
                        chosenCol = posCol;
                        break;

                    default:
                        //playerSquares++;
                        break;
                    }
                }
                if (aiSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") {
                    char row = findRow(chosenRow);
                    int col = chosenCol + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(row);
                    sb.append(col);
                    chosenButton = sb.toString();

                    playingBoard[chosenRow][chosenCol] = 'X';
                }
            }
        }

        //will add code for blocking the player win here
        if (chosenButton == "") { // atempt to prevent the player from finishing a line
            for (int i = 0; i <= 2; i++) { // find horizontal rows
                if (chosenButton == "") {
                    int emptySquares = 0; // variables
                    int aiSquares = 0;
                    int playerSquares = 0;
                    int chosenRow = 0;
                    int chosenCol = 0;

                    for (int j = 0; j <= 2; j++) { // checks each square in the row and counts how many of each type in the row
                        switch (playingBoard[i][j]) {
                        case 'O': // squares occupied by the ai
                            playerSquares++;
                            break;
                        case ' ': // empty squares
                            emptySquares++;
                            chosenRow = i; // array coordinates
                            chosenCol = j;

                        default: // player squares
                            aiSquares++;
                            break;
                        }
                    }
                    System.out.println("AI squares: " + aiSquares); // debug
                    System.out.println("Empty squares: " + emptySquares);
                    System.out.println("Player squares: " + playerSquares);

                    if (playerSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") { // double check to make sure the spot meets the criteria
                        char row = findRow(chosenRow);
                        int col = chosenCol + 1;
                        StringBuilder sb = new StringBuilder(); // build button name
                        sb.append(row);
                        sb.append(col);
                        chosenButton = sb.toString(); // sets string to choose the button

                        playingBoard[chosenRow][chosenCol] = 'X'; // set array square
                    }
                }
            }

            for (int i = 0; i <= 2; i++) { // same as horizontal pretty much (vertical)
                if (chosenButton == "") {
                    int emptySquares = 0;
                    int aiSquares = 0;
                    int playerSquares = 0;
                    int chosenRow = 0;
                    int chosenCol = 0;

                    for (int j = 0; j <= 2; j++) {
                        switch (playingBoard[j][i]) {
                        case 'O':
                            playerSquares++;
                            break;
                        case ' ':
                            emptySquares++;
                            chosenRow = j;
                            chosenCol = i;
                            break;

                        default:
                            aiSquares++;
                            break;
                        }
                    }
                    System.out.println("AI squares: " + aiSquares); // debug
                    System.out.println("Empty squares: " + emptySquares);
                    System.out.println("Player squares: " + playerSquares);

                    if (playerSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") {
                        char row = findRow(chosenRow);
                        int col = chosenCol + 1;
                        StringBuilder sb = new StringBuilder();
                        sb.append(row);
                        sb.append(col);
                        chosenButton = sb.toString();

                        playingBoard[chosenRow][chosenCol] = 'X';
                    }
                }
            }

            int[][] diag1 = {{0,0},{1,1},{2,2}};
            for (int j = 0; j <= 0; j++) { // diagonal 1
                //int emptySquares = 0;
                //int aiSquares = 0;
                int playerSquares = 0;
                int chosenRow = 0;
                int chosenCol = 0;
                for (int i = 0; i <= 2; i++) {
                    int posRow = diag1[i][0];
                    int posCol = diag1[i][1];

                    switch (playingBoard[posRow][posCol]) {
                    case 'O':
                        playerSquares++;
                        break;
                    case ' ':
                        //emptySquares++;
                        chosenRow = posRow;
                        chosenCol = posCol;
                        break;

                    default:
                        //aiSquares++;
                        break;
                    }
                }
                if (playerSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") {
                    char row = findRow(chosenRow);
                    int col = chosenCol + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(row);
                    sb.append(col);
                    chosenButton = sb.toString();

                    playingBoard[chosenRow][chosenCol] = 'X';
                }
            }

            int[][] diag2 = {{2,0},{1,1},{0,2}};
            for (int j = 0; j <= 0; j++) { // diagonal 2
                //int emptySquares = 0;
                //int aiSquares = 0;
                int playerSquares = 0;
                int chosenRow = 0;
                int chosenCol = 0;
                for (int i = 0; i <= 2; i++) {
                    int posRow = diag2[i][0];
                    int posCol = diag2[i][1];

                    switch (playingBoard[posRow][posCol]) {
                    case 'O':
                        playerSquares++;
                        break;
                    case ' ':
                        //emptySquares++;
                        chosenRow = posRow;
                        chosenCol = posCol;
                        break;

                    default:
                        //aiSquares++;
                        break;
                    }
                }
                if (playerSquares == 2 && playingBoard[chosenRow][chosenCol] == ' ' && chosenButton == "") {
                    char row = findRow(chosenRow);
                    int col = chosenCol + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(row);
                    sb.append(col);
                    chosenButton = sb.toString();

                    playingBoard[chosenRow][chosenCol] = 'X';
                }
            }
        }

        while (chosenButton == "") { // if all else fails, randomly pick a square until it finds one thats empty
            int randRow = rand.nextInt(2); // generates array coordinates
            int randCol = rand.nextInt(2);

            if (playingBoard[randRow][randCol] == ' ') { // if its empty
                playingBoard[randRow][randCol] = 'X'; // set the square in the array

                char row = findRow(randRow); // find the row letter
                System.out.println("Row: " + row);

                int col = randCol + 1; // find column

                StringBuilder sb = new StringBuilder();
                sb.append(row);
                sb.append(col);
                chosenButton = sb.toString(); // set string button
            }
        }

        for (JButton button : boardButtons) { // make changes to chosen button
            if (button.getName().contains(chosenButton)) {
                //System.out.println(button.getName() + " | checking for chosen button | success"); // debug
                button.setText("X");
                button.setEnabled(false);
            } else {
                //System.out.println(button.getName() + " | checking for chosen button"); // debug
            }
        }

        System.out.println("Claimed square " + chosenButton + " for AI"); // debug
        checkLine("ai"); // checks for completed lines
    }

    public static void checkLine(String str) { // check if the board is full or there are any completed lines
        System.out.println(Arrays.deepToString(playingBoard));

        boolean isFull = true; // goes through every spot in the array until it finds a blank one to find out if the board is full
        for (int i = 0; i <=2; i++) {
            for (int j = 0; j <=2; j++) {
                if (playingBoard[i][j] == ' ') {
                    isFull = false;
                }
            }
        }

        if (isFull) { // if the board is full
            resultLabel.setText("The board is full!"); // set result
            System.out.println("Board full"); // debug
            continuePlaying = false; // stops playing
        }

        boolean isRow = false; // checks for completed rows
        char winningChar = 'Z';
        for (int i = 0; i <= 2; i++) { // horizontal rows
            if (playingBoard[i][0] != ' ') { // makes sure the first spot isnt empty
                if (playingBoard[i][0] == playingBoard[i][1] && playingBoard[i][0] == playingBoard[i][2]) { // checks if all three on the row are equal
                    isRow = true;
                    winningChar = playingBoard[i][0]; // character of the person who won
                    System.out.println("Horizontal row"); // debug
                }
            }
        }
        for (int i = 0; i <= 2; i++) { // vertical rows, basically same as horizontal
            if (playingBoard[0][i] != ' ') {
                if (playingBoard[0][i] == playingBoard[1][i] && playingBoard[0][i] == playingBoard[2][i]) {
                    isRow = true;
                    winningChar = playingBoard[0][i];
                    System.out.println("Vertical row");
                }
            }
        }
        if (playingBoard[0][0] != ' ') { // diagonal #1
            if (playingBoard[0][0] == playingBoard[1][1] && playingBoard[0][0] == playingBoard[2][2]) {
                isRow = true;
                winningChar = playingBoard[0][0];
                System.out.println("Diagonal win");
            }
        }
        if (playingBoard[2][0] != ' ') { // diagonal #2
            if (playingBoard[2][0] == playingBoard[1][1] && playingBoard[2][0] == playingBoard[0][2]) {
                isRow = true;
                winningChar = playingBoard[2][0];
                System.out.println("Diagonal win");
            }
        }

        if (isRow) { // if there is a completed row
            if (currentGameAI) { // if played against ai
                if (winningChar == 'O') { // player won
                    resultLabel.setText("Congrats! You won!"); // set result
                    System.out.println("Player win"); // debug
                } else { // ai won
                    resultLabel.setText("The AI won");
                    System.out.println("AI win");
                }
            } else {
                if (winningChar == 'O') {
                    resultLabel.setText("Congrats! Player 1 won!");
                    System.out.println("playwr 1 win");
                } else {
                    resultLabel.setText("Congrats! Player 2 won!");
                    System.out.println("player 2 win");
                }
            }
            continuePlaying = false; // stops playing
        }

        if (continuePlaying == false) { // disables every button
            for (JButton button : boardButtons) {
                button.setEnabled(false);
            }
        } else {
            if (str == "player1" && currentGameAI == true) { // check if ai should go next
                aiMove();
            } else if (str == "player1" && currentGameAI == false) {
                resultLabel.setText("Player 2's turn");
            } else if (str == "player2" && currentGameAI == false) {
                resultLabel.setText("Player 1's turn");
            }
        }
    }

    public static void main(String[] args) {
        rand = new Random(); // init random
        playingBoard = new char[][] {{' ',' ',' '}, // blank array used for line completion and ai
                                     {' ',' ',' '},
                                     {' ',' ',' '}};
        //

        EventQueue.invokeLater(new Runnable() { // display the gui
            public void run() {
                new ticTacToe().setVisible(true);
            }
        });
    }
}