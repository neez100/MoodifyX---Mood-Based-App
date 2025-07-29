package com.moodifyx;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;



public class MoodSelectorUI extends JFrame {
    private final MoodRepository repo = new MoodRepository();

    public MoodSelectorUI(){
      setTitle("MoodifyX" + "\nChoosing music based on your mood. Some Dope Stuff right here");
      setSize(400,400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

      setupUI();
      setVisible(true);
    }

    private void setupUI() {
        Set<String> moods = repo.getAllMoods();
        JComboBox<String> moodDropdown = new JComboBox<>(moods.toArray(new String[0]));
        JButton detectMoodBtn = new JButton("Recommend Music");

        detectMoodBtn.addActionListener(_ -> {
            String mood = (String) moodDropdown.getSelectedItem();

            while(true){
                List<Song> songs = repo.getSongsForMood(mood);
                Song selected = songs.get(ThreadLocalRandom.current().nextInt(songs.size()));
                SongPlayer.play(selected.getFilePath());

                 Object[] options = {" Another Song", "Change Mood", "Stop Music & Exit"};
                 int choice = JOptionPane.showOptionDialog(
                    this,
                    "Now Playing: " + selected.getTitle() + "\nMood: " + mood, "Your Mood Music",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                 );
                 SongPlayer.stop();
                 if(choice == JOptionPane.YES_OPTION) {
                    continue;
                 }else if(choice == JOptionPane.NO_OPTION) {
                    break;
                 }else {
                    System.exit(0);
                 }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Your Mood"));
        panel.add(moodDropdown);
        panel.add(detectMoodBtn);
        add(panel);


    }
    
}
