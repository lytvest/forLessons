package lesson18;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class BeatBox {

    String[] names = new String[]{
            "Bass Drum", "Closed hi-hat", "Open hi-hat", "Acoustic Snare",
            "Crash Cymbal", "Hand clap", "High Tom", "Hi bongo", "Maracas", "Whistle",
            "Low conda", "Cowbell", "Vibraslab", "Low-mid Tom", "High Agogo", "Open hi Conga"
    };

    int[] instruments = new int[]{35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
    private Sequencer sequencer;
    ArrayList<JCheckBox> checkboxes = new ArrayList<>();
    JTextField text;

    BeatBox() {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var layout = new BorderLayout();

        var panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        var buttonBox = new Box(BoxLayout.Y_AXIS);

        var start = new JButton("Start");
        start.addActionListener((event) -> {
            buildTrackAndStart();
            card = 0;
            timer.start();
            drawFrame();

        });
        buttonBox.add(start);

        var stop = new JButton("Stop");
        stop.addActionListener((event) -> {
            sequencer.stop();
            timer.stop();
            drawFrame.setVisible(false);
            drawPanel.clear();
        });
        buttonBox.add(stop);

        var upTemp = new JButton("up temp");
        upTemp.addActionListener((event) -> {
            float factor = sequencer.getTempoFactor();
            sequencer.setTempoFactor(factor * 1.03f);
        });
        buttonBox.add(upTemp);

        var downTemp = new JButton("down temp");
        downTemp.addActionListener((event) -> {
            float factor = sequencer.getTempoFactor();
            sequencer.setTempoFactor(factor * 0.97f);
        });
        buttonBox.add(downTemp);

        var serizalite = new JButton("save in file");
        serizalite.addActionListener((event) -> {
            saveData();
        });
        buttonBox.add(serizalite);

        var load = new JButton("load from file");
        load.addActionListener((event) -> {
            readData();
        });
        buttonBox.add(load);

        var random = new JButton("randomize");
        random.addActionListener((e) -> {
            var rand = new Random();
            for (var check: checkboxes) {
                check.setSelected(rand.nextBoolean());
            }
        });
        buttonBox.add(random);

        Box namesBox = new Box(BoxLayout.Y_AXIS);

        for (var name : names) {
            namesBox.add(new Label(name));
        }
        setUpMidi();
        panel.add(BorderLayout.EAST, buttonBox);
        panel.add(BorderLayout.WEST, namesBox);

        text = new JTextField("0", 30);
        text.addActionListener((e -> {
            try {
                loopData = Integer.parseInt(text.getText());
                System.out.println("set loop " + loopData);
                setLoop(text);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }));
        Box boxDown = new Box(BoxLayout.X_AXIS);
        boxDown.add(new JLabel("Количество циклов:  "));
        boxDown.add(text);
        panel.add(BorderLayout.SOUTH, boxDown);


        frame.getContentPane().add(panel);

        var grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);

        var mainPanel = new JPanel(grid);
        panel.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            var checkbox = new JCheckBox();
            checkbox.setSelected(false);
            mainPanel.add(checkbox);
            checkboxes.add(checkbox);
        }

        frame.setBounds(30, 30, 500, 500);
        frame.pack();
        frame.setVisible(true);

    }
    JFrame drawFrame;
    MyJPanel drawPanel = new MyJPanel();
    private void drawFrame(){
        if(drawFrame == null)
            createDrawFrame();

        drawFrame.setVisible(true);
    }

    private void createDrawFrame() {
        drawFrame = new JFrame();
        drawFrame.getContentPane().add(drawPanel);
        drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawFrame.setBounds(650,30,500,500);
        drawFrame.setVisible(true);
    }
    int card = 0;
    Timer timer = new Timer(2000 / 16, (event) -> {
        for (int i = 0; i < 16; i++) {
            if(checkboxes.get(i * 16 + card).isSelected()){
                drawPanel.drawRandom();
            }
        }

        card ++;
        card = card % 16;
    });

    static class MyJPanel extends JPanel {
        Random rand = new Random();
        ArrayList<Integer> xpos = new ArrayList<>();
        ArrayList<Integer> ypos = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < xpos.size(); i++) {
                g.setColor(colors.get(i));
                g.fillRect(xpos.get(i), ypos.get(i),50,50);
            }
        }

        public void drawRandom(){
            xpos.add(rand.nextInt(450));
            ypos.add(rand.nextInt(450));
            colors.add(new Color(rand.nextInt()));
            if (xpos.size() > 1000){
                xpos = new ArrayList<>(xpos.subList(xpos.size() - 100, xpos.size()));
                ypos = new ArrayList<>(ypos.subList(ypos.size() - 100, ypos.size()));
                colors = new ArrayList<>(colors.subList(colors.size() - 100, colors.size()));
            }
            repaint();
        }

        public void clear(){
            colors.clear();
            xpos.clear();
            ypos.clear();
        }
    }

    private int loopData = 0;
    private void setLoop(JTextField text) {
        if (loopData == 0)
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
        else if (loopData > 0)
            sequencer.setLoopCount(loopData - 1);
        else
            text.setText("Не корректный ввод!");
    }

    private void saveData() {
        try {
            var output = new FileOutputStream("file.save");
            var writer = new ObjectOutputStream(output);
            boolean [] checks = new boolean[256];
            for (int i = 0; i < 256; i++) {
                checks[i] = checkboxes.get(i).isSelected();
            }
            // random, количесвто циклов, цвето музыка
            writer.writeObject(checks);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {
        try {
            var input = new FileInputStream("file.save");
            var reader = new ObjectInputStream(input);
            boolean[] checks = (boolean[]) reader.readObject();
            for (int i = 0; i < 256; i++) {
                checkboxes.get(i).setSelected(checks[i]);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void buildTrackAndStart() {
        try {
            var seq = new Sequence(Sequence.PPQ, 4);
            var track = seq.createTrack();
            for (int i = 0; i < 16; i++) {
                var arr = new int[16];
                for (int j = 0; j < 16; j++) {
                    var checkbox = checkboxes.get(j + i * 16);
                    if (checkbox.isSelected())
                        arr[j] = instruments[i];
                    else
                        arr[j] = 0;
                }
                makeTrack(track, arr);
                track.add(makeEvent(176, 1, 127, 0, 16));
            }
            track.add(makeEvent(192, 9, 1, 0, 15));
            sequencer.setSequence(seq);
            setLoop(text);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    private void makeTrack(Track track, int[] arr) {
        for (int i = 0; i < 16; i++) {
            if (arr[i] != 0) {
                track.add(makeEvent(144, 9, arr[i], 100, i));
                track.add(makeEvent(128, 9, arr[i], 100, i + 1));
            }
        }
    }

    private MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
        ShortMessage message = new ShortMessage();
        try {
            message.setMessage(command, channel, one, two);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return new MidiEvent(message, tick);
    }

    public static void main(String[] args) {
        new BeatBox();
    }

}
